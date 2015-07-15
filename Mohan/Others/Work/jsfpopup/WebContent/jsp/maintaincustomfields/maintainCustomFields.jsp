<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@ taglib uri="http://www.acs-inc.com/auditLog" prefix="audit"%>

<script type="text/javascript">
	/*Small save Big Save start*/
	frmId = 'view<portlet:namespace/>:MaintainCustomFields';
	/*Small save Big Save ends*/

	function toggleTest(obj, state) {
		var el = document.getElementById(obj);
		if (state == 1) {
			el.style.display = 'block';
		} else if (state == 0) {
			el.style.display = 'none';
		} else if (state == 2) {
			if (el.style.display == 'none') {
				el.style.display = 'block';
			} else if ((el.style.display == 'block')
					|| (el.style.display == '')) {
				el.style.display = 'none';
			}
		}
	}

	function plusMinusForRefreshTest(id, obj, hiddenTextId) {
		var hiddenTxt = 'plus';
		var el = document.getElementById(id);

		if (el.style.display == 'none') {
			obj.className = 'plus';
			hiddenTxt.value = 'plus';
		} else if ((el.style.display == 'block') || (el.style.display == '')) {
			obj.className = 'minus';
			hiddenTxt.value = 'minus';
		} else if (el.style.display == '') {
			obj.className = 'minus';
			hiddenTxt.value = 'minus';
		}
	}

	function renderAudit(id) {
		var hiddenValue = document
				.getElementById('view<portlet:namespace/>:form1:auditlogDetails:' + id);

		if ((hiddenValue == null) || (hiddenValue == '')
				|| (hiddenValue.length == 0)) {
			hideMe('audit_plus');
		} else if ((hiddenValue.value == 'false')) {
			hideMe('audit_plus');
		}
	}
</script>
<script>
	thisForm = 'view<portlet:namespace/>:MaintainCustomFields';
	function focusThis(id) {

		document.getElementById(thisForm + ':focusId').value = id;
	}

	if (window.addEventListener) //DOM method for binding an event
		window.addEventListener("load", onLoadFunctions, false);
	else if (window.attachEvent) //IE exclusive method for binding an event
		window.attachEvent("onload", onLoadFunctions);
	else if (document.getElementById) //support older modern browsers
		window.onload = onLoadFunctions;

	function onLoadFunctions() {
		focusOnLoad();
	}

	function focusOnLoad() {
		var focusPage = '#' + document.getElementById(thisForm + ':focusId').value;
		if (focusPage != '' && focusPage != '#') {
			document.location.href = focusPage;
		}
	}
	function findObjectByPartOfID(partOfID) {
		for (i = 0; i < document.forms.length; i++) {
			for (j = 0; j < document.forms[i].elements.length; j++) {
				var idValue = document.forms[i].elements[j].id;
				if (idValue.indexOf(partOfID) != -1) {
					G_isFirstTime = false;
					G_countObject = document.forms[i].elements[j];
					return document.forms[i].elements[j];
				}
			}
		}
		return null;
	}
</script>
<f:loadBundle
	basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_ContactCustomFieldsMaintenance"
	var="msg" />

<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.EnterpriseMessages"
	var="ent" />



