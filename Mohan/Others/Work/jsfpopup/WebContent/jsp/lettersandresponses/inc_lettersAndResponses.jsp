<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@ taglib uri="http://www.acs-inc.com/auditLog" prefix="audit"%>
<portlet:defineObjects />
<%--<LINK rel="stylesheet" type="text/css"
	href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/stylesheet.css") %>'
	title="Style">--%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>

<%-- 
  - Author(s): Wipro
  - Date: Mon Jul 07 14:53:56 IST 2008
  - Copyright Notice: Copyright (c) 2006 Affiliated Computer Services, Inc.
  - @ 2006
  - Description: 
--%>
<f:loadBundle
	basename="com.acs.mmis.common.enterprise.lettergeneration.nl.GLB_IN_LetterAndResponses"
	var="mesg" />

<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.EnterpriseMessages"
	var="ent" />

<f:verbatim>
	<SCRIPT type="text/javascript">
		
		var color;
		var flg = false;
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
		
	/** This method confirms the Delete Operation */    
      
       function confirmDelete(formID, id) 
       { 
           var box = confirm("Are you sure that you want to Delete?");
           
           if(box == true){
               document.getElementById('view<portlet:namespace/>:'+formID+':'+id).click();                         
           }          
       } 
       
       
       
function renderAudit(id) 
{ 
	var hiddenValue = document.getElementById('view<portlet:namespace/>:CategoryForm:auditlogDetails:'+id);
    
	if ((hiddenValue == null) ||(hiddenValue == '')|| (hiddenValue.length == 0)) {
		hideMe('audit_plus');
	} else if ((hiddenValue.value == 'false')) 
	{
		hideMe('audit_plus');
	} 
		
}
//This will make sure that onmousedown attribute used will not call the same page/URL 
//2times (1 in new child browser(onclick event) and another in the same window (onmousedown event))
//This will set the page not to load with new URL & retain the focus
function setURLToViewLetter (event, docURL)
{
	if (event.button == 2 || event.which == 3)
	{
		var hrefComponentId = document.getElementById ('editViewLtrButton');
		if (hrefComponentId != null && hrefComponentId != undefined) {
			hrefComponentId.href = docURL;
		}
	}
}
function openNewWindow(docURL)
{
	var hrefComponentId = document.getElementById ('editViewLtrButton');
	if (hrefComponentId != null && hrefComponentId != undefined) {
		hrefComponentId.href = "#editLtrRespPanelGroup";
		hrefComponentId.focus;
	}
	window.open (docURL, '_blank', 'width=400, height=450, resizable=yes');
}//Code ended for Defect ESPRD00796178

function dataloss()
	 { 
	 	
		//var r = confirm('Are you sure you want to navigate away from this page?\n   If data has been entered and not saved, it will be lost. Select OK to navigate away from the page or Cancel to return to the page to save the data.');
		var r = confirm('If data has been entered and not saved, it will be lost. \nSelect OK to navigate away from the page \nor Cancel to return to the page to save the data.');
		if(r==true)
			{
				flg= true;
				return true;
			}
		else
			{
				return false;			
			}
		flagWarn=false;
		flagRole=false;
	}

</SCRIPT>

</f:verbatim>



<h:inputHidden id="loadUserPermissionId"
	value="#{LettersAndResponsesControllerBean.loadConstructorData}"></h:inputHidden>
<%-- Commented following code as part of Heap Dump issue # 60, 62 & 89 defects 659392, 659394 & 667442.
Rather fetching letter details always page reloads, now it will hit DB only when required.
Like Save, View Letter Request, Create Letter, Audit Log etc.
<h:inputHidden id="initialize"	value="#{LettersAndResponsesControllerBean.initialized}"	rendered="#{!LettersAndResponsesDataBean.sortLetterAndRespFlag}" />
--%>


<t:saveState value="#{LettersAndResponsesDataBean}" id="lrDataBean" />

<%--<h:outputText id="PRGCMGTOT587"
	value="#{LettersAndResponsesControllerBean.pageAccessPermission}"></h:outputText>
