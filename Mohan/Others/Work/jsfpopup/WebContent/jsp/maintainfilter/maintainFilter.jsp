<%-- 
  - Author(s): Wipro
  - Date: Fri Oct 05 14:53:56 IST 2007
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

<f:loadBundle
	basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_ContactMaintainFilter"
	var="filter" />

<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.ProgramMessages"
	var="ref" />

<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.EnterpriseMessages"
	var="ent" />

<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.GlobalAuditLabels"
	var="msgAudit" />

<f:view>
	<t:saveState id="CMGTTOMSS455" value="#{FilterDataBean}" />
	<t:saveState id="CMGTTOMSS456" value="#{FilterDataBean.filterVO}" />
	<%-- <t:saveState id="CMGTTOMSS457" value="#{FilterDataBean.filterVO.categoryFilterVO}" />--%>

	<SCRIPT type="text/javascript">
		
		
		
		/*Small save Big Save start*/
		frmId = 'view<portlet:namespace/>:FilterForm';
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
      
       function confirmDelete(formID,id) 
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
				var hiddenValue = document.getElementById('view<portlet:namespace/>:FilterForm:auditlogDetails:'+id);
			    
				if ((hiddenValue == null) ||(hiddenValue == '')|| (hiddenValue.length == 0)) {
					hideMe('audit_plus');
				} else if ((hiddenValue.value == 'false')) 
				{
					hideMe('audit_plus');
				} 
					
			}
	
	</SCRIPT>

	<%-- Small save Big Save start --%>
	<f:subview id="provService">
		<jsp:include page="/jsp/commonEntities/commonScripts.jsp" />
	</f:subview>
	<%-- Small save Big Save ends --%>

	<body onload="renderAudit('audit_open');"	>
	<h:inputHidden id="PRGCMGTIH28"		value="#{FilterControllerBean.loadUserPermission}"></h:inputHidden>
	<h:form id="FilterForm">
	
