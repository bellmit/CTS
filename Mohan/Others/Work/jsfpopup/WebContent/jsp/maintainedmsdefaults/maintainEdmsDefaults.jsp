<%-- 
  - Author(s): Wipro
  - Date: Mon Oct 08 11:04:08 IST 2007
  - Copyright Notice: Copyright (c) 2006 Affiliated Computer Services, Inc.
  - @ 2006
  - Description: 
  --%>
<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>


<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@ taglib uri="http://www.acs-inc.com/auditLog" prefix="audit"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>


<script type="text/javascript">
	/*Small save Big Save start*/
	frmId = 'view<portlet:namespace/>:frm';
	/*Small save Big Save ends*/

	function doEditEdmsDefaultClickAction(link) {
		var saveFlag = document.getElementById(frmId + ':' + "saveFlag").value;
		if (saveFlag == 'true') {
			link.fireEvent('onclick');
		} else {
			var answer = confirm("Are you sure you want to navigate away from page?");
			if (answer) {
				link.fireEvent('onclick');
			}
		}
	}
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




<portlet:defineObjects />

<?xml version="1.0" encoding="UTF-8"?>

<f:loadBundle
	basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_ContactEdmsDefaultsMaintenance"
	var="edmsmtn" />

<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.EnterpriseMessages"
	var="ent" />

<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.ProgramMessages"
	var="ref" />

<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.GlobalAuditLabels"
	var="msgAudit" />

