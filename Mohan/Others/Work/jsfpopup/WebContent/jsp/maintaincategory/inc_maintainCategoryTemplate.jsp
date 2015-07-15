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

<h:dataTable cellspacing="0" styleClass="dataTable"
	columnClasses="columnClass" headerClass="headerClass" onmousedown="flagWarn=false;"
	footerClass="footerClass" rowClasses="row_alt,row"
	id="templatedataTable" width="100%" rows="10" var="templates"
	value="#{CategoryDataBean.listOfCategoryTemplateVOs}"
	rendered="#{CategoryDataBean.renderNoDataTemplate}"
	onmouseover="return tableMouseOver(this, event);"
	onmouseout="return tableMouseOut(this, event);">
	<h:column id="tempincludecol">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD143" columns="2">
				<h:outputLabel id="PRGCMGTOLL543" for="includeTemplateAcs"
					value="#{ctg['label.category.templates.include']}">
				</h:outputLabel>
				<h:panelGroup id="PRGCMGTPGP92">
					<t:div styleClass="alignLeft">
						<t:commandLink id="includeTemplateAcs"
							onmousedown="flagWarn=false;"
							actionListener="#{CategoryControllerBean.sortTemplate}"
							rendered="#{CategoryDataBean.imageRender != 'includeTemplateAcs'}">
							<%--Defect Fixed for sorting image 10/04/2011--%>
							<%--<h:graphicImage id="sortdescid1"
								alt="#{ctg['title.category.forAscd']}" styleClass="sort_asc"
								width="8" />  --%>
							<m:div title="#{ctg['title.category.forAscd']}"
									styleClass="sort_asc" />
							<f:attribute name="#{ctg['id.category.columnName']}"
								value="Include Template" />
							<f:attribute name="#{ctg['id.category.sortOrder']}"
								value="ascending" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="includeTemplateDes"
							onmousedown="flagWarn=false;"
							actionListener="#{CategoryControllerBean.sortTemplate}"
							rendered="#{CategoryDataBean.imageRender != 'includeTemplateDes'}">
							<%--Defect Fixed for sorting image 10/04/2011--%>
							<%--<h:graphicImage id="sortdescid2"
								alt="#{ctg['title.category.forDsnd']}" styleClass="sort_desc"
								width="8" />  --%>
								
							<m:div title="#{ctg['title.category.forDsnd']}"
									styleClass="sort_desc" />
							<f:attribute name="#{ctg['id.category.columnName']}"
								value="Include Template" />
							<f:attribute name="#{ctg['id.category.sortOrder']}"
								value="descending" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>
		
		
		<h:outputLabel id="checkboxColumnLabelCR1744" for="tempcheckbox" styleClass="hide" value="#{ctg['label.category.templates.include']}" ></h:outputLabel>
		<%--disabled attribute is added below for fixing the defect ESPRD00802214 on 03/07/2012--%>
		
		<h:selectBooleanCheckbox id="tempcheckbox"
			value="#{templates.includeTemplate}" disabled="#{!CategoryControllerBean.controlRequired}"></h:selectBooleanCheckbox>
	</h:column>
	<h:column id="tempcol">
		<f:facet name="header">

			<h:panelGrid id="PRGCMGTPGD144" columns="2">
				<h:outputLabel id="PRGCMGTOLL544" for="templateAcs"
					value="#{ctg['label.category.templates.template']}">
				</h:outputLabel>
				<h:panelGroup id="PRGCMGTPGP93">
					<t:div styleClass="alignLeft">
						<t:commandLink id="templateAcs"
							onmousedown="flagWarn=false;"
							actionListener="#{CategoryControllerBean.sortTemplate}"
							rendered="#{CategoryDataBean.imageRender != 'templateAcs'}">
							<%--Defect Fixed for sorting image 10/04/2011--%>
							<%--<h:graphicImage id="sortdescid3"
								alt="#{ctg['title.category.forAscd']}" styleClass="sort_asc"
								width="8" />  --%>
								
							<m:div title="#{ctg['title.category.forAscd']}"
									styleClass="sort_asc" />
							<f:attribute name="#{ctg['id.category.columnName']}"
								value="Template" />
							<f:attribute name="#{ctg['id.category.sortOrder']}"
								value="ascending" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="templateDes"
							onmousedown="flagWarn=false;"
							actionListener="#{CategoryControllerBean.sortTemplate}"
							rendered="#{CategoryDataBean.imageRender != 'templateDes'}">
							<%--Defect Fixed for sorting image 10/04/2011--%>
							<%--<h:graphicImage id="sortdescid4"
								alt="#{ctg['title.category.forDsnd']}" styleClass="sort_desc"
								width="8" /> --%>
								
							<m:div title="#{ctg['title.category.forDsnd']}"
									styleClass="sort_desc" />
							<f:attribute name="#{ctg['id.category.columnName']}"
								value="Template" />
							<f:attribute name="#{ctg['id.category.sortOrder']}"
								value="descending" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>
		<t:commandLink id="generateTemplates1" action="rendersuccess" onmousedown="flagWarn=false;">
			<h:outputText id="PRGCMGTOT1227" styleClass="outputLabel"
				value="#{templates.template}">
			</h:outputText>
		</t:commandLink>
	</h:column>
	<h:column id="tempdesccol">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD145" columns="2">
				<h:outputLabel id="PRGCMGTOLL545" for="templateDescriptionAcs"
					value="#{ctg['label.category.templates.templateDesc']}">
				</h:outputLabel>
				<h:panelGroup id="PRGCMGTPGP94">
					<t:div styleClass="alignLeft">
						<t:commandLink id="templateDescriptionAcs"
							onmousedown="flagWarn=false;"
							actionListener="#{CategoryControllerBean.sortTemplate}"
							rendered="#{CategoryDataBean.imageRender != 'templateDescriptionAcs'}">
							<%--Defect Fixed for sorting image 10/04/2011--%>
							<%--<h:graphicImage id="sortdescid5"
								alt="#{ctg['title.category.forAscd']}" styleClass="sort_asc"
								width="8" /> --%>
							<m:div title="#{ctg['title.category.forAscd']}"
									styleClass="sort_asc" />
							<f:attribute name="#{ctg['id.category.columnName']}"
								value="Template Description" />
							<f:attribute name="#{ctg['id.category.sortOrder']}"
								value="ascending" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="templateDescriptionDes"
							onmousedown="flagWarn=false;"
							actionListener="#{CategoryControllerBean.sortTemplate}"
							rendered="#{CategoryDataBean.imageRender != 'templateDescriptionDes'}">
							<%--Defect Fixed for sorting image 10/04/2011--%>
							<%--<h:graphicImage id="sortdescid6"
								alt="#{ctg['title.category.forDsnd']}" styleClass="sort_desc"
								width="8" /> --%>
								
							<m:div title="#{ctg['title.category.forDsnd']}"
									styleClass="sort_desc" />
							<f:attribute name="#{ctg['id.category.columnName']}"
								value="Template Description" />
							<f:attribute name="#{ctg['id.category.sortOrder']}"
								value="descending" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>

		<h:outputText id="PRGCMGTOT1228" styleClass="outputLabel"
			value="#{templates.templateDescription}" />

	</h:column>
	<t:column id="tempoptionaltectcol" width="80px">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD146" columns="2" width="5px">
				<h:outputLabel id="PRGCMGTOLL546" for="optionalTextAcs"
					value="#{ctg['label.category.templates.optionalText']}">
				</h:outputLabel>
				<h:panelGroup id="PRGCMGTPGP95">
					<t:div styleClass="alignLeft">
						<t:commandLink id="optionalTextAcs"
							onmousedown="flagWarn=false;"
							actionListener="#{CategoryControllerBean.sortTemplate}"
							rendered="#{CategoryDataBean.imageRender != 'optionalTextAcs'}">
							<%--Defect Fixed for sorting image 10/04/2011--%>
							<%--<h:graphicImage id="sortdescid7"
								alt="#{ctg['title.category.forAscd']}" styleClass="sort_asc"
								width="8" /> --%>
								
							<m:div title="#{ctg['title.category.forAscd']}"
									styleClass="sort_asc" />
							<f:attribute name="#{ctg['id.category.columnName']}"
								value="Optional Text" />
							<f:attribute name="#{ctg['id.category.sortOrder']}"
								value="ascending" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="optionalTextDes"
							onmousedown="flagWarn=false;"
							actionListener="#{CategoryControllerBean.sortTemplate}"
							rendered="#{CategoryDataBean.imageRender != 'optionalTextDes'}">
							<%--Defect Fixed for sorting image 10/04/2011--%>
							<%--<h:graphicImage id="sortdescid8"
								alt="#{ctg['title.category.forDsnd']}" styleClass="sort_desc"
								width="8" /> --%>
							
							<m:div title="#{ctg['title.category.forDsnd']}"
									styleClass="sort_desc" />
							<f:attribute name="#{ctg['id.category.columnName']}"
								value="Optional Text" />
							<f:attribute name="#{ctg['id.category.sortOrder']}"
								value="descending" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>

		<h:graphicImage id="PROVIDERGI20120731164811527"  alt="#{ctg['label.category.templates.yes']}" title="#{ctg['label.category.templates.yes']}"
			styleClass="ind_check_yes" width="15"
			rendered="#{templates.optionalText}" url="/images/x.gif"></h:graphicImage>


	</t:column>

	<t:column id="tempsupercol" width="100px">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD147" columns="2" width="5px">
				<h:outputLabel id="PRGCMGTOLL547" for="supervisorApprRqdAcs"
					value="#{ctg['label.category.templates.superAppreq']}">
				</h:outputLabel>
				<h:panelGroup id="PRGCMGTPGP96">
					<t:div styleClass="alignLeft">
						<t:commandLink id="supervisorApprRqdAcs"
							onmousedown="flagWarn=false;"
							actionListener="#{CategoryControllerBean.sortTemplate}"
							rendered="#{CategoryDataBean.imageRender != 'supervisorApprRqdAcs'}">
							<%--Defect Fixed for sorting image 10/04/2011--%>
							<%--<h:graphicImage id="sortdescid9"
								alt="#{ctg['title.category.forAscd']}" styleClass="sort_asc"
								width="8" /> --%>
								
							<m:div title="#{ctg['title.category.forAscd']}"
									styleClass="sort_asc" />
							<f:attribute name="#{ctg['id.category.columnName']}"
								value="Supervisor Approval Required" />
							<f:attribute name="#{ctg['id.category.sortOrder']}"
								value="ascending" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="supervisorApprRqdDes"
							onmousedown="flagWarn=false;"
							actionListener="#{CategoryControllerBean.sortTemplate}"
							rendered="#{CategoryDataBean.imageRender != 'supervisorApprRqdDes'}">
							<%--<h:graphicImage id="sortdescid10"
								alt="#{ctg['title.category.forDsnd']}" styleClass="sort_desc"
								width="8" /> --%>
								
							<m:div title="#{ctg['title.category.forDsnd']}"
									styleClass="sort_desc" />
							<f:attribute name="#{ctg['id.category.columnName']}"
								value="Supervisor Approval Required" />
							<f:attribute name="#{ctg['id.category.sortOrder']}"
								value="descending" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>

		<h:graphicImage id="PROVIDERGI20120731164811528" alt="#{ctg['label.category.templates.yes']}" title="#{ctg['label.category.templates.yes']}"
			styleClass="ind_check_yes" width="15"
			rendered="#{templates.supervisorApprRqd}" url="/images/x.gif"></h:graphicImage>

	</t:column>

