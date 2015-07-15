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
<%-- Modified for the HeapDump Fix --%>
<h:dataTable cellspacing="0" styleClass="dataTable"
	columnClasses="columnClass" headerClass="headerClass"
	footerClass="footerClass" rowClasses="row_alt,row" id="cfdataTable"
	width="100%" rows="10" var="customfields"
	first="#{CaseTypeDataBean.maintainCFDataTable}"
	value="#{CaseTypeDataBean.maintainCaseCustomFieldsList}"
	rendered="#{CaseTypeDataBean.nocustomfieldsData}">

	<h:column id="cfincludecol">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD124" columns="2">
				<h:outputLabel id="PRGCMGTOLL511" for="incCommandLink1"
					value="#{msg['label.casetype.customfields.include']}">
				</h:outputLabel>
				<h:panelGroup id="PRGCMGTPGP71">
					<m:div>
						<t:commandLink id="incCommandLink1"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCustomFields}"
							rendered="#{CaseTypeDataBean.imageRender != 'incCommandLink1'}">
							<h:graphicImage id="PROVIDERGI20120731164811474" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								styleClass="sort_asc" width="8" url="/images/x.gif" />
							<f:attribute name="coloumnDesc" value="activeValue" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</m:div>
					<m:div>
						<t:commandLink id="activeCommandLink2"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCustomFields}"
							rendered="#{CaseTypeDataBean.imageRender != 'activeCommandLink2'}">
							<!--ESPRD00639251
							defect fixed -->
							<h:graphicImage id="PROVIDERGI20120731164811475" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								styleClass="sort_desc" width="8" url="/images/x.gif" />
							<f:attribute name="coloumnDesc" value="activeValue" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</m:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>
		<h:outputLabel id="checkboxMCCFColumnLabel1"  for="entitycheckbox" styleClass="hide" value="#{msg['label.casetype.customfields.include']}" ></h:outputLabel>
		<h:selectBooleanCheckbox id="entitycheckbox"
			disabled="#{CaseTypeDataBean.caseTypeNoActive||CaseTypeDataBean.disableUIFields}"
			value="#{customfields.customFieldSelected}"></h:selectBooleanCheckbox>
	</h:column>
	<h:column id="cfdesccol">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD125" columns="2">

				<h:outputLabel id="PRGCMGTOLL512" for="cfdesccolCommandLink1"
					value="#{msg['label.casetype.customfields.columnDescription']}">
				</h:outputLabel>
				<h:panelGroup id="PRGCMGTPGP72">
					<m:div>
						<t:commandLink id="cfdesccolCommandLink1"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCustomFields}"
							rendered="#{CaseTypeDataBean.imageRender != 'cfdesccolCommandLink1'}">
							<h:graphicImage id="PROVIDERGI20120731164811476" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								styleClass="sort_asc" width="8" url="/images/x.gif" />

							<f:attribute name="coloumnDesc" value="ce_typeValue" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</m:div>
					<m:div>
						<t:commandLink id="cfdesccolCommandLink2"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCustomFields}"
							rendered="#{CaseTypeDataBean.imageRender != 'cfdesccolCommandLink2'}">
							<h:graphicImage id="PROVIDERGI20120731164811477" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								styleClass="sort_desc" width="8" url="/images/x.gif" />

							<f:attribute name="coloumnDesc" value="ce_typeValue" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</m:div>
				</h:panelGroup>
			</h:panelGrid>

		</f:facet>
		<h:outputText id="PRGCMGTOT1132" styleClass="outputLabel"
			value="#{customfields.columnDescription}">
		</h:outputText>

	</h:column>
	<h:column id="cfdatatypecol">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD126" columns="2">

				<h:outputLabel id="PRGCMGTOLL513" for="cfdatatypecolCommandLink1"
					value="#{msg['label.casetype.customfields.dataType']}">
				</h:outputLabel>
				<h:panelGroup id="PRGCMGTPGP73">
					<m:div>
						<t:commandLink id="cfdatatypecolCommandLink1"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCustomFields}"
							rendered="#{CaseTypeDataBean.imageRender != 'cfdatatypecolCommandLink1'}">
							<h:graphicImage id="PROVIDERGI20120731164811478" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								styleClass="sort_asc" width="8" url="/images/x.gif" />

							<f:attribute name="coloumnDesc" value="cf_dataTypeValue" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</m:div>
					<m:div>
						<t:commandLink id="cfdatatypecolCommandLink2"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCustomFields}"
							rendered="#{CaseTypeDataBean.imageRender != 'cfdatatypecolCommandLink2'}">
							<h:graphicImage id="PROVIDERGI20120731164811479" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								styleClass="sort_desc" width="8" url="/images/x.gif" />

							<f:attribute name="coloumnDesc" value="cf_dataTypeValue" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</m:div>
				</h:panelGroup>
			</h:panelGrid>

		</f:facet>
		<h:outputLabel id="PRGCMGTOLL514" value="#{customfields.dataType}" />
	</h:column>
	<h:column id="cffieldlengthcol">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD127" columns="2">
				<h:outputLabel id="PRGCMGTOLL515" for="cffieldlengthCommandLink1"
					value="#{msg['label.casetype.customfields.fieldLength']}">
				</h:outputLabel>
				<h:panelGroup id="PRGCMGTPGP74">
					<m:div>
						<t:commandLink id="cffieldlengthCommandLink1"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCustomFields}"
							rendered="#{CaseTypeDataBean.imageRender != 'cffieldlengthCommandLink1'}">
							<h:graphicImage id="PROVIDERGI20120731164811480" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								styleClass="sort_asc" width="8" url="/images/x.gif" />

							<f:attribute name="coloumnDesc" value="cf_fieldLen" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</m:div>
					<m:div>
						<t:commandLink id="cffieldlengthCommandLink2"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCustomFields}"
							rendered="#{CaseTypeDataBean.imageRender != 'cffieldlengthCommandLink2'}">
							<h:graphicImage id="PROVIDERGI20120731164811481" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								styleClass="sort_desc" width="8" url="/images/x.gif" />

							<f:attribute name="coloumnDesc" value="cf_fieldLen" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</m:div>
				</h:panelGroup>
			</h:panelGrid>

		</f:facet>
		<h:outputLabel id="PRGCMGTOLL516" value="#{customfields.length}" />
	</h:column>

	<h:column id="requiredcol">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD128" columns="2">
				<h:outputLabel id="PRGCMGTOLL517" for="requiredcolCommandLink1"
					value="#{msg['label.casetype.customfields.required']}">
				</h:outputLabel>
				<h:panelGroup id="PRGCMGTPGP75">
					<m:div>
						<t:commandLink id="requiredcolCommandLink1"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCustomFields}"
							rendered="#{CaseTypeDataBean.imageRender != 'requiredcolCommandLink1'}">
							<h:graphicImage id="PROVIDERGI20120731164811482" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								styleClass="sort_asc" width="8" url="/images/x.gif" />

							<f:attribute name="columnName" value="cf_required" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</m:div>
					<m:div>
						<t:commandLink id="requiredcolCommandLink2"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCustomFields}"
							rendered="#{CaseTypeDataBean.imageRender != 'requiredcolCommandLink2'}">
							<h:graphicImage id="PROVIDERGI20120731164811483" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								styleClass="sort_desc" width="8" url="/images/x.gif" />

							<f:attribute name="columnName" value="cf_required" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</m:div>
				</h:panelGroup>
			</h:panelGrid>

		</f:facet>
		<%--<h:outputLabel id="PRGCMGTOLL518" value="#{customfields.requiredInd}" />--%>
		<h:graphicImage id="PROVIDERGI20120731164811484" alt="#{cont['label.ref.yes']}" title="#{cont['label.ref.yes']}" styleClass="ind_check_yes" width="15"
			rendered="#{customfields.requiredInd == 'true'}" url="/images/x.gif"></h:graphicImage>
	</h:column>


	<h:column id="potectedcol">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD129" columns="2">
				<h:outputLabel id="PRGCMGTOLL519" for="potectedcolCommandLink1"
					value="#{msg['label.casetype.customfields.protectedOnSave']}">
				</h:outputLabel>
				<h:panelGroup id="PRGCMGTPGP76">
					<m:div>
						<t:commandLink id="potectedcolCommandLink1"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCustomFields}"
							rendered="#{CaseTypeDataBean.imageRender != 'potectedcolCommandLink1'}">
							<h:graphicImage id="PROVIDERGI20120731164811485" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								styleClass="sort_asc" width="8" url="/images/x.gif" />

							<f:attribute name="columnName" value="cf_protected" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</m:div>
					<m:div>
						<t:commandLink id="potectedcolCommandLink2"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCustomFields}"
							rendered="#{CaseTypeDataBean.imageRender != 'potectedcolCommandLink2'}">
							<h:graphicImage id="PROVIDERGI20120731164811486" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								styleClass="sort_desc" width="8" url="/images/x.gif" />

							<f:attribute name="columnName" value="cf_protected" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</m:div>
				</h:panelGroup>
			</h:panelGrid>

		</f:facet>
		<h:graphicImage id="PROVIDERGI20120731164811487" alt="#{cont['label.ref.yes']}" title="#{cont['label.ref.yes']}"
			styleClass="ind_check_yes" width="15"
			rendered="#{customfields.protectedInd == 'true'}" url="/images/x.gif"></h:graphicImage>
		<h:outputLabel id="PRGCMGTOLL520" value="" />

	</h:column>

