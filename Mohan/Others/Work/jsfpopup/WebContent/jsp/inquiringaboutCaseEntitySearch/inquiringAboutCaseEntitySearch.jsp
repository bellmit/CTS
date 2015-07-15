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

<script type="text/javascript">
		
	var logcaseActionSubmitter = false;	// added for defect ESPRD00330098
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
	document.getElementById(thisFormInq+':focusIdInq').value = 'inqEntityType';
	var hiddenButtonObject = findObjectByPartOfID('entityTypeChangedHiddenButton');

	hiddenButtonObject.click();
}
var thisFormInq = 'view<portlet:namespace/>:inqAbtCaseEntity';
        
		if(window.addEventListener)
		{
			window.addEventListener("load", focusOnLoad, false)
			window.onload=focusOnLoad()
		}
		else if(window.attachEvent)
		{	
			window.attachEvent("onload", focusOnLoad)
		}

		function focusOnLoad() 
		{
   			var focusPage = '#'+document.getElementById(thisFormInq+':focusIdInq').value;
			if(focusPage != '' && focusPage != '#') 
			{
			document.getElementById('view<portlet:namespace/>:inqAbtCaseEntity:entityTypSrch').focus();
			document.getElementById(thisFormInq+':focusIdInq').value = '';
			return;
				
			}
			
			
		}
