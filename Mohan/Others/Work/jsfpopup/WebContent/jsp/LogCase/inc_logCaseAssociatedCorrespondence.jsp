<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>

<t:saveState id="CMGTTOMSS171" value="#{logCaseDataBean.existingCRInfoList}"></t:saveState>
<t:saveState id="CMGTTOMSS172" value="#{logCaseDataBean.associatedCorrespondenceVO}"></t:saveState>

<m:section id="corrSec1">
	<m:legend id="corrLeg1">
		<h:outputText styleClass="strong" id="corrOutTxt1"			value="#{msg['title.case.correspondence']}" />
	</m:legend>
	
	<m:div rendered="#{logCaseDataBean.showCorrSearchScreen}" id="corrDiv1">
	<%--<h:outputText styleClass="colorRed" id="ASCTCASECRSPND" value="#{ent['err-sw-search-no-record-found']}" 
	rendered="#{logCaseDataBean.caseCrspnAsctErrormsg}"/>--%>
	<m:div id="LCASECRRSEARCHERRMESG" styleClass="width:100%">
			<h:inputHidden id="LCASECRRSEARCHERRMESGIDHID" value=" "></h:inputHidden>
			<h:message id="PROVIDERM20120731164811292" for="LCASECRRSEARCHERRMESGIDHID" styleClass="errorMessage"></h:message>
	</m:div>
	<f:subview id="corSearchSubView1">
		 <m:anchor name="caseAscCrspondenceSearch"></m:anchor>
		<jsp:include page="inc_correspondenceSearch.jsp" />
	</f:subview>
	</m:div>
	
	<m:table width="100%" id="corrTab1">
		<m:tr id="corrTab1Row1">
			<m:td styleClass="rightCell" id="corrTab1Col1">
				<h:commandButton styleClass="formBtn"
					id="associatedCaseAndCorspndId"
					onclick="javascript:flagWarn=false;focusThis(this.id);focusPage('caseAscCrspondenceSearch');"
					value="#{msg['label.case.associatedCaseAndCorrespondence.search']}"
					action="#{logCaseControllerBean.showCorrSearchScreen}" 
					disabled="#{logCaseDataBean.disableScreenField}"/>
				<%-- Commented for Defect ESPRD00650091 --%>
				<%-- <h:commandButton styleClass="formBtn" id="associatedCaseAndCorspndCancelBtnId"					value="#{msg['label.case.cancel']}"					action="#{logCaseControllerBean.cancelCorrespondenceSearchScreen}"					disabled="#{logCaseDataBean.disableScreenField}" />  --%>
			</m:td>
		</m:tr>
	</m:table>
	
	<m:div id="corrsDiv2" rendered="#{!logCaseDataBean.showCaseType}">
	<m:table width="100%" styleClass="tableBar" id="corrTab2">
		<m:div align="right" id="corrDiv2">
			<m:td id="corrTd1">
				<h:selectBooleanCheckbox id="selAllExistRows4Srch"
					valueChangeListener="#{logCaseControllerBean.selectAllExistingCRs}"
					value="#{logCaseDataBean.existCrsSelectAll}"
					disabled="#{logCaseDataBean.existCrsSelectAll}"
					onclick="javascript:flagWarn=false;focusPage('corrRecAssWithCaseTableFocusPage');this.form.submit();">
				</h:selectBooleanCheckbox>
				<h:outputLabel for="selAllExistRows4Srch" id="corrLabel1"
					value="#{msg['label.case.associatedCaseAndCorrespondence.selAllRows']}" />
			</m:td>
			<m:td id="corrTd2">
				<h:selectBooleanCheckbox id="deselAllExistRows4Srch"
					value="#{logCaseDataBean.existCrsDeSelectAll}"
					disabled="#{logCaseDataBean.existCrsDeSelectAll}"
					valueChangeListener="#{logCaseControllerBean.deSelectAllExistingCRs}"
					onclick="javascript:flagWarn=false;focusPage('logCaseExistCaseRecDataTableFocusPage');this.form.submit();">
				</h:selectBooleanCheckbox>
				<h:outputLabel for="deselAllExistRows4Srch" id="corrOutLbl1Id"
					value="#{msg['label.case.associatedCaseAndCorrespondence.deSelAllRows']}" />
			</m:td> 
		</m:div>	
	</m:table> 
	 
	<t:htmlTag value="h3" id="corrHtmlTag1Id">
		<h:outputText id="corrOutTxt2"			value="#{msg['label.case.associatedCaseAndCorrespondence.existingCorrRecords']}" />
	</t:htmlTag>

	<m:div rendered="#{logCaseDataBean.showExistingCaseRecordsDataTable}">
		<m:anchor name="logCaseExistCaseRecDataTableFocusPage"></m:anchor>
		<t:dataTable id="existingCorrDataTable" border="1" cellpadding="0"
			cellspacing="0" columnClasses="columnClass" headerClass="headerClass"
			rows="10" footerClass="footerClass" rowClasses="row_alt,rowone"
			styleClass="dataTable" width="99%" var="existingCaseSpanResult"
			first="#{logCaseDataBean.existingCaseSearchRowIndex}"
			value="#{logCaseDataBean.existingCRInfoList}"
			onmouseover="return tableMouseOver(this, event);"
			onmouseout="return tableMouseOut(this, event);">
			<t:column id="existingCorrIDcolumn1">
				<f:facet name="header">
					<h:panelGrid columns="2" id="existingCorrDataTablePanGrid1Id">
						<h:outputLabel id="existingCorrDataTableOutLbl1Id"							value="#{msg['label.case.associatedCaseAndCorrespondence.ID']}"							for="existingCorrIDCommandLink1" />
						<h:panelGroup id="existingCorrDataTablePanGrp1Id">
							<t:div id="CaseAssociatedCorresTDiv001" styleClass="alignLeft">
							<t:commandLink id="existingCorrIDCommandLink1"
								onmousedown="javascript:flagWarn=false;focusPage('logCaseExistCaseRecDataTableFocusPage');" styleClass="clStyle"
								actionListener="#{logCaseControllerBean.sortExistingCase}"
								rendered="#{logCaseDataBean.imageRender != 'existingCorrIDCommandLink1'}">
								<m:div title="#{msg['alt.for.ascending']}"
									id="existingCorrDataTableImg1Id" styleClass="sort_asc"
									 />
								<f:attribute name="columnName" value="existingCase_ID" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
							</t:div>
							<t:div id="CaseAssociatedCorresTDiv002" styleClass="alignLeft">
							<t:commandLink id="existingCorrIDCommandLink2"
								onmousedown="javascript:flagWarn=false;focusPage('logCaseExistCaseRecDataTableFocusPage');" styleClass="clStyle"
								actionListener="#{logCaseControllerBean.sortExistingCase}"
								rendered="#{logCaseDataBean.imageRender != 'existingCorrIDCommandLink2'}">
								<m:div title="#{msg['alt.for.descending']}"
									id="existingCorrDataTableImg2Id" styleClass="sort_desc"
									 />
								<f:attribute name="columnName" value="existingCase_ID" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>
				</f:facet>
				<h:outputText					value="#{existingCaseSpanResult.correspondenceRecordNumber}"					id="existingCorrDataTableOutTxt1Id" />
			</t:column>

			<t:column id="existingCorrcolumn2">
				<f:facet name="header">
					<h:panelGrid columns="2" id="existingCorrDataTablePanGrid2Id">
						<h:outputLabel id="existingCorrDataTableOutLbl2Id"							value="#{msg['label.case.associatedCaseAndCorrespondence.createdDate']}"							for="existingCorrcreatedDateCommandLink1" />
						<h:panelGroup id="existingCorrDataTablePanGrp2Id">
							<t:div id="CaseAssociatedCorresTDiv003" styleClass="alignLeft">
							<t:commandLink id="existingCorrcreatedDateCommandLink1"
								onmousedown="javascript:flagWarn=false;focusPage('logCaseExistCaseRecDataTableFocusPage');" styleClass="clStyle"
								actionListener="#{logCaseControllerBean.sortExistingCase}"
								rendered="#{logCaseDataBean.imageRender != 'existingCorrcreatedDateCommandLink1'}">
								<m:div title="#{msg['alt.for.ascending']}"
									id="existingCorrDataTableImg3Id" styleClass="sort_asc"
									 />
								<f:attribute name="columnName" value="existingCase_CreatedDate" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
							</t:div>
							<t:div id="CaseAssociatedCorresTDiv004" styleClass="alignLeft">
							<t:commandLink id="existingCorrcreatedDateCommandLink2"
								onmousedown="javascript:flagWarn=false;focusPage('logCaseExistCaseRecDataTableFocusPage');" styleClass="clStyle"
								actionListener="#{logCaseControllerBean.sortExistingCase}"
								rendered="#{logCaseDataBean.imageRender != 'existingCorrcreatedDateCommandLink2'}">
								<m:div title="#{msg['alt.for.descending']}"
									id="existingCorrDataTableImg4Id" styleClass="sort_desc"
									 />
								<f:attribute name="columnName" value="existingCase_CreatedDate" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>
				</f:facet>
				<h:outputText value="#{existingCaseSpanResult.openDate}"					id="existingCorrDataTableOutTxt2Id" />
			</t:column>

			<t:column id="existingCorrcolumn3">
				<f:facet name="header">
					<h:panelGrid columns="2" id="existingCorrDataTablePanGrid3Id">
						<h:outputLabel id="existingCorrDataTableOutLbl3Id"							value="#{msg['label.case.associatedCaseAndCorrespondence.status']}"							for="existingCorrstatusCommandLink1" />
						<h:panelGroup id="existingCorrDataTablePanGrp3Id">
							<t:div id="CaseAssociatedCorresTDiv005" styleClass="alignLeft">
							<t:commandLink id="existingCorrstatusCommandLink1"
								onmousedown="javascript:flagWarn=false;focusPage('logCaseExistCaseRecDataTableFocusPage');" styleClass="clStyle"
								actionListener="#{logCaseControllerBean.sortExistingCase}"
								rendered="#{logCaseDataBean.imageRender != 'existingCorrstatusCommandLink1'}">
								<m:div title="#{msg['alt.for.ascending']}"
									id="existingCorrDataTableImg5Id" styleClass="sort_asc"
									 />
								<f:attribute name="columnName" value="existingCase_Status" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
							</t:div>
							<t:div id="CaseAssociatedCorresTDiv006" styleClass="alignLeft">
							<t:commandLink id="existingCorrstatusCommandLink2"
								onmousedown="javascript:flagWarn=false;focusPage('logCaseExistCaseRecDataTableFocusPage');" styleClass="clStyle"
								actionListener="#{logCaseControllerBean.sortExistingCase}"
								rendered="#{logCaseDataBean.imageRender != 'existingCorrstatusCommandLink2'}">
								<m:div title="#{msg['alt.for.descending']}"
									id="existingCorrDataTableImg6Id" styleClass="sort_desc"
									 />
								<f:attribute name="columnName" value="existingCase_Status" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>
				</f:facet>
				<h:outputText value="#{existingCaseSpanResult.statusstr}"					id="existingCorrDataTableOutTxt3Id" />
			</t:column>
			<t:column id="existingCorrcolumn4">
				<f:facet name="header">
					<h:panelGrid columns="2" id="existingCorrDataTablePanGrid4Id">
						<h:outputLabel id="existingCorrDataTableOutLbl4Id"							value="#{msg['label.case.associatedCaseAndCorrespondence.category']}"							for="existingCorrCategoryCommandLink1" />
						<h:panelGroup id="existingCorrDataTablePanGrp4Id">
							<t:div id="CaseAssociatedCorresTDiv007" styleClass="alignLeft">
							<t:commandLink id="existingCorrCategoryCommandLink1"
								onmousedown="javascript:flagWarn=false;focusPage('logCaseExistCaseRecDataTableFocusPage');" styleClass="clStyle"
								actionListener="#{logCaseControllerBean.sortExistingCase}"
								rendered="#{logCaseDataBean.imageRender != 'existingCorrCategoryCommandLink1'}">
								<m:div title="#{msg['alt.for.ascending']}"
									id="existingCorrDataTableImg7Id" styleClass="sort_asc"
									 />
								<f:attribute name="columnName" value="existingCase_Link" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
							</t:div>
							<t:div id="CaseAssociatedCorresTDiv008" styleClass="alignLeft">
							<t:commandLink id="existingCorrCategoryCommandLink2"
								onmousedown="javascript:flagWarn=false;" styleClass="clStyle"
								actionListener="#{logCaseControllerBean.sortExistingCase}"
								rendered="#{logCaseDataBean.imageRender != 'existingCorrCategoryCommandLink2'}">
								<m:div title="#{msg['alt.for.descending']}"
									id="existingCorrDataTableImg8Id" styleClass="sort_desc"
									 />
								<f:attribute name="columnName" value="existingCase_Link" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>
				</f:facet>
				<h:outputText value="#{existingCaseSpanResult.category}"					id="existingCorrDataTableOutTxt4Id" />
			</t:column>

			<t:column id="existingCorrcolumn5">
				<f:facet name="header">
					<h:panelGrid columns="2" id="existingCorrDataTablePanGridl5Id">
						<h:outputLabel id="existingCorrDataTableOutLbl5Id"							value="#{msg['label.case.Correspondence.subject']}"							for="existingCorrSubjectCommandLink1" />
						<h:panelGroup id="existingCorrDataTablePanGrp5Id">
							<t:div id="CaseAssociatedCorresTDiv009" styleClass="alignLeft">
							<t:commandLink id="existingCorrSubjectCommandLink1"
								onmousedown="javascript:flagWarn=false;" styleClass="clStyle"
								actionListener="#{logCaseControllerBean.sortExistingCase}"
								rendered="#{logCaseDataBean.imageRender != 'existingCorrSubjectCommandLink1'}">
								<m:div title="#{msg['alt.for.ascending']}"
									id="existingCorrDataTableImg9Id" styleClass="sort_asc"
									 />
								<f:attribute name="columnName" value="existingCase_Link" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
							</t:div>
							<t:div id="CaseAssociatedCorresTDiv010" styleClass="alignLeft">
							<t:commandLink id="existingCorrSubjectCommandLink2"
								onmousedown="javascript:flagWarn=false;focusPage('logCaseExistCaseRecDataTableFocusPage');" styleClass="clStyle"
								actionListener="#{logCaseControllerBean.sortExistingCase}"
								rendered="#{logCaseDataBean.imageRender != 'existingCorrSubjectCommandLink1'}">
								<m:div title="#{msg['alt.for.descending']}"
									id="existingCorrDataTableImg10Id" styleClass="sort_desc"
									 />
								<f:attribute name="columnName" value="existingCase_Link" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>
				</f:facet>
				<h:outputText value="#{existingCaseSpanResult.subjectstr}"					id="existingCorrDataTableOutTxt5Id" />
			</t:column>
			<t:column id="existingCorrcolumn6">
				<f:facet name="header">
					<h:panelGrid columns="2" id="existingCorrDataTablePanGrid6Id">
						<h:outputLabel id="existingCorrDataTableOutLb6Id"							value="#{msg['label.case.associatedCaseAndCorrespondence.contact']}"							for="existingCorrlinkCommandLink1" />
						<h:panelGroup id="existingCorrDataTablePanGrp6Id">
							<t:div id="CaseAssociatedCorresTDiv011" styleClass="alignLeft">
							<t:commandLink id="existingCorrContactCommandLink1"
								onmousedown="javascript:flagWarn=false;focusPage('logCaseExistCaseRecDataTableFocusPage');" styleClass="clStyle"
								actionListener="#{logCaseControllerBean.sortExistingCase}"
								rendered="#{logCaseDataBean.imageRender != 'existingCorrContactCommandLink1'}">
								<m:div title="#{msg['alt.for.ascending']}"
									id="existingCorrDataTableImg11Id" styleClass="sort_asc"
									 />
								<f:attribute name="columnName" value="existingCase_Link" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
							</t:div>
							<t:div id="CaseAssociatedCorresTDiv012" styleClass="alignLeft">
							<t:commandLink id="existingCorrContactCommandLink2"
								onmousedown="javascript:flagWarn=false;focusPage('logCaseExistCaseRecDataTableFocusPage');" styleClass="clStyle"
								actionListener="#{logCaseControllerBean.sortExistingCase}"
								rendered="#{logCaseDataBean.imageRender != 'existingCorrContactCommandLink2'}">
								<m:div title="#{msg['alt.for.descending']}"
									id="existingCorrDataTableImg12Id" styleClass="sort_desc"
									 />
								<f:attribute name="columnName" value="existingCase_Link" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>
				</f:facet>
				<h:outputText value="#{existingCaseSpanResult.contact}"					id="existingCorrDataTableOutTxt6Id" />
			</t:column>
			<t:column id="existingCorrcolumn7">
				<f:facet name="header">
					<h:panelGrid columns="2" id="existingCorrDataTablePanGrid7">
						<h:outputLabel id="existingCorrDataTableOutLbl7Id"							value="#{msg['label.case.associatedCaseAndCorrespondence.link']}"							for="existingCorrlinkCommandLink1" />
						<h:panelGroup id="existingCorrDataTablePanGrp7Id">
							<t:div id="CaseAssociatedCorresTDiv013" styleClass="alignLeft">
							<t:commandLink id="existingCorrlinkCommandLink1"
								onmousedown="javascript:flagWarn=false;focusPage('logCaseExistCaseRecDataTableFocusPage');" styleClass="clStyle"
								actionListener="#{logCaseControllerBean.sortExistingCase}"
								rendered="#{logCaseDataBean.imageRender != 'existingCorrlinkCommandLink1'}">
								<m:div title="#{msg['alt.for.ascending']}"
									id="existingCorrDataTableImg13Id" styleClass="sort_asc"
									 />
								<f:attribute name="columnName" value="existingCase_Link" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
							</t:div>
							<t:div id="CaseAssociatedCorresTDiv014" styleClass="alignLeft">
							<t:commandLink id="existingCorrlinkCommandLink2"
								onmousedown="javascript:flagWarn=false;focusPage('logCaseExistCaseRecDataTableFocusPage');" styleClass="clStyle"
								actionListener="#{logCaseControllerBean.sortExistingCase}"
								rendered="#{logCaseDataBean.imageRender != 'existingCorrlinkCommandLink2'}">
								<m:div title="#{msg['alt.for.descending']}"
									id="existingCorrDataTableImg14Id" styleClass="sort_desc"
									 />
								<f:attribute name="columnName" value="existingCase_Link" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>
				</f:facet>
				<h:outputLabel id="checkboxLCACColumnLabel2"  for="checkBoxr" styleClass="hide" value="#{msg['label.case.associatedCaseAndCorrespondence.link']}" ></h:outputLabel>
				<h:selectBooleanCheckbox id="checkBoxr"
					value="#{existingCaseSpanResult.linksToCR}"
					disabled="#{logCaseDataBean.disableScreenField}"></h:selectBooleanCheckbox>

			</t:column>
				<f:facet name="footer">
					<m:div id="nodataexistsCr"
						rendered="#{empty logCaseDataBean.existingCRInfoList}"
						align="center">
						<h:outputText id="nodataexistsCorrespondence"
							value="#{msg['value.noData']}"></h:outputText>
					</m:div>

				</f:facet>
			</t:dataTable>
		<t:dataScroller for="existingCorrDataTable" paginator="true"
			onclick="javascript:flagWarn=false;focusPage('logCaseExistCaseRecDataTableFocusPage');"
			paginatorActiveColumnStyle="font-weight:bold;" paginatorMaxPages="3"
			immediate="false" pageCountVar="pageCount" pageIndexVar="pageIndex"
			firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
			rowsCountVar="rowsCount" styleClass="dataScroller"
			id="existingCorrDataTableScrolId">
			<f:facet name="previous">
				<h:outputText styleClass="underline"
					id="existingCorrDataTableScrolPrevId"
					value=" #{msg['label.lessthan']} " rendered="#{pageIndex > 1}"></h:outputText>
			</f:facet>
			<f:facet name="next">
				<h:outputText styleClass="underline"
					id="existingCorrDataTableNxtScrolId"
					value=" #{msg['label.greaterthan']} "
					rendered="#{pageIndex < pageCount}"></h:outputText>
			</f:facet>
			<h:outputText id="PRGCMGTOT625" rendered="#{rowsCount > 0}"
				id="existingCorrDataTableRowsCountScrolId"
				value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
				styleClass="dataScrollerText" />
		</t:dataScroller>
	</m:div>
	</m:div>

	<m:br id="corrBr8" />
	<m:anchor name="corrAssWithCaseFocusPage"></m:anchor>
	<%--<m:div styleClass="msgBox"
		rendered="#{logCaseControllerBean.deleteFlagForCorrRecAssoWithThisCase}"
		id="deleteFlagDivId">
		<h:messages layout="table" showDetail="true"
			id="deleteCaseAssoWithThisRecoSuccessMsgID" showSummary="false">
		</h:messages>
	</m:div>--%>
	<m:div id="LCSEASCTDIV">
		<h:inputHidden id="LCSEASCTCRDIVHDN" value=" "></h:inputHidden>
		<h:message id="PROVIDERM20120731164811293" for="LCSEASCTCRDIVHDN" styleClass="errorMessage"></h:message>
	</m:div>
	<m:br />
	
	<m:anchor name="corrRecAssWithCaseTableFocusPage"></m:anchor>
	<t:htmlTag value="h3" id="corrHtmlTa2Id">
		<h:outputText id="corrOutTxt3"
			value="#{msg['label.case.Correspondence.correspondencerecordsassociatedwiththiscase']}" />
	</t:htmlTag>

	<t:dataTable value="#{logCaseDataBean.caseDetailsVO.associatedCrList}"
		first="#{logCaseDataBean.crRecordsAssocWithCaseRowIndex}"
		var="caseAssoSpanResult" styleClass="dataTable" cellspacing="0"
		width="100%" border="0" headerClass="tableHead"
		rowClasses="rowone,row_alt" rows="10" id="corrAssoDataTable"
		onmouseover="return tableMouseOver(this, event)"
		onmouseout="return tableMouseOut(this, event);"
		rowOnClick="javascript:childNodes[0].lastChild.click();"
		onmousedown="javascript:flagWarn=false;focusPage('caseAscCaseDetails');"
		rowIndexVar="rowIndex">
		<t:column id="corrAssocolumn1">
			<f:facet name="header">
				<h:panelGrid columns="2" id="corrAssoDataTablePanGrid1Id">
					<h:outputLabel id="corrAssoDataTableOutLb1Id"						value="#{msg['label.case.associatedCaseAndCorrespondence.ID']}"						for="corrAssoIDCommandLink1" />
					<h:panelGroup id="corrAssoDataTablePanGrp1Id">
						<t:div id="CaseAssociatedCorresTDiv015" styleClass="alignLeft">
						<t:commandLink id="corrAssoIDCommandLink1" styleClass="clStyle"
							onmousedown="javascript:flagWarn=false;focusPage('corrRecAssWithCaseTableFocusPage');"
							actionListener="#{logCaseControllerBean.sortCrRecordsAssoWithCase}"
							rendered="#{logCaseDataBean.imageRender != 'corrAssoIDCommandLink1'}">
							<m:div id="CaseAssociatedCorresMDiv0001" title="#{msg['alt.for.ascending']}"
								styleClass="sort_asc"  />
							<f:attribute name="columnName" value="CrAssocRecords_ID" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
						</t:div>
						<t:div id="CaseAssociatedCorresTDiv016" styleClass="alignLeft">
						<t:commandLink id="corrAssoIDCommandLink2" styleClass="clStyle"
							onmousedown="javascript:flagWarn=false;focusPage('corrRecAssWithCaseTableFocusPage');"
							actionListener="#{logCaseControllerBean.sortCrRecordsAssoWithCase}"
							rendered="#{logCaseDataBean.imageRender != 'corrAssoIDCommandLink2'}">
							<m:div id="CaseAssociatedCorresMDiv0002" title="#{msg['alt.for.descending']}"
								styleClass="sort_desc"  />
							<f:attribute name="columnName" value="CrAssocRecords_ID" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
						</t:div>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>
			<t:commandLink id="editLink02345"
				onclick="javascript:flagWarn=false;focusPage('caseAscCaseDetails');"
				action="#{logCaseControllerBean.viewCRAssociatedWithThisCase}">
				<h:outputText id="corrOutTxt11"
					value="#{caseAssoSpanResult.correspondenceRecordNumber}" />
			<f:param name="indexCode" value="#{rowIndex}"></f:param>
			</t:commandLink>
		</t:column>

		<t:column id="corrAssocolumn2">
			<f:facet name="header">
				<h:panelGrid columns="2" id="corrAssoDataTablePanGrid2Id">
					<h:outputLabel id="corrAssoDataTableOutLb2Id"						value="#{msg['label.case.associatedCaseAndCorrespondence.createdDate']}"						for="corrAssocreatedDateCommandLink1" />
					<h:panelGroup id="corrAssoDataTablePanGrp2Id">
						<t:div id="CaseAssociatedCorresTDiv017" styleClass="alignLeft">
						<t:commandLink id="corrAssocreatedDateCommandLink1"
							onmousedown="javascript:flagWarn=false;focusPage('corrRecAssWithCaseTableFocusPage');" styleClass="clStyle"
							actionListener="#{logCaseControllerBean.sortCrRecordsAssoWithCase}"
							rendered="#{logCaseDataBean.imageRender != 'corrAssocreatedDateCommandLink1'}">
							<m:div id="CaseAssociatedCorresMDiv0003" title="#{msg['alt.for.ascending']}"
								styleClass="sort_asc"  />
							<f:attribute name="columnName" value="CrAssocRecords_CreateDate" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
						</t:div>
						<t:div id="CaseAssociatedCorresTDiv018" styleClass="alignLeft">
						<t:commandLink id="corrAssocreatedDateCommandLink2"
							onmousedown="javascript:flagWarn=false;focusPage('corrRecAssWithCaseTableFocusPage');" styleClass="clStyle"
							actionListener="#{logCaseControllerBean.sortCrRecordsAssoWithCase}"
							rendered="#{logCaseDataBean.imageRender != 'corrAssocreatedDateCommandLink2'}">
							<m:div id="CaseAssociatedCorresMDiv0004" title="#{msg['alt.for.descending']}"
								styleClass="sort_desc"  />
							<f:attribute name="columnName" value="CrAssocRecords_CreateDate" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
						</t:div>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>
			<h:outputText id="corrOutTxt22" value="#{caseAssoSpanResult.openDate}" />
		</t:column>
		<t:column id="corrAssocolumn3">
			<f:facet name="header">
				<h:panelGrid columns="2" id="corrAssoDataTablePanGrid3Id">
					<h:outputLabel id="corrAssoDataTableOutLb3Id"						value="#{msg['label.case.associatedCaseAndCorrespondence.status']}"						for="corrAssostatusCommandLink1" />
					<h:panelGroup id="corrAssoDataTablePanGrp3Id">
						<t:div id="CaseAssociatedCorresTDiv019" styleClass="alignLeft">
						<t:commandLink id="corrAssostatusCommandLink1"
							onmousedown="javascript:flagWarn=false;focusPage('corrRecAssWithCaseTableFocusPage');" styleClass="clStyle"
							actionListener="#{logCaseControllerBean.sortCrRecordsAssoWithCase}"
							rendered="#{logCaseDataBean.imageRender != 'corrAssostatusCommandLink1'}">
							<m:div id="CaseAssociatedCorresMDiv0005" title="#{msg['alt.for.ascending']}"
								styleClass="sort_asc"  />
							<f:attribute name="columnName" value="CrAssocRecords_Status" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
						</t:div>
						<t:div id="CaseAssociatedCorresTDiv020" styleClass="alignLeft">
						<t:commandLink id="corrAssostatusCommandLink2"
							onmousedown="javascript:flagWarn=false;focusPage('corrRecAssWithCaseTableFocusPage');" styleClass="clStyle"
							actionListener="#{logCaseControllerBean.sortCrRecordsAssoWithCase}"
							rendered="#{logCaseDataBean.imageRender != 'corrAssostatusCommandLink2'}">
							<m:div id="CaseAssociatedCorresMDiv0006" title="#{msg['alt.for.descending']}"
								styleClass="sort_desc"  />
							<f:attribute name="columnName" value="CrAssocRecords_Status" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
						</t:div>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>
			<h:outputText id="corrOutTxt33" value="#{caseAssoSpanResult.status}" />
		</t:column>
      
		<t:column id="corrAssocolumn4">
			<f:facet name="header">
				<h:panelGrid columns="2" id="corrAssoDataTablePanGrid4Id">
					<h:outputLabel id="corrAssoDataTableOutLb4Id"						value="#{msg['label.case.associatedCaseAndCorrespondence.category']}"						for="corrAssoCategoryCommandLink1" />
					<h:panelGroup id="corrAssoDataTablePanGrp4Id">
						<t:div id="CaseAssociatedCorresTDiv021" styleClass="alignLeft">
						<t:commandLink id="corrAssoCategoryCommandLink1"
							onmousedown="javascript:flagWarn=false;focusPage('corrRecAssWithCaseTableFocusPage');" styleClass="clStyle"
							actionListener="#{logCaseControllerBean.sortCrRecordsAssoWithCase}"
							rendered="#{logCaseDataBean.imageRender != 'corrAssoCategoryCommandLink1'}">
							<m:div id="CaseAssociatedCorresMDiv0007" title="#{msg['alt.for.ascending']}"
								styleClass="sort_asc"  />
							<f:attribute name="columnName" value="CrAssocRecords_Category" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
						</t:div>
						<t:div id="CaseAssociatedCorresTDiv022" styleClass="alignLeft">
						<t:commandLink id="corrAssoCategoryCommandLink2"
							onmousedown="javascript:flagWarn=false;focusPage('corrRecAssWithCaseTableFocusPage');" styleClass="clStyle"
							actionListener="#{logCaseControllerBean.sortCrRecordsAssoWithCase}"
							rendered="#{logCaseDataBean.imageRender != 'corrAssoCategoryCommandLink2'}">
							<m:div id="CaseAssociatedCorresMDiv0008" title="#{msg['alt.for.descending']}"
								styleClass="sort_desc"  />
							<f:attribute name="columnName" value="CrAssocRecords_Category" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
						</t:div>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>
			<h:outputText id="corrOutTxt4" value="#{caseAssoSpanResult.category}" />
		</t:column>
		<t:column id="corrAssocolumn5">
			<f:facet name="header">
				<h:panelGrid columns="2" id="corrAssoDataTablePanGrid5Id">
					<h:outputLabel id="corrAssoDataTableOutLb5Id"						value="#{msg['label.case.Correspondence.subject']}"						for="corrAssoSubjectCommandLink1" />
					<h:panelGroup id="corrAssoDataTablePanGrp5Id">
						<t:div id="CaseAssociatedCorresTDiv023" styleClass="alignLeft">
						<t:commandLink id="corrAssoSubjectCommandLink1"
							onmousedown="javascript:flagWarn=false;focusPage('corrRecAssWithCaseTableFocusPage');" styleClass="clStyle"
							actionListener="#{logCaseControllerBean.sortCrRecordsAssoWithCase}"
							rendered="#{logCaseDataBean.imageRender != 'corrAssoSubjectCommandLink1'}">
							<m:div id="CaseAssociatedCorresMDiv0009" title="#{msg['alt.for.ascending']}"
								styleClass="sort_asc"  />
							<f:attribute name="columnName" value="CrAssocRecords_Subject" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
						</t:div>
						<t:div id="CaseAssociatedCorresTDiv024" styleClass="alignLeft">
						<t:commandLink id="corrAssoSubjectCommandLink2"
							onmousedown="javascript:flagWarn=false;focusPage('corrRecAssWithCaseTableFocusPage');" styleClass="clStyle"
							actionListener="#{logCaseControllerBean.sortCrRecordsAssoWithCase}"
							rendered="#{logCaseDataBean.imageRender != 'corrAssoSubjectCommandLink2'}">
							<m:div id="CaseAssociatedCorresMDiv0010" title="#{msg['alt.for.descending']}"
								styleClass="sort_desc"  />
							<f:attribute name="columnName" value="CrAssocRecords_Subject" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
						</t:div>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>
			<h:outputText id="corrOutTxt5" value="#{caseAssoSpanResult.subject}" />
		</t:column>
		<t:column id="corrAssocolumn6">
			<f:facet name="header">
				<h:panelGrid columns="2" id="corrAssoDataTablePanGrid6Id">
					<h:outputLabel id="corrAssoDataTableOutLb6Id"						value="#{msg['label.case.associatedCaseAndCorrespondence.contact']}"						for="corrAssoContactCommandLink1" />
					<h:panelGroup id="corrAssoDataTablePanGrp6Id">
						<t:div id="CaseAssociatedCorresTDiv025" styleClass="alignLeft">
						<t:commandLink id="corrAssoContactCommandLink1"
							onmousedown="javascript:flagWarn=false;focusPage('corrRecAssWithCaseTableFocusPage');" styleClass="clStyle"
							actionListener="#{logCaseControllerBean.sortCrRecordsAssoWithCase}"
							rendered="#{logCaseDataBean.imageRender != 'corrAssoContactCommandLink1'}">
							<m:div id="CaseAssociatedCorresMDiv0011" title="#{msg['alt.for.ascending']}"
								styleClass="sort_asc"  />
							<f:attribute name="columnName" value="CrAssocRecords_Contact" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
						</t:div>
						<t:div id="CaseAssociatedCorresTDiv026" styleClass="alignLeft">
						<t:commandLink id="corrAssoContactCommandLink2"
							onmousedown="javascript:flagWarn=false;focusPage('corrRecAssWithCaseTableFocusPage');" styleClass="clStyle"
							actionListener="#{logCaseControllerBean.sortCrRecordsAssoWithCase}"
							rendered="#{logCaseDataBean.imageRender != 'corrAssoContactCommandLink2'}">
							<m:div id="CaseAssociatedCorresMDiv0012" title="#{msg['alt.for.descending']}"
								styleClass="sort_desc"  />
							<f:attribute name="columnName" value="CrAssocRecords_Contact" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
						</t:div>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>
			<h:outputText id="corrOutTxt6" value="#{caseAssoSpanResult.contact}" />
		</t:column>
		<f:facet name="footer">
			<m:div id="nodataexistsCr2"
				rendered="#{empty logCaseDataBean.caseDetailsVO.associatedCrList}"
				align="center">
				<h:outputText id="nodataexistsCorrespondence2"
					value="#{msg['value.noData']}"></h:outputText>
			</m:div>
		</f:facet>
	</t:dataTable>
	<t:dataScroller id="CMGTTOMDS25" for="corrAssoDataTable" paginator="true"
		onclick="javascript:flagWarn=false;focusPage('corrRecAssWithCaseTableFocusPage');"
		paginatorActiveColumnStyle='font-weight:bold;' paginatorMaxPages="3"
		immediate="false" pageCountVar="pageCount" pageIndexVar="pageIndex"
		firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
		rowsCountVar="rowsCount" styleClass="dataScroller"> <%--Modified fied for the Data scroll fix --%>
		<f:facet name="previous">
			<h:outputText id="corrOutTxt7" styleClass="underline"
				value=" #{msg['label.lessthan']} " rendered="#{pageIndex > 1}"></h:outputText>
		</f:facet>
		<f:facet name="next">
			<h:outputText id="corrOutTxt8" styleClass="underline"
				value=" #{msg['label.greaterthan']} "
				rendered="#{pageIndex < pageCount}"></h:outputText>
		</f:facet>
		<h:outputText id="corrOutTxt9" rendered="#{rowsCount > 0}"
			value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
			styleClass="dataScrollerText" />
	</t:dataScroller>
	<m:br /><m:br />
	
	
	
	<m:div styleClass="clearl" id="corrDiv3">
	</m:div>
	<m:anchor name="caseAscCaseDetails" ></m:anchor>
	<m:div rendered="#{logCaseDataBean.showEditCRAssocWithThisCase}" id="corrDiv4">
		<f:subview id="corSearchSubView2">
		<jsp:include page="inc_ViewCRAssociatedWithCaseDetails.jsp" />
		</f:subview>
	</m:div>
	
</m:section>
