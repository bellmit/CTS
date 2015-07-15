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

<h:messages id="PRGCMGTMS7" />

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>

<%--Added RowIndexVar below to data table for ESPRD00795431 to get the row index --%>
<t:dataTable cellspacing="0" styleClass="dataTable" 	columnClasses="columnClass" headerClass="headerClass"	footerClass="footerClass" rowClasses="row_alt,row" id="categoryDT"	width="100%" var="csCat" value="#{CallScriptDataBean.categoryList}"	rows="10" onmouseover="return tableMouseOver(this, event);"	onmouseout="return tableMouseOut(this, event);" rowIndexVar="rowindexCT">

	<h:column id="incCallScptincat">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD110" columns="2">
				<h:outputLabel id="PRGCMGTOLL477" for="inccs"					value="#{msg['label.callscript.includeCS']}">
				</h:outputLabel>
				<h:panelGroup id="inccs">
					<t:div styleClass="alignLeft">
						<t:commandLink id="incCallScriptCommandLink1"							actionListener="#{CallScriptControllerBean.sortCategory}"							rendered="#{CallScriptDataBean.imageRender !='incCallScriptCommandLink1'}"							onmousedown="javascript:flagWarn=false;">

							<%--<h:graphicImage alt="#{msg['label.callscript.forAscd']}"
							styleClass="sort_asc" width="8" url="/images/x.gif"/> --%>
							
							<m:div title="#{msg['label.callscript.forAscd']}" styleClass="sort_asc" />

							<f:attribute name="#{msg['id.callscript.sortColumn']}"
								value="includeval" />
							<f:attribute name="#{msg['id.callscript.sortOrder']}" value="asc" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="incCallScriptCommandLink2"							actionListener="#{CallScriptControllerBean.sortCategory}"							rendered="#{CallScriptDataBean.imageRender !='incCallScriptCommandLink2'}"							onmousedown="javascript:flagWarn=false;">

							<%--<h:graphicImage alt="#{msg['label.callscript.forDsnd']}"
								styleClass="sort_desc" width="8" url="/images/x.gif"/> --%>
								
							<m:div title="#{msg['label.callscript.forDsnd']}"	styleClass="sort_desc" />

							<f:attribute name="#{msg['id.callscript.sortColumn']}"
								value="includeval" />
							<f:attribute name="#{msg['id.callscript.sortOrder']}"
								value="desc" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>
		<h:outputLabel id="checkboxColumnLabelCR16" for="inccatcheckbox" styleClass="hide" value="#{msg['label.callscript.includeCS']}" ></h:outputLabel>
		<h:selectBooleanCheckbox id="inccatcheckbox"			disabled="#{CallScriptDataBean.callScriptVO.inactive}"			onclick="assctnDeassctnWarn('#{csCat.assignedCallScript}','#{csCat.categoryVO.categoryDesc}','cat',this			,'#{rowindexCT}');"            onmousedown="javascript:flagWarn=false;focusThis('csrpt');"			value="#{csCat.includecallScript}" ></h:selectBooleanCheckbox>
          <%-- 
              DEFECT ID : ESPRD00299559
              onclick="javascript:warnUser(this);"
          --%>
     
	</h:column>

	<h:column id="catcol">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD111" columns="2">
				<h:outputLabel id="PRGCMGTOLL478" for="ctgry"					value="#{msg['label.callscript.category']}">
				</h:outputLabel>
				<h:panelGroup id="ctgry">
					<t:div styleClass="alignLeft">
						<t:commandLink id="catDescScriptCommandLink1"							actionListener="#{CallScriptControllerBean.sortCategory}"							rendered="#{CallScriptDataBean.imageRender !='catDescScriptCommandLink1'}"							onmousedown="javascript:flagWarn=false;">

							<%--<h:graphicImage alt="#{msg['label.callscript.forAscd']}"
							styleClass="sort_asc" width="8" url="/images/x.gif"/> --%>
							
							<m:div title="#{msg['label.callscript.forAscd']}" styleClass="sort_asc" />

							<f:attribute name="#{msg['id.callscript.sortColumn']}"
								value="catval" />
							<f:attribute name="#{msg['id.callscript.sortOrder']}" value="asc" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="catDescScriptCommandLink2"							actionListener="#{CallScriptControllerBean.sortCategory}"							rendered="#{CallScriptDataBean.imageRender !='catDescScriptCommandLink2'}"							onmousedown="javascript:flagWarn=false;">

							<%--<h:graphicImage alt="#{msg['label.callscript.forDsnd']}"
								styleClass="sort_desc" width="8" url="/images/x.gif"/> --%>
								
							<m:div title="#{msg['label.callscript.forDsnd']}"	styleClass="sort_desc" />

							<f:attribute name="#{msg['id.callscript.sortColumn']}"
								value="catval" />
							<f:attribute name="#{msg['id.callscript.sortOrder']}"
								value="desc" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>

		<h:outputText id="callidVal" value="#{csCat.categoryVO.categoryDesc}" />
	</h:column>

	<h:column id="asscdincat">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD112" columns="2">
				<h:outputLabel id="PRGCMGTOLL479" for="asscs"					value="#{msg['label.callscript.assignedCS']}">
				</h:outputLabel>
				<h:panelGroup id="asscs">
					<t:div styleClass="alignLeft">
						<t:commandLink id="assigCSCommandLink1"							actionListener="#{CallScriptControllerBean.sortCategory}"							rendered="#{CallScriptDataBean.imageRender !='assigCSCommandLink1'}"							onmousedown="javascript:flagWarn=false;">

							<%--<h:graphicImage alt="#{msg['label.callscript.forAscd']}"
							styleClass="sort_asc" width="8" url="/images/x.gif"/>  --%>
							
							<m:div title="#{msg['label.callscript.forAscd']}" styleClass="sort_asc" />

							<f:attribute name="#{msg['id.callscript.sortColumn']}"
								value="asscsval" />
							<f:attribute name="#{msg['id.callscript.sortOrder']}" value="asc" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="assigCSCommandLink2"							actionListener="#{CallScriptControllerBean.sortCategory}"							rendered="#{CallScriptDataBean.imageRender !='assigCSCommandLink2'}"							onmousedown="javascript:flagWarn=false;">

							<%--<h:graphicImage alt="#{msg['label.callscript.forDsnd']}"
								styleClass="sort_desc" width="8" url="/images/x.gif"/> --%>
								
							<m:div title="#{msg['label.callscript.forDsnd']}"	styleClass="sort_desc" />

							<f:attribute name="#{msg['id.callscript.sortColumn']}"
								value="asscsval" />
							<f:attribute name="#{msg['id.callscript.sortOrder']}"
								value="desc" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>

		<h:outputText id="descval" value="#{csCat.assignedCallScript}" />
	</h:column>
	<%-- Snippet for  displaying  No Data --%>

	<f:facet name="footer">
		
			<m:div id="nodata" rendered="#{CallScriptDataBean.noData}"
				align="center">
				<h:outputText id="PRGCMGTOT1016" value="#{cont['label.ref.noData']}"></h:outputText>
			</m:div>
		
	</f:facet>

</t:dataTable>
<t:dataScroller id="PROVIDERMDS20120731164811421" pageCountVar="pageCount" pageIndexVar="pageIndex"
	paginator="true" paginatorActiveColumnStyle='font-weight:bold;'
	paginatorMaxPages="3" immediate="false" for="categoryDT"
	firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
	rowsCountVar="rowsCount" 
	styleClass="dataScroller">
	<f:facet name="previous">
		<h:outputText id="PRGCMGTOT1017" styleClass="underline"			value="#{cont['label.ref.lt']}" rendered="#{pageIndex > 1}"></h:outputText>
	</f:facet>
	<f:facet name="next">
		<h:outputText id="PRGCMGTOT1018" styleClass="underline"			value="#{cont['label.ref.gt']}" rendered="#{pageIndex < pageCount}"></h:outputText>
	</f:facet>
	<h:outputText id="PRGCMGTOT1019"		value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"		styleClass="dataScrollerText" rendered="#{rowsCount>0}">
	</h:outputText>
</t:dataScroller>

