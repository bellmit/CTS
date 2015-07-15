<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>

<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.ProgramMessages"
	var="ref" />
<f:loadBundle
	basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_CommonAttachment"
	var="msg" />
<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.EnterpriseMessages"
	var="ent" />

<f:view>

	<%-- Small save Big Save start --%>
	<f:subview id="provService">
		<jsp:include page="/jsp/commonEntities/commonScripts.jsp" />
	</f:subview>
	<body onload="focusComponent();">
	<%-- Small save Big Save ends --%>

	<m:section id="PROVIDERMMS20120731164811139" styleClass="otherbg">
		<m:legend>
			<h:outputText id="crAttach_text"
				value="#{msg['label.attach.attachment']}" />
		</m:legend>
		<h:form id="crAttachmentBrowse">
			<hx:scriptCollector id="scriptCollector1">
				<m:br />
				<%-- onload cursor focus --%>
				<h:inputHidden id="controlRequiredForAddAttachment"
					value="#{AttachmentControllerBean.controlRequired}" />
				<m:br />
				
			<%--Removed onMouseover attribute to remove the hand sign on row for the defect ESPRD00797149 --%>
				<h:dataTable value="#{AttachmentDataBean.attachmentList}"
					first="#{AttachmentDataBean.attachmentsRowIndex}"
					columnClasses="columnClass" var="attachSpanResult"
					footerClass="footerClass" styleClass="dataTable" cellspacing="0"
					width="100%" border="0" headerClass="headerClass"
					rowClasses="row_alt,row" rows="3" id="attachmentsTable" >

					<h:column id="dateAddedCol1">
						<f:facet name="header">
							<h:panelGrid id="PRGCMGTPGD31" columns="2">
								<h:outputLabel id="PRGCMGTOLL126"
									value="#{msg['label.attach.dataAdded']}"
									for="dateAddedCommandLink1" />
								<h:panelGroup id="PRGCMGTPGP31">
									<t:commandLink id="dateAddedCommandLink1"
										actionListener="#{AttachmentControllerBean.sortCaseAttachmentsInfo}"
										rendered="#{AttachmentDataBean.imageRender != 'dateAddedCommandLink1'}">
										<m:div title="for ascending" styleClass="sort_asc" />
										<f:attribute name="columnName" value="Attach_DateAdded" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
									<t:commandLink id="dateAddedCommandLink2"
										actionListener="#{AttachmentControllerBean.sortCaseAttachmentsInfo}"
										rendered="#{AttachmentDataBean.imageRender != 'dateAddedCommandLink2'}">
										<m:div title="for descending" styleClass="sort_desc" />
										<f:attribute name="columnName" value="Attach_DateAdded" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
								</h:panelGroup>
							</h:panelGrid>
						</f:facet>
						<h:outputText id="PRGCMGTOT207"
							value="#{attachSpanResult.dateAdded}" />
					</h:column>
					<h:column id="addedBycolumn2">
						<f:facet name="header">
							<h:panelGrid id="PRGCMGTPGD32" columns="2">
								<h:outputLabel id="PRGCMGTOLL127"
									value="#{msg['label.attach.addedBy']}"
									for="addedByCommandLink1" />
								<h:panelGroup id="PRGCMGTPGP32">
									<t:commandLink id="addedByCommandLink1"
										actionListener="#{AttachmentControllerBean.sortCaseAttachmentsInfo}"
										rendered="#{AttachmentDataBean.imageRender != 'addedByCommandLink1'}">
										<m:div title="for ascending" styleClass="sort_asc" />
										<f:attribute name="columnName" value="Attach_AddedBy" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
									<t:commandLink id="addedByCommandLink2"
										actionListener="#{AttachmentControllerBean.sortCaseAttachmentsInfo}"
										rendered="#{AttachmentDataBean.imageRender != 'addedByCommandLink2'}">
										<m:div title="for descending" styleClass="sort_desc" />
										<f:attribute name="columnName" value="Attach_AddedBy" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
								</h:panelGroup>
							</h:panelGrid>
						</f:facet>
						<h:outputText id="PRGCMGTOT208"
							value="#{attachSpanResult.addedBy}" />

					</h:column>
					<h:column id="fileNamecolumn3">
						<f:facet name="header">
							<h:panelGrid id="PRGCMGTPGD33" columns="2">
								<h:outputLabel id="PRGCMGTOLL128"
									value="#{msg['label.attach.fileName']}"
									for="fileNameCommandLink1" />
								<h:panelGroup id="PRGCMGTPGP33">
									<t:commandLink id="fileNameCommandLink1"
										actionListener="#{AttachmentControllerBean.sortCaseAttachmentsInfo}"
										rendered="#{AttachmentDataBean.imageRender != 'fileNameCommandLink1'}">
										<m:div title="for ascending" styleClass="sort_asc" />
										<f:attribute name="columnName" value="Attach_FileName" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
									<t:commandLink id="fileNameCommandLink2"
										actionListener="#{AttachmentControllerBean.sortCaseAttachmentsInfo}"
										rendered="#{AttachmentDataBean.imageRender != 'fileNameCommandLink2'}">
										<m:div title="for descending" styleClass="sort_desc" />
										<f:attribute name="columnName" value="Attach_FileName" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
								</h:panelGroup>
							</h:panelGrid>
						</f:facet>
						<h:outputText id="PRGCMGTOT209"
							value="#{attachSpanResult.fileName}" />
					</h:column>
					<h:column id="descriptioncolumn4">
						<f:facet name="header">
							<h:panelGrid id="PRGCMGTPGD34" columns="2">
								<h:outputLabel id="PRGCMGTOLL129"
									value="#{msg['label.attach.description']}"
									for="descriptionCommandLink1" />
								<h:panelGroup id="PRGCMGTPGP34">
									<t:commandLink id="descriptionCommandLink1"
										styleClass="textNone"
										actionListener="#{AttachmentControllerBean.sortCaseAttachmentsInfo}"
										rendered="#{AttachmentDataBean.imageRender != 'descriptionCommandLink1'}">
										<m:div title="for ascending" styleClass="sort_asc" />
										<f:attribute name="columnName" value="Attach_Desc" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
									<t:commandLink id="descriptionCommandLink2"
										styleClass="textNone"
										actionListener="#{AttachmentControllerBean.sortCaseAttachmentsInfo}"
										rendered="#{AttachmentDataBean.imageRender != 'descriptionCommandLink2'}">
										<m:div title="for descending" styleClass="sort_desc" />
										<f:attribute name="columnName" value="Attach_Desc" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
								</h:panelGroup>
							</h:panelGrid>
						</f:facet>
						<h:outputText id="PRGCMGTOT210"
							value="#{attachSpanResult.description}" />
					</h:column>
					<%-- Snippet for  displaying  No Data --%>
					<f:facet name="footer">
						<m:div id="nodata" rendered="#{AttachmentDataBean.noData}"
							align="center">
							<h:outputText id="PRGCMGTOT211"
								value="#{msg['label.attach.noData']}"></h:outputText>
						</m:div>
					</f:facet>
				</h:dataTable>
				<t:dataScroller id="CMGTTOMDS6" for="attachmentsTable"
					paginator="true" onclick="flagWarn=false;"
					paginatorActiveColumnStyle='font-weight:bold;'
					paginatorMaxPages="3" immediate="false" pageCountVar="pageCount"
					pageIndexVar="pageIndex" firstRowIndexVar="firstRowIndex"
					lastRowIndexVar="lastRowIndex" rowsCountVar="rowsCount"
					styleClass="scroller">
					<f:facet name="previous">
						<h:outputText id="PRGCMGTOT212" styleClass="underline"
							value=" #{ref['label.ref.lt']} " rendered="#{pageIndex > 1}"></h:outputText>
					</f:facet>
					<f:facet name="next">
						<h:outputText id="PRGCMGTOT213" styleClass="underline"
							value=" #{ref['label.ref.gt']} "
							rendered="#{pageIndex < pageCount}"></h:outputText>
					</f:facet>
					<h:outputText id="PRGCMGTOT214" rendered="#{rowsCount > 0}"
						value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
						styleClass="scrollerBold" />
				</t:dataScroller>
				<m:br />

				<m:div styleClass="moreInfoBar">
					<m:div styleClass="infoTitle">
						<h:outputText id="PRGCMGTOT215"
							value="#{msg['label.attach.addAttachment']}" />
					</m:div>
					<m:div styleClass="infoActions">
						<t:commandLink id="PRGCMGTCL42"
							action="#{AttachmentControllerBean.uploadFile}"
							onmousedown="javascript:flagWarn=false;">
							<h:outputText id="PRGCMGTOT216"
								value="#{msg['label.attach.save']}" styleClass="strong" />
						</t:commandLink>
						<h:outputText id="PRGCMGTOT217" escape="false"
							value="#{ref['label.ref.linkSeperator']}" />
						<%-- Added for Defect ESPRD00249328 
						<h:commandLink id="PRGCMGTCL43" action="">
							<h:outputText id="PRGCMGTOT218" value="#{msg['label.attach.searchDocRep']}"								styleClass="strong" />
						</h:commandLink>--%>
						<%-- Commented for Defect ESPRD00343824 By Infinite --%>
						<%-- Added for Defect ESPRD367960 By Infinite --%>
						<%--<h:commandLink id="PRGCMGTCL44"							action="#{AttachmentControllerBean.openAddDocumentRepository}">
							<h:outputText id="PRGCMGTOT219" value="#{msg['label.attach.searchDocRep']}" />
							<f:param name="com.ibm.portal.propertybroker.action"
								value="SendEDMSSearchCriteriaDataType" />
						</h:commandLink>--%>
						<%-- Ended for Defect ESPRD367960 By Infinite --%>
						<%-- Commented end for Defect ESPRD00343824 By Infinite --%>
						<%-- End --%>
						<t:commandLink id="PRGCMGTCL45"
							onmousedown="javascript:flagWarn=false;"
							action="#{AttachmentControllerBean.resetAddAttachment}">
							<h:outputText id="PRGCMGTOT220"
								value="#{msg['label.attach.reset']}" />
						</t:commandLink>
						<h:outputText id="PRGCMGTOT221" escape="false"
							value="#{ref['label.ref.linkSeperator']}" />
							<%-- style removed for the defect ESPRD00707689 --%>
						<%--<h:commandLink id="PRGCMGTCB2"
							action="#{AttachmentControllerBean.cancelAttachments}"
							value="#{msg['label.attach.cancel']}" />--%>
							
							<%--Modified for the Defect ID : ESPRD00778837 for reset link to work--%>
							<h:commandButton id="PRGCMGTCB2"
							action="#{AttachmentControllerBean.cancelAttachments}"
							value="#{msg['label.attach.cancel']}" 
							style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:50px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal;position:relative;top:0px"
							/>
							
							
							</m:div>
					<%--  Commented for defect  ESPRD00249328 
							<h:outputText id="PRGCMGTOT222" value="#{msg['label.attach.cancel']}" />  --%>
					<%-- Added for Defect ESPRD00249328 --%>
					<%--	<h:outputText id="PRGCMGTOT223" value="#{msg['label.attach.close']}" />--%>
					<%-- End--%>
				</m:div>
				<%--Comments : Modified style class as color : RED : ESPRD00718265--%>
				<m:div styleClass="moreInfo">
					<m:div styleClass="padb">
						<m:div>
							<h:messages layout="table" showDetail="true" id="crAtchmntAddedMsg" 
							 			showSummary="false"	style="color: red">
							</h:messages>
						</m:div>	
						<m:div styleClass="color: red"
							rendered="#{AttachmentDataBean.showSucessMessage}">
							<h:outputText id="PRGCMGTOT224"
								value="#{ent['label-sw-success']}" />
						</m:div>
						
						 <%-- <m:div styleClass="msgBox"
							rendered="#{AttachmentDataBean.showUnSucessMessage}">
							<h:outputText id="PRGCMGTOT124"
								value="User is not having Privileges to attach any document" />
						</m:div> --%>
						<%--Comments : Modified style class as color : RED : ESPRD00718265--%>
						<m:div styleClass="color: red"
							rendered="#{AttachmentDataBean.showUnSucessMessage}">
							<h:outputText id="PRGCMGTOT124"
								value="#{msg['unsuccess.logCase.save']}" />
						</m:div>
						
						<m:div styleClass="width:100%">
							<m:table id="PROVIDERMMT20120731164811140">
								<m:tr>
									<m:td>
										<m:div styleClass="padb">
											<m:span styleClass="required">
												<h:outputText id="PRGCMGTOT225"
													value="#{ref['label.ref.reqSymbol']}" />
											</m:span>
										<h:outputLabel id="PRGCMGTOLL98508M" for="fileupload1">
											<h:outputText id="PRGCMGTOT226"
												value="#{msg['label.attach.file']}" styleClass="outputLabel" />
										</h:outputLabel>
											<m:br />
											<hx:fileupload styleClass="fileupload" id="fileupload1"
												value="#{AttachmentDataBean.attachmentVO.file1}">
												<hx:fileProp name="fileName"
													value="#{AttachmentDataBean.attachmentVO.fileName}" />
												<hx:fileProp name="contentType" />
											</hx:fileupload>
										</m:div>
										<%-- <m:br />--%>
										<h:message id="PRGCMGTM47" for="fileupload1" 
											styleClass="color: red" />
									</m:td>
									<m:td>
										<h:outputText id="PRGCMGTOT227" escape="false"
											value="#{ref['label.ref.singleSpace']}" />
										<h:outputText id="PRGCMGTOT228" escape="false"
											value="#{ref['label.ref.singleSpace']}" />
									</m:td>
								</m:tr>
								<m:tr>
									<m:td>
										<m:div styleClass="padb">
											<m:span styleClass="required">
												<h:outputText id="PRGCMGTOT229"
													value="#{ref['label.ref.reqSymbol']}" />
											</m:span>
											<h:outputLabel id="PRGCMGTOLL130" for="attaddDesc"
												value="#{msg['label.attach.description']}"
												styleClass="outputLabel" />
											<m:br />
											<h:inputText id="attaddDesc" size="40"
												value="#{AttachmentDataBean.attachmentVO.description}" />
											<m:br />
											<h:message id="PRGCMGTM48" for="attaddDesc" showDetail="true"
												styleClass="color: red" />
										</m:div>
									</m:td>
								</m:tr>
								<m:tr>
									<m:td colspan="2">
										<m:div styleClass="padb">
											<m:span styleClass="required">
												<h:outputText id="PRGCMGTOT230"
													value="#{ref['label.ref.reqSymbol']}" />
											</m:span>
											<h:outputLabel id="PRGCMGTOLL131" for="edmsdepartlevel">
												<h:outputText value="#{msg['label.edms.wrkunitlevel']}"
													id="edmsdepartmentlevel" />
											</h:outputLabel>
											<m:br />
											<h:selectOneMenu id="edmsdepartlevel"
												value="#{AttachmentDataBean.attachmentVO.edmsWrkUnitLevel}">
												<f:selectItem itemValue="" itemLabel="" />
												<f:selectItems
													value="#{AttachmentDataBean.edmsWrkUnitLevelList }" />
											</h:selectOneMenu>
											<m:br />
											<h:message id="PRGCMGTM49" for="edmsdepartlevel"
												showDetail="true" styleClass="color: red" />
										</m:div>
									</m:td>
									<m:td>
										<m:div styleClass="padb">
											<m:span styleClass="required">
												<h:outputText id="PRGCMGTOT231"
													value="#{ref['label.ref.reqSymbol']}" />
											</m:span>
											<h:outputLabel id="PRGCMGTOLL132" for="edmsdoctype">
												<h:outputText value="#{msg['label.edms.docType']}"
													id="edmsdocumenttype" />
											</h:outputLabel>
											<m:br />
											<h:selectOneMenu id="edmsdoctype"
												value="#{AttachmentDataBean.attachmentVO.edmsDocType}">
												<f:selectItem itemValue="" itemLabel="" />
												<f:selectItems value="#{AttachmentDataBean.edmsDocTypeList}" />
											</h:selectOneMenu>
											<m:br />
											<h:message id="PRGCMGTM50" for="edmsdoctype"
												showDetail="true" styleClass="color: red" />
										</m:div>
									</m:td>
								</m:tr>
							</m:table>
						</m:div>
					</m:div>
				</m:div>
			</hx:scriptCollector>
		</h:form>
	</m:section>


	<%-- var thisForm : stores the form reference. Form reference is use to get  the computed  role stored in hidden form field.
         FlagRole : Indicator to store role information .Stores 'False' for inquire only users and 'True' for other user.
	--%>
	<script type="text/JavaScript">

   var thisForm = 'view<portlet:namespace/>:crAttachmentBrowse';

   flagRole =((typeof (document.getElementById(thisForm+': controlRequiredForAddAttachment) !=  'undefined')) && document.getElementById(thisForm+':controlRequiredForAddAttachment) != null ?  document.getElementById(thisForm+':controlRequiredForAddAttachment).value:true);

</script>
	</body>
</f:view>