<f:view>


	<%-- Small save Big Save start --%>
	<f:subview id="custField">
		<jsp:include page="/jsp/commonEntities/commonScripts.jsp" />
	</f:subview>
	<body onload="renderAudit('audit_open_cf');">
	<%-- Small save Big Save ends --%>



	<%--<t:saveState id="CMGTTOMSS394" value="#{customFieldDataBean}"></t:saveState>--%>

	<%-- start Added for CR implementation--%>
	<h:inputHidden value="#{customFieldControllerBean.controlRequired}"
		id="controlRequiredForCustomfields" />
	<%-- End Added for CR implementation--%>

	<h:inputHidden id="PRGCMGTIH26"
		value="#{customFieldControllerBean.loadUserPermission}"></h:inputHidden>
	<h:inputHidden id="PRGCMGTIH27"
		value="#{customFieldControllerBean.customFields}" rendered="#{!customFieldDataBean.sortIndicator}"></h:inputHidden>
	<m:div id="wrapper">
		<m:div id="content">
			<m:div styleClass="floatContainer">
				<h:form id="MaintainCustomFields">
					<h:inputHidden id="focusId"
						value="#{customFieldDataBean.inputHiddenId}" />
					<m:div styleClass="moreInfoBar">
						<m:div styleClass="infoTitle" align="left">
							<h:outputText id="reqFld"
								value="#{msg['label.customFields.requiredField']}"
								styleClass="errorMessage" />
						</m:div>

						<m:div styleClass="infoActions">
						
						<%--Code modified below for fixing the defect ESPRD00762539 on 06-03-2012 --%>
							
							<%--<h:commandLink id="PRGCMGTCL191"
								action="#{customFieldControllerBean.redirect}">
								<h:outputText id="PRGCMGTOT1327" styleClass="strong"
									value="#{msg['label.customFields.cancel']}"></h:outputText>
								<f:param name="com.ibm.faces.portlet.page.view"
									value="#{msg['label.customFields.linkCancel']}" />
							</h:commandLink>--%>
							
							<f:verbatim>  <a href="/wps/myportal/InternalUserHomePage" id="PRGCMGTCUST" onclick="flagWarn=true;"> </f:verbatim>
									<h:outputText id="PRGCMGTOT1CNCLCUST" styleClass="strong" value="#{ent['label-sw-cancel']}" />
								<f:verbatim></a></f:verbatim>
							
						</m:div>
					</m:div>
					<m:div styleClass="strong">
						<m:br></m:br>
						<m:br></m:br>
						<m:section id="PROVIDERMMS20120731164811550" styleClass="otherbg">
							<m:legend>

								<h:outputText id="PRGCMGTOT1328"
									value="#{msg['title.label.customFields.maintainCustomfields']}" />

							</m:legend>

							<%--<m:div styleClass="msgBox"
									rendered="#{customFieldDataBean.showDeletedMessage}">
									<h:outputText id="PRGCMGTOT1329"										value="#{msg['label.customFields.deleteMessage']}" />
								</m:div>
								--%>
							<h:inputHidden value=" " id="showDeleteMessageID"></h:inputHidden>
							<m:div>
								<h:message id="PRGCMGTM181" for="showDeleteMessageID"
									style="padding: 4px 622px 4px 4px;" styleClass="msgBox" />
							</m:div>
							<m:br></m:br>
							<m:table id="PROVIDERMMT20120731164811551" styleClass="tableBar" width="100%">
								<m:tr>
									<m:td styleClass="tableAction">
										<h:commandButton id="PRGCMGTCB56" styleClass="formBtn"
											onclick="flagWarn=false;"
											disabled="#{!customFieldControllerBean.controlRequired}"
											value="#{msg['label.customFields.addCustomField']}"
											action="#{customFieldControllerBean.addCustomFields}" />
									</m:td>
								</m:tr>
							</m:table>


							<%--<t:dataTable id="customFieldDataTable" border="1"									cellpadding="0" cellspacing="0" columnClasses="columnClass"									headerClass="headerClass" rows="10" footerClass="footerClass"									rowClasses="row_alt,rowone" styleClass="dataTable" width="100%"									var="customFieldsList" rowIndexVar="rowIndex"									binding="#{customFieldDataBean.maintainCFDataTable}"									value="#{customFieldDataBean.maintainCustomFieldsList}"									rowOnClick="firstChild.lastChild.click();"					            	rowOnMouseOver="this.origClassName = this.className;this.className='row_mouse row_link';"				            		rowOnMouseOut="if (this.origClassName) this.className = this.origClassName;">--%>
							<t:dataTable cellspacing="0" styleClass="dataTable" width="100%"
								rows="10" first="#{customFieldDataBean.startIndex}"
								value="#{customFieldDataBean.maintainCustomFieldsList}"
								var="customFieldsList" id="customFieldDataTable"
								rowIndexVar="rowIndex" columnClasses="columnClass"
								headerClass="headerClass" footerClass="footerClass"
								rowClasses="row_alt,row"
								rowOnClick="firstChild.lastChild.click();"
								rowOnMouseOver="this.origClassName = this.className;this.className='row_mouse row_link';"
								rowOnMouseOut="if (this.origClassName) this.className = this.origClassName;">
								<h:column id="duedate">
									<f:facet name="header">
										<h:panelGrid id="PRGCMGTPGD154" columns="2">
											<h:outputLabel id="PRGCMGTOLL554" for="columnDescription"
												value="#{msg['label.customFields.columnDescription']}" />
											<h:panelGroup id="PRGCMGTPGP104">
												<t:div>
													<t:commandLink id="colDesCommandLink1"
														onmousedown="if(event.button==1) flagWarn=false;"
														actionListener="#{customFieldControllerBean.getAllSortedCustomField}"
														rendered="#{customFieldDataBean.imageRender != 'colDesCommandLink1'}">
														<%--<h:graphicImage
															alt="#{msg['label.customFields.forAscending']}"
															styleClass="sort_asc" width="8" url="/images/x.gif" /> --%>
														
														<m:div title="#{msg['label.customFields.forAscending']}" styleClass="sort_asc" />
														<f:attribute name="columnName" value="columnDescription" />
														<f:attribute name="sortOrder" value="asc" />
													</t:commandLink>
												</t:div>
												<t:div>
													<t:commandLink id="colDesCommandLink2"
														onmousedown="if(event.button==1) flagWarn=false;"
														actionListener="#{customFieldControllerBean.getAllSortedCustomField}"
														rendered="#{customFieldDataBean.imageRender != 'colDesCommandLink2'}">
														<%--<h:graphicImage
															alt="#{msg['label.customFields.forDescending']}"
															styleClass="sort_desc" width="8" url="/images/x.gif" /> --%>
															
														<m:div title="#{msg['label.customFields.forDescending']}"	styleClass="sort_desc" />
														<f:attribute name="columnName" value="columnDescription" />
														<f:attribute name="sortOrder" value="desc" />
													</t:commandLink>
												</t:div>
											</h:panelGroup>
										</h:panelGrid>
									</f:facet>
									<t:commandLink id="PRGCMGTCL192"
										onclick="javascript:flagWarn=false;"
										action="#{customFieldControllerBean.getCustomFieldDetails}">
										<h:outputText id="xx1"
											value="#{customFieldsList.columnDescription}" />
										<f:param name="cfRowIndex" value="#{rowIndex}" />

									</t:commandLink>
								</h:column>
								<h:column id="enddate">
									<f:facet name="header">
										<h:panelGrid id="PRGCMGTPGD155" columns="2">
											<h:outputLabel id="PRGCMGTOLL555" for="dataType"
												value="#{msg['label.customFields.dataType']}" />
											<h:panelGroup id="PRGCMGTPGP105">
												<t:div>
													<t:commandLink id="dataTypeCommandLink1"
														onmousedown="if(event.button==1) flagWarn=false;"
														actionListener="#{customFieldControllerBean.getAllSortedCustomField}"
														rendered="#{customFieldDataBean.imageRender != 'dataTypeCommandLink1'}">
														<%--<h:graphicImage
															alt="#{msg['label.customFields.forAscending']}"
															styleClass="sort_asc" width="8" url="/images/x.gif" /> --%>
															
														<m:div title="#{msg['label.customFields.forAscending']}" styleClass="sort_asc" />
														<f:attribute name="columnName" value="dataType" />
														<f:attribute name="sortOrder" value="asc" />
													</t:commandLink>
												</t:div>
												<t:div>
													<t:commandLink id="dataTypeCommandLink2"
														onmousedown="if(event.button==1) flagWarn=false;"
														actionListener="#{customFieldControllerBean.getAllSortedCustomField}"
														rendered="#{customFieldDataBean.imageRender != 'dataTypeCommandLink2'}">
														<%--<h:graphicImage
															alt="#{msg['label.customFields.forDescending']}"
															styleClass="sort_desc" width="8" url="/images/x.gif" /> --%>
														
														<m:div title="#{msg['label.customFields.forDescending']}"	styleClass="sort_desc" />
														<f:attribute name="columnName" value="dataType" />
														<f:attribute name="sortOrder" value="desc" />
													</t:commandLink>
												</t:div>
											</h:panelGroup>
										</h:panelGrid>
									</f:facet>
									<h:outputText id="xx2" value="#{customFieldsList.dataTypestr}" />
								</h:column>
								<h:column id="status">
									<f:facet name="header">
										<h:panelGrid id="PRGCMGTPGD156" columns="2">
											<h:outputLabel id="PRGCMGTOLL556" for="length"
												value="#{msg['label.customFields.length']}" />
											<h:panelGroup id="PRGCMGTPGP106">
												<t:div>
													<t:commandLink id="lengthCommandLink1"
														onmousedown="if(event.button==1) flagWarn=false;"
														actionListener="#{customFieldControllerBean.getAllSortedCustomField}"
														rendered="#{customFieldDataBean.imageRender != 'lengthCommandLink1'}">
														<%--<h:graphicImage
															alt="#{msg['label.customFields.forAscending']}"
															styleClass="sort_asc" width="8" url="/images/x.gif" /> --%>
														
														<m:div title="#{msg['label.customFields.forAscending']}" styleClass="sort_asc" />
														<f:attribute name="columnName" value="length" />
														<f:attribute name="sortOrder" value="asc" />
													</t:commandLink>
												</t:div>
												<t:div>
													<t:commandLink id="lengthCommandLink2"
														onmousedown="if(event.button==1) flagWarn=false;"
														actionListener="#{customFieldControllerBean.getAllSortedCustomField}"
														rendered="#{customFieldDataBean.imageRender != 'lengthCommandLink2'}">
														<%--<h:graphicImage
															alt="#{msg['label.customFields.forDescending']}"
															styleClass="sort_desc" width="8" url="/images/x.gif" /> --%>
															
														<m:div title="#{msg['label.customFields.forDescending']}"	styleClass="sort_desc" />
														<f:attribute name="columnName" value="length" />
														<f:attribute name="sortOrder" value="desc" />
													</t:commandLink>
												</t:div>
											</h:panelGroup>
										</h:panelGrid>
									</f:facet>
									<h:outputText id="xx3" value="#{customFieldsList.length}" />
								</h:column>
								<h:column id="fininst">
									<f:facet name="header">
										<h:panelGrid id="PRGCMGTPGD157" columns="2">
											<h:outputLabel id="PRGCMGTOLL557" for="scope"
												value="#{msg['label.customFields.scope']}" />
											<h:panelGroup id="PRGCMGTPGP107">
												<t:div>
													<t:commandLink id="scopeCommandLink1"
														onmousedown="if(event.button==1) flagWarn=false;"
														actionListener="#{customFieldControllerBean.getAllSortedCustomField}"
														rendered="#{customFieldDataBean.imageRender != 'scopeCommandLink1'}">
														<%--<h:graphicImage
															alt="#{msg['label.customFields.forAscending']}"
															styleClass="sort_asc" width="8" url="/images/x.gif" />--%>
															
														<m:div title="#{msg['label.customFields.forAscending']}" styleClass="sort_asc" /> 
														<f:attribute name="columnName" value="scope" />
														<f:attribute name="sortOrder" value="asc" />
													</t:commandLink>
												</t:div>
												<t:div>
													<t:commandLink id="scopeCommandLink2"
														onmousedown="if(event.button==1) flagWarn=false;"
														actionListener="#{customFieldControllerBean.getAllSortedCustomField}"
														rendered="#{customFieldDataBean.imageRender != 'scopeCommandLink2'}">
														<%--<h:graphicImage
															alt="#{msg['label.customFields.forDescending']}"
															styleClass="sort_desc" width="8" url="/images/x.gif" />  --%>
															
														<m:div title="#{msg['label.customFields.forDescending']}"	styleClass="sort_desc" />
														<f:attribute name="columnName" value="scope" />
														<f:attribute name="sortOrder" value="desc" />
													</t:commandLink>
												</t:div>
											</h:panelGroup>
										</h:panelGrid>
									</f:facet>
									<h:outputText id="xx4" value="#{customFieldsList.scopeValue}" />
								</h:column>
								<h:column id="Active">
									<f:facet name="header">
										<h:panelGrid id="PRGCMGTPGD158" columns="2">
											<h:outputLabel id="PRGCMGTOLL558" for="active"
												value="#{msg['label.customFields.active']}" />
											<h:panelGroup id="PRGCMGTPGP108">
												<t:div>
													<t:commandLink id="activeCommandLink1"
														onmousedown="if(event.button==1) flagWarn=false;"
														actionListener="#{customFieldControllerBean.getAllSortedCustomField}"
														rendered="#{customFieldDataBean.imageRender != 'activeCommandLink1'}">
														<%--<h:graphicImage
															alt="#{msg['label.customFields.forAscending']}"
															styleClass="sort_asc" width="8" url="/images/x.gif" />  --%>
															
														<m:div title="#{msg['label.customFields.forAscending']}" styleClass="sort_asc" /> 
														<f:attribute name="columnName" value="active" />
														<f:attribute name="sortOrder" value="asc" />
													</t:commandLink>
												</t:div>
												<t:div>
													<t:commandLink id="activeCommandLink2"
														onmousedown="if(event.button==1) flagWarn=false;"
														actionListener="#{customFieldControllerBean.getAllSortedCustomField}"
														rendered="#{customFieldDataBean.imageRender != 'activeCommandLink2'}">
														<%--<h:graphicImage
															alt="#{msg['label.customFields.forDescending']}"
															styleClass="sort_desc" width="8" url="/images/x.gif" /> --%>
															
														<m:div title="#{msg['label.customFields.forDescending']}"	styleClass="sort_desc" />
														<f:attribute name="columnName" value="active" />
														<f:attribute name="sortOrder" value="desc" />
													</t:commandLink>
												</t:div>
											</h:panelGroup>
										</h:panelGrid>
									</f:facet>
									<h:outputText id="xx5" value="#{customFieldsList.activeFlag}" />
								</h:column>
								<h:column id="Required">
									<f:facet name="header">
										<h:panelGrid id="PRGCMGTPGD159" columns="2">
											<h:outputLabel id="PRGCMGTOLL559" for="required"
												value="#{msg['label.customFields.required']}" />
											<h:panelGroup id="PRGCMGTPGP109">
												<t:div>
													<t:commandLink id="requiredCommandLink1"
														onmousedown="if(event.button==1) flagWarn=false;"
														actionListener="#{customFieldControllerBean.getAllSortedCustomField}"
														rendered="#{customFieldDataBean.imageRender != 'requiredCommandLink1'}">
														<%--<h:graphicImage
															alt="#{msg['label.customFields.forAscending']}"
															styleClass="sort_asc" width="8" url="/images/x.gif" />  --%>
															
														<m:div title="#{msg['label.customFields.forAscending']}" styleClass="sort_asc" /> 
														<f:attribute name="columnName" value="required" />
														<f:attribute name="sortOrder" value="asc" />
													</t:commandLink>
												</t:div>
												<t:div>
													<t:commandLink id="requiredCommandLink2"
														onmousedown="if(event.button==1) flagWarn=false;"
														actionListener="#{customFieldControllerBean.getAllSortedCustomField}"
														rendered="#{customFieldDataBean.imageRender != 'requiredCommandLink2'}">
														<%--<h:graphicImage
															alt="#{msg['label.customFields.forDescending']}"
															styleClass="sort_desc" width="8" url="/images/x.gif" /> --%>
														
														<m:div title="#{msg['label.customFields.forDescending']}"	styleClass="sort_desc" />
														<f:attribute name="columnName" value="required" />
														<f:attribute name="sortOrder" value="desc" />
													</t:commandLink>
												</t:div>
											</h:panelGroup>
										</h:panelGrid>
									</f:facet>
									<h:outputText id="xx6" value="#{customFieldsList.requiredFlag}" />
								</h:column>
								<h:column id="Protected">
									<f:facet name="header">
										<h:panelGrid id="PRGCMGTPGD160" columns="2">
											<h:outputLabel id="PRGCMGTOLL560" for="protected"
												value="#{msg['label.customFields.protected']}" />
											<h:panelGroup id="PRGCMGTPGP110">
												<t:div>
													<t:commandLink id="protectedCommandLink1"
														onmousedown="if(event.button==1) flagWarn=false;"
														actionListener="#{customFieldControllerBean.getAllSortedCustomField}"
														rendered="#{customFieldDataBean.imageRender != 'protectedCommandLink1'}">
														<%--<h:graphicImage
															alt="#{msg['label.customFields.forAscending']}"
															styleClass="sort_asc" width="8" url="/images/x.gif" /> --%>
															
														<m:div title="#{msg['label.customFields.forAscending']}" styleClass="sort_asc" />
														<f:attribute name="columnName" value="protected" />
														<f:attribute name="sortOrder" value="asc" />
													</t:commandLink>
												</t:div>
												<t:div>
													<t:commandLink id="protectedCommandLink2"
														onmousedown="if(event.button==1) flagWarn=false;"
														actionListener="#{customFieldControllerBean.getAllSortedCustomField}"
														rendered="#{customFieldDataBean.imageRender != 'protectedCommandLink2'}">
														<%--<h:graphicImage
															alt="#{msg['label.customFields.forDescending']}"
															styleClass="sort_desc" width="8" url="/images/x.gif" /> --%>
															
														<m:div title="#{msg['label.customFields.forDescending']}"	styleClass="sort_desc" />
														<f:attribute name="columnName" value="protected" />
														<f:attribute name="sortOrder" value="desc" />
													</t:commandLink>
												</t:div>
											</h:panelGroup>
										</h:panelGrid>
									</f:facet>
									<h:outputText id="xx7"
										value="#{customFieldsList.protectedFlag}" />
								</h:column>
								<f:facet name="footer">

									<m:div id="nodatabankinfo" align="center">
										<h:outputText id="PRGCMGTOT1330"
											value="#{msg['label.customFields.noData']}"
											rendered="#{customFieldDataBean.noData}"></h:outputText>
									</m:div>

								</f:facet>
							</t:dataTable>
							<t:dataScroller id="CMGTTOMDS47" pageCountVar="pageCount"
								pageIndexVar="pageIndex" onclick="flagWarn=false;"
								paginator="true" paginatorActiveColumnStyle='font-weight:bold;'
								paginatorMaxPages="3" immediate="false"
								for="customFieldDataTable" firstRowIndexVar="firstRowIndex"
								lastRowIndexVar="lastRowIndex" rowsCountVar="rowsCount"
								styleClass="dataScroller">
								<f:facet name="previous">
									<h:outputText id="PRGCMGTOT1331"
										value="#{msg['label.customFields.pageLeft']}"
										rendered="#{pageIndex > 1}">
									</h:outputText>
								</f:facet>
								<f:facet name="next">
									<h:outputText id="PRGCMGTOT1332"
										value="#{msg['label.customFields.pageRight']}"
										rendered="#{pageIndex < pageCount}">
									</h:outputText>
								</f:facet>
								<h:outputText id="PRGCMGTOT1333" styleClass="dataScrollerText"
									value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
									rendered="#{rowsCount>0}">
								</h:outputText>
							</t:dataScroller>


							<m:br></m:br>
							<m:br></m:br>



							<m:div id="moreInfo"
								rendered="#{customFieldDataBean.showAddCustomFields || customFieldDataBean.showEditCustomFields}">
								<%--<m:div styleClass="moreInfo"> --%>
									<%--  EDIT SECTION --%>
									
									<%--Code modified below for fixing the defect ESPRD00762539 on 06-03-2012 --%>
									
									<m:div styleClass="moreInfoBar"
										rendered="#{customFieldDataBean.showEditCustomFields}">
										<m:div styleClass="infoTitle">
											<h:outputText id="PRGCMGTOT1334"
												value="#{msg['label.customFields.editCustomField']}" />
										</m:div>
										<m:div styleClass="infoActions">
											<%--<h:commandLink id="PRGCMGTCL193" styleClass="strong" onmousedown="if(event.button==1) flagWarn=false;"												    rendered="#{customFieldControllerBean.controlRequired}"													action="#{customFieldControllerBean.updateCustomField}">
													<h:outputText id="PRGCMGTOT1335" value="#{msg['label.customFields.save']}"></h:outputText>
												</h:commandLink>--%>
											<h:commandButton id="PRGCMGTCB57"
												style="font-weight:bold;COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:35px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal;position:relative;top:2px"
												onclick="flagWarn=false;"
												disabled="#{!customFieldControllerBean.controlRequired}"
												value="#{msg['label.customFields.save']}"
												action="#{customFieldControllerBean.updateCustomField}" />
											<h:outputText id="PRGCMGTOT1336" escape="false" styleClass="normal"
											style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:3px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: top;word-spacing: normal"
												value="#{msg['label.customFields.space']}" />


											<%--<h:commandLink id="PRGCMGTCL194" onmousedown="if(event.button==1) flagWarn=false;"												     rendered="#{customFieldControllerBean.controlRequired}"													 action="#{customFieldControllerBean.resetEditCustomFields}" >
													<h:outputText id="PRGCMGTOT1337" value="#{msg['label.customFields.reset']}"></h:outputText>
												</h:commandLink>--%>
											<h:commandButton id="PRGCMGTCB58"
												style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:38px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal;position:relative;top:2px"
												onclick="flagWarn=false;"
												disabled="#{!customFieldControllerBean.controlRequired}"
												value="#{msg['label.customFields.reset']}"
												action="#{customFieldControllerBean.resetEditCustomFields}" />



											<h:outputText id="PRGCMGTOT1338" escape="false" styleClass="normal"
											style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:3px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: top;word-spacing: normal"
												value="#{msg['label.customFields.space']}" />
											<h:commandButton id="PRGCMGTCB59"
												style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:42px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal;position:relative;top:2px"
												onclick="flagWarn=false; if (!(confirm('Are you sure you want to Delete?'))) return(false);"
												disabled="#{!customFieldControllerBean.controlRequired || !customFieldControllerBean.controlRequiredFOrDelete}"
												value="#{msg['label.customFields.delete']}"
												action="#{customFieldControllerBean.deleteCustomField}" />
											<h:outputText id="PRGCMGTOT1339" escape="false" styleClass="normal"
											style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:3px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: top;word-spacing: normal"
												value="#{msg['label.customFields.space']}" />

											<h:commandButton id="PRGCMGTCB60" 
												onmousedown="if(event.button==1) flagWarn=false;"
												style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:57px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal;position:relative;top:2px"
												value="Audit Log"
												action="#{customFieldControllerBean.doAuditForCF}" />
											<h:outputText id="PRGCMGTOT1340" escape="false" styleClass="normal"
											style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:3px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: top;word-spacing: normal"
												value="#{msg['label.customFields.space']}" />
											<h:commandButton id="PRGCMGTCB61"
												onclick="flagWarn=true;"
												style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:50px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal;position:relative;top:2px"
												value="#{msg['label.customFields.cancel']}"
												action="#{customFieldControllerBean.cancelCustomField}"/>
										</m:div>
									</m:div>
									<%--  ADD SECTION --%>
									
									<m:div styleClass="moreInfoBar"
										rendered="#{customFieldDataBean.showAddCustomFields}">
										<m:div styleClass="infoTitle">
											<h:outputText id="PRGCMGTOT1341"
												value="#{msg['label.customFields.addCustomField']}" />
										</m:div>
										<m:div styleClass="infoActions">
											<h:panelGroup id="PRGCMGTPGP9811">
											<t:commandLink id="PRGCMGTCL195"
												rendered="#{customFieldControllerBean.controlRequired }"
												onmousedown="if(event.button==1) flagWarn=false;"
												action="#{customFieldControllerBean.createCustomField}">
												<h:outputText id="PRGCMGTOT1342"
													value="#{msg['label.customFields.save']}"></h:outputText>
											</t:commandLink>
											<h:outputText id="PRGCMGTOT1343" escape="false" styleClass="normal"
												value="#{msg['label.customFields.space']}" />

											<t:commandLink id="PRGCMGTCL196" styleClass="normal"
												rendered="#{customFieldControllerBean.controlRequired }"
												onmousedown="if(event.button==1) flagWarn=false;"
												action="#{customFieldControllerBean.resetCustomField}">
												<h:outputText id="PRGCMGTOT1344"
													value="#{msg['label.customFields.reset']}"></h:outputText>
											</t:commandLink>
											<h:outputText id="PRGCMGTOT1345" escape="false" styleClass="normal"
												value="#{msg['label.customFields.space']}" />
												
											<%--Code modified below for fixing the defect ESPRD00762539 on 06-03-2012 --%>	
												
											<%--<h:commandLink id="PRGCMGTCB62" styleClass="normal"
												rendered="#{customFieldControllerBean.controlRequired }"
												value="#{msg['label.customFields.cancel']}"
												action="#{customFieldControllerBean.cancelCustomField}" /> --%>
												
												<h:commandButton id="addCustfieldCancel"
													rendered="#{customFieldControllerBean.controlRequired }"
													onclick="flagWarn=true;"
													action="#{customFieldControllerBean.cancelCustomField}"
													style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:50px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal; position:top;top:2px"
													value="#{msg['label.customFields.cancel']}"> 
												</h:commandButton>
												
											</h:panelGroup>
										</m:div>
									</m:div>
									<%-- Common to both Edit and Add blocks --%>
									<m:div styleClass="moreInfoContent">
										<m:div styleClass="msgBox"
											rendered="#{customFieldDataBean.showSucessMessage}">
											<h:outputText id="PRGCMGTOT1346"
												value="#{ent['label-sw-success']}" />
										</m:div>

										<m:div rendered="#{customFieldDataBean.showCFDescMsgFlag}">
											<h:messages layout="table" showDetail="true"
												id="editCustomFieldDescMessage" showSummary="false"
												styleClass="errorMessage"></h:messages>
										</m:div>
										<m:table id="PROVIDERMMT20120731164811552" cellspacing="0" width="95%">
											<m:tr>
												<m:td>
													<m:div styleClass="padb">
														<h:outputText id="PRGCMGTOT1347"
															value="#{msg['label.customFields.star']}"
															styleClass="errorMessage" />
														<h:outputLabel id="PRGCMGTOLL561" for="ColumnDesc">
															<h:outputText id="PRGCMGTOT1348"
																value="#{msg['label.customFields.columnDescription']}" />
														</h:outputLabel>
														<m:br></m:br>
														<h:inputText id="ColumnDesc" size="30"
															disabled="#{!customFieldControllerBean.controlRequired}"
															value="#{customFieldDataBean.customFieldVO.columnDescription}" />
														<m:br />
														<h:message id="PRGCMGTM182" for="ColumnDesc"
															styleClass="errorMessage" />
													</m:div>
												</m:td>
												<m:td>
													<m:div styleClass="padb">
														<h:outputLabel id="PRGCMGTOLL562" for="DataTypes">
															<h:outputText id="PRGCMGTOT1349"
																value="#{msg['label.customFields.star']}"
																styleClass="errorMessage" />
															<h:outputText id="PRGCMGTOT1350"
																value="#{msg['label.customFields.dataType']}" />
														</h:outputLabel>
														<m:br></m:br>
														<h:selectOneMenu id="DataTypes"
															disabled="#{customFieldDataBean.showDataType || !customFieldControllerBean.controlRequired}"
															value="#{customFieldDataBean.customFieldVO.dataType}"
															valueChangeListener="#{customFieldControllerBean.showDropDownScreen}"
															onkeydown="flagWarn=false;" onchange="this.form.submit()"
															onclick="flagWarn=false;">
															<f:selectItems
																value="#{customFieldDataBean.dataTypeList}" />
														</h:selectOneMenu>
														<m:br></m:br>
														<h:message id="PRGCMGTM183" for="DataTypes"
															styleClass="errorMessage" />
													</m:div>
												</m:td>


												<m:td>
													<m:div styleClass="padb">
														<h:outputText id="PRGCMGTOT1351"
															value="#{msg['label.customFields.star']}"
															rendered="#{customFieldDataBean.lengthFlag}"
															styleClass="errorMessage" />
														<h:outputLabel id="PRGCMGTOLL563" for="Length"
															value="#{msg['label.customFields.length']}" />
														<m:br></m:br>
														<h:inputText id="Length"
															disabled="#{!customFieldControllerBean.controlRequired}"
															value="#{customFieldDataBean.customFieldVO.length}" />
														<m:br></m:br>
														<h:message id="PRGCMGTM184" for="Length"
															styleClass="errorMessage" />
													</m:div>
												</m:td>
												<m:td>
													<m:div styleClass="padb">
														<h:outputLabel id="PRGCMGTOLL564" for="Scope">
															<h:outputText id="PRGCMGTOT1352"
																value="#{msg['label.customFields.star']}"
																styleClass="errorMessage" />
															<h:outputText id="PRGCMGTOT1353"
																value="#{msg['label.customFields.scope']}" />
														</h:outputLabel>
														<m:br></m:br>
														<h:selectOneMenu id="Scope"
															value="#{customFieldDataBean.customFieldVO.scope}"
															disabled="#{!customFieldControllerBean.controlRequired}">
															<f:selectItems value="#{customFieldDataBean.scopeList}" />
														</h:selectOneMenu>
														<m:br></m:br>

														<h:message id="PRGCMGTM185" for="Scope"
															styleClass="errorMessage" />
													</m:div>
												</m:td>
											</m:tr>
											<m:tr>
												<m:td>
													<m:div styleClass="padb">
														<h:outputLabel id="PRGCMGTOLL565" for="selonerad_1">
															<h:outputText id="PRGCMGTOT1354"
																value="#{msg['label.customFields.active']}" />
														</h:outputLabel>
														<m:br></m:br>
														<h:selectOneRadio id="selonerad_1"
															disabled="#{!customFieldControllerBean.controlRequired}"
															value="#{customFieldDataBean.customFieldVO.activeInd}"
															enabledClass="black">
															<f:selectItems value="#{customFieldDataBean.activeList}" />
														</h:selectOneRadio>
													</m:div>
												</m:td>
												<m:td>
													<m:div styleClass="padb">
														<h:outputLabel id="PRGCMGTOLL566" for="selonerad_2">
															<h:outputText id="PRGCMGTOT1355"
																value="#{msg['label.customFields.required']}" />
														</h:outputLabel>
														<m:br></m:br>
														<h:selectOneRadio id="selonerad_2"
															disabled="#{!customFieldControllerBean.controlRequired}"
															value="#{customFieldDataBean.customFieldVO.requiredInd}"
															enabledClass="black">
															<f:selectItems
																value="#{customFieldDataBean.requiredList}" />
														</h:selectOneRadio>
													</m:div>
												</m:td>
												<m:td>
													<m:div styleClass="padb">
														<h:outputLabel id="PRGCMGTOLL567" for="selonerad_3">
															<h:outputText id="PRGCMGTOT1356"
																value="#{msg['label.customFields.protected']}" />
														</h:outputLabel>
														<m:br></m:br>
														<h:selectOneRadio id="selonerad_3"
															disabled="#{!customFieldControllerBean.controlRequired}"
															value="#{customFieldDataBean.customFieldVO.protectedInd}"
															enabledClass="black">
															<f:selectItems
																value="#{customFieldDataBean.protectedList}" />
														</h:selectOneRadio>
													</m:div>
												</m:td>
											</m:tr>
										</m:table>
										<m:br></m:br>

										<m:div
											rendered="#{not empty customFieldDataBean.customFieldVO.customFieldSK}">
											<f:subview id="auditlogDetailsforCustmFlds">
												<m:div rendered="#{customFieldDataBean.showCFforAud}">
													<audit:auditTableSet id="auditlogDetailsforCustmFldsId"
														value="#{customFieldDataBean.customFieldVO.auditKeyList}"
														numOfRecPerPage="10">
													</audit:auditTableSet>
												</m:div>
											</f:subview>
										</m:div>

									</m:div>
								</m:div>
							
							<%--</m:div> --%>


						</m:section>
					</m:div>

					<m:div styleClass="clear"></m:div>
					<m:div>
						<h:graphicImage id="PROVIDERGI20120731164811553" styleClass="blankImage" width="1" height="5"
							alt="" url="/images/x.gif" />
					</m:div>
					<m:div rendered="#{customFieldDataBean.showDropDownScreenFlag}">
						<f:subview id="dropDown">
							<jsp:include page="/jsp/maintaincustomfields/inc_addDropDown.jsp" />
						</f:subview>
					</m:div>
				</h:form>
			</m:div>
		</m:div>
	</m:div>
	<%-- var thisForm : stores the form reference. Form reference is use to get  the computed  role stored in hidden form field.
         FlagRole : Indicator to store role information .Stores 'False' for inquire only users and 'True' for other user.
	--%>
	<script type="text/JavaScript">

   var thisForm = 'view<portlet:namespace/>:MaintainCustomFields';

   flagRole =((typeof (document.getElementById(thisForm+': controlRequiredForCustomfields) !=  'undefined')) && document.getElementById(thisForm+':controlRequiredForCustomfields) != null ?  document.getElementById(thisForm+':controlRequiredForCustomfields).value:true);

</script>
	</body>
</f:view>
