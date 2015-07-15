<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@ taglib uri="http://www.acs-inc.com/auditLog" prefix="audit"%>
<script type="text/javascript">
function editCaseTypeDeleteConfirmation(){
	if(confirm('Are you sure you want to Delete?'))
	{
		return true;
	}else{
		flagWarn=true;
		return true;
		}
}
	</script>
<portlet:defineObjects />

<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.ProgramMessages"
	var="cont" />

<f:loadBundle
	basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_ContactCaseTypeMaintenance"
	var="msg" />

<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.EnterpriseMessages"
	var="ent" />

<f:view>
	<%--Commented for defect ESPRD00723971 starts --%>
	<%--
	<t:saveState id="CMGTTOMSS314" value="#{CaseTypeDataBean.showAddCaseTypes}"></t:saveState>
	<t:saveState id="CMGTTOMSS315" value="#{CaseTypeDataBean.showEditCaseTypes}"></t:saveState>
	<t:saveState id="CMGTTOMSS316" value="#{CaseTypeDataBean.showAddCaseSteps}"></t:saveState>
	<t:saveState id="CMGTTOMSS317" value="#{CaseTypeDataBean.showEditCaseSteps}"></t:saveState>
	<t:saveState id="CMGTTOMSS318" value="#{CaseTypeDataBean.showEditCaseEvents}"></t:saveState>
	<t:saveState id="CMGTTOMSS319" value="#{CaseTypeDataBean.showAddCaseEvents}"></t:saveState>
	<t:saveState id="CMGTTOMSS320" value="#{CaseTypeDataBean.maintainCaseStepList}"></t:saveState>
	<t:saveState id="CMGTTOMSS321" value="#{CaseTypeDataBean.maintainCaseTypeList}"></t:saveState>
	<t:saveState id="CMGTTOMSS322" value="#{CaseTypeDataBean.maintainCaseCustomFieldsList}"></t:saveState>
	<t:saveState id="CMGTTOMSS323" value="#{CaseTypeDataBean.maintainCaseEventList}"></t:saveState>
	<t:saveState id="CMGTTOMSS324" value="#{CaseTypeDataBean.maintainCaseTemplatesList}"></t:saveState>
	<t:saveState id="CMGTTOMSS325" value="#{CaseTypeDataBean.caseTypeStepVO}"></t:saveState>
	<t:saveState id="CMGTTOMSS326" value="#{CaseTypeDataBean.caseTypeStepVO.workunitvo}"></t:saveState>
	<t:saveState id="CMGTTOMSS327" value="#{CaseTypeDataBean.caseTypeEventVO}"></t:saveState>
	<t:saveState id="CMGTTOMSS328" value="#{CaseTypeDataBean.caseTypeVO}"></t:saveState>
	<t:saveState id="CMGTTOMSS329" value="#{CaseTypeDataBean.caseTypeTemplatesVO}"></t:saveState>
	<t:saveState id="CMGTTOMSS330" value="#{CaseTypeDataBean.editCaseStepRowIndex}"></t:saveState>
	<t:saveState id="CMGTTOMSS331" value="#{CaseTypeDataBean.editCaseEventRowIndex}"></t:saveState>
	<t:saveState id="CMGTTOMSS332" value="#{CaseTypeDataBean.noCaseStepsData}"></t:saveState>
	<t:saveState id="CMGTTOMSS333" value="#{CaseTypeDataBean.nocustomfieldsData}"></t:saveState>
	<t:saveState id="CMGTTOMSS334" value="#{CaseTypeDataBean.noTemplatesData}"></t:saveState>
	<t:saveState id="CMGTTOMSS335" value="#{CaseTypeDataBean.noCaseEventsData}"></t:saveState>
	<t:saveState id="CMGTTOMSS336" value="#{CaseTypeDataBean.noCaseTypesData}"></t:saveState>
	<t:saveState id="CMGTTOMSS337" value="#{CaseTypeDataBean.bussinessUnits}"></t:saveState>
	<t:saveState id="CMGTTOMSS338" value="#{CaseTypeDataBean.caseStepAutRoutoToList}"></t:saveState>
	<t:saveState id="CMGTTOMSS339" value="#{CaseTypeDataBean.dfltNotifyAlertList}"></t:saveState>
	<t:saveState id="CMGTTOMSS340" value="#{CaseTypeDataBean.stepOrderList}"></t:saveState>
	<t:saveState id="CMGTTOMSS341" value="#{CaseTypeDataBean.completionBasedOnList}"></t:saveState>
	<t:saveState id="CMGTTOMSS342" value="#{CaseTypeDataBean.dfltAlertBasedOnList}"></t:saveState>
	<t:saveState id="CMGTTOMSS343" value="#{CaseTypeDataBean.dfltAlertSendBasedOnList}"></t:saveState>
	<t:saveState id="CMGTTOMSS344" value="#{CaseTypeDataBean.eventDfltAlertBasedOnList}"></t:saveState>
	<t:saveState id="CMGTTOMSS345" value="#{CaseTypeDataBean.eventDfltNotifyAlertList}"></t:saveState>
	<t:saveState id="CMGTTOMSS346" value="#{CaseTypeDataBean.eventDfltSendAlertList}"></t:saveState>
	<t:saveState id="CMGTTOMSS347" value="#{CaseTypeDataBean.caseTypeEventsList}"></t:saveState>
	<t:saveState id="CMGTTOMSS348" value="#{CaseTypeDataBean.caseEventTypeList}"></t:saveState>
	<t:saveState id="CMGTTOMSS349" value="#{CaseTypeDataBean.caseTypeStepsList}"></t:saveState>
	<t:saveState id="CMGTTOMSS350" value="#{CaseTypeDataBean.incudeflag}"></t:saveState>
	<t:saveState id="CMGTTOMSS351" value="#{CaseTypeDataBean.unSelectedCustomFields}"></t:saveState>
	<t:saveState id="CMGTTOMSS352" value="#{CaseTypeDataBean.unSelectedTemplates}"></t:saveState>
	<t:saveState id="CMGTTOMSS353" value="#{CaseTypeDataBean.caseStepNoIncludeList}"></t:saveState>
	<t:saveState id="CMGTTOMSS354" value="#{CaseTypeDataBean.caseEventNoIncludeList}"></t:saveState>
	<t:saveState id="CMGTTOMSS355" value="#{CaseTypeDataBean.editCaseTypeRowIndex}"></t:saveState>
	<t:saveState id="CMGTTOMSS356" value="#{CaseTypeDataBean.templateDropDownList}"></t:saveState>
	<t:saveState id="CMGTTOMSS357" value="#{CaseTypeDataBean.showDeleteMessage}"></t:saveState>
	<t:saveState id="CMGTTOMSS358" value="#{CaseTypeDataBean.showDeleteFlag}"></t:saveState>
	<t:saveState id="CMGTTOMSS359" value="#{CaseTypeDataBean.showDescOrderFlag}"></t:saveState>
	<t:saveState id="CMGTTOMSS360" value="#{CaseTypeDataBean.showTypeCodeFlag}"></t:saveState>
	<t:saveState id="CMGTTOMSS361" value="#{CaseTypeDataBean.showTypeDescFlag}"></t:saveState>
	<t:saveState id="CMGTTOMSS362" value="#{CaseTypeDataBean.showStepSuccessMsg}"></t:saveState>
	<t:saveState id="CMGTTOMSS363" value="#{CaseTypeDataBean.caseTypeNoActive}"></t:saveState>
	<t:saveState id="CMGTTOMSS364" value="#{CaseTypeDataBean.showStepActMsg}"></t:saveState>
	<t:saveState id="CMGTTOMSS365" value="#{CaseTypeDataBean.showEventActMsg}"></t:saveState>
	<t:saveState id="CMGTTOMSS366" value="#{CaseTypeDataBean.resetLink}"></t:saveState>
	<t:saveState id="CMGTTOMSS367" value="#{CaseTypeDataBean}"></t:saveState>--%>
	<%--defect ESPRD00723971 ends --%>
	<f:subview id="provService">
		<jsp:include page="/jsp/script/scripts.jsp" />
	</f:subview>

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
	//for audit
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
	var hiddenValue = document.getElementById('view<portlet:namespace/>:maintaincasetype:auditlogDetails:'+id);
   
	if ((hiddenValue == null) ||(hiddenValue == '')|| (hiddenValue.length == 0)) {
		hideMe('audit_plus');
	} else if ((hiddenValue.value == 'false')) 
	{
		hideMe('audit_plus');
	} 
}	
	
