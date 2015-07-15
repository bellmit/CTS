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


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>

<%--Added RowIndexVar below to data table for ESPRD00795431 to get the row index --%>
<t:dataTable cellspacing="0" styleClass="dataTable" 	columnClasses="columnClass" headerClass="headerClass"	footerClass="footerClass" rowClasses="row_alt,row" id="subjectDT"	width="100%" var="sublist" value="#{CallScriptDataBean.subjectList}"	rows="10" onmouseover="return tableMouseOver(this, event);"	onmouseout="return tableMouseOut(this, event);" rowIndexVar="rowindexSUB">

	<h:column id="incCallScptinsub">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD116" columns="2">
				<h:outputLabel id="PRGCMGTOLL483" for="incsub"					value="#{msg['label.callscript.includeCS']}">
				</h:outputLabel>
				<h:panelGroup id="incsub">
					<t:div styleClass="alignLeft">
						<t:commandLink id="incCallScriptCommandLink1"							actionListener="#{CallScriptControllerBean.sortSubjects}"							rendered="#{CallScriptDataBean.imageRender !='incCallScriptCommandLink1'}"							onmousedown="javascript:flagWarn=false;">

							<%--<h:graphicImage alt="#{msg['label.callscript.forAscd']}"
							styleClass="sort_asc" width="8" url="/images/x.gif"/> --%>
							
							<m:div title="#{msg['label.callscript.forAscd']}" styleClass="sort_asc" />

							<f:attribute name="columnName" value="includeval" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="incCallScriptCommandLink2"							actionListener="#{CallScriptControllerBean.sortSubjects}"							rendered="#{CallScriptDataBean.imageRender !='incCallScriptCommandLink2'}"							onmousedown="javascript:flagWarn=false;">

							<%--<h:graphicImage alt="#{msg['label.callscript.forDsnd']}"
								styleClass="sort_desc" width="8" url="/images/x.gif"/> --%>
							
							<m:div title="#{msg['label.callscript.forDsnd']}"	styleClass="sort_desc" />

							<f:attribute name="columnName" value="includeval" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>
		<h:outputLabel id="checkboxColumnLabelCR17" for="incsubcheckbox" styleClass="hide" value="#{msg['label.callscript.includeCS']}" ></h:outputLabel>
		<h:selectBooleanCheckbox id="incsubcheckbox"			disabled="#{CallScriptDataBean.callScriptVO.inactive}"			onclick="assctnDeassctnWarn('#{sublist.assignedCallScript}','#{sublist.subjectDescription}','sub',this			,'#{rowindexSUB}');"			onmousedown="javascript:flagWarn=false;focusThis('csrpt');"			value="#{sublist.includecallScript}"></h:selectBooleanCheckbox>
		<%-- 
            DEFECT ID : ESPRD00299559
            onclick="javascript:warnUser(this);"
        --%>

	</h:column>

	<h:column id="subcol">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD117" columns="2">
				<h:outputLabel id="PRGCMGTOLL484" for="subdesc"					value="#{msg['label.callscript.subject']}">
				</h:outputLabel>
				<h:panelGroup id="subdesc">
					<t:div styleClass="alignLeft">
						<t:commandLink id="subDescCommandLink1"							actionListener="#{CallScriptControllerBean.sortSubjects}"							rendered="#{CallScriptDataBean.imageRender !='subDescCommandLink1'}"							onmousedown="javascript:flagWarn=false;">

							<%--<h:graphicImage alt="#{msg['label.callscript.forAscd']}"
							styleClass="sort_asc" width="8" url="/images/x.gif"/> --%>
							
							<m:div title="#{msg['label.callscript.forAscd']}" styleClass="sort_asc" />

							<f:attribute name="columnName" value="subval" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="subDescCommandLink2"							actionListener="#{CallScriptControllerBean.sortSubjects}"							rendered="#{CallScriptDataBean.imageRender !='subDescCommandLink2'}"						onmousedown="javascript:flagWarn=false;">

							<%--<h:graphicImage alt="#{msg['label.callscript.forDsnd']}"
								styleClass="sort_desc" width="8" url="/images/x.gif"/> --%>
							
							<m:div title="#{msg['label.callscript.forDsnd']}"	styleClass="sort_desc" />

							<f:attribute name="columnName" value="subval" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>

		<h:outputText id="callidVal" value="#{sublist.subjectDescription}" />
	</h:column>

	<h:column id="asscdinsub">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD118" columns="2">
				<h:outputLabel id="PRGCMGTOLL485" for="assigSub"					value="#{msg['label.callscript.assignedCS']}">
				</h:outputLabel>
				<h:panelGroup id="assigSub">
					<t:div styleClass="alignLeft">
						<t:commandLink id="assignCSCommandLink1"							actionListener="#{CallScriptControllerBean.sortSubjects}"							rendered="#{CallScriptDataBean.imageRender !='assignCSCommandLink1'}"							onmousedown="javascript:flagWarn=false;">

							<%--<h:graphicImage alt="#{msg['label.callscript.forAscd']}"
							styleClass="sort_asc" width="8" url="/images/x.gif"/> --%>
							
							<m:div title="#{msg['label.callscript.forAscd']}" styleClass="sort_asc" />

							<f:attribute name="columnName" value="asscsval" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="assignCSCommandLink2"							actionListener="#{CallScriptControllerBean.sortSubjects}"							rendered="#{CallScriptDataBean.imageRender !='assignCSCommandLink2'}"						    onmousedown="javascript:flagWarn=false;">

							<%--<h:graphicImage alt="#{msg['label.callscript.forDsnd']}"
								styleClass="sort_desc" width="8" url="/images/x.gif"/>--%>
							
							<m:div title="#{msg['label.callscript.forDsnd']}"	styleClass="sort_desc" />

							<f:attribute name="columnName" value="asscsval" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>

		<h:outputText id="descval" value="#{sublist.assignedCallScript}" />
	</h:column>
	<%-- Snippet for  displaying  No Data --%>

	<f:facet name="footer">
		
			<m:div id="nodata" rendered="#{CallScriptDataBean.noData}"
				align="center">
				<h:outputText id="PRGCMGTOT1024" value="#{cont['label.ref.noData']}"></h:outputText>
			</m:div>
		
	</f:facet>

</t:dataTable>
<t:dataScroller id="PROVIDERMDS20120731164811423" pageCountVar="pageCount" pageIndexVar="pageIndex"
	paginator="true" paginatorActiveColumnStyle='font-weight:bold;'
	paginatorMaxPages="3" immediate="false" for="subjectDT"
	firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
	rowsCountVar="rowsCount"
	styleClass="dataScroller">
	<f:facet name="previous">
		<h:outputText id="PRGCMGTOT1025" styleClass="underline"			value="#{cont['label.ref.lt']}" rendered="#{pageIndex > 1}"></h:outputText>
	</f:facet>
	<f:facet name="next">
		<h:outputText id="PRGCMGTOT1026" styleClass="underline"			value="#{cont['label.ref.gt']}" rendered="#{pageIndex < pageCount}"></h:outputText>
	</f:facet>
	<h:outputText id="PRGCMGTOT1027"		value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"		styleClass="dataScrollerText" rendered="#{rowsCount>0}">
	</h:outputText>
</t:dataScroller>