</script>
<f:view>
	<h:form id="inqAbtCaseEntity">
		
	
		<m:inputHidden value="#{CMEntitySearchDataBean.loadRefernceData}"></m:inputHidden>
		<%-- modified for the defect ESPRD00651399 --%>
		<%--<m:inputHidden name="send.InqAbt.CaseEntityDetails.Action" value="sendInqAbtCaseEntityDetailsAction"></m:inputHidden> --%>
		<h:inputHidden id="focusIdInq" value="#{CMEntitySearchDataBean.focusThisId}" />
	<h:inputHidden id="PRGCMGTIH21" value="#{CMEntitySearchControllerBean.link2Show}"></h:inputHidden> 
		<h:commandButton id="entityTypeChangedHiddenButton" styleClass="hide"			value="Hidden Button To Click"			action="#{CMEntitySearchControllerBean.renderRespEntityBlock}" />
		<m:body>
			<m:div styleClass="moreInfoBar">
				<m:div styleClass="infoTitle" align="left">
					<m:span styleClass="required">
						<h:outputText id="PRGCMGTOT564" value="#{ent['label-sw-reqFld']}"							styleClass="colorMaroon" />
					</m:span>
				</m:div>
				<m:div styleClass="infoActions">
					<%--Modified for defect ESPRD00357943 starts --%>
					
					<t:commandLink id="gotoAE"						action="#{CMEntitySearchControllerBean.addEntityFromInquiryCaseEntity}"						rendered="#{!CMEntitySearchDataBean.disableAddEntity}"						onclick="javascript:flagWarn=false;">
						<h:outputText id="PRGCMGTOT565" styleClass="strong"							value="#{msg['title.label.entity.addEntity']}"></h:outputText>
							<f:param name="send.Correspondence.Entity"
												value="sendCorrespondenceEntity"></f:param>
					</t:commandLink>
					<h:outputText id="PRGCMGTOT566" styleClass="strong"							value="#{msg['title.label.entity.addEntity']}" rendered="#{CMEntitySearchDataBean.disableAddEntity}">
					</h:outputText>
						
					<%--Modified for defect ESPRD00357943 ends --%>
				</m:div>
			</m:div>
			
			<%-- Render attribute added to handle the error message - ESPRD00920239 --%>
			<h:messages id="PRGCMGTMS3" showDetail="true" layout="table" showSummary="false"	rendered="#{CMEntitySearchDataBean.renderErrorMsg}"			styleClass="errorMessage" />
			
			<m:div styleClass="floatContainer">
				<m:div styleClass="fullwidth">
					<m:div styleClass="floatContainer">
						<m:div styleClass="leftCol33">
							<m:section id="PROVIDERMMS20120731164811263">
								<m:pod title="#{ref['label.ref.searchCriteria']}"
									styleClass="otherbg">
									<m:div>
										<m:table id="PROVIDERMMT20120731164811264" width="100%">
											<m:tr>
												<m:td colspan="2">
													<m:span styleClass="required">
														<h:outputText id="PRGCMGTOT567" value="#{ref['label.ref.reqSymbol']}" />
													</m:span>
													<h:outputLabel id="PRGCMGTOLL223" for="entityTypSrch"														value="#{msg['label.entity.entityType']}">
													</h:outputLabel>
													<m:br />

													<t:selectOneMenu id="entityTypSrch"														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.entityType}"														valueChangeListener="#{CMEntitySearchControllerBean.entityTypeChangeAction}"														onchange="javascript:flagRole=true;flagWarn=true;logcaseActionSubmitter=true;entityTypeChangeAction();">
														<f:selectItem itemLabel="Please Select One" itemValue="" />
														<f:selectItems
															value="#{CMEntitySearchDataBean.entityDropDownList}" />
													</t:selectOneMenu>
													<m:br />
													<h:message id="PRGCMGTM88" for="entityTypSrch" showDetail="true"														styleClass="errorMessage" />
												</m:td>
												<h:graphicImage id="PROVIDERGI20120731164811265" styleClass="blankImage" width="1" height="5"
													alt="" url="/images/x.gif"/>
												<%--ADDED FOR THE CR ESPRD00436016 --%>
														<m:td
															rendered="#{CMEntitySearchDataBean.renderTradingPartner}">
															<m:span styleClass="required">
																<h:outputText id="PRGCMGTOT568" value="#{ref['label.ref.reqSymbol']}" />
															</m:span>
															<h:outputLabel id="PRGCMGTOLL224" for="statusap"																value="#{msg['label.entity.status']}">
															</h:outputLabel>
															<m:br />
															<h:selectOneMenu id="statusap"																onchange="javascript:flagWarn=false;focusThis(this.id);"																value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.tradingPartnerStatus}">
																<f:selectItem itemLabel="Please Select One" itemValue="" />
																<f:selectItems
																	value="#{CMEntitySearchDataBean.tradingParnterStatusList}" />

															</h:selectOneMenu>
															<m:br />
															<h:message id="PRGCMGTM89" for="statusap" showDetail="ue"																styleClass="errorMessage" />
														</m:td>
												<%--END FOR THE CR ESPRD00436016 --%>
												<m:td rendered="#{CMEntitySearchDataBean.renderProvider}">
													<h:outputLabel id="PRGCMGTOLL225" for="statusprov"														value="#{msg['label.entity.status']}">
													</h:outputLabel>
													<t:selectOneRadio id="statusprov" enabledClass="black"														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.providerStatus}">
														<f:selectItem itemLabel="#{msg['label.entity.active']}"
															itemValue="A" />
														<f:selectItem itemLabel="#{msg['label.entity.inactive']}"
															itemValue="I" />
													</t:selectOneRadio>
												</m:td>
											</m:tr>
										</m:table>
									</m:div>
									
									<m:div id="showhide_searchby_provunenrolled">
										<m:div>
											<m:table id="PROVIDERMMT20120731164811266">
												<m:tr>
													<m:td>
														<h:outputLabel id="PRGCMGTOLL226" for="enid"															value="#{msg['label.entity.EntityIDType']}" />
														<m:br></m:br>
														<t:selectOneMenu id="enid"															value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.entityIDType}">
															<f:selectItem itemLabel="Please Select One" itemValue="" />
															<f:selectItems
																value="#{CMEntitySearchDataBean.entityIDTypeList}" />
														</t:selectOneMenu>
													</m:td>
													<m:td>
														<h:outputLabel id="PRGCMGTOLL227" for="EntIDSrch"															value="#{msg['label.entity.EntityID']}" />

														<t:inputText id="EntIDSrch" size="10"															value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.entityID}"></t:inputText>
														<m:br />
														<h:message id="PRGCMGTM90" for="EntIDSrch" showDetail="true"															styleClass="errorMessage" />
													</m:td>
												</m:tr>
											</m:table>
										</m:div>
									</m:div>
									
									<m:div rendered="#{CMEntitySearchDataBean.renderProvider}">
										<m:table id="PROVIDERMMT20120731164811267" width="95">
											<m:tr>
												<m:td colspan="3">
													<h:outputLabel id="PRGCMGTOLL228" for="selectProviderType"														value="#{msg['label.entity.ProviderType']}" />
													<m:br></m:br>
													<t:selectOneMenu id="selectProviderType"														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.providerType}">
														<f:selectItem itemLabel="Please Select One" itemValue="" />
														<f:selectItems
															value="#{CMEntitySearchDataBean.provTypeList}" />
													</t:selectOneMenu>
												</m:td>
											</m:tr>
											<m:tr>
												<m:td>
													<h:outputLabel id="PRGCMGTOLL229" for="provSrtnam"														value="#{msg['label.entity.ProviderSortNam']}" />
													<m:br></m:br>
													<t:inputText id="provSrtnam" size="6"														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.providerSortName}"														maxlength="35"></t:inputText>
													<h:message id="PRGCMGTM91" for="provSrtnam" showDetail="true"														styleClass="errorMessage" />
												</m:td>
												<m:td>
													<h:outputLabel id="PRGCMGTOLL230" for="provDBA"														value="#{msg['label.entity.ProviderDBA']}" />
													<m:br></m:br>
													<t:inputText id="provDBA" size="5"														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.providerDBA}"														maxlength="35"></t:inputText>
												</m:td>
												<m:td>
													<h:outputLabel id="PRGCMGTOLL231" for="lobdropdowns"														value="#{msg['label.entity.Lob']}" />
													<m:br></m:br>
													<m:br></m:br>
													<t:selectOneMenu id="lobdropdowns"														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.lineOfBusiness}">
														<f:selectItems value="#{CMEntitySearchDataBean.lobList}" />
													</t:selectOneMenu>
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
															<t:inputText id="TPfnam" size="10"																value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.policyHolderFirstName}"																maxlength="25"></t:inputText>
															<h:message id="TPmsg2" for="TPfnam" showDetail="true"																styleClass="errorMessage" />
														</m:td>
														<m:td id="TPtd4">
															<h:outputLabel id="TPlabel4" for="TPLNameSearch"																value="#{msg['label.entity.LastName']}" />

															<m:br id="TPbr3"></m:br>
															<t:inputText id="TPLNameSearch" size="10"																value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.policyHolderLastName}"																maxlength="35"></t:inputText>
															<m:br id="TPbr4"></m:br>
															<h:message id="TPmsg3" for="TPLNameSearch" showDetail="true"																styleClass="errorMessage" />
														</m:td>
													</m:tr>
												</m:table>
											</m:div>
											<%-- End of TPL Policy Holder for CR ESPRD00486064--%>
											<%--ADDED FOR THE CR ESPRD00436016 --%>
											<m:div id="aPatdiv1" rendered="#{CMEntitySearchDataBean.renderTradingPartner}">
												<m:table id="aPatTable" width="95">
										
														<m:tr>
														<m:td id="TPtd2">
																<h:outputLabel id="PRGCMGTOLL232" for="Pclassi" 																	value="#{msg['label.entity.tradingpartner.classification']}" />
																<m:br></m:br>
																<h:selectOneMenu id="Pclassi"																	value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.tradingPartnerClassification}"																	onmousedown="javascript:flagWarn=false;">
																	<f:selectItem itemLabel="Please Select One"
																		itemValue="" />
																	<f:selectItems
																		value="#{CMEntitySearchDataBean.tradingPartnerclassificationList}" />
																</h:selectOneMenu>
														<m:br></m:br>
													<h:message id="PRGCMGTM92" for="Pclassi" showDetail="ue"																styleClass="errorMessage" />
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
											
									<m:div id="showhide_searchby_other"
										rendered="#{CMEntitySearchDataBean.renderNameSection}">
										<m:table id="PROVIDERMMT20120731164811268">
											<m:tr>
												<m:td>
													<h:outputLabel id="PRGCMGTOLL233" for="fnam"														value="#{msg['label.entity.FirstName']}" />
													<m:br></m:br>
													<t:inputText id="fnam" size="10"														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.firstName}"														maxlength="25"></t:inputText>
													<h:message id="PRGCMGTM93" for="fnam" showDetail="true"														styleClass="errorMessage" />
												</m:td>
												<m:td>
													<h:outputLabel id="PRGCMGTOLL234" for="mi_na"														value="#{msg['label.entity.MI']}" />
													<m:br></m:br>
													<t:inputText id="mi_na" size="2" maxlength="25"														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.middleInitial}"></t:inputText>
													<h:message id="PRGCMGTM94" for="mi_na" showDetail="true"														styleClass="errorMessage" />
												</m:td>
												<m:td>
													<h:outputLabel id="PRGCMGTOLL235" for="LNameSearch"														value="#{msg['label.entity.LastName']}" />
													<m:br></m:br>
													<t:inputText id="LNameSearch" size="10"														value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.lastName}"														maxlength="35"></t:inputText>
													<m:br></m:br>
													<h:message id="PRGCMGTM95" for="LNameSearch" showDetail="true"														styleClass="errorMessage" />
												</m:td>
											</m:tr>
										</m:table>
									</m:div>
									<m:div id="showhide_orgnam"
										rendered="#{CMEntitySearchDataBean.renderOrganizationName}">
										<m:table id="PROVIDERMMT20120731164811269">
											<m:tr>
												<h:outputLabel id="PRGCMGTOLL236" for="orgNCD"													value="#{msg['label.entity.OrgName']}" />
												<t:inputText id="orgNCD" size="12" maxlength="50"													value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.organisationName}"></t:inputText>
												<m:br />
												<h:message id="PRGCMGTM96" for="orgNCD" showDetail="true"													styleClass="errorMessage" />
											</m:tr>
										</m:table>
									</m:div>
									<m:div id="showhide_carrnam"
										rendered="#{CMEntitySearchDataBean.renderCarrierNam}">
										<m:table id="PROVIDERMMT20120731164811270">
											<m:tr>
												<h:outputLabel id="PRGCMGTOLL237" for="carriernam"													value="#{msg['label.entity.CarrierName']}" />
												<t:inputText id="carriernam" size="15" maxlength="40"													value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.carrierName}"></t:inputText>
												<m:br />
												<h:message id="PRGCMGTM97" for="carriernam" showDetail="true"													styleClass="errorMessage" />
											</m:tr>
										</m:table>
									</m:div>
									<m:div id="showhide_counam"
										rendered="#{CMEntitySearchDataBean.countyName}">
										<m:table id="PROVIDERMMT20120731164811271">
											<m:tr>
												<h:outputLabel id="PRGCMGTOLL2388" for="countyname"													value="#{msg['label.entity.OrgName']}" />
												<t:inputText id="countyName" size="12" maxlength="50"													value="#{CMEntitySearchDataBean.entitySearchCriteriaVO.organisationName}"></t:inputText>
												<m:br />
												<h:message id="PRGCMGTM98" for="countyName" showDetail="true"													styleClass="errorMessage" />
											</m:tr>
										</m:table>
									</m:div>
									<m:div styleClass="buttonRow">
										<t:commandButton id="CMGTTOMCBN1" styleClass="formBtn" onclick="javascript:flagWarn=false;"										value="#{ref['label.ref.search']}"											action="#{CMEntitySearchControllerBean.getInquriyAboutEntitiesForCase}" />
										<h:outputText id="PRGCMGTOT569" escape="false"											value="#{ref['label.ref.singleSpace']}" />
										<h:outputText id="PRGCMGTOT570" escape="false"											value="#{ref['label.ref.singleSpace']}" />
										<t:commandButton id="CMGTTOMCBN2" styleClass="formBtn" onclick="javascript:flagWarn=false;"											value="#{ent['label-sw-reset']}"											action="#{CMEntitySearchControllerBean.resetSearch}" />

									</m:div>

								</m:pod>
							</m:section>

						</m:div>
						<m:div styleClass="rightCol66"
							rendered="#{CMEntitySearchDataBean.renderSearchResult}">
							<m:div id="searchResults">
								<m:br />
								<%-- Modified for the HeapDump Fix --%>
								<t:dataTable cellspacing="0" styleClass="dataTable" id="entSrch"
									width="100%" columnClasses="columnClass"
									headerClass="headerClass" footerClass="footerClass"
									first="#{CMEntitySearchDataBean.searchResultRowIndex}"
									rowClasses="row_alt,row" rows="10"
									onmouseover="return tableMouseOver(this, event);"
									onmouseout="return tableMouseOut(this, event);"
									value="#{CMEntitySearchDataBean.searchResultsList}"
									var="srchResult"
									rowOnClick="javascript:flagWarn=false;firstChild.lastChild.click();">
									
									<t:column id="entidcol">
										<f:facet name="header">

											<h:panelGrid id="PRGCMGTPGD83" columns="2">
												<h:outputLabel id="PRGCMGTOLL238" for="dataColEntId"
													value="#{msg['label.entity.EntityID']}">
												</h:outputLabel>
												<h:panelGroup id="dataColEntId">
													<t:div styleClass="alignLeft">
														<t:commandLink id="entCommandLink1"
															onmousedown="javascript:flagWarn=false;"
															actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"
															rendered="#{CMEntitySearchDataBean.imageRender !='entCommandLink1'}">
															<m:div title="for ascending" styleClass="sort_asc" />
															<f:attribute name="columnName" value="entIdVal" />
															<f:attribute name="sortOrder" value="asc" />
														</t:commandLink>
													</t:div>
													<t:div styleClass="alignLeft">
														<t:commandLink id="entCommandLink2"
															onmousedown="javascript:flagWarn=false;"
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
											onmousedown="javascript:flagWarn=false;"
											action="#{CMEntitySearchControllerBean.getCMInquiryAbtCaseEntityDtls}">
											<h:outputText id="a" value="#{srchResult.entityID}" />
											<f:param name="systemIDVal" value="#{srchResult.systemID}"></f:param>
											<f:param name="entiyIDVal" value="#{srchResult.entityID}"></f:param>
											<f:param name="commonEntitySK"
												value="#{srchResult.commonEntitySK}"></f:param>
											<%--ESPRD00512731_UC-PGM-CRM-018.5_03SEP2010 --%>
											<f:param name="send.InqAbt.CaseEntityDetails.Action"
												value="sendInqAbtCaseEntityDetailsAction"></f:param>
										</t:commandLink>
									</t:column>
									<t:column id="nameCol">
										<f:facet name="header">

											<h:panelGrid id="PRGCMGTPGD84" columns="2">
												<h:outputLabel id="PRGCMGTOLL239" for="dataColentname"
													value="#{msg['label.entity.Name']}">
												</h:outputLabel>
												<h:panelGroup id="dataColentname">
													<t:div styleClass="alignLeft">
														<t:commandLink id="namCommandLink1"
															onmousedown="javascript:flagWarn=false;"
															actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"
															rendered="#{CMEntitySearchDataBean.imageRender !='namCommandLink1'}">
															<m:div title="for ascending" styleClass="sort_asc" />
															<f:attribute name="columnName" value="namValue" />
															<f:attribute name="sortOrder" value="asc" />
														</t:commandLink>
													</t:div>
													<t:div styleClass="alignLeft">
														<t:commandLink id="namCommandLink2"
															onmousedown="javascript:flagWarn=false;"
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


									</t:column>


									<t:column id="lobCol">
										<f:facet name="header">

											<h:panelGrid id="PRGCMGTPGD85" columns="2">
												<h:outputLabel id="PRGCMGTOLL240" for="dataColLob"
													value="#{msg['label.entity.Lob']}">
												</h:outputLabel>
												<h:panelGroup id="dataColLob">
													<t:div styleClass="alignLeft">
														<t:commandLink id="lobCommandLink1"
															onmousedown="javascript:flagWarn=false;"
															actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"
															rendered="#{CMEntitySearchDataBean.imageRender !='lobCommandLink1'}">
															<m:div title="for ascending" styleClass="sort_asc" />
															<f:attribute name="columnName" value="lobValue" />
															<f:attribute name="sortOrder" value="asc" />
														</t:commandLink>
													</t:div>
													<t:div styleClass="alignLeft">
														<t:commandLink id="lobCommandLink2"
															onmousedown="javascript:flagWarn=false;"
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






									</t:column>


									<t:column id="addCol">
										<f:facet name="header">

											<h:panelGrid id="PRGCMGTPGD86" columns="2">
												<h:outputLabel id="PRGCMGTOLL241" for="dataColAdd"
													value="#{msg['label.entity.Address']}">
												</h:outputLabel>
												<h:panelGroup id="dataColAdd">
													<t:div styleClass="alignLeft">
														<t:commandLink id="addCommandLink1"
															onmousedown="javascript:flagWarn=false;"
															actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"
															rendered="#{CMEntitySearchDataBean.imageRender !='addCommandLink1'}">
															<m:div title="for ascending" styleClass="sort_asc" />
															<f:attribute name="columnName" value="addValue" />
															<f:attribute name="sortOrder" value="asc" />
														</t:commandLink>
													</t:div>
													<t:div styleClass="alignLeft">
														<t:commandLink id="addCommandLink2"
															onmousedown="javascript:flagWarn=false;"
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




									</t:column>

									<t:column id="cityCol">
										<f:facet name="header">

											<h:panelGrid id="PRGCMGTPGD87" columns="2">
												<h:outputLabel id="PRGCMGTOLL242" for="dataColCity"
													value="#{msg['label.entity.City']}">
												</h:outputLabel>
												<h:panelGroup id="dataColCity">
													<t:div styleClass="alignLeft">
														<t:commandLink id="cityCommandLink1"
															onmousedown="javascript:flagWarn=false;"
															actionListener="#{CMEntitySearchControllerBean.sortSearchResults}"
															rendered="#{CMEntitySearchDataBean.imageRender !='cityCommandLink1'}">
															<m:div title="for ascending" styleClass="sort_asc" />
															<f:attribute name="columnName" value="cityValue" />
															<f:attribute name="sortOrder" value="asc" />
														</t:commandLink>
													</t:div>
													<t:div styleClass="alignLeft">
														<t:commandLink id="cityCommandLink2"
															onmousedown="javascript:flagWarn=false;"
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
									</t:column>
								</t:dataTable>

								<t:dataScroller id="CMGTTOMDS20" for="entSrch" paginator="true"
									onclick="javascript:flagWarn=false;"
									paginatorActiveColumnStyle='font-weight:bold;'
									paginatorMaxPages="3" immediate="false"
									pageCountVar="pageCount" pageIndexVar="pageIndex"
									firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
									rowsCountVar="rowsCount" styleClass="scroller">
									<f:facet name="previous">
										<h:outputText id="PRGCMGTCL100" styleClass="underline"
											value=" #{ref['label.ref.lt']} " rendered="#{pageIndex > 1}"></h:outputText>
									</f:facet>
									<f:facet name="next">
										<h:outputText id="PRGCMGTCL101" styleClass="underline"
											value=" #{ref['label.ref.gt']} "
											rendered="#{pageIndex < pageCount}"></h:outputText>
									</f:facet>
									<h:outputText id="PRGCMGTOT573" rendered="#{rowsCount > 0}"
										value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
										styleClass="scrollerBold" />
								</t:dataScroller>
							</m:div>
							<m:br id= brID1 />
						<m:br id= brID2 />
												
						</m:div>
						
					</m:div>
				</m:div>
			</m:div>
		</m:body>
	</h:form>

</f:view>
