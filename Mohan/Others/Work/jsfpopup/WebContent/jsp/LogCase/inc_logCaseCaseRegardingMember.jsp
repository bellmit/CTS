<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>

<m:table id="logCaseCaseRegMemberTb1" width="100%">
	<m:tr id="logCaseCaseRegMemberTr1">
		<m:td id="logCaseCaseRegMemberTd1">
			<m:div id="logCaseCaseRegMemberDiv1" styleClass="padb">
				<h:outputText id="logCaseCaseRegMemberOutTxt1"					value="#{msg['label.case.caseRegarding.caseRecordNumber']}"					styleClass="outputLabel" />
				<m:br id="logCaseCaseRegMemberBr1"/>
				<h:outputText id="logCaseCaseRegMemberOutTxt2" value="#{logCaseDataBean.caseRegardingVO.caseRecordNumber}" />
			</m:div>
		</m:td>
	</m:tr>
	<m:tr id="logCaseCaseRegMemberTr2">
		<m:td id="logCaseCaseRegMemberTd2">
			<m:div id="logCaseCaseRegMemberDiv2" styleClass="padb">
				<h:outputText id="logCaseCaseRegMemberOutTxt3" value="#{msg['label.case.caseRegarding.entityType']}"					styleClass="outputLabel" />
				<m:br id="logCaseCaseRegMemberBr2" />
				<h:outputText id="logCaseCaseRegMemberOutTxt4" value="#{logCaseDataBean.caseRegardingVO.caseRegardingMemberVO.entityTypeDesc}" />
			</m:div>
		</m:td>
		<m:td id="logCaseCaseRegMemberTd3">
			<m:div id="logCaseCaseRegMemberDiv3" styleClass="padb">
				<h:outputText id="logCaseCaseRegMemberOutTxt5" styleClass="outputLabel"					value="#{msg['label.case.caseRegarding.memberID']}" />
				<m:br id="logCaseCaseRegMemberBr3" />
					<%--ESPRD00529618 starts--%>
					<%-- Modified for the defect ESPRD00865382 starts--%>
					<%--<h:outputText id="logCaseCaseRegMemberOutTxt6" value="#{logCaseDataBean.caseRegardingVO.caseRegardingMemberVO.entityId}" />--%>
					<h:outputText id="logCaseCaseRegMemberOutTxt6" value="#{logCaseDataBean.caseRegardingVO.caseRegardingMemberVO.memberId}" style="color: blue" rendered="#{!logCaseDataBean.disableScreenField}" />
					<%--ESPRD00529618 ends--%>
					<h:outputText id="logCaseCaseRegMemberOutTxt6Inquiry" value="#{logCaseDataBean.caseRegardingVO.caseRegardingMemberVO.memberId}" rendered="#{logCaseDataBean.disableScreenField}" />
					<%--ESPRD00865382 ends--%>
			</m:div>
		</m:td>
		<m:td id="logCaseCaseRegMemberTd4">
			<m:div id="logCaseCaseRegMemberDiv4" styleClass="padb">
				<h:outputText id="logCaseCaseRegMemberOutTxt7" value="#{msg['label.case.caseRegarding.IDtype']}"					styleClass="outputLabel" />
				<m:br id="logCaseCaseRegMemberBr4" />
				<h:outputText id="logCaseCaseRegMemberOutTxt8" value="#{logCaseDataBean.caseRegardingVO.caseRegardingMemberVO.memberIDTypeDesc}" />
			</m:div>
		</m:td>
		<m:td id="logCaseCaseRegMemberTd5">
			<m:div id="logCaseCaseRegMemberDiv5" styleClass="padb">
				<h:outputText id="logCaseCaseRegMemberOutTxt9" value="#{msg['label.case.caseRegarding.ssn']}"					styleClass="outputLabel" />
				<m:br id="logCaseCaseRegMemberBr5" />
				<h:outputText id="logCaseCaseRegMemberOutTxt10" value="#{logCaseDataBean.caseRegardingVO.caseRegardingMemberVO.memberSSN}" />
			</m:div>
		</m:td>
		<m:tr id="logCaseCaseRegMemberTr3">
			<m:td id="logCaseCaseRegMemberTd6">
				<m:div id="logCaseCaseRegMemberDiv6" styleClass="padb">
					<h:outputText id="logCaseCaseRegMemberOutTxt11" value="#{msg['label.case.caseRegarding.name']}"						styleClass="outputLabel" />
					<m:br id="logCaseCaseRegMemberBr6" />
					<h:outputText id="logCaseCaseRegMemberOutTxt12" value="#{logCaseDataBean.caseRegardingVO.caseRegardingMemberVO.name}" />
				</m:div>
			</m:td>
			<m:td id="logCaseCaseRegMemberTd7">
				<m:div id="logCaseCaseRegMemberDiv7" styleClass="padb">
					<h:outputText id="logCaseCaseRegMemberOutTxt13" value="#{msg['label.case.caseRegarding.email']}"						styleClass="outputLabel" />
					<m:br id="logCaseCaseRegMemberBr7" />
					<h:outputText id="logCaseCaseRegMemberOutTxt14" value="#{logCaseDataBean.caseRegardingVO.caseRegardingMemberVO.email}" />
				</m:div>
			</m:td>
		</m:tr>
	</m:tr>
	<m:tr id="logCaseCaseRegMemberTr4">
		<m:td id="logCaseCaseRegMemberTd8">
			<m:div id="logCaseCaseRegMemberDiv8" styleClass="padb">
				<h:outputText id="logCaseCaseRegMemberOutTxt15" styleClass="outputLabel"					value="#{msg['label.case.caseRegarding.catageoryOfEligibility']}" />
				<m:br id="logCaseCaseRegMemberBr8" />
				<h:outputText id="logCaseCaseRegMemberOutTxt16" value="#{logCaseDataBean.caseRegardingVO.caseRegardingMemberVO.coeCodeDesc}" />
			</m:div>
		</m:td>
		<m:td id="logCaseCaseRegMemberTd9">
		</m:td>
		<m:td id="logCaseCaseRegMemberTd10">
			<m:div id="logCaseCaseRegMemberDiv9" styleClass="padb">
				<h:outputText id="logCaseCaseRegMemberOutTxt17"					value="#{msg['label.case.caseRegarding.districtOffice']}"					styleClass="outputLabel" />
				<m:br id="logCaseCaseRegMemberBr9" />
				<h:outputText id="logCaseCaseRegMemberOutTxt18" value="#{logCaseDataBean.caseRegardingVO.caseRegardingMemberVO.districtOfficeDesc}" />
			</m:div>
		</m:td>
		<m:td id="logCaseCaseRegMemberTd11">
			<m:div id="logCaseCaseRegMemberDiv10" styleClass="padb">
				<h:outputText id="logCaseCaseRegMemberOutTxt19" value="#{msg['label.case.caseRegarding.dateOfBirth']}"					styleClass="outputLabel" />
				<m:br id="logCaseCaseRegMemberBr10" />
				<h:outputText id="logCaseCaseRegMemberOutTxt20" value="#{logCaseDataBean.caseRegardingVO.caseRegardingMemberVO.dateofBirth}" />
			</m:div>
		</m:td>
		<m:td id="logCaseCaseRegMemberTd12">
			<m:div id="logCaseCaseRegMemberDiv11" styleClass="padb">
				<h:outputText id="logCaseCaseRegMemberOutTxt21"					value="#{msg['label.case.caseRegarding.previousName']}"					styleClass="outputLabel" />
				<m:br id="logCaseCaseRegMemberBr11" />
				<h:outputText id="logCaseCaseRegMemberOutTxt22" value="#{logCaseDataBean.caseRegardingVO.caseRegardingMemberVO.previousName}" />
			</m:div>
		</m:td>
	</m:tr>
	<m:tr id="logCaseCaseRegMemberTr5">
		<m:td id="logCaseCaseRegMemberTd13">
			<m:div id="logCaseCaseRegMemberDiv12" styleClass="padb">
				<h:outputText id="logCaseCaseRegMemberOutTxt23"					value="#{msg['label.case.caseRegarding.resedentialAddress']}"					styleClass="outputLabel" />
				<m:br id="logCaseCaseRegMemberBr12" />
				<h:outputText id="logCaseCaseRegMemberOutTxt24" escape="false" value="#{logCaseDataBean.caseRegardingVO.caseRegardingMemberVO.residentialAddress}" />
			</m:div>
		</m:td>
		<m:td id="logCaseCaseRegMemberTd14">
			<m:div id="logCaseCaseRegMemberDiv13" styleClass="padb">
				<h:outputText id="logCaseCaseRegMemberOutTxt25"					value="#{msg['label.case.caseRegarding.mailingAddress']}"					styleClass="outputLabel" />
				<m:br id="logCaseCaseRegMemberBr13" />
				<h:outputText id="logCaseCaseRegMemberOutTxt26" escape="false" value="#{logCaseDataBean.caseRegardingVO.caseRegardingMemberVO.mailingAddress}" />
			</m:div>
		</m:td>
	</m:tr>
