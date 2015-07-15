<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
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
	basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_CMSearchEntity"
	var="msg" />
<SCRIPT type="text/javascript">
		
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
		
			function focusButton()
	{
	  var fbid1 ='view<portlet:namespace/>:srchCrspdEntity:PRGCMGTCB92'; 
	  if (fbid1 != null && document.getElementById(fbid1)!= null) 
	  {
		if (window.event.keyCode == 13)
		{	
			document.getElementById(fbid1).focus();
		}
	  }//else{
		 // alert("search correspondence entity button");
	  //}
	}
	
	</SCRIPT>
<f:view>

	<hx:scriptCollector>

	<h:form id="srchCrspdEntity">
		<t:saveState id="CMGTTOMSS535" value="#{CMEntitySearchDataBean}"></t:saveState>
		<t:saveState id="CMGTTOMSS536" value="#{CMEntitySearchDataBean.entitySearchCriteriaVO}"></t:saveState>
		<t:saveState id="CMGTTOMSS537" value="#{CMEntitySearchDataBean.entityDropDownList}"></t:saveState>
		<t:saveState id="CMGTTOMSS538" value="#{CMEntitySearchDataBean.entityIDTypeList}"></t:saveState>
		<t:saveState id="CMGTTOMSS539" value="#{CMEntitySearchDataBean.provTypeList}"></t:saveState>
		<t:saveState id="CMGTTOMSS540" value="#{CMEntitySearchDataBean.lobList}"></t:saveState>
		<t:saveState id="CMGTTOMSS541" value="#{CMEntitySearchDataBean.searchResultsList}"></t:saveState>
		<t:saveState id="CMGTTOMSS542" value="#{CMEntitySearchDataBean.renderProvider}"></t:saveState>
		<t:saveState id="CMGTTOMSS543" value="#{CMEntitySearchDataBean.renderNameSection}"></t:saveState>
		<t:saveState id="CMGTTOMSS544" value="#{CMEntitySearchDataBean.renderOrganizationName}"></t:saveState>
		<t:saveState id="CMGTTOMSS545" value="#{CMEntitySearchDataBean.renderCarrierNam}"></t:saveState>
		<t:saveState id="CMGTTOMSS546" value="#{CMEntitySearchDataBean.renderSearchResult}"></t:saveState>
		<t:saveState id="CMGTTOMSS547" value="#{CMEntitySearchDataBean.itemsPerPage}"></t:saveState>
		<t:saveState id="CMGTTOMSS548" value="#{CMEntitySearchDataBean.currentPage}"></t:saveState>
		<t:saveState id="CMGTTOMSS549" value="#{CMEntitySearchDataBean.numberOfPages}"></t:saveState>
		<t:saveState id="CMGTTOMSS550" value="#{CMEntitySearchDataBean.startRecord}"></t:saveState>
		<t:saveState id="CMGTTOMSS551" value="#{CMEntitySearchDataBean.showPrevious}"></t:saveState>
		<t:saveState id="CMGTTOMSS552" value="#{CMEntitySearchDataBean.showNext}"></t:saveState>



		<m:inputHidden name="send.Correspondence.Entity"
			value="sendCorrespondenceEntity"></m:inputHidden>
		<m:inputHidden value="#{CMEntitySearchDataBean.loadRefernceData}"></m:inputHidden>
		<m:inputHidden value="#{CMEntitySearchControllerBean.intialData}"></m:inputHidden>	
		<%-- onload cursor focus --%>
		<h:inputHidden id="cursorFocusId"		    value="#{CMEntitySearchDataBean.cursorFocusId}"></h:inputHidden>
		<%-- onload cursor focus --%>	

		<h:commandButton id="entityTypeChangedHiddenButton" styleClass="hide"			value="Hidden Button To Click"			action="#{CMEntitySearchControllerBean.renderRespEntityBlock}" />

		<m:body onkeypress="focusButton();" onload="javascript:onLoadFocus();">


			<m:div styleClass="moreInfoBar">
				<m:div styleClass="infoTitle" align="left">
					<m:span styleClass="required">
						<h:outputText id="PRGCMGTOT1817" value="#{ent['label-sw-reqFld']}"							styleClass="colorMaroon" />
					</m:span>

				</m:div>

				<m:div styleClass="infoActions">

					<t:commandLink id="gotoAE"						action="#{CMEntitySearchControllerBean.addEntityFromSearchCorrEntity}"						onmousedown="javascript:flagWarn=false;">
						<h:outputText id="PRGCMGTOT1818" styleClass="strong"							value="#{msg['title.label.entity.addEntity']}"></h:outputText>
							<f:param name="send.Correspondence.Entity"
												value="sendCorrespondenceEntity"></f:param>
					</t:commandLink>




				</m:div>
			</m:div>
			<h:messages id="PRGCMGTMS34" showDetail="true" layout="table" showSummary="false"				styleClass="errorMessage" />


			<%-- Start of 1st section --%>
			<m:div styleClass="floatContainer">
				<m:div styleClass="fullwidth">
					<m:div styleClass="floatContainer">
						<m:div styleClass="leftCol33">
							<m:section id="PROVIDERMMS20120731164811690">
								<m:pod title="#{ref['label.ref.searchCriteria']}"
									styleClass="otherbg">
									<%--<h:panelGroup id="group1" rendered="#{CMEntitySearchControllerBean.ajaxrenderRespEntityBlock}" styleClass="panelGroup">--%>
									<m:div>
										<m:table id="PROVIDERMMT20120731164811691" width="100%">
											<m:tr>
												<m:td colspan="2">
													<m:span styleClass="required">
														<h:outputText id="PRGCMGTOT1819" value="#{ref['label.ref.reqSymbol']}" />
													</m:span>
													<h:outputLabel id="PRGCMGTOLL765" for="entityTypSrch"														value="#{msg['label.entity.entityType']}">
													</h:outputLabel>
													<m:br />
													<hx:behavior event="onchange" target="entityTypSrch"
														behaviorAction="get" targetAction="group1"></hx:behavior>
						
													<h:selectOneMenu id="entityTypSrch" onchange="entityTypeChangeAction();"														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.entityType}"														>
														<f:selectItem itemLabel="Please Select One" itemValue="" />
														<f:selectItems
															value="#{CMEntitySearchDataBean.entityDropDownList}" />

													</h:selectOneMenu>
													<m:br />
													<h:message id="PRGCMGTM244" for="entityTypSrch" showDetail="true"														styleClass="errorMessage" />
												</m:td>
												<h:graphicImage id="PROVIDERGI20120731164811692" styleClass="blankImage" width="1" height="5" alt="" url="/images/x.gif"/>
												<%--ADDED FOR THE Correspondence ESPRD00436016 --%>
												<m:td
													rendered="#{CMEntitySearchDataBean.renderTradingPartner}">
													<m:span styleClass="required">
														<h:outputText id="PRGCMGTOT1820" value="#{ref['label.ref.reqSymbol']}" />
													</m:span>
													<h:outputLabel id="PRGCMGTOLL766" for="statusap"														value="#{msg['label.entity.status']}">
													</h:outputLabel>
													<m:br />
													<h:selectOneMenu id="statusap"														onchange="javascript:focusThis(this.id);"														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.tradingPartnerStatus}">
														<f:selectItem itemLabel="Please Select One" itemValue="" />
														<f:selectItems
															value="#{CMEntitySearchDataBean.tradingParnterStatusList}" />

													</h:selectOneMenu>
													<m:br />
													<h:message id="PRGCMGTM245" for="statusap" showDetail="ue"														styleClass="errorMessage" />
												</m:td>
												<%--END FOR THE Correspondence ESPRD00436016 --%>
												<m:td rendered="#{CMEntitySearchDataBean.renderProvider}">
													<h:outputLabel id="PRGCMGTOLL767" for="statusprov"														value="#{msg['label.entity.status']}">
													</h:outputLabel>

													<h:selectOneRadio id="statusprov" enabledClass="black"														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.providerStatus}">

														<f:selectItem itemLabel="#{msg['label.entity.active']}"
															itemValue="A" />
														<f:selectItem itemLabel="#{msg['label.entity.inactive']}"
															itemValue="I" />
													</h:selectOneRadio>

												</m:td>
											</m:tr>
										</m:table>
									</m:div>
									<%-- Common to all --%>
									<m:div id="showhide_searchby_provunenrolled">
										<m:div>
											<m:table id="PROVIDERMMT20120731164811693">
												<m:tr>
													<m:td>
														<h:outputLabel id="PRGCMGTOLL768" for="enid" value="#{msg['label.entity.EntityIDType']}" />
														<m:br></m:br>
														
														
														<h:selectOneMenu id="enid"															value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.entityIDType}">
															<f:selectItem itemLabel="Please Select One" itemValue="" />
															<f:selectItems
																value="#{CMEntitySearchDataBean.entityIDTypeList}" />
														</h:selectOneMenu>
														
													</m:td>
													<m:td id="entityTDID11">
														<h:outputLabel id="entityIDOutLablID1" for="EntIDSrch"															value="#{msg['label.entity.EntityID']}" />
														<m:br  id="entityIDBRID1" />
														<h:inputText id="EntIDSrch" size="10"															value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.entityID}"></h:inputText>
														<m:br  id="entityIDBRID2" />
														<h:message id="entityIDMSGlID1" for="EntIDSrch" showDetail="true"															styleClass="errorMessage" />
													</m:td>

												</m:tr>
											</m:table>
										</m:div>
									</m:div>

									<%-- For Entity Type - Provider -- render this div for provider --%>
									<m:div rendered="#{CMEntitySearchDataBean.renderProvider}">
										<m:table id="PROVIDERMMT20120731164811694" width="95">
											<m:tr>
												<m:td colspan="3">
													<h:outputLabel id="PRGCMGTOLL769" for="selectProviderType"														value="#{msg['label.entity.ProviderType']}" />
													<m:br></m:br>
													<h:selectOneMenu id="selectProviderType"														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.providerType}">
														<f:selectItem itemLabel="Please Select One" itemValue="" />
														<f:selectItems
															value="#{CMEntitySearchDataBean.provTypeList}" />
													</h:selectOneMenu>
												</m:td>
											</m:tr>
											<m:tr>

												<m:td>
													<h:outputLabel id="PRGCMGTOLL770" for="provSrtnam"														value="#{msg['label.entity.ProviderSortNam']}" />
													<m:br></m:br>
													<h:inputText id="provSrtnam" size="6"														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.providerSortName}"														maxlength="35"></h:inputText>
													<h:message id="PRGCMGTM246" for="provSrtnam" showDetail="true"														styleClass="errorMessage" />
												</m:td>
												<m:td>
													<h:outputLabel id="PRGCMGTOLL771" for="provDBA"														value="#{msg['label.entity.ProviderDBA']}" />
													<m:br></m:br>
													<h:inputText id="provDBA" size="5"														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.providerDBA}"														maxlength="35"></h:inputText>
												</m:td>
												<!-- 



													Author 	: Infinite
													CR		: 1925
													Date	: 07/01/2009 (dd/MM/yyyy)
												 -->
												<m:td rendered="#{! CMEntitySearchDataBean.renderProvider}">
													<h:outputLabel id="PRGCMGTOLL772" for="lobdropdowns" value="#{msg['label.entity.Lob']}" />
													<m:br></m:br>
													<m:br></m:br>
													<h:selectOneMenu id="lobdropdowns"														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.lineOfBusiness}">

														<f:selectItems value="#{CMEntitySearchDataBean.lobList}" />
													</h:selectOneMenu>
												</m:td>

											</m:tr>
										</m:table>
									</m:div>
									<%--ADDED FOR THE CR ESPRD00436016 --%>
											<m:div id="aPatdiv1" rendered="#{CMEntitySearchDataBean.renderTradingPartner}">
												<m:table id="aPatTable" width="95">
										
														<m:tr>
														<m:td id="TPtd2">
																<h:outputLabel id="PRGCMGTOLL773" for="Pclassi" 																	value="#{msg['label.entity.tradingpartner.classification']}" />
																<m:br></m:br>
																<h:selectOneMenu id="Pclassi"																	value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.tradingPartnerClassification}"																	onmousedown="javascript:flagWarn=false;">
																	<f:selectItem itemLabel="Please Select One"
																		itemValue="" />
																	<f:selectItems
																		value="#{CMEntitySearchDataBean.tradingPartnerclassificationList}" />
																</h:selectOneMenu>
														<m:br></m:br>
													<h:message id="PRGCMGTM247" for="Pclassi" showDetail="ue"																styleClass="errorMessage" />
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
									<%-- Render this div for  Member ,Provider ,UP ,UM --%>
									<m:div id="showhide_searchby_other"
										rendered="#{CMEntitySearchDataBean.renderNameSection}">
										<m:table id="PROVIDERMMT20120731164811695">
											<m:tr>
												<m:td>
													<h:outputLabel id="PRGCMGTOLL774" for="fnam"														value="#{msg['label.entity.FirstName']}" />

													<m:br></m:br>
													<h:inputText id="fnam" size="10"														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.firstName}"														maxlength="25"></h:inputText>
													<h:message id="PRGCMGTM248" for="fnam" showDetail="true"														styleClass="errorMessage" />
												</m:td>
												<m:td>
													<h:outputLabel id="PRGCMGTOLL775" for="mi_na"														value="#{msg['label.entity.MI']}" />
													<m:br></m:br>
													<h:inputText id="mi_na" size="2" maxlength="25"														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.middleInitial}"></h:inputText>
													<h:message id="PRGCMGTM249" for="mi_na" showDetail="true"														styleClass="errorMessage" />
												</m:td>
												<m:td>
													<%-- for fixing defect:ESPRD00576206 --%>  
													<m:span styleClass="required" rendered="#{CMEntitySearchDataBean.renderRequired}">
														<h:outputText id="CMGTOT92" value="#{ref['label.ref.reqSymbol']}" />
													</m:span> 
													<h:outputLabel id="PRGCMGTOLL776" for="LNameSearch"														value="#{msg['label.entity.LastName']}" />

													<m:br></m:br>
													<h:inputText id="LNameSearch" size="10"														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.lastName}"														maxlength="35"></h:inputText>
													<m:br></m:br>
													<h:message id="PRGCMGTM250" for="LNameSearch" showDetail="true"														styleClass="errorMessage" />
												</m:td>
											</m:tr>
										</m:table>
									</m:div>

									<m:div id="showhide_orgnam"
										rendered="#{CMEntitySearchDataBean.renderOrganizationName || CMEntitySearchDataBean.countyName}">
										<m:table id="PROVIDERMMT20120731164811696">
											<m:tr>
												<h:outputLabel id="PRGCMGTOLL777" for="orgNCD"													value="#{msg['label.entity.OrgName']}" 													rendered="#{CMEntitySearchDataBean.renderOrganizationName}"/>
												<h:outputLabel id="PRGCMGTOLL778" for="orgNCD"															value="#{msg['label.entity.countyName']}"															rendered="#{CMEntitySearchDataBean.countyName}" />
												<h:inputText id="orgNCD" size="12" maxlength="50"													value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.organisationName}"></h:inputText>
												<m:br />
												<h:message id="PRGCMGTM251" for="orgNCD" showDetail="true"													styleClass="errorMessage" />
											</m:tr>
										</m:table>
									</m:div>

							
									<m:div id="showhide_carrnam"
										rendered="#{CMEntitySearchDataBean.renderCarrierNam}">
										<m:table id="PROVIDERMMT20120731164811697">
											<m:tr>
												<h:outputLabel id="PRGCMGTOLL779" for="carriernam"													value="#{msg['label.entity.CarrierName']}" />
												<h:inputText id="carriernam" size="15" maxlength="40"													value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.carrierName}"></h:inputText>
												<m:br />
												<h:message id="PRGCMGTM252" for="carriernam" showDetail="true"													styleClass="errorMessage" />
											</m:tr>
										</m:table>
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
													<h:outputLabel id="policyHolderFirstNameOutLablID" for="policyHolderFirstName"														value="#{msg['label.entity.FirstName']}" />

													<m:br id="policyHolderFirstNameBrId1" />
													<h:inputText id="policyHolderFirstName" size="10" 														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.policyHolderFirstName}" maxlength="25"></h:inputText>
													<m:br id="policyHolderFirstNameBrId2" />
													<h:message id="policyHolderFirstNameMsgID" for="policyHolderFirstName" showDetail="true"														styleClass="errorMessage" />
												</m:td>

												<m:td id="policyHolderLastNameTdId">
													<%--h:outputLabel id="policyHolderLastNameOutLablId" for="TPLNameSearch"
														value="#{msg['label.entity.policyholder.LastName']}" /--%>
													<%-- for fixing defect:ESPRD00576206 --%>  
													<m:span styleClass="required" rendered="#{CMEntitySearchDataBean.renderRequired}">
														<h:outputText id="CMGTOT93" value="#{ref['label.ref.reqSymbol']}" />
													</m:span>
													<h:outputLabel id="policyHolderLastNameOutLablId" for="TPLNameSearch"														value="#{msg['label.entity.LastName']}" />
													<m:br id="policyHolderLastNameBrId1" />
													<h:inputText id="TPLNameSearch" size="10" 														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.policyHolderLastName}" maxlength="35"></h:inputText>
													<m:br id="policyHolderLastNameBrId2" />
													<h:message id="policyHolderLastNameMsgId" for="TPLNameSearch" showDetail="true"														styleClass="errorMessage" />
												</m:td>
											</m:tr>
										</m:table>
									</m:div>
