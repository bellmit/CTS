<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@ taglib uri="http://www.acs-inc.com/auditLog" prefix="audit"%>

<portlet:defineObjects />
<%--Added for the defect ESPRD00853112 starts--%>
<m:div styleClass="msgBox"
	rendered="#{CaseTypeDataBean.showStepSuccessMsg}">
	<h:outputText id="PRGCMGTOT1127" value="#{ent['label-sw-success']}" />
</m:div>
<m:div styleClass="msgBox"
	rendered="#{CaseTypeDataBean.showStepDeleteMsg}">

	<h:outputText id="PRGCMGTOT108911"
		value="#{ent['err-sw-record-delete-success']}" />
</m:div>
<%--Defect ESPRD00853112 ends--%>
<m:table id="PROVIDERMMT20120731164811447" styleClass="tableBar" width="100%">
	<m:tr>
		<m:td styleClass="tableAction">
			<!-- ankush -->
			<h:commandButton id="PRGCMGTCB41" styleClass="formBtn"
				onclick="javascript:flagWarn=false;javascript:addeditrow('maintaincasetype');"
				onmousedown="javascript:focusPage('maintainCaseTypeAddCasesteps');"
				disabled="#{CaseTypeDataBean.disableAddcaseStep}"
				value="#{msg['label.casetype.casesteps.addCaseStep']}"
				action="#{CaseTypeControllerBean.showAddCaseStep}">
			</h:commandButton>

		</m:td>
	</m:tr>