</h:dataTable>
<h:dataTable id="PRGCMGTDT4" cellspacing="0" styleClass="dataTable"
	columnClasses="columnClass" headerClass="headerClass"
	footerClass="footerClass" rowClasses="row_alt,row" width="100%"
	rendered="#{!CategoryDataBean.renderNoDataTemplate}">

	<h:column id="PRGCMGTC67">
		<f:facet name="header">
			<h:outputText id="PRGCMGTOT1229"
				value="#{ctg['label.category.templates.include']}">
			</h:outputText>
		</f:facet>
	</h:column>
	<h:column id="PRGCMGTC68">
		<f:facet name="header">
			<h:outputText id="PRGCMGTOT1230"
				value="#{ctg['label.category.templates.template']}">
			</h:outputText>
		</f:facet>
	</h:column>
	<h:column id="PRGCMGTC69">
		<f:facet name="header">
			<h:outputText id="PRGCMGTOT1231"
				value="#{ctg['label.category.templates.templateDesc']}">
			</h:outputText>
		</f:facet>
	</h:column>
	<h:column id="PRGCMGTC70">
		<f:facet name="header">
			<h:outputText id="PRGCMGTOT1232"
				value="#{ctg['label.category.templates.optionalText']}">
			</h:outputText>
		</f:facet>
	</h:column>

	<h:column id="PRGCMGTC71">
		<f:facet name="header">
			<h:outputText id="PRGCMGTOT1233"
				value="#{ctg['label.category.templates.superAppreq']}">
			</h:outputText>
		</f:facet>
	</h:column>