<%-- EOF CR_ESPRD00486064_UC-PGM-CRM-013_26JUN2010 --%>
									<m:div styleClass="buttonRow">
										<h:commandButton id="PRGCMGTCB92" styleClass="formBtn"											value="#{ref['label.ref.search']}"											action="#{CMEntitySearchControllerBean.getEntitiesForCrspd}" />
										<h:outputText id="PRGCMGTOT1821" escape="false"											value="#{ref['label.ref.singleSpace']}" />
										<h:outputText id="PRGCMGTOT1822" escape="false"											value="#{ref['label.ref.singleSpace']}" />
										<h:commandButton id="PRGCMGTCB93" styleClass="formBtn"											value="#{ent['label-sw-reset']}"											action="#{CMEntitySearchControllerBean.resetSearch}" />

									</m:div>
									<%--</h:panelGroup>
								<hx:ajaxRefreshRequest id="ajaxRefreshRequest1" target="group1"
									params="entityTypSrch"></hx:ajaxRefreshRequest>--%>

								</m:pod>
							</m:section>

						</m:div>



						<m:div styleClass="rightCol66"
							rendered="#{CMEntitySearchDataBean.renderSearchResult}">

							<m:div id="searchResults">
								<m:br />

								<t:dataTable cellspacing="0" styleClass="dataTable" id="entSrch"									width="100%" columnClasses="columnClass"									headerClass="headerClass" footerClass="footerClass"									rowClasses="row_alt,row" rows="10"									onmouseover="return tableMouseOver(this, event);"									onmouseout="return tableMouseOut(this, event);"									value="#{CMEntitySearchDataBean.searchResultsList}"		first="#{CMEntitySearchDataBean.startIndexForSrchEntity}"	var="srchResult" rowIndexVar="rowIndex" >



									<h:column id="entidcol">
										<f:facet name="header">

											<h:panelGrid id="PRGCMGTPGD253" columns="2">
												<h:outputLabel id="PRGCMGTOLL780"  for="dataColEntId" value="#{msg['label.entity.EntityID']}">
												</h:outputLabel>
												<h:panelGroup id="dataColEntId">
													<t:div styleClass="alignLeft">
														<t:commandLink id="entCommandLink1"															actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"															rendered="#{CMEntitySearchDataBean.imageRender !='entCommandLink1'}">
															<m:div title="for ascending" styleClass="sort_asc" />
															<f:attribute name="columnName" value="entIdVal" />
															<f:attribute name="sortOrder" value="asc" />
														</t:commandLink>
													</t:div>
													<t:div styleClass="alignLeft">
														<t:commandLink id="entCommandLink2"															actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"															rendered="#{CMEntitySearchDataBean.imageRender !='entCommandLink2'}">
															<m:div title="for descending" styleClass="sort_desc" />
															<f:attribute name="columnName" value="entIdVal" />
															<f:attribute name="sortOrder" value="desc" />
														</t:commandLink>
													</t:div>
												</h:panelGroup>
											</h:panelGrid>
										</f:facet>
										<t:commandLink id="edit"											action="#{CMEntitySearchControllerBean.getCMEntityDetailsForCrspd}">
											<h:outputText id="a" value="#{srchResult.entityID}" />
											<f:param name="systemIDVal" value="#{srchResult.systemID}"></f:param>
											<f:param name="entiyIDVal" value="#{srchResult.entityID}"></f:param>
											<f:param name="send.Correspondence.Entity"
												value="sendCorrespondenceEntity"></f:param>
											<f:param name="indexCode" value="#{rowIndex}"></f:param>

										</t:commandLink>
									</h:column>

									<h:column id="nameCol">
										<f:facet name="header">

											<h:panelGrid id="PRGCMGTPGD254" columns="2">
												<h:outputLabel id="PRGCMGTOLL781" for="dataColentname" value="#{msg['label.entity.Name']}">
												</h:outputLabel>
												<h:panelGroup id="dataColentname">
													<t:div styleClass="alignLeft">
														<t:commandLink id="namCommandLink1"															actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"															rendered="#{CMEntitySearchDataBean.imageRender !='namCommandLink1'}">
															<m:div title="for ascending" styleClass="sort_asc" />
															<f:attribute name="columnName" value="namValue" />
															<f:attribute name="sortOrder" value="asc" />
														</t:commandLink>
													</t:div>
													<t:div styleClass="alignLeft">
														<t:commandLink id="namCommandLink2"															actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"															rendered="#{CMEntitySearchDataBean.imageRender !='namCommandLink2'}">
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
									<h:column id="orgNameCol"											rendered="#{CMEntitySearchDataBean.renderLOB}">
											<f:facet name="header">




												<h:panelGrid id="PRGCMGTPGD255" columns="2">
													<h:outputLabel id="PRGCMGTOLL782" value="#{msg['label.entity.orgName']}"														rendered="#{!CMEntitySearchDataBean.countyName}">
													</h:outputLabel>
													<h:outputLabel id="PRGCMGTOLL783" value="#{msg['label.entity.countyName']}"														rendered="#{CMEntitySearchDataBean.countyName}">
													</h:outputLabel>
													<h:panelGroup id="PRGCMGTPGP144">
														<t:div styleClass="alignLeft">
															<t:commandLink id="orgNameCommandLink1"																actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"																rendered="#{CMEntitySearchDataBean.imageRender !='namCommandLink1'}">
																<m:div title="for ascending" styleClass="sort_asc" />
																<f:attribute name="columnName" value="orgNamValue" />
																<f:attribute name="sortOrder" value="asc" />
															</t:commandLink>
														</t:div>
														<t:div styleClass="alignLeft">
															<t:commandLink id="orgNamCommandLink2"																actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"																rendered="#{CMEntitySearchDataBean.imageRender !='namCommandLink2'}">
																<m:div title="for descending" styleClass="sort_desc" />
																<f:attribute name="columnName" value="orgNamValue" />
																<f:attribute name="sortOrder" value="desc" />
															</t:commandLink>
														</t:div>
													</h:panelGroup>
												</h:panelGrid>
											</f:facet>

											<h:outputText id="orgNamOutput"												value="#{srchResult.organisationName}" />


										</h:column>
									


									<h:column id="lobCol" rendered="#{!CMEntitySearchDataBean.renderLOB}">
										<f:facet name="header">

											<h:panelGrid id="PRGCMGTPGD256" columns="2">
												<h:outputLabel id="PRGCMGTOLL784" for="dataColLob" value="#{msg['label.entity.Lob']}">
												</h:outputLabel>
												<h:panelGroup id="dataColLob">
													<t:div styleClass="alignLeft">
														<t:commandLink id="lobCommandLink1"															actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"															rendered="#{CMEntitySearchDataBean.imageRender !='lobCommandLink1'}">
															<m:div title="for ascending" styleClass="sort_asc" />
															<f:attribute name="columnName" value="lobValue" />
															<f:attribute name="sortOrder" value="asc" />
														</t:commandLink>
													</t:div>
													<t:div styleClass="alignLeft">
														<t:commandLink id="lobCommandLink2"															actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"															rendered="#{CMEntitySearchDataBean.imageRender !='lobCommandLink2'}">
															<m:div title="for descending" styleClass="sort_desc" />
															<f:attribute name="columnName" value="lobValue" />
															<f:attribute name="sortOrder" value="desc" />
														</t:commandLink>
													</t:div>
												</h:panelGroup>
											</h:panelGrid>
										</f:facet>

										<h:outputText id="lobOutput"											value="#{srchResult.lineOfBusiness}" />

									</h:column>


									<h:column id="addCol">
										<f:facet name="header">

											<h:panelGrid id="PRGCMGTPGD257" columns="2">
												<h:outputLabel id="PRGCMGTOLL785" for="dataColAdd" value="#{msg['label.entity.Address']}">
												</h:outputLabel>
												<h:panelGroup id="dataColAdd">
													<t:div styleClass="alignLeft">
														<t:commandLink id="addCommandLink1"															actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"															rendered="#{CMEntitySearchDataBean.imageRender !='addCommandLink1'}">
															<m:div title="for ascending" styleClass="sort_asc" />
															<f:attribute name="columnName" value="addValue" />
															<f:attribute name="sortOrder" value="asc" />
														</t:commandLink>
													</t:div>
													<t:div styleClass="alignLeft">
														<t:commandLink id="addCommandLink2"															actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"															rendered="#{CMEntitySearchDataBean.imageRender !='addCommandLink2'}">
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

											<h:panelGrid id="PRGCMGTPGD258" columns="2">
												<h:outputLabel id="PRGCMGTOLL786" for="dataColCity" value="#{msg['label.entity.City']}">
												</h:outputLabel>
												<h:panelGroup id="dataColCity" >
													<t:div styleClass="alignLeft">
														<t:commandLink id="cityCommandLink1"															actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"															rendered="#{CMEntitySearchDataBean.imageRender !='cityCommandLink1'}">
															<m:div title="for ascending" styleClass="sort_asc" />
															<f:attribute name="columnName" value="cityValue" />
															<f:attribute name="sortOrder" value="asc" />
														</t:commandLink>
													</t:div>
													<t:div styleClass="alignLeft">
														<t:commandLink id="cityCommandLink2"															actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"															rendered="#{CMEntitySearchDataBean.imageRender !='cityCommandLink2'}">
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
								</t:dataTable><%--
								<t:dataScroller id="CMGTTOMDS68" for="entSrch" paginator="true"									paginatorActiveColumnStyle='font-weight:bold;'									paginatorMaxPages="3" immediate="false"									pageCountVar="pageCount" pageIndexVar="pageIndex"									firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"									rowsCountVar="rowsCount" styleClass="scroller">
									<f:facet name="previous">
										<h:commandLink id="PRGCMGTCL246" styleClass="underline"											action="#{CMEntitySearchControllerBean.previous}"											rendered="#{CMEntitySearchDataBean.showPrevious}">
											<h:outputText id="PRGCMGTOT1823" value="#{ref['label.ref.lt']}">
											</h:outputText>
										</h:commandLink>
									</f:facet>

									<f:facet name="next">
										<h:commandLink id="PRGCMGTCL247" styleClass="underline"											action="#{CMEntitySearchControllerBean.next}"											rendered="#{CMEntitySearchDataBean.showNext}">
											<h:outputText id="PRGCMGTOT1824" value="#{ref['label.ref.gt']}">
											</h:outputText>
										</h:commandLink>
									</f:facet>


									<h:outputText id="PRGCMGTOT1825" rendered="#{rowsCount > 0}"
										value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
										styleClass="scrollerBold" />
								</t:dataScroller>
								--%>
								<t:dataScroller id="CMGTTOMDS69" for="entSrch" paginator="true"									paginatorActiveColumnStyle='font-weight:bold;' paginatorMaxPages="3"									immediate="false" pageCountVar="pageCount" pageIndexVar="pageIndex"									firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"									rowsCountVar="rowsCount"									styleClass="scroller">
									<f:facet name="previous">
										<h:outputText id="PRGCMGTOT1826" 											value="#{ref['label.ref.lt']}" rendered="#{pageIndex > 1}">
										</h:outputText>
									</f:facet>
									<f:facet name="next">
										<h:outputText id="PRGCMGTOT1827" 											value="#{ref['label.ref.gt']}"											rendered="#{pageIndex < pageCount}">
										</h:outputText>
									</f:facet>
									<h:outputText id="PRGCMGTOT1828" rendered="#{rowsCount > 0}"
										value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
										style="float:left;position:relative;bottom:-6px;font-weight:bold;" />
								</t:dataScroller>
								<m:br/>
								<m:br/>

							</m:div>
						</m:div>
						<%-- End of 2nd section --%>
					</m:div>
				</m:div>
			</m:div>
		</m:body>
	</h:form>
</hx:scriptCollector>











</f:view>
<SCRIPT type="text/javascript">
 function onLoadFocus(){
		frmId = 'view<portlet:namespace/>:srchCrspdEntity';
		//var pageId ='view<portlet:namespace/>:logCorrespondence:logCrspdAsscSubview:logCrspdAssociatedSubview:searchforAssCRSPDsubview:';
		if(document.getElementById(frmId+':cursorFocusId')!=null || document.getElementById(frmId+':cursorFocusId').value!=''){
		    var cursorFocus=frmId+':'+document.getElementById(frmId+':cursorFocusId').value;
			if(cursorFocus!=null && document.getElementById(cursorFocus)!= null){
				document.getElementById(cursorFocus).focus();
			}
		}
	}
</SCRIPT>
