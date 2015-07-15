<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
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
function focusComponent()
{
    
	var fid1 ='view<portlet:namespace/>:srEntFrm:globalNotes:cmnNoteHist:notescmd3'; 
    var noteFocus='view<portlet:namespace/>:srEntFrm:notesFocusflag'; 
	
	 if (fid1 != null && document.getElementById(fid1)!=null&&document.getElementById(noteFocus)!=null ) {
		document.getElementById(fid1).focus();
	}
}
</SCRIPT>


<f:view>


	
	<body onload="focusComponent(); onLoadFocus();">
	<h:form id="srEntFrm">
		<t:saveState id="CMGTTOMSS571" value="#{CMEntitySearchDataBean}"></t:saveState>
		<t:saveState id="CMGTTOMSS572"
			value="#{CMEntitySearchDataBean.entitySearchCriteriaVO}"></t:saveState>
		<t:saveState id="CMGTTOMSS573"
			value="#{CMEntitySearchDataBean.entityDropDownList}"></t:saveState>
		<t:saveState id="CMGTTOMSS574"
			value="#{CMEntitySearchDataBean.entityIDTypeList}"></t:saveState>
		<t:saveState id="CMGTTOMSS575"
			value="#{CMEntitySearchDataBean.provTypeList}"></t:saveState>
		<t:saveState id="CMGTTOMSS576"
			value="#{CMEntitySearchDataBean.lobList}"></t:saveState>
		<t:saveState id="CMGTTOMSS577"
			value="#{CMEntitySearchDataBean.searchResultsList}"></t:saveState>
		<t:saveState id="CMGTTOMSS578"
			value="#{CMEntitySearchDataBean.renderProvider}"></t:saveState>
		<t:saveState id="CMGTTOMSS579"
			value="#{CMEntitySearchDataBean.renderNameSection}"></t:saveState>
		<t:saveState id="CMGTTOMSS580"
			value="#{CMEntitySearchDataBean.renderOrganizationName}"></t:saveState>
		<t:saveState id="CMGTTOMSS581"
			value="#{CMEntitySearchDataBean.renderCarrierNam}"></t:saveState>
		<t:saveState id="CMGTTOMSS582"
			value="#{CMEntitySearchDataBean.renderSearchResult}"></t:saveState>
		<t:saveState id="CMGTTOMSS583"
			value="#{CMEntitySearchDataBean.itemsPerPage}"></t:saveState>
		<t:saveState id="CMGTTOMSS584"
			value="#{CMEntitySearchDataBean.currentPage}"></t:saveState>
		<t:saveState id="CMGTTOMSS585"
			value="#{CMEntitySearchDataBean.numberOfPages}"></t:saveState>
		<t:saveState id="CMGTTOMSS586"
			value="#{CMEntitySearchDataBean.startRecord}"></t:saveState>
		<t:saveState id="CMGTTOMSS587"
			value="#{CMEntitySearchDataBean.showPrevious}"></t:saveState>
		<t:saveState id="CMGTTOMSS588"
			value="#{CMEntitySearchDataBean.showNext}"></t:saveState>

	<h:inputHidden id="controlRequiredForSearchNotes"
					value="#{CommonNotesControllerBean.controlRequired}"></h:inputHidden>


		<m:inputHidden name="send.Entity.Id" value="sendEntityId"></m:inputHidden>
		<m:inputHidden value="#{CMEntitySearchDataBean.loadRefernceData}"></m:inputHidden>
		<%-- onload cursor focus --%>
		<h:inputHidden id="cursorFocusId"
			value="#{CMEntitySearchDataBean.cursorFocusId}"></h:inputHidden>
		<%-- onload cursor focus --%>

		<h:commandButton id="entityTypeChangedHiddenButton" styleClass="hide"
			value="Hidden Button To Click"
			action="#{CMEntitySearchControllerBean.renderRespEntityBlock}" />




		<m:body>


			<m:div styleClass="moreInfoBar">
				<m:div styleClass="infoTitle" align="left">
					<m:span styleClass="required">
						<h:outputText id="PRGCMGTOT1844" value="#{ent['label-sw-reqFld']}"
							styleClass="colorMaroon" />
					</m:span>

				</m:div>

				<m:div styleClass="infoActions">

					<%--Calls javascript method which  in turn will call an mehod to clear the data in MaintainEntityPage --%>
					<%--<h:commandLink id="ipctoME"						action="#{CMEntitySearchControllerBean.addEntityFromSearchEntity}" rendered="#{CMEntitySearchControllerBean.controlRequired}">
						<h:outputText id="PRGCMGTOT1845" styleClass="strong"							value="#{msg['title.label.entity.addEntity']}"></h:outputText>
							<f:param name="send.Entity.Id"
												value="sendEntityId"></f:param>
				 	</h:commandLink>--%>


				</m:div>
			</m:div>
			<h:messages id="PRGCMGTMS36" showDetail="true" layout="table"
				showSummary="false" styleClass="errorMessage" />


			<%-- Start of 1st section --%>
			<m:div styleClass="floatContainer">
				<m:div styleClass="fullwidth">






					<m:div styleClass="floatContainer">
						<m:div styleClass="leftCol33">
							<m:section id="PROVIDERMMS20120731164811706">
								<m:pod title="#{ref['label.ref.searchCriteria']}"
									styleClass="otherbg">
									<m:div>
										<m:table id="PROVIDERMMT20120731164811707" width="100%">
											<m:tr>
												<m:td colspan="2">
													<m:span styleClass="required">
														<h:outputText id="PRGCMGTOT1846"
															value="#{ref['label.ref.reqSymbol']}" />
													</m:span>
													<h:outputLabel id="PRGCMGTOLL811" for="entityTypSrch" 
														value="#{msg['label.entity.entityType']}">
													</h:outputLabel>

													<m:br />
													<t:selectOneMenu id="entityTypSrch"
														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.entityType}"
														onchange="javascript:entityTypeChangeAction();" onclick="flagWarn=false;">
														<f:selectItem itemLabel="Please Select One" itemValue="" />
														<f:selectItems
															value="#{CMEntitySearchDataBean.entityDropDownList}" />

													</t:selectOneMenu>
													<m:br />
													<h:message id="PRGCMGTM263" for="entityTypSrch"
														showDetail="true" styleClass="errorMessage" />
												</m:td>
												<h:graphicImage id="blnkImagID" styleClass="blankImage" width="1" height="5"
													alt="" url="/images/x.gif"/>
												<%--ADDED FOR THE Correspondence ESPRD00436016 --%>
												<m:td
													rendered="#{CMEntitySearchDataBean.renderTradingPartner}">
													<m:span styleClass="required">
														<h:outputText id="PRGCMGTOT1847"
															value="#{ref['label.ref.reqSymbol']}" />
													</m:span>
													<h:outputLabel id="PRGCMGTOLL812" for="statusap"
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
													<h:message id="PRGCMGTM264" for="statusap" showDetail="ue"
														styleClass="errorMessage" />
												</m:td>
												<%--END FOR THE Correspondence ESPRD00436016 --%>
												<m:td rendered="#{CMEntitySearchDataBean.renderProvider}">
													<h:outputLabel id="PRGCMGTOLL813" for="statusprov"
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
											<m:table id="PROVIDERMMT20120731164811708">
												<m:tr>
													<m:td>
														<h:outputLabel id="PRGCMGTOLL814" for="enid"
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
														<h:outputLabel id="PRGCMGTOLL815" for="EntIDSrch"
															value="#{msg['label.entity.EntityID']}" />

														<t:inputText id="EntIDSrch" size="10"
															value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.entityID}">
														</t:inputText>
														<m:br />
														<h:message id="PRGCMGTM265" for="EntIDSrch"
															showDetail="true" styleClass="errorMessage" />
													</m:td>
												</m:tr>
											</m:table>

										</m:div>
									</m:div>

									<%-- For Entity Type - Provider -- render this div for provider --%>
									<m:div rendered="#{CMEntitySearchDataBean.renderProvider}">
										<m:table id="PROVIDERMMT20120731164811709" width="95">
											<m:tr>
												<m:td colspan="3">
													<h:outputLabel id="PRGCMGTOLL816" for="selectProviderType"
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
													<h:outputLabel id="PRGCMGTOLL817" for="provSrtnam"
														value="#{msg['label.entity.ProviderSortNam']}" />
													<m:br></m:br>
													<t:inputText id="provSrtnam" size="6"
														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.providerSortName}"
														maxlength="35">
													</t:inputText>

													<h:message id="PRGCMGTM266" for="provSrtnam"
														showDetail="true" styleClass="errorMessage" />
												</m:td>
												<m:td>
													<h:outputLabel id="PRGCMGTOLL818" for="provDBA"
														value="#{msg['label.entity.ProviderDBA']}" />
													<m:br></m:br>
													<t:inputText id="provDBA" size="5"
														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.providerDBA}"
														maxlength="35">
													</t:inputText>
												</m:td>
												<%--  
													Author 	: Infinite
													CR		: 1925
													Date	: 30/12/2008
												 --%>
												<m:td rendered="#{! CMEntitySearchDataBean.renderProvider}">
													<h:outputLabel id="PRGCMGTOLL819" for="lobdropdowns"
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
									<%--ADDED FOR THE CR ESPRD00436016 --%>
									<m:div id="aPatdiv1"
										rendered="#{CMEntitySearchDataBean.renderTradingPartner}">
										<m:table id="aPatTable" width="95">

											<m:tr>
												<m:td id="TPtd2">
													<h:outputLabel id="PRGCMGTOLL820" for="Pclassi"
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
													<h:message id="PRGCMGTM267" for="Pclassi" showDetail="ue"
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
									<%--END FOR THE CR ESPRD00436016 --%>
									<%-- Render this div for  Member ,Provider ,UP ,UM --%>
									<m:div id="showhide_searchby_other"
										rendered="#{CMEntitySearchDataBean.renderNameSection}">
										<m:table id="PROVIDERMMT20120731164811710">
											<m:tr>
												<m:td>
													<h:outputLabel id="PRGCMGTOLL821" for="fnam"
														value="#{msg['label.entity.FirstName']}" />

													<m:br></m:br>
													<t:inputText id="fnam" size="10"
														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.firstName}"
														maxlength="25">
													</t:inputText>

													<h:message id="PRGCMGTM268" for="fnam" showDetail="true"
														styleClass="errorMessage" />
												</m:td>
												<m:td>
													<h:outputLabel id="PRGCMGTOLL822" for="mi_na"
														value="#{msg['label.entity.MI']}" />

													<m:br></m:br>
													<t:inputText id="mi_na" size="2" maxlength="25"
														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.middleInitial}">
													</t:inputText>

													<h:message id="PRGCMGTM269" for="mi_na" showDetail="true"
														styleClass="errorMessage" />
												</m:td>
												<m:td>
													<m:span styleClass="required">
														<h:outputText id="PRGCMGTOT1848"
															value="#{ref['label.ref.reqSymbol']}" />
													</m:span>
													<h:outputLabel id="PRGCMGTOLL823" for="LNameSearch"
														value="#{msg['label.entity.LastName']}" />

													<m:br></m:br>
													<t:inputText id="LNameSearch" size="10"
														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.lastName}"
														maxlength="35">
													</t:inputText>
													<m:br></m:br>
													<h:message id="PRGCMGTM270" for="LNameSearch"
														showDetail="true" styleClass="errorMessage" />
												</m:td>
											</m:tr>
										</m:table>
									</m:div>

									<m:div id="showhide_orgnam"
										rendered="#{CMEntitySearchDataBean.renderOrganizationName || CMEntitySearchDataBean.countyName}">
										<m:table id="PROVIDERMMT20120731164811711">
											<m:tr>
												<h:outputLabel id="PRGCMGTOLL824" for="orgNCD"
													value="#{msg['label.entity.OrgName']}"
													rendered="#{CMEntitySearchDataBean.renderOrganizationName}" />
												<h:outputLabel id="PRGCMGTOLL825" for="orgNCD"
													value="#{msg['label.entity.countyName']}"
													rendered="#{CMEntitySearchDataBean.countyName}" />
												<t:inputText id="orgNCD" size="12" maxlength="50"
													value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.organisationName}">
												</t:inputText>
												<m:br />
												<h:message id="PRGCMGTM271" for="orgNCD" showDetail="true"
													styleClass="errorMessage" />
											</m:tr>
										</m:table>
									</m:div>


									<m:div id="showhide_carrnam"
										rendered="#{CMEntitySearchDataBean.renderCarrierNam}">
										<m:table id="PROVIDERMMT20120731164811712">
											<m:tr>
												<h:outputLabel id="PRGCMGTOLL826" for="carriernam"
													value="#{msg['label.entity.CarrierName']}" />
												<t:inputText id="carriernam" size="15" maxlength="40"
													value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.carrierName}">
												</t:inputText>
												<m:br />
												<h:message id="PRGCMGTM272" for="carriernam"
													showDetail="true" styleClass="errorMessage" />
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
									<%-- EOF CR_ESPRD00486064_UC-PGM-CRM-013_26JUN2010 --%>
									<m:div styleClass="buttonRow">
										<m:inputHidden name="com.ibm.portal.propertybroker.action"
											value="sendPSysID"></m:inputHidden>
										<h:commandButton id="PRGCMGTCB95" styleClass="formBtn"
											value="#{ref['label.ref.search']}"
											action="#{CMEntitySearchControllerBean.getEntities}" 
											onclick="flagWarn=false;"/>
										<h:outputText id="PRGCMGTOT1849" escape="false"
											value="#{ref['label.ref.singleSpace']}" />
										<h:outputText id="PRGCMGTOT1850" escape="false"
											value="#{ref['label.ref.singleSpace']}" />
										<h:commandButton id="PRGCMGTCB96" styleClass="formBtn"
											value="#{ent['label-sw-reset']}"
											action="#{CMEntitySearchControllerBean.resetSearch}"
											onclick="flagWarn=false;" />

									</m:div>

								</m:pod>
							</m:section>

						</m:div>



						<m:div styleClass="rightCol66"
							rendered="#{CMEntitySearchDataBean.renderSearchResult}">

							<m:div id="searchResults">
								<m:br />
                                <%-- Added For DEFECT ESPRD00743989 --%>
								<t:dataTable cellspacing="0" styleClass="dataTable" id="entSrch" 
								onmousedown="flagWarn=false;"
									rowStyleClass="#{CMEntitySearchDataBean.itemSelectedRowIndex == rowIndex ? 'row_selected' : 'row'}"
									width="100%" columnClasses="columnClass"
									headerClass="headerClass" footerClass="footerClass"
									rowClasses="row_alt,row" rows="10"
									value="#{CMEntitySearchDataBean.searchResultsList}"
									first="#{CMEntitySearchDataBean.startIndexForNOtes}"
									var="srchResult" rowIndexVar="rowIndex"
									rowOnClick="firstChild.lastChild.click();"
								    rowOnMouseOver="this.origClassName = this.className;this.className='row_mouse row_link';"
								    rowOnMouseOut="if (this.origClassName) this.className = this.origClassName;">

									<%-- 	
								<h:column id="noteen" rendered="#{CMEntitySearchDataBean.renderNotes}"> 
								--%>
									<t:column id="noteen">
										<f:facet name="header">
											<h:panelGrid id="PRGCMGTPGD266" columns="2">
												<h:outputLabel id="PRGCMGTOLL827"
													value="#{msg['label.entity.EntityID']}">
												</h:outputLabel>
												<h:panelGroup id="PRGCMGTPGP147">
													<t:div styleClass="alignLeft">
														<t:commandLink id="notesearch"
															actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"
															rendered="#{CMEntitySearchDataBean.imageRender !='entCommandLink1'}">
															<m:div title="#{ref['label.ref.forAscending']}" styleClass="sort_asc" />
															<f:attribute name="columnName" value="entIdVal" />
															<f:attribute name="sortOrder" value="asc" />
														</t:commandLink>
													</t:div>
													<t:div styleClass="alignLeft">
														<t:commandLink id="notesearch2"
															actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"
															rendered="#{CMEntitySearchDataBean.imageRender !='entCommandLink2'}">
															<m:div title="#{ref['label.ref.forDescending']}" styleClass="sort_desc" />
															<f:attribute name="columnName" value="entIdVal" />
															<f:attribute name="sortOrder" value="desc" />
														</t:commandLink>
													</t:div>
												</h:panelGroup>
											</h:panelGrid>
										</f:facet>
										<%-- Modified For DEFECT ESPRD00743989 --%>
										<t:commandLink id="notedt" onclick="flagWarn=false;"
											action="#{CommonNotesControllerBean.getGlobalNotes}">
											<h:outputText id="ant" value="#{srchResult.entityID}" />
											<f:param name="systemIDVal" value="#{srchResult.systemID}"></f:param>
											<f:param name="entiyIDVal" value="#{srchResult.entityID}"></f:param>
											<f:param name="commonEntSKVal"
												value="#{srchResult.commonEntitySK}"></f:param>
											<f:param name="entityName" value="#{srchResult.entityName}"></f:param>
											<f:param name="entityType"
												value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.entityType}"></f:param>
											<f:param name="send.Entity.Id" value="sendEntityId"></f:param>
											<f:param name="indexCode" value="#{rowIndex}"></f:param>
										</t:commandLink>
									</t:column>



									<t:column id="nameCol"
										rendered="#{!CMEntitySearchDataBean.countyName}">
										<f:facet name="header">

											<h:panelGrid id="PRGCMGTPGD267" columns="2">
												<h:outputLabel id="PRGCMGTOLL828" for="dataColentname"
													value="#{msg['label.entity.Name']}">
												</h:outputLabel>
												<h:panelGroup id="dataColentname">
													<t:div styleClass="alignLeft">
														<t:commandLink id="namCommandLink1"
															actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"
															rendered="#{CMEntitySearchDataBean.imageRender !='namCommandLink1'}">
															<m:div title="#{ref['label.ref.forAscending']}" styleClass="sort_asc" />
															<f:attribute name="columnName" value="namValue" />
															<f:attribute name="sortOrder" value="asc" />
														</t:commandLink>
													</t:div>
													<t:div styleClass="alignLeft">
														<t:commandLink id="namCommandLink2"
															actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"
															rendered="#{CMEntitySearchDataBean.imageRender !='namCommandLink2'}">
															<m:div title="#{ref['label.ref.forDescending']}" styleClass="sort_desc" />
															<f:attribute name="columnName" value="namValue" />
															<f:attribute name="sortOrder" value="desc" />
														</t:commandLink>
													</t:div>
												</h:panelGroup>
											</h:panelGrid>
										</f:facet>

										<h:outputText id="namOutput" value="#{srchResult.entityName}" />


									</t:column>


									<t:column id="orgNameCol"
										rendered="#{CMEntitySearchDataBean.renderLOB}">
										<f:facet name="header">




											<h:panelGrid id="PRGCMGTPGD268" columns="2">
												<h:outputLabel id="PRGCMGTOLL829"
													value="#{msg['label.entity.orgName']}"
													rendered="#{!CMEntitySearchDataBean.countyName}">
												</h:outputLabel>
												<h:outputLabel id="PRGCMGTOLL830"
													value="#{msg['label.entity.countyName']}"
													rendered="#{CMEntitySearchDataBean.countyName}">
												</h:outputLabel>
												<h:panelGroup id="PRGCMGTPGP148">
													<t:div styleClass="alignLeft">
														<t:commandLink id="orgNameCommandLink1"
															actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"
															rendered="#{CMEntitySearchDataBean.imageRender !='namCommandLink1'}">
															<m:div title="#{ref['label.ref.forAscending']}" styleClass="sort_asc" />
															<f:attribute name="columnName" value="orgNamValue" />
															<f:attribute name="sortOrder" value="asc" />
														</t:commandLink>
													</t:div>
													<t:div styleClass="alignLeft">
														<t:commandLink id="orgNamCommandLink2"
															actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"
															rendered="#{CMEntitySearchDataBean.imageRender !='namCommandLink2'}">
															<m:div title="#{ref['label.ref.forDescending']}" styleClass="sort_desc" />
															<f:attribute name="columnName" value="orgNamValue" />
															<f:attribute name="sortOrder" value="desc" />
														</t:commandLink>
													</t:div>
												</h:panelGroup>
											</h:panelGrid>
										</f:facet>

										<h:outputText id="orgNamOutput"
											value="#{srchResult.organisationName}" />

									</t:column>


									<t:column id="lobCol"
										rendered="#{!CMEntitySearchDataBean.renderLOB}">
										<f:facet name="header">

											<h:panelGrid id="PRGCMGTPGD269" columns="2">
												<h:outputLabel id="PRGCMGTOLL831" for="dataColLob"
													value="#{msg['label.entity.Lob']}">
												</h:outputLabel>
												<h:panelGroup id="dataColLob">
													<t:div styleClass="alignLeft">
														<t:commandLink id="lobCommandLink1"
															actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"
															rendered="#{CMEntitySearchDataBean.imageRender !='lobCommandLink1'}">
															<m:div title="#{ref['label.ref.forAscending']}" styleClass="sort_asc" />
															<f:attribute name="columnName" value="lobValue" />
															<f:attribute name="sortOrder" value="asc" />
														</t:commandLink>
													</t:div>
													<t:div styleClass="alignLeft">
														<t:commandLink id="lobCommandLink2"
															actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"
															rendered="#{CMEntitySearchDataBean.imageRender !='lobCommandLink2'}">
															<m:div title="#{ref['label.ref.forDescending']}" styleClass="sort_desc" />
															<f:attribute name="columnName" value="lobValue" />
															<f:attribute name="sortOrder" value="desc" />
														</t:commandLink>
													</t:div>
												</h:panelGroup>
											</h:panelGrid>
										</f:facet>

										<h:outputText id="lobOutput"
											value="#{srchResult.lineOfBusiness}" />






									</t:column>


									<t:column id="addCol">
										<f:facet name="header">

											<h:panelGrid id="PRGCMGTPGD270" columns="2">
												<h:outputLabel id="PRGCMGTOLL832" for="dataColAdd"
													value="#{msg['label.entity.Address']}">
												</h:outputLabel>
												<h:panelGroup id="dataColAdd">
													<t:div styleClass="alignLeft">
														<t:commandLink id="addCommandLink1"
															actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"
															rendered="#{CMEntitySearchDataBean.imageRender !='addCommandLink1'}">
															<m:div title="#{ref['label.ref.forAscending']}" styleClass="sort_asc" />
															<f:attribute name="columnName" value="addValue" />
															<f:attribute name="sortOrder" value="asc" />
														</t:commandLink>
													</t:div>
													<t:div styleClass="alignLeft">
														<t:commandLink id="addCommandLink2"
															actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"
															rendered="#{CMEntitySearchDataBean.imageRender !='addCommandLink2'}">
															<m:div title="#{ref['label.ref.forDescending']}" styleClass="sort_desc" />
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

											<h:panelGrid id="PRGCMGTPGD271" columns="2">
												<h:outputLabel id="PRGCMGTOLL833" for="dataColCity"
													value="#{msg['label.entity.City']}">
												</h:outputLabel>
												<h:panelGroup id="dataColCity">
													<t:div styleClass="alignLeft">
														<t:commandLink id="cityCommandLink1"
															actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"
															rendered="#{CMEntitySearchDataBean.imageRender !='cityCommandLink1'}">
															<m:div title="#{ref['label.ref.forAscending']}" styleClass="sort_asc" />
															<f:attribute name="columnName" value="cityValue" />
															<f:attribute name="sortOrder" value="asc" />
														</t:commandLink>
													</t:div>
													<t:div styleClass="alignLeft">
														<t:commandLink id="cityCommandLink2"
															actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"
															rendered="#{CMEntitySearchDataBean.imageRender !='cityCommandLink2'}">
															<m:div title="#{ref['label.ref.forDescending']}" styleClass="sort_desc" />
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
								<m:div
									rendered="#{empty CMEntitySearchDataBean.searchResultsList}"
									align="center">
									<h:outputText id="PRGCMGTOT1851"
										value="#{ent['err-sw-search-no-record-found']}"></h:outputText>
								</m:div>


								<t:dataScroller id="CMGTTOMDS71" for="entSrch" paginator="true"
									paginatorActiveColumnStyle='font-weight:bold;'
									paginatorMaxPages="3" immediate="false"
									pageCountVar="pageCount" pageIndexVar="pageIndex"
									firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
									rowsCountVar="rowsCount" styleClass="scroller">
									<f:facet name="previous">
										<h:outputText id="PRGCMGTOT1852"
											value="#{ref['label.ref.lt']}" rendered="#{pageIndex > 1}">
										</h:outputText>
									</f:facet>
									<f:facet name="next">
										<h:outputText id="PRGCMGTOT1853"
											value="#{ref['label.ref.gt']}"
											rendered="#{pageIndex < pageCount}">
										</h:outputText>
									</f:facet>
									<h:outputText id="PRGCMGTOT1854" rendered="#{rowsCount > 0}"
										value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
										style="float:left;position:relative;bottom:-6px;font-weight:bold;" />
								</t:dataScroller>

							</m:div>
						</m:div>
						<%-- End of 2nd section --%>
					</m:div>





				</m:div>
			</m:div>
		</m:body>
		<f:subview id="globalNotes">
			<jsp:include page="/jsp/commonEntities/commonNotes.jsp" />
		</f:subview>
		<f:subview id="notesearchId">
			<jsp:include page="/jsp/commonEntities/commonScripts.jsp" />
		</f:subview>
		 <m:div id="notesFocusflagID"
			rendered="#{commonEntityDataBean.noteFlag}">
			<h:inputText id="notesFocusflag" style="display:none"></h:inputText>
		</m:div>
	</h:form>


	</body>
</f:view>
<SCRIPT type="text/javascript">
 function onLoadFocus()
 {
		frmId = 'view<portlet:namespace/>:srEntFrm';
		//var pageId ='view<portlet:namespace/>:logCorrespondence:logCrspdAsscSubview:logCrspdAssociatedSubview:searchforAssCRSPDsubview:';
		if(document.getElementById(frmId+':cursorFocusId')!=null || document.getElementById(frmId+':cursorFocusId').value!='')
			{
		    var cursorFocus=frmId+':'+document.getElementById(frmId+':cursorFocusId').value;
			if(cursorFocus!=null && document.getElementById(cursorFocus)!= null)
				{
					document.getElementById(cursorFocus).focus();
				}
			}
 }
</SCRIPT>
<script type="text/JavaScript">

   var thisForm = 'view<portlet:namespace/>:srEntFrm';
   flagRole =((typeof (document.getElementById(thisForm+': controlRequiredForSearchNotes) !=  'undefined')) && document.getElementById(thisForm+':controlRequiredForSearchNotes) != null ?  document.getElementById(thisForm+':controlRequiredForSearchNotes).value:true);

</script>