</h:dataTable>
<m:table id="PROVIDERMMT20120731164811529" styleClass="dataTable" cellspacing="0" width="100%"
	rendered="#{!CategoryDataBean.renderNoDataTemplate}">
	<m:tr>
		<m:td align="center">
			<h:outputText id="PRGCMGTOT1234" value="#{ref['label.ref.noData']}" />
		</m:td>
	</m:tr>
</m:table>
<t:dataScroller id="PROVIDERMDS20120731164811530" pageCountVar="pageCount" pageIndexVar="pageIndex"
	paginator="true" paginatorActiveColumnStyle='font-weight:bold;'
	paginatorMaxPages="3" immediate="false" for="templatedataTable"
	firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
	rowsCountVar="rowsCount" styleClass="dataScroller">
	<f:facet name="previous">
		<h:outputText id="PRGCMGTOT1235" styleClass="underline"
			value="#{ref['label.ref.lt']}" rendered="#{pageIndex > 1}"></h:outputText>
	</f:facet>
	<f:facet name="next">
		<h:outputText id="PRGCMGTOT1236" styleClass="underline"
			value="#{ref['label.ref.gt']}" rendered="#{pageIndex < pageCount}"></h:outputText>
	</f:facet>
	<h:outputText id="PRGCMGTOT1237"
		value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
		styleClass="dataScrollerText" rendered="#{rowsCount>0}">
	</h:outputText>
</t:dataScroller>