</m:table>
<t:dataTable cellspacing="0" styleClass="dataTable"
	columnClasses="columnClass" headerClass="headerClass"
	footerClass="footerClass" rowClasses="row_alt,row"
	id="casestepDataTable" width="100%"
	onmouseover="return tableMouseOver(this, event);"
	onmouseout="return tableMouseOut(this, event);"
	value="#{CaseTypeDataBean.caseTypeStepsList}" var="casestep" rows="10"
	rendered="#{CaseTypeDataBean.noCaseStepsData}"
	first="#{CaseTypeDataBean.startIndexForCaseTypeSteps}"
	rowOnClick="javascript:flagWarn=false;firstChild.lastChild.click();"
	rowIndexVar="index">
	<t:column id="includecol">
		<f:facet name="header">
			<t:panelGrid id="CMGTTOMPGD24" columns="2">
				<h:outputLabel id="PRGCMGTOLL498" for="includeCommandLink1"
					value="#{msg['label.casetype.casesteps.include']}">
				</h:outputLabel>
				<t:panelGroup id="CMGTTOMPGP24">
					<m:div>
						<t:commandLink id="includeCommandLink1"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCaseSteps}"
							rendered="#{CaseTypeDataBean.imageRender != 'includeCommandLink1'}">
							<h:graphicImage id="PROVIDERGI20120731164811448" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								styleClass="sort_asc" width="8" url="/images/x.gif" />
							<f:attribute name="columnName" value="includeValue" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</m:div>
					<m:div>
						<t:commandLink id="includeCommandLink2"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCaseSteps}"
							rendered="#{CaseTypeDataBean.imageRender != 'includeCommandLink2'}">
							<h:graphicImage id="PROVIDERGI20120731164811449" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								styleClass="sort_desc" width="8" url="/images/x.gif" />
							<f:attribute name="columnName" value="includeValue" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</m:div>
				</t:panelGroup>
			</t:panelGrid>
		</f:facet>
		<h:graphicImage id="PROVIDERGI20120731164811450" alt="#{cont['label.ref.yes']}" title="#{cont['label.ref.yes']}"
			styleClass="ind_check_yes" width="15"
			rendered="#{casestep.imgInclude}" url="/images/x.gif"></h:graphicImage>
		<t:commandLink id="editcasestep"
			action="#{CaseTypeControllerBean.getCaseStepDetails}">
			<f:param name="rowIndex" value="#{index}"></f:param>
		</t:commandLink>
	</t:column>
	<t:column id="descriptioncol">
		<f:facet name="header">
			<t:panelGrid id="CMGTTOMPGD25" columns="2">
				<h:outputLabel id="PRGCMGTOLL499" for="descriptCommandLink1"
					value="#{msg['label.casetype.casesteps.description']}">
				</h:outputLabel>
				<t:panelGroup id="CMGTTOMPGP25">
					<m:div>
						<t:commandLink id="descriptCommandLink1"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCaseSteps}"
							rendered="#{CaseTypeDataBean.imageRender != 'descriptCommandLink1'}">
							<h:graphicImage id="PROVIDERGI20120731164811451" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								styleClass="sort_asc" width="8" url="/images/x.gif" />
							<f:attribute name="columnName" value="cs_descriptionValue" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</m:div>
					<m:div>
						<t:commandLink id="descriptCommandLink2"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCaseSteps}"
							rendered="#{CaseTypeDataBean.imageRender != 'descriptCommandLink2'}">
							<h:graphicImage id="PROVIDERGI20120731164811452" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								styleClass="sort_desc" width="8" url="/images/x.gif" />
							<f:attribute name="columnName" value="cs_descriptionValue" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</m:div>
				</t:panelGroup>
			</t:panelGrid>
		</f:facet>
		<h:outputText id="descriptValue" value="#{casestep.descValue}" />
	</t:column>
	<t:column id="ordercol">
		<f:facet name="header">
			<t:panelGrid id="CMGTTOMPGD26" columns="2">
				<h:outputLabel id="PRGCMGTOLL500" for="orderCommandLink1"
					value="#{msg['label.casetype.casesteps.order']}">
				</h:outputLabel>
				<t:panelGroup id="CMGTTOMPGP26">
					<m:div>
						<t:commandLink id="orderCommandLink1"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCaseSteps}"
							rendered="#{CaseTypeDataBean.imageRender != 'orderCommandLink1'}">
							<h:graphicImage id="PROVIDERGI20120731164811453" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								styleClass="sort_asc" width="8" url="/images/x.gif" />
							<f:attribute name="columnName" value="cs_casesteporder" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</m:div>
					<m:div>
						<t:commandLink id="orderCommandLink2"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCaseSteps}"
							rendered="#{CaseTypeDataBean.imageRender != 'orderCommandLink2'}">
							<h:graphicImage id="PROVIDERGI20120731164811454" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								styleClass="sort_desc" width="8" url="/images/x.gif" />
							<f:attribute name="columnName" value="cs_casesteporder" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</m:div>
				</t:panelGroup>
			</t:panelGrid>
		</f:facet>
		<h:outputText id="PRGCMGTOT1091" value="#{casestep.stepOrderNumDesc}" />
	</t:column>
	<t:column id="automaticcol">
		<f:facet name="header">
			<t:panelGrid id="CMGTTOMPGD27" columns="2">
				<h:outputLabel id="PRGCMGTOLL501" for="automaticCommandLink1"
					value="#{msg['label.casetype.casesteps.automaticRouteTo']}">
				</h:outputLabel>
				<t:panelGroup id="CMGTTOMPGP27">
					<m:div>
						<t:commandLink id="automaticCommandLink1"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCaseSteps}"
							rendered="#{CaseTypeDataBean.imageRender != 'automaticCommandLink1'}">
							<h:graphicImage id="PROVIDERGI20120731164811455" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								styleClass="sort_asc" width="8" url="/images/x.gif" />
							<f:attribute name="columnName" value="cs_automaticRouteTo" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</m:div>
					<m:div>
						<t:commandLink id="automaticCommandLink2"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCaseSteps}"
							rendered="#{CaseTypeDataBean.imageRender != 'automaticCommandLink2'}">
							<h:graphicImage id="PROVIDERGI20120731164811456" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								styleClass="sort_desc" width="8" url="/images/x.gif" />
							<f:attribute name="columnName" value="cs_automaticRouteTo" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</m:div>
				</t:panelGroup>
			</t:panelGrid>
		</f:facet>
		<%--		<h:outputText id="PRGCMGTOT1092" value="#{casestep.automaticRouteTo}" />--%>
		<h:outputText id="PRGCMGTOT1093"
			value="#{casestep.automaticRouteToDesc}" />
		<%--EOF ESPRD00527652_UC-PGM-CRM-48_28SEP2010 --%>

	</t:column>
	<t:column id="dayscompletecol">
		<f:facet name="header">
			<t:panelGrid id="CMGTTOMPGD28" columns="2">
				<h:outputLabel id="PRGCMGTOLL502" for="dayscompleteCommandLink1"
					value="#{msg['label.casetype.casesteps.daysToComplete']}">
				</h:outputLabel>
				<t:panelGroup id="CMGTTOMPGP28">
					<m:div>
						<t:commandLink id="dayscompleteCommandLink1"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCaseSteps}"
							rendered="#{CaseTypeDataBean.imageRender != 'dayscompleteCommandLink1'}">
							<h:graphicImage id="PROVIDERGI20120731164811457" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								styleClass="sort_asc" width="8" url="/images/x.gif" />
							<f:attribute name="columnName" value="cs_daysToComplete" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</m:div>
					<m:div>
						<t:commandLink id="dayscompleteCommandLink2"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCaseSteps}"
							rendered="#{CaseTypeDataBean.imageRender != 'dayscompleteCommandLink2'}">
							<h:graphicImage id="PROVIDERGI20120731164811458" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								styleClass="sort_desc" width="8" url="/images/x.gif" />
							<f:attribute name="columnName" value="cs_daysToComplete" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</m:div>
				</t:panelGroup>
			</t:panelGrid>
		</f:facet>

		<h:outputText id="PRGCMGTOT1094" value="#{casestep.dfltDaysToCmplCnt}" />

	</t:column>
	<t:column id="completebasedcol">
		<f:facet name="header">
			<t:panelGrid id="CMGTTOMPGD29" columns="2">
				<h:outputLabel id="PRGCMGTOLL503" for="completebasedCommandLink1"
					value="#{msg['label.casetype.casesteps.completionBasedOn']}">
				</h:outputLabel>
				<t:panelGroup id="CMGTTOMPGP29">
					<m:div>
						<t:commandLink id="completebasedCommandLink1"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCaseSteps}"
							rendered="#{CaseTypeDataBean.imageRender != 'completebasedCommandLink1'}">
							<h:graphicImage id="PROVIDERGI20120731164811459" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								styleClass="sort_asc" width="8" url="/images/x.gif" />
							<f:attribute name="columnName" value="cs_completeBasedOn" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</m:div>
					<m:div>
						<t:commandLink id="completebasedCommandLink2"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCaseSteps}"
							rendered="#{CaseTypeDataBean.imageRender != 'completebasedCommandLink2'}">
							<h:graphicImage id="PROVIDERGI20120731164811460" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								styleClass="sort_desc" width="8" url="/images/x.gif" />
							<f:attribute name="columnName" value="cs_completeBasedOn" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</m:div>
				</t:panelGroup>
			</t:panelGrid>
		</f:facet>
		<h:outputText id="PRGCMGTOT1095"
			value="#{casestep.dfltCmpltnBasedOnDesc}" />
		<%--<h:outputText id="PRGCMGTOT1096" value="#{casestep.dfltCmpltnBasedOnColName}" />--%>
	</t:column>
	<t:column id="notifyalertcol">
		<f:facet name="header">
			<t:panelGrid id="CMGTTOMPGD30" columns="2">
				<h:outputLabel id="PRGCMGTOLL504" for="notifyalertCommandLink1"
					value="#{msg['label.casetype.casesteps.notifyViaAlert']}">
				</h:outputLabel>
				<t:panelGroup id="CMGTTOMPGP30">
					<m:div>
						<t:commandLink id="notifyalertCommandLink1"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCaseSteps}"
							rendered="#{CaseTypeDataBean.imageRender != 'notifyalertCommandLink1'}">
							<h:graphicImage id="PROVIDERGI20120731164811461" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								styleClass="sort_asc" width="8" url="/images/x.gif" />
							<f:attribute name="columnName" value="cs_notifyalert" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</m:div>
					<m:div>
						<t:commandLink id="notifyalertCommandLink2"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							style="text-decoration: none;"
							actionListener="#{CaseTypeControllerBean.getAllSortedCaseSteps}"
							rendered="#{CaseTypeDataBean.imageRender != 'notifyalertCommandLink2'}">
							<h:graphicImage id="PROVIDERGI20120731164811462" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								styleClass="sort_desc" width="8" url="/images/x.gif" />
							<f:attribute name="columnName" value="cs_notifyalert" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</m:div>
				</t:panelGroup>
			</t:panelGrid>
		</f:facet>
		<%-- ESPRD00527856_UC-PGM-CRM-48_01oct2010
		<h:outputText id="PRGCMGTOT1097" value="#{casestep.dfltNotfyAlertUserId}" />
		<h:outputText id="PRGCMGTOT1098" value=" - "			rendered="#{casestep.dfltNotfyAlertUserName != null}" />--%>
		<h:outputText id="PRGCMGTOT1099"
			value="#{casestep.dfltNotfyAlertUserName}" />
	</t:column>
	<t:column id="alertbasedcol">
		<f:facet name="header">
			<t:panelGrid id="CMGTTOMPGD31" columns="2">
				<h:outputLabel id="PRGCMGTOLL505" for="alertbasedCommandLink1"
					value="#{msg['label.casetype.casesteps.alertBasedOn']}">
				</h:outputLabel>
				<t:panelGroup id="CMGTTOMPGP31">
					<m:div>
						<t:commandLink id="alertbasedCommandLink1"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							style="text-decoration: none;"
							actionListener="#{CaseTypeControllerBean.getAllSortedCaseSteps}"
							rendered="#{CaseTypeDataBean.imageRender != 'alertbasedCommandLink1'}">
							<h:graphicImage id="PROVIDERGI20120731164811463" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								styleClass="sort_asc" width="8" url="/images/x.gif" />
							<f:attribute name="columnName" value="cs_alertBased" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</m:div>
					<m:div>
						<t:commandLink id="alertbasedCommandLink2"
							style="text-decoration: none;"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCaseSteps}"
							rendered="#{CaseTypeDataBean.imageRender != 'alertbasedCommandLink2'}">
							<h:graphicImage id="PROVIDERGI20120731164811464" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								styleClass="sort_desc" width="8" url="/images/x.gif" />
							<f:attribute name="columnName" value="cs_alertBased" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</m:div>
				</t:panelGroup>
			</t:panelGrid>
		</f:facet>
		<%--ESPRD00527655_UC-PGM-CRM-48_29SEP2010	<h:outputText id="PRGCMGTOT1100" value="#{casestep.dfltAlertBasedOnColName}" />--%>
		<h:outputText id="PRGCMGTOT1101"
			value="#{casestep.dfltAlertBasedOnDesc}" />
		<%--EOF ESPRD00527655_UC-PGM-CRM-48_29SEP2010 --%>

	</t:column>

	<t:column id="defaultsendcol">
		<f:facet name="header">
			<t:panelGrid id="CMGTTOMPGD32" columns="2">
				<h:outputLabel id="PRGCMGTOLL506" for="defaultsendCommandLink1"
					value="#{msg['label.casetype.casesteps.sendAlert']}">
				</h:outputLabel>
				<t:panelGroup id="CMGTTOMPGP32">
					<m:div>
						<t:commandLink id="defaultsendCommandLink1"
							style="text-decoration: none;"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCaseSteps}"
							rendered="#{CaseTypeDataBean.imageRender != 'defaultsendCommandLink1'}">
							<h:graphicImage id="PROVIDERGI20120731164811465" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								styleClass="sort_asc" width="8" url="/images/x.gif" />
							<f:attribute name="columnName" value="cs_dfltSendAlertBased" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</m:div>
					<m:div>
						<t:commandLink id="defaultsendCommandLink2"
							style="text-decoration: none;"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCaseSteps}"
							rendered="#{CaseTypeDataBean.imageRender != 'defaultsendCommandLink2'}">
							<h:graphicImage id="PROVIDERGI20120731164811466" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								styleClass="sort_desc" width="8" url="/images/x.gif" />
							<f:attribute name="columnName" value="cs_dfltSendAlertBased" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</m:div>
				</t:panelGroup>
			</t:panelGrid>
		</f:facet>

		<h:outputText id="PRGCMGTOT1102"
			value="#{casestep.dfltSendAlertDaysBeforeOrAfter}" />
	</t:column>
	<t:column id="templatecol">
		<f:facet name="header">
			<t:panelGrid id="CMGTTOMPGD33" columns="2">
				<h:outputLabel id="PRGCMGTOLL507" for="templateCommandLink1"
					value="#{msg['label.casetype.casesteps.defaultTemplate']}">
				</h:outputLabel>
				<t:panelGroup id="CMGTTOMPGP33">
					<m:div>
						<t:commandLink id="templateCommandLink1"
							style="text-decoration: none;"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCaseSteps}"
							rendered="#{CaseTypeDataBean.imageRender != 'templateCommandLink1'}">
							<h:graphicImage id="PROVIDERGI20120731164811467" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								styleClass="sort_asc" width="8" url="/images/x.gif" />
							<f:attribute name="columnName" value="cs_dfltTemlateAlert" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</m:div>
					<m:div>
						<t:commandLink id="templateCommandLink2"
							style="text-decoration: none;"
							onmousedown="javascript:flagWarn=false;focusPage('maintainCaseTypeSort');"
							actionListener="#{CaseTypeControllerBean.getAllSortedCaseSteps}"
							rendered="#{CaseTypeDataBean.imageRender != 'templateCommandLink2'}">
							<h:graphicImage id="PROVIDERGI20120731164811468" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								styleClass="sort_desc" width="8" url="/images/x.gif" />
							<f:attribute name="columnName" value="cs_dfltTemlateAlert" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</m:div>
				</t:panelGroup>
			</t:panelGrid>
		</f:facet>
		<h:outputText id="PRGCMGTOT1103"
			value="#{casestep.dfltCotsLtrTmpltKeyData}" />
	</t:column>