</h:dataTable>
<h:dataTable id="PRGCMGTDT2" cellspacing="0" styleClass="dataTable"
	columnClasses="columnClass" headerClass="headerClass"
	footerClass="footerClass" rowClasses="row_alt,row" width="100%"
	rendered="#{!CaseTypeDataBean.nocustomfieldsData}">

	<h:column id="PRGCMGTC49">
		<f:facet name="header">
			<h:outputLabel id="PRGCMGTOLL521"
				value="#{msg['label.casetype.customfields.include']}">
			</h:outputLabel>
		</f:facet>
	</h:column>
	<h:column id="PRGCMGTC50">
		<f:facet name="header">
			<h:outputLabel id="PRGCMGTOLL522"
				value="#{msg['label.casetype.customfields.columnDescription']}">
			</h:outputLabel>
		</f:facet>
	</h:column>
	<h:column id="PRGCMGTC51">
		<f:facet name="header">
			<h:outputLabel id="PRGCMGTOLL523"
				value="#{msg['label.casetype.customfields.dataType']}">
			</h:outputLabel>
		</f:facet>
	</h:column>
	<h:column id="PRGCMGTC52">
		<f:facet name="header">
			<h:outputLabel id="PRGCMGTOLL524"
				value="#{msg['label.casetype.customfields.fieldLength']}">
			</h:outputLabel>
		</f:facet>
	</h:column>
	<h:column id="PRGCMGTC53">
		<f:facet name="header">
			<h:outputLabel id="PRGCMGTOLL525"
				value="#{msg['label.casetype.customfields.required']}">
			</h:outputLabel>
		</f:facet>
	</h:column>
	<h:column id="PRGCMGTC54">
		<f:facet name="header">
			<h:outputLabel id="PRGCMGTOLL526"
				value="#{msg['label.casetype.customfields.protectedOnSave']}">
			</h:outputLabel>
		</f:facet>
	</h:column>
