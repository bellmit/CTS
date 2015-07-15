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
	
	
	</SCRIPT>
<f:view>


	<h:form id="inqAbtEntity">

		<m:inputHidden name="send.InquiryaboutEntity.Data"
			value="sendinquiryaboutEntityData"></m:inputHidden>
		<m:inputHidden value="#{CMEntitySearchDataBean.loadRefernceData}"></m:inputHidden>
		<m:inputHidden value="#{CMEntitySearchControllerBean.intialData}"></m:inputHidden>
		<h:commandButton id="entityTypeChangedHiddenButton" styleClass="hide"
			value="Hidden Button To Click"
			action="#{CMEntitySearchControllerBean.renderRespEntityBlock}" />

		<m:body>


			<m:div styleClass="moreInfoBar">
				<m:div styleClass="infoTitle" align="left">
					<m:span styleClass="required">
						<h:outputText id="PRGCMGTOT574" value="#{ent['label-sw-reqFld']}"
							styleClass="colorMaroon" />
					</m:span>

				</m:div>

				<m:div styleClass="infoActions">

					<%--<h:outputLink id="PRGCMGTOLK24" value="/wps/myportal/MaintainEntity" rendered="#{CMEntitySearchControllerBean.controlRequired}">
						<h:outputText id="PRGCMGTOT575" styleClass="strong"							value="#{msg['title.label.entity.addEntity']}"></h:outputText>
					</h:outputLink>
					--%>
					<t:commandLink id="gotoAddEntity"
						rendered="#{CMEntitySearchControllerBean.controlRequired}"
						action="#{CMEntitySearchControllerBean.addEntityFromInquiryEntity}"
						onmousedown="javascript:document.getElementsByName('send.InquiryaboutEntity.Data')[0].value='';flagWarn=false;">
						<h:outputText id="PRGCMGTOT576" styleClass="strong"
							value="#{msg['title.label.entity.addEntity']}"></h:outputText>
						<f:param name="send.Correspondence.Entity"
							value="sendCorrespondenceEntity"></f:param>
					</t:commandLink>
					<h:outputLabel id="addntity1"
						value="#{msg['title.label.entity.addEntity']}"
						rendered="#{!CMEntitySearchControllerBean.controlRequired}" />

					<%--<h:commandButton id="gotoAddEntity" 					action="#{CMEntitySearchControllerBean.addEntityFromInquiryEntity}" 					styleClass="strong" 					style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:14px;WIDTH:70px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal;position:relative;top:2px"					value="#{msg['title.label.entity.addEntity']}" 					disabled="#{!CMEntitySearchControllerBean.controlRequired}">
						<f:param name="send.Correspondence.Entity"
									value="sendCorrespondenceEntity"></f:param>
					</h:commandButton>


				--%>
				</m:div>
			</m:div>
			
			<%--Render attribute added to handle the error message - ESPRD00920227 --%>
			
			<h:messages id="PRGCMGTMS4" showDetail="true" layout="table" rendered="#{CMEntitySearchDataBean.renderErrorMsg}"
				showSummary="false" styleClass="errorMessage" />


			<%-- Start of 1st section --%>
			<m:div styleClass="floatContainer">
				<m:div styleClass="fullwidth">
					<m:div styleClass="floatContainer">
						<m:div styleClass="leftCol33">
							<m:section id="PROVIDERMMS20120731164811272">
								<m:pod title="#{ref['label.ref.searchCriteria']}"
									styleClass="otherbg">
									<m:div>
										<m:table id="PROVIDERMMT20120731164811273" width="100%">
											<m:tr>
												<m:td colspan="2">
													<m:span styleClass="required">
														<h:outputText id="PRGCMGTOT577"
															value="#{ref['label.ref.reqSymbol']}" />
													</m:span>
													<h:outputLabel id="PRGCMGTOLL243" for="entityTypSrch"
														value="#{msg['label.entity.entityType']}">
													</h:outputLabel>
													<m:br />
													<t:selectOneMenu id="entityTypSrch"
														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.entityType}"
														onchange="javascript:flagWarn=false;entityTypeChangeAction();">
														<f:selectItem itemLabel="Please Select One" itemValue="" />
														<f:selectItems
															value="#{CMEntitySearchDataBean.entityDropDownList}" />


													</t:selectOneMenu>
													<m:br />

													<h:message id="PRGCMGTM98" for="entityTypSrch"
														showDetail="true" styleClass="errorMessage" />
												</m:td>
												<h:graphicImage id="PROVIDERGI20120731164811274" styleClass="blankImage" width="1" height="5"
													alt="" url="/images/x.gif" />
												<%--ADDED FOR THE Correspondence ESPRD00436016 --%>
												<m:td
													rendered="#{CMEntitySearchDataBean.renderTradingPartner}">
													<m:span styleClass="required">
														<h:outputText id="PRGCMGTOT578"
															value="#{ref['label.ref.reqSymbol']}" />
													</m:span>
													<h:outputLabel id="PRGCMGTOLL244" for="statusap"
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
													<h:message id="PRGCMGTM99" for="statusap" showDetail="ue"
														styleClass="errorMessage" />
												</m:td>
												<%--END FOR THE Correspondence ESPRD00436016 --%>
												<m:td rendered="#{CMEntitySearchDataBean.renderProvider}">
													<h:outputLabel id="PRGCMGTOLL245" for="statusprov"
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
											<m:table id="PROVIDERMMT20120731164811275">
												<m:tr>
													<m:td>
														<h:outputLabel id="PRGCMGTOLL246" for="enid"
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
														<h:outputLabel id="PRGCMGTOLL247" for="EntIDSrch"
															value="#{msg['label.entity.EntityID']}" />

														<t:inputText id="EntIDSrch" size="10"
															value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.entityID}">
														</t:inputText>
														<m:br />
														<h:message id="PRGCMGTM100" for="EntIDSrch"
															showDetail="true" styleClass="errorMessage" />
													</m:td>
												</m:tr>
											</m:table>
										</m:div>
									</m:div>

									<%-- For Entity Type - Provider -- render this div for provider --%>
									<m:div rendered="#{CMEntitySearchDataBean.renderProvider}">
										<m:table id="PROVIDERMMT20120731164811276" width="95">
											<m:tr>
												<m:td colspan="3">
													<h:outputLabel id="PRGCMGTOLL248" for="selectProviderType"
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
													<h:outputLabel id="PRGCMGTOLL249" for="provSrtnam"
														value="#{msg['label.entity.ProviderSortNam']}" />
													<m:br></m:br>
													<t:inputText id="provSrtnam" size="6"
														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.providerSortName}"
														maxlength="35">
													</t:inputText>
													<h:message id="PRGCMGTM101" for="provSrtnam"
														showDetail="true" styleClass="errorMessage" />
												</m:td>
												<m:td>
													<h:outputLabel id="PRGCMGTOLL250" for="provDBA"
														value="#{msg['label.entity.ProviderDBA']}" />
													<m:br></m:br>
													<t:inputText id="provDBA" size="5"
														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.providerDBA}"
														maxlength="35">
													</t:inputText>
												</m:td>
												<m:td>
													<h:outputLabel id="PRGCMGTOLL251" for="lobdropdowns"
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
													<h:outputLabel id="PRGCMGTOLL252" for="Pclassi"
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
													<h:message id="PRGCMGTM102" for="Pclassi" showDetail="ue"
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
									<m:div id="showhide_searchby_other"
										rendered="#{CMEntitySearchDataBean.renderNameSection}">
										<m:table id="PROVIDERMMT20120731164811277">
											<m:tr>
												<m:td>
													<h:outputLabel id="PRGCMGTOLL253" for="fnam"
														value="#{msg['label.entity.FirstName']}" />

													<m:br></m:br>
													<t:inputText id="fnam" size="10"
														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.firstName}"
														maxlength="25">
													</t:inputText>
													<h:message id="PRGCMGTM103" for="fnam" showDetail="true"
														styleClass="errorMessage" />
												</m:td>
												<m:td>
													<h:outputLabel id="PRGCMGTOLL254" for="mi_na"
														value="#{msg['label.entity.MI']}" />

													<m:br></m:br>
													<t:inputText id="mi_na" size="2" maxlength="25"
														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.middleInitial}">
													</t:inputText>
													<h:message id="PRGCMGTM104" for="mi_na" showDetail="true"
														styleClass="errorMessage" />
												</m:td>
												<m:td>
													<%-- for fixing defect:ESPRD00576206 --%>
													<m:span styleClass="required"
														rendered="#{CMEntitySearchDataBean.renderRequired}">
														<h:outputText id="CMGTOT14"
															value="#{ref['label.ref.reqSymbol']}" />
													</m:span>
													<h:outputLabel id="PRGCMGTOLL255" for="LNameSearch"
														value="#{msg['label.entity.LastName']}" />

													<m:br></m:br>
													<t:inputText id="LNameSearch" size="10"
														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.lastName}"
														maxlength="35">
													</t:inputText>
													<m:br></m:br>
													<h:message id="PRGCMGTM105" for="LNameSearch"
														showDetail="true" styleClass="errorMessage" />
												</m:td>
											</m:tr>
										</m:table>
									</m:div>

									<m:div id="showhide_orgnam"
										rendered="#{CMEntitySearchDataBean.renderOrganizationName}">
										<m:table id="PROVIDERMMT20120731164811278">
											<m:tr>
												<h:outputLabel id="PRGCMGTOLL256" for="orgNCD"
													value="#{msg['label.entity.OrgName']}" />
												<t:inputText id="orgNCD" size="12" maxlength="50"
													value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.organisationName}">
												</t:inputText>
												<m:br />
												<h:message id="PRGCMGTM106" for="orgNCD" showDetail="true"
													styleClass="errorMessage" />
											</m:tr>
										</m:table>
									</m:div>


									<m:div id="showhide_carrnam"
										rendered="#{CMEntitySearchDataBean.renderCarrierNam}">
										<m:table id="PROVIDERMMT20120731164811279">
											<m:tr>
												<h:outputLabel id="PRGCMGTOLL257" for="carriernam"
													value="#{msg['label.entity.CarrierName']}" />
												<t:inputText id="carriernam" size="15" maxlength="40"
													value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.carrierName}">
												</t:inputText>
												<m:br />
												<h:message id="PRGCMGTM107" for="carriernam"
													showDetail="true" styleClass="errorMessage" />
											</m:tr>
										</m:table>
									</m:div>

									<m:div id="showhide_tplPolcyHldr"
										rendered="#{CMEntitySearchDataBean.renderTPLPlcyHldr}">
										<m:table id="payeeTableId" width="95">
											<m:tr id="policyHolderLastNameTrId">
												<m:td id="policyHolderFirstNameTdId">
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
													<%-- for fixing defect:ESPRD00576206 --%>
													<m:span styleClass="required"
														rendered="#{CMEntitySearchDataBean.renderRequired}">
														<h:outputText id="CMGTOT15"
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

									<m:div styleClass="buttonRow">
										<h:commandButton id="PRGCMGTCB18" styleClass="formBtn"
											value="#{ref['label.ref.search']}" onclick="flagWarn=false;"
											action="#{CMEntitySearchControllerBean.getInquriyAboutEntitiesForCrspd}" />
										<h:outputText id="PRGCMGTOT579" escape="false"
											value="#{ref['label.ref.singleSpace']}" />
										<h:outputText id="PRGCMGTOT580" escape="false"
											value="#{ref['label.ref.singleSpace']}" />
										<h:commandButton id="PRGCMGTCB19" styleClass="formBtn"
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

								<t:dataTable cellspacing="0" styleClass="dataTable" id="entSrch"
									width="100%" columnClasses="columnClass"
									headerClass="headerClass" footerClass="footerClass"
									rowClasses="row_alt,row" rows="10"
									onmouseover="return tableMouseOver(this, event);"
									onmouseout="return tableMouseOut(this, event);"
									value="#{CMEntitySearchDataBean.searchResultsList}"
									first="#{CMEntitySearchDataBean.startIndexForSrchEntity}"
									var="srchResult" rowIndexVar="rowIndex">



									<h:column id="entidcol">
										<f:facet name="header">

											<h:panelGrid id="PRGCMGTPGD88" columns="2">
												<h:outputLabel id="PRGCMGTOLL258" for="dataColEntId"
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
										<t:commandLink id="edit"
											action="#{CMEntitySearchControllerBean.getCMInquiryAbtEntityDtls}"
											onmousedown="flagWarn=false;">
											<h:outputText id="a" value="#{srchResult.entityID}" />
											<f:param name="systemIDVal" value="#{srchResult.systemID}"></f:param>
											<f:param name="entiyIDVal" value="#{srchResult.entityID}"></f:param>
											<f:param name="commonEntSKVal"
												value="#{srchResult.commonEntitySK}"></f:param>
											<f:param name="send.InquiryaboutEntity.Data"
												value="sendinquiryaboutEntityData"></f:param>
											<f:param name="indexCode" value="#{rowIndex}"></f:param>

										</t:commandLink>
									</h:column>

									<h:column id="nameCol">
										<f:facet name="header">

											<h:panelGrid id="PRGCMGTPGD89" columns="2">
												<h:outputLabel id="PRGCMGTOLL259" for="dataColentname"
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


									<h:column id="lobCol">
										<f:facet name="header">

											<h:panelGrid id="PRGCMGTPGD90" columns="2">
												<h:outputLabel id="PRGCMGTOLL260" for="dataColLob"
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

											<h:panelGrid id="PRGCMGTPGD91" columns="2">
												<h:outputLabel id="PRGCMGTOLL261" for="dataColAdd"
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

											<h:panelGrid id="PRGCMGTPGD92" columns="2">
												<h:outputLabel id="PRGCMGTOLL262" for="dataColCity"
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

								<%--<t:dataScroller id="CMGTTOMDS21" for="entSrch" paginator="true"									paginatorActiveColumnStyle='font-weight:bold;'									paginatorMaxPages="3" immediate="false"									pageCountVar="pageCount" pageIndexVar="pageIndex"									firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"									rowsCountVar="rowsCount" styleClass="scroller">
									<f:facet name="previous">
										<h:commandLink id="PRGCMGTCL102" styleClass="underline"											action="#{CMEntitySearchControllerBean.previous}"											rendered="#{CMEntitySearchDataBean.showPrevious}">
											<h:outputText id="PRGCMGTOT581" value="#{ref['label.ref.lt']}">
											</h:outputText>
										</h:commandLink>
									</f:facet>

									<f:facet name="next">
										<h:commandLink id="PRGCMGTCL103" styleClass="underline"											action="#{CMEntitySearchControllerBean.next}"											rendered="#{CMEntitySearchDataBean.showNext}">
											<h:outputText id="PRGCMGTOT582" value="#{ref['label.ref.gt']}">
											</h:outputText>
										</h:commandLink>
									</f:facet>


									<h:outputText id="PRGCMGTOT583" rendered="#{rowsCount > 0}"
										value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
										styleClass="scrollerBold" />
								</t:dataScroller>--%>
								<t:dataScroller id="CMGTTOMDS22" for="entSrch" paginator="true"
									paginatorActiveColumnStyle='font-weight:bold;'
									paginatorMaxPages="3" immediate="false"
									pageCountVar="pageCount" pageIndexVar="pageIndex"
									firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
									rowsCountVar="rowsCount" styleClass="scroller"
									onclick="flagWarn=false;">
									<f:facet name="previous">
										<h:outputText id="PRGCMGTOT584" value="#{ref['label.ref.lt']}"
											rendered="#{pageIndex > 1}">
										</h:outputText>
									</f:facet>
									<f:facet name="next">
										<h:outputText id="PRGCMGTOT585" value="#{ref['label.ref.gt']}"
											rendered="#{pageIndex < pageCount}">
										</h:outputText>
									</f:facet>
									<h:outputText id="PRGCMGTOT586" rendered="#{rowsCount > 0}"
										value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
										style="float:left;position:relative;bottom:-6px;font-weight:bold;" />
								</t:dataScroller>
								<m:br />
								<m:br />

							</m:div>
						</m:div>
						<%-- End of 2nd section --%>
					</m:div>
				</m:div>
			</m:div>
		</m:body>
	</h:form>
</f:view>
