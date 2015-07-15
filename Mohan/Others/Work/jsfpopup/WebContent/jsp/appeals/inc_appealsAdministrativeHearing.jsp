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


	
<m:section id="PROVIDERMMS2012073116481119" styleClass="otherbg">
	<m:legend>
	    	<h:outputText id="PRGCMGTOT52" value="#{msg['title.appeals.administrativehearing']}" />
    </m:legend>
	<%-- To fix Defect ESPRD00696684  --%>
	<!--<m:div rendered="#{appealDataBean.showSuccessMsgFlag}"
		styleClass="msgBox">
		<h:messages layout="table" showDetail="true" styleClass="successMsg"
			id="adminHrngSuccessMessage" showSummary="false"></h:messages>
	</m:div> -->
	
	<m:div styleClass="clear"></m:div>
	<m:div>
		<m:br />
		<m:br />
	</m:div>
	
	<m:table id="PROVIDERMMT2012073116481120" width="100%" styleClass="tableBar">
		<m:tr>
			<m:td styleClass="tableAction">			    
		
				<h:commandButton styleClass="formBtn" disabled="#{appealDataBean.disableAppAddAdminHear}"					value="#{msg['link.administrativehearing.add']}" id="adminHearingButton"					action="#{appealControllerBean.addAdminHearing}" 					onclick="javascript:focusThis('add_AdminHrngDT');javascript:fileSavedFlag=true;javascript:flagWarn=false;"/>

			</m:td>
		</m:tr>
	</m:table>
	<m:div rendered="#{appealDataBean.showDeletedMsgFlag}"
				styleClass="msgBox">
				<h:outputText id="Edit2" escape="false"
							  value="#{msg['display.appeals.delete.message']}">
				</h:outputText>
	</m:div>
	<t:dataTable id="adminHearingTable" border="1" cellpadding="0"
		cellspacing="0" columnClasses="columnClass"
		headerClass="headerClass" rows="10" footerClass="footerClass"
		rowClasses="row_alt,rowone" styleClass="dataTable"
		width="100%" var="adminHearingResult"
		value="#{appealDataBean.adminHearingList}" 
		rendered="#{appealDataBean.adminHearingDataFlag}"
		first="#{appealDataBean.addAppealsRowIndex}"
		rowOnMouseOver="this.origClassName = this.className;this.className='row_mouse row_link';"
		rowOnMouseOut="if (this.origClassName) this.className = this.origClassName;" 
		rowIndexVar="index" onmousedown="javascript:focusThis('edit_AdminHrngDT');"
		rowOnClick="javascript:flagWarn=false;childNodes[0].lastChild.click();" >

		<t:column id="adminHear_admnHrDt">
			<f:facet name="header">
			<t:panelGrid id="CMGTTOMPGD1" columns="2">
				<t:outputLabel id="CMGTTOMOL1" for="adminHear_dt" value="#{msg['label.appeals.adminhearingdate']}" />
				<t:panelGroup id="CMGTTOMPGP1">
					 <m:div>
						<t:commandLink id="hearingDtLink1" styleClass="clStyle"							actionListener="#{appealControllerBean.sortAdminHearing}"							rendered="#{appealDataBean.imageRender != 'hearingDtLink1'}"							onmousedown="javascript:flagWarn=false;">
							<h:graphicImage id="PROVIDERGI2012073116481121" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}" styleClass="sort_asc"
								width="8" url="/images/x.gif"/>
							<f:attribute name="columnName" value="adminHrngDt" />
							<f:attribute name="sortOrder" value="asc" />
						</t:commandLink>
					</m:div>
					<t:commandLink id="hearingDtLink2" styleClass="clStyle"						actionListener="#{appealControllerBean.sortAdminHearing}"						rendered="#{appealDataBean.imageRender != 'hearingDtLink2'}"						onmousedown="javascript:flagWarn=false;">
						<h:graphicImage id="PROVIDERGI2012073116481122" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}" styleClass="sort_desc"
							width="8" url="/images/x.gif"/>
						<f:attribute name="columnName" value="adminHrngDt" />
						<f:attribute name="sortOrder" value="desc" />
					</t:commandLink>
				</t:panelGroup>
				</t:panelGrid>
			</f:facet>
			
			<t:commandLink id="PRGCMGTCL15"				action="#{appealControllerBean.editAdminHearing}"				onmousedown="javascript:flagWarn=false;" onclick="javascript:flagWarn=false;">
				<f:param name="rowIndex" value="#{index}"></f:param>
				<h:outputText id="adminHear_dt" value="#{adminHearingResult.strAdminHearingDate}" />
			</t:commandLink>
			
		</t:column>

		<t:column id="adminHear_hearingRslts">
			<f:facet name="header">
			<t:panelGrid id="CMGTTOMPGD2" columns="2">
				<t:outputLabel id="CMGTTOMOL2" for="adminHear_rslts" value="#{msg['label.appeals.hearingresults']}" />
				<t:panelGroup id="CMGTTOMPGP2">
					<m:div>
					<t:commandLink id="hearingRsltsLink1" styleClass="clStyle"						actionListener="#{appealControllerBean.sortAdminHearing}"						rendered="#{appealDataBean.imageRender != 'hearingRsltsLink1'}"						onmousedown="javascript:flagWarn=false;">
						<h:graphicImage id="PROVIDERGI2012073116481123" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}" styleClass="sort_asc"
							width="8" url="/images/x.gif"/>
						<f:attribute name="columnName" value="hrngRslts" />
						<f:attribute name="sortOrder" value="asc" />
					</t:commandLink>
					</m:div>
					<t:commandLink id="hearingRsltsLink2" styleClass="clStyle"						actionListener="#{appealControllerBean.sortAdminHearing}"						rendered="#{appealDataBean.imageRender != 'hearingRsltsLink2'}"						onmousedown="javascript:flagWarn=false;">
						<h:graphicImage id="PROVIDERGI2012073116481124" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}" styleClass="sort_desc"
							width="8" url="/images/x.gif"/>
						<f:attribute name="columnName" value="hrngRslts" />
						<f:attribute name="sortOrder" value="desc" />
					</t:commandLink>
				</t:panelGroup>
				</t:panelGrid>
			</f:facet>
	<%-- ESPRD00514325_UC-PGM-CRM-067_26AUG2010
		<h:outputText id="adminHear_rslts" value="#{adminHearingResult.hearingResults}" />--%>
		<h:outputText id="adminHear_rslts" value="#{adminHearingResult.hearingResultsDesc}" />
		</t:column>
		
		<t:column id="adminHear_hearingStatus">
			<f:facet name="header">
			<t:panelGrid id="CMGTTOMPGD3" columns="2">
				<t:outputLabel id="CMGTTOMOL3" for="adminHear_sts" value="#{msg['labe.appeals.hearingstatus']}" />
				<t:panelGroup id="CMGTTOMPGP3">
					<m:div>
					<t:commandLink id="hearingStsLink1" styleClass="clStyle"						actionListener="#{appealControllerBean.sortAdminHearing}"						rendered="#{appealDataBean.imageRender != 'hearingStsLink1'}"						onmousedown="javascript:flagWarn=false;">
						<h:graphicImage id="PROVIDERGI2012073116481125" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}" styleClass="sort_asc"
							width="8" url="/images/x.gif"/>
						<f:attribute name="columnName" value="hrngSts" />
						<f:attribute name="sortOrder" value="asc" />
					</t:commandLink>
					</m:div>
					<t:commandLink id="hearingStsLink2" styleClass="clStyle"						actionListener="#{appealControllerBean.sortAdminHearing}"						rendered="#{appealDataBean.imageRender != 'hearingStsLink2'}"						onmousedown="javascript:flagWarn=false;">
						<h:graphicImage id="PROVIDERGI2012073116481126" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}" styleClass="sort_desc"
							width="8" url="/images/x.gif"/>
						<f:attribute name="columnName" value="hrngSts" />
						<f:attribute name="sortOrder" value="desc" />
					</t:commandLink>
				</t:panelGroup>
				</t:panelGrid>
			</f:facet>
