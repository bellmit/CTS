<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>

<portlet:defineObjects />

<m:table id="caseRegTabl01" width="100%">
	<m:tr id="caseRegTabl01TR01">
		<m:td id="caseRegTabl01TR01TD01">
			<m:div id="caseRegT1R1D1Div01" styleClass="padb">
				<h:outputText id="caseRegProvCaseRecNum"					value="#{msg['label.case.caseRegarding.caseRecordNumber']}"					styleClass="outputLabel" />
				<m:br id="caseRegProvBr01" />
				<h:outputText id="caseRegProvCaseRecNumVaL" value="#{logCaseDataBean.caseRegardingVO.caseRecordNumber}" />
			</m:div>
		</m:td>
	</m:tr>
	<m:tr id="caseRegTabl01TR02">
		<m:td id="caseRegTabl01TR02TD01">
			<m:div id="caseRegT1R2D1Div01"  styleClass="padb">
				<h:outputText id="caseRegProvEntiTypeLabl" value="#{msg['label.case.caseRegarding.entityType']}"					styleClass="outputLabel" />
				<m:br />
				<h:outputText id="caseRegProvEntiTypeVal" value="#{logCaseDataBean.caseRegardingVO.caseRegardingProviderVO.entityTypeDesc}" />
			</m:div>
		</m:td>
		<m:td id="caseRegTabl01TR02TD02">
			<m:div id="caseRegT1R2D2Div01" styleClass="padb">
				<h:outputText id="caseRegProvProvIDLabl" styleClass="outputLabel"					value="#{msg['label.case.caseRegarding.providerID']}" />
				<m:br />
					<%--ESPRD00529618 starts--%>
					<%-- Modified for the defect ESPRD00865382 starts--%>
					<%--<h:outputText id="caseRegProvProvIDVal" value="#{logCaseDataBean.caseRegardingVO.caseRegardingProviderVO.entityId}" />--%>
					<h:outputText id="caseRegProvProvIDVal" value="#{logCaseDataBean.caseRegardingVO.caseRegardingProviderVO.providerId}" style="color: blue" rendered="#{!logCaseDataBean.disableScreenField}" />
					<%--ESPRD00529618 ends--%>
					<h:outputText id="caseRegProvProvIDValInquiry" value="#{logCaseDataBean.caseRegardingVO.caseRegardingProviderVO.providerId}" rendered="#{logCaseDataBean.disableScreenField}" />
					<%--ESPRD00865382 ends--%>

			</m:div>
		</m:td>
	<%-- Added for defect ESPRD00332994--%>
		<m:td id="caseRegTabl01TR02TD03">
			<m:div id="caseRegT1R2D3Div01" styleClass="padb">
				<h:outputText id="caseRegProvIDTypLabl" styleClass="outputLabel"					value="#{msg['label.case.caseRegarding.idType']}" />
				<m:br id="caseRegProvIDTypBR" />
					<h:outputText id="caseRegProvIDTypVal" value="#{logCaseDataBean.caseRegardingVO.caseRegardingProviderVO.idType}" />
			</m:div>
		</m:td>

		<m:td id="caseRegTabl01TR02TD04">
			<m:div id="caseRegT1R2D4Div01" styleClass="padb">
				<h:outputText id="caseRegProvPayrIdLabl" value="#{msg['label.case.caseRegarding.payerID']}"					styleClass="outputLabel" />
				<m:br id="caseRegProvPayrBR" />
				<h:outputText id="caseRegProvPayrIdVal" value="#{logCaseDataBean.caseRegardingVO.caseRegardingProviderVO.payeeId}" />
			</m:div>
		</m:td>
		<m:td id="caseRegTabl01TR02TD05">
			<m:div  id="caseRegT1R2D5Div01" styleClass="padb">
				<h:outputText id="caseRegProvStatusLabl" value="#{msg['label.case.caseRegarding.status']}"					styleClass="outputLabel" />
				<m:br id="caseRegProvStatusBR" />
				<h:outputText id="caseRegProvStatusVal" value="#{logCaseDataBean.caseRegardingVO.caseRegardingProviderVO.statusDesc}" />
			</m:div>
		</m:td>
	</m:tr>
	<m:tr id="caseRegTabl01TR03">
		<m:td id="caseRegTabl01TR03TD01">
			<m:div id="caseRegT1R3D1Div01" styleClass="padb">
				<h:outputText id="caseRegProvNameLabl" styleClass="outputLabel"					value="#{msg['label.case.caseRegarding.name']}" />
				<m:br id="caseRegProvNameBR" />
				<h:outputText  id="caseRegProvNameVal" value="#{logCaseDataBean.caseRegardingVO.caseRegardingProviderVO.name}" />
			</m:div>
		</m:td>
		<m:td id="caseRegTabl01TR03TD02"></m:td>
		<m:td id="caseRegTabl01TR03TD03">
			<m:div id="caseRegT1R3D3Div01"  styleClass="padb">
				<h:outputText id="caseRegProvSpecialtyLabl" value="#{msg['label.case.caseRegarding.specialty']}"					styleClass="outputLabel" />
				<m:br id="caseRegProvSpecialtyBR" />
				<h:outputText  id="caseRegProvSpecialtyVal" value="#{logCaseDataBean.caseRegardingVO.caseRegardingProviderVO.specialtyDesc}" />
			</m:div>
		</m:td>
		<m:td id="caseRegTabl01TR03TD04">
			<m:div id="caseRegT1R3D4Div01" styleClass="padb">
				<h:outputText id="caseRegProvProvTypeLabl"					value="#{msg['label.case.caseRegarding.providerType']}"					styleClass="outputLabel" />
				<m:br  id="caseRegProvProvTypeBR" />
				<h:outputText  id="caseRegProvProvTypeVal" value="#{logCaseDataBean.caseRegardingVO.caseRegardingProviderVO.providerTypeCodeDesc}" />
			</m:div>
		</m:td>
	</m:tr>
