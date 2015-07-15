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
<LINK rel="stylesheet" type="text/css"
	href='<%=renderResponse.encodeURL(renderRequest.getContextPath()
							+ "/theme/stylesheet.css")%>'
	title="Style">
<f:view>

	<%@page import="javax.faces.context.FacesContext"%>
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
		basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_LetterTemplateMaintenance"
		var="tmplt" />
	<f:loadBundle
		basename="com.acs.enterprise.common.util.helper.nl.ProgramMessages"
		var="ref" />
	<f:loadBundle
		basename="com.acs.enterprise.common.util.helper.nl.EnterpriseMessages"
		var="ent" />
	<hx:scriptCollector id="scriptCollector1">

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

function focusComponent ()
{
	var focusVal = document.getElementById ("view<portlet:namespace/>:LetterTemplateForm:curFocusValId");
	var evtMsgCode = document.getElementById ("view<portlet:namespace/>:LetterTemplateForm:templateName");
	//alert ('focusVal = ' + focusVal);
	if (focusVal != null)
	{
		//alert ('focusVal.value = ' + focusVal.value);
		//alert ('Event Message Code Value = ' + evtMsgCode);
		//alert ('Satified Condition ?? ' + (focusVal.value == 'Edit'));
		if (focusVal.value == 'Edit')
		{
			//alert ('Ready to Set Focus');
			evtMsgCode.focus();
			focusVal.value = "";
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
	var hiddenValue = document.getElementById('view<portlet:namespace/>:CategoryForm:auditlogDetails:'+id);
    
	if ((hiddenValue == null) ||(hiddenValue == '')|| (hiddenValue.length == 0)) {
		hideMe('audit_plus');
	} else if ((hiddenValue.value == 'false')) 
	{
		hideMe('audit_plus');
	} 
		
}

function textLimit(field, maxlen) {
	
	if (field.value.length > maxlen)
	field.value = field.value.substring(0, maxlen);
   }
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
		 
	}

	</SCRIPT>

		</f:verbatim>
		<body id="letterTemplatesBody" onload="focusComponent()">
		
		<h:outputText id="PRGCMGTOT1514"
			value="#{MaintainTemplateControllerBean.pageAccessPermission}"></h:outputText>
		<h:form id="LetterTemplateForm">
			<f:subview id="scriptViewTMPLTID">
				<jsp:include page="/jsp/script/scripts.jsp" />
			</f:subview>
		
			<h:inputHidden id="PRGCMGTIHMT01" value="#{MaintainTemplateControllerBean.allTemplateValues}"
			 rendered="#{MaintainTemplateDataBean.allTemplates}"></h:inputHidden>

			<h:inputHidden id="curFocusValId"
				value="#{MaintainTemplateDataBean.initialCursorFocusValue}"></h:inputHidden>
		

			<m:body>
				<%--ESPRD00696109 to remove fieldset <m:table width="100%">--%>

					<m:div styleClass="moreInfoBar">
						<h:panelGroup id="linksSection">
							<m:div styleClass="infoTitle">
								<m:span styleClass="required">
									<h:outputText id="PRGCMGTOT1515"
										value="#{ref['label.ref.requiredField']}"
										styleClass="style:RED" style="font-weight:normal" />
								</m:span>
							</m:div>
							<m:div styleClass="infoActions">
								<h:commandButton id="LtrTmpltsAuditLogCmdButton"
									onclick="javascript:flagWarn=false;"
									style="align:right;COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:10px;WIDTH:60px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"
									value="#{tmplt['label.ref.auditLog']}"
									action="#{MaintainTemplateControllerBean.doAuditKeyListOperation}">
								</h:commandButton>
							</m:div>
						</h:panelGroup>
					</m:div>


					<%--	<m:div style="width:100%">--%>
				<%--<m:div styleClass="moreInfoBar">
								<m:div styleClass="infoTitle" align="left">
									<h:outputText id="PRGCMGTOT1515" value="#{ref['label.ref.requiredField']}"										style="color:maroon" />
								</m:div>--%>
				<%--m:div styleClass="infoActions">
									<h:outputLink id="PRGCMGTOLK34" value="/wps/myportal/InternalUserHomePage">
										<h:outputText id="PRGCMGTOT1516" styleClass="strong"											value="#{ent['label-sw-cancel']}" />
									</h:outputLink>
								</m:div--%>
				<%--	</m:div>--%>
				<%--	<m:table id="LtrTmpltsAuditLogLinkTable" width="97%">
								<m:tr>
									<m:td>
										<m:div align="right" id="LtrTmpltsAuditLogLinkDiv">--%>
				<%--h:commandLink id="LtrTmpltsAuditLogCmdLink"
												action="#{MaintainTemplateControllerBean.doAuditKeyListOperation}">

												<h:outputText id="LtrTmpltsAuditLogLinkText"													value="#{tmplt['label.ref.auditLog']}" />
											</h:commandLink--%>
				<%--		<h:commandButton id="LtrTmpltsAuditLogCmdButton"											  style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:54px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"									          value="#{tmplt['label.ref.auditLog']}" disabled="#{MaintainTemplateDataBean.updateLtrTemplates}"									          action="#{MaintainTemplateControllerBean.doAuditKeyListOperation}" >
											</h:commandButton>
										</m:div>
									</m:td>
								</m:tr>
							</m:table>--%>
				<h:messages id="PRGCMGTMS22" layout="table" showDetail="true"
					showSummary="false" style="color: red" />
				<m:br clear="all" />
				<m:br />

				<m:section id="PRGCMGTSEC01" styleClass="otherbg">
					<m:legend>
						<h:outputText id="PRGCMGTOT1517"
							value="#{tmplt['label.template.maintainTemplate']}" />
					</m:legend>
					<!-- Rendering the Template List -->
					<%--h:dataTable id="letterTemplates" width="100%"
									styleClass="dataTable" rows="10" var="templateDetails"
									columnClasses="columnClass" headerClass="headerClass"
									footerClass="footerClass" rowClasses="row_alt,row"
									binding="#{MaintainTemplateDataBean.templateHtmlDataTable}"
									value="#{MaintainTemplateDataBean.listOfTemplateVOs}"
									onmouseover="return tableMouseOver(this, event);"
									onmouseout="return tableMouseOut(this, event);"--%>
					<t:dataTable id="letterTemplates" width="100%"
						styleClass="dataTable" first="#{MaintainTemplateDataBean.startIndexForCatgry}"
						rowOnMouseOver="this.origClassName = this.className;this.className='row_mouse row_link';"
						rowOnMouseOut="if (this.origClassName) this.className = this.origClassName;"
						rowOnClick="firstChild.lastChild.click();" rows="10"
						var="templateDetails" columnClasses="columnClass"
						headerClass="headerClass" footerClass="footerClass"
						rowClasses="row_alt,row" rowIndexVar="rowIndex"
						value="#{MaintainTemplateDataBean.listOfTemplateVOs}">

						<h:column id="templateIDCol">
							<f:facet name="header">
								<h:panelGrid id="PRGCMGTPGD169" columns="2">
									<%-- 
                                           - Defect # : ESPRD00696109
                                           - Purpose : To solve Letter Templates UI issues
                                        --%>
									<h:outputText id="templateIDLabel"
										value="#{tmplt['label.template.templateID']}"
										style="color: #fff;font-weight:bold;" />
									<%-- End of modification: CTS --%>
									<h:panelGroup id="PRGCMGTPGP115">
										<t:div style="width x;align=left">
											<t:commandLink id="templateAscCmdLink"
												onclick="javascript:flagWarn=false;"
												style="text-decoration: none;"
												actionListener="#{MaintainTemplateControllerBean.getAllSortedTemplates}"
												rendered="#{MaintainTemplateDataBean.imageRender != 'templateAscCmdLink'}">
												<%-- <h:graphicImage alt="for ascending" styleClass="sort_asc"
														width="8" /> --%>
												<m:div title="for ascending" styleClass="sort_asc" />

												<f:attribute name="columnName" value="Template ID" />
												<f:attribute name="sortOrder" value="ascending" />
												<%--h:outputText value="Asc" /--%>
											</t:commandLink>
										</t:div>

										<t:div style="width x;align=left">
											<t:commandLink id="templateDscCmdLink"
												onclick="javascript:flagWarn=false;"
												style="text-decoration: none;"
												actionListener="#{MaintainTemplateControllerBean.getAllSortedTemplates}"
												rendered="#{MaintainTemplateDataBean.imageRender != 'templateDscCmdLink'}">
												<%-- <h:graphicImage alt="for descending" styleClass="sort_desc"
														width="8" /> --%>
												<m:div title="for descending" styleClass="sort_desc" />
												<f:attribute name="columnName" value="Template ID" />
												<f:attribute name="sortOrder" value="descending" />
												<%--h:outputText value="Desc" /--%>
											</t:commandLink>
										</t:div>
									</h:panelGroup>
								</h:panelGrid>
							</f:facet>


							<%--h:commandLink id="edit" immediate="true"
											action="#{MaintainTemplateControllerBean.getTemplateDetails}">
											<h:outputText id="valTemplateID"												value="#{templateDetails.cotsLtrTmpltKeyData}" />
										</h:commandLink--%>
							<t:commandLink id="edit"
								action="#{MaintainTemplateControllerBean.getTemplateDetails}"
								onclick="javascript:flagWarn=false;"
								onmousedown="javascript:flagWarn=false;">
								<h:outputText id="valTemplateID"
									value="#{templateDetails.cotsLtrTmpltKeyData}" />
								<f:param name="rowNum" value="#{rowIndex}"></f:param>
							</t:commandLink>


						</h:column>

						<h:column id="templateNameCol">
							<f:facet name="header">
								<h:panelGrid id="PRGCMGTPGD170" columns="2">
									<%-- 
                                           - Defect # : ESPRD00696109
                                           - Purpose : To solve Letter Templates UI issues
                                        --%>
									<h:outputText id="templateNameLabel"
										value="#{tmplt['label.template.templateName']}"
										style="color: #fff;font-weight:bold;" />
									<%-- End of modification: CTS --%>
									<h:panelGroup id="PRGCMGTPGP116">
										<t:div style="width x;align=left">
											<t:commandLink id="nameAscCmdLink"
												onclick="javascript:flagWarn=false;"
												style="text-decoration: none;"
												actionListener="#{MaintainTemplateControllerBean.getAllSortedTemplates}"
												rendered="#{MaintainTemplateDataBean.imageRender != 'nameAscCmdLink'}">
												<%-- <h:graphicImage alt="for ascending" styleClass="sort_asc"
														width="8" />--%>
												<%--m:div title="#{tmplt['title.template.forAscd']}"
																styleClass="sort_asc" /--%>
												<m:div title="for ascending" styleClass="sort_asc" />
												<f:attribute name="columnName" value="Template Name" />
												<f:attribute name="sortOrder" value="ascending" />

												<%--h:outputText value="Asc" /--%>
											</t:commandLink>
										</t:div>
										<t:div style="width x;align=left">
											<t:commandLink id="nameDscCmdLink"
												onclick="javascript:flagWarn=false;"
												style="text-decoration: none;"
												actionListener="#{MaintainTemplateControllerBean.getAllSortedTemplates}"
												rendered="#{MaintainTemplateDataBean.imageRender != 'nameDscCmdLink'}">
												<%--<h:graphicImage alt="for descending" styleClass="sort_desc"
														width="8" />--%>
												<%--m:div title="#{tmplt['title.template.forDsnd']}"
																styleClass="sort_desc" /--%>
												<m:div title="for descending" styleClass="sort_desc" />
												<f:attribute name="columnName" value="Template Name" />
												<f:attribute name="sortOrder" value="descending" />

												<%--h:outputText value="Desc" /--%>
											</t:commandLink>
										</t:div>
									</h:panelGroup>
								</h:panelGrid>
							</f:facet>
							<h:outputText id="valTemplateName"
								value="#{templateDetails.templateName}" />
						</h:column>

						<h:column id="templateNameDescriptionCol">
							<f:facet name="header">
								<h:panelGrid id="PRGCMGTPGD171" columns="2">
									<%-- 
                                           - Defect # : ESPRD00696109
                                           - Purpose : To solve Letter Templates UI issues
                                        --%>
									<h:outputText id="templateNameDescription"
										value="#{tmplt['label.template.templateDescription']}"
										style="color: #fff;font-weight:bold;" />
									<%-- End of modification: CTS --%>
									<h:panelGroup id="PRGCMGTPGP117">
										<t:div style="width x;align=left">
											<t:commandLink id="descAscCmdLink"
												onclick="javascript:flagWarn=false;"
												style="text-decoration: none;"
												actionListener="#{MaintainTemplateControllerBean.getAllSortedTemplates}"
												rendered="#{MaintainTemplateDataBean.imageRender != 'descAscCmdLink'}">
												<%--<h:graphicImage alt="for ascending" styleClass="sort_asc"
														width="8" />--%>
												<%--m:div title="#{tmplt['title.template.forAscd']}"
																styleClass="sort_asc" /--%>
												<m:div title="for ascending" styleClass="sort_asc" />
												<f:attribute name="columnName" value="Template Description" />
												<f:attribute name="sortOrder" value="ascending" />

												<%--h:outputText value="Desc" /--%>
											</t:commandLink>
										</t:div>
										<t:div style="width x;align=left">
											<t:commandLink id="descDscCmdLink"
												onclick="javascript:flagWarn=false;"
												style="text-decoration: none;"
												actionListener="#{MaintainTemplateControllerBean.getAllSortedTemplates}"
												rendered="#{MaintainTemplateDataBean.imageRender != 'descDscCmdLink'}">
												<%--<h:graphicImage alt="for descending" styleClass="sort_desc"
														width="8" />--%>
												<%--m:div title="#{tmplt['title.template.forDsnd']}"
																styleClass="sort_desc" /--%>
												<m:div title="for descending" styleClass="sort_desc" />
												<f:attribute name="columnName" value="Template Description" />
												<f:attribute name="sortOrder" value="descending" />

												<%--h:outputText value="Desc" /--%>
											</t:commandLink>
										</t:div>
									</h:panelGroup>
								</h:panelGrid>
							</f:facet>
							<h:outputText id="valTemplateDescription"
								value="#{templateDetails.templateDescription}" />
						</h:column>


						<h:column id="supervisorRevwReqdCol">
							<f:facet name="header">
								<h:panelGrid id="PRGCMGTPGD172" columns="2">
									<%--
                                           - Defect # : ESPRD00696109
                                           - Purpose : To solve Letter Templates UI issues
                                        --%>
									<h:outputText id="supervisorRevwReqd"
										value="#{tmplt['label.template.supervisorRevReqd']}"
										style="color: #fff;font-weight:bold;" />
									<%-- End of modification: CTS --%>
									<h:panelGroup id="PRGCMGTPGP118">
										<t:div style="width x;align=left">
											<t:commandLink id="sprRevReqAscCmdLink"
												onclick="javascript:flagWarn=false;"
												style="text-decoration: none;"
												actionListener="#{MaintainTemplateControllerBean.getAllSortedTemplates}"
												rendered="#{MaintainTemplateDataBean.imageRender != 'sprRevReqAscCmdLink'}">
												<%--<h:graphicImage alt="for ascending" styleClass="sort_asc"
														width="8" />-- %>
													<%--m:div title="#{tmplt['title.template.forAscd']}"
																styleClass="sort_asc" /--%>
												<m:div title="for ascending" styleClass="sort_asc" />
												<f:attribute name="columnName"
													value="Supervisor Review Required" />
												<f:attribute name="sortOrder" value="ascending" />

												<%--h:outputText value="Asc" /--%>
											</t:commandLink>
										</t:div>
										<t:div style="width x;align=left">
											<t:commandLink id="sprRevReqDscCmdLink"
												onclick="javascript:flagWarn=false;"
												style="text-decoration: none;"
												actionListener="#{MaintainTemplateControllerBean.getAllSortedTemplates}"
												rendered="#{MaintainTemplateDataBean.imageRender != 'sprRevReqDscCmdLink'}">
												<%-- <h:graphicImage alt="for descending" styleClass="sort_desc"
														width="8" />--%>
												<%--m:div title="#{tmplt['title.template.forDsnd']}"
																styleClass="sort_desc" /--%>
												<m:div title="for descending" styleClass="sort_desc" />
												<f:attribute name="columnName"
													value="Supervisor Review Required" />
												<f:attribute name="sortOrder" value="descending" />

												<%--h:outputText value="Desc" /--%>
											</t:commandLink>
										</t:div>
									</h:panelGroup>
								</h:panelGrid>
							</f:facet>
							<h:outputText id="valSupervisorRevReqd"
								value="#{templateDetails.dfltSprvsrRevwReqdIndicator}" />
						</h:column>


						<h:column id="voidIndCol">
							<f:facet name="header">
								<h:panelGrid id="PRGCMGTPGD173" columns="2">
									<%--
                                           - Defect # : ESPRD00696109
                                           - Purpose : To solve Letter Templates UI issues
                                        --%>
									<h:outputText id="voidInd"
										value="#{tmplt['label.template.voidInd']}"
										style="color: #fff;font-weight:bold;" />
									<%--End of modification: CTS --%>
									<h:panelGroup id="PRGCMGTPGP119">
										<t:div style="width x;align=left">
											<t:commandLink id="voidAscCmdLink"
												onclick="javascript:flagWarn=false;"
												style="text-decoration: none;"
												actionListener="#{MaintainTemplateControllerBean.getAllSortedTemplates}"
												rendered="#{MaintainTemplateDataBean.imageRender != 'voidAscCmdLink'}">
												<%-- <h:graphicImage alt="for ascending" styleClass="sort_asc"
														width="8" />--%>
												<%--m:div title="#{tmplt['title.template.forAscd']}"
																styleClass="sort_asc" /--%>
												<m:div title="for ascending" styleClass="sort_asc" />

												<f:attribute name="columnName" value="Void" />
												<f:attribute name="sortOrder" value="ascending" />

												<%--h:outputText value="Asc" /--%>
											</t:commandLink>
										</t:div>
										<t:div style="width x;align=left">
											<t:commandLink id="voidDscCmdLink"
												style="text-decoration: none;"
												onclick="javascript:flagWarn=false;"
												actionListener="#{MaintainTemplateControllerBean.getAllSortedTemplates}"
												rendered="#{MaintainTemplateDataBean.imageRender != 'voidDscCmdLink'}">
												<%--<h:graphicImage alt="for descending" styleClass="sort_desc"
														width="8" />--%>
												<%--m:div title="#{tmplt['title.template.forDsnd']}"
																styleClass="sort_desc" /--%>
												<m:div title="for descending" styleClass="sort_desc" />
												<f:attribute name="columnName" value="Void" />
												<f:attribute name="sortOrder" value="descending" />
												<%--h:outputText value="Asc" /--%>
											</t:commandLink>
										</t:div>
									</h:panelGroup>
								</h:panelGrid>
							</f:facet>
							<h:outputText id="valVoidInd"
								value="#{templateDetails.voidIndicator}" />
						</h:column>

					</t:dataTable>

				<%--	<t:dataScroller id="CMGTTOMDS54" pageCountVar="pageCount"
						onclick="javascript:flagWarn=false;" pageIndexVar="pageIndex"
						paginator="true" paginatorActiveColumnStyle='font-weight:bold;'
						paginatorMaxPages="3" immediate="false" for="letterTemplates"
						firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
						rowsCountVar="rowsCount"
						style="float:right;position:relative;bottom:-4px">
						<f:facet name="previous">
							<h:outputText id="PRGCMGTOT1518"
								style="text-decoration:underline;"
								value=" #{ref['label.ref.lt']} " rendered="#{pageIndex > 1}"></h:outputText>
						</f:facet>
						<f:facet name="next">
							<h:outputText id="PRGCMGTOT1519"
								style="text-decoration:underline;"
								value=" #{ref['label.ref.gt']} "
								rendered="#{pageIndex < pageCount}"></h:outputText>
						</f:facet>
						<%--<h:outputText id="PRGCMGTOT1520"
								value="showing #{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
								style="float:left;position:relative;bottom:-6px;font-weight:bold;"
								rendered="#{rowsCount>0}">
							</h:outputText>--%>
						<%-- 
                               - Defect # : ESPRD00696109
                               - Purpose : To solve Letter Templates UI issues
                            --%>
					<%-- 	<h:outputText id="PRGCMGTOT1520"
							value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
							style="float:left;position:relative;bottom:-6px;"
							rendered="#{rowsCount>0}">
						</h:outputText>
						<%--End of modification: CTS --%>
				<%-- 	</t:dataScroller>--%>
				<%--Pagination starts from here --%>
					<h:panelGrid id="searchinfoDetails" columns="2" style="width:100%;">
		    <h:panelGroup id="searchRecordInfo" style="text-align: left;width:100%;">
		       <h:outputText id="searchRecordInfoText" value="#{MaintainTemplateDataBean.startRecord}-#{MaintainTemplateDataBean.endRecord} of #{MaintainTemplateDataBean.count}"/> 
		    </h:panelGroup>
		     <h:panelGroup id="searchPageInfo" style="text-align: right;width:100%;">
		       <t:commandLink id ="showPrevious" action="#{MaintainTemplateControllerBean.searchPageNavigation}" 
		               rendered="#{MaintainTemplateDataBean.showPrevious}"
		               onclick="javascript:flagWarn=false;" >
		          <f:param name="param" value="showPrevious" />     
		        <h:outputText id="showPreviousText" escape="false" value="&lt&lt" />
		        <f:verbatim >&nbsp;</f:verbatim>
		       </t:commandLink> 
		       <t:commandLink id="firstPage" action="#{MaintainTemplateControllerBean.searchPageNavigation}" 
		               rendered="#{MaintainTemplateDataBean.renderFirstPage}" 
		               onclick="javascript:flagWarn=false;">  
		         <f:param name="param" value="firstPage" />       
		         <h:outputText id="firstPageText"  value="#{MaintainTemplateDataBean.firstPage}" rendered="#{!MaintainTemplateDataBean.boldPageNum[0]}"/>
		         <h:outputText id="firstPageText1" style="color:blue;font-weight:bold;"  value="#{MaintainTemplateDataBean.firstPage}" 
		                  rendered="#{MaintainTemplateDataBean.boldPageNum[0]}" />
		         <f:verbatim >&nbsp;</f:verbatim>
		       </t:commandLink>
		       <t:commandLink id="firstPagePlusOne" action="#{MaintainTemplateControllerBean.searchPageNavigation}" 
		               rendered="#{MaintainTemplateDataBean.renderFirstPagePlusOne}" 
		               onclick="javascript:flagWarn=false;">  
		         <f:param name="param" value="firstPagePlusOne" />        
		         <h:outputText id="firstPagePlusOneText" value="#{MaintainTemplateDataBean.firstPage+1}" rendered="#{!MaintainTemplateDataBean.boldPageNum[1]}"/>
		         <h:outputText id="firstPagePlusOneText1" style="color:blue;font-weight:bold;"  value="#{MaintainTemplateDataBean.firstPage+1}" 
		                rendered="#{MaintainTemplateDataBean.boldPageNum[1]}" />
		         <f:verbatim >&nbsp;</f:verbatim>
		       </t:commandLink>  
		        <t:commandLink id="firstPagePlusTwo" action="#{MaintainTemplateControllerBean.searchPageNavigation}" 
		               rendered="#{MaintainTemplateDataBean.renderFirstPagePlusTwo}" 
		               onclick="javascript:flagWarn=false;">  
                 <f:param name="param" value="firstPagePlusTwo" />  
		         <h:outputText id="firstPagePlusTwoText" value="#{MaintainTemplateDataBean.firstPage+2}" rendered="#{!MaintainTemplateDataBean.boldPageNum[2]}"/>
		         <h:outputText id="firstPagePlusTwoText1"  style="color:blue;font-weight:bold;"  value="#{MaintainTemplateDataBean.firstPage+2}" 
		                           rendered="#{MaintainTemplateDataBean.boldPageNum[2]}" />
		         <f:verbatim >&nbsp;</f:verbatim>
		       </t:commandLink>  
		       <t:commandLink id ="showNext" action="#{MaintainTemplateControllerBean.searchPageNavigation}" 
		               rendered="#{MaintainTemplateDataBean.showNext}" 
		               onclick="javascript:flagWarn=false;">
                 <f:param name="param" value="showNext" />  
		         <h:outputText id="showNextText" value=">>" />
		       </t:commandLink> 
		    </h:panelGroup>
		</h:panelGrid>	
	
<%--Pagination end from here --%>
					<m:br />
					<m:br />

					<!-- Rendering the Template Details-->
					<%--ESPRD00696109--%>
					<m:div styleClass="moreInfo"
						rendered="#{MaintainTemplateDataBean.renderEditTemplate}">
						<m:div styleClass="moreInfoBar">
							<%--<m:div styleClass="moreInfoBar"
							rendered="#{MaintainTemplateDataBean.renderEditTemplate}">--%>
							<%--End of modification: CTS--%>
							<m:table id="PROVIDERMMT20120731164811610" border="0" width="100%">
								<m:tr>
									<m:td styleClass="alignLeft">
										<m:div styleClass="infoTitle">
											<h:outputText id="PRGCMGTOT1521"
												value="#{tmplt['label.template.editTemplate']}" />
										</m:div>
									</m:td>
									<m:td styleClass="alignRight">
										<m:div styleClass="infoActions">
											<h:panelGroup id="PRGCMGTPGP120">
												<%--h:commandLink styleClass="strong"
												action="#{MaintainTemplateControllerBean.updateTemplateDetails}">
												<h:outputText id="PRGCMGTOT1522" value="#{ent['label-sw-save']}" />
											</h:commandLink--%>
												<%--below command button added for CR 1825--%>
												<h:commandButton id="editSaveButton"
													onclick="javascript:flagWarn=false;"
													action="#{MaintainTemplateControllerBean.updateTemplateDetails}"
													value="#{ent['label-sw-save']}"
													disabled="#{MaintainTemplateDataBean.updateLtrTemplates}"
													style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:40px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal">
												</h:commandButton>
												<h:outputText id="PRGCMGTOT1523" escape="false"
													style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:3px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: top;word-spacing: normal"
													value="#{ref['label.ref.linkSeperator']}" />

												<%--h:commandLink styleClass="commandLink"
												action="#{MaintainTemplateControllerBean.resetTemplateDetails}" immediate="true">
												<h:outputText id="PRGCMGTOT1524" value="#{ent['label-sw-reset']}" />
											</h:commandLink--%>
												<%--below command button added for CR 1825--%>
												<h:commandButton id="editResetButton"
													onclick="javascript:flagWarn=false;"
													action="#{MaintainTemplateControllerBean.resetTemplateDetails}"
													value="#{ent['label-sw-reset']}"
													disabled="#{MaintainTemplateDataBean.updateLtrTemplates}"
													style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:40px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal">
												</h:commandButton>
												<h:outputText id="PRGCMGTOT1525" escape="false"
													style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:3px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: top;word-spacing: normal"
													value="#{ref['label.ref.linkSeperator']}" />

												<%--h:commandLink id="PRGCMGTCL223" 
									styleClass="commandLink" 
									onclick="return dataloss();"	
									action="#{MaintainTemplateControllerBean.cancelTemplateDetails}">
									<h:outputText id="PRGCMGTOT1526"
									value="#{ent['label-sw-cancel']}" />
									</h:commandLink--%>


												<h:commandButton id="PRGCMGTCL223"
													action="#{MaintainTemplateControllerBean.cancelTemplateDetails}"
													value="#{ent['label-sw-cancel']}"
													style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:50px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal">
												</h:commandButton>

											</h:panelGroup>
										</m:div>
									</m:td>
								</m:tr>
							</m:table>
							<%--ESPRD00696109--%>
						</m:div>
						<%--End of modification: CTS--%>


						<m:div styleClass="moreInfoContent">
							<m:div styleClass="padb">
								<m:div styleClass="msgBox"
									rendered="#{MaintainTemplateDataBean.showSucessMessage}">
									<h:outputText id="PRGCMGTOT1527"
										value="#{ent['label-sw-success']}" />
								</m:div>
								<m:table id="PROVIDERMMT20120731164811611" cellspacing="0" width="94%">
									<m:tr>
										<m:td id="labelVoidInd">
											<m:div styleClass="padb">
												<h:outputLabel id="PRGCMGTOT1528"
													value="#{tmplt['label.template.voidInd']}" for="voidIndicator"/>
												<m:br />
												<h:selectOneRadio id="voidIndicator" enabledClass="black"
													onclick="javascript:flagWarn=false;"
													value="#{MaintainTemplateDataBean.templateVO.voidIndicator}">
													<f:selectItem itemLabel="#{ref['label.ref.yes']}"
														itemValue="Yes" />
													<f:selectItem itemLabel="#{ref['label.ref.no']}"
														itemValue="No" />
												</h:selectOneRadio>
											</m:div>
										</m:td>
										<m:td id="labelTemplateName">
											<m:div styleClass="padb">
												<h:outputLabel id="PRGCMGTOT1529"
													value="#{tmplt['label.template.templateName']}" for="templateName"/>
												<m:br />
												<h:inputText id="templateName" size="25" maxlength="50"
													value="#{MaintainTemplateDataBean.templateVO.templateName}">
												</h:inputText>


											</m:div>
										</m:td>

										<m:td id="labelTemplateDescription">
											<m:div styleClass="padb">
												<h:outputLabel id="PRGCMGTOT1530"
													value="#{tmplt['label.template.templateDescription']}" for="templateDescription"/>
												<m:br />
												<h:inputTextarea id="templateDescription" cols="35" rows="5"
													value="#{MaintainTemplateDataBean.templateVO.templateDescription}"
													onkeyup="textLimit(this,'50');"
													onmouseout="textLimit(this,'50');">
												</h:inputTextarea>




											</m:div>
										</m:td>


										<m:td id="supRevRqdInd">
											<m:div styleClass="padb">
												<h:outputLabel id="PRGCMGTOT1531"
													value="#{tmplt['label.template.supervisorRevReqd']}" for="labelSprRevRqd"/>
												<m:br />
												<h:selectOneRadio id="labelSprRevRqd" enabledClass="black"
													value="#{MaintainTemplateDataBean.templateVO.dfltSprvsrRevwReqdIndicator}">
													<f:selectItem itemLabel="#{ref['label.ref.yes']}"
														itemValue="Yes" />
													<f:selectItem itemLabel="#{ref['label.ref.no']}"
														itemValue="No" />
												</h:selectOneRadio>
											</m:div>
										</m:td>
									</m:tr>
								</m:table>
								<m:br />
								<m:br />
								<m:table id="row2">
									<m:tr>
										<m:td id="labelNumberOfdays">
											<m:div styleClass="padb">
												<h:outputLabel id="PRGCMGTOT1532"
													value="#{tmplt['label.template.numOfDays']}" for="dfltDueDtOffsetNum"/>
												<m:br />

												<%--h:inputText id="dfltDueDtOffsetNum" size="10"
																			maxlength="2"
																			value="#{MaintainTemplateDataBean.templateVO.dfltDueDtOffsetNum}">
																		</h:inputText--%>
												<h:inputText id="dfltDueDtOffsetNum" size="10" maxlength="2"
													value="#{MaintainTemplateDataBean.dueDtOffsetNum}">
												</h:inputText>
												<m:br />
												<h:message id="dfltDueDtOffsetNumMsg"
													for="dfltDueDtOffsetNum" style="color: red" />

											</m:div>
										</m:td>

										<m:td id="labelTemplateID">
											<m:div styleClass="padb">
												<h:outputText id="PRGCMGTOT1533"
													value="#{tmplt['label.template.templateID']}"></h:outputText>
												<m:br />

												<h:outputText id="PRGCMGTOT1534"
													value="#{MaintainTemplateDataBean.templateVO.cotsLtrTmpltKeyData}">
												</h:outputText>
											</m:div>
										</m:td>
										<m:td>
											<m:table id="row2_list">
												<m:tr>
													<m:td styleClass="padb">
														<h:outputLabel id="PRGCMGTOT1535"
															value="#{tmplt['label.template.availableKeywords']}" for="availableKeywords"/>
													</m:td>
													<m:td styleClass="padb">
														<h:outputText id="PRGCMGTOT1536" escape="false"
															value="#{ref['label.ref.singleSpace']}" />
													</m:td>
													<m:td styleClass="padb">
														<h:outputLabel id="PRGCMGTOT1537"
															value="#{tmplt['label.template.associatedKeywords']}"  for="associatedKeywords"/>
													</m:td>
												</m:tr>
												<m:tr>
													<m:td align="top">
														<h:selectManyListbox size="11"
															onclick="javascript:flagWarn=false;"
															id="availableKeywords"
															value="#{MaintainTemplateDataBean.selectedList}"
															disabled="#{MaintainTemplateDataBean.updateLtrTemplates}">
															<f:selectItems
																value="#{MaintainTemplateDataBean.tempAvailableList}" />
														</h:selectManyListbox>
													</m:td>
													<m:td>
														<m:br />
														<m:table id="PROVIDERMMT20120731164811612">
															<m:tr>
																<m:td align="center">
																	<h:commandButton id="PRGCMGTCB81" styleClass="formBtn"
																		onclick="javascript:flagWarn=false;"
																		value="#{tmplt['label.template.rShift']}"
																		action="#{MaintainTemplateControllerBean.moveSelectedList}"
																		disabled="#{MaintainTemplateDataBean.updateLtrTemplates}">
																	</h:commandButton>
																</m:td>
															</m:tr>
															<m:tr>
																<m:td align="center">
																	<h:commandButton id="PRGCMGTCB82" styleClass="formBtn"
																		onclick="javascript:flagWarn=false;"
																		value="#{tmplt['label.template.lShift']}"
																		action="#{MaintainTemplateControllerBean.removeSelectedList}"
																		disabled="#{MaintainTemplateDataBean.updateLtrTemplates}">
																	</h:commandButton>
																</m:td>
															</m:tr>
														</m:table>
													</m:td>
													<m:td>
														<h:selectManyListbox size="11" id="associatedKeywords"
															onclick="javascript:flagWarn=false;"
															value="#{MaintainTemplateDataBean.removedList}"
															disabled="#{MaintainTemplateDataBean.updateLtrTemplates}">
															<f:selectItems
																value="#{MaintainTemplateDataBean.tempAssociatedList}" />
														</h:selectManyListbox>
													</m:td>
												</m:tr>
											</m:table>
										</m:td>
									</m:tr>
								</m:table>
							</m:div>
						</m:div>
						<%--Audit Log Section Start --%>
						<m:div id="LtrTmpltsDetailsAuditDivOne"
							rendered="#{MaintainTemplateDataBean.auditLogFlag}">
							<f:subview id="LtrTmpltsDetailsAuditSubView">
								<m:div id="LtrTmpltsDetailsAuditDivTwo">
									<audit:auditTableSet id="LtrTmpltsDetailsAuditId"
										value="#{MaintainTemplateDataBean.templateVO.auditKeyList}"
										numOfRecPerPage="10"></audit:auditTableSet>
								</m:div>
							</f:subview>
						</m:div>
						<%--Audit Log Section End --%>
					</m:div>
				</m:section>


				<%--</m:table>--%>
			</m:body>
			<h:message styleClass="message" id="message1"
				for="dfltDueDtOffsetNum"></h:message>
		</h:form>
		</body>
	</hx:scriptCollector>
</f:view>