--%><m:div id="incLtrRespMainDiv">
	<f:subview id="logCrspdlettersNrespSubview">
		<m:section styleClass="otherbg" id="incLtrRespSection">
			<m:legend id="incLtrRespTitleLegend">
				
				<%--Code modified fo fixing DEFECT - ESPRD00722284  --%>
				
				<h:outputLink
					onclick="setHiddenValue('logCorrespondence:logCrspdlettNrespSubview:logCrspdlettersNrespSubview:letNrespHidden','minus');toggle('log_div_lettNresp',2);
							plusMinusToggle('log_div_lettNresp',this,'logCorrespondence:logCrspdlettNrespSubview:logCrspdlettersNrespSubview:letNrespHidden');
							 return false;" onmousedown="javascript:flagWarn=false;"
					id="plusMinusLettNresp" styleClass="plus">

				<%--<h:outputLink
					onclick="setHiddenValue(this,'minus');toggleTest('log_div_lettNresp',2); 						plusMinusForRefreshTest('log_div_lettNresp',this,'letNrespHidden');return false;"
					id="plusMinus_lettNrespFalse" styleClass="plus"> --%>

					<h:outputText id="incLtrRespTitle" value="Letters & Responses" />
					<h:inputHidden id="letNrespHidden"
						value="#{CorrespondenceDataBean.letNrespHidden}"></h:inputHidden>
				
				</h:outputLink>

			</m:legend>
			<m:div sid="log_div_lettNresp">
				<m:div styleClass="floatContainer" id="floatContainerDiv">

					<%--h:messages layout="table" showDetail="true" showSummary="false"
						style="color: red" /--%>
					<m:br clear="all" id="firstBr" />
					<m:br id="SecondBr" />
					<m:div id="displyCreateLetterBtRenderDiv" align="right"
						rendered="#{!(CorrespondenceDataBean.correspondenceRecordVO.correspondenceRecordNumber != null 
							&& CorrespondenceDataBean.correspondenceRecordVO.correspondenceRecordNumber != '') || (LettersAndResponsesDataBean.updateLtrAndRespFlag) 
							|| (LettersAndResponsesDataBean.accessForLGRFlag) || (CorrespondenceDataBean.correspondenceRecordVO.correspondenceDetailsVO.statusCode =='C')}">
						<h:commandButton id="createLtrBtn"
							value="#{mesg['label.ltres.createltr']}"
							style="HEIGHT:24;WIDTH:95px;border-width:4px;border-style:solid;color:white;background-color:#518ACD;border-color:#518ACD;font-size:11px;font-weight:bold;border-height:100px;align:center;vertical-align:text-top;"
							disabled="true">
						</h:commandButton>
					</m:div>

					<m:div id="displyCreateLinkRenderDiv" align="right"
						rendered="#{(CorrespondenceDataBean.correspondenceRecordVO.correspondenceRecordNumber != null 
	                                   && CorrespondenceDataBean.correspondenceRecordVO.correspondenceRecordNumber != '') && (!LettersAndResponsesDataBean.updateLtrAndRespFlag) 
	                                   && (!LettersAndResponsesDataBean.accessForLGRFlag) && (CorrespondenceDataBean.correspondenceRecordVO.correspondenceDetailsVO.statusCode !='C')}">
	                     
	                    <t:commandLink id="invokeCreateletter"	
							style="border-width:4px;border-style:solid;color:white;background-color:#518ACD;border-color:#518ACD;font-size:11px;font-weight:bold;border-height:100px;"
							action="#{LettersAndResponsesControllerBean.invokeCreateLetter}">
							<h:outputText id="createletterinvoke"
							value="#{mesg['label.ltres.createltr']}"></h:outputText>
			  			</t:commandLink>              

						<t:commandLink
							action="#{LettersAndResponsesControllerBean.createLetter}"
							style="border-width:4px;border-style:solid;color:white;background-color:#518ACD;border-color:#518ACD;font-size:11px;font-weight:bold;border-height:100px;" styleClass="hide"	
							id="createLetterBtn"> 
							<h:outputText id="displyCreateBtTextRender"
								value="#{mesg['label.ltres.createltr']}"></h:outputText>
							<f:param id="createLetterParam1"
								name="send.CommonLetterInputData.Action"
								value="SendCommonLetterInputAction"></f:param>
							<f:param id="createLetterParam2" name="funcSK"
								value="#{LettersAndResponsesDataBean.funcSK}"></f:param>
							<f:param id="createLetterParam3" name="funcArea"
								value="#{LettersAndResponsesDataBean.funcArea}"></f:param>
							<f:param id="createLetterParam4" name="LETTER_CATEGORY"
								value="#{LettersAndResponsesDataBean.letterCategory}"></f:param>
						</t:commandLink>
					</m:div>

					<m:br id="ThirdBr" />
					<!-- Rendering the Template List -->

					<m:div>
						<h:graphicImage id="PROVIDERGI20120731164811280" styleClass="blankImage" width="1" height="5"
							alt="" url="/images/x.gif" />
						<m:anchor name="searchResultFocus"></m:anchor>
					</m:div>

					<t:dataTable id="letterRequest" styleClass="dataTable" width="100%" 
						rowOnMouseOver="this.origClassName = this.className;this.className='row_mouse row_link';"
						rowOnMouseOut="if (this.origClassName) this.className = this.origClassName;"
						rowOnClick="flagWarn=false;firstChild.lastChild.click();" rows="10"
						var="letterRequestDetails" columnClasses="columnClass"
						headerClass="headerClass" footerClass="footerClass"
						rowClasses="row_alt,row" rowIndexVar="rowIndex" onmousedown="javascript:flagWarn=false;"
						value="#{LettersAndResponsesDataBean.letterGenerationRequests}">

						<t:column id="colCreatedDt" width="8%">
							<f:facet name="header">
								<t:panelGrid columns="2" id="creatDtPanelGrid">
									<h:outputText id="createdDtLabel" style="color:white"
										value="#{mesg['label.ltres.createddt']}" />
									<t:panelGroup id="creatDtPanelGroup">
										<t:div id="createdDtAsc" style="width x;align=left">
											<t:commandLink id="createdDtAscCmdLink"
												style="text-decoration: none;"
												actionListener="#{LettersAndResponsesControllerBean.getAllSortedLetterRequests}"
												onmousedown="javascript:flagWarn=false;"
												rendered="#{LettersAndResponsesDataBean.imageRender != 'createdDtAscCmdLink'}"
												immediate="true">
												<m:div id="creatDtSortAsc" title="for ascending"
													styleClass="sort_asc" />
												<f:attribute name="#{mesg['id.ltres.columnName']}"
													value="Created Date" />
												
												<f:attribute name="#{mesg['id.ltres.order']}"
													value="ascending" />
											</t:commandLink>
										</t:div>

										<t:div id="createdDtDsc" style="width x;align=left">
											<t:commandLink id="createdDtDscCmdLink"
												style="text-decoration: none;"
												actionListener="#{LettersAndResponsesControllerBean.getAllSortedLetterRequests}"
												onmousedown="javascript:flagWarn=false;"
												rendered="#{LettersAndResponsesDataBean.imageRender != 'createdDtDscCmdLink'}"
												immediate="true">
												<m:div id="creatDtSortDsc" title="for descending"
													styleClass="sort_desc" />
												<f:attribute name="#{mesg['id.ltres.columnName']}"
													value="Created Date" />
												<f:attribute name="#{mesg['id.ltres.order']}"
													value="descending" />
											</t:commandLink>
										</t:div>
									</t:panelGroup>
								</t:panelGrid>
							</f:facet>

							<t:commandLink id="edit"
								action="#{LettersAndResponsesControllerBean.openLetterRequestInEditMode}"
								onmousedown="javascript:flagWarn=false;">
								<h:outputText id="valCreatedDt"
									value="#{letterRequestDetails.createdDt}" />
								<f:param name="rowNum" value="#{ rowIndex }"></f:param>
							</t:commandLink>

						</t:column>

						<t:column id="colTemplate" width="12%">
							<f:facet name="header">
								<t:panelGrid columns="2" id="templatePanelGrid">
									<h:outputText id="TemplateLabel" style="color:white"
										value="#{mesg['label.ltres.template']}" />
									<t:panelGroup id="templatePanelGroup">
										<t:div id="templateAsc" style="width x;align=left">
											<t:commandLink id="templateAscCmdLink"
												style="text-decoration: none;"
												actionListener="#{LettersAndResponsesControllerBean.getAllSortedLetterRequests}"
												onmousedown="javascript:flagWarn=false;"
												rendered="#{LettersAndResponsesDataBean.imageRender != 'templateAscCmdLink'}"
												immediate="true">
												<m:div id="templateSortAsc" title="for ascending"
													styleClass="sort_asc" />
												<f:attribute name="#{mesg['id.ltres.columnName']}"
													value="Template" />
												
												<f:attribute name="#{mesg['id.ltres.order']}"
													value="ascending" />
											</t:commandLink>
										</t:div>

										<t:div id="templateDsc" style="width x;align=left">
											<t:commandLink id="templateDescCmdLink"
												style="text-decoration: none;"
												actionListener="#{LettersAndResponsesControllerBean.getAllSortedLetterRequests}"
												onmousedown="javascript:flagWarn=false;"
												rendered="#{LettersAndResponsesDataBean.imageRender != 'templateDescCmdLink'}"
												immediate="true">
												<m:div id="templateSortDsc" title="for descending"
													styleClass="sort_desc" />
												<f:attribute name="#{mesg['id.ltres.columnName']}"
													value="Template" />
												<f:attribute name="#{mesg['id.ltres.order']}"
													value="descending" />
											</t:commandLink>
										</t:div>
									</t:panelGroup>
								</t:panelGrid>
							</f:facet>

							<h:outputText id="valTemplateName"
								value="#{letterRequestDetails.templateName}" />

						</t:column>


						<t:column id="colDateSent" width="8%">
							<f:facet name="header">
								<t:panelGrid columns="2" id="dateSentPanelGrid">
									<h:outputText id="dateSentLabel" style="color:white"
										value="#{mesg['label.ltres.sentdt']}" />
									<t:panelGroup id="dateSentPanelGroup">
										<t:div style="width x;align=left" id="dateSentAsc">
											<t:commandLink id="dateSentAscCmdLink"
												style="text-decoration: none;"
												actionListener="#{LettersAndResponsesControllerBean.getAllSortedLetterRequests}"
												onmousedown="javascript:flagWarn=false;"
												rendered="#{LettersAndResponsesDataBean.imageRender != 'dateSentAscCmdLink'}"
												immediate="true">
												<m:div id="dateSentSortAsc" title="for ascending"
													styleClass="sort_asc" />
												<f:attribute name="#{mesg['id.ltres.columnName']}"
													value="Date Sent" />
												
												<f:attribute name="#{mesg['id.ltres.order']}"
													value="ascending" />
											</t:commandLink>
										</t:div>

										<t:div style="width x;align=left" id="dateSentDsc">
											<t:commandLink id="dateSentDescCmdLink"
												style="text-decoration: none;"
												actionListener="#{LettersAndResponsesControllerBean.getAllSortedLetterRequests}"
												onmousedown="javascript:flagWarn=false;"
												rendered="#{LettersAndResponsesDataBean.imageRender != 'dateSentDescCmdLink'}"
												immediate="true">
												<m:div id="dateSentSortDsc" title="for descending"
													styleClass="sort_desc" />
												<f:attribute name="#{mesg['id.ltres.columnName']}"
													value="Date Sent" />
												<f:attribute name="#{mesg['id.ltres.order']}"
													value="descending" />
											</t:commandLink>
										</t:div>
									</t:panelGroup>
								</t:panelGrid>
							</f:facet>

							<h:outputText id="valDateSent"
								value="#{letterRequestDetails.strSentDate}" />

						</t:column>

						<t:column id="colDueDate" width="8%">
							<f:facet name="header">
								<t:panelGrid columns="2" id="dueDatePanelGrid">
									<h:outputText id="dueDateLabel" style="color:white"
										value="#{mesg['label.ltres.duedt']}" />
									<t:panelGroup id="dueDatePanelGroup">
										<t:div style="width x;align=left" id="dueDateAsc">
											<t:commandLink id="dueDateAscCmdLink"
												style="text-decoration: none;"
												actionListener="#{LettersAndResponsesControllerBean.getAllSortedLetterRequests}"
												onmousedown="javascript:flagWarn=false;"
												rendered="#{LettersAndResponsesDataBean.imageRender != 'dueDateAscCmdLink'}"
												immediate="true">
												<m:div id="dueDateSortAsc" title="for ascending"
													styleClass="sort_asc" />
												<f:attribute name="#{mesg['id.ltres.columnName']}"
													value="Due Date" />
												
												<f:attribute name="#{mesg['id.ltres.order']}"
													value="ascending" />
											</t:commandLink>
										</t:div>

										<t:div style="width x;align=left" id="dueDateDsc">
											<t:commandLink id="dueDateDescCmdLink"
												style="text-decoration: none;"
												actionListener="#{LettersAndResponsesControllerBean.getAllSortedLetterRequests}"
												onmousedown="javascript:flagWarn=false;"
												rendered="#{LettersAndResponsesDataBean.imageRender != 'dueDateDescCmdLink'}"
												immediate="true">
												<m:div id="dueDateSortDsc" title="for descending"
													styleClass="sort_desc" />
												<f:attribute name="#{mesg['id.ltres.columnName']}"
													value="Due Date" />
												<f:attribute name="#{mesg['id.ltres.order']}"
													value="descending" />
											</t:commandLink>
										</t:div>
									</t:panelGroup>
								</t:panelGrid>
							</f:facet>

							<h:outputText id="valDueDate"
								value="#{letterRequestDetails.strDueDate}" />

						</t:column>

						<t:column id="colDateRcvd" width="8%">
							<f:facet name="header">
								<t:panelGrid columns="2" id="dateRecPanelGrid">
									<h:outputText id="dateRcvdLabel" style="color:white"
										value="#{mesg['label.ltres.rcvddt']}" />
									<t:panelGroup id="dateRecPanelGroup">
										<t:div style="width x;align=left" id="dateRecAsc">
											<t:commandLink id="dateRcvdAscCmdLink"
												style="text-decoration: none;"
												actionListener="#{LettersAndResponsesControllerBean.getAllSortedLetterRequests}"
												onmousedown="javascript:flagWarn=false;"
												rendered="#{LettersAndResponsesDataBean.imageRender != 'dateRcvdAscCmdLink'}"
												immediate="true">
												<m:div id="dateRecSortAsc" title="for ascending"
													styleClass="sort_asc" />
												<f:attribute name="#{mesg['id.ltres.columnName']}"
													value="Date Received" />
												
												<f:attribute name="#{mesg['id.ltres.order']}"
													value="ascending" />
											</t:commandLink>
										</t:div>

										<t:div style="width x;align=left" id="dateRecDes">
											<t:commandLink id="dateRcvdDescCmdLink"
												style="text-decoration: none;"
												actionListener="#{LettersAndResponsesControllerBean.getAllSortedLetterRequests}"
												onmousedown="javascript:flagWarn=false;"
												rendered="#{LettersAndResponsesDataBean.imageRender != 'dateRcvdDescCmdLink'}"
												immediate="true">
												<m:div id="dateRecSortDsc" title="for descending"
													styleClass="sort_desc" />
												<f:attribute name="#{mesg['id.ltres.columnName']}"
													value="Date Received" />
												<f:attribute name="#{mesg['id.ltres.order']}"
													value="descending" />
											</t:commandLink>
										</t:div>
									</t:panelGroup>
								</t:panelGrid>
							</f:facet>

							<h:outputText id="valDateRcvd"
								value="#{letterRequestDetails.strRcvdDate}" />
						</t:column>

						<t:column id="colNotifyViaAlert" width="14%">
							<f:facet name="header">
								<t:panelGrid columns="2" id="notifyViaAlertPanelGrid">
									<h:outputText id="notifyViaAlertLabel" style="color:white"
										value="#{mesg['label.ltres.notifyalert']}" />
									<t:panelGroup id="notifyViaAlertPanelGroup">
										<t:div style="width x;align=left" id="notifyViaAlertAsc">
											<t:commandLink id="notifyAscCmdLink"
												style="text-decoration: none;"
												actionListener="#{LettersAndResponsesControllerBean.getAllSortedLetterRequests}"
												onmousedown="javascript:flagWarn=false;"
												rendered="#{LettersAndResponsesDataBean.imageRender != 'notifyAscCmdLink'}"
												immediate="true">
												<m:div id="notifyViaAlertSortAsc" title="for ascending"
													styleClass="sort_asc" />
												<f:attribute name="#{mesg['id.ltres.columnName']}"
													value="Notify Via Alert" />
												
												<f:attribute name="#{mesg['id.ltres.order']}"
													value="ascending" />
											</t:commandLink>
										</t:div>

										<t:div style="width x;align=left" id="notifyViaAlertDsc">
											<t:commandLink id="notifyDescCmdLink"
												style="text-decoration: none;"
												actionListener="#{LettersAndResponsesControllerBean.getAllSortedLetterRequests}"
												onmousedown="javascript:flagWarn=false;"
												rendered="#{LettersAndResponsesDataBean.imageRender != 'notifyDescCmdLink'}"
												immediate="true">
												<m:div id="notifyViaAlertSrtDsc" title="for descending"
													styleClass="sort_desc" />
												<f:attribute name="#{mesg['id.ltres.columnName']}"
													value="Notify Via Alert" />
												<f:attribute name="#{mesg['id.ltres.order']}"
													value="descending" />
											</t:commandLink>
										</t:div>
									</t:panelGroup>
								</t:panelGrid>
							</f:facet>

							<h:outputText id="valNotifyViaAlert"
								value="#{letterRequestDetails.displayNotifyAlrtUserId}" />
						</t:column>

						<t:column id="colAlertBasedOn" width="8%">
							<f:facet name="header">
								<t:panelGrid columns="2" id="alertBasedOnPanelGrid">
									<h:outputText id="alertBasedOnLabel" style="color:white"
										value="#{mesg['label.ltres.alertbasedon']}" />
									<t:panelGroup id="alertBasedOnPanelGroup">
										<t:div style="width x;align=left" id="alertBasedOnAsc">
											<t:commandLink id="alertBasedOnAscCmdLink"
												style="text-decoration: none;"
												actionListener="#{LettersAndResponsesControllerBean.getAllSortedLetterRequests}"
												onmousedown="javascript:flagWarn=false;"
												rendered="#{LettersAndResponsesDataBean.imageRender != 'alertBasedOnAscCmdLink'}"
												immediate="true">
												<m:div id="alertBasedOnSortAsc" title="for ascending"
													styleClass="sort_asc" />
												<f:attribute name="#{mesg['id.ltres.columnName']}"
													value="Alert Based On" />
												
												<f:attribute name="#{mesg['id.ltres.order']}"
													value="ascending" />
											</t:commandLink>
										</t:div>

										<t:div style="width x;align=left" id="alertBasedOnDsc">
											<t:commandLink id="alertBasedOnDescCmdLink"
												style="text-decoration: none;"
												actionListener="#{LettersAndResponsesControllerBean.getAllSortedLetterRequests}"
												onmousedown="javascript:flagWarn=false;"
												rendered="#{LettersAndResponsesDataBean.imageRender != 'alertBasedOnDescCmdLink'}"
												immediate="true">
												<m:div id="alertBasedOnSortDsc" title="for descending"
													styleClass="sort_desc" />
												<f:attribute name="#{mesg['id.ltres.columnName']}"
													value="Alert Based On" />
												<f:attribute name="#{mesg['id.ltres.order']}"
													value="descending" />
											</t:commandLink>
										</t:div>
									</t:panelGroup>
								</t:panelGrid>
							</f:facet>

							<h:outputText id="valAlertBasedOn"
								value="#{letterRequestDetails.alertBasedOnLabel}" />
						</t:column>
						<%--infinite changes begins --%>
						<t:column id="recipientName" width="12%">
							<f:facet name="header">
								<t:panelGrid columns="2" id="recipientNamePanelGrid">
									<h:outputText id="ltrrecname" style="color:white"
										value="#{mesg['label.ltres.recipientName']}" />
									<t:panelGroup id="recipientNamePanelGroup">
										<t:div style="width x;align=left" id="recipientNameAsc">
											<t:commandLink id="recNameAscCmdLink"
												style="text-decoration: none;"
												actionListener="#{LettersAndResponsesControllerBean.getAllSortedLetterRequests}"
												onmousedown="javascript:flagWarn=false;"
												rendered="#{LettersAndResponsesDataBean.imageRender != 'recNameAscCmdLink'}"
												immediate="true">
												<m:div id="recipientNameSortAsc" title="for ascending"
													styleClass="sort_asc" />
												<f:attribute name="#{mesg['id.ltres.columnName']}"
													value="Recipient Name" />
												<f:attribute name="#{mesg['id.ltres.order']}"
													value="ascending" />
											</t:commandLink>
										</t:div>

										<t:div style="width x;align=left" id="recipientNameDsc">
											<t:commandLink id="recNameDescCmdLink"
												style="text-decoration: none;"
												actionListener="#{LettersAndResponsesControllerBean.getAllSortedLetterRequests}"
												onmousedown="javascript:flagWarn=false;"
												rendered="#{LettersAndResponsesDataBean.imageRender != 'recNameDescCmdLink'}"
												immediate="true">
												<m:div id="recipientNameSortDsc" title="for descending"
													styleClass="sort_desc" />
												<f:attribute name="#{mesg['id.ltres.columnName']}"
													value="Recipient Name" />
												<f:attribute name="#{mesg['id.ltres.order']}"
													value="descending" />
											</t:commandLink>
										</t:div>
									</t:panelGroup>
								</t:panelGrid>
							</f:facet>

							<h:outputText id="ltrrec"
								value="#{letterRequestDetails.receiverName}" />

						</t:column>


						<t:column id="ltrreqId" width="10%">
							<f:facet name="header">
								<t:panelGrid columns="2" id="ltrreqIdPanelGrid">
									<h:outputText id="ltrreqid" style="color:white"
										value="#{mesg['label.ltres.ltrreqId']}" />
									<t:panelGroup id="ltrreqIdPanelGroup">
										<t:div style="width x;align=left" id="ltrreqIdAsc">
											<t:commandLink id="ltrreqidAscCmdLink"
												style="text-decoration: none;"
												actionListener="#{LettersAndResponsesControllerBean.getAllSortedLetterRequests}"
												onmousedown="javascript:flagWarn=false;"
												rendered="#{LettersAndResponsesDataBean.imageRender != 'ltrreqidAscCmdLink'}"
												immediate="true">
												<m:div id="ltrreqIdSortAsc" title="for ascending"
													styleClass="sort_asc" />
												<f:attribute name="#{mesg['id.ltres.columnName']}"
													value="Letter Request ID" />
												<f:attribute name="#{mesg['id.ltres.order']}"
													value="ascending" />
											</t:commandLink>
										</t:div>

										<t:div style="width x;align=left" id="ltrreqIdDsc">
											<t:commandLink id="ltrreqidDescCmdLink"
												style="text-decoration: none;"
												actionListener="#{LettersAndResponsesControllerBean.getAllSortedLetterRequests}"
												onmousedown="javascript:flagWarn=false;"
												rendered="#{LettersAndResponsesDataBean.imageRender != 'ltrreqidDescCmdLink'}"
												immediate="true">
												<m:div id="ltrreqIdSortDsc" title="for descending"
													styleClass="sort_desc" />
												<f:attribute name="#{mesg['id.ltres.columnName']}"
													value="Letter Request ID" />
												<f:attribute name="#{mesg['id.ltres.order']}"
													value="descending" />
											</t:commandLink>
										</t:div>
									</t:panelGroup>
								</t:panelGrid>
							</f:facet>

							<h:outputText id="ltrreq"
								value="#{letterRequestDetails.letterReqSK}" />
						</t:column>
						<%--infinite changes ends --%>
						<t:column id="colStatus" width="6%">
							<f:facet name="header">
								<t:panelGrid columns="2" id="statusPanelGrid">
									<h:outputText id="statusLabel" style="color:white"
										value="#{mesg['label.ltres.status']}" />
									<t:panelGroup id="statusPanelGroup">
										<t:div style="width x;align=left" id="statusAsc">
											<t:commandLink id="statusAscCmdLink"
												style="text-decoration: none;"
												actionListener="#{LettersAndResponsesControllerBean.getAllSortedLetterRequests}"
												onmousedown="javascript:flagWarn=false;"
												rendered="#{LettersAndResponsesDataBean.imageRender != 'statusAscCmdLink'}"
												immediate="true">
												<m:div id="statusSortAsc" title="for ascending"
													styleClass="sort_asc" />
												<f:attribute name="#{mesg['id.ltres.columnName']}"
													value="Status" />
												
												<f:attribute name="#{mesg['id.ltres.order']}"
													value="ascending" />
											</t:commandLink>
										</t:div>

										<t:div style="width x;align=left" id="statusDsc">
											<t:commandLink id="statusDescCmdLink"
												style="text-decoration: none;"
												actionListener="#{LettersAndResponsesControllerBean.getAllSortedLetterRequests}"
												onmousedown="javascript:flagWarn=false;"
												rendered="#{LettersAndResponsesDataBean.imageRender != 'statusDescCmdLink'}"
												immediate="true">
												<m:div id="statusSortDsc" title="for descending"
													styleClass="sort_desc" />
												<f:attribute name="#{mesg['id.ltres.columnName']}"
													value="Status" />
												<f:attribute name="#{mesg['id.ltres.order']}"
													value="descending" />
											</t:commandLink>
										</t:div>
									</t:panelGroup>
								</t:panelGrid>
							</f:facet>

							<h:outputText id="valStatus"
								value="#{letterRequestDetails.statusDesc}" />
						</t:column>

						<t:column id="colSupvAppReq" width="6%">
							<f:facet name="header">
								<t:panelGrid columns="2" id="SupvAppReqPanelGrid">
									<h:outputText id="supvAppReqLabel" style="color:white"
										value="#{mesg['label.ltres.supappreq']}" />
									<t:panelGroup id="SupvAppReqPanelGroup">
										<t:div style="width x;align=left" id="SupvAppReqAsc">
											<t:commandLink id="supvAppReqAscCmdLink"
												style="text-decoration: none;"
												actionListener="#{LettersAndResponsesControllerBean.getAllSortedLetterRequests}"
												onmousedown="javascript:flagWarn=false;"
												rendered="#{LettersAndResponsesDataBean.imageRender != 'supvAppReqAscCmdLink'}"
												immediate="true">
												<m:div id="SupvAppReqSortAsc" title="for ascending"
													styleClass="sort_asc" />
												<f:attribute name="#{mesg['id.ltres.columnName']}"
													value="Supv Appr. Req" />
											
												<f:attribute name="#{mesg['id.ltres.order']}"
													value="ascending" />
											</t:commandLink>
										</t:div>

										<t:div style="width x;align=left" id="SupvAppReqDsc">
											<t:commandLink id="supvAppReqDescCmdLink"
												style="text-decoration: none;"
												actionListener="#{LettersAndResponsesControllerBean.getAllSortedLetterRequests}"
												onmousedown="javascript:flagWarn=false;"
												rendered="#{LettersAndResponsesDataBean.imageRender != 'supvAppReqDescCmdLink'}"
												immediate="true">
												<m:div id="SupvAppReqSortDsc" title="for descending"
													styleClass="sort_desc" />
												<f:attribute name="#{mesg['id.ltres.columnName']}"
													value="Supv Appr. Req" />
												<f:attribute name="#{mesg['id.ltres.order']}"
													value="descending" />
											</t:commandLink>
										</t:div>
									</t:panelGroup>
								</t:panelGrid>
							</f:facet>

							<h:outputText id="valSupvAppReqYes" value="Yes"
								rendered="#{letterRequestDetails.supApprovalReq}" />
							<h:outputText id="valSupvAppReqNo" value="No"
								rendered="#{!letterRequestDetails.supApprovalReq}" />

							<%--h:graphicImage id="supApprovalReqTrueImage" styleClass="ind_check_yes" width="15" 
							rendered="#{letterRequestDetails.supApprovalReq}" url="/images/x.gif"/--%>
						</t:column>

					</t:dataTable>
	                 <%--Data Base Pagination Starts--%>
					<t:dataScroller id="LtrResDataScr" pageCountVar="pageCount"
						pageIndexVar="pageIndex" paginator="true"
						paginatorActiveColumnStyle='font-weight:bold;'
						paginatorMaxPages="3" immediate="false" for="letterRequest"
						firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
						rowsCountVar="rowsCount"
						onclick="javascript:flagWarn=false;focusThis('LtrResDataScr');"
						style="float:right;position:relative;bottom:-6px">
						<f:facet name="previous">
							<h:outputText style="text-decoration:underline;" id="ltSymbol"
								value=" #{ref['label.ref.lt']} " rendered="#{pageIndex > 1}"></h:outputText>
						</f:facet>
						<f:facet name="next">
							<h:outputText style="text-decoration:underline;" id="gtSymbol"
								value=" #{ref['label.ref.gt']} "
								rendered="#{pageIndex < pageCount}"></h:outputText>
						</f:facet>
						<h:outputText id="ltrRespRowsCount"
							value="showing #{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
							style="float:left;position:relative;bottom:-6px;font-weight:bold;"
							rendered="#{rowsCount>0}">
						</h:outputText>
					</t:dataScroller>
					<%--Data Base Pagination Ends--%>

					<m:br id="fourBr" />
					<m:br id="fiveBr" />

					<%-- Rendering the Template Details --%>

					<m:div id="ltrRespEditSection" styleClass="moreInfoBar"
						rendered="#{LettersAndResponsesDataBean.renderEditSection}">
						<m:div id="ltrRespEditTitle" styleClass="infoTitle">
							<h:outputText id="ltrRespEditTitleVal"
								value="#{mesg['label.ltres.editltrandres']}" />
						</m:div>

						<m:div id="ltrRespEditDetails" styleClass="infoActions">
							<t:panelGroup id="editLtrRespPanelGroup">
								<m:div id="ltrRespControlReq"
									rendered="#{LettersAndResponsesControllerBean.controlRequired}">
									<%--h:commandLink styleClass="strong" id="editViewLtrLink"
									onmousedown="javascript:newWindow('#{LettersAndResponsesDataBean.letterGenerationInputVO.edmsPageURL}')"
									rendered="#{LettersAndResponsesDataBean.letterGenerationInputVO.edmsPageId != null 
										&& LettersAndResponsesDataBean.letterGenerationInputVO.statusDesc == 'Generated'}">
									<h:outputText id="editViewLtr" value="#{mesg['link.ltres.viewLetter']}" />
								</h:commandLink--%>
									<%--below command button added for CR 1825--%>
									<%--Modified <h:commandButton> to <a> and <h:Outputlink> as a part of Defect ESPRD00796178 --%>
									<h:outputLink id="PROVIDEROLK20120731164811281"
										rendered="#{LettersAndResponsesDataBean.letterGenerationInputVO.edmsPageId != null && LettersAndResponsesDataBean.letterGenerationInputVO.statusDesc == 'Generated'}">
										<a href="#" id="editViewLtrButton" style="COLOR: #00f;"
											onmousedown="setURLToViewLetter(event, '#{LettersAndResponsesDataBean.letterGenerationInputVO.edmsPageURL}');"
											onclick="javascript:flagWarn=false;openNewWindow('#{LettersAndResponsesDataBean.letterGenerationInputVO.edmsPageURL}')" >
											<h:outputText id="editViewLtrLinkRen11" value="#{mesg['link.ltres.viewLetter']}"></h:outputText>
										</a>
									</h:outputLink>
									<h:outputText id="editViewLtrLinkRen"
										value="#{mesg['link.ltres.viewLetter']}"
										rendered="#{LettersAndResponsesDataBean.letterGenerationInputVO.edmsPageId == null 										|| LettersAndResponsesDataBean.letterGenerationInputVO.statusDesc != 'Generated'}" />

									<h:outputText escape="false" id="editViewLtrLinkSep"
										value="#{mesg['label.ltres.linkSeperator']}" />
									<%--Added <h:requestLink> as a part of Defect ESPRD00796178 --%>
									<t:commandLink id="invokeViewLetterRequestID" styleClass="strong"
										action="#{LettersAndResponsesControllerBean.invokeCRViewLetterReq}"
										rendered="#{!LettersAndResponsesDataBean.updateLtrAndRespFlag && !LettersAndResponsesDataBean.accessForLGRFlag}">
										<h:outputText id="invokeViewLetterRequestOutput" value="#{mesg['link.ltres.viewLetterRequest']}"></h:outputText>
									</t:commandLink>
									<t:commandLink styleClass="hide" id="editViewLtrLinksec123"
										action="#{LettersAndResponsesControllerBean.viewLetterRequest}"
										rendered="#{!LettersAndResponsesDataBean.updateLtrAndRespFlag && !LettersAndResponsesDataBean.accessForLGRFlag}">
										<h:outputText id="editViewLtrsec"
											value="#{mesg['link.ltres.viewLetterRequest']}"></h:outputText>
										<f:param name="Send.MyAlerts.AlertSk" value="SendMyAlertSk"></f:param>
									</t:commandLink>
									<%--below command button added for CR 1825--%>
									<h:commandButton id="editViewLtrReqButton"
										action="#{LettersAndResponsesControllerBean.viewLetterRequest}"
										value="#{mesg['link.ltres.viewLetterRequest']}"
										disabled="#{LettersAndResponsesDataBean.updateLtrAndRespFlag || LettersAndResponsesDataBean.accessForLGRFlag}"
										rendered="#{LettersAndResponsesDataBean.updateLtrAndRespFlag || LettersAndResponsesDataBean.accessForLGRFlag}"
										style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:14px;WIDTH:120px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal">
									</h:commandButton>
									<h:outputText escape="false" id="editViewLtrLinkSepsec"
										value="#{mesg['label.ltres.linkSeperator']}" />

									<%--h:commandLink styleClass="strong" id="editSaveLink"
										action="#{LettersAndResponsesControllerBean.saveLetterRequest}" onmousedown="javascript:flagWarn=false;">
										<h:outputText id="editSaveText" value="#{ent['label-sw-save']}" />
									</h:commandLink--%>
									<%--below command button added for CR 1825--%>
									<h:commandButton id="editSaveButton"
										action="#{LettersAndResponsesControllerBean.saveLetterRequest}"
										onclick="javascript:flagWarn=false;"
										value="#{ent['label-sw-save']}"
										disabled="#{LettersAndResponsesDataBean.updateLtrAndRespFlag}"
										style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:14px;WIDTH:30px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal">
									</h:commandButton>
									<h:outputText escape="false" id="editSaveLinkSep"
										value="#{mesg['label.ltres.linkSeperator']}" />
									<%--h:commandLink id="editResetLink" styleClass="commandLink" onmousedown="this.form.submit();"
										action="#{LettersAndResponsesControllerBean.resetLetterRequestDetails}">  								<h:outputText id="editResetLinkText" value="#{ent['label-sw-reset']}" />
									</h:commandLink--%>
									<%--below command button added for CR 1825--%>
									<h:commandButton id="editResetButton"
										disabled="#{LettersAndResponsesDataBean.updateLtrAndRespFlag}"
										action="#{LettersAndResponsesControllerBean.resetLetterRequestDetails}"
										value="#{ent['label-sw-reset']}"
										style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:14px;WIDTH:35px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal">
									</h:commandButton>
									<h:outputText escape="false" id="editResetLinkSep"
										value="#{mesg['label.ltres.linkSeperator']}" />
									<t:commandLink styleClass="strong" id="editCancelLink" 
										action="#{LettersAndResponsesControllerBean.cancelLetterRequestDetails}"
										immediate="true">
										<h:outputText value="#{ent['label-sw-cancel']}"
											id="editCancelText" />
									</t:commandLink>
								</m:div>
								<%--h:commandLink styleClass="strong"
								action="#{LettersAndResponsesControllerBean.cancelLetterRequestDetails}"
									immediate="true">
									<h:outputText id="PRGCMGTOT588" value="#{ent['label-sw-cancel']}" />
								</h:commandLink--%>

							</t:panelGroup>
						</m:div>

						<m:div styleClass="floatContainer" id="editDeatilsDivOne">
							<m:div id="editDeatilsDivTwo">
								<m:div styleClass="moreInfoBar" id="editDeatilsDivThree">
									<m:div styleClass="moreInfoContent" id="editDeatilsDivFour">
										<m:div styleClass="padb" id="editDeatilsDivFive">
											<m:div styleClass="msgBox" id="editDeatilsSuccess"
												rendered="#{LettersAndResponsesDataBean.showSucessMessage}">
												<h:outputText id="editDeatilsSuccessText"
													value="#{ent['label-sw-success']}" />
											</m:div>
											<h:inputHidden id="showErrorMessage"></h:inputHidden>
											<m:br id="editDetailsBr" />
											<h:message id="errorMsg" for="showErrorMessage"
												style="color: red" />
											<m:table id="editLtrRespDetailsTable" cellspacing="8">
												<m:tr id="editLtrRespDetailsTR1">
													<m:td id="labelCreatedDt" width="30%">
														<m:div styleClass="padb" id="editCreatedDateTextDiv">
															<h:outputLabel id="PRGCMGTOLL263"
																for="editCreatedDateText">
																<h:outputText id="editCreatedDateText"
																	value="#{mesg['label.ltres.createddt']}" />
															</h:outputLabel>
															<m:br id="editCreateDtBr" />
															<h:outputText id="editCreatedDateEdit"
																value="#{LettersAndResponsesDataBean.letterGenerationInputVO.createdDt}">
															</h:outputText>
														</m:div>
													</m:td>
													<m:td id="labelTemplate" width="12%">
														<m:div styleClass="padb" id="editTemplateTextDiv">
															<h:outputLabel id="PRGCMGTOLL264" for="editTemplateText">
																<h:outputText id="editTemplateText"
																	value="#{mesg['label.ltres.template']}"></h:outputText>
															</h:outputLabel>
															<m:br id="editTmplateBr" />
															<h:outputText id="editTemplateVal"
																value="#{LettersAndResponsesDataBean.letterGenerationInputVO.templateName}"></h:outputText>
														</m:div>
													</m:td>

													<m:td id="labelDateSent" width="15%">
														<m:div styleClass="padb" id="editSentDateTextDiv">
															<h:outputLabel id="PRGCMGTOLL265" for="editSentDateText">
																<h:outputText id="editSentDateText"
																	value="#{mesg['label.ltres.sentdt']}"></h:outputText>
															</h:outputLabel>
															<m:br id="editSentDateBr" />
															<h:outputText id="editSentDateVal"
																value="#{LettersAndResponsesDataBean.letterGenerationInputVO.strSentDate}"></h:outputText>
														</m:div>
													</m:td>


													<m:td id="labelDaysDueBack" width="15%">
														<m:div styleClass="padb" id="editDueDaysTextDiv"
															rendered="#{LettersAndResponsesDataBean.duedateoffsetnumRender}">
															<h:outputLabel id="PRGCMGTOLL266" for="daysDueBack"
																	value="#{mesg['label.ltres.daysdueback']}">
															</h:outputLabel>
															<m:br id="editDueDaysBr" />
															<hx:behavior id="daysDueBackBehavior" event="onchange"
																behaviorAction="get" targetAction="dueDatePanel"
																target="daysDueBack"></hx:behavior>
															<%--<h:inputText id="daysDueBack" size="10" maxlength="2"																value="#{LettersAndResponsesDataBean.letterGenerationInputVO.dueDateOffsetNum}">
															</h:inputText>--%>
															<h:inputText id="daysDueBack" size="10" maxlength="3"
																value="#{LettersAndResponsesDataBean.letterGenerationInputVO.dueDateOffsetNum}">
															</h:inputText>
															<m:br id="editDueDaysBrOne" />
															<h:message id="daysDueBackErrMsg" for="daysDueBack"
																showDetail="true" showSummary="false" style="color: red" />
														</m:div>
														<m:div styleClass="padb" id="editDueDaysTextDivRender"
															rendered="#{!LettersAndResponsesDataBean.duedateoffsetnumRender}">
															<f:verbatim>&nbsp;</f:verbatim>
														</m:div>
													</m:td>

													<m:td id="labelDueDate" width="13%">
														<m:div styleClass="padb" id="editDueDateTextDiv"
															rendered="#{LettersAndResponsesDataBean.duedateoffsetnumRender}">
															<h:outputLabel id="PRGCMGTOLL267" for="editDueDateText">
																<h:outputText id="editDueDateText"
																	value="#{mesg['label.ltres.duedt']}" />
															</h:outputLabel>
															<m:br id="editDueDateBr" />
															<h:panelGroup id="dueDatePanel">
																<h:inputHidden id="hiddenDueDate"
																	value="#{LettersAndResponsesControllerBean.addDate}"></h:inputHidden>
																<h:outputText id="editDueDateVal"
																	value="#{LettersAndResponsesDataBean.letterGenerationInputVO.strDueDate}"></h:outputText>
															</h:panelGroup>
															<hx:ajaxRefreshRequest id="dueDateRefreshRequest"
																target="dueDatePanel" params="daysDueBack"></hx:ajaxRefreshRequest>
														</m:div>
														<m:div styleClass="padb" id="editDueDateTextDivRender"
															rendered="#{!LettersAndResponsesDataBean.duedateoffsetnumRender}">
															<f:verbatim>&nbsp;</f:verbatim>
														</m:div>
													</m:td>

													<m:td id="labelDateRecd" width="10%">
														<m:div styleClass="padb" id="editRecDateTextDiv"
															rendered="#{LettersAndResponsesDataBean.duedateoffsetnumRender}">
															<h:outputLabel id="PRGCMGTOLL268" for="toBeSentDate"
																	value="#{mesg['label.ltres.rcvddt']}" >
															</h:outputLabel>
															<m:br id="editRecDateBr" />


															<m:inputCalendar monthYearRowClass="yearMonthHeader"
																weekRowClass="weekHeader" id="toBeSentDate"
																currentDayCellClass="currentDayCell" size="10"
																renderAsPopup="true" addResources="true"
																renderPopupButtonAsImage="true"
																value="#{LettersAndResponsesDataBean.letterGenerationInputVO.strRcvdDate}"
																popupDateFormat="MM/dd/yyyy" maxlength="12"
																disabled="#{LettersAndResponsesDataBean.updateLtrAndRespFlag}">
															</m:inputCalendar>
															<m:br id="editRecDateBrOne" />
															<h:message id="dateRecdErrMsg" for="toBeSentDate"
																showDetail="true" showSummary="false" style="color: red" />
														</m:div>
														<m:div styleClass="padb" id="editRecDateTextDivRender"
															rendered="#{!LettersAndResponsesDataBean.duedateoffsetnumRender}">
															<f:verbatim>&nbsp;</f:verbatim>
														</m:div>
													</m:td>
													<m:td id="dummyCoulmn">
														<m:div styleClass="padb" id="editdummyCoulmnDiv">
															<f:verbatim>&nbsp;</f:verbatim>
														</m:div>
													</m:td>
												</m:tr>

												<m:tr id="editLtrRespDetailsTR2">

													<m:td id="labelNotify" width="30%">
														<m:div styleClass="padb" id="editnotifyalertTextDiv">
															<h:outputLabel id="PRGCMGTOLL269" for="notify"
																	value="#{mesg['label.ltres.notifyalert']}" >
															</h:outputLabel>
															<m:br id="editnotifyalertBr" />

															<h:selectOneMenu id="notify"
																value="#{LettersAndResponsesDataBean.letterGenerationInputVO.notifyAlrtUserId}">
																<f:selectItem id="nofitySelectItem1" itemLabel=""
																	itemValue="" />
																<f:selectItems id="nofitySelectItem2"
																	value="#{LettersAndResponsesDataBean.notifyViaAlert}" />
															</h:selectOneMenu>
															<m:br id="notifySelectMenuBr" />
															<h:message id="notifyErrMsg" for="notify"
																showDetail="true" showSummary="false" style="color: red" />
														</m:div>
													</m:td>

													<m:td id="labelAlertBasedOn" width="12%">
														<m:div styleClass="padb" id="editAlertBasedOnTextDiv">
															<h:outputLabel id="PRGCMGTOLL270" for="alertBasedOn"
																	value="#{mesg['label.ltres.alertbasedon']}" >
															</h:outputLabel>
															<m:br id="editAlertBasedOnBr" />

															<h:selectOneMenu id="alertBasedOn"
																value="#{LettersAndResponsesDataBean.letterGenerationInputVO.altrBsdOnColNm}">
																<f:selectItem id="alertBasedOnSelectItem1"
																	itemLabel="Please Select One" itemValue="" />
																<f:selectItems id="alertBasedOnSelectItem2"
																	value="#{LettersAndResponsesDataBean.alertBasedOn}" />
															</h:selectOneMenu>
															<m:br id="editAlertBasedOnBrOne" />
															<h:message id="alertBasedOnErrMsg" for="alertBasedOn"
																showDetail="true" showSummary="false" style="color: red" />
														</m:div>
													</m:td>
													<%-- infinite changes begins --%>
													<m:td id="receiverName" width="15%">
														<m:div styleClass="padb" id="editRecipientNameTextDiv">
															<h:outputLabel id="PRGCMGTOLL271"
																for="editRecipientNameText">
																<h:outputText id="editRecipientNameText"
																	value="#{mesg['label.ltres.recipientName']}" />
															</h:outputLabel>
															<m:br id="editRecipientNameBr" />
															<h:outputText id="editRecipientNameVal"
																value="#{LettersAndResponsesDataBean.letterGenerationInputVO.receiverName}"></h:outputText>
															<m:br id="editRecipientNameBrOne" />
														</m:div>
													</m:td>

													<m:td id="letterRequestId" width="13%">
														<m:div styleClass="padb" id="editLtrreqIdTextDiv">
															<h:outputLabel id="PRGCMGTOLL272" for="editLtrreqIdText">
																<h:outputText id="editLtrreqIdText"
																	value="#{mesg['label.ltres.ltrreqId']}" />
															</h:outputLabel>
															<m:br id="editLtrreqIdBr" />
															<h:outputText id="editLtrreqIdTextVal"
																value="#{LettersAndResponsesDataBean.letterGenerationInputVO.letterReqSK}"></h:outputText>
															<m:br id="editLtrreqIdBrOne" />
														</m:div>
													</m:td>
													<%--infinite changes ends --%>

													<m:td id="labelAlertDays" width="12%">
														<m:div styleClass="padb" id="editSendAlertDaysTextDiv">
															<h:outputLabel id="PRGCMGTOLL273" for="alertDays"
																	value="#{mesg['label.ltres.sendalertdays']}" >
															</h:outputLabel>
															<m:br id="editSendAlertDaysBr" />


															<h:selectOneMenu id="alertDays"
																value="#{LettersAndResponsesDataBean.letterGenerationInputVO.sendAlertDaysCd}">
																<f:selectItem id="alertDaysSelectItem1"
																	itemLabel="Please Select One" itemValue="" />
																<f:selectItems id="alertDaysSelectItem2"
																	value="#{LettersAndResponsesDataBean.alertNumberOfDays}" />
															</h:selectOneMenu>

															<h:selectOneRadio id="beforeAfterCd" enabledClass="black"
																value="#{LettersAndResponsesDataBean.letterGenerationInputVO.alertBfrAftrCd}">
																<f:selectItem id="beforeAfterCdSelectItem1"
																	itemLabel="#{mesg['label.ltres.before']}" itemValue="B" />
																<f:selectItem id="beforeAfterCdSelectItem2"
																	itemLabel="#{mesg['label.ltres.after']}" itemValue="A" />
															</h:selectOneRadio>
															<h:message id="beforeOrAfterErrMsg" for="beforeAfterCd"
																showDetail="true" showSummary="false" style="color: red" />

															<m:br id="editSendAlertDaysBrOne" />
															<h:message id="sendAlertDaysErrMsg" for="alertDays"
																showDetail="true" showSummary="false" style="color: red" />
														</m:div>
													</m:td>
													<m:td id="labelStatus" width="9%">
														<m:div styleClass="padb" id="editStausTextDiv">
															<h:outputText id="editStausText"
																value="#{mesg['label.ltres.status']}" />
															<%--f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim--%>
															<%--h:outputText value="#{mesg['label.ltres.supappreq']}" /--%>
															<m:br id="editStatusBr" />
															<h:outputText id="editStausTextVal"
																value="#{LettersAndResponsesDataBean.letterGenerationInputVO.statusDesc}" />
															<%--<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
															<h:outputText id="PRGCMGTOT589" value="Y"																rendered="#{LettersAndResponsesDataBean.letterGenerationInputVO.supApprovalReq == true}"></h:outputText>
															<h:outputText id="PRGCMGTOT590" value="N"																rendered="#{LettersAndResponsesDataBean.letterGenerationInputVO.supApprovalReq == false}"></h:outputText>--%>
														</m:div>
													</m:td>

													<m:td id="labelSupAppReq" width="7%">
														<m:div styleClass="padb" id="editSupAppReqTextDiv">
															<h:outputText id="editSupAppReqText"
																value="#{mesg['label.ltres.supappreq']}" />
															<m:br id="editSupAppReqBr" />
															<h:outputText value="Yes" id="editSupAppReqTextYes"
																rendered="#{LettersAndResponsesDataBean.letterGenerationInputVO.supApprovalReq == true}"></h:outputText>
															<h:outputText value="No" id="editSupAppReqTextNo"
																rendered="#{LettersAndResponsesDataBean.letterGenerationInputVO.supApprovalReq == false}"></h:outputText>
														</m:div>
													</m:td>


												</m:tr>

												<%--m:tr>
													<m:td id="labelMessageExpln" colspan="4" style="width:750px">
														<m:div styleClass="padb">
															<h:outputText id="PRGCMGTOT591" value="#{mesg['label.ltres.explanation']}" />
															<m:br />
															<h:inputText id="messageExpln" size="75" maxlength="255" 																value="#{LettersAndResponsesDataBean.letterGenerationInputVO.explnText}">
															</h:inputText>
														</m:div>
													</m:td>
												</m:tr--%>

											</m:table>
											<m:table id="editMessageExplnTable" width="98%">
												<m:tr id="editMessageExplnTR1">
													<m:td id="labelMessageExpln" colspan="4"
														style="width:750px">
														<m:div styleClass="padb" id="editExplanationTextDiv">
															<h:outputLabel id="editExplanationText" for="messageExpln"
																value="#{mesg['label.ltres.explanation']}" />
															<m:br id="editExplanationTextBr" />
															<h:inputText id="messageExpln" size="75" maxlength="255"
																value="#{LettersAndResponsesDataBean.letterGenerationInputVO.explnText}">
															</h:inputText>
														</m:div>
													</m:td>
												</m:tr>
											</m:table>
										</m:div>
									</m:div>
									<%--Audit Log Section Start --%>
									<m:div id="logCrspdLtrRespDetailsAuditDivOne"
										rendered="#{CorrespondenceDataBean.auditLogFlag}">
										<f:subview id="logCrspdLtrRespDetailsAuditSubView">
											<m:div id="logCrspdLtrRespDetailsAuditDivTwo">
												<audit:auditTableSet id="logCrspdLtrRespDetailsAuditId"
													value="#{LettersAndResponsesDataBean.letterGenerationInputVO.auditKeyList}"
													numOfRecPerPage="10"></audit:auditTableSet>
											</m:div>
										</f:subview>
									</m:div>
									<%--Audit Log Section End --%>
								</m:div>
							</m:div>
						</m:div>
					</m:div>

				</m:div>
			</m:div>

		</m:section>
	</f:subview>
</m:div>


<%-- Commented following code as part of Heap Dump issue # 60 & 62 defects 659392, 659394.
Rather fetching letter details always page reloads, now it will hit DB only when required.
Like Save, View Letter Request, Create Letter, Audit Log etc.
--%>