</m:table>

<m:section id="caseRegProvMSection01" styleClass="expand">
	<m:legend id="caseRegProvMSectionLegnd">
		<h:outputLink				onclick="javascript:flagWarn=false;setHiddenValue('CMlogCase:caseRegarding:caseRegardingProvider:provAltIden_hiddenID','minus');	 		 	toggle('prov_alt_iden_plus',2);			 	plusMinusToggle('prov_alt_iden_plus',this,'CMlogCase:caseRegarding:caseRegardingProvider:provAltIden_hiddenID');			 	return false;" id="provIden_plusMinus"  styleClass="plus">
				<h:outputText id="prov_iden_label" value="#{msg['label.case.caseRegarding.alternativeIdentifiers']}" />
			</h:outputLink>
			<h:inputHidden id="provAltIden_hiddenID" value="#{logCaseDataBean.provAltIdentifierHidden}"/>
	</m:legend>
	<m:div sid="prov_alt_iden_plus" id="prov_alt_iden_plus">
		<t:dataTable value="#{logCaseDataBean.alternateIdentiProvList}"			rendered="#{logCaseDataBean.showAlternateIdentifiersProv}"			first="#{logCaseDataBean.altIdentifiersProvRowIndex}"			var="altIdenProvResult" styleClass="dataTable" cellspacing="0"			width="100%" border="0" headerClass="tableHead"			rowClasses="rowone,row_alt" rows="10" id="altIdenProvSpanstable"			onmouseover="return tableMouseOver(this, event);"			onmouseout="return tableMouseOut(this, event);">
			<t:column id="altIdenProvcolumn1">
				<f:facet name="header">
					<h:panelGrid id="caseRegProvTablePGrid01" columns="2">
						<h:outputLabel id="caseRegProvColLabl01" value="#{msg['label.case.caseRegarding.type']}" for="altIdenProvTypeCommandLink1"/>
						<h:panelGroup id="caseRegProvTablePGroup01">
							<t:commandLink id="altIdenProvTypeCommandLink1"								styleClass="clStyle"								actionListener="#{logCaseControllerBean.sortAltIdenForProv}"								rendered="#{logCaseDataBean.imageRender != 'altIdenProvTypeCommandLink1'}"  onmousedown="javascript:flagWarn=false;focusPage('lgCsCaseRegFocusPage');">
								<h:graphicImage id="caseRegProvCol01Imag0" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}" styleClass="sort_asc"
									width="8" url="/images/x.gif"/>
								<f:attribute name="columnName" value="altIden_Prov_Type" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
							<m:br id="caseRegProvTablColBR0" />
							<t:commandLink id="altIdenProvTypeCommandLink2"								styleClass="clStyle"								actionListener="#{logCaseControllerBean.sortAltIdenForProv}"								rendered="#{logCaseDataBean.imageRender != 'altIdenProvTypeCommandLink2'}"  onmousedown="javascript:flagWarn=false;focusPage('lgCsCaseRegFocusPage');">
								<h:graphicImage id="caseRegProvCol01Imag1" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}" styleClass="sort_desc"
									width="8" url="/images/x.gif"/>
								<f:attribute name="columnName" value="altIden_Prov_Type" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
						</h:panelGroup>
					</h:panelGrid>
				</f:facet>
				<h:outputText id="caseRegProvColVal01" value="#{altIdenProvResult.alternateIDTypeCodeDesc}" />
			</t:column>
			<t:column id="altIdenProvcolumn2">
				<f:facet name="header">
					<h:panelGrid id="caseRegProvTablePGrid02" columns="2">
						<h:outputLabel id="caseRegProvColLabl02"							value="#{msg['label.case.caseRegarding.alternateID']}" for="altIdenProvAltIDCommandLink1"/>
						<h:panelGroup id="caseRegProvTablePGroup02">
							<t:commandLink id="altIdenProvAltIDCommandLink1"								styleClass="clStyle"								actionListener="#{logCaseControllerBean.sortAltIdenForProv}"								rendered="#{logCaseDataBean.imageRender != 'altIdenProvAltIDCommandLink1'}"  onmousedown="javascript:flagWarn=false;focusPage('lgCsCaseRegFocusPage');">
								<h:graphicImage id="caseRegProvCol01Imag2" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}" styleClass="sort_asc"
									width="8" url="/images/x.gif"/>
								<f:attribute name="columnName" value="altIden_Prov_AltID" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
							<m:br id="caseRegProvTablColBR1" />
							<t:commandLink id="altIdenProvAltIDCommandLink2"								styleClass="clStyle"								actionListener="#{logCaseControllerBean.sortAltIdenForProv}"								rendered="#{logCaseDataBean.imageRender != 'altIdenProvAltIDCommandLink2'}"  onmousedown="javascript:flagWarn=false;focusPage('lgCsCaseRegFocusPage');">
								<h:graphicImage  id="caseRegProvCol01Imag3" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"  styleClass="sort_desc"
									width="8" url="/images/x.gif"/>
								<f:attribute name="columnName" value="altIden_Prov_AltID" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
						</h:panelGroup>
					</h:panelGrid>
				</f:facet>
				<h:outputText  id="caseRegProvColVal02" value="#{altIdenProvResult.alternateID}" />
			</t:column>

			<t:column id="altIdenProvcolumn3">
				<f:facet name="header">
					<h:panelGrid id="caseRegProvTablePGrid03" columns="2">
						<h:outputLabel id="caseRegProvColLabl03"							value="#{msg['label.case.caseRegarding.lineOfBusiness']}" for="altIdenProvLOBCommandLink1"/>
						<h:panelGroup id="caseRegProvTablePGroup03">
							<t:commandLink id="altIdenProvLOBCommandLink1"								styleClass="clStyle"								actionListener="#{logCaseControllerBean.sortAltIdenForProv}"								rendered="#{logCaseDataBean.imageRender != 'altIdenProvLOBCommandLink1'}"  onmousedown="javascript:flagWarn=false;focusPage('lgCsCaseRegFocusPage');">
								<h:graphicImage id="caseRegProvCol01Imag4" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}" styleClass="sort_asc"
									width="8" url="/images/x.gif"/>
								<f:attribute name="columnName" value="altIden_Prov_LOB" />
								<f:attribute name="sortOrder" value="asc" />
							</t:commandLink>
							<m:br id="caseRegProvTablColBR2"/>
							<t:commandLink id="altIdenProvLOBCommandLink2"								styleClass="clStyle"								actionListener="#{logCaseControllerBean.sortAltIdenForProv}"								rendered="#{logCaseDataBean.imageRender != 'altIdenProvLOBCommandLink2'}"  onmousedown="javascript:flagWarn=false;focusPage('lgCsCaseRegFocusPage');">
								<h:graphicImage id="caseRegProvCol01Imag5" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}" styleClass="sort_desc"
									width="8" url="/images/x.gif"/>
								<f:attribute name="columnName" value="altIden_Prov_LOB" />
								<f:attribute name="sortOrder" value="desc" />
							</t:commandLink>
						</h:panelGroup>
					</h:panelGrid>
				</f:facet>
				<h:outputText id="caseRegProvColVal03" value="#{altIdenProvResult.lineOfBusiness}" />
			</t:column>
		</t:dataTable>
		<h:dataTable var="dummyaltIden_Prov"			rendered="#{!logCaseDataBean.showAlternateIdentifiersProv}"			styleClass="dataTable" cellspacing="0" width="100%" border="0"			headerClass="tableHead" rowClasses="rowone,row_alt"			id="altIden_ProvdummyTable">
			<t:column id="dummyaltIdenProvcolumn1">
				<f:facet name="header">
					<h:outputText id="caseRegProvDummyCol1" value="#{msg['label.case.caseRegarding.type']}" />
				</f:facet>
			</t:column>
			<t:column id="dummyaltIdenProvcolumn2">
				<f:facet name="header">
					<h:outputText id="caseRegProvDummyCol2"						value="#{msg['label.case.caseRegarding.alternateID']}" />
				</f:facet>
			</t:column>
			<t:column id="dummyaltIdenProvcolumn3">
				<f:facet name="header">
					<h:outputText id="caseRegProvDummyCol3"						value="#{msg['label.case.caseRegarding.lineOfBusiness']}" />
				</f:facet>
			</t:column>
		</h:dataTable>
		<m:table id="caseRegProvTableNoData" styleClass="dataTable" width="100%" border="0"
			rendered="#{!logCaseDataBean.showAlternateIdentifiersProv}">
			<m:tr id="caseRegProvTableNoDataTR">
				<m:td id="caseRegProvTableNoDataTRTD" align="center">
					<h:outputText id="caseRegProvTableNoDataVal" value="#{msg['value.noData']}" />
				</m:td>
			</m:tr>
		</m:table>
		<m:br id="caseRegProvTableNoDataBR" />
	</m:div>
</m:section>
