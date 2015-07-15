<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>



<f:loadBundle var="crsrch"
	basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_ContactCorrespondenceSearch" />
<f:loadBundle
	basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_ContactCorrespondenceLog"
	var="crspd" />

<m:div styleClass="padb" />
<m:div styleClass="infoTitle">
	<h:inputHidden id="existingCRListsSize"
		value="#{CorrespondenceDataBean.existingCRListsSize}"></h:inputHidden>

	<h:commandButton id="checkAllExstsSubmit"
		value="#{crspd['label.crspd.click']}" styleClass="hide"
		action="#{CorrespondenceControllerBean.selectAllExistingSubmit}" />
	<h:commandButton id="unCheckAllExstsSubmit"
		value="#{crspd['label.crspd.click']}" styleClass="hide"
		action="#{CorrespondenceControllerBean.deSelectAllExistingSubmit}" />

	<m:table id="PROVIDERMMT20120731164811320" width="100%" styleClass="tableBar">
		<m:tr>
			<m:td>
				<h:outputText id="ecr" value="Existing Correspondence Records"></h:outputText>
			</m:td>
			<m:td>
				<h:selectBooleanCheckbox id="selAllRows"
					onclick="javascript:flagWarn=false;callMethod('logCorrespondence:logCrspdAsscSubview:logCrspdAssociatedSubview:existingCRSPDsubview', 'checkAllExstsSubmit')"
					value="#{CorrespondenceDataBean.selAllExisRecs}"
					disabled="#{CorrespondenceDataBean.selAllExisRecs}"></h:selectBooleanCheckbox>
				<%--<h:panelGroup id="selAllRowsPanelId" rendered="#{CorrespondenceControllerBean.associateCrsExtRecDeselectAjax}">
                       <h:selectBooleanCheckbox id="selAllRows"	value="#{CorrespondenceDataBean.selAllExisRecs}" disabled="#{CorrespondenceDataBean.deSelAllExisRecs}">
                              <hx:behavior event="onmouseup" behaviorAction="get;get;get;stop" targetAction="AssociateCrsExitSelectPanel1;existingCrnPanelId;chklistOfAssociatedCRsId;" ></hx:behavior>
                       </h:selectBooleanCheckbox>
                    </h:panelGroup>
                 <hx:ajaxRefreshSubmit id="ExistingCrnajaxReferredToRequestDeselct1" target="selAllRowsPanelId"> </hx:ajaxRefreshSubmit>--%>
				<h:outputLabel id="PRGCMGTOLL331" for="selAllRows"
					value="Select All Rows" />
			</m:td>
			<m:td>
				<h:selectBooleanCheckbox id="deselAllRows"
					onclick="javascript:flagWarn=false;callMethod('logCorrespondence:logCrspdAsscSubview:logCrspdAssociatedSubview:existingCRSPDsubview', 'unCheckAllExstsSubmit')"
					value="#{CorrespondenceDataBean.deSelAllExisRecs}"
					disabled="#{CorrespondenceDataBean.deSelAllExisRecs}"></h:selectBooleanCheckbox>
				<%--<h:panelGroup id="AssociateCrsExitSelectPanel1" rendered="#{CorrespondenceControllerBean.associateCrsExtRecAjax}">
					  <h:selectBooleanCheckbox id="deselAllRowsId"	value="#{CorrespondenceDataBean.deSelAllExisRecs}" disabled="#{CorrespondenceDataBean.selAllExisRecs}">
                            <hx:behavior event="onmouseup" behaviorAction="get;get;stop" targetAction="selAllRowsPanelId;existingCrnPanelId;" onActionFunction="deSelAllExisRecs();"></hx:behavior>
                      </h:selectBooleanCheckbox>
                    </h:panelGroup>
                 <hx:ajaxRefreshSubmit id="ExistingCrnajaxReferredToRequest1" target="AssociateCrsExitSelectPanel1"> </hx:ajaxRefreshSubmit>--%>
				<h:outputLabel id="PRGCMGTOLL332" for="deselAllRows"
					value="Deselect All Rows" />



			</m:td>
		</m:tr>
	</m:table>




	<t:dataTable cellspacing="0" styleClass="dataTable" width="99%"
		rowClasses="row_alt,row"
		value="#{CorrespondenceDataBean.correspondenceRecordVO.existingCRLists}"
		rows="10" id="ExistingCorrespondenceTable" var="existCrList"
		first="#{CorrespondenceDataBean.startIndexForexistAsc}"
		columnClasses="columnClass" headerClass="headerClass"
		footerClass="footerClass" rowIndexVar="rowIndex"
		onmousedown="javascript:flagWarn=false;focusThis(this.id);"
		onmouseover="return tableMouseOver(this, event);"
		onmouseout="return tableMouseOut(this, event);">
		<h:column id="ExistingCrTableID1">
			<f:facet name="header">
				<h:outputText value="#{crsrch['label.crspd.crn']}"
					id="ExistingCrTableID2" />
			</f:facet>
			<t:commandLink id="crnr1"
				action="#{CorrespondenceControllerBean.showExistingRecords}">
				<h:outputText value="#{existCrList.correspondenceRecordNumber}"
					id="ExistingCrTableID3"></h:outputText>
				<f:param name="indexCode" value="#{rowIndex}"></f:param>
			</t:commandLink>
			<h:message showDetail="true" style="color: red" for="crnr1"
				id="crnr1Msg"></h:message>

		</h:column>
		<h:column id="ExistingCrTableID4">
			<f:facet name="header">
				<h:outputText value="Open Date" id="ExistingCrTableID5" />
			</f:facet>
			<h:outputText id="crtdOnr1" value="#{existCrList.openDate}" />
			<h:message showDetail="true" style="color: red" for="crtdOnr1"
				id="crtdOnr1Msg"></h:message>
		</h:column>
		<h:column id="ExistingCrTableID15">
			<f:facet name="header">
				<h:outputText value="#{crsrch['label.crspd.status']}"
					id="ExistingCrTableID7" />
			</f:facet>
			<h:outputText id="statusr1" value="#{existCrList.status}" />
			<h:message showDetail="true" style="color: red" for="statusr1"
				id="statusr1Msg"></h:message>
		</h:column>
		<h:column id="ExistingCrTableID14">
			<f:facet name="header">
				<h:outputText value="#{crsrch['label.crspd.category']}"
					id="ExistingCrTableID8" />
			</f:facet>
			<h:outputText id="categoryr1" value="#{existCrList.category}" />
			<h:message showDetail="true" style="color: red" for="categoryr1"
				id="categoryrMsg"></h:message>
		</h:column>
		<h:column id="ExistingCrTableID13">
			<f:facet name="header">
				<h:outputText value="#{crsrch['label.crspd.subject']}"
					id="ExistingCrTableID9" />
			</f:facet>
			<h:outputText id="subr1" value="#{existCrList.subject}" />
			<h:message showDetail="true" style="color: red" for="subr1"
				id="subr1Msg"></h:message>
		</h:column>
		<h:column id="ExistingCrTableID12">
			<f:facet name="header">
				<h:outputText value="#{crsrch['label.crspd.contact']}"
					id="ExistingCrTableID10" />
			</f:facet>
			<h:outputText value="#{existCrList.contact}" id="ExistingCrTableID11" />
		</h:column>
		<h:column id="ExistingCrTableID16">

			<f:facet name="header">
				<h:outputText id="chk12" value="#{crspd['label.crspd.linkToCR']}" />
			</f:facet>
			<h:outputLabel id="checkboxColumnLabelCR14" for="checkBoxr1" 
							styleClass="hide" value="#{crspd['label.crspd.linkToCR']}" ></h:outputLabel>
			<h:selectBooleanCheckbox id="checkBoxr1"
				value="#{existCrList.linkToCR}"
				disabled="#{CorrespondenceDataBean.selAllExisRecs}">
			</h:selectBooleanCheckbox>

			<h:message showDetail="true" style="color: red" for="checkBoxr1"
				id="checkBoxr1Msg"></h:message>
		</h:column>
	</t:dataTable>


	<m:div styleClass="searchResults">
		<t:dataScroller id="searchResultsIDdsfdfg" onclick="flagWarn=false;"
			for="ExistingCorrespondenceTable" paginator="true"
			paginatorActiveColumnStyle='font-weight:bold;' paginatorMaxPages="3"
			immediate="false" pageCountVar="pageCount" pageIndexVar="pageIndex"
			firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
			rowsCountVar="rowsCount"
			style="float:right;position:relative;bottom:-6px;text-decoration:underline;">
			<f:facet name="previous">
				<h:outputText id="djldjlkjdfs" style="text-decoration:underline;"
					value="#{crsrch['label.crspd.pageLeft']}"
					rendered="#{pageIndex > 1}">
				</h:outputText>
			</f:facet>
			<f:facet name="next">
				<h:outputText id="hdkjshkwewe" style="text-decoration:underline;"
					value="#{crsrch['label.crspd.pageRight']}"
					rendered="#{pageIndex < pageCount}">
				</h:outputText>
			</f:facet>
			<h:outputText id="dfjhdfkjhdfs" rendered="#{rowsCount > 0}"
				value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
				style="float:left;position:relative;bottom:-6px;font-weight:bold;" />
		</t:dataScroller>
	</m:div>
</m:div>
