<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<m:section styleClass="casebg" id="sec1">
	<m:legend id="leg1">
		<h:outputText styleClass="strong" id="leg2"
			value="#{msg['title.case.associatedCaseAndCorrespondence.case']}" />
	</m:legend>
	<m:div rendered="#{logCaseDataBean.showCaseSearchScreen}" id="div1">
	<%--<h:outputText id="PRGCMGTOT1765"  styleClass="colorRed" value="#{msg['label.searchCase.noData']}" rendered="#{caseSearchDataBean.caseAsctErrormsg}"/>--%>
	<m:div id="LOGCASESEARCHERRMESGDIV" styleClass="width:100%">
			<h:inputHidden id="LOGCASESEARCHERRMESGIDHID" value=" "></h:inputHidden>
			<h:message id="PROVIDERM20120731164811289" for="LOGCASESEARCHERRMESGIDHID" styleClass="errorMessage"></h:message>
	</m:div>
		<f:subview id="logCaseAssCaseSubView1">
			<m:anchor name="caseCaseSearchPage"></m:anchor>
			<jsp:include page="inc_caseSearch.jsp" />
		</f:subview>
	</m:div>

	<m:table width="100%" id="t1">
		<m:tr id="t1tr1">
			<m:td styleClass="rightCell" id="t1_tr1_td1">

				<h:commandButton styleClass="formBtn" id="associatedCaseId"
					onclick="javascript:flagWarn=false;focusThis('cancelCaseSearchScreenId');focusPage('caseCaseSearchPage');"
					disabled="#{logCaseDataBean.disableScreenField}"
					value="#{msg['label.case.associatedCaseAndCorrespondence.search']}"
					action="#{logCaseControllerBean.showCaseSearchScreen}" />

			</m:td>
		</m:tr>
	</m:table>

	<m:div id="corrDiv2" rendered="#{!logCaseDataBean.showCaseType}">

		<m:table width="100%" styleClass="tableBar" id="tab2">
			<m:div align="right" id="tab2_div1">

				<m:td id="tab2Col1Id">
					<t:commandLink id="selAllExistCaseRecordsCommand"
						onclick="javascript:flagWarn=false;"
						onmouseover="if(#{logCaseDataBean.existCaseSelectAll}==true){this.style.cursor='default';}"
						action="#{logCaseControllerBean.selectAllExistingCaseRecords}">
						<h:selectBooleanCheckbox id="selAllExistCaseRecords"
							onmousedown="javascript:flagWarn=false;focusPage('caseRecAssWithCaseTableFocusPage');"
							value="#{logCaseDataBean.existCaseSelectAll}"
							disabled="#{logCaseDataBean.existCaseSelectAll}">
						</h:selectBooleanCheckbox>
					</t:commandLink>
					<h:outputLabel for="selAllExistCaseRecords"
						value="#{msg['label.case.associatedCaseAndCorrespondence.selAllRows']}"
						id="selAllRowsOLId" />
				</m:td>
				<m:td id="tab2Col2Id">
					<t:commandLink id="deSelAllExistCaseRecordsCommand"
						onclick="javascript:flagWarn=false;"
						onmouseover="if(#{logCaseDataBean.existCaseDeSelectAll}==true){this.style.cursor='default';}"
						action="#{logCaseControllerBean.deSelectAllExistingCaseRecords}">
						<h:selectBooleanCheckbox id="deSelAllExistCaseRecords"
							onmousedown="javascript:flagWarn=false;focusPage('caseExistingRecordsHeader');"
							value="#{logCaseDataBean.existCaseDeSelectAll}"
							disabled="#{logCaseDataBean.existCaseDeSelectAll}">
						</h:selectBooleanCheckbox>
					</t:commandLink>
					<h:outputLabel for="deSelAllExistCaseRecords" id="deSelAllRowsOLId"
						value="#{msg['label.case.associatedCaseAndCorrespondence.deSelAllRows']}" />
				</m:td>

			</m:div>
		</m:table>

		<t:htmlTag value="h3" id="caseHtmlTag1">
			<h:outputText id="existingCaseRecordsOTId"
				value="#{msg['label.case.associatedCaseAndCorrespondence.existingCaseRecords']}" />
		</t:htmlTag>
		<m:anchor name="caseExistingRecordsHeader"></m:anchor>
		<t:dataTable id="existingCaseDataTable"
			first="#{logCaseDataBean.caseExistingCaseRecordsRowIndex}"
			rendered="#{logCaseDataBean.showExistingCaseRecordsDataTable}"
			border="1" cellpadding="0" cellspacing="0"
			columnClasses="columnClass" headerClass="headerClass" rows="10"
			footerClass="footerClass" rowClasses="row_alt,rowone"
			styleClass="dataTable" width="100%" var="existingCaseSpanResult"
			value="#{logCaseDataBean.existingCaseRecordsList}"
			onmouseover="return tableMouseOver(this, event);"
			onmouseout="return tableMouseOut(this, event);">

			<t:column id="existingCaseIDcolumn1">
				<f:facet name="header">
					<h:panelGrid columns="2" id="assocCrrpID">
						<h:outputLabel id="assocCrrpIDLevel"
							value="#{msg['label.case.associatedCaseAndCorrespondence.ID']}"
							for="existingCaseIDCommandLink1" />
						<h:panelGroup id="assocCrrpIDPanelGrp">
							<t:div id="CaseAssociatedCaseTDiv001" styleClass="alignLeft">
							<t:commandLink id="existingCaseIDCommandLink1"
								onmousedown="javascript:flagWarn=false;focusPage('caseExistingRecordsHeader');" styleClass="clStyle"
								actionListener="#{logCaseControllerBean.sortExistingCase}"
								rendered="#{logCaseDataBean.imageRender != 'existingCaseIDCommandLink1'}">
								<m:div title="#{msg['alt.for.ascending']}"
									styleClass="sort_asc" 
									id="assocCrrpAscImgId" />
								<f:attribute name="columnName" value="existingCase_ID" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
							</t:div>
							<t:div id="CaseAssociatedCaseTDiv002" styleClass="alignLeft">
							<t:commandLink id="existingCaseIDCommandLink2"
								onmousedown="javascript:flagWarn=false;focusPage('caseExistingRecordsHeader');" styleClass="clStyle"
								actionListener="#{logCaseControllerBean.sortExistingCase}"
								rendered="#{logCaseDataBean.imageRender != 'existingCaseIDCommandLink2'}">
								<m:div title="#{msg['alt.for.descending']}"
									id="assocCrrpDscImgId" styleClass="sort_desc"  />
								<f:attribute name="columnName" value="existingCase_ID" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>
				</f:facet>
				<h:outputText value="#{existingCaseSpanResult.caseID}"
					id="assCrpIDOT" />
			</t:column>

			<t:column id="existingCasecolumn2">
				<f:facet name="header">
					<h:panelGrid columns="2" id="assocCrrpCrDt">
						<h:outputLabel id="assocCrrpCrDtLebl"
							value="#{msg['label.case.associatedCaseAndCorrespondence.createdDate']}"
							for="existingCasecreatedDateCommandLink1" />
						<h:panelGroup id="assocCrrpCrDtPGrpId">
							<t:div id="CaseAssociatedCaseTDiv003" styleClass="alignLeft">
							<t:commandLink id="existingCasecreatedDateCommandLink1"
								onmousedown="javascript:flagWarn=false;focusPage('caseExistingRecordsHeader');" styleClass="clStyle"
								actionListener="#{logCaseControllerBean.sortExistingCase}"
								rendered="#{logCaseDataBean.imageRender != 'existingCasecreatedDateCommandLink1'}">
								<m:div title="#{msg['alt.for.ascending']}"
									id="assocCrrpCrDtImgAscId" styleClass="sort_asc"  />
								<f:attribute name="columnName" value="existingCase_CreatedDate" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
							</t:div>
							<t:div id="CaseAssociatedCaseTDiv004" styleClass="alignLeft">
							<t:commandLink id="existingCasecreatedDateCommandLink2"
								onmousedown="javascript:flagWarn=false;focusPage('caseExistingRecordsHeader');" styleClass="clStyle"
								actionListener="#{logCaseControllerBean.sortExistingCase}"
								rendered="#{logCaseDataBean.imageRender != 'existingCasecreatedDateCommandLink2'}">
								<m:div title="#{msg['alt.for.descending']}"
									id="assocCrrpCrDtImgDscId" styleClass="sort_desc" />
								<f:attribute name="columnName" value="existingCase_CreatedDate" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>
				</f:facet>
				<h:outputText value="#{existingCaseSpanResult.createdDateStr}"
					id="assocCrrpCrDtOutTxt" />
			</t:column>

			<t:column id="existingCasecolumn3">
				<f:facet name="header">
					<h:panelGrid columns="2" id="assocCrrpStatusPId">
						<h:outputLabel id="assocCrrpStatusPLeblId"
							value="#{msg['label.case.associatedCaseAndCorrespondence.status']}"
							for="existingCasestatusCommandLink1" />
						<h:panelGroup id="assocCrrpStatusPGrpId">
							<t:div id="CaseAssociatedCaseTDiv005" styleClass="alignLeft">
							<t:commandLink id="existingCasestatusCommandLink1"
								onmousedown="javascript:flagWarn=false;focusPage('caseExistingRecordsHeader');" styleClass="clStyle"
								actionListener="#{logCaseControllerBean.sortExistingCase}"
								rendered="#{logCaseDataBean.imageRender != 'existingCasestatusCommandLink1'}">
								<m:div title="#{msg['alt.for.ascending']}"
									id="assocCrrpStatusImgAscId" styleClass="sort_asc" />
								<f:attribute name="columnName" value="existingCase_Status" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
							</t:div>
							<t:div id="CaseAssociatedCaseTDiv006" styleClass="alignLeft">
							<t:commandLink id="existingCasestatusCommandLink2"
								onmousedown="javascript:flagWarn=false;focusPage('caseExistingRecordsHeader');" styleClass="clStyle"
								actionListener="#{logCaseControllerBean.sortExistingCase}"
								rendered="#{logCaseDataBean.imageRender != 'existingCasestatusCommandLink2'}">
								<m:div title="#{msg['alt.for.descending']}"
									id="assocCrrpStatusImgDscId" styleClass="sort_desc"/>
								<f:attribute name="columnName" value="existingCase_Status" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>
				</f:facet>
				<h:outputText value="#{existingCaseSpanResult.statusstr}"
					id="assocCrrpStatusOTId" />
			</t:column>

			<t:column id="existingCasecolumn4">
				<f:facet name="header">
					<h:panelGrid columns="2" id="assocCrrpCaseTypePId">
						<h:outputLabel id="assocCrrpCaseTypeLevId"
							value="#{msg['label.case.associatedCaseAndCorrespondence.caseType']}"
							for="existingCasecaseTypeCommandLink1" />
						<h:panelGroup id="assocCrrpCaseTypePGrpId">
							<t:div id="CaseAssociatedCaseTDiv007" styleClass="alignLeft">
							<t:commandLink id="existingCasecaseTypeCommandLink1"
								onmousedown="javascript:flagWarn=false;focusPage('caseExistingRecordsHeader');" styleClass="clStyle"
								actionListener="#{logCaseControllerBean.sortExistingCase}"
								rendered="#{logCaseDataBean.imageRender != 'existingCasecaseTypeCommandLink1'}">
								<m:div title="#{msg['alt.for.ascending']}"
									id="assocCrrpCaseTypeImgAscId" styleClass="sort_asc"/>
								<f:attribute name="columnName" value="existingCase_CaseType" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
							</t:div>
							<t:div id="CaseAssociatedCaseTDiv008" styleClass="alignLeft">
							<t:commandLink id="existingCasecaseTypeCommandLink2"
								onmousedown="javascript:flagWarn=false;focusPage('caseExistingRecordsHeader');" styleClass="clStyle"
								actionListener="#{logCaseControllerBean.sortExistingCase}"
								rendered="#{logCaseDataBean.imageRender != 'existingCasecaseTypeCommandLink2'}">
								<m:div title="#{msg['alt.for.descending']}"
									id="assocCrrpCaseTypeImgDscId" styleClass="sort_desc"/>
								<f:attribute name="columnName" value="existingCase_CaseType" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>
				</f:facet>
				<h:outputText value="#{existingCaseSpanResult.caseType}"
					id="assocCaseCaseTypeOTId" />
			</t:column>
			<t:column id="existingCasecolumn5">
				<f:facet name="header">
					<h:panelGrid columns="2" id="assocCrrpLinkPId">
						<h:outputLabel id="assocCrrpLinkLeblId"
							value="#{msg['label.case.associatedCaseAndCorrespondence.link']}"
							for="existingCaselinkCommandLink1" />
						<h:panelGroup id="assocCrrpLinkPGrpId">
							<t:div id="CaseAssociatedCaseTDiv009" styleClass="alignLeft">
							<t:commandLink id="existingCaselinkCommandLink1"
								onmousedown="javascript:flagWarn=false;focusPage('caseExistingRecordsHeader');" styleClass="clStyle"
								actionListener="#{logCaseControllerBean.sortExistingCase}"
								rendered="#{logCaseDataBean.imageRender != 'existingCaselinkCommandLink1'}">
								<m:div title="#{msg['alt.for.ascending']}"
									id="assocCrrpLinkImgAscId" styleClass="sort_asc"/>
								<f:attribute name="columnName" value="existingCase_Link" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
							</t:div>
							<t:div id="CaseAssociatedCaseTDiv010" styleClass="alignLeft">
							<t:commandLink id="existingCaselinkCommandLink2"
								onmousedown="javascript:flagWarn=false;focusPage('caseExistingRecordsHeader');" styleClass="clStyle"
								actionListener="#{logCaseControllerBean.sortExistingCase}"
								rendered="#{logCaseDataBean.imageRender != 'existingCaselinkCommandLink2'}">
								<m:div title="#{msg['alt.for.descending']}"
									id="assocCrrpLinkImgDscId" styleClass="sort_desc"/>
								<f:attribute name="columnName" value="existingCase_Link" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>
				</f:facet>
				<h:outputLabel id="checkboxLCACColumnLabel1"  for="checkBoxr" styleClass="hide" value="#{msg['label.case.associatedCaseAndCorrespondence.link']}" ></h:outputLabel>
				<h:selectBooleanCheckbox id="checkBoxr"
					onclick="changeSelectAndDeSelectChkBox()"
					value="#{existingCaseSpanResult.caseLink}">
				</h:selectBooleanCheckbox>

			</t:column>
		</t:dataTable>

		<t:dataScroller for="existingCaseDataTable" paginator="true"
			rendered="#{logCaseDataBean.showExistingCaseRecordsDataTable}"
			onclick="javascript:flagWarn=false;focusPage('caseExistingRecordsHeader');"
			paginatorActiveColumnStyle='font-weight:bold;' paginatorMaxPages="3"
			immediate="false" pageCountVar="pageCount" pageIndexVar="pageIndex"
			firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
			rowsCountVar="rowsCount" styleClass="dataScroller"
			id="existingCaseDataTableDataControllerId">
			<f:facet name="previous">
				<h:outputText id="PRGCMGTOT622" styleClass="underline"
					value=" #{msg['label.lessthan']} " rendered="#{pageIndex > 1}"></h:outputText>
			</f:facet>
			<f:facet name="next">
				<h:outputText id="PRGCMGTOT623" styleClass="underline"
					value=" #{msg['label.greaterthan']} "
					rendered="#{pageIndex < pageCount}"></h:outputText>
			</f:facet>
			<h:outputText id="PRGCMGTOT624" rendered="#{rowsCount > 0}"
				value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
				styleClass="dataScrollerText" />
		</t:dataScroller>

		<h:dataTable var="dummyExistingCase"
			rendered="#{!logCaseDataBean.showExistingCaseRecordsDataTable}"
			styleClass="dataTable" cellspacing="0" width="100%" border="0"
			headerClass="tableHead" rowClasses="rowone,row_alt"
			id="ExistingCasedummyTable">
			<t:column id="dummyExistingCasecolumn1">
				<f:facet name="header">
					<h:outputText
						value="#{msg['label.case.associatedCaseAndCorrespondence.ID']}"
						id="ExistingCasedummyTableAscCorId" />
				</f:facet>
			</t:column>
			<t:column id="dummyExistingCasecolumn2">
				<f:facet name="header">
					<h:outputText
						value="#{msg['label.case.associatedCaseAndCorrespondence.createdDate']}"
						id="ExistingCasedummyTableCrDtId" />
				</f:facet>
			</t:column>
			<t:column id="dummyExistingCasecolumn3">
				<f:facet name="header">
					<h:outputText
						value="#{msg['label.case.associatedCaseAndCorrespondence.status']}"
						id="ExistingCasedummyTableStatusId" />
				</f:facet>
			</t:column>
			<t:column id="dummyExistingCasecolumn4">
				<f:facet name="header">
					<h:outputText
						value="#{msg['label.case.associatedCaseAndCorrespondence.caseType']}"
						id="ExistingCasedummyTableCaseTypeId" />
				</f:facet>
			</t:column>
			<t:column id="dummyExistingCasecolumn5">
				<f:facet name="header">
					<h:outputText
						value="#{msg['label.case.associatedCaseAndCorrespondence.link']}"
						id="ExistingCasedummyTableLinkId" />
				</f:facet>
			</t:column>
		</h:dataTable>
		<m:table styleClass="dataTable" width="100%" border="0"
			rendered="#{!logCaseDataBean.showExistingCaseRecordsDataTable}"
			id="noDataTabId">
			<m:tr id="noDataTabId_row">
				<m:td align="center" id="noDataTabId_col">
					<h:outputText value="#{msg['value.noData']}" id="noDatamsgId" />
				</m:td>
			</m:tr>
		</m:table>
	</m:div>
	<m:br/><m:br id="caseassocBr1" />
	<%-- ESPRD00328556 --%>
	<m:anchor name="caseRecAssWithCaseFocusPage"></m:anchor>
	<%-- <m:div styleClass="msgBox"
		rendered="#{logCaseControllerBean.deleteFlagForCaseRecAssoWithThisCase}"
		id="deleteFlagDivId">
		<h:messages layout="table" showDetail="true"
			id="deleteCaseAssoWithThisRecoSuccessMsgID" showSummary="false">
		</h:messages>
	</m:div>--%>
	<m:div id="LCSEASCTCASEDIV">
		<h:inputHidden id="LCSEASCTCASEDIVHDN" value=" "></h:inputHidden>
		<h:message id="PROVIDERM20120731164811290" for="LCSEASCTCASEDIVHDN" styleClass="errorMessage"></h:message>
	</m:div>
	<%-- EOF ESPRD00328556 --%>
	<m:br id="caseAssoIDCommandBR4" />
	<t:htmlTag value="h3" id="caseRecAssociateId">
		<h:outputText
			value="#{msg['label.case.associatedCaseAndCorrespondence.caseRecordsAssociatedWithThisCase']}"
			id="caseRecAssociateOTId" />
	</t:htmlTag>

	<m:anchor name="caseRecAssWithCaseTableFocusPage"></m:anchor>
	<t:dataTable value="#{logCaseDataBean.caseRecordsAssoWithCaseList}"
		rendered="#{logCaseDataBean.showCaseRecordsAssoWithCaseDataTable}"
		var="caseAssoSpanResult" styleClass="dataTable" cellspacing="0"
		width="100%" border="0" headerClass="tableHead" rowIndexVar="rowIndex"
		rowClasses="rowone,row_alt" rows="10" id="caseAssoDataTable"
		rowOnMouseOver="this.origClassName = this.className;this.className='row_mouse row_link';"
		rowOnMouseOut="if (this.origClassName) this.className = this.origClassName;"
		rowOnClick="javascript:childNodes[0].lastChild.click();"
		first="#{logCaseDataBean.caseRecordsWithThisCaeRowIndex}"
		onmousedown="javascript:flagWarn=false;">

		<t:column id="caseAssocolumn1">
			<f:facet name="header">
				<h:panelGrid columns="2" id="caseAssoDataTablePGrid1">
					<h:outputLabel id="caseAssocolumnOL1"
						value="#{msg['label.case.associatedCaseAndCorrespondence.ID']}"
						for="caseAssoIDCommandLink1" />
					<h:panelGroup id="caseAssoDataTablePGrp1">
						<t:div id="CaseAssociatedCaseTDiv011" styleClass="alignLeft">
						<t:commandLink id="caseAssoIDCommandLink1" styleClass="clStyle"
							onmousedown="javascript:flagWarn=false;focusPage('caseRecAssWithCaseTableFocusPage');"
							actionListener="#{logCaseControllerBean.sortCaseRecordsAssoWithCase}"
							rendered="#{logCaseDataBean.imageRender != 'caseAssoIDCommandLink1'}">
							<m:div title="#{msg['alt.for.ascending']}"
								id="caseAssocolumn1AscImgId" styleClass="sort_asc"/>
							<f:attribute name="columnName" value="caseAsso_ID" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
						</t:div>
						<t:div id="CaseAssociatedCaseTDiv012" styleClass="alignLeft">
						<t:commandLink id="caseAssoIDCommandLink2" styleClass="clStyle"
							onmousedown="javascript:flagWarn=false;focusPage('caseRecAssWithCaseTableFocusPage');"
							actionListener="#{logCaseControllerBean.sortCaseRecordsAssoWithCase}"
							rendered="#{logCaseDataBean.imageRender != 'caseAssoIDCommandLink2'}">
							<m:div title="#{msg['alt.for.descending']}"
								id="caseAssocolumn1DscImgId" styleClass="sort_desc"/>
							<f:attribute name="columnName" value="caseAsso_ID" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
						</t:div>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>
			<t:commandLink id="editLink023"
				onclick="javascript:flagWarn=false;focusPage('viewCaseRecordAssctdHeader');"
				action="#{logCaseControllerBean.viewCaseAssociatedWithThisCase}">
				<f:param name="rowindex" value="#{rowIndex}" />
				<h:outputText value="#{caseAssoSpanResult.caseID}"
					id="caseAssocCaseOTId1" />
			</t:commandLink>
			<h:outputText value="#{caseAssoSpanResult.caseID}"
				rendered="#{logCaseDataBean.disableScreenField}"
				id="caseAssoDisableCaseId" />
		</t:column>

		<t:column id="caseAssocolumn2">
			<f:facet name="header">
				<h:panelGrid columns="2" id="caseAssoDataTablePGrid2">
					<h:outputLabel id="caseAssocolumnOL2Id"
						value="#{msg['label.case.associatedCaseAndCorrespondence.createdDate']}"
						for="caseAssocreatedDateCommandLink1" />
					<h:panelGroup id="caseAssoDataTablePgrp2">
						<t:div id="CaseAssociatedCaseTDiv013" styleClass="alignLeft">
						<t:commandLink id="caseAssocreatedDateCommandLink1"
							onmousedown="javascript:flagWarn=false;focusPage('caseRecAssWithCaseTableFocusPage');" styleClass="clStyle"
							actionListener="#{logCaseControllerBean.sortCaseRecordsAssoWithCase}"
							rendered="#{logCaseDataBean.imageRender != 'caseAssocreatedDateCommandLink1'}">
							<m:div title="#{msg['alt.for.ascending']}"
								id="caseAssocolumnAscImg2Id" styleClass="sort_asc"/>
							<f:attribute name="columnName" value="caseAsso_CreateDate" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
						</t:div>
						<t:div id="CaseAssociatedCaseTDiv014" styleClass="alignLeft">
						<t:commandLink id="caseAssocreatedDateCommandLink2"
							onmousedown="javascript:flagWarn=false;focusPage('caseRecAssWithCaseTableFocusPage');" styleClass="clStyle"
							actionListener="#{logCaseControllerBean.sortCaseRecordsAssoWithCase}"
							rendered="#{logCaseDataBean.imageRender != 'caseAssocreatedDateCommandLink2'}">
							<m:div title="#{msg['alt.for.descending']}"
								id="caseAssocolumnDscImg2Id" styleClass="sort_desc" />
							<f:attribute name="columnName" value="caseAsso_CreateDate" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
						</t:div>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>
			<h:outputText value="#{caseAssoSpanResult.createdDateStr}"
				id="caseAssoCrtDtOTId" />
		</t:column>

		<t:column id="caseAssocolumn3">
			<f:facet name="header">
				<h:panelGrid columns="2" id="caseAssoDataTablePGrid3">
					<h:outputLabel id="caseStatusLbId"
						value="#{msg['label.case.associatedCaseAndCorrespondence.status']}"
						for="caseAssostatusCommandLink1" />
					<h:panelGroup id="caseAssoDataTablePgrp3">
						<t:div id="CaseAssociatedCaseTDiv015" styleClass="alignLeft">
						<t:commandLink id="caseAssostatusCommandLink1"
							styleClass="clStyle" onmousedown="javascript:flagWarn=false;focusPage('caseRecAssWithCaseTableFocusPage');"
							actionListener="#{logCaseControllerBean.sortCaseRecordsAssoWithCase}"
							rendered="#{logCaseDataBean.imageRender != 'caseAssostatusCommandLink1'}">
							<m:div title="#{msg['alt.for.ascending']}"
								id="caseStatusAscImgId" styleClass="sort_asc"/>
							<f:attribute name="columnName" value="caseAsso_Status" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
						</t:div>
						<t:div id="CaseAssociatedCaseTDiv016" styleClass="alignLeft">
						<t:commandLink id="caseAssostatusCommandLink2"
							onmousedown="javascript:flagWarn=false;focusPage('caseRecAssWithCaseTableFocusPage');" styleClass="clStyle"
							actionListener="#{logCaseControllerBean.sortCaseRecordsAssoWithCase}"
							rendered="#{logCaseDataBean.imageRender != 'caseAssostatusCommandLink2'}">
							<m:div title="#{msg['alt.for.descending']}"
								id="caseStatusDscImgId" styleClass="sort_desc"/>
							<f:attribute name="columnName" value="caseAsso_Status" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
						</t:div>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>
			<h:outputText value="#{caseAssoSpanResult.status}"
				id="caseStatusOutTxtId" />
		</t:column>

		<t:column id="caseAssocolumn4">
			<f:facet name="header">
				<h:panelGrid columns="2" id="caseAssoPG4">
					<h:outputLabel id="caseTypeOLId"
						value="#{msg['label.case.associatedCaseAndCorrespondence.caseType']}"
						for="caseAssocaseTypeCommandLink1" />
					<h:panelGroup id="caseAssoDataTablePgrp4">
						<t:div id="CaseAssociatedCaseTDiv017" styleClass="alignLeft">
						<t:commandLink id="caseAssocaseTypeCommandLink1"
							onmousedown="javascript:flagWarn=false;focusPage('caseRecAssWithCaseTableFocusPage');" styleClass="clStyle"
							actionListener="#{logCaseControllerBean.sortCaseRecordsAssoWithCase}"
							rendered="#{logCaseDataBean.imageRender != 'caseAssocaseTypeCommandLink1'}">
							<m:div title="#{msg['alt.for.ascending']}"
								id="caseTypeAscImgId" styleClass="sort_asc"/>
							<f:attribute name="columnName" value="caseAsso_CaseType" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
						</t:div>
						<t:div id="CaseAssociatedCaseTDiv018" styleClass="alignLeft">
						<t:commandLink id="caseAssocaseTypeCommandLink2"
							onmousedown="javascript:flagWarn=false;focusPage('caseRecAssWithCaseTableFocusPage');" styleClass="clStyle"
							actionListener="#{logCaseControllerBean.sortCaseRecordsAssoWithCase}"
							rendered="#{logCaseDataBean.imageRender != 'caseAssocaseTypeCommandLink2'}">
							<m:div title="#{msg['alt.for.descending']}"
								id="caseTypeDscImgId" styleClass="sort_desc"/>
							<f:attribute name="columnName" value="caseAsso_CaseType" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
						</t:div>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>
			<h:outputText value="#{caseAssoSpanResult.caseType}"
				id="caseTypeOutTxtId" />
		</t:column>
	</t:dataTable>
	<t:dataScroller for="caseAssoDataTable" paginator="true"
		onclick="javascript:flagWarn=false;focusPage('caseRecAssWithCaseTableFocusPage');"
		paginatorActiveColumnStyle='font-weight:bold;' paginatorMaxPages="3"
		immediate="false" pageCountVar="pageCount" pageIndexVar="pageIndex"
		firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
		rowsCountVar="rowsCount" styleClass="dataScroller"
		id="caseAssoDataTableDataScrl">
		<f:facet name="previous">
			<h:outputText styleClass="underline"
				id="caseAssoDataTableDataScrlPrevId"
				value=" #{msg['label.lessthan']} " rendered="#{pageIndex > 1}"></h:outputText>
		</f:facet>
		<f:facet name="next">
			<h:outputText styleClass="underline"
				id="caseAssoDataTableDataScrlNextId"
				value=" #{msg['label.greaterthan']} "
				rendered="#{pageIndex < pageCount}"></h:outputText>
		</f:facet>
		<h:outputText id="PROVIDEROLT20120731164811291" rendered="#{rowsCount > 0}"
			id="caseAssoDataTableDataScrlRowsCountId"
			value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
			styleClass="dataScrollerText" />
	</t:dataScroller>
	<h:dataTable var="dummyCaseAsso"
		rendered="#{!logCaseDataBean.showCaseRecordsAssoWithCaseDataTable}"
		styleClass="dataTable" cellspacing="0" width="100%" border="0"
		headerClass="tableHead" rowClasses="rowone,row_alt"
		id="CaseAssodummyTable">
		<t:column id="dummyCaseAssocolumn1">
			<f:facet name="header">
				<h:outputText id="CaseAssodummyTableCorrId"
					value="#{msg['label.case.associatedCaseAndCorrespondence.ID']}" />
			</f:facet>
		</t:column>
		<t:column id="dummyCaseAssocolumn2">
			<f:facet name="header">
				<h:outputText id="CaseAssodummyTableCrDtId"
					value="#{msg['label.case.associatedCaseAndCorrespondence.createdDate']}" />
			</f:facet>
		</t:column>
		<t:column id="dummyCaseAssocolumn3">
			<f:facet name="header">
				<h:outputText id="CaseAssodummyTableStatusId"
					value="#{msg['label.case.associatedCaseAndCorrespondence.status']}" />
			</f:facet>
		</t:column>
		<t:column id="dummyCaseAssocolumn4">
			<f:facet name="header">
				<h:outputText id="CaseAssodummyTableCaseTypeId"
					value="#{msg['label.case.associatedCaseAndCorrespondence.caseType']}" />
			</f:facet>
		</t:column>
	</h:dataTable>
	<m:table styleClass="dataTable" width="100%" border="0"
		id="noDataTbleId"
		rendered="#{!logCaseDataBean.showCaseRecordsAssoWithCaseDataTable}">
		<m:tr id="noDataTbleIdRow">
			<m:td align="center" id="noDataTbleIdColmn">
				<h:outputText value="#{msg['value.noData']}" id="noDataOutTxt" />
			</m:td>
		</m:tr>
	</m:table>
	<m:br id="caseAssoIDCommandBR3" />
	<m:div styleClass="clearl" id="divBlank">
	</m:div>
	<m:anchor name="viewCaseRecordAssctdHeader"></m:anchor>
	<m:div rendered="#{logCaseDataBean.showEditAssocCaseWithThisCase}"
		id="viewCaseAsscCaseDivId">
		<f:subview id="logCaseAssCaseSubView2">
			<jsp:include page="inc_ViewCaseRecordAssoWithCase.jsp" />
		</f:subview>
	</m:div>
</m:section>
<script type="text/javascript">
function changeSelectAndDeSelectChkBox(){
//alert(1);
try{
var selectObj = findObjectByPartOfID('selAllExistCaseRecords');
selectObj.value = false;
selectObj.checked = false;
selectObj.disabled = false;

}catch(e){
//alert(1)
}
try{
var deSelectObj = findObjectByPartOfID('deSelAllExistCaseRecords');
deSelectObj.value = false;
deSelectObj.checked = false;
deSelectObj.disabled = false;
}catch(e){
//alert(2);
}

}
</script>