</t:dataTable>
<%-- If there is No data in table --%>
<t:dataTable id="CMGTTOMDT1" cellspacing="0" styleClass="dataTable"
	columnClasses="columnClass" headerClass="headerClass" var="nocasesteps"
	footerClass="footerClass" rowClasses="row_alt,row" width="100%"
	rendered="#{!CaseTypeDataBean.noCaseStepsData}">

	<t:column id="CMGTTOMCS12">
		<f:facet name="header">
			<h:outputText id="PRGCMGTOT1104"
				value="#{msg['label.casetype.casesteps.include']}">
			</h:outputText>
		</f:facet>
	</t:column>
	<t:column id="CMGTTOMCS13">
		<f:facet name="header">
			<h:outputText id="PRGCMGTOT1105"
				value="#{msg['label.casetype.casesteps.description']}">
			</h:outputText>
		</f:facet>
	</t:column>
	<t:column id="CMGTTOMCS14">
		<f:facet name="header">
			<h:outputText id="PRGCMGTOT1106"
				value="#{msg['label.casetype.casesteps.order']}">
			</h:outputText>
		</f:facet>
	</t:column>
	<t:column id="CMGTTOMCS15">
		<f:facet name="header">
			<h:outputText id="PRGCMGTOT1107"
				value="#{msg['label.casetype.casesteps.automaticRouteTo']}">
			</h:outputText>
		</f:facet>
	</t:column>
	<t:column id="CMGTTOMCS16">
		<f:facet name="header">
			<h:outputText id="PRGCMGTOT1108"
				value="#{msg['label.casetype.casesteps.daysToComplete']}">
			</h:outputText>
		</f:facet>
	</t:column>
	<t:column id="CMGTTOMCS17">
		<f:facet name="header">
			<h:outputText id="PRGCMGTOT1109"
				value="#{msg['label.casetype.casesteps.completionBasedOn']}">
			</h:outputText>
		</f:facet>
	</t:column>
	<t:column id="CMGTTOMCS18">
		<f:facet name="header">
			<h:outputText id="PRGCMGTOT1110"
				value="#{msg['label.casetype.casesteps.notifyViaAlert']}">
			</h:outputText>
		</f:facet>
	</t:column>
	<t:column id="CMGTTOMCS19">
		<f:facet name="header">
			<h:outputText id="PRGCMGTOT1111"
				value="#{msg['label.casetype.casesteps.alertBasedOn']}">
			</h:outputText>
		</f:facet>
	</t:column>
	<t:column id="CMGTTOMCS20">
		<f:facet name="header">
			<h:outputText id="PRGCMGTOT1112"
				value="#{msg['label.casetype.casesteps.sendAlert']}">
			</h:outputText>
		</f:facet>
	</t:column>
	<t:column id="CMGTTOMCS21">
		<f:facet name="header">
			<h:outputText id="PRGCMGTOT1113"
				value="#{msg['label.casetype.casesteps.defaultTemplate']}">
			</h:outputText>
		</f:facet>
	</t:column>
