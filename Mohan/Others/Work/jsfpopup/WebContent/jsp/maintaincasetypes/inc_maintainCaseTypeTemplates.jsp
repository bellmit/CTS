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
<%-- Modified for the HeapDump Fix --%>
<h:dataTable cellspacing="0" styleClass="dataTable"
	columnClasses="columnClass" headerClass="headerClass"
	footerClass="footerClass" rowClasses="row_alt,row" id="templdataTable"
	width="100%" rows="10" var="templates"
	first="#{CaseTypeDataBean.maintainTemplatesDataTable}"
	value="#{CaseTypeDataBean.maintainCaseTemplatesList}"
	rendered="#{CaseTypeDataBean.noTemplatesData}">
	<h:column id="tempincludecol">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD130" columns="2">
				<h:outputLabel id="PRGCMGTOLL528" for="includeCommandLink1"
					value="#{msg['label.casetype.templates.include']}">
				</h:outputLabel>
				<h:panelGroup id="PRGCMGTPGP77">
					<m:div>
						<t:commandLink id="includeCommandLink1"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCaseTemplates}"
							rendered="#{CaseTypeDataBean.imageRender != 'includeCommandLink1'}">
							<h:graphicImage id="PROVIDERGI20120731164811489" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								styleClass="sort_asc" width="8" url="/images/x.gif" />
							<f:attribute name="columnName" value="t_include" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</m:div>
					<m:div>
						<t:commandLink id="includeCommandLink2"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCaseTemplates}"
							rendered="#{CaseTypeDataBean.imageRender != 'includeCommandLink2'}">
							<h:graphicImage id="PROVIDERGI20120731164811490" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								styleClass="sort_desc" width="8" url="/images/x.gif" />
							<f:attribute name="columnName" value="t_include" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</m:div>
				</h:panelGroup>
			</h:panelGrid>


		</f:facet>
		<h:outputLabel id="checkboxMCTMColumnLabel1"  for="entitycheckbox" styleClass="hide" value="#{msg['label.casetype.templates.include']}" ></h:outputLabel>
		<h:selectBooleanCheckbox id="entitycheckbox"
			disabled="#{CaseTypeDataBean.caseTypeNoActive||CaseTypeDataBean.disableUIFields}"
			value="#{templates.includeTemplate}"></h:selectBooleanCheckbox>
	</h:column>
	<h:column id="tempcol">
		<f:facet name="header">

			<h:panelGrid id="PRGCMGTPGD131" columns="2">
				<h:outputLabel id="PRGCMGTOLL529" for="templateCommandLink1"
					value="#{msg['label.casetype.templates.template']}">
				</h:outputLabel>
				<h:panelGroup id="PRGCMGTPGP78">
					<m:div>
						<t:commandLink id="templateCommandLink1"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCaseTemplates}"
							rendered="#{CaseTypeDataBean.imageRender != 'templateCommandLink1'}">
							<h:graphicImage id="PROVIDERGI20120731164811491" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								styleClass="sort_asc" width="8" url="/images/x.gif" />

							<f:attribute name="columnName" value="t_template" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</m:div>
					<m:div>
						<t:commandLink id="templateCommandLink2"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCaseTemplates}"
							rendered="#{CaseTypeDataBean.imageRender != 'templateCommandLink2'}">
							<h:graphicImage id="PROVIDERGI20120731164811492" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								styleClass="sort_desc" width="8" url="/images/x.gif" />
							<f:attribute name="columnName" value="t_template" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</m:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>
		<h:outputText id="PRGCMGTOT1136" styleClass="outputLabel"
			value="#{templates.template}">
		</h:outputText>
	</h:column>
	<h:column id="tempdesccol">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD132" columns="2">
				<h:outputLabel id="PRGCMGTOLL530" for="templateDescCommandLink1"
					value="#{msg['label.casetype.templates.templateDesc']}">
				</h:outputLabel>
				<h:panelGroup id="PRGCMGTPGP79">
					<m:div>
						<t:commandLink id="templateDescCommandLink1"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCaseTemplates}"
							rendered="#{CaseTypeDataBean.imageRender != 'templateDescCommandLink1'}">
							<h:graphicImage id="PROVIDERGI20120731164811493" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								styleClass="sort_asc" width="8" url="/images/x.gif" />

							<f:attribute name="columnName" value="t_templateDesc" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</m:div>
					<m:div>
						<t:commandLink id="templateDescCommandLink2"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCaseTemplates}"
							rendered="#{CaseTypeDataBean.imageRender != 'templateDescCommandLink2'}">
							<h:graphicImage id="PROVIDERGI20120731164811494" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								styleClass="sort_desc" width="8" url="/images/x.gif" />
							<f:attribute name="columnName" value="t_templateDesc" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</m:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>
		<h:outputText id="PRGCMGTOT1137" styleClass="outputLabel"
			value="#{templates.templateDescription}" />
	</h:column>
	<h:column id="tempoptionaltectcol">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD133" columns="2">
				<h:outputLabel id="PRGCMGTOLL531" for="otCommandLink1"
					value="#{msg['label.casetype.templates.optionalText']}">
				</h:outputLabel>
				<h:panelGroup id="PRGCMGTPGP80">
					<m:div>
						<t:commandLink id="otCommandLink1"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCaseTemplates}"
							rendered="#{CaseTypeDataBean.imageRender != 'otCommandLink1'}">
							<h:graphicImage id="PROVIDERGI20120731164811495" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								styleClass="sort_asc" width="8" url="/images/x.gif" />

							<f:attribute name="columnName" value="t_optionalText" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</m:div>
					<m:div>
						<t:commandLink id="otCommandLink2"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCaseTemplates}"
							rendered="#{CaseTypeDataBean.imageRender != 'otCommandLink2'}">
							<h:graphicImage id="PROVIDERGI20120731164811496" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								styleClass="sort_desc" width="8" url="/images/x.gif" />
							<f:attribute name="columnName" value="t_optionalText" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</m:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>
		<h:graphicImage id="PROVIDERGI20120731164811497" alt="#{cont['label.ref.yes']}" title="#{cont['label.ref.yes']}"
			styleClass="ind_check_yes" width="15"
			rendered="#{templates.optionalText}" url="/images/x.gif"></h:graphicImage>

	</h:column>

	<h:column id="tempsupercol">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD134" columns="2">
				<h:outputLabel id="PRGCMGTOLL532" for="t_superReqdCommandLink1"
					value="#{msg['label.casetype.templates.superApproval']}">
				</h:outputLabel>
				<h:panelGroup id="PRGCMGTPGP81">
					<m:div>
						<t:commandLink id="t_superReqdCommandLink1"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCaseTemplates}"
							rendered="#{CaseTypeDataBean.imageRender != 't_superReqdCommandLink1'}">
							<h:graphicImage id="PROVIDERGI20120731164811498" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								styleClass="sort_asc" width="8" url="/images/x.gif" />

							<f:attribute name="columnName" value="t_superReqd" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</m:div>
					<m:div>
						<t:commandLink id="t_superReqdCommandLink2"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCaseTemplates}"
							rendered="#{CaseTypeDataBean.imageRender != 't_superReqdCommandLink2'}">
							<h:graphicImage id="PROVIDERGI20120731164811499" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								styleClass="sort_desc" width="8" url="/images/x.gif" />
							<f:attribute name="columnName" value="t_superReqd" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</m:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>
		<h:graphicImage id="PROVIDERGI20120731164811500" alt="#{cont['label.ref.yes']}" title="#{cont['label.ref.yes']}"
			styleClass="ind_check_yes" width="15"
			rendered="#{templates.supervisorApprRqd}" url="/images/x.gif"></h:graphicImage>
	</h:column>

