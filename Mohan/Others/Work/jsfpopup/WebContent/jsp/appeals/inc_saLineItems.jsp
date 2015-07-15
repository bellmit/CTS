<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<portlet:defineObjects />
<script type="text/javascript">
function listEnable(Id){
	//var formId = 'view<portlet:namespace/>:';
	//alert(formId+':saLineAppStatus_list');
	
	 var list1_Id = Id.replace(/include_ch/, "saLineAppStatus_list");
	 var list1_Id1 = Id.replace(/include_ch/, "saLineAppResult_list");
	
if(document.getElementById(Id).checked) {		
		document.getElementById(list1_Id).disabled=false;
		document.getElementById(list1_Id1).disabled=false;
	}
	else {	
		document.getElementById(list1_Id).disabled=true;
		document.getElementById(list1_Id1).disabled=true;
	}
}
</script>

<m:div styleClass="clear">
</m:div>
<m:div>
	<m:br />
	<m:br />
</m:div>
<m:section id="PROVIDERMMS2012073116481183" styleClass="otherbg">

	<m:legend>
		<h:outputText id="PRGCMGTOT132" value="#{msg['label.appeals.salineitems']}" />
	</m:legend>

	<m:table id="PROVIDERMMT2012073116481184" styleClass="tableBar" width="100%">
		<m:tr>
			<m:td styleClass="tableTitle">
				<h:outputText id="PRGCMGTOT133" value="#{msg['title.appeals.saLineitemdetailTable']}" />
			</m:td>
		</m:tr>
	</m:table>

	<h:dataTable id="memberAppealSAItemTable" border="1" cellpadding="0"		cellspacing="0" columnClasses="columnClass" headerClass="headerClass"		rows="5" footerClass="footerClass" rowClasses="row_alt,rowone"		styleClass="dataTable" width="100%" var="memberAppealSAItemResult"		value="#{appealDataBean.appealVO.listSALineItemsVO}"		rendered="#{appealDataBean.saLineItemDataFlag}">
		<h:column id="saMember_Include">
			<f:facet name="header">
				<h:panelGrid id="PRGCMGTPGD14" columns="2">
					<h:outputLabel id="PRGCMGTOLL109" for="saDetails_Include"						value="#{msg['label.appeals.include']}" />
					<h:panelGroup id="PRGCMGTPGP14">
						<t:div id="saLineItemsSortDivID0">
						<t:commandLink id="saDetails_Include" styleClass="clStyle"							onmousedown="javascript:flagWarn=false;"							actionListener="#{appealControllerBean.sortSADataItemForMember}"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_Include'}">
							<h:graphicImage id="PROVIDERGI2012073116481185" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								url="/images/x.gif" styleClass="sort_asc" width="8" />
							<f:attribute name="columnName" value="include" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
						</t:div>
						<t:div id="saLineItemsSortDivID1">
						<t:commandLink id="saDetails_Include1" styleClass="clStyle"							onmousedown="javascript:flagWarn=false;"							actionListener="#{appealControllerBean.sortSADataItemForMember}"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_Include'}">
							<h:graphicImage id="PROVIDERGI2012073116481186" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								url="/images/x.gif" styleClass="sort_desc" width="8" />
							<f:attribute name="columnName" value="include" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
						</t:div>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>
			<h:outputLabel id="checkboxSAColumnLabel"  for="include_ch" styleClass="hide" value="#{msg['label.appeals.include']}" ></h:outputLabel>
			<h:selectBooleanCheckbox id="include_ch"				value="#{memberAppealSAItemResult.include}"				onchange="listEnable(this.id)" />
		</h:column>

		<h:column id="saMember_LineNumber">
			<f:facet name="header">
				<h:panelGrid id="PRGCMGTPGD15" columns="2">
					<h:outputLabel id="PRGCMGTOLL110" for="saDetails_LineNumber"						value="#{msg['label.appeals.line']}" />
					<h:panelGroup id="PRGCMGTPGP15">
						<t:div id="saLineItemsSortDivID2">
						<t:commandLink id="saDetails_LineNumber" styleClass="clStyle"							onmousedown="javascript:flagWarn=false;"							actionListener="#{appealControllerBean.sortSADataItemForMember}"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_LineNumber'}">
							<h:graphicImage id="PROVIDERGI2012073116481187" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								url="/images/x.gif" styleClass="sort_asc" width="8" />
							<f:attribute name="columnName" value="serviceLineNum" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
						</t:div>
						<t:div id="saLineItemsSortDivID3">
						<t:commandLink id="saDetails_LineNumber1" styleClass="clStyle"							onmousedown="javascript:flagWarn=false;"							actionListener="#{appealControllerBean.sortSADataItemForMember}"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_LineNumber1'}">
							<h:graphicImage id="PROVIDERGI2012073116481188" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								url="/images/x.gif" styleClass="sort_desc" width="8" />
							<f:attribute name="columnName" value="serviceLineNum" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
						</t:div>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>
			<h:outputText id="PRGCMGTOT134" value="#{memberAppealSAItemResult.lineNumber}" />
		</h:column>
		<h:column id="saMember_saLineStatus">
			<f:facet name="header">
				<h:panelGrid id="PRGCMGTPGD16" columns="2">
					<h:outputLabel id="PRGCMGTOLL111" for="saDetails_saLineStatus"						value="#{msg['label.appeals.saLineStatus']}" />
					<h:panelGroup id="PRGCMGTPGP16">
						<t:div id="saLineItemsSortDivID4">
						<t:commandLink id="saDetails_saLineStatus" styleClass="clStyle"							actionListener="#{appealControllerBean.sortSADataItemForMember}"							onmousedown="javascript:flagWarn=false;"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_saLineStatus'}">
							<h:graphicImage id="PROVIDERGI2012073116481189" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								url="/images/x.gif" styleClass="sort_asc" width="8" />
							<f:attribute name="columnName" value="serviceStatus" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
						</t:div>
						<t:div id="saLineItemsSortDivID5">
						<t:commandLink id="saDetails_saLineStatus1" styleClass="clStyle"							actionListener="#{appealControllerBean.sortSADataItemForMember}"							onmousedown="javascript:flagWarn=false;"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_saLineStatus1'}">
							<h:graphicImage id="PROVIDERGI2012073116481190" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								url="/images/x.gif" styleClass="sort_desc" width="8" />
							<f:attribute name="columnName" value="serviceStatus" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
						</t:div>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>

			<h:outputText id="PRGCMGTOT135" value="#{memberAppealSAItemResult.saLineStatus}" />
		</h:column>



		<h:column id="saMember_saLineAppStatus">
			<f:facet name="header">
				<h:panelGrid id="PRGCMGTPGD17" columns="2">
					<h:outputLabel id="PRGCMGTOLL112" for="saDetails_saLineAppStatus"						value="#{msg['label.appeals.saLineAppStatus']}" />
					<h:panelGroup id="PRGCMGTPGP17">
						<t:div id="saLineItemsSortDivID6">
						<t:commandLink id="saDetails_saLineAppStatus" styleClass="clStyle"							actionListener="#{appealControllerBean.sortSADataItemForMember}"							onmousedown="javascript:flagWarn=false;"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_saLineAppStatus'}">
							<h:graphicImage id="PROVIDERGI2012073116481191" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								url="/images/x.gif" styleClass="sort_asc" width="8" />
							<f:attribute name="columnName" value="saLineAppStatus" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
						</t:div>
						<t:div id="saLineItemsSortDivID7">
						<t:commandLink id="saDetails_saLineAppStatus1"							styleClass="clStyle" onmousedown="javascript:flagWarn=false;"							actionListener="#{appealControllerBean.sortSADataItemForMember}"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_saLineAppStatus1'}">
							<h:graphicImage id="PROVIDERGI2012073116481192" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								url="/images/x.gif" styleClass="sort_desc" width="8" />
							<f:attribute name="columnName" value="saLineAppStatus" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
						</t:div>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>
			<h:outputLabel id="checkboxSAColumnLabel1"  for="saLineAppStatus_list" styleClass="hide" value="#{msg['label.appeals.saLineAppStatus']}" ></h:outputLabel>
			<h:selectOneMenu id="saLineAppStatus_list"				onchange="javascript:selectOne=true;javascript:flagWarn=false;"				value="#{memberAppealSAItemResult.saLineAppStatusValue}">
				<f:selectItems value="#{memberAppealSAItemResult.saLineAppStatus}" />
			</h:selectOneMenu>

		</h:column>



		<h:column id="saMember_saLineAppResult">
			<f:facet name="header">
				<h:panelGrid id="PRGCMGTPGD18" columns="2">
					<h:outputLabel id="PRGCMGTOLL113" for="saDetails_saLineAppResult"						value="#{msg['label.appeals.saLineAppResult']}" />
					<h:panelGroup id="PRGCMGTPGP18">
						<t:div id="saLineItemsSortDivID8">
						<t:commandLink id="saDetails_saLineAppResult" styleClass="clStyle"							actionListener="#{appealControllerBean.sortSADataItemForMember}"							onmousedown="javascript:flagWarn=false;"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_saLineAppResult'}">
							<h:graphicImage id="PROVIDERGI2012073116481193" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								url="/images/x.gif" styleClass="sort_asc" width="8" />
							<f:attribute name="columnName" value="saLineAppResult" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
						</t:div>
						<t:div id="saLineItemsSortDivID9">
						<t:commandLink id="saDetails_saLineAppResult1"							styleClass="clStyle" onmousedown="javascript:flagWarn=false;"							actionListener="#{appealControllerBean.sortSADataItemForMember}"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_saLineAppResult1'}">
							<h:graphicImage id="PROVIDERGI2012073116481194" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								url="/images/x.gif" styleClass="sort_desc" width="8" />
							<f:attribute name="columnName" value="saLineAppResult" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
						</t:div>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>
			<h:outputLabel id="checkboxSAColumnLabel2"  for="saLineAppResult_list" styleClass="hide" value="#{msg['label.appeals.saLineAppResult']}" ></h:outputLabel>
			<h:selectOneMenu id="saLineAppResult_list"				onchange="javascript:selectOne=true;javascript:flagWarn=false;"				value="#{memberAppealSAItemResult.saLineAppResultValue}">
				<f:selectItems value="#{memberAppealSAItemResult.saLineAppResult}" />
			</h:selectOneMenu>

		</h:column>




		<h:column id="saMember_ServiceTyCd">
			<f:facet name="header">
				<h:panelGrid id="PRGCMGTPGD19" columns="2">
					<h:outputLabel id="PRGCMGTOLL114" for="saDetails_ServiceTyCd"						value="#{msg['label.appeals.svcTypeCode']}" />
					<h:panelGroup id="PRGCMGTPGP19">
						<t:div id="saLineItemsSortDivID10">
						<t:commandLink id="saDetails_ServiceTyCd" styleClass="clStyle"							actionListener="#{appealControllerBean.sortSADataItemForMember}"							onmousedown="javascript:flagWarn=false;"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_ServiceTyCd'}">
							<h:graphicImage id="PROVIDERGI2012073116481195" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								url="/images/x.gif" styleClass="sort_asc" width="8" />
							<f:attribute name="columnName" value="serviceTyCd" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
						</t:div>
						<t:div id="saLineItemsSortDivID11">
						<t:commandLink id="saDetails_ServiceTyCd1" styleClass="clStyle"							actionListener="#{appealControllerBean.sortSADataItemForMember}"							onmousedown="javascript:flagWarn=false;"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_ServiceTyCd1'}">
							<h:graphicImage id="PROVIDERGI2012073116481196" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								url="/images/x.gif" styleClass="sort_desc" width="8" />
							<f:attribute name="columnName" value="serviceTyCd" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
						</t:div>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>

			<h:outputText id="PRGCMGTOT136" value="#{memberAppealSAItemResult.svcTypeCode}" />
		</h:column>


		<h:column id="saMember_ServiceFrmCd">
			<f:facet name="header">
				<h:panelGrid id="PRGCMGTPGD20" columns="2">
					<h:outputLabel id="PRGCMGTOLL115" for="saDetails_ServiceFrmCd"						value="#{msg['label.appeals.svcFromCode']}" />
					<h:panelGroup id="PRGCMGTPGP20">
						<t:div id="saLineItemsSortDivID12">
						<t:commandLink id="saDetails_ServiceFrmCd" styleClass="clStyle"							actionListener="#{appealControllerBean.sortSADataItemForMember}"							onmousedown="javascript:flagWarn=false;"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_ServiceFrmCd'}">
							<h:graphicImage id="PROVIDERGI2012073116481197" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								url="/images/x.gif" styleClass="sort_asc" width="8" />
							<f:attribute name="columnName" value="serviceFrmCd" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
						</t:div>
						<t:div id="saLineItemsSortDivID13">
						<t:commandLink id="saDetails_ServiceFrmCd1" styleClass="clStyle"							actionListener="#{appealControllerBean.sortSADataItemForMember}"							onmousedown="javascript:flagWarn=false;"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_ServiceFrmCd1'}">
							<h:graphicImage id="PROVIDERGI2012073116481198" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								url="/images/x.gif" styleClass="sort_desc" width="8" />
							<f:attribute name="columnName" value="serviceFrmCd" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
						</t:div>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>

			<h:outputText id="PRGCMGTOT137" value="#{memberAppealSAItemResult.svcFromCode}" />
		</h:column>

		<h:column id="saMember_ServiceToCd">
			<f:facet name="header">
				<h:panelGrid id="PRGCMGTPGD21" columns="2">
					<h:outputLabel id="PRGCMGTOLL116" for="saDetails_ServiceToCd"						value="#{msg['label.appeals.svcToCode']}" />
					<h:panelGroup id="PRGCMGTPGP21">
						<t:div id="saLineItemsSortDivID14">
						<t:commandLink id="saDetails_ServiceToCd" styleClass="clStyle"							actionListener="#{appealControllerBean.sortSADataItemForMember}"							onmousedown="javascript:flagWarn=false;"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_ServiceToCd'}">
							<h:graphicImage id="PROVIDERGI2012073116481199" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								url="/images/x.gif" styleClass="sort_asc" width="8" />
							<f:attribute name="columnName" value="serviceToCd" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
						</t:div>
						<t:div id="saLineItemsSortDivID15">
						<t:commandLink id="saDetails_ServiceToCd1" styleClass="clStyle"							actionListener="#{appealControllerBean.sortSADataItemForMember}"							onmousedown="javascript:flagWarn=false;"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_ServiceToCd1'}">
							<h:graphicImage id="PROVIDERGI20120731164811100" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								url="/images/x.gif" styleClass="sort_desc" width="8" />
							<f:attribute name="columnName" value="serviceToCd" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
						</t:div>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>

			<h:outputText id="PRGCMGTOT138" value="#{memberAppealSAItemResult.svcToCode}" />
		</h:column>
		
		<h:column id="saMember_reqAmount">
			<f:facet name="header">
				<h:panelGrid id="PRGCMGTPGD22" columns="2">
					<h:outputLabel id="PRGCMGTOLL117" for="saDetails_reqAmount"						value="#{msg['label.appeals.reqAmount']}" />
					<h:panelGroup id="PRGCMGTPGP22">
						<t:div id="saLineItemsSortDivID16">
						<t:commandLink id="saDetails_reqAmount" styleClass="clStyle"							onmousedown="javascript:flagWarn=false;"							actionListener="#{appealControllerBean.sortSADataItemForMember}"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_reqAmount'}">
							<h:graphicImage id="PROVIDERGI20120731164811101" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								url="/images/x.gif" styleClass="sort_asc" width="8" />
							<f:attribute name="columnName" value="serviceReqAmt" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
						</t:div>
						<t:div id="saLineItemsSortDivID17">
						<t:commandLink id="saDetails_reqAmount1" styleClass="clStyle"							onmousedown="javascript:flagWarn=false;"							actionListener="#{appealControllerBean.sortSADataItemForMember}"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_reqAmount1'}">
							<h:graphicImage id="PROVIDERGI20120731164811102" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								url="/images/x.gif" styleClass="sort_desc" width="8" />
							<f:attribute name="columnName" value="serviceReqAmt" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
						</t:div>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>
			<h:outputText id="PRGCMGTOT139" value="#{memberAppealSAItemResult.reqAmount}" />
		</h:column>

		<h:column id="saMember_reqUnits">
			<f:facet name="header">
				<h:panelGrid id="PRGCMGTPGD23" columns="2">
					<h:outputLabel id="PRGCMGTOLL118" for="saDetails_reqUnits"						value="#{msg['label.appeals.reqUnit']}" />
					<h:panelGroup id="PRGCMGTPGP23">
						<t:div id="saLineItemsSortDivID18">
						<t:commandLink id="saDetails_reqUnits" styleClass="clStyle"							onmousedown="javascript:flagWarn=false;"							actionListener="#{appealControllerBean.sortSADataItemForMember}"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_reqUnits'}">
							<h:graphicImage id="PROVIDERGI20120731164811103" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								url="/images/x.gif" styleClass="sort_asc" width="8" />
							<f:attribute name="columnName" value="serviceReqUnit" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
						</t:div>
						<t:div id="saLineItemsSortDivID19">
						<t:commandLink id="saDetails_reqUnits1" styleClass="clStyle"							onmousedown="javascript:flagWarn=false;"							actionListener="#{appealControllerBean.sortSADataItemForMember}"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_reqUnits1'}">
							<h:graphicImage id="PROVIDERGI20120731164811104" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								url="/images/x.gif" styleClass="sort_desc" width="8" />
							<f:attribute name="columnName" value="serviceReqUnit" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
						</t:div>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>

			<h:outputText id="PRGCMGTOT140" value="#{memberAppealSAItemResult.reqUnits}" />
		</h:column>

		<h:column id="saMember_reqRate">
			<f:facet name="header">
				<h:panelGrid id="PRGCMGTPGD24" columns="2">
					<h:outputLabel id="PRGCMGTOLL119" for="saDetails_reqRate"						value="#{msg['label.appeals.reqRate']}" />
					<h:panelGroup id="PRGCMGTPGP24">
						<t:commandLink id="saDetails_reqRate" styleClass="clStyle"							onmousedown="javascript:flagWarn=false;"							actionListener="#{appealControllerBean.sortSADataItemForMember}"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_reqRate'}">
							<h:graphicImage id="PROVIDERGI20120731164811105" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								url="/images/x.gif" styleClass="sort_asc" width="8" />
							<f:attribute name="columnName" value="serviceReqRate" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>

						<t:commandLink id="saDetails_reqRate1" styleClass="clStyle"							onmousedown="javascript:flagWarn=false;"							actionListener="#{appealControllerBean.sortSADataItemForMember}"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_reqRate1'}">
							<h:graphicImage id="PROVIDERGI20120731164811106" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								url="/images/x.gif" styleClass="sort_desc" width="8" />
							<f:attribute name="columnName" value="serviceReqRate" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>

			<h:outputText id="PRGCMGTOT141" value="#{memberAppealSAItemResult.reqRate}" />
		</h:column>

		<h:column id="saMember_ReqBegDt">
			<f:facet name="header">
				<h:panelGrid id="PRGCMGTPGD25" columns="2">
					<h:outputLabel id="PRGCMGTOLL120" for="saDetails_ReqBegDt"						value="#{msg['label.appeals.reqBeginDate']}" />
					<h:panelGroup id="PRGCMGTPGP25">
						<t:commandLink id="saDetails_ReqBegDt" styleClass="clStyle"							onmousedown="javascript:flagWarn=false;"							actionListener="#{appealControllerBean.sortSADataItemForMember}"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_ReqBegDt'}">
							<h:graphicImage id="PROVIDERGI20120731164811107" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								url="/images/x.gif" styleClass="sort_asc" width="8" />
							<f:attribute name="columnName" value="serviceReqBegDt" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>

						<t:commandLink id="saDetails_ReqBegDt1" styleClass="clStyle"							onmousedown="javascript:flagWarn=false;"							actionListener="#{appealControllerBean.sortSADataItemForMember}"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_ReqBegDt1'}">
							<h:graphicImage id="PROVIDERGI20120731164811108" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								url="/images/x.gif" styleClass="sort_desc" width="8" />
							<f:attribute name="columnName" value="serviceReqBegDt" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>
			<h:outputText id="PRGCMGTOT142" value="#{memberAppealSAItemResult.reqBeginDate}">
				<f:convertDateTime pattern="MM/dd/yyyy" />
			</h:outputText>
		</h:column>


		<h:column id="saMember_ReqEndDt">
			<f:facet name="header">
				<h:panelGrid id="PRGCMGTPGD26" columns="2">
					<h:outputLabel id="PRGCMGTOLL121" for="saDetails_ReqEndDt"						value="#{msg['label.appeals.reqEndDate']}" />
					<h:panelGroup id="PRGCMGTPGP26">
						<t:commandLink id="saDetails_ReqEndDt" styleClass="clStyle"							onmousedown="javascript:flagWarn=false;"							actionListener="#{appealControllerBean.sortSADataItemForMember}"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_ReqEndDt'}">
							<h:graphicImage id="PROVIDERGI20120731164811109" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								url="/images/x.gif" styleClass="sort_asc" width="8" />
							<f:attribute name="columnName" value="serviceReqEndDt" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>

						<t:commandLink id="saDetails_ReqEndDt1" styleClass="clStyle"							onmousedown="javascript:flagWarn=false;"							actionListener="#{appealControllerBean.sortSADataItemForMember}"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_ReqEndDt1'}">
							<h:graphicImage id="PROVIDERGI20120731164811110" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								url="/images/x.gif" styleClass="sort_desc" width="8" />
							<f:attribute name="columnName" value="serviceReqEndDt" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>
			<h:outputText id="PRGCMGTOT143" value="#{memberAppealSAItemResult.reqEndDate }">
				<f:convertDateTime pattern="MM/dd/yyyy" />
			</h:outputText>
		</h:column>


	</h:dataTable>

	<t:dataScroller  id="CMGTTOMDS3" for="memberAppealSAItemTable" paginator="true"
		paginatorActiveColumnStyle='font-weight:bold;' paginatorMaxPages="3"
		immediate="false" pageCountVar="pageCount" pageIndexVar="pageIndex"
		firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
		rowsCountVar="rowsCount" styleClass="datascrollerStyle"  onclick="javascript:flagWarn=false;">
		<f:facet name="previous">
			<h:outputText id="PRGCMGTOT144" styleClass="underline"				value="#{msg['label.appeals.lt']}" rendered="#{pageIndex > 1}"></h:outputText>
		</f:facet>
		<f:facet name="next">
			<h:outputText id="PRGCMGTOT145" styleClass="underline"				value="#{msg['label.appeals.gt']}"				rendered="#{pageIndex < pageCount}"></h:outputText>
		</f:facet>
		<h:outputText id="PRGCMGTOT146" rendered="#{rowsCount > 0}"
			value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
			styleClass="floatLeft" />
	</t:dataScroller>


	<h:dataTable var="dummymemberAppealSAItemTable"		rendered="#{!appealDataBean.showSACompFlag}" styleClass="dataTable"		cellspacing="0" width="100%" border="1" headerClass="tableHead"		rowClasses="rowone,row_alt" id="dummymemberAppealSAItemTable">

		<t:column id="dummySAItemTabledatacolumn1">
			<f:facet name="header">
				<h:outputText id="PRGCMGTOT147" value="#{msg['label.appeals.include']}" />
			</f:facet>
		</t:column>
		<t:column id="dummySAItemTabledatacolumn32">
			<f:facet name="header">
				<h:outputText id="PRGCMGTOT148" value="#{msg['label.appeals.line']}" />
			</f:facet>
		</t:column>
		<t:column id="dummySAItemTabledatacolumn31">
			<f:facet name="header">
				<h:outputText id="PRGCMGTOT149" value="#{msg['label.appeals.saLineStatus']}" />
			</f:facet>
		</t:column>

		<t:column id="dummySAItemTabledatacolumn23">
			<f:facet name="header">
				<h:outputText id="PRGCMGTOT150" value="#{msg['label.appeals.saLineAppStatus']}" />
			</f:facet>
		</t:column>
		<t:column id="dummySAItemTabledatacolumn24">
			<f:facet name="header">
				<h:outputText id="PRGCMGTOT151" value="#{msg['label.appeals.saLineAppResult']}" />
			</f:facet>
		</t:column>

		<t:column id="dummySAItemTabledatacolumn2">
			<f:facet name="header">
				<h:outputText id="PRGCMGTOT152" value="#{msg['label.appeals.svcTypeCode']}" />
			</f:facet>
		</t:column>
		<t:column id="dummySAItemTabledatacolumn3">
			<f:facet name="header">
				<h:outputText id="PRGCMGTOT153" value="#{msg['label.appeals.svcFromCode']}" />
			</f:facet>
		</t:column>
		<t:column id="dummySAItemTabledatacolumn4">
			<f:facet name="header">
				<h:outputText id="PRGCMGTOT154" value="#{msg['label.appeals.svcToCode']}" />
			</f:facet>
		</t:column>
		<t:column id="dummySAItemTabledatacolumn5">
			<f:facet name="header">
				<h:outputText id="PRGCMGTOT155" value="#{msg['label.appeals.reqAmount']}" />
			</f:facet>
		</t:column>
		<t:column id="dummySAItemTabledatacolumn6">
			<f:facet name="header">
				<h:outputText id="PRGCMGTOT156" value="#{msg['label.appeals.reqUnit']}" />
			</f:facet>
		</t:column>
		<t:column id="dummySAItemTabledatacolumn7">
			<f:facet name="header">
				<h:outputText id="PRGCMGTOT157" value="#{msg['label.appeals.reqRate']}" />
			</f:facet>
		</t:column>
		<t:column id="dummySAItemTabledatacolumn8">
			<f:facet name="header">
				<h:outputText id="PRGCMGTOT158" value="#{msg['label.appeals.reqBeginDate']}" />
			</f:facet>
		</t:column>
		<t:column id="dummySAItemTabledatacolumn9">
			<f:facet name="header">
				<h:outputText id="PRGCMGTOT159" value="#{msg['label.appeals.reqEndDate']}" />
			</f:facet>
		</t:column>
	</h:dataTable>
</m:section>