//start of CFR
	var thisForm = 'view<portlet:namespace/>:maintaincasetype';
		function focusThis(id) { 
   			document.getElementById(thisForm+':firstFieldFocusID').value=id;  			
	   	}						
		if (window.addEventListener) //DOM method for binding an event
            	window.addEventListener("load", onLoadFunctions, false);
      	else if (window.attachEvent) //IE exclusive method for binding an event
            	window.attachEvent("onload", onLoadFunctions);
      	else if (document.getElementById) //support older modern browsers
           		window.onload=onLoadFunctions;		
		function onLoadFunctions() {
			focusOnLoad();
			focusOnFirstField();	
		}
		function focusOnLoad() {
			try{	
   				var focusPage = '#'+document.getElementById(thisForm+':focusId').value;

				if(focusPage != '' && focusPage != '#') {				
					document.location.href=focusPage;
				}
			}catch(e){
			}
			focusOnFirstField();
   		}
	//end of CFR
//CR_ESPRD00168761_MaintainCaseTypes_10AUG2010
	function focusOnFirstField(){
			var fieldID = document.getElementById(thisForm+':firstFieldFocusID').value;
			//alert(document.getElementById(thisForm+':'+fieldID));
			var fieldIDObj = document.getElementById(thisForm+':'+fieldID);
			if(fieldIDObj != null ){
				try{
					fieldIDObj.focus();
					fieldIDObj.value = fieldIDObj.value;
				}catch(e){
					//alert(e);
				}
			}
	}
	function focusPage(name) { 
		document.getElementById(thisForm+':focusId').value=name;  			
	}	
