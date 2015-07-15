<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%--

<SCRIPT>
function plusMinusToggleWithoutHidden(id,obj) {

		 var el = document.getElementById(id);
		 if (el.style.display == 'none') {
		  obj.className = 'plus';
		 } else if (el.style.display == 'block') {
		  obj.className = 'minus';
		 } else if (el.style.display == '') {
		  obj.className = 'minus';
		 }
	}
</SCRIPT>
--%>
<%-- <m:div sid="t_div_hide"> --%>
<m:div id="assocDataID1">

	<m:div id="assocDataID2">
		<h:outputText styleClass="strong" id="assocDataID3"
			value="#{crspd['label.crspd.crasswiththis']}"></h:outputText>

		<t:dataTable styleClass="dataTable" columnClasses="list-column-center"
			id="assocData" footerClass="list-footer" headerClass="tableHead"
			rows="10" rowClasses="list-row-even, list-row-odd" width="100%"
			first="#{CorrespondenceDataBean.startIndexForAsc}"
			rowIndexVar="rowIndex"
			value="#{CorrespondenceDataBean.correspondenceRecordVO.listOfAssociatedCRs}"
			var="assocCR">
			<h:column id="col11">
				<f:facet name="header">
					<h:panelGrid columns="2" id="assocDataPanelId1">
						<h:outputLabel id="assocDataID4" for="corresNumValue"
							value="#{crspd['label.crspd.crn']}" />
						<h:panelGroup id="corresNumValue">
							<t:div id="assocDatadiv1" style="width x;align=left">
								<t:commandLink id="assocCRNAsc" style="text-decoration: none;"
									actionListener="#{CorrespondenceControllerBean.sortAssocCR}"
									onmousedown="javascript:flagWarn=false;"
									rendered="#{CorrespondenceDataBean.imageRender != 'assocCRNAsc'}">
									<m:div id="assocDatadiv2" title="for ascending"
										styleClass="sort_asc" />
									<f:attribute name="columnName" value="assocCRN" />
									<f:attribute name="sortOrder" value="asc" />
								</t:commandLink>
							</t:div>

							<t:div id="assocDatadiv3" style="width x;align=left">
								<t:commandLink id="assocCRNDesc" style="text-decoration: none;"
									actionListener="#{CorrespondenceControllerBean.sortAssocCR}"
									onmousedown="javascript:flagWarn=false;"
									rendered="#{CorrespondenceDataBean.imageRender != 'assocCRNDesc'}">
									<m:div id="assocDatadiv4" title="for descending"
										styleClass="sort_desc" />
									<f:attribute name="columnName" value="assocCRN" />
									<f:attribute name="sortOrder" value="desc" />
								</t:commandLink>

							</t:div>
						</h:panelGroup>
					</h:panelGrid>
				</f:facet>
				<t:commandLink id="assoccrnr" onclick="flagWarn=false;"
					action="#{CorrespondenceControllerBean.showAssocCorrespondence}">
					<h:outputText id="assoccrnrText"
						value="#{assocCR.correspondenceRecordNumber}" />
					<f:param name="indexCode" value="#{rowIndex}"></f:param>
				</t:commandLink>
			</h:column>
			<h:column id="col112">
				<f:facet name="header">
					<h:panelGrid columns="2" id="assocDataPanelId2">
						<h:outputLabel id="assocDataID5" for="openDateValue"
							value="#{crspd['label.crspd.opendate']}" />
						<h:panelGroup id="openDateValue">
							<t:div id="assocDatadiv5" style="width x;align=left">
								<t:commandLink id="assocODAsc" style="text-decoration: none;"
									actionListener="#{CorrespondenceControllerBean.sortAssocCR}"
									onmousedown="javascript:flagWarn=false;"
									rendered="#{CorrespondenceDataBean.imageRender != 'assocODAsc'}">
									<m:div id="assocDatadiv6" title="for ascending"
										styleClass="sort_asc" />
									<f:attribute name="columnName" value="assocOD" />
									<f:attribute name="sortOrder" value="asc" />
								</t:commandLink>
							</t:div>

							<t:div id="assocDatadiv7" style="width x;align=left">
								<t:commandLink id="assocODDesc" style="text-decoration: none;"
									actionListener="#{CorrespondenceControllerBean.sortAssocCR}"
									onmousedown="javascript:flagWarn=false;"
									rendered="#{CorrespondenceDataBean.imageRender != 'assocODDesc'}">
									<m:div id="assocDatadiv8" title="for descending"
										styleClass="sort_desc" />
									<f:attribute name="columnName" value="assocOD" />
									<f:attribute name="sortOrder" value="desc" />
								</t:commandLink>

							</t:div>
						</h:panelGroup>
					</h:panelGrid>
				</f:facet>
				<h:outputText value="#{assocCR.openDate}" id="AssopenDateId" />
			</h:column>
			<h:column id="col113">
				<f:facet name="header">
					<h:panelGrid columns="2" id="assocDataPanelId3">
						<h:outputLabel for="statusValue"
							value="#{crspd['label.crspd.status']}" id="statValLblId" />
						<h:panelGroup id="statusValue">
							<t:div id="assocDatadiv9" style="width x;align=left">
								<t:commandLink id="assocStatusAsc"
									style="text-decoration: none;"
									actionListener="#{CorrespondenceControllerBean.sortAssocCR}"
									onmousedown="javascript:flagWarn=false;"
									rendered="#{CorrespondenceDataBean.imageRender != 'assocStatusAsc'}">
									<m:div id="assocDatadiv10" title="for ascending"
										styleClass="sort_asc" />
									<f:attribute name="columnName" value="assocStatus" />
									<f:attribute name="sortOrder" value="asc" />
								</t:commandLink>
							</t:div>
							<t:div id="assocDataImg1">
								<h:graphicImage id="assocDataImg2" styleClass="blankImage"
									width="1" height="5" alt="" url="/images/x.gif" />
							</t:div>
							<t:div id="assocDatadiv11" style="width x;align=left">
								<t:commandLink id="assocStatusDesc"
									style="text-decoration: none;"
									actionListener="#{CorrespondenceControllerBean.sortAssocCR}"
									onmousedown="javascript:flagWarn=false;"
									rendered="#{CorrespondenceDataBean.imageRender != 'assocStatusDesc'}">
									<m:div id="assocDatadiv12" title="for descending"
										styleClass="sort_desc" />
									<f:attribute name="columnName" value="assocStatus" />
									<f:attribute name="sortOrder" value="desc" />
								</t:commandLink>
								<t:div id="assocDataImg3">
									<h:graphicImage id="assocDataImg4" styleClass="blankImage"
										width="1" height="5" alt="" url="/images/x.gif" />
								</t:div>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>
				</f:facet>
				<h:outputText value="#{assocCR.status}" id="AssStatusId" />
			</h:column>
			<h:column id="col114">
				<f:facet name="header">
					<h:panelGrid columns="2" id="assocDataPanelId4">
						<h:outputLabel for="categoryValue"
							value="#{crspd['label.crspd.category']}" id="catValLblId" />
						<h:panelGroup id="categoryValue">
							<t:div id="assocDatadiv13" style="width x;align=left">
								<t:commandLink id="assocCatAsc" style="text-decoration: none;"
									actionListener="#{CorrespondenceControllerBean.sortAssocCR}"
									onmousedown="javascript:flagWarn=false;"
									rendered="#{CorrespondenceDataBean.imageRender != 'assocCatAsc'}">
									<m:div id="assocDatadiv14" title="for ascending"
										styleClass="sort_asc" />
									<f:attribute name="columnName" value="assocCat" />
									<f:attribute name="sortOrder" value="asc" />
								</t:commandLink>
							</t:div>
							<t:div id="assocDataImg5">
								<h:graphicImage id="assocDataImg56" styleClass="blankImage"
									width="1" height="5" alt="" url="/images/x.gif" />
							</t:div>
							<t:div id="assocDatadiv15" style="width x;align=left">
								<t:commandLink id="assocCatDesc" style="text-decoration: none;"
									actionListener="#{CorrespondenceControllerBean.sortAssocCR}"
									onmousedown="javascript:flagWarn=false;"
									rendered="#{CorrespondenceDataBean.imageRender != 'assocCatDesc'}">
									<m:div id="assocDatadiv16" title="for descending"
										styleClass="sort_desc" />
									<f:attribute name="columnName" value="assocCat" />
									<f:attribute name="sortOrder" value="desc" />
								</t:commandLink>
								<t:div id="assocDataImg6">
									<h:graphicImage id="assocDataImg7" styleClass="blankImage"
										width="1" height="5" alt="" url="/images/x.gif" />
								</t:div>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>
				</f:facet>
				<h:outputText value="#{assocCR.category}" id="assocDataCatId" />
			</h:column>
			<h:column id="col115">
				<f:facet name="header">
					<h:panelGrid columns="2" id="assocDataPanelId5">
						<h:outputLabel for="subjectValue"
							value="#{crspd['label.crspd.subject']}" id="subValLblId" />
						<h:panelGroup id="subjectValue">
							<t:div id="assocDatadiv17" style="width x;align=left">
								<t:commandLink id="assocSubjAsc" style="text-decoration: none;"
									actionListener="#{CorrespondenceControllerBean.sortAssocCR}"
									onmousedown="javascript:flagWarn=false;"
									rendered="#{CorrespondenceDataBean.imageRender != 'assocSubjAsc'}">
									<m:div id="assocDatadiv18" title="for ascending"
										styleClass="sort_asc" />
									<f:attribute name="columnName" value="assocSubj" />
									<f:attribute name="sortOrder" value="asc" />
								</t:commandLink>
							</t:div>
							<t:div id="assocDataImg8">
								<h:graphicImage id="assocDataImg9" styleClass="blankImage"
									width="1" height="5" alt="" url="/images/x.gif" />
							</t:div>
							<t:div id="assocDatadiv19" style="width x;align=left">
								<t:commandLink id="assocSubjDesc" style="text-decoration: none;"
									actionListener="#{CorrespondenceControllerBean.sortAssocCR}"
									onmousedown="javascript:flagWarn=false;"
									rendered="#{CorrespondenceDataBean.imageRender != 'assocSubjDesc'}">
									<m:div id="assocDatadiv20" title="for descending"
										styleClass="sort_desc" />
									<f:attribute name="columnName" value="assocSubj" />
									<f:attribute name="sortOrder" value="desc" />
								</t:commandLink>
								<t:div id="assocDataImg10">
									<h:graphicImage id="assocDataImg11" styleClass="blankImage"
										width="1" height="5" alt="" url="/images/x.gif" />
								</t:div>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>
				</f:facet>
				<h:outputText value="#{assocCR.subject}" id="assocDataSubId" />
			</h:column>
			<h:column id="col9">
				<f:facet name="header">
					<h:panelGrid columns="2" id="assocDataPanelId6">
						<h:outputLabel for="contactValue"
							value="#{crspd['label.crspd.contact']}" id="conValLblId" />
						<h:panelGroup id="contactValue">
							<t:div id="assocDatadiv21" style="width x;align=left">
								<t:commandLink id="assocContAsc" style="text-decoration: none;"
									actionListener="#{CorrespondenceControllerBean.sortAssocCR}"
									onmousedown="javascript:flagWarn=false;"
									rendered="#{CorrespondenceDataBean.imageRender != 'assocContAsc'}">
									<m:div id="assocDatadiv22" title="for ascending"
										styleClass="sort_asc" />
									<f:attribute name="columnName" value="assocCont" />
									<f:attribute name="sortOrder" value="asc" />
								</t:commandLink>
							</t:div>
							<t:div id="assocDataImg12">
								<h:graphicImage id="assocDataImg13" styleClass="blankImage"
									width="1" height="5" alt="" url="/images/x.gif" />
							</t:div>
							<t:div id="assocDatadiv23" style="width x;align=left">
								<t:commandLink id="assocContDesc" style="text-decoration: none;"
									actionListener="#{CorrespondenceControllerBean.sortAssocCR}"
									onmousedown="javascript:flagWarn=false;"
									rendered="#{CorrespondenceDataBean.imageRender != 'assocContDesc'}">
									<m:div id="assocDatadiv24" title="for descending"
										styleClass="sort_desc" />
									<f:attribute name="columnName" value="assocCont" />
									<f:attribute name="sortOrder" value="desc" />
								</t:commandLink>
								<t:div id="assocDataImg14">
									<h:graphicImage id="assocDataImg15" styleClass="blankImage"
										width="1" height="5" alt="" url="/images/x.gif" />
								</t:div>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>
				</f:facet>
				<h:outputText value="#{assocCR.contact}" id="assocDataConId" />
			</h:column>
			<f:facet name="footer">
				<m:div id="nodataExistCR"
					rendered="#{empty CorrespondenceDataBean.correspondenceRecordVO.listOfAssociatedCRs}"
					align="center">
					<h:outputText id="PRGCMGTOT633" value="#{ref['label.ref.noData']}"></h:outputText>
				</m:div>
			</f:facet>

		</t:dataTable>

	</m:div>
	<m:div styleClass="floatl" id="assocDataID6">
	</m:div>
	<m:div styleClass="searchResults" id="assocDataID7">

		<t:dataScroller for="assocData" paginator="true" id="assocDataID8"
			onclick="flagWarn=false;"
			paginatorActiveColumnStyle='font-weight:bold;' paginatorMaxPages="3"
			immediate="false" pageCountVar="pageCount" pageIndexVar="pageIndex"
			firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
			rowsCountVar="rowsCount"
			style="float:right;position:relative;bottom:-6px;text-decoration:underline;">


			<f:facet name="previous">
				<h:outputText style="text-decoration:underline;" id="assocDataID9"
					value="#{crspd['label.crspd.pageLeft']}"
					rendered="#{pageIndex > 1}">
				</h:outputText>
			</f:facet>
			<f:facet name="next">
				<h:outputText style="text-decoration:underline;" id="assocDataID10"
					value="#{crspd['label.crspd.pageRight']}"
					rendered="#{pageIndex < pageCount}">
				</h:outputText>
			</f:facet>
			<h:outputText rendered="#{rowsCount > 0}" id="assocDataID11"
				value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
				style="float:left;position:relative;bottom:-6px;font-weight:bold;" />
		</t:dataScroller>

	</m:div>

	<m:br id="brId1" />
	<m:br id="brId2" />
	<m:br id="brId3" />
	<m:div id="assocDataID12"
		rendered="#{CorrespondenceDataBean.renderShowAssocRec}">
		<m:div styleClass="moreInfo" id="assocDataID13">
			<m:div styleClass="moreInfoBar" id="assocDataID14">
				<m:div styleClass="infoTitle" id="assocDataID17">
					<h:outputText value="#{crspd['label.crspd.cr']}" id="assocDataID15" />
				</m:div>
				<m:div align="right" id="assocDataID16">
					<m:div id="assocDataID18">
					<%--flagWarn=false is added to onClick attribute below for the Defect ESPRD00840752--%>
						<h:commandButton id="assocDataID19"
							disabled="#{CorrespondenceDataBean.crClosed || !CorrespondenceDataBean.controlRequired}"
							style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:38px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal"
							onclick="flagWarn=false;if (!confirm('Are you sure you want to Delete?')) return(false);"
							action="#{CorrespondenceControllerBean.deleteAssocRecord}"
							value="#{crspd['label.crspd.delete']}" />
						<%-- <h:commandLink id="PRGCMGTCL112">
						<h:outputText id="PRGCMGTOT634" value="#{crspd['label.crspd.delete']}" />
					</h:commandLink>
					--%>
						<h:outputText escape="false" id="assocDataID20"
							value="#{ref['label.ref.linkSeperator']}" />
						<t:commandLink id="cancelAssoc"
							action="#{CorrespondenceControllerBean.cancelShowAssocCorrespondence}">
							<h:outputText id="assocDataID21"
								value="#{crspd['label.crspd.cancel']}" />
						</t:commandLink>
					</m:div>

				</m:div>
			</m:div>
			<m:table width="100%" id="assocDataID22">
				<m:tr id="assocDataID23">
					<m:td id="assocDataID24">
						<m:div styleClass="padx" id="assocDataID25">
							<h:outputLabel for="assocCorresNum" id="assocDataID26"
								value="#{crspd['label.crspd.crn']}" />
							<m:br id="brId4" />
							<h:inputText id="assocCorresNum"
								value="#{CorrespondenceDataBean.associatedCorrespondenceVO.correspondenceRecordNumber}"
								disabled="#{CorrespondenceDataBean.disableAssocRec}" />
						</m:div>
					</m:td>
					<m:td id="assocDataID27">
						<m:div styleClass="padx" id="assocDataID28">
							<h:outputLabel for="assocOpenDate" id="assocDataID29"
								value="#{crspd['label.crspd.opendate']}" />
							<m:br id="brId5" />
							<h:inputText id="assocOpenDate"
								value="#{CorrespondenceDataBean.associatedCorrespondenceVO.openDate}"
								size="10" disabled="#{CorrespondenceDataBean.disableAssocRec}" />
						</m:div>
					</m:td>

					<m:td id="assocDataID30">
						<m:div styleClass="padx" id="assocDataID31">
							<h:outputLabel for="assocStatus" id="assocDataID32"
								value="#{crspd['label.crspd.status']}" />
							<m:br id="brId6" />
							<h:inputText id="assocStatus" size="10"
								value="#{CorrespondenceDataBean.associatedCorrespondenceVO.status}"
								disabled="#{CorrespondenceDataBean.disableAssocRec}" />
						</m:div>
					</m:td>
					<m:td id="assocDataID33">
						<m:div styleClass="padx" id="assocDataID34">
							<h:outputLabel for="assocCat" id="assocDataID35"
								value="#{crspd['label.crspd.category']}" />
							<m:br id="brId7" />
							<h:inputText id="assocCat" size="10"
								value="#{CorrespondenceDataBean.associatedCorrespondenceVO.category}"
								disabled="#{CorrespondenceDataBean.disableAssocRec}" />
						</m:div>
					</m:td>
					<m:td id="assocDataID36">
						<m:div styleClass="padx" id="assocDataID37">
							<h:outputLabel for="assocSubj" id="assocDataID38"
								value="#{crspd['label.crspd.subject']}" />
							<m:br id="brId8" />
							<h:inputText id="assocSubj"
								value="#{CorrespondenceDataBean.associatedCorrespondenceVO.subject}"
								disabled="#{CorrespondenceDataBean.disableAssocRec}" />
						</m:div>
					</m:td>
					<m:td id="assocDataID39">
						<m:div styleClass="padx" id="assocDataID40">
							<h:outputLabel for="assocContact" id="assocDataID41"
								value="#{crspd['label.crspd.contact']}" />
							<m:br id="brId9" />
							<h:inputText id="assocContact"
								value="#{CorrespondenceDataBean.associatedCorrespondenceVO.contact}"
								disabled="#{CorrespondenceDataBean.disableAssocRec}" />
						</m:div>
					</m:td>
				</m:tr>
			</m:table>
		</m:div>
	</m:div>
</m:div>