</h:dataTable>
<h:dataTable id="PRGCMGTDT3" cellspacing="0" styleClass="dataTable"
	columnClasses="columnClass" headerClass="headerClass"
	footerClass="footerClass" rowClasses="row_alt,row" width="100%"
	rendered="#{!CaseTypeDataBean.noTemplatesData}">

	<h:column id="PRGCMGTC55">
		<f:facet name="header">
			<h:outputText id="PRGCMGTOT1138"
				value="#{msg['label.casetype.templates.include']}">
			</h:outputText>
		</f:facet>
	</h:column>
	<h:column id="PRGCMGTC56">
		<f:facet name="header">
			<h:outputText id="PRGCMGTOT1139"
				value="#{msg['label.casetype.templates.template']}">
			</h:outputText>
		</f:facet>
	</h:column>
	<h:column id="PRGCMGTC57">
		<f:facet name="header">
			<h:outputText id="PRGCMGTOT1140"
				value="#{msg['label.casetype.templates.templateDesc']}">
			</h:outputText>
		</f:facet>
	</h:column>
	<h:column id="PRGCMGTC58">
		<f:facet name="header">
			<h:outputText id="PRGCMGTOT1141"
				value="#{msg['label.casetype.templates.optionalText']}">
			</h:outputText>
		</f:facet>
	</h:column>

	<h:column id="PRGCMGTC59">
		<f:facet name="header">
			<h:outputText id="PRGCMGTOT1142"
				value="#{msg['label.casetype.templates.superApproval']}">
			</h:outputText>
		</f:facet>
	</h:column>

</h:dataTable>
<m:div onclick="javascript:flagWarn=false;"
	onmousedown="javascript:flagWarn=false;">
	<t:dataScroller id="CMGTTOMDS41" pageCountVar="pageCount"
		pageIndexVar="pageIndex" onclick="javascript:flagWarn=false;"
		paginator="true" paginatorActiveColumnStyle='font-weight:bold;'
		paginatorMaxPages="3" immediate="false" for="templdataTable"
		firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
		rowsCountVar="rowsCount" styleClass="dataScroller">
		<f:facet name="previous">
			<h:outputText id="PRGCMGTOT1143" styleClass="underline"
				value="#{cont['label.ref.lt']}" rendered="#{pageIndex > 1}"></h:outputText>
		</f:facet>
		<f:facet name="next">
			<h:outputText id="PRGCMGTOT1144" styleClass="underline"
				value="#{cont['label.ref.gt']}" rendered="#{pageIndex < pageCount}"></h:outputText>
		</f:facet>
		<h:outputText id="PRGCMGTOT1145"
			value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
			styleClass="dataScrollerText" rendered="#{rowsCount>0}">
		</h:outputText>
	</t:dataScroller>
</m:div>