//eof CR_ESPRD00168761_MaintainCaseTypes_10AUG2010
	</script>
	<%-- Commented for Defect ESPRD00628599 --%>
	<%-- <body onBeforeUnload="javascript:warnBeforeExit('maintaincasetype');" 
		onload="renderAudit('audit_open');"> --%>

	<body onload="warnBeforeCancel();renderAudit('audit_open');">
	<m:anchor name="maintaincasetypepagefocus"></m:anchor>
	<h:form id="maintaincasetype"
		onclick="javascript:setformId('maintaincasetype');">

		<%--Added for CFR starts--%>
		<h:inputHidden id="focusId"
			value="#{CaseTypeDataBean.maintainCaseTypeFocusId}"></h:inputHidden>
		<h:inputHidden id="firstFieldFocusID"
			value="#{CaseTypeDataBean.firstFieldFocusID}"></h:inputHidden>
		<h:inputHidden id="warnBeforeExitId"
			value="#{CaseTypeDataBean.warnBeforeExitStatus}"></h:inputHidden>
		<%--Added for CFR ends--%>

		<h:inputHidden id="internalSubmit" value=" "></h:inputHidden>
		<%-- Grey Mode Code Starts Here  --%>

		<h:inputHidden id="userHavingUpdateRole"
			value="#{CaseTypeDataBean.controlRequired}" />
		<%-- Grey Mode Code Ends Here --%>
		<h:inputHidden id="validvaluesid"
			value="#{CaseTypeControllerBean.loadValidValues}"
			rendered="#{CaseTypeDataBean.valiValueFlag}" />
		<%--Modified for defect ESPRD00723971 starts --%>
		<h:inputHidden id="link2ShowId"
			value="#{CaseTypeControllerBean.link2Show}" />
		<%--defect ESPRD00723971 ends --%>
		<%--m:body onload="renderAudit('audit_open');"--%>
		<%--<h:inputHidden id="PRGCMGTIH25" value="#{CaseTypeControllerBean.allCaseTypes}"></h:inputHidden>--%>
		<%--<h:inputHidden id="UsersList" value="#{CaseTypeControllerBean.usersList}"></h:inputHidden>--%>
		<m:div styleClass="floatContainer">
			<m:div styleClass="fullwidth">
				<m:div styleClass="moreInfoBar">
					<m:div styleClass="infoTitle" align="left">
						<m:span styleClass="required">
							<h:outputText id="PRGCMGTOT1146"
								value="#{msg['label.casetype.reqFiled']}" style="color:maroon" />
						</m:span>
					</m:div>

				</m:div>
				<m:div styleClass="infoActions">

					<%-- CR_ESPRD00352986 Starts --%>
					<%-- <h:commandLink id="PRGCMGTCL166" 						rendered="#{!CaseTypeDataBean.disableaddSave}"						onmousedown="javascript:flagWarn=false;">
						<h:outputText id="PRGCMGTOT1147" value="#{msg['label.casetype.save']}" />
					</h:commandLink>
					<h:outputText id="PRGCMGTOT1148" value="#{msg['label.casetype.save']}"						rendered="#{CaseTypeDataBean.disableaddSave}" />

					<h:outputText id="PRGCMGTOT1149" escape="false" value="&nbsp;" />
					<h:outputText id="PRGCMGTOT1150" value="|" />
					<h:commandLink id="PRGCMGTCL167" 						rendered="#{!CaseTypeDataBean.disableaddCaseTypereset}"						onmousedown="javascript:flagWarn=false;">
						<h:outputText id="PRGCMGTOT1151" value="#{msg['label.casetype.reset']}" />
					</h:commandLink>
					<h:outputText id="PRGCMGTOT1152" value="#{msg['label.casetype.reset']}"						rendered="#{CaseTypeDataBean.disableaddCaseTypereset}" />

					<h:outputText id="PRGCMGTOT1153" escape="false" value="&nbsp;" />
					<h:outputText id="PRGCMGTOT1154" value="|" />--%>
					<%--CR_ESPRD00373565_MaintainCaseTypes_06AUG2010 --%>
					<%--<h:commandLink action="#{CaseTypeControllerBean.doAuditKeyListOperation}" id="casetypeAuditLogId"					onmousedown="javascript:flagWarn=false;">
					<h:outputText id="PRGCMGTOT1155"  value="#{msg['label.casetype.auditLog']}" />
				</h:commandLink>
				<h:outputText id="PRGCMGTOT1156"  value="|" />--%>

					<%-- CR_ESPRD00352986 ENDS --%>
					<%-- EOF CR_ESPRD00373565_MaintainCaseTypes_06AUG2010 --%>
					<t:commandLink id="PRGCMGTCL168"
						action="#{CaseTypeControllerBean.redirect}">
						<h:outputText id="PRGCMGTOT1157"
							value="#{msg['label.casetype.cancel']}" />
						<f:param name="com.ibm.faces.portlet.page.view"
							value="#{msg['label.casetype.linkCancel']}" />
					</t:commandLink>

				</m:div>
				<%--Added for the defect ESPRD00853112--%>
				<h:messages id="PRGCMGTMS16" layout="table" showDetail="true"
					showSummary="false" styleClass="errorMessage" />

				<m:section id="PROVIDERMMS20120731164811501" styleClass="otherbg">
					<m:legend>
						<h:outputText id="PRGCMGTOT1158" styleClass="strong"
							value="#{msg['label.casetype.maintaincasetype']}" />
					</m:legend>
					<m:div styleClass="msgBox"
						rendered="#{CaseTypeDataBean.showDeleteMessage}">
						<h:outputText id="PRGCMGTOT1159"
							value="#{msg['label.casetype.deleteMessage']}" />
					</m:div>
					<m:table id="PROVIDERMMT20120731164811502" styleClass="tableBar" width="100%">
						<m:tr>
							<m:td styleClass="tableAction">
								<h:commandButton id="PRGCMGTCB42" styleClass="formBtn"
									onmousedown="javascript:flagWarn=false;"
									disabled="#{CaseTypeDataBean.disableAddCaseType}"
									value="#{msg['label.casetype.addCaseType']}"
									action="#{CaseTypeControllerBean.showAddCaseType}" />
							</m:td>
						</m:tr>
					</m:table>
					<t:dataTable id="caseTypeDataTable" cellspacing="0"
						styleClass="dataTable" columnClasses="columnClass"
						headerClass="tableHead" footerClass="footerClass"
						rowClasses="row,row_alt" width="100%" rows="10" var="casetype"
						value="#{CaseTypeDataBean.maintainCaseTypeList}"
						rendered="#{CaseTypeDataBean.noCaseTypesData}"
						first="#{CaseTypeDataBean.startIndexForCaseType}"
						rowOnMouseOver="this.origClassName = this.className;this.className='row_mouse row_link';"
						rowOnMouseOut="if (this.origClassName) this.className = this.origClassName;"
						rowOnClick="javascript:flagWarn=false;childNodes[0].lastChild.click();"
						rowIndexVar="index">
						<t:column id="activecol">
							<f:facet name="header">
								<t:panelGrid id="CMGTTOMPGD34" columns="2">
									<h:outputLabel id="PRGCMGTOLL533"
										value="#{msg['label.casetype.Active']}"
										for="activeCommandLink1" />
									<t:panelGroup id="CMGTTOMPGP34">
										<t:div>
											<t:commandLink id="activeCommandLink1"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{CaseTypeControllerBean.getAllSortedCaseTypes}"
												rendered="#{CaseTypeDataBean.imageRender != 'activeCommandLink1'}">
												<h:graphicImage id="PROVIDERGI20120731164811503" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}" styleClass="sort_asc"
													width="8" url="/images/x.gif" />
												<f:attribute name="columnName" value="ct_activeValue" />
												<f:attribute name="sortOrder" value="asc" />
											</t:commandLink>
										</t:div>
										<t:div>
											<t:commandLink id="activeCommandLink2"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{CaseTypeControllerBean.getAllSortedCaseTypes}"
												rendered="#{CaseTypeDataBean.imageRender != 'activeCommandLink2'}">
												<h:graphicImage id="PROVIDERGI20120731164811504" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}" styleClass="sort_desc"
													width="8" url="/images/x.gif" />
												<f:attribute name="columnName" value="ct_activeValue" />
												<f:attribute name="sortOrder" value="desc" />
											</t:commandLink>
										</t:div>
									</t:panelGroup>
								</t:panelGrid>
							</f:facet>
							<%--<h:graphicImage alt="true" styleClass="ind_check_yes" width="15"
									rendered="#{casetype.caseTypeStatusCode =='Y'}" />
								<h:outputText id="vid" value="" />--%>


							<t:commandLink id="editcasetype"
								onmousedown="javascript:flagWarn=false;"
								action="#{CaseTypeControllerBean.getCaseTypeDetails}">
								<f:param name="rowIndex" value="#{index}"></f:param>
								<h:outputText id="activeValue"
									value="#{casetype.caseTypeStatusCode}" />
							</t:commandLink>

						</t:column>
						<t:column id="caseshortDesc">
							<f:facet name="header">
								<t:panelGrid id="CMGTTOMPGD35" columns="2">
									<h:outputLabel id="PRGCMGTOLL534"
										value="#{msg['label.casetype.shortDesc']}"
										for="colDesCommandLink1" />
									<t:panelGroup id="CMGTTOMPGP35">
										<t:div>
											<t:commandLink id="colDesCommandLink1"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{CaseTypeControllerBean.getAllSortedCaseTypes}"
												rendered="#{CaseTypeDataBean.imageRender != 'colDesCommandLink1'}">
												<h:graphicImage id="PROVIDERGI20120731164811505" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}" styleClass="sort_asc"
													width="8" url="/images/x.gif" />
												<f:attribute name="columnName" value="ct_shortDesc" />
												<f:attribute name="sortOrder" value="asc" />
											</t:commandLink>
										</t:div>
										<t:div>
											<t:commandLink id="shortDescCommandLink2"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{CaseTypeControllerBean.getAllSortedCaseTypes}"
												rendered="#{CaseTypeDataBean.imageRender != 'shortDescCommandLink2'}">

												<h:graphicImage id="PROVIDERGI20120731164811506" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}" styleClass="sort_desc"
													width="8" url="/images/x.gif" />
												<f:attribute name="columnName" value="ct_shortDesc" />
												<f:attribute name="sortOrder" value="desc" />
											</t:commandLink>
										</t:div>
									</t:panelGroup>
								</t:panelGrid>
							</f:facet>
							<h:outputText id="caseshortdescVal" value="#{casetype.shortDesc}" />
						</t:column>
						<t:column id="caselongDesc">
							<f:facet name="header">
								<t:panelGrid id="CMGTTOMPGD36" columns="2">
									<h:outputLabel id="PRGCMGTOLL535"
										value="#{msg['label.casetype.longDesc']}"
										for="longDescCommandLink1" />
									<t:panelGroup id="CMGTTOMPGP36">
										<t:div>
											<t:commandLink id="longDescCommandLink1"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{CaseTypeControllerBean.getAllSortedCaseTypes}"
												rendered="#{CaseTypeDataBean.imageRender != 'longDescCommandLink1'}">
												<h:graphicImage id="PROVIDERGI20120731164811507" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}" styleClass="sort_asc"
													width="8" url="/images/x.gif" />
												<f:attribute name="columnName" value="ct_longDesc" />
												<f:attribute name="sortOrder" value="asc" />
											</t:commandLink>
										</t:div>
										<t:div>
											<t:commandLink id="longDescCommandLink2"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{CaseTypeControllerBean.getAllSortedCaseTypes}"
												rendered="#{CaseTypeDataBean.imageRender != 'longDescCommandLink2'}">
												<h:graphicImage id="PROVIDERGI20120731164811508" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}" styleClass="sort_desc"
													width="8" url="/images/x.gif" />
												<f:attribute name="columnName" value="ct_longDesc" />
												<f:attribute name="sortOrder" value="desc" />
											</t:commandLink>
										</t:div>
									</t:panelGroup>
								</t:panelGrid>
							</f:facet>

							<h:outputText id="caselongdescVal" value="#{casetype.longDesc}"
								style="color: blue" />
							<%--
						<h:commandLink id="editcasetype1"								action="#{CaseTypeControllerBean.getCaseTypeDetails}">
						</h:commandLink>   
						--%>
						</t:column>
						<t:column id="casebussUnit">
							<f:facet name="header">
								<t:panelGrid id="CMGTTOMPGD37" columns="2">
									<h:outputLabel id="PRGCMGTOLL536"
										value="#{msg['label.casetype.bussinessUnit']}"
										for="bussUnitCommandLink1" />
									<t:panelGroup id="CMGTTOMPGP37">
										<t:div>
											<t:commandLink id="bussUnitCommandLink1"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{CaseTypeControllerBean.getAllSortedCaseTypes}"
												rendered="#{CaseTypeDataBean.imageRender != 'bussUnitCommandLink1'}">
												<h:graphicImage id="PROVIDERGI20120731164811509" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}" styleClass="sort_asc"
													width="8" url="/images/x.gif" />
												<f:attribute name="columnName" value="ct_caseBussUnit" />
												<f:attribute name="sortOrder" value="asc" />
											</t:commandLink>
										</t:div>
										<t:div>
											<t:commandLink id="bussUnitCommandLink2"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{CaseTypeControllerBean.getAllSortedCaseTypes}"
												rendered="#{CaseTypeDataBean.imageRender != 'bussUnitCommandLink2'}">
												<h:graphicImage id="PROVIDERGI20120731164811510" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}" styleClass="sort_desc"
													width="8" url="/images/x.gif" />

												<f:attribute name="columnName" value="ct_caseBussUnit" />
												<f:attribute name="sortOrder" value="desc" />
											</t:commandLink>
										</t:div>
									</t:panelGroup>
								</t:panelGrid>
							</f:facet>
							<h:outputText id="caseBussUnitVal"
								value="#{casetype.busnUnitTypeCodeDesc}" />
						</t:column>
						<t:column id="casetypesupApproval">
							<f:facet name="header">
								<t:panelGrid id="CMGTTOMPGD38" columns="2">
									<h:outputLabel id="PRGCMGTOLL537" for="supApprCommandLink1"
										value="#{msg['label.casetype.supervisorApproval']}" />
									<t:panelGroup id="CMGTTOMPGP38">
										<t:div>
											<t:commandLink id="supApprCommandLink1"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{CaseTypeControllerBean.getAllSortedCaseTypes}"
												rendered="#{CaseTypeDataBean.imageRender != 'supApprCommandLink1'}">
												<h:graphicImage id="PROVIDERGI20120731164811511" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}" styleClass="sort_asc"
													width="8" url="/images/x.gif" />
												<f:attribute name="columnName" value="ct_supApproval" />
												<f:attribute name="sortOrder" value="asc" />
											</t:commandLink>
										</t:div>
										<t:div>
											<t:commandLink id="supApprCommandLink2"
												onmousedown="javascript:flagWarn=false;"
												actionListener="#{CaseTypeControllerBean.getAllSortedCaseTypes}"
												rendered="#{CaseTypeDataBean.imageRender != 'supApprCommandLink2'}">
												<h:graphicImage id="PROVIDERGI20120731164811512" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}" styleClass="sort_desc"
													width="8" url="/images/x.gif" />
												<f:attribute name="columnName" value="ct_supApproval" />
												<f:attribute name="sortOrder" value="desc" />
											</t:commandLink>
										</t:div>
									</t:panelGroup>
								</t:panelGrid>
							</f:facet>

							<%--<h:graphicImage alt="true" styleClass="ind_check_yes" width="15"
									rendered="#{casetype.sprvsrRevwReqdInd =='true'}" />--%>
							<h:outputText id="casesupapprVal"
								value="#{casetype.sprvsrRevwReqdInd}" />
						</t:column>
					</t:dataTable>
					<t:dataTable id="CMGTTOMDT2" cellspacing="0" styleClass="dataTable"
						columnClasses="columnClass" headerClass="headerClass"
						var="nocasetype" footerClass="footerClass"
						rowClasses="row_alt,row" width="100%" rows="5"
						rendered="#{!CaseTypeDataBean.noCaseTypesData}">

						<t:column id="CMGTTOMCS22">
							<f:facet name="header">
								<h:outputText id="PRGCMGTOT1160"
									value="#{msg['label.casetype.Active']}">
								</h:outputText>
							</f:facet>
						</t:column>
						<t:column id="CMGTTOMCS23">
							<f:facet name="header">
								<h:outputText id="PRGCMGTOT1161"
									value="#{msg['label.casetype.shortDesc']}" />
							</f:facet>
						</t:column>
						<t:column id="CMGTTOMCS24">
							<f:facet name="header">
								<h:outputText id="PRGCMGTOT1162"
									value="#{msg['label.casetype.longDesc']}" />
							</f:facet>
						</t:column>
						<t:column id="CMGTTOMCS25">
							<f:facet name="header">
								<h:outputText id="PRGCMGTOT1163"
									value="#{msg['label.casetype.bussinessUnit']}" />
							</f:facet>
						</t:column>
						<t:column id="CMGTTOMCS26">
							<f:facet name="header">
								<h:outputText id="PRGCMGTOT1164"
									value="#{msg['label.casetype.supervisorApproval']}" />
							</f:facet>
						</t:column>
					</t:dataTable>
					<m:table id="PROVIDERMMT20120731164811513" styleClass="dataTable" cellspacing="0" width="100%"
						rendered="#{!CaseTypeDataBean.noCaseTypesData}">
						<m:tr>
							<m:td align="center">
								<h:outputText id="PRGCMGTOT1165"
									value="#{msg['label.casetype.table.notabledata']}" />
							</m:td>
						</m:tr>
					</m:table>
					<%--  Data Scroller--%>
					<m:div onclick="javascript:flagWarn=false;"
						onmousedown="javascript:flagWarn=false;">
						<t:dataScroller id="CMGTTOMDS42" pageCountVar="pageCount"
							pageIndexVar="pageIndex" onclick="javascript:flagWarn=false;"
							paginator="true" paginatorActiveColumnStyle='font-weight:bold;'
							paginatorMaxPages="3" immediate="false" for="caseTypeDataTable"
							firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
							rowsCountVar="rowsCount" styleClass="dataScroller">
							<f:facet name="previous">
								<h:outputText id="PRGCMGTOT1166" styleClass="underline"
									value="#{cont['label.ref.lt']}" rendered="#{pageIndex > 1}"></h:outputText>
							</f:facet>
							<f:facet name="next">
								<h:outputText id="PRGCMGTOT1167" styleClass="underline"
									value="#{cont['label.ref.gt']}"
									rendered="#{pageIndex < pageCount}"></h:outputText>
							</f:facet>
							<h:outputText id="PRGCMGTOT1168"
								value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
								styleClass="dataScrollerText" rendered="#{rowsCount>0}">
							</h:outputText>
						</t:dataScroller>
					</m:div>
					<m:br />
					<m:br />

					<m:div id="edtcs" styleClass=""
						rendered="#{CaseTypeDataBean.showEditCaseTypes || CaseTypeDataBean.showAddCaseTypes}">

						<m:anchor name="addEditCaseTypes" />
						<%-- added for CFR--%>
						<m:div styleClass="moreInfo">
							<%--  EDIT CASE TYPES SECTION --%>
							<m:div styleClass="moreInfoBar"
								rendered="#{CaseTypeDataBean.showEditCaseTypes}">
								<m:div styleClass="infoTitle">
									<h:outputText id="PRGCMGTOT1169"
										value="#{msg['label.casetype.editCaseType']}" />
								</m:div>
								<m:div styleClass="infoActions">

									<%-- srikanth start --%>

									<t:commandLink id="PRGCMGTCL169" styleClass="strong"
										onmousedown="javascript:flagWarn=false;"
										rendered="#{!CaseTypeDataBean.disableSaveCasTyp}"
										action="#{CaseTypeControllerBean.updateCaseType}">
										<h:outputText id="PRGCMGTOT1170"
											value="#{ent['label-sw-save']}" />
									</t:commandLink>

									<h:outputText id="PRGCMGTOT1171"
										value="#{ent['label-sw-save']}"
										rendered="#{CaseTypeDataBean.disableSaveCasTyp}" />
									<h:outputText id="PRGCMGTOT1172"
										value="#{msg['label.casetype.pipe']}" />

									<t:commandLink id="PRGCMGTCL170"
										onmousedown="javascript:flagWarn=false;"
										rendered="#{!CaseTypeDataBean.disableResetCasTyp}"
										action="#{CaseTypeControllerBean.resetEditCaseTypes}">
										<h:outputText id="PRGCMGTOT1173"
											value="#{ent['label-sw-reset']}" />
									</t:commandLink>
									<h:outputText id="PRGCMGTOT1174"
										value="#{ent['label-sw-reset']}"
										rendered="#{CaseTypeDataBean.disableResetCasTyp}" />

									<h:outputText id="PRGCMGTOT1175"
										value="#{msg['label.casetype.pipe']}" />

									<%--Modified to enable delete action starts--%>
									<%--Modified defect ESPRD00558558--%>
									<%--<h:commandLink id="PRGCMGTCL171" 										rendered="#{CaseTypeDataBean.disableDeleteCasTyp}"										onmousedown="javascript:flagWarn=false;if (!confirm('Are you sure that you want to Delete?')) return(false);"										action="#{CaseTypeControllerBean.deleteCaseType}">
										<h:outputText id="PRGCMGTOT1176" value="#{ent['label-sw-delete']}" />
									</h:commandLink>--%>
									<h:outputText id="PRGCMGTOT1177"
										value="#{ent['label-sw-delete']}"
										rendered="#{CaseTypeDataBean.disableDeleteCasTyp}" />

									<h:commandButton id="PRGCMGTCB43"
										rendered="#{!CaseTypeDataBean.disableDeleteCasTyp}"
										styleClass="deleteCancelAsLink" style="margin-top: 6px;"
										onclick="javascript:flagWarn=false;if (!confirm('Are you sure that you want to Delete?')) return(false);"
										action="#{CaseTypeControllerBean.deleteCaseType}"
										value="#{ent['label-sw-delete']}" />
									<%--Modified to enable delete action ends--%>
									<%--<h:outputText id="PRGCMGTOT1178" escape="false" value="&nbsp;"></h:outputText>--%>
									<h:outputText id="PRGCMGTOT1179"
										value="#{msg['label.casetype.pipe']}" />

									<%-- CR_ESPRD00352986 Starts --%>
									<t:commandLink
										action="#{CaseTypeControllerBean.doAuditKeyListOperation}"
										id="casetypeAuditLogId"
										onmousedown="javascript:flagWarn=false;">
										<h:outputText id="PRGCMGTOT1155"
											value="#{msg['label.casetype.auditLog']}" />
									</t:commandLink>

									<%-- CR_ESPRD00352986 Ends --%>
									<h:outputText id="PRGCMGTOT1156"
										value="#{msg['label.casetype.pipe']}" />
									<t:commandLink id="PRGCMGTCL172"
										action="#{CaseTypeControllerBean.cancelEditCaseTypes}">
										<h:outputText id="PRGCMGTOT1180"
											value="#{ent['label-sw-cancel']}" />
									</t:commandLink>
								</m:div>
							</m:div>
							<%--  ADD CASE TYPES SECTION --%>
							<m:div styleClass="moreInfoBar"
								rendered="#{CaseTypeDataBean.showAddCaseTypes}">
								<m:div styleClass="infoTitle">
									<h:outputText id="PRGCMGTOT1181"
										value="#{msg['label.casetype.addCaseType']}" />
								</m:div>
								<m:div styleClass="infoActions">
									<t:commandLink id="PRGCMGTCL173" styleClass="strong"
										onmousedown="javascript:flagWarn=false;"
										rendered="#{!CaseTypeDataBean.disableAddLink}"
										action="#{CaseTypeControllerBean.addCaseType}">
										<h:outputText id="PRGCMGTOT1182"
											value="#{ent['label-sw-save']}" />
									</t:commandLink>
									<h:outputText id="PRGCMGTOT1183"
										value="#{ent['label-sw-save']}"
										rendered="#{CaseTypeDataBean.disableAddLink}" />
									<h:outputText id="PRGCMGTOT1184"
										value="#{msg['label.casetype.pipe']}" />

									<%--  rendered="#{!CaseTypeDataBean.disableAddLink}"
										onmousedown="javascript:flagWarn=false;"--%>
									<t:commandLink id="PRGCMGTCL174"
										value="#{ent['label-sw-reset']}"
										onmousedown="javascript:flagWarn=false;"
										action="#{CaseTypeControllerBean.resetAddCaseTypes}">
									</t:commandLink>
									<%-- 
									<h:outputText id="PRGCMGTOT1185" value="#{ent['label-sw-reset']}"										rendered="#{CaseTypeDataBean.disableAddLink}" /> --%>

									<h:outputText id="PRGCMGTOT1186"
										value="#{msg['label.casetype.pipe']}" />
									<t:commandLink id="PRGCMGTCL175"
										action="#{CaseTypeControllerBean.cancelAddCaseTypes}">
										<h:outputText id="PRGCMGTOT1187"
											value="#{ent['label-sw-cancel']}" />
									</t:commandLink>
								</m:div>
							</m:div>

							<%-- Common to both Edit and Add blocks --%>
							<m:div styleClass="moreInfoContent">
								<m:div>
								</m:div>
								<m:div styleClass="msgBox"
									rendered="#{CaseTypeDataBean.showSucessMessage}">
									<h:outputText id="PRGCMGTOT1188"
										value="#{ent['label-sw-success']}" />
								</m:div>
								<%--Moved to top--%>
								<%--	<m:div styleClass="msgBox"
									rendered="#{CaseTypeDataBean.showDeleteMessage}">
									<h:outputText id="PRGCMGTOT1189" value="#{msg['label.casetype.deleteMessage']}" />
								</m:div>--%>

								<m:div styleClass="msgBox"
									rendered="#{CaseTypeDataBean.showDeleteFlag}">
									<h:messages id="PRGCMGTMS14" showDetail="true" layout="table"
										showSummary="false" styleClass="errorMessage" />
								</m:div>
								<m:div rendered="#{CaseTypeDataBean.showTypeDescFlag}">
									<h:messages id="PRGCMGTMS15" showDetail="true" layout="table"
										showSummary="false" styleClass="errorMessage" />
								</m:div>
								<m:div style="width:100%">
									<m:table id="PROVIDERMMT20120731164811514" cellspacing="0" width="100%">
										<m:tr>

											<m:td>
												<m:div styleClass="padb">
													<h:outputLabel id="PRGCMGTOLL538" for="activeradioval"
														value="#{msg['label.casetype.Active']}">
													</h:outputLabel>
													<%--  Modification for Defect ESPRD00614020  --%>
													<%--  Removed  javascript:this.form.submit() from    
                                                        onclick="javascript:internalsubmmit('maintaincasetype');javascript:this.form.submit();" --%>

													<h:selectOneRadio id="activeradioval"
														disabled="#{CaseTypeDataBean.disableUIFields}"
														valueChangeListener="#{CaseTypeControllerBean.inactiveCaseType}"
														onclick="javascript:internalsubmmit('maintaincasetype');"
														value="#{CaseTypeDataBean.caseTypeVO.caseTypeStatusCode}">
														<f:selectItem itemLabel="#{msg['label.casetype.Yes']}"
															itemValue="Yes" />
														<f:selectItem itemLabel="#{msg['label.casetype.No']}"
															itemValue="No" />
													</h:selectOneRadio>
												</m:div>

											</m:td>
											<m:td>
												<m:div styleClass="padb">
													<m:span styleClass="required">
														<h:outputText id="PRGCMGTOT1190"
															value="#{cont['label.ref.reqSymbol']}" />
													</m:span>
													<h:outputLabel id="PRGCMGTOLL539" for="casetypesDesc"
														value="#{msg['label.casetype.shortDesc']}"></h:outputLabel>
													<m:br></m:br>
													<h:inputText id="casetypesDesc" size="20"
														disabled="#{CaseTypeDataBean.disableUIFields}"
														value="#{CaseTypeDataBean.caseTypeVO.shortDesc}"
														maxlength="30"></h:inputText>
													<m:br></m:br>
													<h:message id="PRGCMGTM169" for="casetypesDesc"
														styleClass="errorMessage" />
												</m:div>
											</m:td>
											<m:td>
												<m:div styleClass="padb">
													<m:span styleClass="required">
														<h:outputText id="PRGCMGTOT1191"
															value="#{cont['label.ref.reqSymbol']}" />
													</m:span>
													<h:outputLabel id="PRGCMGTOLL540" for="casetypelDesc"
														value="#{msg['label.casetype.longDesc']}"></h:outputLabel>
													<m:br></m:br>
													<h:inputText id="casetypelDesc" size="40"
														disabled="#{CaseTypeDataBean.disableUIFields}"
														value="#{CaseTypeDataBean.caseTypeVO.longDesc}"
														maxlength="50"></h:inputText>
													<m:br></m:br>
													<h:message id="PRGCMGTM170" for="casetypelDesc"
														style="color:red" />
												</m:div>
											</m:td>
											<m:td>
												<m:div styleClass="padb">
													<h:outputLabel id="PRGCMGTOLL541" for="selonemen"
														value="#{msg['label.casetype.bussinessUnit']}">
													</h:outputLabel>
													<m:br></m:br>
													<h:selectOneMenu id="selonemen"
														disabled="#{CaseTypeDataBean.disableUIFields}"
														onchange="javascript:flagWarn=false;selectOne=true;"
														value="#{CaseTypeDataBean.caseTypeVO.busnUnitCaseTypeCode}">

														<f:selectItems value="#{CaseTypeDataBean.bussinessUnits}" />
													</h:selectOneMenu>

												</m:div>
											</m:td>
											<m:td>
												<m:div styleClass="padb">
													<h:outputLabel id="PRGCMGTOLL542" for="supVal"
														value="#{msg['label.casetype.supervisorApproval']}">
													</h:outputLabel>

													<h:selectOneRadio id="supVal"
														disabled="#{CaseTypeDataBean.disableUIFields}"
														value="#{CaseTypeDataBean.caseTypeVO.sprvsrRevwReqdInd}">
														<f:selectItem itemLabel="#{msg['label.casetype.Yes']}"
															itemValue="Yes" />
														<f:selectItem itemLabel="#{msg['label.casetype.No']}"
															itemValue="No" />
													</h:selectOneRadio>
												</m:div>

											</m:td>


										</m:tr>
									</m:table>

									<%-- Strating of Tabs  --%>
									<m:div styleClass="tabs" id="tabssection">
										<m:anchor name="maintainCaseTypeSort" />
										<%-- Begin - Modified the code for the defect id ESPRD00723971_05DEC2011 --%>
										<t:panelTabbedPane id="CMGTTOMPTP2" width="99%"
											serverSideTabSwitch="true" immediateTabChange="false"
											onclick="javascript:flagWarn=false;addeditrow('maintaincasetype');">
											<t:panelTab label="#{msg['label.casetype.casesteps']}"
												id="tabone">
												<m:div rendered="#{CaseTypeDataBean.caseTypeCaseStepsFlag}">
													<f:subview id="casesteps1">
														<jsp:include
															page="/jsp/maintaincasetypes/inc_maintainCaseTypeCaseSteps.jsp" />
													</f:subview>
												</m:div>
											</t:panelTab>
											<t:panelTab label="#{msg['label.casetype.caseevents']}"
												id="tabtwo">
												<m:div rendered="#{CaseTypeDataBean.caseTypeCaseEventsFlag}">
													<f:subview id="caseevents1">
														<jsp:include
															page="/jsp/maintaincasetypes/inc_maintainCaseTypeCaseEvents.jsp" />
													</f:subview>
												</m:div>
											</t:panelTab>
											<t:panelTab label="#{msg['label.casetype.customfields']}"
												id="tabthree">
												<m:div
													rendered="#{CaseTypeDataBean.caseTypeCustomFieldsFlag}">
													<f:subview id="customfields1">
														<jsp:include
															page="/jsp/maintaincasetypes/inc_maintainCaseTypeCustomFields.jsp" />
													</f:subview>
												</m:div>
											</t:panelTab>
											<t:panelTab label="#{msg['label.casetype.templates']}"
												id="tabfour">
												<m:div rendered="#{CaseTypeDataBean.caseTypeTemplatesFlag}">
													<f:subview id="templates1">
														<jsp:include
															page="/jsp/maintaincasetypes/inc_maintainCaseTypeTemplates.jsp" />
													</f:subview>
												</m:div>
											</t:panelTab>
											<t:tabChangeListener
												type="com.acs.enterprise.common.program.contactmanagement.view.bean.MaintainCaseTypeTabListner" />
											<%-- End - Modified the code for the defect id ESPRD00723971_05DEC2011 --%>
										</t:panelTabbedPane>
									</m:div>
									<m:div rendered="#{CaseTypeDataBean.showEditCaseTypes}">
										<m:div onclick="javascript:flagWarn=false;"
											onmousedown="javascript:flagWarn=false;"
											id="showEditCaseTypeAuditId"
											rendered="#{CaseTypeDataBean.enableMaintainCaseTypeAuditLog}">
											<f:subview id="auditlogDetails">
												<%-- CR_ESPRD00373565_MaintainCaseTypes_16JUL2010 <jsp:include
												page="/jsp/maintaincasetypes/inc_maintainCaseTypeAuditLog.jsp" /--%>
												<audit:auditTableSet id="caseTypeVOAuditId"
													value="#{CaseTypeDataBean.caseTypeVO.auditKeyList}"
													numOfRecPerPage="10">
												</audit:auditTableSet>

											</f:subview>
										</m:div>
									</m:div>
								</m:div>
							</m:div>
						</m:div>
					</m:div>
				</m:section>
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
