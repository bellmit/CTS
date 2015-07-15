<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
  Portlet 8.0 Migration Activity: h:commandButton with param is not supported in portal 8.0, replacing with myfaces t:commandButton tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%--@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"--%>

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

/*Begin - Modify for defect ESPRD00709317 Cursor focus */
var thisForm = 'view<portlet:namespace/>:srEntFrm';
function focusThis(id) { 
		document.getElementById(thisForm+':cursorFocusId').value=id;		  			
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
	if((thisForm+':cursorFocusId').value=='' || (thisForm+':cursorFocusId').value==undefined){
		document.getElementById('view<portlet:namespace/>:srEntFrm:entityTypSrch').focus();
		
	}
	}

/*Ends - Modify for defect ESPRD00709317 Cursor focus */

function focusButton()
	{
	  var fbid1 ='view<portlet:namespace/>:srEntFrm:buttonEntSearch'; 
	  if (fbid1 != null && document.getElementById(fbid1)!= null) 
	  {
		if (window.event.keyCode == 13)
		{	
			document.getElementById(fbid1).focus();
		}
	  }
	}

	onerror=function(e){
	//alert("err");
	}
</script>
<%--
<
--%>
<f:view>
	<%--hx:scriptCollector--%>
	<h:form id="srEntFrm">
		<t:saveState id="CMGTTOMSS553" value="#{CMEntitySearchDataBean}"></t:saveState>
		<t:saveState id="CMGTTOMSS554"
			value="#{CMEntitySearchDataBean.entitySearchCriteriaVO}"></t:saveState>
		<t:saveState id="CMGTTOMSS555"
			value="#{CMEntitySearchDataBean.entityDropDownList}"></t:saveState>
		<t:saveState id="CMGTTOMSS556"
			value="#{CMEntitySearchDataBean.entityIDTypeList}"></t:saveState>
		<t:saveState id="CMGTTOMSS557"
			value="#{CMEntitySearchDataBean.provTypeList}"></t:saveState>
		<t:saveState id="CMGTTOMSS558"
			value="#{CMEntitySearchDataBean.lobList}"></t:saveState>
		<t:saveState id="CMGTTOMSS559"
			value="#{CMEntitySearchDataBean.searchResultsList}"></t:saveState>
		<t:saveState id="CMGTTOMSS560"
			value="#{CMEntitySearchDataBean.renderProvider}"></t:saveState>
		<t:saveState id="CMGTTOMSS561"
			value="#{CMEntitySearchDataBean.renderNameSection}"></t:saveState>
		<t:saveState id="CMGTTOMSS562"
			value="#{CMEntitySearchDataBean.renderOrganizationName}"></t:saveState>
		<t:saveState id="CMGTTOMSS563"
			value="#{CMEntitySearchDataBean.renderCarrierNam}"></t:saveState>
		<t:saveState id="CMGTTOMSS564"
			value="#{CMEntitySearchDataBean.renderSearchResult}"></t:saveState>
		<t:saveState id="CMGTTOMSS565"
			value="#{CMEntitySearchDataBean.itemsPerPage}"></t:saveState>
		<t:saveState id="CMGTTOMSS566"
			value="#{CMEntitySearchDataBean.currentPage}"></t:saveState>
		<t:saveState id="CMGTTOMSS567"
			value="#{CMEntitySearchDataBean.numberOfPages}"></t:saveState>
		<t:saveState id="CMGTTOMSS568"
			value="#{CMEntitySearchDataBean.startRecord}"></t:saveState>
		<t:saveState id="CMGTTOMSS569"
			value="#{CMEntitySearchDataBean.showPrevious}"></t:saveState>
		<t:saveState id="CMGTTOMSS570"
			value="#{CMEntitySearchDataBean.showNext}"></t:saveState>


		<%--<m:inputHidden name="send.Entity.Id" value="sendEntityId"></m:inputHidden> UC-PGM-CRM-033_ESPRD00624909_09jun2011--%>
		<m:inputHidden value="#{CMEntitySearchDataBean.loadRefernceData}"></m:inputHidden>
		<m:inputHidden value="#{CMEntitySearchControllerBean.intialData}"></m:inputHidden>
		<m:inputHidden value="#{CMEntitySearchControllerBean.cancelValue}"></m:inputHidden>
		<%-- UC-PGM-CRM-033_ESPRD00624909_09jun2011 --%>
		<m:div
			rendered="#{!(CMEntitySearchDataBean.callFromSrchCorr || CMEntitySearchDataBean.callFromSrchEDMS)}">
			<m:div rendered="#{!(CMEntitySearchDataBean.callFromSearchCase)}">
				<%-- onload cursor focus --%>
				<h:inputHidden id="cursorFocusId"
					value="#{CMEntitySearchDataBean.cursorFocusId}"></h:inputHidden>
				<%-- onload cursor focus --%>
				<m:inputHidden name="send.Entity.Id" value="sendEntityId"></m:inputHidden>
			</m:div>
		</m:div>
		<%--EOF UC-PGM-CRM-033_ESPRD00624909_09jun2011 --%>
		<h:commandButton id="entityTypeChangedHiddenButton" styleClass="hide"
			value="Hidden Button To Click"
			action="#{CMEntitySearchControllerBean.renderRespEntityBlock}" />




		<m:body onkeypress="focusButton();" onload="javascript:onLoadFocus();">


			<m:div styleClass="moreInfoBar">
				<m:div styleClass="infoTitle" align="left">
					<m:span styleClass="required">
						<h:outputText id="PRGCMGTOT1829" value="#{ent['label-sw-reqFld']}"
							styleClass="colorMaroon" />
					</m:span>

				</m:div>

				<m:div styleClass="infoActions">

					<%--Calls javascript method which  in turn will call an mehod to clear the data in MaintainEntityPage --%>
					<%--<h:commandLink id="ipctoME"						action="#{CMEntitySearchControllerBean.addEntityFromSearchEntity}" 						rendered="#{CMEntitySearchControllerBean.controlRequired && !CMEntitySearchDataBean.callFromSrchCorr}">
						<h:outputText id="PRGCMGTOT1830" styleClass="strong"							value="#{msg['title.label.entity.addEntity']}"></h:outputText>
							<f:param name="send.Entity.Id"
												value="sendEntityId"></f:param>
				 	</h:commandLink>
				--%>
					<%--Code Modified Below to show Add Entity link for the CR ESPRD00831502--%>
					<t:commandButton id="ipctoME"
						action="#{CMEntitySearchControllerBean.addEntityFromSearchEntity}"
						rendered="#{!CMEntitySearchDataBean.callFromSrchEDMS}"
						styleClass="strong"
						style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:14px;WIDTH:70px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal;position:relative;top:2px"
						value="#{msg['title.label.entity.addEntity']}"
						disabled="#{!CMEntitySearchControllerBean.controlRequired}">
						<f:param name="send.Entity.Id" value="sendEntityId"></f:param>
					</t:commandButton>
				<%-- 	<h:outputLink id="PRGCMGTOLK35"
						value="/wps/myportal/MaintainEntity"
						rendered="#{CMEntitySearchControllerBean.controlRequired && CMEntitySearchDataBean.callFromSrchEDMS}">
						<h:outputText id="PRGCMGTOT1831" styleClass="strong"
							value="#{msg['title.label.entity.addEntity']}"></h:outputText>
					</h:outputLink> --%>
					<m:div id="IPRGCMGTOLK35" rendered="#{CMEntitySearchControllerBean.controlRequired && CMEntitySearchDataBean.callFromSrchEDMS}">
						<f:verbatim><a href="/wps/myportal/MaintainEntity" id="PRGCMGTOLK35">
						</f:verbatim>
						<h:outputText id="PRGCMGTOT1831" styleClass="strong" value="#{msg['title.label.entity.addEntity']}"></h:outputText>
						<f:verbatim></a></f:verbatim>
					</m:div>	
					<h:outputLabel id="srchLink3"
						value="#{msg['title.label.entity.addEntity']}"
						rendered="#{!CMEntitySearchControllerBean.controlRequired && CMEntitySearchDataBean.callFromSrchEDMS}" />


				<%-- 	<h:outputLink id="PRGCMGTOLK36"
						value="/wps/myportal/FromSearchCase">
						<h:outputText id="PRGCMGTOT1832"
							rendered="#{CMEntitySearchDataBean.callFromSearchCase}"
							value="Cancel"></h:outputText>
					</h:outputLink>  --%>

                   <%--Code Commented Below to remove cancel link on Entity Search Page for the Defect ESPRD00833631 --%>
                   
					<%--  <f:verbatim>  <a href="/wps/myportal/FromSearchCase" id="PRGCMGTOLK36" > </f:verbatim>
						<h:outputText id="PRGCMGTOT1832" rendered="#{CMEntitySearchDataBean.callFromSearchCase}" value="Cancel"></h:outputText>
					<f:verbatim></a></f:verbatim> --%>
					
					<%-- 	<h:outputLink id="PRGCMGTOLK37" value="/wps/myportal/FromLogCase">
						<h:outputText id="PRGCMGTOT1833"
							rendered="#{CMEntitySearchDataBean.callFromLogSrchCase}"
							value="Cancel"></h:outputText>
					</h:outputLink>  --%>
					
					<%--Code Commented Below to remove cancel link on Entity Search Page for the Defect ESPRD00833631 --%>
					
					<%--<f:verbatim>  <a href="/wps/myportal/FromLogCase" id="PRGCMGTOLK37"> </f:verbatim>
						<h:outputText id="PRGCMGTOT1833" rendered="#{CMEntitySearchDataBean.callFromLogSrchCase}" value="Cancel"></h:outputText>
					<f:verbatim></a></f:verbatim>--%>

				</m:div>
			</m:div>
			
			<h:messages id="PRGCMGTMS35" showDetail="true" layout="table"
				showSummary="false" styleClass="errorMessage" />
			
			
			<%-- Start of 1st section --%>
			<m:div styleClass="floatContainer">
				<m:div styleClass="fullwidth">
					<m:div styleClass="floatContainer">
						<m:div styleClass="leftCol33">
							<m:section id="PROVIDERMMS20120731164811698">
								<m:pod title="#{ref['label.ref.searchCriteria']}"
									styleClass="otherbg">
									<%--h:panelGroup id="group1"
											rendered="#{CMEntitySearchControllerBean.ajaxrenderRespEntityBlock}"--%>
									<m:div>
										<m:table id="PROVIDERMMT20120731164811699" width="100%">
											<m:tr>
												<m:td colspan="2">
													<m:span styleClass="required">
														<h:outputText id="PRGCMGTOT1834"
															value="#{ref['label.ref.reqSymbol']}" />
													</m:span>
													<h:outputLabel id="PRGCMGTOLL787" for="entityTypSrch"
														value="#{msg['label.entity.entityType']}">
													</h:outputLabel>

													<m:br />
													<t:selectOneMenu id="entityTypSrch"
													 
														onchange="javascript:entityTypeChangeAction();"
													
														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.entityType}">
														<f:selectItem itemLabel="Please Select One" itemValue="" />
														<f:selectItems
															value="#{CMEntitySearchDataBean.entityDropDownList}" />

													</t:selectOneMenu>
													<%--<hx:behavior event="onchange" behaviorAction="get"
																targetAction="group1" target="entityTypSrch"></hx:behavior--%>
													<m:br />
													<h:message id="PRGCMGTM253" for="entityTypSrch"
														showDetail="true" styleClass="errorMessage" />
												</m:td>
												<h:graphicImage id="PROVIDERGI20120731164811700" styleClass="blankImage" width="1" height="5"
													alt="" url="/images/x.gif" />
												<%--ADDED FOR THE Correspondence ESPRD00436016 --%>
												<m:td
													rendered="#{CMEntitySearchDataBean.renderTradingPartner}">
													<m:span styleClass="required">
														<h:outputText id="PRGCMGTOT1835"
															value="#{ref['label.ref.reqSymbol']}" />
													</m:span>
													<h:outputLabel id="PRGCMGTOLL788" for="statusap"
														value="#{msg['label.entity.status']}">
													</h:outputLabel>
													<m:br />
													<h:selectOneMenu id="statusap"
														onchange="javascript:focusThis(this.id);"
														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.tradingPartnerStatus}">
														<f:selectItem itemLabel="Please Select One" itemValue="" />
														<f:selectItems
															value="#{CMEntitySearchDataBean.tradingParnterStatusList}" />

													</h:selectOneMenu>
													<m:br />
													<h:message id="PRGCMGTM254" for="statusap" showDetail="ue"
														styleClass="errorMessage" />
												</m:td>
												<%--END FOR THE Correspondence ESPRD00436016 --%>
												<m:td rendered="#{CMEntitySearchDataBean.renderProvider}">
													<h:outputLabel id="PRGCMGTOLL789" for="statusprov"
														value="#{msg['label.entity.status']}">
													</h:outputLabel>

													<t:selectOneRadio id="statusprov" enabledClass="black"
														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.providerStatus}">

														<f:selectItem itemLabel="#{msg['label.entity.active']}"
															itemValue="A" />
														<f:selectItem itemLabel="#{msg['label.entity.inactive']}"
															itemValue="I" />
													</t:selectOneRadio>

												</m:td>
											</m:tr>
										</m:table>
									</m:div>
									<%-- Common to all --%>
									<m:div id="showhide_searchby_provunenrolled">
										<m:div>
											<m:table id="PROVIDERMMT20120731164811701">
												<m:tr>
													<m:td>
														<h:outputLabel id="PRGCMGTOLL790" for="enid"
															value="#{msg['label.entity.EntityIDType']}" />
														<m:br></m:br>
														<t:selectOneMenu id="enid"
															value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.entityIDType}">
															<f:selectItem itemLabel="Please Select One" itemValue="" />
															<f:selectItems
																value="#{CMEntitySearchDataBean.entityIDTypeList}" />
														</t:selectOneMenu>
													</m:td>
													<m:td>
														<h:outputLabel id="PRGCMGTOLL791" for="EntIDSrch"
															value="#{msg['label.entity.EntityID']}" />
														<m:br></m:br>
														<t:inputText id="EntIDSrch" size="10"
															value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.entityID}"
															onkeyup="this.value=this.value.toUpperCase();"
															onblur="this.value=this.value.toUpperCase();"></t:inputText>
														<m:br />
														<h:message id="PRGCMGTM255" for="EntIDSrch"
															showDetail="true" styleClass="errorMessage" />
													</m:td>
												</m:tr>
											</m:table>
										</m:div>
									</m:div>

									<%-- For Entity Type - Provider -- render this div for provider --%>
									<m:div rendered="#{CMEntitySearchDataBean.renderProvider}">
										<m:table id="PROVIDERMMT20120731164811702" width="95">
											<m:tr>
												<m:td colspan="3">
													<h:outputLabel id="PRGCMGTOLL792" for="selectProviderType"
														value="#{msg['label.entity.ProviderType']}" />
													<m:br></m:br>
													<t:selectOneMenu id="selectProviderType"
														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.providerType}">
														<f:selectItem itemLabel="Please Select One" itemValue="" />
														<f:selectItems
															value="#{CMEntitySearchDataBean.provTypeList}" />
													</t:selectOneMenu>
												</m:td>
											</m:tr>
											<m:tr>

												<m:td>
													<h:outputLabel id="PRGCMGTOLL793" for="provSrtnam"
														value="#{msg['label.entity.ProviderSortNam']}" />
													<m:br></m:br>
													<t:inputText id="provSrtnam" size="6"
														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.providerSortName}"
														maxlength="35"></t:inputText>

													<h:message id="PRGCMGTM256" for="provSrtnam"
														showDetail="true" styleClass="errorMessage" />
												</m:td>
												<m:td>
													<h:outputLabel id="PRGCMGTOLL794" for="provDBA"
														value="#{msg['label.entity.ProviderDBA']}" />
													<m:br></m:br>
													<t:inputText id="provDBA" size="5"
														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.providerDBA}"
														maxlength="35"></t:inputText>
												</m:td>
												<%--  
															Author 	: Infinite
															CR		: 1925
															Date	: 30/12/2008
														 --%>
												<m:td rendered="#{! CMEntitySearchDataBean.renderProvider}">
													<h:outputLabel id="PRGCMGTOLL795" for="lobdropdowns"
														value="#{msg['label.entity.Lob']}" />
													<m:br></m:br>
													<m:br></m:br>
													<t:selectOneMenu id="lobdropdowns"
														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.lineOfBusiness}">

														<f:selectItems value="#{CMEntitySearchDataBean.lobList}" />
													</t:selectOneMenu>
												</m:td>
											</m:tr>
										</m:table>
									</m:div>
									<%--ADDED FOR THE Correspondence ESPRD00436016 --%>
									<m:div id="aPatdiv1"
										rendered="#{CMEntitySearchDataBean.renderTradingPartner}">
										<m:table id="aPatTable" width="95">

											<m:tr>
												<m:td id="TPtd2">
													<h:outputLabel id="PRGCMGTOLL796" for="Pclassi"
														value="#{msg['label.entity.tradingpartner.classification']}" />
													<m:br></m:br>
													<h:selectOneMenu id="Pclassi"
														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.tradingPartnerClassification}"
														onmousedown="javascript:flagWarn=false;">
														<f:selectItem itemLabel="Please Select One" itemValue="" />
														<f:selectItems
															value="#{CMEntitySearchDataBean.tradingPartnerclassificationList}" />
													</h:selectOneMenu>
													<m:br></m:br>
													<h:message id="PRGCMGTM257" for="Pclassi" showDetail="ue"
														styleClass="errorMessage" />
												</m:td>
											</m:tr>
											<m:tr>
												<m:td id="Ptd1">
													<h:outputLabel id="Plabel1" for="PNameSearch"
														value="#{msg['label.entity.tradingpartner.Name']}" />

													<m:br id="Pbr1"></m:br>
													<h:inputText id="PNameSearch" size="10"
														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.tradingPartnerName}"
														maxlength="35"></h:inputText>
													<m:br id="Pbr2"></m:br>
													<h:message id="Pmsg1" for="PNameSearch" showDetail="ue"
														styleClass="errorMessage" />
												</m:td>
											</m:tr>
										</m:table>
									</m:div>
									<%--END FOR THE Correspondence ESPRD00436016 --%>
									<%-- Render this div for  Member ,Provider ,UP ,UM --%>
									<%--for fixing defect ESPRD00603442 --%>
									<m:div id="showhide_searchby_other"
										rendered="#{CMEntitySearchDataBean.renderNameSection}">
										<m:table id="PROVIDERMMT20120731164811703">
											<m:tr>
												<m:td>
													<h:outputLabel id="PRGCMGTOLL797" for="fnam"
														value="#{msg['label.entity.FirstName']}" />

													<m:br></m:br>
													<t:inputText id="fnam" size="10"
														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.firstName}"
														maxlength="25"></t:inputText>
													<m:br></m:br>
													<h:message id="PRGCMGTM258" for="fnam" showDetail="true"
														styleClass="errorMessage" />
												</m:td>
												<m:td>
													<h:outputLabel id="PRGCMGTOLL798" for="mi_na"
														value="#{msg['label.entity.MI']}" />

													<m:br></m:br>
													<t:inputText id="mi_na" size="2" maxlength="1"
														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.middleInitial}"></t:inputText>
													<m:br></m:br>
													<h:message id="PRGCMGTM259" for="mi_na" showDetail="true"
														styleClass="errorMessage" />
												</m:td>
												<m:td>
													<m:span styleClass="required">
														<h:outputText id="PRGCMGTOT1836"
															value="#{ref['label.ref.reqSymbol']}" />
													</m:span>
													<h:outputLabel id="PRGCMGTOLL799" for="LNameSearch"
														value="#{msg['label.entity.LastName']}" />

													<m:br></m:br>
													<t:inputText id="LNameSearch" size="10"
														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.lastName}"
														maxlength="35"></t:inputText>
													<m:br></m:br>
													<h:message id="PRGCMGTM260" for="LNameSearch"
														showDetail="true" styleClass="errorMessage" />
												</m:td>
											</m:tr>
										</m:table>
									</m:div>

									<m:div id="showhide_orgnam"
										rendered="#{CMEntitySearchDataBean.renderOrganizationName || CMEntitySearchDataBean.countyName}">
										<m:table id="PROVIDERMMT20120731164811704">
											<m:tr>
												<h:outputLabel id="PRGCMGTOLL800" for="orgNCD"
													value="#{msg['label.entity.OrgName']}"
													rendered="#{CMEntitySearchDataBean.renderOrganizationName}" />
												<h:outputLabel id="PRGCMGTOLL801" for="orgNCD"
													value="#{msg['label.entity.OrgName']}"
													rendered="#{CMEntitySearchDataBean.countyName}" />
													<m:br/>
												<t:inputText id="orgNCD" size="15" maxlength="50"
													value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.organisationName}"></t:inputText>
													
													<%--Code added below for fixing the Defect DEFECT_ESPRD00778011 on 27-04-2012 --%>
													
													<br/>
												<h:message id="PRGCMGTM261" for="orgNCD" showDetail="true"
													styleClass="errorMessage" />
											</m:tr>
										</m:table>
									</m:div>
									

									<m:div id="showhide_carrnam"
										rendered="#{CMEntitySearchDataBean.renderCarrierNam}">
										<m:table id="PROVIDERMMT20120731164811705">
											<m:tr>
												<h:outputLabel id="PRGCMGTOLL802" for="carriernam"
													value="#{msg['label.entity.CarrierName']}" />
												<t:inputText id="carriernam" size="15" maxlength="40"
													value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.carrierName}"></t:inputText>
												<h:message id="PRGCMGTM262" for="carriernam"
													showDetail="true" styleClass="errorMessage" />
											</m:tr>
										</m:table>
									</m:div>

									<%-- ESPRD0033432_21112009 --%>
									<%-- <m:div rendered="#{CMEntitySearchDataBean.callFromLogCase}">
												<m:div rendered="#{CMEntitySearchDataBean.ipcFlag}">
													<m:inputHidden name="EntSrchCrsSrch_ACTION"
														value="EntSrchCrsSrch_SOURCE_ACTION"></m:inputHidden>
												</m:div>
											</m:div> --%>
									<m:div rendered="#{CMEntitySearchDataBean.callFromLogCase}">
										<m:div rendered="#{CMEntitySearchDataBean.ipcFlag}">
											<m:inputHidden name="EntSrchtoLogCase_ACTION"
												value="EntSrchtoLogCase_SOURCE_ACTION"></m:inputHidden>
										</m:div>
									</m:div>
									<m:div rendered="#{CMEntitySearchDataBean.callFromSearchCase}">
										<m:div rendered="#{CMEntitySearchDataBean.ipcFlag}">
											<m:inputHidden name="EntSrchCrsSrch_ACTION"
												value="EntSrchCrsSrch_SOURCE_ACTION"></m:inputHidden>
										</m:div>
									</m:div>
									<%-- end  --%>
									<m:div
										rendered="#{CMEntitySearchDataBean.callFromSrchCorr || CMEntitySearchDataBean.callFromSrchEDMS}">
										<m:div rendered="#{CMEntitySearchDataBean.ipcFlag}">
											<m:inputHidden name="EntSrchCrsSrch_ACTION"
												value="EntSrchCrsSrch_SOURCE_ACTION"></m:inputHidden>
										</m:div>
										<m:div rendered="#{!CMEntitySearchDataBean.ipcFlag}">
											<m:inputHidden name="SrcEntLogCrs_ACTION"
												value="SrcEntLogCrs_SOURCE_ACTION"></m:inputHidden>
										</m:div>
									</m:div>
									<m:div rendered="#{!CMEntitySearchDataBean.callFromSrchCorr}">
										<m:inputHidden name="com.ibm.portal.propertybroker.action"
											value="sendPSysID"></m:inputHidden>
									</m:div>

									<%-- CR_ESPRD00486064_UC-PGM-CRM-013_08JUL2010 --%>
									<m:div id="showhide_tplPolcyHldr"
										rendered="#{CMEntitySearchDataBean.renderTPLPlcyHldr}">
										<m:table id="payeeTableId" width="95">
											<%--m:tr id="payeeTrId">
												<m:td id="payeeTD" colspan="2">
													<h:outputLabel id="tplPayeeOutputLabl" for="tplPayee"														value="#{msg['label.entity.Payee']}" />
													<m:br id="payeeMbr1" />
													<h:inputText id="tplPayeeInputTxt" size="6"														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.policyHolderPayeeID}"  maxlength="35"></h:inputText>
													<m:br id="payeeMbr2" />
													<h:message id="tplPayeeMSG" for="tplPayee" showDetail="true"														styleClass="errorMessage" />
												</m:td>
											</m:tr>
											<m:tr id="placeHolderIDTrID">
												<m:td id="placeHolderIDdId" colspan="2">
												<h:outputLabel id="placeHolderIDOutputLablID" for="placeHolderID"													value="#{msg['label.entity.policyholder.id']}" />
												<m:br id="placeHolderIDBrId1" />
												<h:inputText id="placeHolderID" size="5" 													value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.policyHolderID}"  maxlength="35"></h:inputText>
												<m:br id="placeHolderIDBrId2" />
												<h:message id="placeHolderIDMsgId" for="placeHolderID" showDetail="true"													styleClass="errorMessage" />
												</m:td>
											</m:tr--%>
											<m:tr id="policyHolderLastNameTrId">
												<m:td id="policyHolderFirstNameTdId">
													<%--h:outputLabel id="policyHolderFirstNameOutLablID" for="policyHolderFirstName"
														value="#{msg['label.entity.policyholder.FirstName']}" /--%>
													<h:outputLabel id="policyHolderFirstNameOutLablID"
														for="policyHolderFirstName"
														value="#{msg['label.entity.FirstName']}" />

													<m:br id="policyHolderFirstNameBrId1" />
													<h:inputText id="policyHolderFirstName" size="10"
														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.policyHolderFirstName}"
														maxlength="25"></h:inputText>
													<m:br id="policyHolderFirstNameBrId2" />
													<h:message id="policyHolderFirstNameMsgID"
														for="policyHolderFirstName" showDetail="true"
														styleClass="errorMessage" />
												</m:td>

												<m:td id="policyHolderLastNameTdId">
													<%--h:outputLabel id="policyHolderLastNameOutLablId" for="TPLNameSearch"
														value="#{msg['label.entity.policyholder.LastName']}" /--%>
													<m:span styleClass="required">
														<h:outputText id="PRGCMGTOT1837"
															value="#{ref['label.ref.reqSymbol']}" />
													</m:span>
													<h:outputLabel id="policyHolderLastNameOutLablId"
														for="TPLNameSearch"
														value="#{msg['label.entity.LastName']}" />
													<m:br id="policyHolderLastNameBrId1" />
													<h:inputText id="TPLNameSearch" size="10"
														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.policyHolderLastName}"
														maxlength="35"></h:inputText>
													<m:br id="policyHolderLastNameBrId2" />
													<h:message id="policyHolderLastNameMsgId"
														for="TPLNameSearch" showDetail="true"
														styleClass="errorMessage" />
												</m:td>
											</m:tr>
										</m:table>
									</m:div>
									<%--UC-PGM-CRM-033_ESPRD00624909_09jun2011 --%>
									<m:div
										rendered="#{!(CMEntitySearchDataBean.callFromSrchCorr || CMEntitySearchDataBean.callFromSrchEDMS)}">
										<m:div
											rendered="#{!(CMEntitySearchDataBean.callFromSearchCase)}">
											<m:inputHidden name="send.Entity.Id" value="sendEntityId"></m:inputHidden>
										</m:div>
									</m:div>
									<%--EOF UC-PGM-CRM-033_ESPRD00624909_09jun2011 --%>
									<%-- EOF CR_ESPRD00486064_UC-PGM-CRM-013_08JUL2010 --%>
									<m:div styleClass="buttonRow">
										<h:commandButton styleClass="formBtn" id="buttonEntSearch"
											value="#{ref['label.ref.search']}"
											action="#{CMEntitySearchControllerBean.getEntities}" />
										<h:outputText id="PRGCMGTOT1838" escape="false"
											value="#{ref['label.ref.singleSpace']}" />
										<h:outputText id="PRGCMGTOT1839" escape="false"
											value="#{ref['label.ref.singleSpace']}" />
										<h:commandButton id="PRGCMGTCB94" styleClass="formBtn"
											value="#{ent['label-sw-reset']}"
											action="#{CMEntitySearchControllerBean.resetSearch}" />

									</m:div>

									<%--</h:panelGroup>
										<hx:ajaxRefreshRequest id="ajaxRefreshRequest1"
											target="group1" params="entityTypSrch"></hx:ajaxRefreshRequest--%>

								</m:pod>
							</m:section>

						</m:div>



						<m:div styleClass="rightCol66"
							rendered="#{CMEntitySearchDataBean.renderSearchResult}">

							<m:div id="searchResults">
								<m:br />
								
								<%--rowOnClick="firstChild.lastChild.click();" is removed below for the defect  ESPRD00674544--%>

								<t:dataTable cellspacing="0" styleClass="dataTable" id="entSrch"
									width="100%" columnClasses="columnClass"
									headerClass="headerClass" footerClass="footerClass"
									rowClasses="row_alt,row" rows="10"
									value="#{CMEntitySearchDataBean.searchResultsList}"
									first="#{CMEntitySearchDataBean.startIndexForSrchEntity}"
									var="srchResult" rowIndexVar="rowIndex"
									rowOnMouseOver="this.origClassName = this.className;this.className='row_mouse row_link';"
									rowOnMouseOut="if (this.origClassName) this.className = this.origClassName;">




									<h:column id="entidcol"
										rendered="#{CMEntitySearchDataBean.renderEntity}">

										<f:facet name="header">

											<h:panelGrid id="PRGCMGTPGD259" columns="2">
												<h:outputLabel id="PRGCMGTOLL803" for="dataColEntId"
													value="#{msg['label.entity.EntityID']}">
												</h:outputLabel>
												<h:panelGroup id="dataColEntId">
													<t:div styleClass="alignLeft">
														<t:commandLink id="entCommandLink1"
															actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"
															rendered="#{CMEntitySearchDataBean.imageRender !='entCommandLink1'}">
															<m:div title="for ascending" styleClass="sort_asc" />
															<f:attribute name="columnName" value="entIdVal" />
															<f:attribute name="sortOrder" value="asc" />
														</t:commandLink>
													</t:div>
													<t:div styleClass="alignLeft">
														<t:commandLink id="entCommandLink2"
															actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"
															rendered="#{CMEntitySearchDataBean.imageRender !='entCommandLink2'}">
															<m:div title="for descending" styleClass="sort_desc" />
															<f:attribute name="columnName" value="entIdVal" />
															<f:attribute name="sortOrder" value="desc" />
														</t:commandLink>
													</t:div>
												</h:panelGroup>
											</h:panelGrid>
										</f:facet>
										<%--UC-PGM-CRM-033_ESPRD00624909_09jun2011--%>
										<t:commandLink id="edit"
											rendered="#{(!(CMEntitySearchDataBean.callFromSrchCorr || CMEntitySearchDataBean.callFromSrchEDMS))
												&&!(CMEntitySearchDataBean.callFromSearchCase)}"
											action="#{CMEntitySearchControllerBean.getCMEntityDetails}">
											<h:outputText id="a" value="#{srchResult.entityID}" />
											<f:param name="systemIDVal" value="#{srchResult.systemID}"></f:param>
											<f:param name="entiyIDVal" value="#{srchResult.entityID}"></f:param>
											<f:param name="commonEntSKVal"
												value="#{srchResult.commonEntitySK}"></f:param>
											<f:param name="send.Entity.Id" value="sendEntityId"></f:param>
											<f:param name="indexCode" value="#{rowIndex}"></f:param>

											<%-- 	<f:param name="EntSrchtoLogCase_ACTION"
													value="EntSrchtoLogCase_SOURCE_ACTION"></f:param>--%>
										</t:commandLink>
										<t:commandLink id="edit3"
											rendered="#{(!(CMEntitySearchDataBean.callFromSrchCorr || CMEntitySearchDataBean.callFromSrchEDMS))
												&&(CMEntitySearchDataBean.callFromSearchCase)}"
											action="#{CMEntitySearchControllerBean.getCMEntityDetails}">
											<h:outputText id="a3" value="#{srchResult.entityID}" />
											<f:param name="systemIDVal" value="#{srchResult.systemID}"></f:param>
											<f:param name="entiyIDVal" value="#{srchResult.entityID}"></f:param>
											<f:param name="commonEntSKVal"
												value="#{srchResult.commonEntitySK}"></f:param>
											<f:param name="indexCode" value="#{rowIndex}"></f:param>
										</t:commandLink>
										<t:commandLink id="edit2"
											rendered="#{(CMEntitySearchDataBean.callFromSrchCorr || CMEntitySearchDataBean.callFromSrchEDMS)}"
											action="#{CMEntitySearchControllerBean.getCMEntityDetails}">
											<h:outputText id="a2" value="#{srchResult.entityID}" />
											<f:param name="systemIDVal" value="#{srchResult.systemID}"></f:param>
											<f:param name="entiyIDVal" value="#{srchResult.entityID}"></f:param>
											<f:param name="EntSrchCrsSrch_ACTION"
												value="EntSrchCrsSrch_SOURCE_ACTION"></f:param>
											<f:param name="indexCode" value="#{rowIndex}"></f:param>
										</t:commandLink>
										<%--EOF UC-PGM-CRM-033_ESPRD00624909_09jun2011--%>
									</h:column>

									<h:column id="noteen"
										rendered="#{CMEntitySearchDataBean.renderNotes}">
										<f:facet name="header">
											<h:panelGrid id="PRGCMGTPGD260" columns="2">
												<h:outputLabel id="PRGCMGTOLL804"
													value="#{msg['label.entity.EntityID']}">
												</h:outputLabel>
												<h:panelGroup id="PRGCMGTPGP145">
													<t:div styleClass="alignLeft">
														<t:commandLink id="notesearch"
															actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"
															rendered="#{CMEntitySearchDataBean.imageRender !='entCommandLink1'}">
															<m:div title="for ascending" styleClass="sort_asc" />
															<f:attribute name="columnName" value="entIdVal" />
															<f:attribute name="sortOrder" value="asc" />
														</t:commandLink>
													</t:div>
													<t:div styleClass="alignLeft">
														<t:commandLink id="notesearch2"
															actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"
															rendered="#{CMEntitySearchDataBean.imageRender !='entCommandLink2'}">
															<m:div title="for descending" styleClass="sort_desc" />
															<f:attribute name="columnName" value="entIdVal" />
															<f:attribute name="sortOrder" value="desc" />
														</t:commandLink>
													</t:div>
												</h:panelGroup>
											</h:panelGrid>
										</f:facet>
										<t:commandLink id="notedt"
											action="#{CommonNotesControllerBean.getGlobalNotes}">
											<h:outputText id="ant" value="#{srchResult.entityID}" />
											<f:param name="systemIDVal" value="#{srchResult.systemID}"></f:param>
											<f:param name="entiyIDVal" value="#{srchResult.entityID}"></f:param>
											<f:param name="commonEntSKVal"
												value="#{srchResult.commonEntitySK}"></f:param>
											<f:param name="entityName" value="#{srchResult.entityName}"></f:param>
											<f:param name="entityType" value="#{srchResult.entityType}"></f:param>
											<f:param name="send.Entity.Id" value="sendEntityId"></f:param>
										</t:commandLink>
									</h:column>

									<h:column id="nameCol"
										rendered="#{!CMEntitySearchDataBean.countyName}">
										<f:facet name="header">

											<h:panelGrid id="PRGCMGTPGD261" columns="2">
												<h:outputLabel id="PRGCMGTOLL805" for="dataColentname"
													value="#{msg['label.entity.Name']}">
												</h:outputLabel>
												<h:panelGroup id="dataColentname">
													<t:div styleClass="alignLeft">
														<t:commandLink id="namCommandLink1"
															actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"
															rendered="#{CMEntitySearchDataBean.imageRender !='namCommandLink1'}">
															<m:div title="for ascending" styleClass="sort_asc" />
															<f:attribute name="columnName" value="namValue" />
															<f:attribute name="sortOrder" value="asc" />
														</t:commandLink>
													</t:div>
													<t:div styleClass="alignLeft">
														<t:commandLink id="namCommandLink2"
															actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"
															rendered="#{CMEntitySearchDataBean.imageRender !='namCommandLink2'}">
															<m:div title="for descending" styleClass="sort_desc" />
															<f:attribute name="columnName" value="namValue" />
															<f:attribute name="sortOrder" value="desc" />
														</t:commandLink>
													</t:div>
												</h:panelGroup>
											</h:panelGrid>
										</f:facet>

										<h:outputText id="namOutput" value="#{srchResult.entityName}" />


									</h:column>


									<h:column id="orgNameCol"
										rendered="#{CMEntitySearchDataBean.renderLOB}">
										<f:facet name="header">




											<h:panelGrid id="PRGCMGTPGD262" columns="2">
												<h:outputLabel id="PRGCMGTOLL806"
													value="#{msg['label.entity.orgName']}"
													rendered="#{!CMEntitySearchDataBean.countyName}">
												</h:outputLabel>
												<h:outputLabel id="PRGCMGTOLL807"
													value="#{msg['label.entity.countyName']}"
													rendered="#{CMEntitySearchDataBean.countyName}">
												</h:outputLabel>
												<h:panelGroup id="PRGCMGTPGP146">
													<t:div styleClass="alignLeft">
														<t:commandLink id="orgNameCommandLink1"
															actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"
															rendered="#{CMEntitySearchDataBean.imageRender !='orgNameCommandLink1'}">
															<m:div title="for ascending" styleClass="sort_asc" />
															<f:attribute name="columnName" value="orgNamValue" />
															<f:attribute name="sortOrder" value="asc" />
														</t:commandLink>
													</t:div>
													<t:div styleClass="alignLeft">
														<t:commandLink id="orgNamCommandLink2"
															actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"
															rendered="#{CMEntitySearchDataBean.imageRender !='orgNamCommandLink2'}">
															<m:div title="for descending" styleClass="sort_desc" />
															<f:attribute name="columnName" value="orgNamValue" />
															<f:attribute name="sortOrder" value="desc" />
														</t:commandLink>
													</t:div>
												</h:panelGroup>
											</h:panelGrid>
										</f:facet>

										<h:outputText id="orgNamOutput"
											value="#{srchResult.organisationName}" />


									</h:column>


									<h:column id="lobCol"
										rendered="#{!CMEntitySearchDataBean.renderLOB}">
										<f:facet name="header">

											<h:panelGrid id="PRGCMGTPGD263" columns="2">
												<h:outputLabel id="PRGCMGTOLL808" for="dataColLob"
													value="#{msg['label.entity.Lob']}">
												</h:outputLabel>
												<h:panelGroup id="dataColLob">
													<t:div styleClass="alignLeft">
														<t:commandLink id="lobCommandLink1"
															actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"
															rendered="#{CMEntitySearchDataBean.imageRender !='lobCommandLink1'}">
															<m:div title="for ascending" styleClass="sort_asc" />
															<f:attribute name="columnName" value="lobValue" />
															<f:attribute name="sortOrder" value="asc" />
														</t:commandLink>
													</t:div>
													<t:div styleClass="alignLeft">
														<t:commandLink id="lobCommandLink2"
															actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"
															rendered="#{CMEntitySearchDataBean.imageRender !='lobCommandLink2'}">
															<m:div title="for descending" styleClass="sort_desc" />
															<f:attribute name="columnName" value="lobValue" />
															<f:attribute name="sortOrder" value="desc" />
														</t:commandLink>
													</t:div>
												</h:panelGroup>
											</h:panelGrid>
										</f:facet>

										<h:outputText id="lobOutput"
											value="#{srchResult.lineOfBusiness}" />






									</h:column>


									<h:column id="addCol">
										<f:facet name="header">

											<h:panelGrid id="PRGCMGTPGD264" columns="2">
												<h:outputLabel id="PRGCMGTOLL809" for="dataColAdd"
													value="#{msg['label.entity.Address']}">
												</h:outputLabel>
												<h:panelGroup id="dataColAdd">
													<t:div styleClass="alignLeft">
														<t:commandLink id="addCommandLink1"
															actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"
															rendered="#{CMEntitySearchDataBean.imageRender !='addCommandLink1'}">
															<m:div title="for ascending" styleClass="sort_asc" />
															<f:attribute name="columnName" value="addValue" />
															<f:attribute name="sortOrder" value="asc" />
														</t:commandLink>
													</t:div>
													<t:div styleClass="alignLeft">
														<t:commandLink id="addCommandLink2"
															actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"
															rendered="#{CMEntitySearchDataBean.imageRender !='addCommandLink2'}">
															<m:div title="for descending" styleClass="sort_desc" />
															<f:attribute name="columnName" value="addValue" />
															<f:attribute name="sortOrder" value="desc" />
														</t:commandLink>
													</t:div>
												</h:panelGroup>
											</h:panelGrid>
										</f:facet>

										<h:outputText id="addOutput" value="#{srchResult.address}" />




									</h:column>

									<h:column id="cityCol">
										<f:facet name="header">

											<h:panelGrid id="PRGCMGTPGD265" columns="2">
												<h:outputLabel id="PRGCMGTOLL810" for="dataColCity"
													value="#{msg['label.entity.City']}">
												</h:outputLabel>
												<h:panelGroup id="dataColCity">
													<t:div styleClass="alignLeft">
														<t:commandLink id="cityCommandLink1"
															actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"
															rendered="#{CMEntitySearchDataBean.imageRender !='cityCommandLink1'}">
															<m:div title="for ascending" styleClass="sort_asc" />
															<f:attribute name="columnName" value="cityValue" />
															<f:attribute name="sortOrder" value="asc" />
														</t:commandLink>
													</t:div>
													<t:div styleClass="alignLeft">
														<t:commandLink id="cityCommandLink2"
															actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"
															rendered="#{CMEntitySearchDataBean.imageRender !='cityCommandLink2'}">
															<m:div title="for descending" styleClass="sort_desc" />
															<f:attribute name="columnName" value="cityValue" />
															<f:attribute name="sortOrder" value="desc" />
														</t:commandLink>
													</t:div>
												</h:panelGroup>
											</h:panelGrid>
										</f:facet>

										<h:outputText id="cityOutput" value="#{srchResult.city}" />
									</h:column>
								</t:dataTable>
								<m:div
									rendered="#{empty CMEntitySearchDataBean.searchResultsList}"
									align="center">
									<h:outputText id="PRGCMGTOT1840"
										value="#{ent['err-sw-search-no-record-found']}"></h:outputText>
								</m:div>

								<t:dataScroller id="CMGTTOMDS70" for="entSrch" paginator="true"
									paginatorActiveColumnStyle='font-weight:bold;'
									paginatorMaxPages="3" immediate="false"
									pageCountVar="pageCount" pageIndexVar="pageIndex"
									firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
									rowsCountVar="rowsCount" styleClass="scroller">
									<f:facet name="previous">
										<h:outputText id="PRGCMGTOT1841"
											value="#{ref['label.ref.lt']}" rendered="#{pageIndex > 1}">
										</h:outputText>
									</f:facet>
									<f:facet name="next">
										<h:outputText id="PRGCMGTOT1842"
											value="#{ref['label.ref.gt']}"
											rendered="#{pageIndex < pageCount}">
										</h:outputText>
									</f:facet>
									<h:outputText id="PRGCMGTOT1843" rendered="#{rowsCount > 0}"
										value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
										style="float:left;position:relative;bottom:-6px;font-weight:bold;" />
								</t:dataScroller>
								<br />
								<br />
							</m:div>
						</m:div>
						<%-- End of 2nd section --%>
					</m:div>
				</m:div>
			</m:div>
		</m:body>
		<%--<f:subview id="globalNotes">
				<jsp:include page="/jsp/commonEntities/commonNotes.jsp" />
			</f:subview>--%>
	</h:form>
	<%--/hx:scriptCollector--%>
</f:view>
<SCRIPT type="text/javascript">
 function onLoadFocus(){
		frmId = 'view<portlet:namespace/>:srEntFrm';
		//var pageId ='view<portlet:namespace/>:logCorrespondence:logCrspdAsscSubview:logCrspdAssociatedSubview:searchforAssCRSPDsubview:';
		if(document.getElementById(frmId+':cursorFocusId')!=null || document.getElementById(frmId+':cursorFocusId').value!=''){
		    var cursorFocus=frmId+':'+document.getElementById(frmId+':cursorFocusId').value;
			if(cursorFocus!=null && document.getElementById(cursorFocus)!= null){
				document.getElementById(cursorFocus).focus();
			}
		}
	}
</SCRIPT>