</t:dataTable>
<m:table id="PROVIDERMMT20120731164811469" styleClass="dataTable" cellspacing="0" width="100%"
	rendered="#{!CaseTypeDataBean.noCaseStepsData}">
	<m:tr>
		<m:td align="center">
			<h:outputText id="PRGCMGTOT1114"
				value="#{msg['label.casetype.table.notabledata']}" />
		</m:td>
	</m:tr>
</m:table>
<%--  Data Scroller--%>
<m:div onclick="javascript:flagWarn=false;"
	onmousedown="javascript:flagWarn=false;">
	<t:dataScroller id="CMGTTOMDS39" pageCountVar="pageCount"
		pageIndexVar="pageIndex" onclick="javascript:flagWarn=false;"
		paginator="true" paginatorActiveColumnStyle='font-weight:bold;'
		paginatorMaxPages="3" immediate="false" for="casestepDataTable"
		firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
		rowsCountVar="rowsCount" styleClass="dataScroller">
		<f:facet name="previous">
			<h:outputText id="PRGCMGTOT1115" styleClass="underline"
				value="#{cont['label.ref.lt']}" rendered="#{pageIndex > 1}"></h:outputText>
		</f:facet>
		<f:facet name="next">
			<h:outputText id="PRGCMGTOT1116" styleClass="underline"
				value="#{cont['label.ref.gt']}" rendered="#{pageIndex < pageCount}"></h:outputText>
		</f:facet>
		<h:outputText id="PRGCMGTOT1117"
			value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
			styleClass="dataScrollerText" rendered="#{rowsCount>0}">
		</h:outputText>
	</t:dataScroller>
