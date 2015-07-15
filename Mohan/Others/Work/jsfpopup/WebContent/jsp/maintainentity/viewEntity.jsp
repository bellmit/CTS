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


<script type="text/javascript">
/* For Datatable onmouseover and onmouseout*/
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
</script>

<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.ProgramMessages"
	var="ref" />

<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.EnterpriseMessages"
	var="ent" />

<f:loadBundle
	basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_CMMaintainEntity"
	var="msg" />



<t:saveState id="CMGTTOMSS444" value="#{CMEntityMaintainDataBean}"></t:saveState>
<t:saveState id="CMGTTOMSS445"
	value="#{CMEntityMaintainDataBean.duplicateEntityList}"></t:saveState>
<t:saveState id="CMGTTOMSS446"
	value="#{CMEntityMaintainDataBean.duplicateCMEnityDetailVO}"></t:saveState>
<t:saveState id="CMGTTOMSS447"
	value="#{CMEntityMaintainDataBean.renderViewEntityDetail}"></t:saveState>
<t:saveState id="CMGTTOMSS448" value="#{commonEntityDataBean}" />

<h:messages id="PRGCMGTMS19" showDetail="true" layout="table"
	showSummary="false" styleClass="errorMessage" />

<m:section id="PROVIDERMMS20120731164811596">
	<m:body onload="renderAudit('audit_open');">
		<m:div styleClass="floatContainer">
			<m:div styleClass="fullwidth">
				<m:div styleClass="moreInfoBar">
					<m:div styleClass="infoTitle" align="left">
						<m:span styleClass="required">
							<h:outputText id="PRGCMGTOT1439"
								value="#{ent['label-sw-reqFld']}" styleClass="errorMessage" />
						</m:span>

					</m:div>
					<m:div styleClass="infoActions">
						<t:commandLink id="PRGCMGTCL211" styleClass="Strong"
							onmousedown="javascript:flagWarn=false;"
							action="#{CMEntityMaintainControllerBean.continueWithADD}">
							<h:outputText id="PRGCMGTOT1440"
								value="#{msg['label.entity.ContinueWithAdd']}" />
							<f:param name="#{CMEntityMaintainDataBean.continueParamName}"
								value="#{CMEntityMaintainDataBean.continueParamValue}"></f:param>
						</t:commandLink>

						<h:outputText id="PRGCMGTOT1441" escape="false"
							value="#{ref['label.ref.linkSeperator']}" />
						<t:commandLink id="PRGCMGTCL212"
							action="#{CMEntityMaintainControllerBean.cancelViewEntity}"
							onmousedown="javascript:flagWarn=false;">
							<h:outputText id="PRGCMGTOT1442"
								value="#{ent['label-sw-cancel']}" />
						</t:commandLink>
					</m:div>
				</m:div>


				<m:br />
				<%-- Snippet to show The Message  --%>
				<m:div styleClass="msgBox"
					rendered="#{CMEntityMaintainDataBean.showMessage}">
					<h:outputText id="PRGCMGTOT1443"
						value="#{msg['label.entity.DuplicateEnt']}" />
				</m:div>

				<m:br />
				<t:dataTable cellspacing="0" styleClass="dataTable"
					columnClasses="columnClass" headerClass="headerClass"
					footerClass="footerClass" rowClasses="row_alt,row" id="dupentity"
					width="100%" rows="10"
					value="#{CMEntityMaintainDataBean.duplicateEntityList}"
					var="dupent" rowIndexVar="rowIndex"
					first="#{CMEntityMaintainDataBean.startIndexForViewEntity}"
					onmouseover="return tableMouseOver(this, event);"
					onmouseout="return tableMouseOut(this, event);">

					<h:column id="entityID">
						<f:facet name="header">
							<h:panelGrid id="PRGCMGTPGD161" columns="2">
								<h:outputLabel id="PRGCMGTOLL607" for="dataColEntType"
									value="#{msg['label.entity.entityType']}">
								</h:outputLabel>
								<h:panelGroup id="dataColEntType">
									<t:div styleClass="alignLeft">
										<t:commandLink id="entCommandLink1"
											actionListener="#{CMEntityMaintainControllerBean.sortDuplicateEnt}"
											rendered="#{CMEntityMaintainDataBean.imageRender !='entCommandLink1'}">

											<m:div title="for ascending" styleClass="sort_asc" />
											<f:attribute name="columnName" value="entIdTypeVal" />
											<f:attribute name="sortOrder" value="asc" />
										</t:commandLink>
									</t:div>
									<t:div styleClass="alignLeft">
										<t:commandLink id="entCommandLink2"
											onmousedown="javascript:flagWarn=false;"
											actionListener="#{CMEntityMaintainControllerBean.sortDuplicateEnt}"
											rendered="#{CMEntityMaintainDataBean.imageRender !='entCommandLink2'}">
											<m:div title="for descending" styleClass="sort_desc" />
											<f:attribute name="columnName" value="entIdTypeVal" />
											<f:attribute name="sortOrder" value="desc" />
										</t:commandLink>
									</t:div>
								</h:panelGroup>
							</h:panelGrid>
						</f:facet>
						<t:commandLink id="edit" onmousedown="javascript:flagWarn=false;"
							action="#{CMEntityMaintainControllerBean.getViewEntityDetails}">
							<h:outputText id="a" value="#{dupent.entityType}" />
							<f:param name="indexCode" value="#{rowIndex}"></f:param>

						</t:commandLink>
					</h:column>

					<h:column id="fnameCol">
						<f:facet name="header">
							<h:panelGrid id="PRGCMGTPGD162" columns="2">
								<h:outputLabel id="PRGCMGTOLL608" for="dataColFrstNam"
									value="#{msg['label.entity.firstName']}">
								</h:outputLabel>
								<h:panelGroup id="dataColFrstNam">
									<t:div styleClass="alignLeft">
										<t:commandLink id="fnameCommnadLink1"
											actionListener="#{CMEntityMaintainControllerBean.sortDuplicateEnt}"
											rendered="#{CMEntityMaintainDataBean.imageRender !='fnameCommnadLink1'}">
											<m:div title="for ascending" styleClass="sort_asc" />
											<f:attribute name="columnName" value="firstNamValue" />
											<f:attribute name="sortOrder" value="asc" />
										</t:commandLink>
									</t:div>
									<t:div styleClass="alignLeft">
										<t:commandLink id="fnameCommnadLink2"
											actionListener="#{CMEntityMaintainControllerBean.sortDuplicateEnt}"
											rendered="#{CMEntityMaintainDataBean.imageRender !='fnameCommnadLink2'}">
											<m:div title="for descinding" styleClass="sort_desc" />
											<f:attribute name="columnName" value="firstNamValue" />
											<f:attribute name="sortOrder" value="desc" />
										</t:commandLink>
									</t:div>
								</h:panelGroup>
							</h:panelGrid>
						</f:facet>

						<h:outputText id="namOutput" value="#{dupent.firstName}" />


					</h:column>


					<h:column id="lnamcol">
						<f:facet name="header">
							<h:panelGrid id="PRGCMGTPGD163" columns="2">
								<h:outputLabel id="PRGCMGTOLL609" for="dataColLastNam"
									value="#{msg['label.entity.lastName']}">
								</h:outputLabel>
								<h:panelGroup id="dataColLastNam">
									<t:div styleClass="alignLeft">
										<t:commandLink id="lnameCommnadLink1"
											actionListener="#{CMEntityMaintainControllerBean.sortDuplicateEnt}"
											rendered="#{CMEntityMaintainDataBean.imageRender !='lnameCommnadLink1'}">
											<m:div title="for ascending" styleClass="sort_asc" />
											<f:attribute name="columnName" value="lastNamValue" />
											<f:attribute name="sortOrder" value="asc" />
										</t:commandLink>
									</t:div>
									<t:div styleClass="alignLeft">
										<t:commandLink id="lnameCommnadLink2"
											actionListener="#{CMEntityMaintainControllerBean.sortDuplicateEnt}"
											rendered="#{CMEntityMaintainDataBean.imageRender !='lnameCommnadLink2'}">
											<m:div title="for descending" styleClass="sort_desc" />
											<f:attribute name="columnName" value="lastNamValue" />
											<f:attribute name="sortOrder" value="desc" />
										</t:commandLink>
									</t:div>
								</h:panelGroup>
							</h:panelGrid>
						</f:facet>

						<h:outputText id="lnamOutput" value="#{dupent.lastName}" />


					</h:column>


					<h:column id="cmentid">
						<f:facet name="header">
							<h:panelGrid id="PRGCMGTPGD164" columns="2">
								<h:outputLabel id="PRGCMGTOLL610" for="dataColCmEntID"
									value="#{msg['label.entity.cmEntityID']}">
								</h:outputLabel>
								<h:panelGroup id="dataColCmEntID">
									<t:div styleClass="alignLeft">
										<t:commandLink id="cmentityIDCommanLink1"
											actionListener="#{CMEntityMaintainControllerBean.sortDuplicateEnt}"
											rendered="#{CMEntityMaintainDataBean.imageRender !='cmentityIDCommanLink1'}">
											<m:div title="for ascending" styleClass="sort_asc" />
											<f:attribute name="columnName" value="entIdVal" />
											<f:attribute name="sortOrder" value="asc" />
										</t:commandLink>
									</t:div>
									<t:div styleClass="alignLeft">
										<t:commandLink id="cmentityIDCommanLink2"
											actionListener="#{CMEntityMaintainControllerBean.sortDuplicateEnt}"
											rendered="#{CMEntityMaintainDataBean.imageRender !='cmentityIDCommanLink2'}">
											<m:div title="for descending" styleClass="sort_desc" />
											<f:attribute name="columnName" value="entIdVal" />
											<f:attribute name="sortOrder" value="desc" />
										</t:commandLink>
									</t:div>
								</h:panelGroup>
							</h:panelGrid>
						</f:facet>

						<h:outputText id="cmidOutput" value="#{dupent.entityID}" />


					</h:column>

					<h:column id="orgCol">
						<f:facet name="header">
							<h:panelGrid id="PRGCMGTPGD165" columns="2">
								<h:outputLabel id="PRGCMGTOLL611" for="dataColOrgnam"
									value="#{msg['label.entity.orgName']}">
								</h:outputLabel>
								<h:panelGroup id="dataColOrgnam">
									<t:div styleClass="alignLeft">
										<t:commandLink id="orgNameCommanLink1"
											actionListener="#{CMEntityMaintainControllerBean.sortDuplicateEnt}"
											rendered="#{CMEntityMaintainDataBean.imageRender !='orgNameCommanLink1'}">
											<m:div title="for ascending" styleClass="sort_asc" />
											<f:attribute name="columnName" value="orgValue" />
											<f:attribute name="sortOrder" value="asc" />
										</t:commandLink>
									</t:div>
									<t:div styleClass="alignLeft">
										<t:commandLink id="orgNameCommanLink2"
											actionListener="#{CMEntityMaintainControllerBean.sortDuplicateEnt}"
											rendered="#{CMEntityMaintainDataBean.imageRender !='orgNameCommanLink2'}">
											<m:div title="for ascending" styleClass="sort_desc" />
											<f:attribute name="columnName" value="orgValue" />
											<f:attribute name="sortOrder" value="desc" />
										</t:commandLink>
									</t:div>
								</h:panelGroup>
							</h:panelGrid>
						</f:facet>

						<h:outputText id="orgnamOutput" value="#{dupent.organisationName}" />


					</h:column>
				</t:dataTable>

				<m:div styleClass="floatl">
				</m:div>
				<m:div styleClass="searchResults">
					<t:dataScroller id="CMGTTOMDS50" pageCountVar="pageCount"
						pageIndexVar="pageIndex" paginator="true"
						paginatorActiveColumnStyle='font-weight:bold;'
						paginatorMaxPages="3" immediate="false" for="dupentity"
						firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
						rowsCountVar="rowsCount" styleClass="dataScroller"
						onclick="javascript:flagWarn=false;">
						<f:facet name="previous">
							<h:outputText id="PRGCMGTOT1444" styleClass="underline"
								value="#{ref['label.ref.lt']}" rendered="#{pageIndex > 1}"></h:outputText>
						</f:facet>
						<f:facet name="next">
							<h:outputText id="PRGCMGTOT1445" styleClass="underline"
								value="#{ref['label.ref.gt']}"
								rendered="#{pageIndex < pageCount}"></h:outputText>
						</f:facet>
						<h:outputText id="PRGCMGTOT1446"
							value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
							styleClass="dataScrollerText" rendered="#{rowsCount>0}">
						</h:outputText>
					</t:dataScroller>
				</m:div>

				<m:br />
				<m:br />
				<m:div style="width:100%"
					rendered="#{CMEntityMaintainDataBean.renderViewEntityDetail}">
					<m:div styleClass="" id="viewentiyDet">
						<m:div styleClass="moreInfo">
							<m:div styleClass="moreInfoBar">
								<m:div styleClass="infoTitle">
									<h:outputText id="PRGCMGTOT1447"
										value="#{msg['label.entity.viewEnity']}" />
								</m:div>
								<m:div styleClass="infoActions">
									<t:commandLink id="PRGCMGTCL213" styleClass="Strong"
										action="#{CMEntityMaintainControllerBean.continueWithRecord}">
										<h:outputText id="PRGCMGTOT1448"
											value="#{msg['label.entity.ContinueWithThisRecord']}" />
										<f:param name="#{CMEntityMaintainDataBean.continueParamName}"
											value="#{CMEntityMaintainDataBean.continueParamValue}"></f:param>
									</t:commandLink>

									<h:outputText id="PRGCMGTOT1449" escape="false"
										value="#{ref['label.ref.linkSeperator']}" />
									<t:commandLink id="PRGCMGTCL214"
										action="#{CMEntityMaintainControllerBean.cancelViewEntityDetails}">
										<h:outputText id="PRGCMGTOT1450"
											value="#{ent['label-sw-cancel']}" />
									</t:commandLink>
								</m:div>
							</m:div>

							<m:div styleClass="moreInfoContent">

								<m:div>
								</m:div>
								<m:div style="width:100%">
									<m:table id="PROVIDERMMT20120731164811597" cellspacing="0" width="100%">
										<m:tr>
											<m:td>
												<m:div styleClass="padb">
													<h:outputLabel id="PRGCMGTOLL612" for="dpentty"
														value="#{msg['label.entity.entityType']}" />

													<m:br></m:br>
													<h:inputText id="dpentty" size="20" disabled="true"
														value="#{CMEntityMaintainDataBean.duplicateCMEnityDetailVO.entityType}"></h:inputText>
												</m:div>
											</m:td>
											<m:td>
												<m:div styleClass="padb">
													<h:outputLabel id="PRGCMGTOLL613" for="frstn"
														value="#{msg['label.entity.firstName']}" />

													<m:br></m:br>
													<h:inputText id="frstn" disabled="true" size="20"
														value="#{CMEntityMaintainDataBean.duplicateCMEnityDetailVO.nameVO.firstName}"></h:inputText>
												</m:div>
											</m:td>
											<m:td>
												<m:div styleClass="padb">
													<h:outputLabel id="PRGCMGTOLL614" for="lstnam"
														value="#{msg['label.entity.lastName']}" />

													<m:br></m:br>
													<h:inputText id="lstnam" disabled="true" size="20"
														value="#{CMEntityMaintainDataBean.duplicateCMEnityDetailVO.nameVO.lastName}"></h:inputText>
												</m:div>
											</m:td>
											<m:td>
												<m:div styleClass="padb">
													<h:outputLabel id="PRGCMGTOLL615" for="cmid"
														value="#{msg['label.entity.cmEntityID']}" />

													<m:br></m:br>
													<h:inputText id="cmid" disabled="true" size="20"
														value="#{CMEntityMaintainDataBean.duplicateCMEnityDetailVO.cmEntityID}"></h:inputText>
												</m:div>
											</m:td>
											<m:td>
												<m:div styleClass="padb">
													<h:outputLabel id="PRGCMGTOLL616" for="orgnam"
														value="#{msg['label.entity.orgName']}" />

													<m:br></m:br>
													<h:inputText id="orgnam" disabled="true" size="20"
														value="#{CMEntityMaintainDataBean.duplicateCMEnityDetailVO.organizationName}"></h:inputText>
												</m:div>
											</m:td>
										</m:tr>
										<m:tr>
											<m:td>
												<m:div styleClass="padb">
													<h:outputLabel id="PRGCMGTOLL617" for="ssn"
														value="#{msg['label.entity.SSN']}" />

													<m:br></m:br>
													<h:inputText id="ssn" disabled="true" size="20"
														value="#{CMEntityMaintainDataBean.duplicateCMEnityDetailVO.ssnNumber}"></h:inputText>
												</m:div>
											</m:td>
											<m:td>
												<m:div styleClass="padb">
													<h:outputLabel id="PRGCMGTOLL618" for="ein"
														value="#{msg['label.entity.EIN']}" />

													<m:br></m:br>
													<h:inputText id="ein" disabled="true" size="20"
														value="#{CMEntityMaintainDataBean.duplicateCMEnityDetailVO.employeeIdentificationNumber}"></h:inputText>
												</m:div>
											</m:td>
											<m:td>
												<h:outputLabel id="PRGCMGTOLL619" for="provty"
													value="#{msg['label.entity.provType']}" />
												<m:br></m:br>
												<h:selectOneMenu id="provty" disabled="true"
													value="#{CMEntityMaintainDataBean.duplicateCMEnityDetailVO.providerType}">
													<f:selectItem itemValue=""
														itemLabel="#{msg['label.entity.Please.Select.One']}" />
													<f:selectItems
														value="#{CMEntityMaintainDataBean.provTypeListME}" />
												</h:selectOneMenu>
											</m:td>

											<m:td>
												<h:outputLabel id="PRGCMGTOLL620" for="loblist"
													value="#{msg['label.entity.lineOFBusiness']}" />
												<m:br></m:br>
												<h:selectOneMenu id="loblist" disabled="true"
													value="#{CMEntityMaintainDataBean.duplicateCMEnityDetailVO.lineOfBusiness}">
													<f:selectItem itemValue=""
														itemLabel="#{msg['label.entity.Please.Select.One']}" />
													<f:selectItems
														value="#{CMEntityMaintainDataBean.lobListME}" />
												</h:selectOneMenu>
											</m:td>
										</m:tr>
									</m:table>

									<m:div
										rendered="#{CMEntityMaintainDataBean.duplicateCMEnityDetailVO.auditLogRender}">
										<f:subview id="dupEntityCodeInc">
											<jsp:include
												page="/jsp/maintainentity/inc_ME_DuplicateEntity.jsp" />
										</f:subview>
									</m:div>
								</m:div>
							</m:div>
						</m:div>
					</m:div>
				</m:div>
			</m:div>
		</m:div>
	</m:body>
</m:section>

