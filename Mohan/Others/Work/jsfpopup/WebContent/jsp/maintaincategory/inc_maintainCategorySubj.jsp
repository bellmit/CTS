<%-- 
  - Author(s): Wipro
  - Date: Mon Jan 29 14:37:45 IST 2007
  - Copyright Notice: Copyright (c) 2006 Affiliated Computer Services, Inc.
  - @ 2006
  - Description: 
  --%>
<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>

<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>

<m:div id="subjectTab">

	<t:dataTable id="subjectTB" width="100%" styleClass="dataTable"		var="subDetails" rows="10" columnClasses="columnClass"		headerClass="headerClass" footerClass="footerClass"		rowClasses="row_alt,row"		value="#{CategoryDataBean.listOfCategorySubVOs}"		rowOnMouseOver="this.origClassName = this.className;this.className='row_mouse row_link';"		rowOnMouseOut="if (this.origClassName) this.className = this.origClassName;"		onmousedown="javascript:focusThis(this.id);">

		<h:column id="includeCol">
			<f:facet name="header">
				<h:panelGrid id="PRGCMGTPGD141" columns="2">
					<h:outputLabel id="includeLabel" for="includeAscCmdLink"						value="#{ctg['label.category.include']}" />
					<h:panelGroup id="PRGCMGTPGP89">
						<t:div styleClass="alignLeft">
							<t:commandLink id="includeAscCmdLink"								onmousedown="if(event.button==1) flagWarn=false;"								actionListener="#{CategoryControllerBean.sortSubjects}"								rendered="#{CategoryDataBean.imageRender != 'includeAscCmdLink'}">
								<m:div title="#{ctg['title.category.forAscd']}"
									styleClass="sort_asc" />
								<f:attribute name="#{ctg['id.category.columnName']}"
									value="include" />
								<f:attribute name="#{ctg['id.category.sortOrder']}"
									value="ascending" />
							</t:commandLink>
						</t:div>
						<t:div styleClass="alignLeft">
							<t:commandLink id="includeDescCmdLink"								onmousedown="if(event.button==1) flagWarn=false;"								actionListener="#{CategoryControllerBean.sortSubjects}"								rendered="#{CategoryDataBean.imageRender != 'includeDescCmdLink'}">
								<m:div title="#{ctg['title.category.forDsnd']}"
									styleClass="sort_desc" />
								<f:attribute name="#{ctg['id.category.columnName']}"
									value="include" />
								<f:attribute name="#{ctg['id.category.sortOrder']}"
									value="descending" />
							</t:commandLink>
						</t:div>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>
			<m:div align="center">

				<t:commandLink id="include"					onmousedown="if(event.button==1) flagWarn=false;"					action="#{CategoryControllerBean.getSubjectDetails}"					rendered="#{subDetails.includeIndicatorforimage}">
					<h:graphicImage id="PROVIDERGI20120731164811524" styleClass="ind_check_yes" width="15" alt="#{ref['label.ref.true']}" title="#{ref['label.ref.true']}"
						url="/images/x.gif" />
					<f:param name="include" value="#{subDetails.includeIndicator}" />
					<f:param name="Subject" value="#{subDetails.subjectDesc}" />
					<f:param name="subjectCodeValue" value="#{subDetails.subjectCode}" />
				</t:commandLink>
				<t:commandLink id="notinclude"					onmousedown="if(event.button==1) flagWarn=false;"					action="#{CategoryControllerBean.getSubjectDetails}"					rendered="#{! subDetails.includeIndicatorforimage}">
					<%-- Infinite UC-PGM-CRM-031 DEFECT 3192--%>
					<%--<h:graphicImage  styleClass="ind_check_no" width="15" />--%>
					<f:param name="include" value="#{subDetails.includeIndicator}" />
					<f:param name="Subject" value="#{subDetails.subjectDesc}" />
					<f:param name="subjectCodeValue" value="#{subDetails.subjectCode}" />
				</t:commandLink>
			</m:div>
		</h:column>

		<h:column id="subjectCol">
			<f:facet name="header">
				<h:panelGrid id="PRGCMGTPGD142" columns="2">
					<h:outputLabel id="subLabel" for="subjAscCmdLink"						value="#{ctg['label.category.subject']}" />
					<h:panelGroup id="PRGCMGTPGP90">
						<t:div styleClass="alignLeft">
							<t:commandLink id="subjAscCmdLink"								onmousedown="if(event.button==1) flagWarn=false;"								actionListener="#{CategoryControllerBean.sortSubjects}"								rendered="#{CategoryDataBean.imageRender != 'subjAscCmdLink'}">
								<m:div title="#{ctg['title.category.forAscd']}"
									styleClass="sort_asc" />
								<f:attribute name="#{ctg['id.category.columnName']}"
									value="Subject" />
								<f:attribute name="#{ctg['id.category.sortOrder']}"
									value="ascending" />
							</t:commandLink>
						</t:div>
						<t:div styleClass="alignLeft">
							<t:commandLink id="subjDescCmdLink"								onmousedown="if(event.button==1) flagWarn=false;"								actionListener="#{CategoryControllerBean.sortSubjects}"								rendered="#{CategoryDataBean.imageRender != 'subjDescCmdLink'}">
								<m:div title="#{ctg['title.category.forDsnd']}"
									styleClass="sort_desc" />
								<f:attribute name="#{ctg['id.category.columnName']}"
									value="Subject" />
								<f:attribute name="#{ctg['id.category.sortOrder']}"
									value="descending" />
							</t:commandLink>
						</t:div>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>
			<%-- Infinite UC-PGM-CRM-031 DEFECT 59781--%>
			<t:commandLink id="subject"				onmousedown="if(event.button==1) flagWarn=false;"				action="#{CategoryControllerBean.getSubjectDetails}">
				<h:outputText id="valSubject" value="#{subDetails.subjectDesc}" />
				<f:param name="include" value="#{subDetails.includeIndicator}" />
				<f:param name="Subject" value="#{subDetails.subjectDesc}" />
				<f:param name="subjectCodeValue" value="#{subDetails.subjectCode}" />
			</t:commandLink>
		</h:column>
		<f:facet name="footer">
			<m:div id="nodata" align="center"
				rendered="#{CategoryDataBean.renderNoDataSub}">
				<h:outputText id="PRGCMGTOT1219" value="#{ref['label.ref.noData']}" />
			</m:div>
		</f:facet>
	</t:dataTable>

	<t:dataScroller id="PROVIDERMDS20120731164811525" pageCountVar="pageCount" pageIndexVar="pageIndex"
		paginator="true" paginatorActiveColumnStyle='font-weight:bold;'
		paginatorMaxPages="3" immediate="false" for="subjectTB"
		firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
		rowsCountVar="rowsCount" styleClass="dataScroller">
		<f:facet name="previous">
			<h:outputText id="PRGCMGTOT1220" styleClass="underline"				value=" #{ref['label.ref.lt']} " rendered="#{pageIndex > 1}"></h:outputText>
		</f:facet>
		<f:facet name="next">
			<h:outputText id="PRGCMGTOT1221" styleClass="underline"				value=" #{ref['label.ref.gt']} " rendered="#{pageIndex < pageCount}"></h:outputText>
		</f:facet>
		<h:outputText id="PRGCMGTOT1222"			value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"			styleClass="dataScrollerText" rendered="#{rowsCount>0}">
		</h:outputText>
	</t:dataScroller>
	<br>
	<br>