<f:view>



	<%--<t:saveState id="CMGTTOMSS402" value="#{EDMSDefaultsDataBean}" />
	<t:saveState id="CMGTTOMSS403" value="#{EDMSDefaultsDataBean.edmsDefaultsList}" />
	<t:saveState id="CMGTTOMSS404" value="#{EDMSDefaultsDataBean.rowIndex}" />
	<t:saveState id="CMGTTOMSS405" value="#{EDMSDefaultsDataBean.renderEditEDMSDefaults}" />
	<t:saveState id="CMGTTOMSS406" value="#{EDMSDefaultsDataBean.edmsDefaultsVO}" />
	<t:saveState id="CMGTTOMSS407" value="#{EDMSDefaultsDataBean.renderCase}" />
	<t:saveState id="CMGTTOMSS408" value="#{EDMSDefaultsDataBean.edmsValidValues}" />
	<t:saveState id="CMGTTOMSS409" value="#{EDMSDefaultsDataBean.categorySubjectValues}" />
	<t:saveState id="CMGTTOMSS410" value="#{EDMSDefaultsDataBean.categoryList}" />
	<t:saveState id="CMGTTOMSS411" value="#{EDMSDefaultsDataBean.statCodeValidValues}" />
	<t:saveState id="CMGTTOMSS412" value="#{EDMSDefaultsDataBean.routeToList}" />
	<t:saveState id="CMGTTOMSS413" value="#{EDMSDefaultsDataBean.departmentsList}" />--%>

	<f:subview id="provService">
		<jsp:include page="/jsp/commonEntities/commonScripts.jsp" />
	</f:subview>
	<body id="maintainEDMS01">

	<!--  <h:messages id="PRGCMGTMS17"></h:messages>
		<m:div styleClass="hide">
		</m:div> -->

	<!-- To display all the messages at top left of the portlet -->
	<h:messages id="edmsDefaultsID1" layout="table" showDetail="true"
		showSummary="false" styleClass="errorMessage" />
	<m:br id="maintainEDMS02" clear="all" />
	<m:br id="brID22" />

	<m:section styleClass="otherbg" id="edmsDefaultsID2">
		<m:legend id="edmsDefaultsID3">
			<h:outputText id="edmsDefaultsID4"
				value="#{edmsmtn['title.edmsdflts.contactMgmt_EDMSDefaults']}" />
		</m:legend>

		<hx:scriptCollector id="maintainEDMS03">
			<h:form id="frm">

				<h:inputHidden id="controlRequiredForEDMS"
					value="#{EDMSDefaultsControllerBean.controlRequired}"></h:inputHidden>

				<h:inputHidden id="saveFlag"
					value="#{EDMSDefaultsDataBean.saveFlag}"></h:inputHidden>

								<m:table cellspacing="0" cellpadding="0" styleClass="dataTable"
					id="demo_table" border="0" width="100%">
					<m:tbody>
					<m:tr id="edmsDefaultsID5">
						 <m:th id="edmsDefaultsID6" style="width:155px" >
						 <h:outputText id="PROVIDEROLT20120731164811557" value="#{ref['label.ref.singleSpace']}" escape="false"></h:outputText>
						
					</m:th>
						<m:th align="centre" colspan="6" id="edmsDefaultsID10">
							Defaults </m:th>
							
					</m:tr>
					</m:tbody>
				</m:table>

				<%--  Fixed for Defect ESPRD00709740 ESPRD00586661 commented the static table starts here 
				<m:table cellspacing="0" styleClass="dataTable" id="demo_table"
					width="100%">
					<m:tr id="edmsDefaultsID5">
						<m:th rowspan="2" id="edmsDefaultsID6" style="width:16%">
							<h:panelGrid columns="2" id="edmsDefaultsID7">
								<h:outputLabel id="docType" for="docTypeValue"
									value="#{edmsmtn['label.edmsdflts.documentType']}" />
								<h:panelGroup id="docTypeValue">
									<t:div style="width x;align=left" id="edmsDefaultsID8">
										<h:commandLink id="docTypeAscCmdLink"
											style="text-decoration: none;"
											actionListener="#{EDMSDefaultsControllerBean.sort}"
											onmousedown="javascript:flagWarn=false;"
											rendered="#{EDMSDefaultsDataBean.imageRender != 'docTypeAscCmdLink'}">
											<h:graphicImage id="PROVIDERGI20120731164811558" title="for ascending" styleClass="sort_asc"
												alt="" url="/images/x.gif" width="8" />
											<f:attribute name="columnName" value="documentType" />
											<f:attribute name="sortOrder" value="asc" />
										</h:commandLink>
									</t:div>
									<t:div style="width x;align=left" id="edmsDefaultsID9">
										<h:commandLink id="docTypeDscCmdLink"
											style="text-decoration: none;"
											actionListener="#{EDMSDefaultsControllerBean.sort}"
											onmousedown="javascript:flagWarn=false;"
											rendered="#{EDMSDefaultsDataBean.imageRender != 'docTypeDscCmdLink'}">
											<h:graphicImage id="PROVIDERGI20120731164811559" title="for descending" styleClass="sort_desc"
												alt="" url="/images/x.gif" width="8" />
											<f:attribute name="columnName" value="documentType" />
											<f:attribute name="sortOrder" value="desc" />
										</h:commandLink>
									</t:div>
								</h:panelGroup>
							</h:panelGrid>
						</m:th>

						<m:th align="center" colspan="6" id="edmsDefaultsID10">Defaults</m:th>
					</m:tr>
					<m:tr id="edmsDefaultsID11">
						<m:th id="edmsDefaultsID12">
							<h:panelGrid columns="2" id="edmsDefaultsID13">
								<h:outputLabel id="crCategory01" for="crCategoryValue"
									value="#{edmsmtn['label.edmsdflts.crCategory']}" />
								<h:panelGroup id="crCategoryValue">
									<t:div style="width x;align=left" id="edmsDefaultsID14">
										<h:commandLink id="crCategoryAscCmdLink"
											style="text-decoration: none;"
											actionListener="#{EDMSDefaultsControllerBean.sort}"
											onmousedown="javascript:flagWarn=false;"
											rendered="#{EDMSDefaultsDataBean.imageRender != 'crCategoryAscCmdLink'}">
											<h:graphicImage id="PROVIDERGI20120731164811560" title="for ascending" styleClass="sort_asc"
												alt="" url="/images/x.gif" width="8" />
											<f:attribute name="columnName" value="crCategory" />
											<f:attribute name="sortOrder" value="asc" />
										</h:commandLink>
									</t:div>
									<t:div style="width x;align=left" id="edmsDefaultsID15">
										<h:commandLink id="crCategoryDscCmdLink"
											style="text-decoration: none;"
											actionListener="#{EDMSDefaultsControllerBean.sort}"
											onmousedown="javascript:flagWarn=false;"
											rendered="#{EDMSDefaultsDataBean.imageRender != 'crCategoryDscCmdLink'}">
											<h:graphicImage id="PROVIDERGI20120731164811561" title="for descending" styleClass="sort_desc"
												alt="" url="/images/x.gif" width="8" />
											<f:attribute name="columnName" value="crCategory" />
											<f:attribute name="sortOrder" value="desc" />
										</h:commandLink>
									</t:div>
								</h:panelGroup>
							</h:panelGrid>
						</m:th>
						<m:th id="edmsDefaultsID16">
							<h:panelGrid columns="2" id="edmsDefaultsID17">
								<h:outputLabel id="crSubject" for="crSubjectValue"
									value="#{edmsmtn['label.edmsdflts.crSubject']}" />
								<h:panelGroup id="crSubjectValue">
									<t:div style="width x;align=left" id="edmsDefaultsID18">
										<h:commandLink id="crSubjectAscCmdLink"
											style="text-decoration: none;"
											actionListener="#{EDMSDefaultsControllerBean.sort}"
											onmousedown="javascript:flagWarn=false;"
											rendered="#{EDMSDefaultsDataBean.imageRender != 'crSubjectAscCmdLink'}">
											<h:graphicImage id="PROVIDERGI20120731164811562" title="for ascending" styleClass="sort_asc"
												alt="" url="/images/x.gif" width="8" />
											<f:attribute name="columnName" value="crSubject" />
											<f:attribute name="sortOrder" value="asc" />
										</h:commandLink>
									</t:div>
									<t:div style="width x;align=left" id="edmsDefaultsID19">
										<h:commandLink id="crSubjectDscCmdLink"
											style="text-decoration: none;"
											actionListener="#{EDMSDefaultsControllerBean.sort}"
											onmousedown="javascript:flagWarn=false;"
											rendered="#{EDMSDefaultsDataBean.imageRender != 'crSubjectDscCmdLink'}">
											<h:graphicImage id="PROVIDERGI20120731164811563" title="for descending" styleClass="sort_desc"
												alt="" url="/images/x.gif" width="8" />
											<f:attribute name="columnName" value="crSubject" />
											<f:attribute name="sortOrder" value="desc" />
										</h:commandLink>
									</t:div>
								</h:panelGroup>
							</h:panelGrid>
						</m:th>
						<m:th id="edmsDefaultsID20">
							<h:panelGrid columns="2" id="edmsDefaultsID21">
								<h:outputLabel id="status" for="statusValue"
									value="#{edmsmtn['label.edmsdflts.crStatus']}" />
								<h:panelGroup id="statusValue">
									<t:div style="width x;align=left" id="edmsDefaultsID22">
										<h:commandLink id="crStatusAscCmdLink"
											style="text-decoration: none;"
											actionListener="#{EDMSDefaultsControllerBean.sort}"
											onmousedown="javascript:flagWarn=false;"
											rendered="#{EDMSDefaultsDataBean.imageRender != 'crStatusAscCmdLink'}">
											<h:graphicImage id="PROVIDERGI20120731164811564" title="for ascending" styleClass="sort_asc"
												alt="" url="/images/x.gif" width="8" />
											<f:attribute name="columnName" value="crStatus" />
											<f:attribute name="sortOrder" value="asc" />
										</h:commandLink>
									</t:div>
									<t:div style="width x;align=left" id="edmsDefaultsID23">
										<h:commandLink id="crStatusDscCmdLink"
											style="text-decoration: none;"
											actionListener="#{EDMSDefaultsControllerBean.sort}"
											onmousedown="javascript:flagWarn=false;"
											rendered="#{EDMSDefaultsDataBean.imageRender != 'crStatusDscCmdLink'}">
											<h:graphicImage id="PROVIDERGI20120731164811565" title="for descending" styleClass="sort_desc"
												alt="" url="/images/x.gif" width="8" />
											<f:attribute name="columnName" value="crStatus" />
											<f:attribute name="sortOrder" value="desc" />
										</h:commandLink>
									</t:div>
								</h:panelGroup>
							</h:panelGrid>
						</m:th>
						<m:th id="edmsDefaultsID24">
							<h:panelGrid columns="2" id="edmsDefaultsID25">
								<h:outputLabel id="caseTyp" for="caseTypeValue"
									value="#{edmsmtn['label.edmsdflts.caseType']}" />
								<h:panelGroup id="caseTypeValue">
									<t:div style="width x;align=left" id="edmsDefaultsID26">
										<h:commandLink id="caseTypeAscCmdLink"
											style="text-decoration: none;"
											actionListener="#{EDMSDefaultsControllerBean.sort}"
											onmousedown="javascript:flagWarn=false;"
											rendered="#{EDMSDefaultsDataBean.imageRender != 'caseTypeAscCmdLink'}">
											<h:graphicImage id="PROVIDERGI20120731164811566" title="for ascending" styleClass="sort_asc"
												alt="" url="/images/x.gif" width="8" />
											<f:attribute name="columnName" value="caseType" />
											<f:attribute name="sortOrder" value="asc" />
										</h:commandLink>
									</t:div>
									<t:div style="width x;align=left" id="edmsDefaultsID27">
										<h:commandLink id="caseTypeDscCmdLink"
											style="text-decoration: none;"
											actionListener="#{EDMSDefaultsControllerBean.sort}"
											onmousedown="javascript:flagWarn=false;"
											rendered="#{EDMSDefaultsDataBean.imageRender != 'caseTypeDscCmdLink'}">
											<h:graphicImage id="PROVIDERGI20120731164811567" title="for descending" styleClass="sort_desc"
												alt="" url="/images/x.gif" width="8" />
											<f:attribute name="columnName" value="caseType" />
											<f:attribute name="sortOrder" value="desc" />
										</h:commandLink>
									</t:div>
								</h:panelGroup>
							</h:panelGrid>
						</m:th>
						<m:th id="edmsDefaultsID28">
							<h:panelGrid columns="2" id="edmsDefaultsID29">
								<h:outputLabel id="routeTo" for="routeToValue"
									value="#{edmsmtn['label.edmsdflts.routeTo']}" />
								<h:panelGroup id="routeToValue">
									<t:div style="width x;align=left" id="edmsDefaultsID30">
										<h:commandLink id="routeToAscCmdLink"
											style="text-decoration: none;"
											actionListener="#{EDMSDefaultsControllerBean.sort}"
											onmousedown="javascript:flagWarn=false;"
											rendered="#{EDMSDefaultsDataBean.imageRender != 'routeToAscCmdLink'}">
											<h:graphicImage id="PROVIDERGI20120731164811568" title="for ascending" styleClass="sort_asc"
												alt="" url="/images/x.gif" width="8" />
											<f:attribute name="columnName" value="routeTo" />
											<f:attribute name="sortOrder" value="asc" />
										</h:commandLink>
									</t:div>
									<t:div style="width x;align=left" id="edmsDefaultsID31">
										<h:commandLink id="routeToDscCmdLink"
											style="text-decoration: none;"
											actionListener="#{EDMSDefaultsControllerBean.sort}"
											onmousedown="javascript:flagWarn=false;"
											rendered="#{EDMSDefaultsDataBean.imageRender != 'routeToDscCmdLink'}">
											<h:graphicImage id="PROVIDERGI20120731164811569" title="for descending" styleClass="sort_desc"
												alt="" url="/images/x.gif" width="8" />
											<f:attribute name="columnName" value="routeTo" />
											<f:attribute name="sortOrder" value="desc" />
										</h:commandLink>
									</t:div>
								</h:panelGroup>
							</h:panelGrid>
						</m:th>
						<m:th id="edmsDefaultsID32">
							<h:panelGrid columns="2" id="edmsDefaultsID33">
								<h:outputLabel id="dept" for="deptValue"
									value="#{edmsmtn['label.edmsdflts.department']}" />
								<h:panelGroup id="deptValue">
									<t:div style="width x;align=left" id="edmsDefaultsID34">
										<h:commandLink id="deptAscCmdLink"
											style="text-decoration: none;"
											actionListener="#{EDMSDefaultsControllerBean.sort}"
											onmousedown="javascript:flagWarn=false;"
											rendered="#{EDMSDefaultsDataBean.imageRender != 'deptAscCmdLink'}">
											<h:graphicImage id="PROVIDERGI20120731164811570" title="for ascending" styleClass="sort_asc"
												alt="" url="/images/x.gif" width="8" />
											<f:attribute name="columnName" value="department" />
											<f:attribute name="sortOrder" value="asc" />
										</h:commandLink>
									</t:div>
									<t:div style="width x;align=left" id="edmsDefaultsID35">
										<h:commandLink id="deptDscCmdLink"
											style="text-decoration: none;"
											actionListener="#{EDMSDefaultsControllerBean.sort}"
											onmousedown="javascript:flagWarn=false;"
											rendered="#{EDMSDefaultsDataBean.imageRender != 'deptDscCmdLink'}">
											<h:graphicImage id="PROVIDERGI20120731164811571" title="for descending" styleClass="sort_desc"
												alt="" url="/images/x.gif" width="8" />
											<f:attribute name="columnName" value="department" />
											<f:attribute name="sortOrder" value="desc" />
										</h:commandLink>
									</t:div>
								</h:panelGroup>
							</h:panelGrid>
						</m:th>
					</m:tr>
				</m:table>
				commented for defect ESPRD00586661 ends here --%>

				<%-- modified for edms data table row selection activity in datatable : added row style class--%>

				<t:dataTable cellspacing="0" styleClass="dataTable" width="100%"
					rows="10" first="#{EDMSDefaultsDataBean.edmsStartIndex}"
					value="#{EDMSDefaultsDataBean.edmsDefaultsList}"
					var="edmsDefaultsDetails" id="edmsDefaultsData"
					rowIndexVar="rowIndex" columnClasses="columnClass"
					headerClass="headerClass" footerClass="footerClass"
					rowStyleClass="#{EDMSDefaultsDataBean.edmsDefaultsRowIndex == rowIndex ? 'row_selected' : 'row'}"
					rowClasses="row_alt,row" rowOnClick="firstChild.lastChild.click();"
					rowOnMouseOver="this.origClassName = this.className;this.className='row_mouse row_link';"
					rowOnMouseOut="if (this.origClassName) this.className = this.origClassName;">
					<%-- Fix for defect ESPRD00586661 added the header in Data table for all the columns --%>
					<t:column>
						<f:facet name="header">
							<h:panelGrid id="PRGCMGTPGD148" columns="2">
								<h:outputLabel id="activeLabel" for="categoryPanelGrid"
									value="#{edmsmtn['label.edmsdflts.documentType']}" />
								<h:panelGroup id="categoryPanelGrid">
									<t:div styleClass="alignLeft">
										<t:commandLink id="docTypeAscCmdLink"
											style="text-decoration: none;"
											actionListener="#{EDMSDefaultsControllerBean.sort}"
											onmousedown="javascript:flagWarn=false;"
											rendered="#{EDMSDefaultsDataBean.imageRender != 'docTypeAscCmdLink'}">
											<h:graphicImage id="PROVIDERGI20120731164811572" title="for ascending" styleClass="sort_asc"
												alt="" url="/images/x.gif" width="8" />
											<f:attribute name="columnName" value="documentType" />
											<f:attribute name="sortOrder" value="asc" />
										</t:commandLink>
									</t:div>
									<t:div styleClass="alignLeft">
										<t:commandLink id="docTypeDscCmdLink"
											style="text-decoration: none;"
											actionListener="#{EDMSDefaultsControllerBean.sort}"
											onmousedown="javascript:flagWarn=false;"
											rendered="#{EDMSDefaultsDataBean.imageRender != 'docTypeDscCmdLink'}">
											<h:graphicImage id="PROVIDERGI20120731164811573" title="for descending" styleClass="sort_desc"
												alt="" url="/images/x.gif" width="8" />
											<f:attribute name="columnName" value="documentType" />
											<f:attribute name="sortOrder" value="desc" />
										</t:commandLink>
									</t:div>
								</h:panelGroup>
							</h:panelGrid>
						</f:facet>
						<%-- <h:column id="DocTypeIdForChk"> --%>
						<t:commandLink id="DocTypeLink"
							action="#{EDMSDefaultsControllerBean.editEDMSDefaults}"
							onclick="javascript:flagWarn=false;" style="cursor: hand;">
							<h:outputText id="valueDocType"
								value="#{edmsDefaultsDetails.documentType}" />

							<f:param name="indexCode" value="#{rowIndex}" />
						</t:commandLink>
						<%-- </h:column> --%>
					</t:column>
					<t:column>
						<f:facet name="header">
							<h:panelGrid id="edmsDefaultsID13" columns="2">
								<h:outputLabel id="crCategory01" for="crCategoryValue"
									value="#{edmsmtn['label.edmsdflts.crCategory']}" />
								<h:panelGroup id="crCategoryValue">
									<t:div style="width x;align=left" id="edmsDefaultsID14">
										<t:commandLink id="crCategoryAscCmdLink"
											style="text-decoration: none;"
											actionListener="#{EDMSDefaultsControllerBean.sort}"
											onmousedown="javascript:flagWarn=false;"
											rendered="#{EDMSDefaultsDataBean.imageRender != 'crCategoryAscCmdLink'}">
											<h:graphicImage id="PROVIDERGI20120731164811574" title="for ascending" styleClass="sort_asc"
												alt="" url="/images/x.gif" width="8" />
											<f:attribute name="columnName" value="crCategory" />
											<f:attribute name="sortOrder" value="asc" />
										</t:commandLink>
									</t:div>
									<t:div style="width x;align=left" id="edmsDefaultsID15">
										<t:commandLink id="crCategoryDscCmdLink"
											style="text-decoration: none;"
											actionListener="#{EDMSDefaultsControllerBean.sort}"
											onmousedown="javascript:flagWarn=false;"
											rendered="#{EDMSDefaultsDataBean.imageRender != 'crCategoryDscCmdLink'}">
											<h:graphicImage id="PROVIDERGI20120731164811575" title="for descending" styleClass="sort_desc"
												alt="" url="/images/x.gif" width="8" />
											<f:attribute name="columnName" value="crCategory" />
											<f:attribute name="sortOrder" value="desc" />
										</t:commandLink>
									</t:div>
								</h:panelGroup>
							</h:panelGrid>
						</f:facet>
						<%--<h:column id="CRCategory"> --%>
						<h:outputText id="valueCRCategory" style="width:80px"
							value="#{edmsDefaultsDetails.categoryDesc}" />
						<%--</h:column>--%>
					</t:column>
					<t:column id="maintainEDMS04">
						<f:facet name="header">
							<h:panelGrid columns="2" id="edmsDefaultsID17">
								<h:outputLabel id="crSubject" for="crSubjectValue"
									value="#{edmsmtn['label.edmsdflts.crSubject']}" />
								<h:panelGroup id="crSubjectValue">
									<t:div style="width x;align=left" id="edmsDefaultsID18">
										<t:commandLink id="crSubjectAscCmdLink"
											style="text-decoration: none;"
											actionListener="#{EDMSDefaultsControllerBean.sort}"
											onmousedown="javascript:flagWarn=false;"
											rendered="#{EDMSDefaultsDataBean.imageRender != 'crSubjectAscCmdLink'}">
											<h:graphicImage id="PROVIDERGI20120731164811576" title="for ascending" styleClass="sort_asc"
												alt="" url="/images/x.gif" width="8" />
											<f:attribute name="columnName" value="crSubject" />
											<f:attribute name="sortOrder" value="asc" />
										</t:commandLink>
									</t:div>
									<t:div style="width x;align=left" id="edmsDefaultsID19">
										<t:commandLink id="crSubjectDscCmdLink"
											style="text-decoration: none;"
											actionListener="#{EDMSDefaultsControllerBean.sort}"
											onmousedown="javascript:flagWarn=false;"
											rendered="#{EDMSDefaultsDataBean.imageRender != 'crSubjectDscCmdLink'}">
											<h:graphicImage id="PROVIDERGI20120731164811577" title="for descending" styleClass="sort_desc"
												alt="" url="/images/x.gif" width="8" />
											<f:attribute name="columnName" value="crSubject" />
											<f:attribute name="sortOrder" value="desc" />
										</t:commandLink>
									</t:div>
								</h:panelGroup>
							</h:panelGrid>
						</f:facet>
						<%--<h:column id="maintainEDMS04">
						<h:commandLink id="CRSubjectLink"													action="#{EDMSDefaultsControllerBean.editEDMSDefaults}"													onmousedown="javascript:flagWarn=false;">
							<h:outputText id="valueCRSubject" style="width:73px"															value="#{edmsDefaultsDetails.subject}" />
							<f:param name="indexCode" value="#{rowIndex}" />
						</h:commandLink>--%>
						<h:outputText id="valueCRSubject" style="width:73px"
							value="#{edmsDefaultsDetails.subject}" />
						<%-- </h:column> --%>
					</t:column>
					<t:column>
						<f:facet name="header">
							<h:panelGrid columns="2" id="edmsDefaultsID21">
								<h:outputLabel id="status" for="statusValue"
									value="#{edmsmtn['label.edmsdflts.crStatus']}" />
								<h:panelGroup id="statusValue">
									<t:div style="width x;align=left" id="edmsDefaultsID22">
										<t:commandLink id="crStatusAscCmdLink"
											style="text-decoration: none;"
											actionListener="#{EDMSDefaultsControllerBean.sort}"
											onmousedown="javascript:flagWarn=false;"
											rendered="#{EDMSDefaultsDataBean.imageRender != 'crStatusAscCmdLink'}">
											<h:graphicImage id="PROVIDERGI20120731164811578" title="for ascending" styleClass="sort_asc"
												alt="" url="/images/x.gif" width="8" />
											<f:attribute name="columnName" value="crStatus" />
											<f:attribute name="sortOrder" value="asc" />
										</t:commandLink>
									</t:div>
									<t:div style="width x;align=left" id="edmsDefaultsID23">
										<t:commandLink id="crStatusDscCmdLink"
											style="text-decoration: none;"
											actionListener="#{EDMSDefaultsControllerBean.sort}"
											onmousedown="javascript:flagWarn=false;"
											rendered="#{EDMSDefaultsDataBean.imageRender != 'crStatusDscCmdLink'}">
											<h:graphicImage id="PROVIDERGI20120731164811579" title="for descending" styleClass="sort_desc"
												alt="" url="/images/x.gif" width="8" />
											<f:attribute name="columnName" value="crStatus" />
											<f:attribute name="sortOrder" value="desc" />
										</t:commandLink>
									</t:div>
								</h:panelGroup>
							</h:panelGrid>
						</f:facet>
						<%-- <h:column id="maintainEDMS07">
						<h:commandLink id="CRStatusLink"							action="#{EDMSDefaultsControllerBean.editEDMSDefaults}"							onmousedown="javascript:flagWarn=false;">
							<h:outputText id="valueCRStatus" style="width:67px"							value="#{edmsDefaultsDetails.status}" />
							<f:param name="indexCode" value="#{rowIndex}" />
						</h:commandLink>
					<%-- </h:column>--%>
						<h:outputText id="valueCRStatus" style="width:67px"
							value="#{edmsDefaultsDetails.status}" />
					</t:column>
					<t:column>
						<f:facet name="header">
							<h:panelGrid columns="2" id="edmsDefaultsID25">
								<h:outputLabel id="caseTyp" for="caseTypeValue"
									value="#{edmsmtn['label.edmsdflts.caseType']}" />
								<h:panelGroup id="caseTypeValue">
									<t:div style="width x;align=left" id="edmsDefaultsID26">
										<t:commandLink id="caseTypeAscCmdLink"
											style="text-decoration: none;"
											actionListener="#{EDMSDefaultsControllerBean.sort}"
											onmousedown="javascript:flagWarn=false;"
											rendered="#{EDMSDefaultsDataBean.imageRender != 'caseTypeAscCmdLink'}">
											<h:graphicImage id="PROVIDERGI20120731164811580" title="for ascending" styleClass="sort_asc"
												alt="" url="/images/x.gif" width="8" />
											<f:attribute name="columnName" value="caseType" />
											<f:attribute name="sortOrder" value="asc" />
										</t:commandLink>
									</t:div>
									<t:div style="width x;align=left" id="edmsDefaultsID27">
										<t:commandLink id="caseTypeDscCmdLink"
											style="text-decoration: none;"
											actionListener="#{EDMSDefaultsControllerBean.sort}"
											onmousedown="javascript:flagWarn=false;"
											rendered="#{EDMSDefaultsDataBean.imageRender != 'caseTypeDscCmdLink'}">
											<h:graphicImage id="PROVIDERGI20120731164811581" title="for descending" styleClass="sort_desc"
												alt="" url="/images/x.gif" width="8" />
											<f:attribute name="columnName" value="caseType" />
											<f:attribute name="sortOrder" value="desc" />
										</t:commandLink>
									</t:div>
								</h:panelGroup>
							</h:panelGrid>
						</f:facet>
						<%--<h:column id="maintainEDMS08">
						<h:commandLink id="CaseTypeLink"							action="#{EDMSDefaultsControllerBean.editEDMSDefaults}"							onmousedown="javascript:flagWarn=false;">
							<h:outputText id="valueCaseType" style="width:69px"								value="#{edmsDefaultsDetails.caseType}" />
							<f:param name="indexCode" value="#{rowIndex}" />
						</h:commandLink>
					<%-- </h:column>--%>
						<h:outputText id="valueCaseType" style="width:69px"
							value="#{edmsDefaultsDetails.caseType}" />
					</t:column>
					<t:column>
						<f:facet name="header">
							<h:panelGrid columns="2" id="edmsDefaultsID29">
								<h:outputLabel id="routeTo" for="routeToValue"
									value="#{edmsmtn['label.edmsdflts.routeTo']}" />
								<h:panelGroup id="routeToValue">
									<t:div style="width x;align=left" id="edmsDefaultsID30">
										<t:commandLink id="routeToAscCmdLink"
											style="text-decoration: none;"
											actionListener="#{EDMSDefaultsControllerBean.sort}"
											onmousedown="javascript:flagWarn=false;"
											rendered="#{EDMSDefaultsDataBean.imageRender != 'routeToAscCmdLink'}">
											<h:graphicImage id="PROVIDERGI20120731164811582" title="for ascending" styleClass="sort_asc"
												alt="" url="/images/x.gif" width="8" />
											<f:attribute name="columnName" value="routeTo" />
											<f:attribute name="sortOrder" value="asc" />
										</t:commandLink>
									</t:div>
									<t:div style="width x;align=left" id="edmsDefaultsID31">
										<t:commandLink id="routeToDscCmdLink"
											style="text-decoration: none;"
											actionListener="#{EDMSDefaultsControllerBean.sort}"
											onmousedown="javascript:flagWarn=false;"
											rendered="#{EDMSDefaultsDataBean.imageRender != 'routeToDscCmdLink'}">
											<h:graphicImage id="PROVIDERGI20120731164811583" title="for descending" styleClass="sort_desc"
												alt="" url="/images/x.gif" width="8" />
											<f:attribute name="columnName" value="routeTo" />
											<f:attribute name="sortOrder" value="desc" />
										</t:commandLink>
									</t:div>
								</h:panelGroup>
							</h:panelGrid>
						</f:facet>
						<h:outputText id="valueRouteTo" style="width:65px"
							value="#{edmsDefaultsDetails.edmsWorkUnitVO.userName}" />
					</t:column>
					<%--<h:column id="maintainEDMS05">
						<h:commandLink id="RouteToLink"							action="#{EDMSDefaultsControllerBean.editEDMSDefaults}"							onmousedown="javascript:flagWarn=false;">
							<h:outputText id="valueRouteTo" style="width:65px"								value="#{edmsDefaultsDetails.edmsWorkUnitVO.userName}" />
							<f:param name="indexCode" value="#{rowIndex}" />
						</h:commandLink>
						<h:outputText id="valueRouteTo" style="width:65px"
							value="#{edmsDefaultsDetails.edmsWorkUnitVO.userName}" />
					</h:column>--%>
					<t:column>
						<f:facet name="header">
							<h:panelGrid columns="2" id="edmsDefaultsID33">
								<h:outputLabel id="dept" for="deptValue"
									value="#{edmsmtn['label.edmsdflts.department']}" />
								<h:panelGroup id="deptValue">
									<t:div style="width x;align=left" id="edmsDefaultsID34">
										<t:commandLink id="deptAscCmdLink"
											style="text-decoration: none;"
											actionListener="#{EDMSDefaultsControllerBean.sort}"
											onmousedown="javascript:flagWarn=false;"
											rendered="#{EDMSDefaultsDataBean.imageRender != 'deptAscCmdLink'}">
											<h:graphicImage id="PROVIDERGI20120731164811584" title="for ascending" styleClass="sort_asc"
												alt="" url="/images/x.gif" width="8" />
											<f:attribute name="columnName" value="department" />
											<f:attribute name="sortOrder" value="asc" />
										</t:commandLink>
									</t:div>
									<t:div style="width x;align=left" id="edmsDefaultsID35">
										<t:commandLink id="deptDscCmdLink"
											style="text-decoration: none;"
											actionListener="#{EDMSDefaultsControllerBean.sort}"
											onmousedown="javascript:flagWarn=false;"
											rendered="#{EDMSDefaultsDataBean.imageRender != 'deptDscCmdLink'}">
											<h:graphicImage id="PROVIDERGI20120731164811585" title="for descending" styleClass="sort_desc"
												alt="" url="/images/x.gif" width="8" />
											<f:attribute name="columnName" value="department" />
											<f:attribute name="sortOrder" value="desc" />
										</t:commandLink>
									</t:div>
								</h:panelGroup>
							</h:panelGrid>
						</f:facet>
						<h:outputText id="valueDept" style="width:62px"
							value="#{edmsDefaultsDetails.deptName}" />
					</t:column>
					<%--<h:column id="maintainEDMS06">
						<h:commandLink id="DeptLink"							action="#{EDMSDefaultsControllerBean.editEDMSDefaults}"							onmousedown="javascript:flagWarn=false;">
							<h:outputText id="valueDept" style="width:62px"								value="#{edmsDefaultsDetails.deptName}" />
							<f:param name="indexCode" value="#{rowIndex}" />
						</h:commandLink>
						<h:outputText id="valueDept" style="width:62px"
							value="#{edmsDefaultsDetails.deptName}" />
					</h:column>--%>
				</t:dataTable>
				<m:div styleClass="searchResults">
					<t:dataScroller pageCountVar="pageCount" pageIndexVar="pageIndex"
						onclick="flagWarn=false;" paginator="true"
						paginatorActiveColumnStyle='font-weight:bold;'
						paginatorMaxPages="3" immediate="false" for="edmsDefaultsData"
						firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
						rowsCountVar="rowsCount" id="edmsDefaultsID36"
						style="float:right;position:relative;bottom:-6px;text-decoration:underline;">
						<f:facet name="previous">
							<h:outputText style="text-decoration:underline;"
								id="edmsDefaultsID37" value=" #{ref['label.ref.lt']} "
								rendered="#{pageIndex > 1}"></h:outputText>
						</f:facet>
						<f:facet name="next">
							<h:outputText style="text-decoration:underline;"
								id="edmsDefaultsID38" value=" #{ref['label.ref.gt']} "
								rendered="#{pageIndex < pageCount}"></h:outputText>
						</f:facet>
						<h:outputText id="edmsDefaultsID39"
							value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
							style="float:left;position:relative;bottom:-6px;font-weight:bold;"
							rendered="#{rowsCount>0}">
						</h:outputText>
					</t:dataScroller>
				</m:div>
				<m:br id="brID1" />
				<m:br id="brID2" />

				<m:div styleClass="moreInfo" id="edmsDefaultsID40"
					rendered="#{EDMSDefaultsDataBean.renderEditEDMSDefaults}">

					<m:div styleClass="moreInfoBar" id="edmsDefaultsID41">

						<m:div styleClass="infoTitle" id="edmsDefaultsID42">
							<h:outputText id="edmsDefaultsID43"
								value="#{edmsmtn['title.edmsdflts.contactMgmt_EditEDMSDefaults']}" />
						</m:div>
						<m:div styleClass="infoActions" id="edmsDefaultsID44">
							<m:div styleClass="infoActions" id="edmsDefaultsID45">
								<h:panelGroup id="edmsDefaultsID46">
									<%--<h:commandLink styleClass="strong" id="edmsDefaultsID48"											action="#{EDMSDefaultsControllerBean.updateEDMSDefaults}"											rendered="#{EDMSDefaultsControllerBean.controlRequired}"											onmousedown="javascript:flagWarn=false; ">
											<h:outputText value="#{ent['label-sw-save']}" id="outputTextID1"/>
										</h:commandLink>--%>
									<h:commandButton styleClass="strong"
										style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:44px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"
										onmousedown="javascript:flagWarn=false; "
										disabled="#{!EDMSDefaultsControllerBean.controlRequired}"
										value="#{ent['label-sw-save']}" id="outputTextID1"
										action="#{EDMSDefaultsControllerBean.updateEDMSDefaults}" />
									<h:outputText id="edmsDefaultsID49" escape="false"
										value="#{ref['label.ref.linkSeperator']}" />


									<%--<h:commandLink styleClass="commandLink" id="edmsDefaultsID50"											action="#{EDMSDefaultsControllerBean.reset}"											rendered="#{EDMSDefaultsControllerBean.controlRequired}"											onmousedown="javascript:flagWarn=false;">
											<h:outputText value="#{ent['label-sw-reset']}" id="outputTextID2"/>
										</h:commandLink>--%>
									<h:commandButton
										style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:44px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"
										onmousedown="javascript:flagWarn=false; "
										disabled="#{!EDMSDefaultsControllerBean.controlRequired}"
										value="#{ent['label-sw-reset']}" id="outputTextID2"
										action="#{EDMSDefaultsControllerBean.reset}" />

									<h:outputText id="maintainEDMS09" escape="false"
										value="#{ref['label.ref.linkSeperator']}" />

									<h:commandButton
										onmousedown="if(event.button==1) flagWarn=false;"
										style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:55px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"
										value="#{ent['label-sw-auditLog']}" id="outputTextID261"
										action="#{EDMSDefaultsControllerBean.doAuditKeyListOperation}" />

									<h:outputText escape="false"
										value="#{ref['label.ref.linkSeperator']}"
										id="edmsDefaultsID501" />
										
										<%--Code Modified Below For Fixing The Defect ESPRD00762526 on 03/01/2012--%>

									<%--<h:commandLink styleClass="commandLink" id="edmsDefaultsID52"
										action="#{EDMSDefaultsControllerBean.cancel}"
										onmousedown="javascript:flagWarn=true;">
										<h:outputText value="#{ent['label-sw-cancel']}"
											id="outputTextID3" />
									</h:commandLink>  --%>
									
									<h:commandButton id="outputTextID3"
													 style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:44px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"
													 value="#{ent['label-sw-cancel']}"
													 onclick="flagWarn=true;"  
										             action="#{EDMSDefaultsControllerBean.cancel}" />

								</h:panelGroup>
							</m:div>
						</m:div>
					</m:div>
					<m:div styleClass="moreInfoContent" id="edmsDefaultsID53">
						<m:div styleClass="padb" style="width:100%" id="edmsDefaultsID54">
							<m:div styleClass="msgBox" id="edmsDefaultsID55"
								rendered="#{EDMSDefaultsDataBean.showSucessMessage}">
								<h:outputText value="#{ent['label-sw-success']}"
									id="outputTextID4" />
							</m:div>
							<m:div style="color: red" id="error1"
								rendered="#{EDMSDefaultsDataBean.showErrorMessage}">
								<h:outputText value="#{ent['label-sw-error']}" id="error2" />
							</m:div>
							<m:table cellspacing="0" width="50%" id="edmsDefaultsID56">
								<m:tr id="edmsDefaultsID57">
									<m:td id="edmsDefaultsID58">
										<h:outputLabel id="edmsDocTypeLabel" for="edmsDocType"
											value="#{edmsmtn['label.edmsdflts.documentType']}" />
										<m:br id="brID3"></m:br>
										<h:outputText id="edmsDocType"
											value="#{EDMSDefaultsDataBean.edmsDefaultsVO.documentType}" />
									</m:td>
									<m:td id="edmsDefaultsID60">
										<h:outputLabel id="edmsTypeLabel" for="edmsType"
											value="#{edmsmtn['label.edmsdflts.edmsType']}" />
										<m:br id="brID4"></m:br>
										<h:selectOneMenu id="edmsType"
											valueChangeListener="#{EDMSDefaultsControllerBean.getCaseOrCRDetails}"
											onchange="flagWarn=false;"
											value="#{EDMSDefaultsDataBean.edmsDefaultsVO.edmsDefaultTypeFromUI}">
											<f:selectItems id="edmsTypeID1"
												value="#{EDMSDefaultsDataBean.edmsValidValues}" />
										</h:selectOneMenu>
										<hx:behavior id="edmsTypeID2" event="onchange"
											behaviorAction="get" targetAction="edmsDefaultSectionID"
											target="edmsType"></hx:behavior>
									</m:td>
								</m:tr>
							</m:table>
							<h:panelGroup id="edmsDefaultSectionID">
								<m:table width="100%" id="edmsDefaultsID62">
									<m:tr id="edmsDefaultsID63">
										<m:td id="edmsDefaultsID64">
											<m:div rendered="#{EDMSDefaultsDataBean.renderCase}"
												id="edmsDefaultsID65">
												<m:section styleClass="inner" id="edmsDefaultsID66">
													<m:legend id="edmsDefaultsID67">
														<h:outputText value="#{edmsmtn['label.edmsdflts.case']}"
															id="edmsDefaultsID68" />
													</m:legend>
													<m:table cellspacing="0" width="100%" id="edmsDefaultsID69">
														<m:tr id="edmsDefaultsID70">
															<m:td id="edmsDefaultsID71">
																<m:div styleClass="padb" id="edmsDefaultsID72">
																	<m:span styleClass="required" id="edmsDefaultsID73">
																		<h:outputText value="#{ref['label.ref.reqSymbol']}"
																			id="edmsDefaultsID74" />
																	</m:span>
																	<h:outputLabel id="caseTypeLabel" for="caseType"
																		value="#{edmsmtn['label.edmsdflts.caseType']}" />
																	<m:br id="brID5"></m:br>
																	<h:selectOneMenu id="caseType"
																		valueChangeListener="#{EDMSDefaultsControllerBean.getCaseTypeDetails}"
																		onchange="this.form.submit();" immediate="true"
																		value="#{EDMSDefaultsDataBean.edmsDefaultsVO.caseTypeSk}">
																		<f:selectItem itemValue=" " id="caseTypeID1"
																			itemLabel="Please Select One" />
																		<f:selectItems id="caseTypeID2"
																			value="#{EDMSDefaultsDataBean.caseTypes}" />
																	</h:selectOneMenu>
																	<m:br id="brID6"></m:br>
																	<h:message for="caseType" showDetail="true"
																		id="edmsDefaultsID75" style="color: red" />
																</m:div>
															</m:td>
															<m:td id="edmsDefaultsID76">
																<m:div styleClass="padb" id="edmsDefaultsID77">
																	<m:span styleClass="required" id="edmsDefaultsID78">
																		<h:outputText value="#{ref['label.ref.reqSymbol']}"
																			id="edmsDefaultsID79" />
																	</m:span>
																	<h:outputLabel id="caseStatusLabel" for="caseStatus"
																		value="#{edmsmtn['label.edmsdflts.status']}" />
																	<m:br id="brID23"></m:br>
																	<h:selectOneMenu id="caseStatus"
																		value="#{EDMSDefaultsDataBean.edmsDefaultsVO.statusCode}">
																		<f:selectItem itemValue=" " id="caseStatusID1"
																			itemLabel="Please Select One" />
																		<f:selectItems id="caseStatusID2"
																			value="#{EDMSDefaultsDataBean.statCodeValidValues}" />

																	</h:selectOneMenu>
																	<m:br id="brID7"></m:br>
																	<h:message for="caseStatus" showDetail="true"
																		id="edmsDefaultsID80" style="color: red" />
																</m:div>
															</m:td>
															<m:td id="edmsDefaultsID81">
																<m:div styleClass="padb" id="edmsDefaultsID82">
																	<m:span styleClass="required" id="edmsDefaultsID83">
																		<h:outputText value="#{ref['label.ref.reqSymbol']}"
																			id="edmsDefaultsID84" />
																	</m:span>
																	<h:outputLabel id="routeToLabel" for="caseRouteTo"
																		value="#{edmsmtn['label.edmsdflts.routeTo']}" />
																	<m:br id="brID8"></m:br>
																	<h:selectOneMenu id="caseRouteTo"
																		valueChangeListener="#{EDMSDefaultsControllerBean.getDeptForResUser}"
																		onchange="this.form.submit();"
																		value="#{EDMSDefaultsDataBean.edmsDefaultsVO.caseUserWorkUnitSkStr}">
																		<f:selectItem itemValue=" " id="caseRouteToID1"
																			itemLabel="Please Select One" />
																		<f:selectItems id="caseRouteToID2"
																			value="#{EDMSDefaultsDataBean.caseRouteToList}" />
																	</h:selectOneMenu>
																	<m:br id="brID9"></m:br>
																	<h:message id="PRGCMGTM186" for="caseRouteTo"
																		showDetail="true" style="color: red" />
																</m:div>
															</m:td>
															<m:td id="edmsDefaultsID85">
																<m:div styleClass="padb" id="edmsDefaultsID86">
																	<h:outputLabel id="deptLabel" for="caseDept"
																		value="#{edmsmtn['label.edmsdflts.department']}" />
																	<m:br id="brID10"></m:br>
																	<h:selectOneMenu id="caseDept"
																		disabled="#{EDMSDefaultsDataBean.renderProtected}"
																		value="#{EDMSDefaultsDataBean.edmsDefaultsVO.deptWorkUnitSK}">
																		<f:selectItems
																			value="#{EDMSDefaultsDataBean.departmentsList}" />
																	</h:selectOneMenu>
																	<m:br id="brID11"></m:br>
																	<h:message for="caseDept" showDetail="true"
																		id="edmsDefaultsID87" style="color: red" />
																</m:div>
															</m:td>
														</m:tr>
													</m:table>
												</m:section>
											</m:div>
											<m:div
												rendered="#{EDMSDefaultsDataBean.renderCorrespondence}"
												id="edmsDefaultsID88">
												<m:section styleClass="inner" id="edmsDefaultsID89">
													<m:legend id="edmsDefaultsID90">
														<h:outputText id="edmsDefaultsID91"
															value="#{edmsmtn['label.edmsdflts.correspondence']}" />
													</m:legend>
													<h:panelGroup id="categoryForSubjectsPanelID">
														<m:table cellspacing="0" width="100%"
															id="edmsDefaultsID92">
															<m:tr id="edmsDefaultsID93">
																<m:td id="edmsDefaultsID94">
																	<m:div styleClass="padb" id="edmsDefaultsID95">
																		<m:span styleClass="required" id="edmsDefaultsID96">
																			<h:outputText value="#{ref['label.ref.reqSymbol']}"
																				id="edmsDefaultsID97" />
																		</m:span>
																		<h:outputLabel id="categoryLabel"
																			for="categoryForSubjects"
																			value="#{edmsmtn['label.edmsdflts.category']}" />
																		<m:br id="brID12"></m:br>

																		<h:selectOneMenu id="categoryForSubjects"
																			valueChangeListener="#{EDMSDefaultsControllerBean.getSubjectsForCategory}"
																			onchange="flagWarn=false;"
																			value="#{EDMSDefaultsDataBean.edmsDefaultsVO.categorySK}">
																			<f:selectItem itemValue=" "
																				id="categoryForSubjectsID1"
																				itemLabel="Please Select One" />
																			<f:selectItems id="categoryForSubjectsID2"
																				value="#{EDMSDefaultsDataBean.categoryList}" />
																		</h:selectOneMenu>
																		<hx:behavior id="categoryForSubjectsID"
																			event="onchange" behaviorAction="get"
																			targetAction="categoryForSubjectsPanelID"
																			target="categoryForSubjects"></hx:behavior>
																		<m:br id="brID13"></m:br>
																		<h:message for="categoryForSubjects" showDetail="true"
																			id="edmsDefaultsID98" style="color: red" />
																	</m:div>
																</m:td>
																<m:td id="edmsDefaultsID99">
																	<m:div styleClass="padb" id="edmsDefaultsID100">
																		<m:span styleClass="required" id="edmsDefaultsID101">
																			<h:outputText value="#{ref['label.ref.reqSymbol']}"
																				id="edmsDefaultsID102" />
																		</m:span>
																		<h:outputLabel id="subjectLabel" for="subject"
																			value="#{edmsmtn['label.edmsdflts.subject']}" />
																		<m:br id="brID14"></m:br>
																		<h:selectOneMenu id="subject"
																			value="#{EDMSDefaultsDataBean.edmsDefaultsVO.subjectCode}">
																			<f:selectItem itemValue=" " id="subjectID1"
																				itemLabel="Please Select One" />
																			<f:selectItems id="subjectID2"
																				value="#{EDMSDefaultsDataBean.categorySubjectValues}" />
																		</h:selectOneMenu>
																		<m:br id="brID15"></m:br>
																		<h:message for="subject" showDetail="true"
																			id="edmsDefaultsID103" style="color: red" />
																	</m:div>
																</m:td>
																<m:td id="edmsDefaultsID104">
																	<m:div styleClass="padb" id="edmsDefaultsID105">
																		<m:span styleClass="required" id="edmsDefaultsID106">
																			<h:outputText value="#{ref['label.ref.reqSymbol']}"
																				id="edmsDefaultsID107" />
																		</m:span>
																		<h:outputLabel id="crStatusLabel" for="crStatus"
																			value="#{edmsmtn['label.edmsdflts.status']}" />
																		<m:br id="brID16"></m:br>
																		<h:selectOneMenu id="crStatus"
																			value="#{EDMSDefaultsDataBean.edmsDefaultsVO.statusCode}">
																			<f:selectItem itemValue=" " id="crStatusID1"
																				itemLabel="Please Select One" />
																			<f:selectItems id="crStatusID2"
																				value="#{EDMSDefaultsDataBean.statCodeValidValues}" />
																		</h:selectOneMenu>
																		<m:br id="brID17"></m:br>
																		<h:message for="crStatus" showDetail="true"
																			id="edmsDefaultsID108" style="color: red" />
																	</m:div>
																</m:td>
																<m:td id="edmsDefaultsID109">
																	<m:div styleClass="padb" id="edmsDefaultsID110">
																		<m:span styleClass="required" id="edmsDefaultsID111">
																			<h:outputText value="#{ref['label.ref.reqSymbol']}"
																				id="edmsDefaultsID112" />
																		</m:span>
																		<h:outputLabel id="crRouteToLabel" for="crRouteTo"
																			value="#{edmsmtn['label.edmsdflts.routeTo']}" />
																		<m:br id="brID18"></m:br>
																		<h:selectOneMenu id="crRouteTo"
																			valueChangeListener="#{EDMSDefaultsControllerBean.getDeptForResUser}"
																			onchange="flagWarn=false;"
																			value="#{EDMSDefaultsDataBean.edmsDefaultsVO.userWorkUnitSkStr}">
																			<f:selectItem itemValue=" " id="crRouteToID1"
																				itemLabel="Please Select One" />
																			<f:selectItems id="crRouteToID2"
																				value="#{EDMSDefaultsDataBean.routeToList}" />
																		</h:selectOneMenu>
																		<hx:behavior id="crRouteToBehaviorID" event="onchange"
																			behaviorAction="get"
																			targetAction="categoryForSubjectsPanelID"
																			target="crRouteTo"></hx:behavior>
																		<m:br id="brID19"></m:br>
																		<h:message for="crRouteTo" showDetail="true"
																			id="edmsDefaultsID113" style="color: red" />
																	</m:div>
																</m:td>
																<m:td id="edmsDefaultsID114">
																	<m:div styleClass="padb" id="edmsDefaultsID115">
																		<h:outputLabel id="crDeptLabel" for="ProCrDept"
																			value="#{edmsmtn['label.edmsdflts.department']}" />
																		<m:br id="brID20"></m:br>
																		<h:selectOneMenu id="ProCrDept"
																			disabled="#{EDMSDefaultsDataBean.renderProtected}"
																			value="#{EDMSDefaultsDataBean.edmsDefaultsVO.deptWorkUnitSK}">

																			<f:selectItems id="ProCrDeptID2"
																				value="#{EDMSDefaultsDataBean.departmentsList}" />
																		</h:selectOneMenu>
																		<m:br id="brID21"></m:br>
																		<h:message for="ProCrDept" showDetail="true"
																			id="edmsDefaultsID116" style="color: red" />
																	</m:div>
																</m:td>
															</m:tr>
														</m:table>
													</h:panelGroup>
													<hx:ajaxRefreshSubmit id="ajaxCategoryForSubjects"
														target="categoryForSubjectsPanelID">
													</hx:ajaxRefreshSubmit>
												</m:section>
											</m:div>
											<m:div id="edmsDefaultsID118">
												<h:outputText escape="false"
													value="#{ref['label.ref.singleSpace']}"
													id="edmsDefaultsID117" />
											</m:div>
										</m:td>
									</m:tr>
								</m:table>
							</h:panelGroup>
							<hx:ajaxRefreshSubmit id="ajaxEdmsDefaultSectionID"
								target="edmsDefaultSectionID">
							</hx:ajaxRefreshSubmit>
							<m:div
								rendered="#{not empty EDMSDefaultsDataBean.edmsDefaultsVO.documentType}"
								id="edmsDefaultsID119">

								<f:subview id="edmsAuditlogDetails">
									<m:div rendered="#{EDMSDefaultsDataBean.auditLogFlag}">
										<audit:auditTableSet id="edmsDefaultAuditId"
											value="#{EDMSDefaultsDataBean.edmsDefaultsVO.auditKeyList}"
											numOfRecPerPage="10"></audit:auditTableSet>
									</m:div>
								</f:subview>


							</m:div>
						</m:div>
					</m:div>
				</m:div>
			</h:form>
		</hx:scriptCollector>
	</m:section>

	<%-- var thisForm : stores the form reference. Form reference is use to get  the computed  role stored in hidden form field.
         FlagRole : Indicator to store role information .Stores 'False' for inquire only users and 'True' for other user.
	--%>
	<script type="text/JavaScript">

   var thisForm = 'view<portlet:namespace/>:frm';

   flagRole =((typeof (document.getElementById(thisForm+': controlRequiredForEDMS) !=  'undefined')) && document.getElementById(thisForm+':controlRequiredForEDMS) != null ?  document.getElementById(thisForm+':controlRequiredForEDMS).value:true);

</script>
	</body>
</f:view>