<h:inputHidden id="controlRequiredForFilter"		value="#{FilterControllerBean.controlRequired}"></h:inputHidden>
		
		<m:div>
			<m:div styleClass="floatContainer">
				<m:div style="width:100%">
					<m:div styleClass="moreInfoBar">
						<m:div styleClass="infoTitle" align="left">
							<h:outputText id="PRGCMGTOT1470"								value="#{ref['label.ref.requiredField']}" style="color:maroon" />
						</m:div>
					</m:div>
					<h:messages id="PRGCMGTMS20" layout="table" showDetail="true"						showSummary="false" style="color: red" />
					<m:br clear="all" />
					<m:table id="PROVIDERMMT20120731164811600" styleClass="tableBar" width="100%">
						<m:tr>
							<m:td styleClass="tableAction">
								<h:commandButton id="PRGCMGTCB67" styleClass="formBtn"									onmousedown="if(event.button==1) flagWarn=false;"									disabled="#{!FilterControllerBean.controlRequired}"									value="#{filter['label.filter.addFilter']}"									action="#{FilterControllerBean.addFilter}" />
							</m:td>
						</m:tr>
					</m:table>
					<t:dataTable cellspacing="0" styleClass="dataTable"						columnClasses="columnClass" headerClass="headerClass"						footerClass="footerClass" rowClasses="row_alt,row" id="filterTB"						width="100%" var="filterList"						first="#{FilterDataBean.filterstartIndex}"						value="#{FilterDataBean.filterArrayList}" rows="10"		rowIndexVar="rowIndex"				onmouseover="return tableMouseOver(this, event);"						onmouseout="return tableMouseOut(this, event);">

						<h:column id="descriptioncol">
							<f:facet name="header">
								<h:panelGrid id="PRGCMGTPGD166" columns="2">
									<h:outputLabel id="PRGCMGTOLL621" for="panelGroup12"										value="#{filter['label.filter.description']}" />
									<h:panelGroup id="panelGroup12">
										<t:div style="width x;align=left">

											<t:commandLink id="descAscCmdLink"												style="text-decoration: none;"												onmousedown="if(event.button==1) flagWarn=false;"												actionListener="#{FilterControllerBean.getAllSortedFilters}"												rendered="#{FilterDataBean.imageRender != 'descAscCmdLink'}">

												<m:div title="#{ref['label.ref.forAscending']}"
													styleClass="sort_asc" />
												<f:attribute name="columnName" value="filterName" />
												<f:attribute name="sortOrder" value="asc" />
											</t:commandLink>

										</t:div>
										<t:div style="width x;align=left">

											<t:commandLink id="descDescCmdLink"												style="text-decoration: none;"												onmousedown="if(event.button==1) flagWarn=false;"												actionListener="#{FilterControllerBean.getAllSortedFilters}"												rendered="#{FilterDataBean.imageRender != 'descDescCmdLink'}">

												<m:div title="#{ref['label.ref.forDescending']}"
													styleClass="sort_desc" />
												<f:attribute name="columnName" value="filterName" />
												<f:attribute name="sortOrder" value="desc" />
											</t:commandLink>

										</t:div>
									</h:panelGroup>
								</h:panelGrid>
							</f:facet>
							<t:commandLink id="edit"								onmousedown="if(event.button==1) flagWarn=false;"								immediate="true"								action="#{FilterControllerBean.getFilterDetails}">
								<h:outputText id="valueElement1"									value="#{filterList.filterName}" />
								<f:param name="filterName" value="#{filterList.filterName}"></f:param>
								<f:param name="filterType" value="#{filterList.filterType}"></f:param>
								<f:param name="rowIndex" value="#{rowIndex}"></f:param>

							</t:commandLink>
						</h:column>

						<%-- Snippet for  displaying  No Data --%>

						<f:facet name="footer">
							<m:div id="nodata" rendered="#{FilterDataBean.noData}"
								align="center">
								<h:outputText id="PRGCMGTOT1471"									value="#{ref['label.ref.noData']}"></h:outputText>
							</m:div>
						</f:facet>

					</t:dataTable>
					<t:dataScroller id="CMGTTOMDS52" pageCountVar="pageCount" pageIndexVar="pageIndex"						onclick="flagWarn=false;"						paginator="true" paginatorActiveColumnStyle='font-weight:bold;'						paginatorMaxPages="3" immediate="false" for="filterTB"						firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"						rowsCountVar="rowsCount"						style="float:right;position:relative;bottom:-6px">
						<f:facet name="previous">
							<h:outputText id="PRGCMGTOT1472"								style="text-decoration:underline;"								value=" #{ref['label.ref.lt']} " rendered="#{pageIndex > 1}"></h:outputText>
						</f:facet>
						<f:facet name="next">
							<h:outputText id="PRGCMGTOT1473"								style="text-decoration:underline;"								value=" #{ref['label.ref.gt']} "								rendered="#{pageIndex < pageCount}"></h:outputText>
						</f:facet>
						<h:outputText id="PRGCMGTOT1474"							value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"							style="float:left;position:relative;bottom:-6px;font-weight:bold;"							rendered="#{rowsCount>0}">

						</h:outputText>
					</t:dataScroller>

					<m:br />
					<m:br />
					<m:div styleClass="moreInfo"
						rendered="#{FilterDataBean.showNewFilterBar || FilterDataBean.showEditFilterBar}">
						<m:div styleClass="moreInfoBar"
							rendered="#{FilterDataBean.showNewFilterBar}">
							<m:div styleClass="infoTitle">
								<h:outputText id="PRGCMGTOT1475"									value="#{filter['label.filter.newfilter']}" />
							</m:div>

							<m:div styleClass="infoActions">
								<h:panelGroup id="PRGCMGTPGP112">

									<m:div>
										<t:commandLink id="PRGCMGTCL217" styleClass="strong"											rendered="#{FilterControllerBean.controlRequired}"											onmousedown="if(event.button==1) flagWarn=false;"											action="#{FilterControllerBean.createFilter}">
											<h:outputText id="PRGCMGTOT1476"												value="#{ent['label-sw-save']}" />
										</t:commandLink>

										<h:outputText id="PRGCMGTOT1477" escape="false"											value="#{ref['label.ref.linkSeperator']}"											rendered="#{FilterControllerBean.controlRequired}" />
										<t:commandLink id="PRGCMGTCL218" styleClass="commandLink"											rendered="#{FilterControllerBean.controlRequired}"											onmousedown="if(event.button==1) flagWarn=false;"											action="#{FilterControllerBean.resetFilter}">
											<h:outputText id="PRGCMGTOT1478"												value="#{ent['label-sw-reset']}" />
										</t:commandLink>


										<h:outputText id="PRGCMGTOT1479" escape="false"											value="#{ref['label.ref.linkSeperator']}"											rendered="#{FilterControllerBean.controlRequired}" />
										<h:commandButton id="PRGCMGTCB68"											style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:44px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"											value="#{ent['label-sw-cancel']}"											action="#{FilterControllerBean.cancelFilter}">
										</h:commandButton>

									</m:div>
								</h:panelGroup>
							</m:div>

						</m:div>
						<m:div styleClass="moreInfoBar"
							rendered="#{FilterDataBean.showEditFilterBar}">
							<m:div styleClass="infoTitle">
								<h:outputText id="PRGCMGTOT1480"									value="#{filter['label.filter.editfilter']}" />
							</m:div>

							<m:div styleClass="infoActions">
								<h:panelGroup id="PRGCMGTPGP113">

									<m:div>
										<%--<h:commandLink id="PRGCMGTCL219" styleClass="strong"										    rendered="#{FilterControllerBean.controlRequired}"											onmousedown="if(event.button==1) flagWarn=false;"											action="#{FilterControllerBean.updateFilter}">
											<h:outputText id="PRGCMGTOT1481" value="#{ent['label-sw-save']}" />
										</h:commandLink>--%>
										<h:commandButton id="PRGCMGTCB69"											onmousedown="if(event.button==1) flagWarn=false;"											style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;font-weight:bold;WIDTH:34px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"											disabled="#{!FilterControllerBean.controlRequired}"											value="#{ent['label-sw-save']}"											action="#{FilterControllerBean.updateFilter}" />
										<h:outputText id="PRGCMGTOT1482" escape="false"											value="#{ref['label.ref.linkSeperator']}" />

										<%--<h:commandLink id="PRGCMGTCL220" styleClass="commandLink"										    rendered="#{FilterControllerBean.controlRequired}"											onmousedown="if(event.button==1) flagWarn=false;"											action="#{FilterControllerBean.resetEditedFilter}">
											<h:outputText id="PRGCMGTOT1483" value="#{ent['label-sw-reset']}" />
										</h:commandLink>--%>

										<h:commandButton id="PRGCMGTCB70"											onmousedown="if(event.button==1) flagWarn=false;"											style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:32px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"											disabled="#{!FilterControllerBean.controlRequired}"											value="#{ent['label-sw-reset']}"											action="#{FilterControllerBean.resetEditedFilter}" />

										<h:outputText id="PRGCMGTOT1484" escape="false"											value="#{ref['label.ref.linkSeperator']}" />

										<h:commandButton id="PRGCMGTCB71"											style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:37px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"											onclick="flagWarn=false; if (!(confirm('Are you sure you want to Delete?'))) return(false);"											disabled="#{!FilterControllerBean.controlRequired || !FilterControllerBean.controlRequiredFOrDelete}"											value="#{ent['label-sw-delete']}"											action="#{FilterControllerBean.deleteFilter}" />

										<%--		<h:commandButton  id="hiddenButton"																	     style="display:none"									     action="#{FilterControllerBean.deleteFilter}">
								       </h:commandButton>
								     <m:anchor style="cursor:hand; color:#0000ff" onclick="confirmDelete('form1','hiddenButton');">
								     <h:outputText id="PRGCMGTOT1485" value="#{ent['label-sw-delete']}"></h:outputText> </m:anchor>
			
								 	<h:commandLink id="PRGCMGTCL221" styleClass="commandLink"											value="#{ent['label-sw-delete']}"											action="#{FilterControllerBean.deleteFilter}" /> --%>
										<h:outputText id="PRGCMGTOT1486" escape="false"											value="#{ref['label.ref.linkSeperator']}" />


										<h:commandButton id="PRGCMGTCB72"											onmousedown="if(event.button==1) flagWarn=false;"											style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:54px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"											value="Audit Log"											action="#{FilterControllerBean.doAuditFunctions}" />

										<h:outputText id="PRGCMGTOT1487" escape="false"											value="#{ref['label.ref.linkSeperator']}" />

										<h:commandButton id="PRGCMGTCB73"											style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:44px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"											value="#{ent['label-sw-cancel']}"											action="#{FilterControllerBean.cancelFilter}" />
									</m:div>
								</h:panelGroup>
							</m:div>

						</m:div>

						<m:div styleClass="moreInfoContent">
							<m:div styleClass="padb">
								<m:div styleClass="msgBox"
									rendered="#{FilterDataBean.showSucessMessage}">
									<h:outputText id="PRGCMGTOT1488"										value="#{ent['label-sw-success']}" />
								</m:div>
								<m:table id="PROVIDERMMT20120731164811601" width="50%">
									<m:tr>
										<m:td id="descriptionfilter">
											<m:span styleClass="required">
												<h:outputText id="PRGCMGTOT1489"													value="#{filter['label.Action.required']}" />
											</m:span>
											<h:outputLabel id="PRGCMGTOLL622" for="descriptionOfFilter"												value="#{filter['label.filter.filterDescription']}"></h:outputLabel>
											<m:br></m:br>
											<h:inputText id="descriptionOfFilter" size="10"												maxlength="50" rendered="#{FilterDataBean.showNewFilterBar}"												value="#{FilterDataBean.filterVO.filterName}">
											</h:inputText>
											<h:outputText id="PRGCMGTOT1490"												rendered="#{FilterDataBean.showEditFilterBar}"												value="#{FilterDataBean.filterVO.filterName}">
											</h:outputText>
											<m:br></m:br>
											<h:message id="PRGCMGTM213" for="descriptionOfFilter"												showDetail="true" styleClass="errorMessage" />
										</m:td>
										<m:td id="labelfiltertype">
											<m:span styleClass="required">
												<h:outputText id="PRGCMGTOT1491"
													value="#{filter['label.Action.required']}" />
											</m:span>
											<h:outputLabel id="PRGCMGTOLL623" for="typeForFilter">
												<h:outputText value="#{filter['label.filter.type']}"
													id="typeForFilter11"></h:outputText>
											</h:outputLabel>
											<m:br></m:br>
											<h:selectOneMenu id="typeForFilter"
												value="#{FilterDataBean.filterVO.filterType}"
												immediate="true"
												onchange="flagWarn=false; this.form.submit();"
												valueChangeListener="#{FilterControllerBean.changeFilterType}"
												disabled="#{!FilterControllerBean.controlRequired}">
												<f:selectItem itemValue="" itemLabel="" />
												<f:selectItems value="#{FilterDataBean.filterTypeArrayList}" />
											</h:selectOneMenu>
											<m:br></m:br>
											<h:message id="PRGCMGTM214" for="typeForFilter"												showDetail="true" styleClass="errorMessage" />
										</m:td>
										<m:td id="filteraction">
											<m:span styleClass="required">
												<h:outputText id="PRGCMGTOT1492"													value="#{filter['label.Action.required']}" />
											</m:span>
											<h:outputLabel id="PRGCMGTOLL624" for="actionForFilter">
												<h:outputText value="#{filter['label.filter.action']}"
													id="actionForFilter11"></h:outputText>
											</h:outputLabel>
											<m:br></m:br>
											<h:selectOneRadio id="actionForFilter" enabledClass="black"												disabled="#{!FilterControllerBean.controlRequired}"												value="#{FilterDataBean.filterVO.filterScope}">
												<f:selectItem itemLabel="#{filter['label.filter.include']}"
													itemValue="I"></f:selectItem>
												<f:selectItem itemLabel="#{filter['label.filter.exclude']}"
													itemValue="E"></f:selectItem>
											</h:selectOneRadio>
											<h:message id="PRGCMGTM215" for="actionForFilter"												showDetail="true" styleClass="errorMessage" />
										</m:td>
									</m:tr>
								</m:table>
								<h:panelGroup id="groupList">
									<m:div rendered="#{FilterDataBean.showCategoryList}">
										<h:outputLabel id="PRGCMGTOLL625" for="crCategoryForFilter">
											<h:outputText value="#{filter['label.filter.crCategory']}"												id="crCategoryForFilter"></h:outputText>
										</h:outputLabel>
										<m:table id="PROVIDERMMT20120731164811602">
											<m:tr>
												<m:td styleClass="padb">
													<t:htmlTag value="h4">
														<h:outputLabel id="PRGCMGTOLL626"
															for="availableCR">
															<h:outputText value="#{filter['label.filter.available']}"
																id="availableCrForFilter" />
														</h:outputLabel>
													</t:htmlTag>
												</m:td>
												<m:td styleClass="padb">
													<h:outputText id="PRGCMGTOT1493" escape="false"														value="#{ref['label.ref.singleSpace']}" />
												</m:td>
												<m:td styleClass="padb">
													<t:htmlTag value="h4">
														<h:outputLabel id="PRGCMGTOLL627"
															for="selectedCR">
															<h:outputText value="#{filter['label.filter.selected']}"
																id="selectedCrForFilter" />
														</h:outputLabel>
													</t:htmlTag>
												</m:td>
											</m:tr>
											<m:tr>
												<m:td valign="top">
													<t:selectManyListbox														disabled="#{!FilterControllerBean.controlRequired}"														size="5" id="availableCR"														value="#{FilterDataBean.selectedList}">
														<f:selectItems value="#{FilterDataBean.tempAvailableList}" />
													</t:selectManyListbox>
												</m:td>
												<m:td>
													<m:br />
													<m:table id="PROVIDERMMT20120731164811603">
														<m:tr>
															<m:td align="center">
																<h:commandButton id="PRGCMGTCB74" styleClass="formBtn"																	value="#{filter['label.filter.rShift']}"																	action="#{FilterControllerBean.moveSelectedList}"																	disabled="#{!FilterControllerBean.controlRequired}"																	onclick="flagWarn=false">
																</h:commandButton>
															</m:td>
														</m:tr>
														<m:tr>
															<m:td align="center">
																<h:commandButton id="PRGCMGTCB75" styleClass="formBtn"																	value="#{filter['label.filter.lShift']}"																	action="#{FilterControllerBean.removeSelectedList}"																	disabled="#{!FilterControllerBean.controlRequired}"																	onclick="flagWarn=false">
																</h:commandButton>
															</m:td>
														</m:tr>
													</m:table>
												</m:td>
												<m:td>
													<t:selectManyListbox														disabled="#{!FilterControllerBean.controlRequired}"														size="5" id="selectedCR"														value="#{FilterDataBean.removedList}">
														<f:selectItems
															value="#{FilterDataBean.filterVO.categoryFilterVO}" />
													</t:selectManyListbox>
												</m:td>
											</m:tr>
										</m:table>
									</m:div>

									<m:div styleClass="" rendered="#{FilterDataBean.showCaseList}">
										<h:outputLabel id="PRGCMGTOLL628" for="caseTypesForFilter">
											<h:outputText value="#{filter['label.filter.caseTypes']}"												id="caseTypesForFilter"></h:outputText>
										</h:outputLabel>
										<m:table id="PROVIDERMMT20120731164811604">
											<m:tr>
												<m:td styleClass="padb">
													<t:htmlTag value="h4">
														<h:outputLabel id="PRGCMGTOLL629"															for="availableCase">
															<h:outputText value="#{filter['label.filter.available']}"																id="availableForFilter" />
														</h:outputLabel>
													</t:htmlTag>
												</m:td>
												<m:td styleClass="padb">
													<h:outputText id="PRGCMGTOT1494" escape="false"														value="#{ref['label.ref.singleSpace']}" />
												</m:td>
												<m:td styleClass="padb">
													<t:htmlTag value="h4">
														<h:outputLabel id="PRGCMGTOLL630"															for="selectedCase">
															<h:outputText value="#{filter['label.filter.selected']}"																id="selectedCaseForFilter" />
														</h:outputLabel>
													</t:htmlTag>
												</m:td>
											</m:tr>
											<m:tr>
												<m:td valign="top">
													<t:selectManyListbox														disabled="#{!FilterControllerBean.controlRequired}"														size="5" id="availableCase"														value="#{FilterDataBean.selectedList}">
														<f:selectItems value="#{FilterDataBean.tempAvailableList}" />
													</t:selectManyListbox>
												</m:td>
												<m:td>
													<m:br />
													<m:table id="PROVIDERMMT20120731164811605">
														<m:tr>
															<m:td align="center">
																<h:commandButton id="PRGCMGTCB76" styleClass="formBtn"																	value="#{filter['label.filter.rShift']}"																	action="#{FilterControllerBean.moveSelectedCaseList}"																	disabled="#{!FilterControllerBean.controlRequired}"																	onclick="flagWarn=false">
																</h:commandButton>
															</m:td>
														</m:tr>
														<m:tr>
															<m:td align="center">
																<h:commandButton id="PRGCMGTCB77" styleClass="formBtn"																	value="#{filter['label.filter.lShift']}"																	action="#{FilterControllerBean.removeSelectedCaseList}"																	disabled="#{!FilterControllerBean.controlRequired}"																	onclick="flagWarn=false">
																</h:commandButton>
															</m:td>
														</m:tr>
													</m:table>
												</m:td>
												<m:td>
													<t:selectManyListbox														disabled="#{!FilterControllerBean.controlRequired}"														size="5" id="selectedCase"														value="#{FilterDataBean.removedList}">
														<f:selectItems
															value="#{FilterDataBean.filterVO.caseTypeFilterVO}" />
													</t:selectManyListbox>
												</m:td>
											</m:tr>
										</m:table>


									</m:div>
								</h:panelGroup>
							</m:div>

							<%-- Audit Logging in Detail  --%>

							<m:div rendered="#{not empty FilterDataBean.filterVO.filterName}">
								<f:subview id="auditlogFilterDetails">
									<m:div rendered="#{FilterDataBean.auditLogFlagforFilter}">
										<audit:auditTableSet id="auditlogFilterId"
											value="#{FilterDataBean.filterVO.auditKeyList}"
											numOfRecPerPage="10"></audit:auditTableSet>
									</m:div>
								</f:subview>
							</m:div>

						</m:div>
					</m:div>
				</m:div>
			</m:div>
		</m:div>
	</h:form>

	<%-- var thisForm : stores the form reference. Form reference is use to get  the computed  role stored in hidden form field.
         FlagRole : Indicator to store role information .Stores 'False' for inquire only users and 'True' for other user.
	--%>
	<script type="text/JavaScript">

   var thisForm = 'view<portlet:namespace/>:FilterForm';

   flagRole =((typeof (document.getElementById(thisForm+': controlRequiredForFilter) !=  'undefined')) && document.getElementById(thisForm+':controlRequiredForFilter) != null ?  document.getElementById(thisForm+':controlRequiredForFilter).value:true);

</script>
	</body>
</f:view>
