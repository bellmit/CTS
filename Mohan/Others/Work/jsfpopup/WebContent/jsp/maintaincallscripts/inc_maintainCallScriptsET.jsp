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
	<t:dataTable cellspacing="0" styleClass="dataTable" 	columnClasses="columnClass" headerClass="headerClass"	footerClass="footerClass" rowClasses="row_alt,row" id="entitytyDT"	width="100%" var="entList" value="#{CallScriptDataBean.entityList}"	rows="10" onmouseover="return tableMouseOver(this, event);"	onmouseout="return tableMouseOut(this, event);" rowIndexVar="rowindexET">



	<h:column id="incCallScpt">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD113" columns="2" >
				<h:outputLabel id="PRGCMGTOLL480" for="incCSpt" value="#{msg['label.callscript.includeCS']}">
				</h:outputLabel>
				<h:panelGroup id="incCSpt">
					<t:div styleClass="alignLeft">
						<t:commandLink id="incCallScriptCommandLink1"							actionListener="#{CallScriptControllerBean.sortEntityType}"							rendered="#{CallScriptDataBean.imageRender !='incCallScriptCommandLink1'}"							onmousedown="javascript:flagWarn=false;" >
							
							<%--<h:graphicImage alt="#{msg['label.callscript.forAscd']}"
							styleClass="sort_asc" width="8" url="/images/x.gif"/>  --%>
							
							<m:div title="#{msg['label.callscript.forAscd']}" styleClass="sort_asc" />

							<f:attribute name="columnName" value="includeval" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="incCallScriptCommandLink2"							actionListener="#{CallScriptControllerBean.sortEntityType}"							rendered="#{CallScriptDataBean.imageRender !='incCallScriptCommandLink2'}"							onmousedown="javascript:flagWarn=false;">
							
							<%--<h:graphicImage alt="#{msg['label.callscript.forDsnd']}"
								styleClass="sort_desc" width="8" url="/images/x.gif"/>--%>
		
							<m:div title="#{msg['label.callscript.forDsnd']}"	styleClass="sort_desc" />
		
							
							<f:attribute name="columnName" value="includeval" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>
		<h:outputLabel id="checkboxColumnLabelCR17" for="entitycheckbox" styleClass="hide" value="#{msg['label.callscript.includeCS']}" ></h:outputLabel>
		<h:selectBooleanCheckbox id="entitycheckbox"  			disabled="#{CallScriptDataBean.callScriptVO.inactive}"			onclick="assctnDeassctnWarn('#{entList.assignedCallScript}','#{entList.entityDescription}','entTyp',this			,'#{rowindexET}');"            onmousedown="javascript:flagWarn=false;focusThis('csrpt');"			value="#{entList.includecallScript}"></h:selectBooleanCheckbox>


	</h:column>

	<h:column id="entDesc">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD114" columns="2">
				<h:outputLabel id="PRGCMGTOLL481" for="eD" value="#{cont['label.ref.description']}">
				</h:outputLabel>
				<h:panelGroup id="eD">
					<t:div styleClass="alignLeft">
						<t:commandLink id="enityDescCommandLink1"							actionListener="#{CallScriptControllerBean.sortEntityType}"							rendered="#{CallScriptDataBean.imageRender !='enityDescCommandLink1'}"							onmousedown="javascript:flagWarn=false;">

							<%--<h:graphicImage alt="#{msg['label.callscript.forAscd']}"
							styleClass="sort_asc" width="8" url="/images/x.gif"/> --%>
							
							<m:div title="#{msg['label.callscript.forAscd']}" styleClass="sort_asc" />

							<f:attribute name="columnName" value="entityDescval" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="enityDescCommandLink2"							actionListener="#{CallScriptControllerBean.sortEntityType}"							rendered="#{CallScriptDataBean.imageRender !='enityDescCommandLink2'}"							onmousedown="javascript:flagWarn=false;">

							<%--<h:graphicImage alt="#{msg['label.callscript.forDsnd']}"
								styleClass="sort_desc" width="8" url="/images/x.gif"/> --%>
									
							<m:div title="#{msg['label.callscript.forDsnd']}"	styleClass="sort_desc" />	

							<f:attribute name="columnName" value="entityDescval" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>

		<h:outputText id="callidVal" value="#{entList.entityDescription}" />
	</h:column>

	<h:column id="asscscol">
		<f:facet name="header">
			<h:panelGrid id="PRGCMGTPGD115" columns="2">
				<h:outputLabel id="PRGCMGTOLL482" for="assgCSEnt" value="#{msg['label.callscript.assignedCS']}">
				</h:outputLabel>
				<h:panelGroup id="assgCSEnt">
					<t:div styleClass="alignLeft">
						<t:commandLink id="assigCSCommandLink1"							actionListener="#{CallScriptControllerBean.sortEntityType}"							rendered="#{CallScriptDataBean.imageRender !='assigCSCommandLink1'}"							onmousedown="javascript:flagWarn=false;">

							<%--<h:graphicImage alt="#{msg['label.callscript.forAscd']}"
							styleClass="sort_asc" width="8" url="/images/x.gif"/> --%>
							
							<m:div title="#{msg['label.callscript.forAscd']}" styleClass="sort_asc" />

							<f:attribute name="columnName" value="asscsval" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</t:div>
					<t:div styleClass="alignLeft">
						<t:commandLink id="assigCSCommandLink2"							actionListener="#{CallScriptControllerBean.sortEntityType}"							rendered="#{CallScriptDataBean.imageRender !='assigCSCommandLink2'}"							onmousedown="javascript:flagWarn=false;">

							<%--<h:graphicImage alt="#{msg['label.callscript.forDsnd']}"
								styleClass="sort_desc" width="8" url="/images/x.gif"/> --%>
								
							<m:div title="#{msg['label.callscript.forDsnd']}"	styleClass="sort_desc" />

							<f:attribute name="columnName" value="asscsval" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</t:div>
				</h:panelGroup>
			</h:panelGrid>
		</f:facet>

		<h:outputText id="descval" value="#{entList.assignedCallScript}" />
	</h:column>
	<%-- Snippet for  displaying  No Data --%>

	<f:facet name="footer">
		
			<m:div id="nodata"  rendered="#{CallScriptDataBean.noData}"
				align="center">
				<h:outputText id="PRGCMGTOT1020" value="#{cont['label.ref.noData']}"></h:outputText>
			</m:div>
		
	</f:facet>

</t:dataTable>

<m:div id="sfsdf" onclick="pageScroll()" > 
<t:dataScroller id="PROVIDERMDS20120731164811422" pageCountVar="pageCount" pageIndexVar="pageIndex"
	paginator="true" paginatorActiveColumnStyle='font-weight:bold;'
	paginatorMaxPages="3" immediate="false" for="entitytyDT" 
	firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex" 
	rowsCountVar="rowsCount"
	styleClass="dataScroller">
	<f:facet name="previous">

		<h:outputText id="PRGCMGTOT1021" styleClass="underline"			value="#{cont['label.ref.lt']}" rendered="#{pageIndex > 1}"></h:outputText>

	</f:facet>
	<f:facet name="next">
		<h:outputText id="PRGCMGTOT1022" styleClass="underline"			value="#{cont['label.ref.gt']}" rendered="#{pageIndex < pageCount}"></h:outputText>
	</f:facet>
	<h:outputText id="PRGCMGTOT1023"		value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"		styleClass="dataScrollerText" rendered="#{rowsCount>0}">
	</h:outputText>
</t:dataScroller>
</m:div>