</h:dataTable>
<m:table id="PROVIDERMMT20120731164811488" styleClass="dataTable" cellspacing="0" width="100%"
	rendered="#{!CaseTypeDataBean.nocustomfieldsData}">
	<m:tr>
		<m:td align="center">
			<h:outputLabel id="PRGCMGTOLL527"
				value="#{msg['label.casetype.table.notabledata']}" />
		</m:td>
	</m:tr>
</m:table>
<%--  Data Scroller--%>
<m:div onclick="javascript:flagWarn=false;"
	onmousedown="javascript:flagWarn=false;">
	<t:dataScroller id="CMGTTOMDS40" pageCountVar="pageCount"
		pageIndexVar="pageIndex" onclick="javascript:flagWarn=false;"
		paginator="true" paginatorActiveColumnStyle='font-weight:bold;'
		paginatorMaxPages="3" immediate="false" for="cfdataTable"
		firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
		rowsCountVar="rowsCount" styleClass="dataScroller">
		<f:facet name="previous">
			<h:outputText id="PRGCMGTOT1133" styleClass="underline"
				value="#{cont['label.ref.lt']}" rendered="#{pageIndex > 1}"></h:outputText>
		</f:facet>
		<f:facet name="next">
			<h:outputText id="PRGCMGTOT1134" styleClass="underline"
				value="#{cont['label.ref.gt']}" rendered="#{pageIndex < pageCount}"></h:outputText>
		</f:facet>
		<h:outputText id="PRGCMGTOT1135"
			value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
			styleClass="dataScrollerText" rendered="#{rowsCount>0}">
		</h:outputText>
	</t:dataScroller>
</m:div>