</m:table>

<m:section id="logCaseCaseRegMemberSec1" styleClass="expand">
	<m:legend id="logCaseCaseRegMemberLeg1">
		<h:outputLink				onclick="javascript:flagWarn=false;setHiddenValue('CMlogCase:caseRegarding:caseRegardingMember:memAltIden_hiddenID','minus');	 		 	toggle('mem_alt_iden_plus',2);			 	plusMinusToggle('mem_alt_iden_plus',this,'CMlogCase:caseRegarding:caseRegardingMember:memAltIden_hiddenID');			 	return false;" id="memIden_plusMinus" styleClass="plus">
				<h:outputText id="mem_iden_label" value="#{msg['label.case.caseRegarding.alternativeIdentifiers']}" />
		</h:outputLink>
			<h:inputHidden id="memAltIden_hiddenID" value="#{logCaseDataBean.memAltIdentifierHidden}"/>
	</m:legend>
	<m:div sid="mem_alt_iden_plus">
			<t:dataTable value="#{logCaseDataBean.alternateIdentiMemList}"				rendered="#{logCaseDataBean.showAlternateIdentifiersMem}"				first="#{logCaseDataBean.altIdentifiersMemRowIndex}"				var="altIdenMemResult" styleClass="dataTable" cellspacing="0"				width="100%" border="0" headerClass="tableHead"				rowClasses="rowone,row_alt" rows="10" id="altIdenMemtable"				onmouseover="return tableMouseOver(this, event);"				onmouseout="return tableMouseOut(this, event);">
				<t:column id="altIdenMemcolumn1">
					<f:facet name="header">
						<h:panelGrid id="logCaseCaseRegMemberPgrd1" columns="2">
							<h:outputLabel id="logCaseCaseRegMemberLabel1" value="#{msg['label.case.caseRegarding.type']}" for="altIdenMemTypeCommandLink1"/>
							<h:panelGroup id="logCaseCaseRegMemberPgrp1">
								<t:commandLink id="altIdenMemTypeCommandLink1"									styleClass="clStyle"									actionListener="#{logCaseControllerBean.sortAltIdenForMem}"									rendered="#{logCaseDataBean.imageRender != 'altIdenMemTypeCommandLink1'}" onmousedown="javascript:flagWarn=false;focusPage('lgCsCaseRegFocusPage');">
									<h:graphicImage id="PROVIDERGI20120731164811293A" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}" styleClass="sort_asc"
										width="8" url="/images/x.gif"/>
									<f:attribute name="columnName" value="altIden_Mem_Type" />
									<f:attribute name="sortOrder" value="asc" />
								</t:commandLink>
								<m:br id="logCaseCaseRegMemberBr0" />
								<t:commandLink id="altIdenMemTypeCommandLink2"									styleClass="clStyle"									actionListener="#{logCaseControllerBean.sortAltIdenForMem}"									rendered="#{logCaseDataBean.imageRender != 'altIdenMemTypeCommandLink2'}" onmousedown="javascript:flagWarn=false;focusPage('lgCsCaseRegFocusPage');">
									<h:graphicImage id="PROVIDERGI20120731164811294" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}" styleClass="sort_desc"
										width="8" url="/images/x.gif"/>
									<f:attribute name="columnName" value="altIden_Mem_Type" />
									<f:attribute name="sortOrder" value="desc" />
								</t:commandLink>
							</h:panelGroup>
						</h:panelGrid>
					</f:facet>
					<h:outputText id="logCaseCaseRegMemberOutTxt27" value="#{altIdenMemResult.alternateIDTypeCodeDesc}" />
				</t:column>

				<t:column id="altIdenMemcolumn2">
					<f:facet name="header">
						<h:panelGrid id="logCaseCaseRegMemberPgrd2" columns="2">
							<h:outputLabel id="logCaseCaseRegMemberLabel2"								value="#{msg['label.case.caseRegarding.alternateID']}" for="altIdenMemAltIDCommandLink1"/>
							<h:panelGroup id="logCaseCaseRegMemberPgrp2">
								<t:commandLink id="altIdenMemAltIDCommandLink1"									styleClass="clStyle"									actionListener="#{logCaseControllerBean.sortAltIdenForMem}"									rendered="#{logCaseDataBean.imageRender != 'altIdenMemAltIDCommandLink1'}" onmousedown="javascript:flagWarn=false;focusPage('lgCsCaseRegFocusPage');">
									<h:graphicImage id="PROVIDERGI20120731164811295" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}" styleClass="sort_asc"
										width="8" url="/images/x.gif"/>
									<f:attribute name="columnName" value="altIden_Mem_AltID" />
									<f:attribute name="sortOrder" value="asc" />
								</t:commandLink>
								<m:br id="logCaseCaseRegMemberBr14" />
								<t:commandLink id="altIdenMemAltIDCommandLink2"									styleClass="clStyle"									actionListener="#{logCaseControllerBean.sortAltIdenForMem}"									rendered="#{logCaseDataBean.imageRender != 'altIdenMemAltIDCommandLink2'}" onmousedown="javascript:flagWarn=false;focusPage('lgCsCaseRegFocusPage');">
									<h:graphicImage id="PROVIDERGI20120731164811296" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}" styleClass="sort_desc"
										width="8" url="/images/x.gif"/>
									<f:attribute name="columnName" value="altIden_Mem_AltID" />
									<f:attribute name="sortOrder" value="desc" />
								</t:commandLink>
							</h:panelGroup>
						</h:panelGrid>
					</f:facet>
					<h:outputText id="logCaseCaseRegMemberOutTxt277" value="#{altIdenMemResult.alternateID}" />
				</t:column>
				<t:column id="altIdenMemcolumn3">
					<f:facet name="header">
						<h:panelGrid id="logCaseCaseRegMemberPgrd3" columns="2">
							<h:outputLabel id="logCaseCaseRegMemberLabel3"								value="#{msg['label.case.caseRegarding.lineOfBusiness']}" for="altIdenMemLOBCommandLink1"/>
							<h:panelGroup id="logCaseCaseRegMemberPgrp3">
								<t:commandLink id="altIdenMemLOBCommandLink1"									styleClass="clStyle"									actionListener="#{logCaseControllerBean.sortAltIdenForMem}"									rendered="#{logCaseDataBean.imageRender != 'altIdenMemLOBCommandLink1'}" onmousedown="javascript:flagWarn=false;focusPage('lgCsCaseRegFocusPage');">
									<h:graphicImage id="PROVIDERGI20120731164811297" alt="#{ent['label.ent.forAscending']}" title="#{ent['label.ent.forAscending']}" styleClass="sort_asc"
										width="8" url="/images/x.gif"/>
									<f:attribute name="columnName" value="altIden_Mem_LOB" />
									<f:attribute name="sortOrder" value="asc" />
								</t:commandLink>
								<m:br id="logCaseCaseRegMemberBr15" />
								<t:commandLink id="altIdenMemLOBCommandLink2"									styleClass="clStyle"									actionListener="#{logCaseControllerBean.sortAltIdenForMem}"									rendered="#{logCaseDataBean.imageRender != 'altIdenMemLOBCommandLink2'}" onmousedown="javascript:flagWarn=false;focusPage('lgCsCaseRegFocusPage');">
									<h:graphicImage id="PROVIDERGI20120731164811298" alt="#{ent['label.ent.forDescending']}" title="#{ent['label.ent.forDescending']}" styleClass="sort_desc"
										width="8" url="/images/x.gif"/>
									<f:attribute name="columnName" value="altIden_Mem_LOB" />
									<f:attribute name="sortOrder" value="desc" />
								</t:commandLink>
							</h:panelGroup>
						</h:panelGrid>
					</f:facet>
						<h:outputText id="logCaseCaseRegMemberOutTxt28" value="#{altIdenMemResult.lineOfBusinessDesc}" />
				</t:column>
			</t:dataTable>
			<t:dataTable var="dummyaltIden_Mem"			rendered="#{!logCaseDataBean.showAlternateIdentifiersMem}"			styleClass="dataTable" cellspacing="0" width="100%" border="0"			headerClass="tableHead" rowClasses="rowone,row_alt"			id="altIden_MemdummyTable">
			<t:column id="dummyaltIdenMemvcolumn1">
				<f:facet name="header">
					<h:outputText id="logCaseCaseRegMemberOutTxt29" value="#{msg['label.case.caseRegarding.type']}" />
				</f:facet>
			</t:column>
			<t:column id="dummyaltIdenMemcolumn2">
				<f:facet name="header">
					<h:outputText id="logCaseCaseRegMemberOutTxt30"						value="#{msg['label.case.caseRegarding.alternateID']}" />
				</f:facet>
			</t:column>
			<t:column id="dummyaltIdenMemcolumn3">
				<f:facet name="header">
					<h:outputText id="logCaseCaseRegMemberOutTxt31"						value="#{msg['label.case.caseRegarding.lineOfBusiness']}" />
				</f:facet>
			</t:column>
		</t:dataTable>
		<m:table id="logCaseCaseRegMemberTb2" styleClass="dataTable" width="100%" border="0"
			rendered="#{!logCaseDataBean.showAlternateIdentifiersMem}">
			<m:tr id="logCaseCaseRegMemberTr6">
				<m:td id="logCaseCaseRegMemberTd15" align="center">
					<h:outputText id="logCaseCaseRegMemberOutTxt32" value="#{msg['value.noData']}" />
				</m:td>
			</m:tr>
		</m:table>
		<m:br id="logCaseCaseRegMemberBr16" />
	</m:div>
</m:section>