</m:div>
<m:br />
<m:br />

<m:div id="edtcs1"
	rendered="#{CaseTypeDataBean.showEditCaseSteps || CaseTypeDataBean.showAddCaseSteps}">
	<m:anchor name="maintainCaseTypeCasesteps" />
	<m:div styleClass="moreInfo">
		<%--Edit Casesteps Section--%>
		<m:div styleClass="moreInfoBar"
			rendered="#{CaseTypeDataBean.showEditCaseSteps}">
			<m:anchor name="maintainCaseTypeEditCasesteps" />
			<m:div styleClass="infoTitle">
				<h:outputText id="PRGCMGTOT1118"
					value="#{msg['label.casetype.casesteps.editCaseStep']}" />
			</m:div>
			<m:div styleClass="infoActions">
				<%-- srikanth--%>
				<t:commandLink id="PRGCMGTCL162" styleClass="strong"
					onmousedown="javascript:flagWarn=false;focusPage('addEditCaseTypes');javascript:addeditrow('maintaincasetype');"
					rendered="#{!CaseTypeDataBean.disableEditCaseStepSave}"
					action="#{CaseTypeControllerBean.saveEditCaseStep}">
					<h:outputText id="PRGCMGTOT1119" value="#{ent['label-sw-save']}" />
				</t:commandLink>
				<h:outputText id="PRGCMGTOT1120" value="#{ent['label-sw-save']}"
					rendered="#{CaseTypeDataBean.disableEditCaseStepSave}" />
				<%-- Endsrikanth --%>
				<h:outputText id="PRGCMGTOT1121"
					value="#{msg['label.casetype.pipe']}" />
				<t:commandLink id="PRGCMGTCL163"
					onmousedown="javascript:flagWarn=true;focusPage('addEditCaseTypes');"
					action="#{CaseTypeControllerBean.cancelEditCaseSteps}">
					<h:outputText id="PRGCMGTOT1122" value="#{ent['label-sw-cancel']}" />
				</t:commandLink>
			</m:div>
		</m:div>
		<%--  ADD CASE STEPS SECTION --%>
		<m:div styleClass="moreInfoBar"
			rendered="#{CaseTypeDataBean.showAddCaseSteps}">
			<m:anchor name="maintainCaseTypeAddCasesteps" />
			<m:div styleClass="infoTitle">
				<h:outputText id="PRGCMGTOT1123"
					value="#{msg['label.casetype.casesteps.addCaseStep']}" />
			</m:div>
			<m:div styleClass="infoActions">

				<t:commandLink id="PRGCMGTCL164" styleClass="strong"
					onmousedown="javascript:addeditrow('maintaincasetype');focusPage('addEditCaseTypes');"
					action="#{CaseTypeControllerBean.addCaseStep}">
					<h:outputText id="PRGCMGTOT1124" value="#{ent['label-sw-save']}"></h:outputText>
				</t:commandLink>
				<h:outputText id="PRGCMGTOT1125"
					value="#{msg['label.casetype.pipe']}" />
				<t:commandLink id="PRGCMGTCL165"
					onmousedown="javascript:flagWarn=true;focusPage('addEditCaseTypes');"
					action="#{CaseTypeControllerBean.cancelAddCaseSteps}">
					<h:outputText id="PRGCMGTOT1126" value="#{ent['label-sw-cancel']}" />
				</t:commandLink>
			</m:div>
		</m:div>
		<%-- Common to both Edit and Add blocks --%>
		<m:div styleClass="moreInfoContent"
			onmouseover="javascript:flagWarn=false;javascript:fileSavedFlag=false;">
			<m:div>
			</m:div>
			<%--Commented for the defect ESPRD00853112 starts and code moved to top of the JSP Page--%>
			<%--<m:div styleClass="msgBox"
				rendered="#{CaseTypeDataBean.showStepSuccessMsg}">
				<h:outputText id="PRGCMGTOT1127" value="#{ent['label-sw-success']}" />
			</m:div>--%>
			<%--Modified for defect ESPRDOO561174 Begin--%>
			<%--<m:div styleClass="msgBox"
				rendered="#{CaseTypeDataBean.showStepDeleteMsg}">

				<h:outputText id="PRGCMGTOT108911"
					value="#{ent['err-sw-record-delete-success']}" />
			</m:div>--%>
			<%--Defect ESPRD00853112 ends--%>
			<%--Modified for defect ESPRDOO561174 ends--%>
			<m:div rendered="#{CaseTypeDataBean.showDescOrderFlag}">
				<h:messages id="PRGCMGTMS12" showDetail="true" layout="table"
					showSummary="false" styleClass="errorMessage" />
			</m:div>
			<m:div rendered="#{CaseTypeDataBean.showStepActMsg}">
				<h:messages id="PRGCMGTMS13" showDetail="true" layout="table"
					showSummary="false" styleClass="errorMessage" />
			</m:div>
			<m:div styleClass="fullwidth">
				<m:table id="PROVIDERMMT20120731164811470" cellspacing="0" width="100%">
					<m:tr>
						<m:td>
							<m:table id="PROVIDERMMT20120731164811471" cellspacing="0" width="100%">
								<m:tr>
									<m:td>
										<m:div styleClass="padb">
											<h:outputLabel for="includradioval1" id="ctinclude" 
												value="#{msg['label.casetype.casesteps.include']}" />

											<%--disabled="#{CaseTypeDataBean.incudeflag}"--%>
											<h:selectOneRadio id="includradioval1"
												disabled="#{CaseTypeDataBean.disableUIFields}"
												value="#{CaseTypeDataBean.caseTypeStepVO.caseTypeStepInclude}">
												<f:selectItem itemLabel="#{msg['label.casetype.Yes']}"
													itemValue="Yes" />
												<f:selectItem itemLabel="#{msg['label.casetype.No']}"
													itemValue="No" />
												<%--<f:selectItems value="#{CaseTypeDataBean.includeRadioList}" />--%>
											</h:selectOneRadio>
										</m:div>
									</m:td>
									<m:td>
										<m:div styleClass="padb">
											<m:span styleClass="required">
												<h:outputText id="PRGCMGTOT1128"
													value="#{cont['label.ref.reqSymbol']}" />
											</m:span>
											<h:outputLabel id="casestepDesc" for="descid"
												value="#{msg['label.casetype.casesteps.description']}" />
											<m:br></m:br>
											<h:selectOneMenu id="descid"
												disabled="#{CaseTypeDataBean.disableUIFields}"
												onchange="javascript:selectOne=true;flagWarn=false;"
												value="#{CaseTypeDataBean.caseTypeStepVO.description}">
												<f:selectItems value="#{CaseTypeDataBean.descnCaseStepList}" />
											</h:selectOneMenu>
											<m:br></m:br>
											<h:message id="PRGCMGTM164" for="descid"
												styleClass="errorMessage" />


										</m:div>
									</m:td>
									<%--   Commented for the defect id ESPRD00304541
			                         <m:td>
										<m:div styleClass="padb">
											<m:span styleClass="required">
												<h:outputText id="PRGCMGTOT1129" value="#{cont['label.ref.reqSymbol']}" />
											</m:span>
											<h:outputLabel id="casestepOrder" for="steporder"												value="#{msg['label.casetype.casesteps.order']}" />
											<m:br></m:br>
											<h:selectOneMenu id="steporder"												onchange="javascript:selectOne=true;"												value="#{CaseTypeDataBean.caseTypeStepVO.stepOrderNum}">
												<f:selectItems value="#{CaseTypeDataBean.orderCaseStepList}" />
											</h:selectOneMenu>
											<h:message id="PRGCMGTM165" for="steporder" styleClass="errorMessage" />
											<m:br></m:br>
										</m:div>
									</m:td>   --%>
									<%--  Added for the defect id ESPRD00304541 --%>
									<m:td>
										<m:div styleClass="padb">
											<m:span styleClass="required">
												<h:outputText id="PRGCMGTOT1130"
													value="#{cont['label.ref.reqSymbol']}" />
											</m:span>
											<h:outputLabel id="casestepOrder" for="steporder"
												value="#{msg['label.casetype.casesteps.order']}" rendered="#{CaseTypeDataBean.showEditCaseSteps}" />
											<m:br rendered="#{CaseTypeDataBean.showEditCaseSteps}"></m:br>
											<h:selectOneMenu id="steporder"
												disabled="#{CaseTypeDataBean.disableUIFields}"
												rendered="#{CaseTypeDataBean.showEditCaseSteps}"
												onchange="javascript:flagWarn=false;selectOne=true;javascript:orderNoWarn(this.value);"
												value="#{CaseTypeDataBean.caseTypeStepVO.stepOrderNum}">
												<f:selectItems value="#{CaseTypeDataBean.orderCaseStepList}" />
											</h:selectOneMenu>
											<h:outputLabel id="casestepOrder1" for="steporder1"
												value="#{msg['label.casetype.casesteps.order']}" rendered="#{CaseTypeDataBean.showAddCaseSteps}" />
											<m:br rendered="#{CaseTypeDataBean.showAddCaseSteps}"></m:br>
											<h:selectOneMenu id="steporder1"
												disabled="#{CaseTypeDataBean.disableUIFields}"
												rendered="#{CaseTypeDataBean.showAddCaseSteps}"
												onchange="javascript:flagWarn=false;selectOne=true;"
												value="#{CaseTypeDataBean.caseTypeStepVO.stepOrderNum}">
												<f:selectItems value="#{CaseTypeDataBean.orderCaseStepList}" />
											</h:selectOneMenu>
											<m:br></m:br>
											<h:message id="PRGCMGTM166" for="steporder"
												styleClass="errorMessage" />

										</m:div>
									</m:td>


									<%--  End of the defect id ESPRD00304541 --%>

									<%-- Commented for the defect id ESPRD00304541 
									<m:td>
										<m:div styleClass="padb">
											<h:outputLabel id="PRGCMGTOLL508" for="automaticmenu"												value="#{msg['label.casetype.casesteps.automaticRouteTo']}" />
											<m:br></m:br>
											<h:selectOneMenu id="automaticmenu"												onchange="javascript:selectOne=true;"												value="#{CaseTypeDataBean.caseTypeStepVO.automaticRouteTo}">
			
												<f:selectItems
													value="#{CaseTypeDataBean.caseStepAutRoutoToList}" />
											</h:selectOneMenu>
											<m:br></m:br>
										</m:div>
									</m:td> --%>
									<%--  Added for the defect id ESPRD00304541 --%>
									<m:td>
										<m:div styleClass="padb">
											<h:outputLabel id="PRGCMGTOLL509" for="automaticmenu"
												value="#{msg['label.casetype.casesteps.automaticRouteTo']}" rendered="#{CaseTypeDataBean.showEditCaseSteps}" />
											<m:br rendered="#{CaseTypeDataBean.showEditCaseSteps}"></m:br>
											<h:selectOneMenu id="automaticmenu"
												disabled="#{CaseTypeDataBean.disableUIFields}"
												rendered="#{CaseTypeDataBean.showEditCaseSteps}"
												onchange="javascript:flagWarn=false;selectOne=true;javascript:automaticRouteToWarn(this.value);"
												value="#{CaseTypeDataBean.caseTypeStepVO.automaticRouteTo}">
												<f:selectItem itemLabel="" itemValue="" />
												<f:selectItems
													value="#{CaseTypeDataBean.caseStepAutRoutoToList}" />
											</h:selectOneMenu>
											<h:outputLabel id="PRGCMGTOLL509A" for="automaticmenu1"
												value="#{msg['label.casetype.casesteps.automaticRouteTo']}" rendered="#{CaseTypeDataBean.showAddCaseSteps}" />
											<m:br rendered="#{CaseTypeDataBean.showAddCaseSteps}"></m:br>
											<h:selectOneMenu id="automaticmenu1"
												disabled="#{CaseTypeDataBean.disableUIFields}"
												rendered="#{CaseTypeDataBean.showAddCaseSteps}"
												onchange="javascript:flagWarn=false;selectOne=true;"
												value="#{CaseTypeDataBean.caseTypeStepVO.automaticRouteTo}">
												<f:selectItem itemLabel="" itemValue="" />
												<f:selectItems
													value="#{CaseTypeDataBean.caseStepAutRoutoToList}" />
											</h:selectOneMenu>
											<m:br></m:br>
										</m:div>
									</m:td>
								</m:tr>
							</m:table>
						</m:td>
					</m:tr>
					<m:tr>
						<m:td>
							<m:table id="PROVIDERMMT20120731164811472" cellspacing="0" width="100%">
								<m:tr>
									<m:td>
										<m:div styleClass="padb">
											<h:outputLabel for="PRGCMGTIT5" id="PRGCMGTOT1131" 
												value="#{msg['label.casetype.casesteps.daysToComplete']}"
												style="WIDTH:90px" />
											<m:br></m:br>
											<h:inputText id="PRGCMGTIT5"
												disabled="#{CaseTypeDataBean.disableUIFields}"
												value="#{CaseTypeDataBean.caseTypeStepVO.dfltDaysToCmplCnt}"
												style="WIDTH:90px">
											</h:inputText>
											<m:br></m:br>
										</m:div>
									</m:td>

									<m:td>
										<m:div styleClass="padb">
											<h:outputLabel id="PRGCMGTOLL510" for="cesendalertmenu32"
												value="#{msg['label.casetype.casesteps.completionBasedOn']}" />
											<m:br></m:br>
											<h:selectOneMenu id="cesendalertmenu32"
												disabled="#{CaseTypeDataBean.disableUIFields}"
												onchange="javascript:flagWarn=false;selectOne=true;"
												value="#{CaseTypeDataBean.caseTypeStepVO.dfltCmpltnBasedOnColName}">
												<f:selectItems
													value="#{CaseTypeDataBean.completionBasedOnList}" />
											</h:selectOneMenu>
											<m:br></m:br>
										</m:div>
									</m:td>

									<m:td>
										<m:div styleClass="padb">
											<h:outputLabel id="cenotifyalert" for="csnotifyalert"
												value="#{msg['label.casetype.caseevents.notifyViaAlert']}" />
											<m:br></m:br>
											<h:selectOneMenu id="csnotifyalert"
												disabled="#{CaseTypeDataBean.disableUIFields}"
												onchange="javascript:flagWarn=false;selectOne=true;"
												value="#{CaseTypeDataBean.caseTypeStepVO.dfltNotfyAlertUserId}">
												<f:selectItem itemLabel="" itemValue="" />
												<f:selectItems
													value="#{CaseTypeDataBean.dfltNotifyAlertList}" />
											</h:selectOneMenu>
											<m:br></m:br>
										</m:div>
									</m:td>
									<m:td>
										<m:div styleClass="padb">
											<h:outputLabel id="cealertbased" for="cealertbasedmenu"
												value="#{msg['label.casetype.caseevents.alertBasedOn']}" />
											<m:br></m:br>
											<h:selectOneMenu id="cealertbasedmenu"
												disabled="#{CaseTypeDataBean.disableUIFields}"
												onchange="javascript:flagWarn=false;selectOne=true;"
												value="#{CaseTypeDataBean.caseTypeStepVO.dfltAlertBasedOnColName}">
												<f:selectItems
													value="#{CaseTypeDataBean.dfltAlertBasedOnList}" />
											</h:selectOneMenu>
											<m:br></m:br>
											<m:br />
											<h:message id="PRGCMGTM167" for="cealertbasedmenu"
												styleClass="colorRed" />
										</m:div>

										<m:br />
									</m:td>
								</m:tr>
							</m:table>
						</m:td>
					</m:tr>
					<m:tr>
						<m:td>
							<m:table id="PROVIDERMMT20120731164811473" cellspacing="0" width="100%">
								<m:tr>
									<m:td>
										<m:div styleClass="padb">
											<h:outputLabel for="cesendalertmen3" id="sendalertlabel"
												value="#{msg['label.casetype.casesteps.sendAlert']}" />
											<m:br />
											<h:panelGrid id="PRGCMGTPGD123" columns="2">

												<h:selectOneMenu id="cesendalertmen3"
													disabled="#{CaseTypeDataBean.disableUIFields}"
													onchange="javascript:flagWarn=false;selectOne=true;"
													value="#{CaseTypeDataBean.caseTypeStepVO.dfltSendAlertDaysCode}">
													<f:selectItems
														value="#{CaseTypeDataBean.dfltAlertSendBasedOnList}" />

												</h:selectOneMenu>
												<h:selectOneRadio id="sendalertradio"
													disabled="#{CaseTypeDataBean.disableUIFields}"
													value="#{CaseTypeDataBean.caseTypeStepVO.dfltBeforeAfterCode}">
													<f:selectItem
														itemLabel="#{msg['label.casetype.casesteps.before']}"
														itemValue="B" />
													<f:selectItem
														itemLabel="#{msg['label.casetype.casesteps.after']}"
														itemValue="A" />
												</h:selectOneRadio>
											</h:panelGrid>
											<m:br />
											<h:message id="PRGCMGTM168" for="cesendalertmen3"
												styleClass="colorRed" />
										</m:div>

										<m:br />
									</m:td>
									<m:td colspan="2">
										<m:div styleClass="padb">
											<h:outputLabel for="defaulttempmenu" id="defaulttemp"
												value="#{msg['label.casetype.casesteps.defaultTemplate']}" />
											<m:br />
											<h:selectOneMenu id="defaulttempmenu"
												disabled="#{CaseTypeDataBean.disableUIFields}"
												onchange="javascript:flagWarn=false;selectOne=true;"
												value="#{CaseTypeDataBean.caseTypeStepVO.dfltCotsLtrTmpltKeyData}">
												<f:selectItems
													value="#{CaseTypeDataBean.templateDropDownList}" />
											</h:selectOneMenu>
											<m:br />
										</m:div>
									</m:td>
									<m:td>
										<m:br />
									</m:td>
								</m:tr>
							</m:table>
						</m:td>
					</m:tr>
				</m:table>
			</m:div>
			<%--CR_ESPRD00373565_MaintainCaseTypes_06AUG2010 --%>
			<f:subview id="CaseStepsAuditSubViewId">
				<m:div onclick="javascript:flagWarn=false;"
					onmousedown="javascript:flagWarn=false;"
					id="showEditCaseStepsAuditId"
					rendered="#{CaseTypeDataBean.showEditCaseSteps && CaseTypeDataBean.enableMaintainCaseTypeAuditLog}">
					<audit:auditTableSet id="EditCaseStepsAuditId"
						value="#{CaseTypeDataBean.caseTypeStepVO.auditKeyList}"
						numOfRecPerPage="10">
					</audit:auditTableSet>

				</m:div>
			</f:subview>
			<%--EOF CR_ESPRD00373565_MaintainCaseTypes_06AUG2010 --%>

		</m:div>

	</m:div>
</m:div>
