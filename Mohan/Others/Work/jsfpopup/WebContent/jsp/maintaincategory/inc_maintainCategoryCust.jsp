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

<m:div id="custFldTab">


	<t:dataTable id="custFldTB" width="100%" styleClass="dataTable"
		rows="10" var="cfDetails" border="0" columnClasses="columnClass"
		headerClass="headerClass" footerClass="footerClass"
		rowClasses="row_alt,row"
		rowOnMouseOver="this.origClassName = this.className;this.className='row_mouse row_link';"
		rowOnMouseOut="if (this.origClassName) this.className = this.origClassName;"
		value="#{CategoryDataBean.listOfCategoryCustomFldVOs}"
		onmousedown="javascript:focusThis(this.id);">

		<h:column id="includeCol">
			<f:facet name="header">
				<h:panelGrid id="PRGCMGTPGD135" columns="2">
					<h:outputLabel id="includeLabel" for="includeCFAscCmdLink"
						value="#{ctg['label.category.include']}" />
					<h:panelGroup id="PRGCMGTPGP82">
						<t:div styleClass="alignLeft">
							<t:commandLink id="includeCFAscCmdLink"
								onmousedown="if(event.button==1) flagWarn=false;"
								actionListener="#{CategoryControllerBean.sortCustomFields}"
								rendered="#{CategoryDataBean.imageRender != 'includeCFAscCmdLink'}">
								<m:div title="#{ctg['title.category.forAscd']}"
									styleClass="sort_asc" />
								<f:attribute name="#{ctg['id.category.columnName']}"
									value="include" />
								<f:attribute name="#{ctg['id.category.sortOrder']}"
									value="ascending" />
							</t:commandLink>
						</t:div>
						<t:div styleClass="alignLeft">
							<t:commandLink id="includeCFDescCmdLink"
								onmousedown="if(event.button==1) flagWarn=false;"
								actionListener="#{CategoryControllerBean.sortCustomFields}"
								rendered="#{CategoryDataBean.imageRender != 'includeCFDescCmdLink'}">
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
			<%--rendered attribute is changed below from cfDetails.includeIndicator to cfDetails.includeIndicatorImage for the Defect ESPRD00802214--%>
			<t:commandLink id="include"
				onmousedown="if(event.button==1) flagWarn=false;"
				action="#{CategoryControllerBean.getCustomFieldDetails}"
				rendered="#{cfDetails.includeIndicatorImage}">
				<h:graphicImage id="PROVIDERGI20120731164811518" styleClass="ind_check_yes" width="15" alt="#{ref['label.ref.true']}"
					title="#{ref['label.ref.true']}" url="/images/x.gif" />
				<f:param name="CustomField" value="#{cfDetails.customFieldSK}" />
				<f:param name="include" value="#{cfDetails.includeIndicator}" />
				<f:param name="Column Description" value="#{cfDetails.columnDesc}" />
				<f:param name="Data Type" value="#{cfDetails.dataType}" />
				<f:param name="Field Length" value="#{cfDetails.fieldLength}" />
				<f:param name="Required" value="#{cfDetails.cfRequired}" />
				<f:param name="Protected on Save"
					value="#{cfDetails.cfProtectedOnSave}" />
			</t:commandLink>
			<t:commandLink id="notinclude"
				onmousedown="if(event.button==1) flagWarn=false;"
				action="#{CategoryControllerBean.getCustomFieldDetails}"
				rendered="#{!cfDetails.includeIndicatorImage}">
				<%-- Infinite UC-PGM-CRM-031 DEFECT 3192--%>
				<%--<h:graphicImage  styleClass="ind_check_no" width="15" />--%>
				<f:param name="CustomField" value="#{cfDetails.customFieldSK}" />
				<f:param name="include" value="#{cfDetails.includeIndicator}" />
				<f:param name="Column Description" value="#{cfDetails.columnDesc}" />
				<f:param name="Data Type" value="#{cfDetails.dataType}" />
				<f:param name="Field Length" value="#{cfDetails.fieldLength}" />
				<f:param name="Required" value="#{cfDetails.cfRequired}" />
				<f:param name="Protected on Save"
					value="#{cfDetails.cfProtectedOnSave}" />
			</t:commandLink>
		</h:column>

		<h:column id="colDescCol">
			<f:facet name="header">
				<h:panelGrid id="PRGCMGTPGD136" columns="2">
					<h:outputLabel id="cdLabel" for="colDescCFAscCmdLink"
						value="#{ctg['label.category.colDesc']}" />
					<h:panelGroup id="PRGCMGTPGP83">
						<t:div styleClass="alignLeft">
							<t:commandLink id="colDescCFAscCmdLink"
								onmousedown="if(event.button==1) flagWarn=false;"
								actionListener="#{CategoryControllerBean.sortCustomFields}"
								rendered="#{CategoryDataBean.imageRender != 'colDescCFAscCmdLink'}">
								<m:div title="#{ctg['title.category.forAscd']}"
									styleClass="sort_asc" />
								<f:attribute name="#{ctg['id.category.columnName']}"
									value="Column Description" />
								<f:attribute name="#{ctg['id.category.sortOrder']}"
									value="ascending" />
							</t:commandLink>
						</t:div>
						<t:div styleClass="alignLeft">
							<t:commandLink id="colDescCFDescCmdLink"
								onmousedown="if(event.button==1) flagWarn=false;"
								actionListener="#{CategoryControllerBean.sortCustomFields}"
								rendered="#{CategoryDataBean.imageRender != 'colDescCFDescCmdLink'}">
								<m:div title="#{ctg['title.category.forDsnd']}"
									styleClass="sort_desc" />
								<f:attribute name="#{ctg['id.category.columnName']}"
									value="Column Description" />
								<f:attribute name="#{ctg['id.category.sortOrder']}"
									value="descending" />
							</t:commandLink>
						</t:div>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>
			<%-- Infinite UC-PGM-CRM-031 DEFECT 59072--%>
			<t:commandLink id="colDesc1"
				onmousedown="if(event.button==1) flagWarn=false;"
				action="#{CategoryControllerBean.getCustomFieldDetails}">
				<h:outputText id="valColDesc" value="#{cfDetails.columnDescDisp}" />
				<f:param name="CustomField" value="#{cfDetails.customFieldSK}" />
				<f:param name="include" value="#{cfDetails.includeIndicator}" />
				<f:param name="Column Description" value="#{cfDetails.columnDesc}" />
				<f:param name="Data Type" value="#{cfDetails.dataType}" />
				<f:param name="Field Length" value="#{cfDetails.fieldLength}" />
				<f:param name="Required" value="#{cfDetails.cfRequired}" />
				<f:param name="Protected on Save"
					value="#{cfDetails.cfProtectedOnSave}" />
			</t:commandLink>
		</h:column>

		<h:column id="dataTypeCol">
			<f:facet name="header">
				<h:panelGrid id="PRGCMGTPGD137" columns="2">
					<h:outputLabel id="dtLabel" for="dataTypeCFAscCmdLink"
						value="#{ctg['label.category.dataType']}" />
					<h:panelGroup id="PRGCMGTPGP84">
						<t:div styleClass="alignLeft">
							<t:commandLink id="dataTypeCFAscCmdLink"
								onmousedown="if(event.button==1) flagWarn=false;"
								actionListener="#{CategoryControllerBean.sortCustomFields}"
								rendered="#{CategoryDataBean.imageRender != 'dataTypeCFAscCmdLink'}">
								<m:div title="#{ctg['title.category.forAscd']}"
									styleClass="sort_asc" />
								<f:attribute name="#{ctg['id.category.columnName']}"
									value="Data Type" />
								<f:attribute name="#{ctg['id.category.sortOrder']}"
									value="ascending" />
							</t:commandLink>
						</t:div>
						<t:div styleClass="alignLeft">
							<t:commandLink id="dataTypeCFDescCmdLink"
								onmousedown="if(event.button==1) flagWarn=false;"
								actionListener="#{CategoryControllerBean.sortCustomFields}"
								rendered="#{CategoryDataBean.imageRender != 'dataTypeCFDescCmdLink'}">
								<m:div title="#{ctg['title.category.forDsnd']}"
									styleClass="sort_desc" />
								<f:attribute name="#{ctg['id.category.columnName']}"
									value="Data Type" />
								<f:attribute name="#{ctg['id.category.sortOrder']}"
									value="descending" />
							</t:commandLink>
						</t:div>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>

			<h:outputText id="valDataType" value="#{cfDetails.dataType}" />

		</h:column>

		<h:column id="FldLenCol">
			<f:facet name="header">
				<h:panelGrid id="PRGCMGTPGD138" columns="2">
					<h:outputLabel id="flLabel" for="fldLenCFAscCmdLink"
						value="#{ctg['label.category.fieldLength']}" />
					<h:panelGroup id="PRGCMGTPGP85">
						<t:div styleClass="alignLeft">
							<t:commandLink id="fldLenCFAscCmdLink"
								onmousedown="if(event.button==1) flagWarn=false;"
								actionListener="#{CategoryControllerBean.sortCustomFields}"
								rendered="#{CategoryDataBean.imageRender != 'fldLenCFAscCmdLink'}">
								<m:div title="#{ctg['title.category.forAscd']}"
									styleClass="sort_asc" />
								<f:attribute name="#{ctg['id.category.columnName']}"
									value="Field Length" />
								<f:attribute name="#{ctg['id.category.sortOrder']}"
									value="ascending" />
							</t:commandLink>
						</t:div>
						<t:div styleClass="alignLeft">
							<t:commandLink id="fldLenCFDescCmdLink"
								onmousedown="if(event.button==1) flagWarn=false;"
								actionListener="#{CategoryControllerBean.sortCustomFields}"
								rendered="#{CategoryDataBean.imageRender != 'fldLenCFDescCmdLink'}">
								<m:div title="#{ctg['title.category.forDsnd']}"
									styleClass="sort_desc" />
								<f:attribute name="#{ctg['id.category.columnName']}"
									value="Field Length" />
								<f:attribute name="#{ctg['id.category.sortOrder']}"
									value="descending" />
							</t:commandLink>
						</t:div>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>

			<h:outputText id="valFldLen" value="#{cfDetails.fieldLength}" />

		</h:column>

		<h:column id="requiredCol">
			<f:facet name="header">
				<h:panelGrid id="PRGCMGTPGD139" columns="2">
					<h:outputLabel id="reqLabel" for="reqCFAscCmdLink"
						value="#{ctg['label.category.required']}" />
					<h:panelGroup id="PRGCMGTPGP86">
						<t:div styleClass="alignLeft">
							<t:commandLink id="reqCFAscCmdLink"
								onmousedown="if(event.button==1) flagWarn=false;"
								actionListener="#{CategoryControllerBean.sortCustomFields}"
								rendered="#{CategoryDataBean.imageRender != 'reqCFAscCmdLink'}">
								<m:div title="#{ctg['title.category.forAscd']}"
									styleClass="sort_asc" />
								<f:attribute name="#{ctg['id.category.columnName']}"
									value="Required" />
								<f:attribute name="#{ctg['id.category.sortOrder']}"
									value="ascending" />
							</t:commandLink>
						</t:div>
						<t:div styleClass="alignLeft">
							<t:commandLink id="reqCFDescCmdLink"
								onmousedown="if(event.button==1) flagWarn=false;"
								actionListener="#{CategoryControllerBean.sortCustomFields}"
								rendered="#{CategoryDataBean.imageRender != 'reqCFDescCmdLink'}">
								<m:div title="#{ctg['title.category.forDsnd']}"
									styleClass="sort_desc" />
								<f:attribute name="#{ctg['id.category.columnName']}"
									value="Required" />
								<f:attribute name="#{ctg['id.category.sortOrder']}"
									value="descending" />
							</t:commandLink>
						</t:div>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>
			<%-- Infinite UC-PGM-CRM-031 DEFECT 3192--%>
			<%--<h:graphicImage title="false" styleClass="ind_check_no" width="15"
				rendered="#{cfDetails.cfRequired=='false'}" />--%>
			<h:graphicImage id="PROVIDERGI20120731164811519" title="true" styleClass="ind_check_yes" width="15"
				rendered="#{cfDetails.cfRequired=='true'}" alt=""
				url="/images/x.gif" />
		</h:column>

		<h:column id="posCol">
			<f:facet name="header">
				<h:panelGrid id="PRGCMGTPGD140" columns="2">
					<h:outputLabel id="posLabel" for="posCFAscCmdLink"
						value="#{ctg['label.category.protectedOnSave']}" />
					<h:panelGroup id="PRGCMGTPGP87">
						<t:div styleClass="alignLeft">
							<t:commandLink id="posCFAscCmdLink"
								onmousedown="if(event.button==1) flagWarn=false;"
								actionListener="#{CategoryControllerBean.sortCustomFields}"
								rendered="#{CategoryDataBean.imageRender != 'posCFAscCmdLink'}">
								<m:div title="#{ctg['title.category.forAscd']}"
									styleClass="sort_asc" />
								<f:attribute name="#{ctg['id.category.columnName']}"
									value="Protected on Save" />
								<f:attribute name="#{ctg['id.category.sortOrder']}"
									value="ascending" />
							</t:commandLink>
						</t:div>
						<t:div styleClass="alignLeft">
							<t:commandLink id="posCFDescCmdLink"
								onmousedown="if(event.button==1) flagWarn=false;"
								actionListener="#{CategoryControllerBean.sortCustomFields}"
								rendered="#{CategoryDataBean.imageRender != 'posCFDescCmdLink'}">
								<m:div title="#{ctg['title.category.forDsnd']}"
									styleClass="sort_desc" />
								<f:attribute name="#{ctg['id.category.columnName']}"
									value="Protected on Save" />
								<f:attribute name="#{ctg['id.category.sortOrder']}"
									value="descending" />
							</t:commandLink>
						</t:div>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>
			<%-- Infinite UC-PGM-CRM-031 DEFECT 3192--%>
			<%--<h:graphicImage title="false" styleClass="ind_check_no" width="15"
				rendered="#{cfDetails.cfProtectedOnSave=='false'}" />--%>
			<h:graphicImage id="PROVIDERGI20120731164811520" title="true" styleClass="ind_check_yes" width="15"
				rendered="#{cfDetails.cfProtectedOnSave=='true'}" alt=""
				url="/images/x.gif" />
		</h:column>
		<f:facet name="footer">
			<m:div id="nodata" align="center"
				rendered="#{CategoryDataBean.renderNoDataCF}">
				<h:outputText id="PRGCMGTOT1211" value="#{ref['label.ref.noData']}" />
			</m:div>
		</f:facet>
	</t:dataTable>

	<t:dataScroller id="PROVIDERMDS20120731164811521" pageCountVar="pageCount" pageIndexVar="pageIndex"
		paginator="true" paginatorActiveColumnStyle='font-weight:bold;'
		paginatorMaxPages="3" immediate="false" for="custFldTB"
		firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
		rowsCountVar="rowsCount" styleClass="dataScroller">
		<f:facet name="previous">
			<h:outputText id="PRGCMGTOT1212" styleClass="underline"
				value=" #{ref['label.ref.lt']} " rendered="#{pageIndex > 1}"></h:outputText>
		</f:facet>
		<f:facet name="next">
			<h:outputText id="PRGCMGTOT1213" styleClass="underline"
				value=" #{ref['label.ref.gt']} " rendered="#{pageIndex < pageCount}"></h:outputText>
		</f:facet>
		<h:outputText id="PRGCMGTOT1214"
			value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
			styleClass="dataScrollerText" rendered="#{rowsCount>0}">
		</h:outputText>
	</t:dataScroller>
	<br>
	<br>