</m:div>

<m:div styleClass="moreInfo"
	rendered="#{CategoryDataBean.renderSubjectDetails}">

	<m:div styleClass="moreInfoBar">

		<m:div styleClass="infoTitle">
			<h:outputText id="PRGCMGTOT1223"				value="#{ctg['label.category.editSubject']}" />
		</m:div>

		<m:div styleClass="infoActions">
			<h:panelGroup id="PRGCMGTPGP91">
				<%--<h:commandLink id="PRGCMGTCL179" styleClass="strong" onmousedown="javascript:flagWarn=false;" 						     rendered="#{CategoryControllerBean.controlRequired}"						     action="#{CategoryControllerBean.saveSubjectStatus}">
							<h:outputText id="PRGCMGTOT1224" value="#{ent['label-sw-save']}" />
						</h:commandLink>--%>

				<h:commandButton					style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:30px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"					onmousedown="javascript:flagWarn=false; javascript:focusThis(this.id);"					value="#{ent['label-sw-save']}" id="cfIdforcatSub"					disabled="#{!CategoryControllerBean.controlRequired}"					action="#{CategoryControllerBean.saveSubjectStatus}" />

				<h:outputText id="PRGCMGTOT1225" escape="false"					value="#{ref['label.ref.linkSeperator']}" />



				<h:commandButton id="PRGCMGTCB45"					style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:44px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"					value="#{ent['label-sw-cancel']}"					action="#{CategoryControllerBean.cancelSubCategory}" />

			</h:panelGroup>
		</m:div>
	</m:div>

	<m:div styleClass="msgBox"
		rendered="#{CategoryDataBean.showEditSucessMessage}">
		<h:outputText id="PRGCMGTOT1226" value="#{ent['label-sw-success']}" />
	</m:div>

	<m:table id="PROVIDERMMT20120731164811526" cellspacing="0" width="94%">
		<m:tr>
			<m:td>
				<m:div styleClass="padb">
					<h:outputLabel id="activeinclude" for="SubCtgry11"						value="#{ctg['label.category.include']}" />
					<m:br />
					
					<%--disabled attribute is added below for fixing the defect ESPRD00802214 on 03/07/2012--%>
					
					<h:selectOneRadio id="SubCtgry11" enabledClass="black"						value="#{CategoryDataBean.subjectVO.includeIndicator}"  disabled="#{!CategoryControllerBean.controlRequired}">
						<f:selectItem itemValue="Yes"
							itemLabel="#{ref['label.ref.yes']}" />
						<f:selectItem itemValue="No"
							itemLabel="#{ref['label.ref.no']}" />
					</h:selectOneRadio>
				</m:div>
			</m:td>
			<m:td>
				<h:outputLabel id="subjectLabel" for="subjLabl"					value="#{ctg['label.category.subject']}" />
				<m:br></m:br>
				<h:outputText id="editSubject"					value="#{CategoryDataBean.subjectVO.subjectDesc}" />
			</m:td>
		</m:tr>
	</m:table>

</m:div>