<%-- ESPRD00514325_UC-PGM-CRM-067_26AUG2010
			<h:outputText id="adminHear_sts" value="#{adminHearingResult.hearingStatus}" />--%>
<h:outputText id="adminHear_sts" value="#{adminHearingResult.hearingStatusDesc}" />
		</t:column>
		
		<t:column id="adminHear_hearingOffNm">
			<f:facet name="header">
			<t:panelGrid id="CMGTTOMPGD4" columns="2">
				<t:outputLabel id="CMGTTOMOL4" for="adminHear_offNm" value="#{msg['label.appeals.hearingofficername']}" />
				<t:panelGroup id="CMGTTOMPGP4">
				<m:div>
					<t:commandLink id="hearingOffNmLink1" styleClass="clStyle"						actionListener="#{appealControllerBean.sortAdminHearing}"						rendered="#{appealDataBean.imageRender != 'hearingOffNmLink1'}"						onmousedown="javascript:flagWarn=false;">
						<h:graphicImage id="PROVIDERGI2012073116481127" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}" styleClass="sort_asc"
							width="8" url="/images/x.gif"/>
						<f:attribute name="columnName" value="hrngOffNm" />
						<f:attribute name="sortOrder" value="asc" />
					</t:commandLink>
					</m:div>
					<t:commandLink id="hearingOffNmLink2" styleClass="clStyle"						actionListener="#{appealControllerBean.sortAdminHearing}"						rendered="#{appealDataBean.imageRender != 'hearingOffNmLink2'}"						onmousedown="javascript:flagWarn=false;">
						<h:graphicImage id="PROVIDERGI2012073116481128" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}" styleClass="sort_desc"
							width="8" url="/images/x.gif"/>
						<f:attribute name="columnName" value="hrngOffNm" />
						<f:attribute name="sortOrder" value="desc" />
					</t:commandLink>
				</t:panelGroup>
				</t:panelGrid>
			</f:facet>
			<h:outputText id="adminHear_offNm" value="#{adminHearingResult.hearingOfficerName}" />
		</t:column>
		
		<t:column id="adminHear_dockNum">
			<f:facet name="header">
			<t:panelGrid id="CMGTTOMPGD5" columns="2">
				<t:outputLabel id="CMGTTOMOL5" for="adminHear_dckNm" value="#{msg['label.appeals.docketnumber']}" />
				<t:panelGroup id="CMGTTOMPGP5">
				<m:div>
					<t:commandLink id="doclNumLink1" styleClass="clStyle"						actionListener="#{appealControllerBean.sortAdminHearing}"						rendered="#{appealDataBean.imageRender != 'doclNumLink1'}"						onmousedown="javascript:flagWarn=false;">
						<h:graphicImage id="PROVIDERGI2012073116481129" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}" styleClass="sort_asc"
							width="8" url="/images/x.gif"/>
						<f:attribute name="columnName" value="dcktNm" />
						<f:attribute name="sortOrder" value="asc" />
					</t:commandLink>
				</m:div>
					<t:commandLink id="doclNumLink2" styleClass="clStyle"						actionListener="#{appealControllerBean.sortAdminHearing}"						rendered="#{appealDataBean.imageRender != 'doclNumLink2'}"						onmousedown="javascript:flagWarn=false;">
						<h:graphicImage id="PROVIDERGI2012073116481130" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}" styleClass="sort_desc"
							width="8" url="/images/x.gif"/>
						<f:attribute name="columnName" value="dcktNm" />
						<f:attribute name="sortOrder" value="desc" />
					</t:commandLink>
				</t:panelGroup>
				</t:panelGrid>
			</f:facet>
			<h:outputText id="adminHear_dckNm" value="#{adminHearingResult.docketNumber}" />
		</t:column>
		
	</t:dataTable> 
	
	
	<m:div styleClass="searchResults">

		<t:dataScroller  id="CMGTTOMDS1" for="adminHearingTable" paginator="true"
			paginatorActiveColumnStyle='font-weight:bold;' paginatorMaxPages="3"
			immediate="false" pageCountVar="pageCount" pageIndexVar="pageIndex"
			firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
			rowsCountVar="rowsCount"
			styleClass="datascrollerStyle"  onclick="javascript:flagWarn=false;">
			<f:facet name="previous">
				<h:outputText id="PRGCMGTOT53" styleClass="underline" value="#{msg['label.appeals.lt']}"					rendered="#{pageIndex > 1}"></h:outputText>
			</f:facet>
			<f:facet name="next">
				<h:outputText id="PRGCMGTOT54" styleClass="underline" value="#{msg['label.appeals.gt']}"					rendered="#{pageIndex < pageCount}"></h:outputText>
			</f:facet>
			<h:outputText id="PRGCMGTOT55" rendered="#{rowsCount > 0}"
				value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
				styleClass="floatLeft" />
		</t:dataScroller>
		
	<h:dataTable var="dummyAdminHearing"			rendered="#{!appealDataBean.adminHearingDataFlag}"			styleClass="dataTable" cellspacing="0" width="100%" border="1"			headerClass="tableHead" rowClasses="rowone,row_alt" id="dummyAdminHearingTable" >
			
			<t:column id="dummydddatacolumn1">
				<f:facet name="header">
					<h:outputText id="PRGCMGTOT56" value="#{msg['label.appeals.adminhearingdate']}" />
				</f:facet>
			</t:column>
			<t:column id="dummydddatacolumn2">
				<f:facet name="header">
					<h:outputText id="PRGCMGTOT57" value="#{msg['label.appeals.hearingresults']}" />
				</f:facet>
			</t:column>
			<t:column id="dummydddatacolumn3">
				<f:facet name="header">
					<h:outputText id="PRGCMGTOT58" value="#{msg['label.appeals.hearingstatus']}" />
				</f:facet>
			</t:column>
			<t:column id="dummydddatacolumn4">
				<f:facet name="header">
					<h:outputText id="PRGCMGTOT59" value="#{msg['label.appeals.hearingofficername']}" />
				</f:facet>
			</t:column>
			<t:column id="dummydddatacolumn5">
				<f:facet name="header">
					<h:outputText id="PRGCMGTOT60" value="#{msg['label.appeals.docketnumber']}" />
				</f:facet>
			</t:column>
		</h:dataTable> 

		<m:table id="PROVIDERMMT2012073116481131" styleClass="dataTable" width="100%" border="0"
			rendered="#{!appealDataBean.adminHearingDataFlag}">
			<m:tr>
				<m:td align="center">
					<h:outputText id="PRGCMGTOT61" value="#{msg['label.appeals.nodata']}" />
				</m:td>
			</m:tr>
		</m:table> 

		<m:div styleClass="clear">
		</m:div>
		<m:div>
			<m:br />
			<m:br />
		</m:div>
	</m:div>
	
	<m:div id="addAdministrativeHearing" rendered="#{appealDataBean.addAdminHearingFlag}">
		<f:subview id="addAdminHearingFlagID">
		<jsp:include
			page="/jsp/appeals/inc_appealsAddAdministrativeHearing.jsp" />
		</f:subview>
	</m:div>
	
	<m:div id="editAdministrativeHearing" rendered="#{appealDataBean.editAdminHearingFlag}">
		<f:subview id="editAdminHearingFlagID">
		<jsp:include
			page="/jsp/appeals/inc_appealsEditAdministrativeHearing.jsp" />
		</f:subview>
	</m:div>
	
	     <m:div id="notes">
				<f:subview id="notesSet">
					<jsp:include page="/jsp/commonEntities/commonNotesDetail.jsp" />
				</f:subview>
	  </m:div>
</m:section>