</m:div>

<m:div styleClass="moreInfo"
	rendered="#{CategoryDataBean.renderCustomFieldDetails}">

	<m:div styleClass="moreInfoBar">

		<m:div styleClass="infoTitle">
			<h:outputText id="PRGCMGTOT1215"
				value="#{ctg['label.category.editCustomField']}" />
		</m:div>

		<m:div styleClass="infoActions">
			<h:panelGroup id="PRGCMGTPGP88">
				<%--<h:commandLink id="PRGCMGTCL178" styleClass="strong" onmousedown="javascript:flagWarn=false;" 						    rendered="#{CategoryControllerBean.controlRequired}"							action="#{CategoryControllerBean.saveCustomFieldStatus}">
							<h:outputText id="PRGCMGTOT1216" value="#{ent['label-sw-save']}" />
						</h:commandLink>--%>

				<h:commandButton
					style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:30px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"
					value="#{ent['label-sw-save']}"
					onclick="javascript:flagWarn=false; javascript:focusThis(this.id);"
					disabled="#{!CategoryControllerBean.controlRequired}"
					id="cfIdforcatCust"
					action="#{CategoryControllerBean.saveCustomFieldStatus}" />
				<h:outputText id="PRGCMGTOT1217" escape="false"
					value="#{ref['label.ref.linkSeperator']}" />

				<h:commandButton id="PRGCMGTCB44"
					style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:44px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"
					value="#{ent['label-sw-cancel']}"
					action="#{CategoryControllerBean.cancelSubCategory}" />



			</h:panelGroup>
		</m:div>
	</m:div>


	<m:div styleClass="msgBox"
		rendered="#{CategoryDataBean.showEditSucessMessage}">
		<h:outputText id="PRGCMGTOT1218" value="#{ent['label-sw-success']}" />
	</m:div>

	<m:table id="PROVIDERMMT20120731164811522" cellspacing="0" width="94%">
		<m:tr>
			<m:td>
				<m:div styleClass="padb">
					<h:outputLabel id="actinclude" for="custFldCtgry11"
						value="#{ctg['label.category.include']}" />
					<m:br />
					
					<%--disabled attribute is added below for fixing the defect ESPRD00802214 on 03/07/2012--%>
					
					<h:selectOneRadio id="custFldCtgry11" enabledClass="black"
						value="#{CategoryDataBean.categoryCustomFieldsVO.includeIndicator}"
						disabled="#{!CategoryControllerBean.controlRequired}">
						
						<%--Below code is modified for fixing the defect ESPRD00802214 on 03/07/2012--%>
						
						<%--<f:selectItem itemValue="#{true}"
							itemLabel="#{ref['label.ref.yes']}" />
						<f:selectItem itemValue="#{false}"
							itemLabel="#{ref['label.ref.no']}" /> --%>
						<f:selectItem itemValue="Yes"
								itemLabel="#{ref['label.ref.yes']}" />
						<f:selectItem itemValue="No"
								itemLabel="#{ref['label.ref.no']}" />
					</h:selectOneRadio>
				</m:div>
			</m:td>
			<m:td>
				<h:outputLabel id="colmnLabel" for="clmlabl"
					value="#{ctg['label.category.colDesc']}" />
				<m:br></m:br>
				<h:outputText id="editColDesc"
					value="#{CategoryDataBean.categoryCustomFieldsVO.columnDesc}" />
			</m:td>
			<m:td>
				<h:outputLabel id="dataLabel" for="datalabl"
					value="#{ctg['label.category.dataType']}" />
				<m:br></m:br>
				<h:outputText id="editdataType"
					value="#{CategoryDataBean.categoryCustomFieldsVO.dataType}" />
			</m:td>
			<m:td>
				<h:outputLabel id="fieldabel" for="fildlabl"
					value="#{ctg['label.category.fieldLength']}" />
				<m:br></m:br>
				<h:outputText id="editField"
					value="#{CategoryDataBean.categoryCustomFieldsVO.fieldLength}" />
			</m:td>
			<m:td>
				<h:outputLabel id="requiredLabel" for="editreqFld"
					value="#{ctg['label.category.required']}" />
				<m:br></m:br>
				<h:selectOneRadio id="editreqFld" enabledClass="black"
					disabled="true"
					value="#{CategoryDataBean.categoryCustomFieldsVO.cfRequired}">
					<f:selectItem itemValue="#{true}"
						itemLabel="#{ref['label.ref.yes']}" />
					<f:selectItem itemValue="#{false}"
						itemLabel="#{ref['label.ref.no']}" />
				</h:selectOneRadio>

			</m:td>
			<m:td>
				<h:outputLabel id="proOnSaveLabel" for="editProtected"
					value="#{ctg['label.category.protectedOnSave']}" />
				<m:br></m:br>
				<h:selectOneRadio id="editProtected" enabledClass="black"
					disabled="true"
					value="#{CategoryDataBean.categoryCustomFieldsVO.cfProtectedOnSave}">
					<f:selectItem itemValue="#{true}"
						itemLabel="#{ref['label.ref.yes']}" />
					<f:selectItem itemValue="#{false}"
						itemLabel="#{ref['label.ref.no']}" />
				</h:selectOneRadio>

			</m:td>
		</m:tr>
	</m:table>

</m:div>





