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

function chkListEnable(Id){
     var Status_list1 = Id.replace(/include_chk/, "saLineAppStatus_list");
	 var Result_list2 = Id.replace(/include_chk/, "saLineAppResult_list");
  
if(document.getElementById(Id).checked) {	

        document.getElementById(Status_list1).disabled=false;
		document.getElementById(Result_list2).disabled=false;
	
	}
	else {	
	    document.getElementById(Status_list1).disabled=true;
		document.getElementById(Result_list2).disabled=true;
	}
}
</script>

<f:loadBundle
	basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_ContactProviderAppealsInquiry"
	var="msg" />

<m:div styleClass="clear">
</m:div>
<m:div>
	<m:br />
	<m:br />
</m:div>

<m:section id="PROVIDERMMS2012073116481156" styleClass="otherbg">

	<m:legend>
		<h:outputText id="PRGCMGTOT104" value="#{msg['label.audit.sainfo.saLineItems']}" />
	</m:legend>

	<m:table id="PROVIDERMMT2012073116481157" styleClass="tableBar" width="100%">

		<m:tr>

			<m:td styleClass="tableTitle">

				<h:outputText id="PRGCMGTOT105" value="#{msg['title.saprovider.salineitemdatatable']}" />


			</m:td>

		</m:tr>
	</m:table>

	<h:dataTable id="providerAppealSAItemTable" border="1" cellpadding="0"		cellspacing="0" columnClasses="columnClass" headerClass="headerClass"		rows="5" footerClass="footerClass" rowClasses="row_alt,rowone"		styleClass="dataTable" width="100%"		var="providerAppealSAItemResult"		value="#{appealDataBean.appealVO.listSALineItemsVO}"		rendered="#{appealDataBean.saLineItemDataFlag}">
		


		<h:column id="saProvider_Include">
			<f:facet name="header">
				<h:panelGrid id="PRGCMGTPGD1" columns="2">
					<h:outputLabel id="PRGCMGTOLL96" for="saDetails_Include"						value="#{msg['label.provider.sainclude']}" />
					<h:panelGroup id="PRGCMGTPGP1">

						<t:commandLink id="saDetails_Include" styleClass="clStyle"							actionListener="#{appealControllerBean.sortSADataItem}"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_Include'}" onmousedown="javascript:flagWarn=false;">
							<h:graphicImage id="PROVIDERGI2012073116481158" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								url="/images/x.gif" styleClass="sort_asc" width="3" />
							<f:attribute name="columnName" value="serviceLineNum" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
						
						<t:commandLink id="saDetails_Include1" styleClass="clStyle"							actionListener="#{appealControllerBean.sortSADataItem}"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_Include1'}" onmousedown="javascript:flagWarn=false;">
							<h:graphicImage id="PROVIDERGI2012073116481158A" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								url="/images/x.gif" styleClass="sort_desc" width="3" />
							<f:attribute name="columnName" value="serviceLineNum" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>

					</h:panelGroup>
				</h:panelGrid>
			</f:facet>
			<h:outputLabel id="checkboxPSAColumnLabel"  for="include_chk" styleClass="hide" value="#{msg['label.provider.sainclude']}" ></h:outputLabel>
			<h:selectBooleanCheckbox id="include_chk"				value="#{providerAppealSAItemResult.include}"				onmouseover="chkListEnable(this.id)"				onmouseout="chkListEnable(this.id)" />
		</h:column>


		<h:column id="saProvider_LineNumber">
			<f:facet name="header">
				<h:panelGrid id="PRGCMGTPGD2" columns="2">
					<h:outputLabel id="PRGCMGTOLL97" for="saDetails_LineNumber"						value="#{msg['label.provider.salinenumber']}" />
					<h:panelGroup id="PRGCMGTPGP2">

						<t:commandLink id="saDetails_LineNumber" styleClass="clStyle"							actionListener="#{appealControllerBean.sortSADataItem}"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_LineNumber'}" onmousedown="javascript:flagWarn=false;">
							<h:graphicImage id="PROVIDERGI2012073116481159" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								url="/images/x.gif" styleClass="sort_asc" width="8" />
							<f:attribute name="columnName" value="serviceLineNum" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>

						<t:commandLink id="saDetails_LineNumber1" styleClass="clStyle"							actionListener="#{appealControllerBean.sortSADataItem}"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_LineNumber1'}" onmousedown="javascript:flagWarn=false;">
							<h:graphicImage id="PROVIDERGI2012073116481160" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								url="/images/x.gif" styleClass="sort_desc" width="8" />
							<f:attribute name="columnName" value="serviceLineNum" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>

			<h:outputText id="PRGCMGTOT106" value="#{providerAppealSAItemResult.lineNumber}" />

		</h:column>


		<h:column id="saProvider_ServiceAppStatus">
			<f:facet name="header">
				<h:panelGrid id="PRGCMGTPGD3" columns="2">
					<h:outputLabel id="PRGCMGTOLL98" for="saDetails_ServiceAppStatus"						value="#{msg['label.provider.saappstatus']}" />
					<h:panelGroup id="PRGCMGTPGP3">

						<t:commandLink id="saDetails_ServiceAppStatus"							styleClass="clStyle"							actionListener="#{appealControllerBean.sortSADataItem}"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_ServiceAppStatus'}" onmousedown="javascript:flagWarn=false;">
							<h:graphicImage id="PROVIDERGI2012073116481161" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								url="/images/x.gif" styleClass="sort_asc" width="8" />
							<f:attribute name="columnName" value="saLineAppStatus" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>

						<t:commandLink id="saDetails_ServiceAplStatus1"							styleClass="clStyle"							actionListener="#{appealControllerBean.sortSADataItem}"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_ServiceAppStatus1'}" onmousedown="javascript:flagWarn=false;">
							<h:graphicImage id="PROVIDERGI2012073116481162" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								url="/images/x.gif" styleClass="sort_desc" width="8" />
							<f:attribute name="columnName" value="saLineAppStatus" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>
			<h:outputLabel id="checkboxPSAColumnLabel1"  for="saLineAppStatus_list" styleClass="hide" value="#{msg['label.provider.saappstatus']}" ></h:outputLabel>
			<h:selectOneMenu id="saLineAppStatus_list"				value="#{providerAppealSAItemResult.saLineAppStatusValue }" onmousedown="javascript:flagWarn=false;"
			>
				<f:selectItem itemValue="" itemLabel="" />
				<f:selectItems value="#{providerAppealSAItemResult.saLineAppStatus}" />
			</h:selectOneMenu>


		</h:column>


		<h:column id="saProvider_ServiceAppResult">
			<f:facet name="header">
				<h:panelGrid id="PRGCMGTPGD4" columns="2">
					<h:outputLabel id="PRGCMGTOLL99" for="saDetails_ServiceAppResult"						value="#{msg['label.provider.saappresult']}" />
					<h:panelGroup id="PRGCMGTPGP4">

						<t:commandLink id="saDetails_ServiceAppResult"							styleClass="clStyle"							actionListener="#{appealControllerBean.sortSADataItem}"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_ServiceAppResult'}" onmousedown="javascript:flagWarn=false;">
							<h:graphicImage id="PROVIDERGI2012073116481163" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								url="/images/x.gif" styleClass="sort_asc" width="8" />
							<f:attribute name="columnName" value="saLineAppResult" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>

						<t:commandLink id="saDetails_ServiceAppResult1"							styleClass="clStyle"							actionListener="#{appealControllerBean.sortSADataItem}"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_ServiceAppResult1'}" onmousedown="javascript:flagWarn=false;">
							<h:graphicImage id="PROVIDERGI2012073116481164" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								url="/images/x.gif" styleClass="sort_desc" width="8" />
							<f:attribute name="columnName" value="saLineAppResult" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>
			<h:outputLabel id="checkboxPSAColumnLabel2"  for="saLineAppResult_list" styleClass="hide" value="#{msg['label.provider.saappresult']}" ></h:outputLabel>
			<h:selectOneMenu id="saLineAppResult_list"				value="#{providerAppealSAItemResult.saLineAppResultValue}" onmousedown="javascript:flagWarn=false;"
			>
				<f:selectItem itemValue="" itemLabel="" />
				<f:selectItems value="#{providerAppealSAItemResult.saLineAppResult}" />
			</h:selectOneMenu>
		</h:column>


		<h:column id="saProvider_ServiceTyCd">
			<f:facet name="header">
				<h:panelGrid id="PRGCMGTPGD5" columns="2">
					<h:outputLabel id="PRGCMGTOLL100" for="saDetails_ServiceTyCd"						value="#{msg['label.provider.saservicetycd']}" />
					<h:panelGroup id="PRGCMGTPGP5">

						<t:commandLink id="saDetails_ServiceTyCd" styleClass="clStyle"							actionListener="#{appealControllerBean.sortSADataItem}"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_ServiceTyCd'}" onmousedown="javascript:flagWarn=false;">
							<h:graphicImage id="PROVIDERGI2012073116481165" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								url="/images/x.gif" styleClass="sort_asc" width="8" />
							<f:attribute name="columnName" value="serviceTyCd" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>

						<t:commandLink id="saDetails_ServiceTyCd1" styleClass="clStyle"							actionListener="#{appealControllerBean.sortSADataItem}"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_ServiceTyCd1'}" onmousedown="javascript:flagWarn=false;">
							<h:graphicImage id="PROVIDERGI2012073116481166" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								url="/images/x.gif" styleClass="sort_desc" width="8" />
							<f:attribute name="columnName" value="serviceTyCd" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>

			<h:outputText id="PRGCMGTOT107" value="#{providerAppealSAItemResult.svcTypeCode}" />

		</h:column>


		<h:column id="saProvider_ServiceFrmCd">
			<f:facet name="header">
				<h:panelGrid id="PRGCMGTPGD6" columns="2">
					<h:outputLabel id="PRGCMGTOLL101" for="saDetails_ServiceFrmCd"						value="#{msg['label.provider.saservicefrmcd']}" />
					<h:panelGroup id="PRGCMGTPGP6">

						<t:commandLink id="saDetails_ServiceFrmCd" styleClass="clStyle"							actionListener="#{appealControllerBean.sortSADataItem}"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_ServiceFrmCd'}" onmousedown="javascript:flagWarn=false;">
							<h:graphicImage id="PROVIDERGI2012073116481167" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								url="/images/x.gif" styleClass="sort_asc" width="8" />
							<f:attribute name="columnName" value="serviceFrmCd" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>

						<t:commandLink id="saDetails_ServiceFrmCd1" styleClass="clStyle"							actionListener="#{appealControllerBean.sortSADataItem}"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_ServiceFrmCd1'}" onmousedown="javascript:flagWarn=false;">
							<h:graphicImage id="PROVIDERGI2012073116481168" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								url="/images/x.gif" styleClass="sort_desc" width="8" />
							<f:attribute name="columnName" value="serviceFrmCd" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>

			<h:outputText id="PRGCMGTOT108" value="#{providerAppealSAItemResult.svcFromCode}" />

		</h:column>


		<h:column id="saProvider_ServiceToCd">
			<f:facet name="header">
				<h:panelGrid id="PRGCMGTPGD7" columns="2">
					<h:outputLabel id="PRGCMGTOLL102" for="saDetails_ServiceToCd"						value="#{msg['label.provider.saservicetocd']}" />
					<h:panelGroup id="PRGCMGTPGP7">

						<t:commandLink id="saDetails_ServiceToCd" styleClass="clStyle"							actionListener="#{appealControllerBean.sortSADataItem}"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_ServiceToCd'}" onmousedown="javascript:flagWarn=false;">
							<h:graphicImage id="PROVIDERGI2012073116481169" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								url="/images/x.gif" styleClass="sort_asc" width="8" />
							<f:attribute name="columnName" value="serviceToCd" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>

						<t:commandLink id="saDetails_ServiceToCd1" styleClass="clStyle"							actionListener="#{appealControllerBean.sortSADataItem}"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_ServiceToCd1'}" onmousedown="javascript:flagWarn=false;">
							<h:graphicImage id="PROVIDERGI2012073116481170" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								url="/images/x.gif" styleClass="sort_desc" width="8" />
							<f:attribute name="columnName" value="serviceToCd" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>


			<h:outputText id="PRGCMGTOT109" value="#{providerAppealSAItemResult.svcToCode}" />

		</h:column>


		<h:column id="saProvider_ServiceReqAmount">
			<f:facet name="header">
				<h:panelGrid id="PRGCMGTPGD8" columns="2">
					<h:outputLabel id="PRGCMGTOLL103" for="saDetails_ServiceReqAmount"						value="#{msg['label.provider.sareqamt']}" />
					<h:panelGroup id="PRGCMGTPGP8">

						<t:commandLink id="saDetails_ServiceReqAmount"							styleClass="clStyle"							actionListener="#{appealControllerBean.sortSADataItem}"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_ServiceReqAmount'}" onmousedown="javascript:flagWarn=false;">
							<h:graphicImage id="PROVIDERGI2012073116481171" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								url="/images/x.gif" styleClass="sort_asc" width="8" />
							<f:attribute name="columnName" value="serviceReqAmt" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>

						<t:commandLink id="saDetails_ServiceReqAmount1"							styleClass="clStyle"							actionListener="#{appealControllerBean.sortSADataItem}"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_ServiceReqAmount1'}" onmousedown="javascript:flagWarn=false;">
							<h:graphicImage id="PROVIDERGI2012073116481172" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								url="/images/x.gif" styleClass="sort_desc" width="8" />
							<f:attribute name="columnName" value="serviceReqAmt" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>


			<h:outputText id="PRGCMGTOT110" value="#{providerAppealSAItemResult.reqAmount}" />

		</h:column>


		<h:column id="saProvider_ServiceReqUnits">
			<f:facet name="header">
				<h:panelGrid id="PRGCMGTPGD9" columns="2">
					<h:outputLabel id="PRGCMGTOLL104" for="saDetails_ServiceReqUnits"						value="#{msg['label.provider.sarequnits']}" />
					<h:panelGroup id="PRGCMGTPGP9">

						<t:commandLink id="saDetails_ServiceReqUnits" styleClass="clStyle"							actionListener="#{appealControllerBean.sortSADataItem}"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_ServiceReqUnits'}" onmousedown="javascript:flagWarn=false;">
							<h:graphicImage id="PROVIDERGI2012073116481173" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								url="/images/x.gif" styleClass="sort_asc" width="8" />
							<f:attribute name="columnName" value="serviceReqUnit" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>

						<t:commandLink id="saDetails_ServiceReqUnits1"							styleClass="clStyle"							actionListener="#{appealControllerBean.sortSADataItem}"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_ServiceReqUnits1'}" onmousedown="javascript:flagWarn=false;">
							<h:graphicImage id="PROVIDERGI2012073116481174" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								url="/images/x.gif" styleClass="sort_desc" width="8" />
							<f:attribute name="columnName" value="serviceReqUnit" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>


			<h:outputText id="PRGCMGTOT111" value="#{providerAppealSAItemResult.reqUnits}" />

		</h:column>


		<h:column id="saProvider_ServiceReqRate">
			<f:facet name="header">
				<h:panelGrid id="PRGCMGTPGD10" columns="2">
					<h:outputLabel id="PRGCMGTOLL105" for="saDetails_ServiceReqRate"						value="#{msg['label.provider.sareqrate']}" />
					<h:panelGroup id="PRGCMGTPGP10">

						<t:commandLink id="saDetails_ServiceReqRate" styleClass="clStyle"							actionListener="#{appealControllerBean.sortSADataItem}"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_ServiceReqRate'}" onmousedown="javascript:flagWarn=false;">
							<h:graphicImage id="PROVIDERGI2012073116481175" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								url="/images/x.gif" styleClass="sort_asc" width="8" />
							<f:attribute name="columnName" value="serviceReqRate" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>

						<t:commandLink id="saDetails_ServiceReqRate1" styleClass="clStyle"							actionListener="#{appealControllerBean.sortSADataItem}"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_ServiceReqRate1'}" onmousedown="javascript:flagWarn=false;">
							<h:graphicImage id="PROVIDERGI2012073116481176" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								url="/images/x.gif" styleClass="sort_desc" width="8" />
							<f:attribute name="columnName" value="serviceReqRate" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>
			<h:outputText id="PRGCMGTOT112" value="#{providerAppealSAItemResult.reqRate}" />
		</h:column>


		<h:column id="saProvider_ReqBegDt">
			<f:facet name="header">
				<h:panelGrid id="PRGCMGTPGD11" columns="2">
					<h:outputLabel id="PRGCMGTOLL106" for="saDetails_ReqBegDt"						value="#{msg['label.provider.saservicereqbegdt']}" />
					<h:panelGroup id="PRGCMGTPGP11">

						<t:commandLink id="saDetails_ReqBegDt" styleClass="clStyle"							actionListener="#{appealControllerBean.sortSADataItem}"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_ReqBegDt'}" onmousedown="javascript:flagWarn=false;">
							<h:graphicImage id="PROVIDERGI2012073116481177" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								url="/images/x.gif" styleClass="sort_asc" width="8" /> 
							<f:attribute name="columnName" value="serviceReqBegDt" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>

						<t:commandLink id="saDetails_ReqBegDt1" styleClass="clStyle"							actionListener="#{appealControllerBean.sortSADataItem}"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_ReqBegDt1'}" onmousedown="javascript:flagWarn=false;">
							<h:graphicImage id="PROVIDERGI2012073116481178" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								url="/images/x.gif" styleClass="sort_desc" width="8" />
							<f:attribute name="columnName" value="serviceReqBegDt" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>


			<h:outputText id="PRGCMGTOT113" value="#{providerAppealSAItemResult.reqBeginDate}">
				<f:convertDateTime pattern="MM/dd/yyyy" />
			</h:outputText>

		</h:column>


		<h:column id="saProvider_ReqEndDt">
			<f:facet name="header">
				<h:panelGrid id="PRGCMGTPGD12" columns="2">
					<h:outputLabel id="PRGCMGTOLL107" for="saDetails_ReqEndDt"						value="#{msg['label.provider.saservicereqenddt']}" />
					<h:panelGroup id="PRGCMGTPGP12">

						<t:commandLink id="saDetails_ReqEndDt" styleClass="clStyle"							actionListener="#{appealControllerBean.sortSADataItem}"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_ReqEndDt'}" onmousedown="javascript:flagWarn=false;">
							<h:graphicImage id="PROVIDERGI2012073116481179" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								url="/images/x.gif" styleClass="sort_asc" width="8" />
							<f:attribute name="columnName" value="serviceReqEndDt" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>

						<t:commandLink id="saDetails_ReqEndDt1" styleClass="clStyle"							actionListener="#{appealControllerBean.sortSADataItem}"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_ReqEndDt1'}" onmousedown="javascript:flagWarn=false;">
							<h:graphicImage id="PROVIDERGI2012073116481180" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								url="/images/x.gif" styleClass="sort_desc" width="8" />
							<f:attribute name="columnName" value="serviceReqEndDt" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>

			<h:outputText id="PRGCMGTOT114" value="#{providerAppealSAItemResult.reqEndDate }">
				<f:convertDateTime pattern="MM/dd/yyyy" />
			</h:outputText>

		</h:column>


		<h:column id="saProvider_ServiceStatus">
			<f:facet name="header">
				<h:panelGrid id="PRGCMGTPGD13" columns="2">
					<h:outputLabel id="PRGCMGTOLL108" for="saDetails_ServiceStatus"						value="#{msg['label.provider.saservicestatus']}" />
					<h:panelGroup id="PRGCMGTPGP13">

						<t:commandLink id="saDetails_ServiceStatus" styleClass="clStyle"							actionListener="#{appealControllerBean.sortSADataItem}"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_ServiceStatus'}" onmousedown="javascript:flagWarn=false;">
							<h:graphicImage id="PROVIDERGI2012073116481181" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}"
								url="/images/x.gif" styleClass="sort_asc" width="8" />
							<f:attribute name="columnName" value="serviceStatus" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>

						<t:commandLink id="saDetails_ServiceStatus1" styleClass="clStyle"							actionListener="#{appealControllerBean.sortSADataItem}"							rendered="#{appealDataBean.memAppealImageRender != 'saDetails_ServiceStatus1'}" onmousedown="javascript:flagWarn=false;">
							<h:graphicImage id="PROVIDERGI2012073116481182" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}"
								url="/images/x.gif" styleClass="sort_desc" width="8" />
							<f:attribute name="columnName" value="serviceStatus" />
							<f:attribute name="sortOrder" value="desc" />
						</t:commandLink>
					</h:panelGroup>
				</h:panelGrid>
			</f:facet>

			<h:outputText id="PRGCMGTOT115" value="#{providerAppealSAItemResult.saLineStatus }" />

		</h:column>


	</h:dataTable>
	<t:dataScroller   id="CMGTTOMDS2"  for="providerAppealSAItemTable" paginator="true"
		paginatorActiveColumnStyle='font-weight:bold;' paginatorMaxPages="5"
		immediate="false" pageCountVar="pageCount" pageIndexVar="pageIndex"
		firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
		rowsCountVar="rowsCount" styleClass="datascrollerStyle"  onclick="javascript:flagWarn=false;">
		<f:facet name="previous">
			<h:outputText id="PRGCMGTOT116" styleClass="underline"				value="#{msg['label.appeals.lt']}" rendered="#{pageIndex > 1}"></h:outputText>
		</f:facet>
		<f:facet name="next">
			<h:outputText id="PRGCMGTOT117" styleClass="underline"				value="#{msg['label.appeals.gt']}"				rendered="#{pageIndex < pageCount}"></h:outputText>
		</f:facet>
		<h:outputText id="PRGCMGTOT118" rendered="#{rowsCount > 0}"
			value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
			styleClass="floatLeft" />
	</t:dataScroller>
	<h:dataTable var="dummyproviderAppealSAItemTable"		rendered="#{!appealDataBean.showSACompFlag}" styleClass="dataTable"		cellspacing="0" width="100%" border="1" headerClass="tableHead"		rowClasses="rowone,row_alt" id="dummyproviderAppealSAItemTable">
		<t:column id="dummySAItemTabledatacolumn1">
			<f:facet name="header">
				<h:outputText id="PRGCMGTOT119" value="#{msg['label.provider.sainclude']}" />
			</f:facet>
		</t:column>
		<t:column id="dummySAItemTabledatacolumn2">
			<f:facet name="header">
				<h:outputText id="PRGCMGTOT120" value="#{msg['label.provider.salinenumber']}" />
			</f:facet>
		</t:column>
		<t:column id="dummySAItemTabledatacolumn3">
			<f:facet name="header">
				<h:outputText id="PRGCMGTOT121" value="#{msg['label.provider.saappstatus']}" />
			</f:facet>
		</t:column>
		<t:column id="dummySAItemTabledatacolumn4">
			<f:facet name="header">
				<h:outputText id="PRGCMGTOT122" value="#{msg['label.provider.saappresult']}" />
			</f:facet>
		</t:column>
		<t:column id="dummySAItemTabledatacolumn5">
			<f:facet name="header">
				<h:outputText id="PRGCMGTOT123" value="#{msg['label.provider.saservicetycd']}" />
			</f:facet>
		</t:column>
		<t:column id="dummySAItemTabledatacolumn6">
			<f:facet name="header">
				<h:outputText id="PRGCMGTOT124" value="#{msg['label.provider.saservicefrmcd']}" />
			</f:facet>
		</t:column>
		<t:column id="dummySAItemTabledatacolumn7">
			<f:facet name="header">
				<h:outputText id="PRGCMGTOT125" value="#{msg['label.provider.saservicetocd']}" />
			</f:facet>
		</t:column>
		<t:column id="dummySAItemTabledatacolumn8">
			<f:facet name="header">
				<h:outputText id="PRGCMGTOT126" value="#{msg['label.provider.sareqamt']}" />
			</f:facet>
		</t:column>
		<t:column id="dummySAItemTabledatacolumn9">
			<f:facet name="header">
				<h:outputText id="PRGCMGTOT127" value="#{msg['label.provider.sarequnits']}" />
			</f:facet>
		</t:column>
		<t:column id="dummySAItemTabledatacolumn10">
			<f:facet name="header">
				<h:outputText id="PRGCMGTOT128" value="#{msg['label.provider.sareqrate']}" />
			</f:facet>
		</t:column>
		<t:column id="dummySAItemTabledatacolumn11">
			<f:facet name="header">
				<h:outputText id="PRGCMGTOT129" value="#{msg['label.provider.saservicereqbegdt']}" />
			</f:facet>
		</t:column>
		<t:column id="dummySAItemTabledatacolumn12">
			<f:facet name="header">
				<h:outputText id="PRGCMGTOT130" value="#{msg['label.provider.saservicereqenddt']}" />
			</f:facet>
		</t:column>
		<t:column id="dummySAItemTabledatacolumn13">
			<f:facet name="header">
				<h:outputText id="PRGCMGTOT131" value="#{msg['label.provider.saservicestatus']}" />
			</f:facet>
		</t:column>
	</h:dataTable>
</m:section>
