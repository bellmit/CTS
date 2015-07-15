<%-- 
  - Author(s): Wipro
  - Date: Mon Jan 29 14:37:45 IST 2007
  - Copyright Notice: Copyright (c) 2006 Affiliated Computer Services, Inc.
  - @ 2006
  - Description: 
  --%>
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
<portlet:defineObjects />



<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>

<f:loadBundle
	basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_ContactCategoryMaintenance"
	var="ctg" />

<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.ProgramMessages"
	var="ref" />

<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.EnterpriseMessages"
	var="ent" />

<f:view>
	<t:saveState id="CMGTTOMSS375" value="#{CategoryDataBean}" />
	<t:saveState id="CMGTTOMSS376"
		value="#{CategoryDataBean.listOfCategorySubVOs}" />
	<t:saveState id="CMGTTOMSS377"
		value="#{CategoryDataBean.listOfCategoryVOs}" />
	<t:saveState id="CMGTTOMSS378"
		value="#{CategoryDataBean.listOfCategoryCustomFldVOs}" />
	<t:saveState id="CMGTTOMSS379"
		value="#{CategoryDataBean.refSubjectList}" />
	<t:saveState id="CMGTTOMSS380"
		value="#{CategoryDataBean.refPriorityList}" />
	<t:saveState id="CMGTTOMSS381"
		value="#{CategoryDataBean.categoryVO.listOfDeletedSubjects}" />

	<SCRIPT type="text/javascript">
		
		
		
	/*Small save Big Save starts*/
	  frmId = 'view<portlet:namespace/>:CategoryForm';
	/*Small save Big Save ends*/
		
		
		
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
		
	/** This method confirms the Delete Operation */    
      
       function confirmDelete(formID, id) 
       { 
           var box = confirm("Are you sure that you want to Delete?");
           
           if(box == true){
               document.getElementById('view<portlet:namespace/>:'+formID+':'+id).click();                         
           }          
       } 
       
	    /** Defect Fixed */
       function maintainCategoryCancel(link)
       {
       	if (isFormChanged(frmId) == true)
       	{
         		var answer = confirm("Unsaved changes will be lost.  Are you sure that you want to navigate?");				
       		if(answer)
       		{						
       			var entObject = findObjectByPartOfID(link);
       			entObject.click();
       		}
       	}
       	else
       	{
       		var entObject = findObjectByPartOfID(link);
       		entObject.click();
       	} 
       }
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
	var hiddenValuecat= document.getElementById('view<portlet:namespace/>:CategoryForm:CategoryAuditInc:'+id);
	var hiddenValue = document.getElementById('view<portlet:namespace/>:CategoryForm:auditlogDetails:'+id);
    
	if ((hiddenValue == null) ||(hiddenValue == '')|| (hiddenValue.length == 0)) {
		hideMe('audit_plus');
	} else if ((hiddenValue.value == 'false')) 
	{
		hideMe('audit_plus');
	} 
	if ((hiddenValuecat == null) ||(hiddenValuecat == '')|| (hiddenValuecat.length == 0)) 
	{
		hideMe('auditsubject');
	} else if ((hiddenValuecat.value == 'false')) 
	{
		hideMe('auditsubject');
	}
		
		
}
function toggleDiv(divid){
    if(document.getElementById(divid).style.display == 'none'){
      document.getElementById(divid).style.display = 'block';
    }else{
      document.getElementById(divid).style.display = 'none';
    }
  }
	
	</SCRIPT>

	<script>
		var thisForm = 'view<portlet:namespace/>:CategoryForm';
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
   		var focusPage = '#'+document.getElementById(thisForm+':focusId').value;
			if(focusPage != '' && focusPage != '#') {				
				document.location.href=focusPage;
			}
   		}
	</script>



	<%-- Small save Big Save start --%>
	<f:subview id="categorySave">
		<jsp:include page="/jsp/commonEntities/commonScripts.jsp" />
	</f:subview>
	<%--start Removed  onBeforeUnload for CR implementation--%>
	<body>
	<%--End Removed  onBeforeUnload for CR implementation--%>
	<%-- Small save Big Save ends --%>

	<h:form id="CategoryForm">
		<%-- start Added for CR implementation--%>
		<h:inputHidden value="#{CategoryControllerBean.controlRequired}"
			id="controlRequiredForCategory" />
		<%-- End Added for CR implementation--%>
		<m:inputHidden value="#{CategoryControllerBean.loadCategoryData}"></m:inputHidden>
		<m:inputHidden value="#{CategoryDataBean.validValuesforBusiUnit}"></m:inputHidden>
		<h:inputHidden id="focusId" value="#{CategoryDataBean.focusId}" />

		<m:body onload="renderAudit('audit_open');">
			<m:div>
				<m:div styleClass="floatContainer">
					<m:div styleClass="fullwidth">
						<m:div styleClass="moreInfoBar">
							<m:div styleClass="infoTitle" align="left">
								<h:outputText id="PRGCMGTOT1238"
									value="#{ref['label.ref.requiredField']}" styleClass="required" />
							</m:div>
							<m:div styleClass="infoActions">
								<%-- Defect Fixed 
								<h:commandLink id="CMGTCL8" action="#{CategoryControllerBean.redirect}" onclick="flagWarn=true;">							
									<h:outputText id="CMGTOT67" styleClass="strong"	value="#{ent['label-sw-cancel']}"/>
									<f:param name="com.ibm.faces.portlet.page.view" value="/wps/myportal/InternalUserHomePage"/>
								</h:commandLink> 
								<h:commandButton id="categoryCancel"
									action="#{CategoryControllerBean.redirect}"
									style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:50px;BORDER:0;text-align:left;font-family: Verdana; font-weight:bold; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal; position:relative;top:0.3px"
									value="#{ent['label-sw-cancel']}" onclick="flagWarn=true;"></h:commandButton> --%>
									
								<%--DEFECT_ESPRD00761890 --%>	
								<f:verbatim>  <a href="/wps/myportal/InternalUserHomePage" id="PRGCMGTCAT" onclick="flagWarn=true;"> </f:verbatim>
									<h:outputText id="PRGCMGTOT1CNCLCAT" styleClass="strong" value="#{ent['label-sw-cancel']}" />
								<f:verbatim></a></f:verbatim>
							
							</m:div>
						</m:div>

						<h:messages id="PRGCMGTMS16" layout="table" showDetail="true"
							showSummary="false" styleClass="errorMessage" />
						<m:br clear="all" />
						<m:br />

						<m:section id="PROVIDERMMS20120731164811531" styleClass="otherbg">
							<m:legend>
								<h:outputText id="PRGCMGTOT1240"
									value="#{ctg['label.category.maintainCategory']}" />
							</m:legend>

							<m:div styleClass="msgBox"
								rendered="#{CategoryDataBean.showDeletedMessage}">
								<h:outputText id="PRGCMGTOT1241"
									value="#{ctg['label.category.deleteMessage']}" />
							</m:div>

							<m:table id="PROVIDERMMT20120731164811532" styleClass="tableBar" width="100%">
								<m:tr>
									<m:td styleClass="tableAction">
										<h:commandButton id="PRGCMGTCB46" styleClass="formBtn"
											onclick="flagWarn=false;"
											disabled="#{!CategoryControllerBean.controlRequired}"
											value="#{ctg['label.category.addCategory']}"
											action="#{CategoryControllerBean.addCategory}" />
									</m:td>
								</m:tr>
							</m:table>

							

							<t:dataTable cellspacing="0" styleClass="dataTable" width="100%"
								rows="10" first="#{CategoryDataBean.startIndexForCatgry}"
								value="#{CategoryDataBean.listOfCategoryVOs}"
								var="categoryDetails" id="addCategory" rowIndexVar="rowIndex"
								columnClasses="columnClass" headerClass="headerClass"
								footerClass="footerClass" rowClasses="row_alt,row"
								rowOnClick="firstChild.lastChild.click();"
								rowOnMouseOver="this.origClassName = this.className;this.className='row_mouse row_link';"
								rowOnMouseOut="if (this.origClassName) this.className = this.origClassName;">

								<t:column id="activeCol">
									<f:facet name="header">
										<h:panelGrid id="PRGCMGTPGD148" columns="2">
											<h:outputLabel id="activeLabel" for="categoryPanelGrid"
												value="#{ctg['label.category.active']}" />
											<h:panelGroup id="categoryPanelGrid">
												<t:div styleClass="alignLeft">
													<t:commandLink id="categoryAscCmdLink"
														onmousedown="if(event.button==1) flagWarn=false;"
														actionListener="#{CategoryControllerBean.getAllSortedCategories}"
														rendered="#{CategoryDataBean.imageRender != 'categoryAscCmdLink'}">
														<m:div title="#{ctg['title.category.forAscd']}"
															styleClass="sort_asc" />
														<f:attribute name="#{ctg['id.category.columnName']}"
															value="voidIndicator" />
														<f:attribute name="#{ctg['id.category.sortOrder']}"
															value="ascending" />
													</t:commandLink>
												</t:div>
												<t:div styleClass="alignLeft">
													<t:commandLink id="categoryDscCmdLink"
														onmousedown="if(event.button==1) flagWarn=false;"
														actionListener="#{CategoryControllerBean.getAllSortedCategories}"
														rendered="#{CategoryDataBean.imageRender != 'categoryDscCmdLink'}">
														<m:div title="#{ctg['title.category.forDsnd']}"
															styleClass="sort_desc" />
														<f:attribute name="#{ctg['id.category.columnName']}"
															value="voidIndicator" />
														<f:attribute name="#{ctg['id.category.sortOrder']}"
															value="descending" />
													</t:commandLink>
												</t:div>
											</h:panelGroup>
										</h:panelGrid>
									</f:facet>
									<h:outputText id="valActCtg"
										value="#{categoryDetails.activeIndicator}" />
								</t:column>
								<t:column id="descCol">
									<f:facet name="header">
										<h:panelGrid id="PRGCMGTPGD149" columns="2">
											<h:outputLabel id="descLabel" for="descLabelValue"
												value="#{ref['label.ref.description']}" />
											<h:panelGroup id="descLabelValue">
												<t:div styleClass="alignLeft">
													<t:commandLink id="descAscCmdLink"
														onmousedown="if(event.button==1) flagWarn=false;"
														actionListener="#{CategoryControllerBean.getAllSortedCategories}"
														rendered="#{CategoryDataBean.imageRender != 'descAscCmdLink'}">

														<m:div title="#{ctg['title.category.forAscd']}"
															styleClass="sort_asc" />
														<f:attribute name="#{ctg['id.category.columnName']}"
															value="description" />
														<f:attribute name="#{ctg['id.category.sortOrder']}"
															value="ascending" />
													</t:commandLink>
												</t:div>
												<t:div styleClass="alignLeft">
													<t:commandLink id="descDscCmdLink"
														onmousedown="if(event.button==1) flagWarn=false;"
														actionListener="#{CategoryControllerBean.getAllSortedCategories}"
														rendered="#{CategoryDataBean.imageRender != 'descDscCmdLink'}">
														<m:div title="#{ctg['title.category.forDsnd']}"
															styleClass="sort_desc" />
														<f:attribute name="#{ctg['id.category.columnName']}"
															value="description" />
														<f:attribute name="#{ctg['id.category.sortOrder']}"
															value="descending" />
													</t:commandLink>
												</t:div>
											</h:panelGroup>
										</h:panelGrid>
									</f:facet>
									<t:commandLink id="editCategory"
										onmousedown="if(event.button==1) flagWarn=false;"
										action="#{CategoryControllerBean.getCategoryDetails}">
										<h:outputText id="valDescCtg"
											value="#{categoryDetails.categoryDesc}" />
										<f:param name="categoryRowIndex" value="#{rowIndex}" />
									</t:commandLink>
								</t:column>
								<t:column id="busiunitCol">
									<f:facet name="header">
										<h:panelGrid id="PRGCMGTPGD150" columns="2">
											<h:outputLabel id="busiunitLabel"
												value="#{ref['label.ref.businessunit']}" />
											<h:panelGroup id="PRGCMGTPGP97">
												<t:div styleClass="alignLeft">
													<t:commandLink id="busiunitAscCmdLink"
														onmousedown="if(event.button==1) flagWarn=false;"
														actionListener="#{CategoryControllerBean.getAllSortedCategories}"
														rendered="#{CategoryDataBean.imageRender != 'busiunitAscCmdLink'}">

														<m:div title="#{ctg['title.category.forAscd']}"
															styleClass="sort_asc" />
														<f:attribute name="#{ctg['id.category.columnName']}"
															value="categoryTypeCode" />
														<f:attribute name="#{ctg['id.category.sortOrder']}"
															value="ascending" />
													</t:commandLink>
												</t:div>
												<t:div styleClass="alignLeft">
													<t:commandLink id="busiunitDscCmdLink"
														onmousedown="if(event.button==1) flagWarn=false;"
														actionListener="#{CategoryControllerBean.getAllSortedCategories}"
														rendered="#{CategoryDataBean.imageRender != 'busiunitDscCmdLink'}">
														<m:div title="#{ctg['title.category.forDsnd']}"
															styleClass="sort_desc" />
														<f:attribute name="#{ctg['id.category.columnName']}"
															value="categoryTypeCode" />
														<f:attribute name="#{ctg['id.category.sortOrder']}"
															value="descending" />
													</t:commandLink>
												</t:div>
											</h:panelGroup>
										</h:panelGrid>
									</f:facet>
									<h:outputText id="valbusiunitCtg"
										value="#{categoryDetails.categoryTypeCode}" />
								</t:column>
								<t:column id="sapCol">
									<f:facet name="header">
										<h:panelGrid id="PRGCMGTPGD151" columns="2">
											<h:outputLabel id="sapLabel" for="sapLabelValue"
												value="#{ctg['label.category.supAppReq']}" />
											<h:panelGroup id="sapLabelValue">
												<t:div styleClass="alignLeft">
													<t:commandLink id="sapAscCmdLink"
														onmousedown="if(event.button==1) flagWarn=false;"
														actionListener="#{CategoryControllerBean.getAllSortedCategories}"
														rendered="#{CategoryDataBean.imageRender != 'sapAscCmdLink'}">
														<m:div title="#{ctg['title.category.forAscd']}"
															styleClass="sort_asc" />
														<f:attribute name="#{ctg['id.category.columnName']}"
															value="supervisorReviewReqIndicator" />
														<f:attribute name="#{ctg['id.category.sortOrder']}"
															value="ascending" />
													</t:commandLink>
												</t:div>
												<t:div styleClass="alignLeft">
													<t:commandLink id="sapDescCmdLink"
														onmousedown="if(event.button==1) flagWarn=false;"
														actionListener="#{CategoryControllerBean.getAllSortedCategories}"
														rendered="#{CategoryDataBean.imageRender != 'sapDescCmdLink'}">
														<m:div title="#{ctg['title.category.forDsnd']}"
															styleClass="sort_desc" />
														<f:attribute name="#{ctg['id.category.columnName']}"
															value="supervisorReviewReqIndicator" />
														<f:attribute name="#{ctg['id.category.sortOrder']}"
															value="descending" />
													</t:commandLink>
												</t:div>
											</h:panelGroup>
										</h:panelGrid>
									</f:facet>
									<h:outputText id="valSARCtg"
										value="#{categoryDetails.supervisorAppReqInd}" />
								</t:column>
								<f:facet name="footer">
									<m:div id="nodata" align="center"
										rendered="#{CategoryDataBean.renderNoDataCategory}">
										<h:outputText id="PRGCMGTOT1242"
											value="#{ref['label.ref.noData']}" />
									</m:div>
								</f:facet>
							</t:dataTable>

							<t:dataScroller id="PROVIDERMDS20120731164811533" pageCountVar="pageCount" pageIndexVar="pageIndex"
								paginator="true" paginatorActiveColumnStyle='font-weight:bold;'
								paginatorMaxPages="3" immediate="false" for="addCategory"
								firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
								rowsCountVar="rowsCount" styleClass="dataScroller">
								<f:facet name="previous">
									<h:outputText id="PRGCMGTOT1243" styleClass="underline"
										value=" #{ref['label.ref.lt']} " rendered="#{pageIndex > 1}"></h:outputText>
								</f:facet>
								<f:facet name="next">
									<h:outputText id="PRGCMGTOT1244" styleClass="underline"
										value=" #{ref['label.ref.gt']} "
										rendered="#{pageIndex < pageCount}"></h:outputText>
								</f:facet>
								<h:outputText id="PRGCMGTOT1245"
									value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
									styleClass="dataScrollerText" rendered="#{rowsCount>0}">
								</h:outputText>
							</t:dataScroller>

							<m:br />
							<m:br />

							<m:div styleClass="moreInfo"
								rendered="#{CategoryDataBean.renderAddCategory || CategoryDataBean.renderEditCategory}">
								<m:div styleClass="moreInfoBar"
									rendered="#{CategoryDataBean.renderAddCategory}">
									<m:div styleClass="infoTitle">
										<h:outputText id="PRGCMGTOT1246"
											value="#{ctg['label.category.addCategory']}" />
									</m:div>

									<m:div styleClass="infoActions">
										<h:panelGroup id="PRGCMGTPGP98">
										<%--Defect Fixed Below--%>
											<%--<h:commandLink id="PRGCMGTCL181" styleClass="strong"
												onmousedown="if(event.button==1) flagWarn=false;"
												action="#{CategoryControllerBean.createCategory}">
												<h:outputText id="PRGCMGTOT1247"
													value="#{ent['label-sw-save']}" />
											</h:commandLink>
											<h:outputText id="PRGCMGTOT1248" escape="false"
												value="#{ref['label.ref.linkSeperator']}" />
											<h:commandLink id="PRGCMGTCL182" styleClass="commandLink"
												onmousedown="if(event.button==1) flagWarn=false;"
												action="#{CategoryControllerBean.resetCategory}">
												<h:outputText id="PRGCMGTOT1249"
													value="#{ent['label-sw-reset']}" />
											</h:commandLink>
											<h:outputText id="PRGCMGTOT1250" escape="false"
												value="#{ref['label.ref.linkSeperator']}" />
											<h:commandButton id="PRGCMGTCB47" onclick="flagWarn=true;"
												style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:44px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"
												value="#{ent['label-sw-cancel']}"
												action="#{CategoryControllerBean.cancelCategory}" /> --%>
												
										<t:commandLink styleClass="strong" id="PRGCMGTCL181"
													   rendered="#{CategoryControllerBean.controlRequired}"
											           action="#{CategoryControllerBean.createCategory}"
													   onmousedown="javascript:flagWarn=false;focusThis('add');">
											<h:outputText id="PRGCMGTOT1247"
												          value="#{ent['label-sw-save']}"></h:outputText>
										</t:commandLink>
										<h:outputText id="PRGCMGTOT1045" escape="false"
													  value="#{ref['label.ref.linkSeperator']}"
													  rendered="#{CategoryControllerBean.controlRequired}" />
										<t:commandLink id="PRGCMGTCL182"
													   rendered="#{CategoryControllerBean.controlRequired}"
											 		   action="#{CategoryControllerBean.resetCategory}"
											      	   onmousedown="javascript:flagWarn=false;">
											<h:outputText id="PRGCMGTOT1249"
												          value="#{ent['label-sw-reset']}"></h:outputText>
										</t:commandLink>
										<h:outputText id="PRGCMGTOT1250" escape="false"
											          value="#{ref['label.ref.linkSeperator']}"
											          rendered="#{CategoryControllerBean.controlRequired}" />


										<t:commandLink id="PRGCMGTCB47" onclick="flagWarn=true;"
											           action="#{CategoryControllerBean.cancelCategory}">
											<h:outputText id="PRGCMGTOT1212"
												 		  value="#{ent['label-sw-cancel']}"></h:outputText>
										</t:commandLink>
										</h:panelGroup>
									</m:div>

								</m:div>
								<m:div styleClass="moreInfoBar"
									rendered="#{CategoryDataBean.renderEditCategory}">
									<m:div styleClass="infoTitle">
										<h:outputText id="PRGCMGTOT1251"
											value="#{ctg['label.category.editCategory']}" />
									</m:div>

									<m:div styleClass="infoActions">
										<%--<h:panelGroup id="PRGCMGTPGP99">
											<h:panelGroup id="PRGCMGTPGP100"> --%>

												<%--<h:commandLink id="PRGCMGTCL183" styleClass="strong" onmousedown="if(event.button==1) flagWarn=false;"												    rendered="#{CategoryControllerBean.controlRequired}"												   													action="#{CategoryControllerBean.updateCategory}">
													<h:outputText id="PRGCMGTOT1252" value="#{ent['label-sw-save']}" />
												</h:commandLink>--%>

												<h:commandButton id="cfvalueforSave"
													style="font-weight:bold;COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:34px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal;position:relative;top:2px"
													onmousedown="if(event.button==1) flagWarn=false;"
													disabled="#{!CategoryControllerBean.controlRequired}"
													value="#{ent['label-sw-save']}"
													action="#{CategoryControllerBean.updateCategory}" />
											<%--	<h:commandLink styleClass="strong" id="cfvalueforSave"
													   rendered="#{CategoryControllerBean.controlRequired}"
													   action="#{CategoryControllerBean.updateCategory}"
													   onclick="javascript:flagWarn=false;">
											<h:outputText id="PRGCMGTOT124701"
												value="#{ent['label-sw-save']}"></h:outputText>
										</h:commandLink> --%>

												<h:outputText id="PRGCMGTOT1253" escape="false"
												style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:3px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: top;word-spacing: normal"
													value="#{ref['label.ref.linkSeperator']}" />
												<%--<h:commandLink id="PRGCMGTCL184" styleClass="commandLink"													onmousedown="if(event.button==1) flagWarn=false;"													 rendered="#{CategoryControllerBean.controlRequired}"													action="#{CategoryControllerBean.resetEditedCategory}">
													<h:outputText id="PRGCMGTOT1254" value="#{ent['label-sw-reset']}" />
												</h:commandLink>--%>
												<h:commandButton id="PRGCMGTCB48"
													style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:35px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal;position:relative;top:2px"
													onmousedown="if(event.button==1) flagWarn=false;"
													disabled="#{!CategoryControllerBean.controlRequired}"
													value="#{ent['label-sw-reset']}"
													action="#{CategoryControllerBean.resetEditedCategory}" />  
													
												<%--<h:commandLink id="PRGCMGTCB48"
											               rendered="#{CategoryControllerBean.controlRequired}"
											               action="#{CategoryControllerBean.resetCategory}"
											               onmousedown="javascript:flagWarn=false;">
											<h:outputText id="PRGCMGTOT124901"
												value="#{ent['label-sw-reset']}"></h:outputText>
										</h:commandLink>--%>

												<h:outputText id="PRGCMGTOT1255" escape="false"
												style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:3px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: top;word-spacing: normal"
													value="#{ref['label.ref.linkSeperator']}" />
											<%--</h:panelGroup> --%>

											<%--<h:panelGroup id="PRGCMGTPGP101">--%>
												<h:commandButton id="PRGCMGTCB49"
													style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:40px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal;position:relative;top:2px"
													onclick="flagWarn=false; if (!(confirm('Are you sure you want to Delete?'))) return(false);"
													disabled="#{!CategoryControllerBean.controlRequired  || !CategoryControllerBean.controlRequired}"
													value="#{ent['label-sw-delete']}"
													action="#{CategoryControllerBean.deleteCategory}" /> 
													
												<%--	<h:commandLink id = "PRGCMGTCB49"
														   rendered="#{CategoryControllerBean.controlRequired}"
														   action = "#{CategoryControllerBean.deleteCategory }"
														   onclick="javascript:flagWarn=false; if (!(confirm('Are you sure you want to Delete?'))) return(false);" >
												<h:outputText id="PRGCMGTOT1249011"
															value="#{ent['label-sw-delete']}"></h:outputText>
											</h:commandLink> --%>

												<h:outputText id="PRGCMGTOT1256" escape="false"
												style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:3px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: top;word-spacing: normal"
													value="#{ref['label.ref.linkSeperator']}" />

												<h:commandButton id="auditlog"
													style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:55px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal;position:relative;top:2px"
													onmousedown="javascript:flagWarn=false;focusThis(this.id);"
													value="#{ref['label.ref.auditLog']}"
													action="#{CategoryControllerBean.doAuditKeyListOperation}" />
													
													<%--<h:commandLink id = "auditlog"
														   rendered="#{CategoryControllerBean.controlRequired}"
														   action = "#{CategoryControllerBean.doAuditKeyListOperation}"
														   onmousedown="javascript:flagWarn=false;" >
												<h:outputText id="PRGCMGTOT1249021"
															value="#{ref['label.ref.auditLog']}"></h:outputText>
											</h:commandLink>--%>


												<%--<h:commandButton id="hiddenButton"																	     			style="display:none" 									     			action="#{CategoryControllerBean.deleteCategory}" />
									 			<m:anchor style="cursor:hand; color:#0000ff" 
									 				onclick="confirmDelete('CategoryForm','hiddenButton');">
								     				<h:outputText id="PRGCMGTOT1257" value="#{ent['label-sw-delete']}" />
								     			</m:anchor>--%>
												<h:outputText id="PRGCMGTOT1258" escape="false"
												style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:3px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: top;word-spacing: normal"
													value="#{ref['label.ref.linkSeperator']}" />
												<%--Defect Fixed Below --%>
												<%--<h:commandButton id="PRGCMGTCB50"													style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:44px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"													onclick="flagWarn=false;"													value="#{ent['label-sw-cancel']}"													action="#{CategoryControllerBean.cancelCategory}" /> --%>
												<h:commandButton id="editCategoryCancel"
													onclick="flagWarn=true;"
													action="#{CategoryControllerBean.cancelCategory}"
													style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:50px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal; position:relative;top:2px"
													value="#{ent['label-sw-cancel']}"> 
												</h:commandButton>
												<%--<h:commandLink id="editCategoryCancel" onclick="flagWarn=true;"
															   action="#{CategoryControllerBean.cancelCategory}">
														<h:outputText id="PRGCMGTOT12490451"
																	  value="#{ent['label-sw-cancel']}"></h:outputText>
											  </h:commandLink>--%>
											<%--</h:panelGroup> --%>
										<%--</h:panelGroup>--%>
									
									</m:div>

								</m:div>

								<m:div styleClass="moreInfoContent">
									<m:div styleClass="padb">
										<m:div styleClass="msgBox"
											rendered="#{CategoryDataBean.showSucessMessage}">
											<h:outputText id="PRGCMGTOT1259"
												value="#{ent['label-sw-success']}" />
										</m:div>
										<m:table id="PROVIDERMMT20120731164811534" cellspacing="0" width="94%">
											<m:tr>
												<m:td>
													<m:div styleClass="padb">
														<h:outputLabel id="active" for="strOrCnt"
															value="#{ctg['label.category.active']}" />
														<m:br />
														
														<%--disabled attribute is added below for fixing the defect ESPRD00802214 on 03/07/2012--%>
														
														<h:selectOneRadio id="strOrCnt" enabledClass="black"
															value="#{CategoryDataBean.categoryVO.activeIndicator}"
															disabled="#{!CategoryControllerBean.controlRequired}">
															<f:selectItem itemLabel="#{ref['label.ref.yes']}"
																itemValue="Yes" />
															<f:selectItem itemLabel="#{ref['label.ref.no']}"
																itemValue="No" />
														</h:selectOneRadio>
													</m:div>
												</m:td>
												<m:td>
													<m:div styleClass="padb">
														<m:span styleClass="required">
															<h:outputText id="PRGCMGTOT1260"
																value="#{ref['label.ref.reqSymbol']}" />
														</m:span>
														<h:outputLabel id="categoryDesc" for="descVal"
															value="#{ref['label.ref.description']}" />
														<m:br />
														
														<%--disabled attribute is modified below for fixing the defect ESPRD00802214 on 03/07/2012--%>
														
														<%--<h:inputText id="descVal" size="40" maxlength="50"
															disabled="#{CategoryDataBean.categoryVO.inactive}"
															value="#{CategoryDataBean.categoryVO.categoryDesc}" /> --%>
														<h:inputText id="descVal" size="40" maxlength="50"
																disabled="#{!CategoryControllerBean.controlRequired}"
																value="#{CategoryDataBean.categoryVO.categoryDesc}" />
														<m:br />
														<h:message id="PRGCMGTM175" for="categoryDesc"
															showDetail="true" styleClass="color: red" />
													</m:div>
												</m:td>
												<m:td>
													<m:div styleClass="padb">
														<h:outputLabel id="categoryTypeCode" for="busiUnit"
															value="#{ref['label.ref.businessunit']}" />
														<m:br />
														
														<%--disabled attribute is added below for fixing the defect ESPRD00802214 on 03/07/2012--%>
														
														<h:selectOneMenu id="busiUnit"
															value="#{CategoryDataBean.categoryVO.categoryTypeCode}"
															disabled="#{!CategoryControllerBean.controlRequired}">
															<f:selectItem itemLabel="Please Select One" itemValue="" />
															<f:selectItems
																value="#{CategoryDataBean.categoryTypeCodeListME}" />
														</h:selectOneMenu>
														<m:br />
														<h:message id="PRGCMGTM176" for="categoryTypeCode"
															showDetail="true" styleClass="color: red" />
													</m:div>
												</m:td>
												<m:td>
													<m:div styleClass="padb">
														<h:outputLabel id="sap" for="sapVal"
															value="#{ctg['label.category.supAppReq']}" />
														<m:br />
														
														<%--disabled attribute is modified below for fixing the defect ESPRD00802214 on 03/07/2012--%>
														
														<%--<h:selectOneRadio id="sapVal" enabledClass="black"
															disabled="#{CategoryDataBean.categoryVO.inactive}"
															value="#{CategoryDataBean.categoryVO.supervisorAppReqInd}"> --%>
															<h:selectOneRadio id="sapVal" enabledClass="black"
																disabled="#{!CategoryControllerBean.controlRequired}"
																value="#{CategoryDataBean.categoryVO.supervisorAppReqInd}">
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

										<m:div styleClass="tabs">
											<t:panelTabbedPane width="98%" id="addTab"
												onclick="flagWarn=false;"
												selectedIndex="#{CategoryDataBean.tabIndex}">
												<t:panelTab id="tab1"
													label="#{ctg['label.category.subjects']}" rendered="true">
													<jsp:include
														page="/jsp/maintaincategory/inc_maintainCategorySubj.jsp" />
												</t:panelTab>

												<t:panelTab id="tab2"
													label="#{ctg['label.category.customFields']}"
													rendered="true">
													<jsp:include
														page="/jsp/maintaincategory/inc_maintainCategoryCust.jsp" />
												</t:panelTab>

												<t:panelTab id="tab4"
													label="#{ctg['label.category.templates']}" rendered="true">
													<jsp:include
														page="/jsp/maintaincategory/inc_maintainCategoryTemplate.jsp" />
												</t:panelTab>

												<t:panelTab id="tab3"
													label="#{ctg['label.category.defaults']}" rendered="true">
													<jsp:include
														page="/jsp/maintaincategory/inc_maintainCategoryDefs.jsp" />
												</t:panelTab>



											</t:panelTabbedPane>
										</m:div>

										<m:br />
										<m:br />
										<%--  Audit Log 
										<m:div rendered="#{CategoryDataBean.renderEditCategory}">
											<f:subview id="auditlogDetails">
												<jsp:include page="/jsp/maintaincategory/inc_maintainCategoryAuditLog.jsp" />
											</f:subview>
										</m:div>--%>
										<m:div
											rendered="#{not empty CategoryDataBean.categoryVO.categorySK}">
											<f:subview id="CategoryAuditInc">
												<m:div rendered="#{CategoryDataBean.auditLogFlag}">
													<audit:auditTableSet id="CategoryAuditId"
														value="#{CategoryDataBean.categoryVO.auditKeyList}"
														numOfRecPerPage="10"></audit:auditTableSet>
												</m:div>
											</f:subview>


										</m:div>
									</m:div>
								</m:div>
							</m:div>
						</m:section>
					</m:div>
				</m:div>
			</m:div>
		</m:body>
	</h:form>

	<script>
		var thisForm = 'view<portlet:namespace/>:CategoryForm';
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
   		var focusPage = '#'+document.getElementById(thisForm+':focusId').value;
			if(focusPage != '' && focusPage != '#') {				
				document.location.href=focusPage;
			}
   		}
	</script>


	<%-- var thisForm : stores the form reference. Form reference is use to get  the computed  role stored in hidden form field.
         FlagRole : Indicator to store role information .Stores 'False' for inquire only users and 'True' for other user.
	--%>
	<script type="text/JavaScript">

   var thisForm = 'view<portlet:namespace/>:CategoryForm';

   flagRole =((typeof (document.getElementById(thisForm+': controlRequiredForCategory')) !=  'undefined') && document.getElementById(thisForm+':controlRequiredForCategory') != null ?  document.getElementById(thisForm+':controlRequiredForCategory').value:true);

</script>
	</body>
</f:view>


