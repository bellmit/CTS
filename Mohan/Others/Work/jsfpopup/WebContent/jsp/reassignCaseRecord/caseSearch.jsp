<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<portlet:defineObjects />

<f:loadBundle
	basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_ReassignCaseRecords"
	var="msg1" />

<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.ProgramMessages"
	var="cont" />
	<t:saveState id="CMGTTOMSS501" value="#{caseSearchDataBean}"></t:saveState>	
	<h:inputHidden id="PRGCMGTIH31" value="#{caseSearchControllerBean.loadValidValue}" rendered="#{caseSearchDataBean.validValueFlag}"></h:inputHidden>
<m:table id="PROVIDERMMT20120731164811643" width="100%">
			<m:tr>
				<m:td>
					<m:div styleClass="msgBox"
						rendered="#{caseSearchDataBean.success}">
						<h:outputText id="PRGCMGTOT1733" value="#{msg['success.saved']}" />
					</m:div>
				</m:td>
			</m:tr>
</m:table>
<m:table id="PROVIDERMMT20120731164811644" cellspacing="2" width="95%">
	<m:tr>
		<m:td>
			<m:div styleClass="padb">
				<h:outputLabel id="PRGCMGTOLL684" for="EntityID">
					<h:outputText id="PRGCMGTOT1734" value="#{msg1['label.searchCase.entityID']}" />
				</h:outputLabel>
				<m:br></m:br>
				<h:inputText id="EntityID" size="10" maxlength="80" onmousedown="javascript:flagWarn=false;"
				
					value="#{caseSearchDataBean.caseRecordSearchCriteriaVO.entityId}" />
				<m:br />
				<h:message id="PROVIDERM20120731164811645" for="EntityID" styleClass="errorMessage" />
			</m:div>
		</m:td>
		<m:td>

			<m:div styleClass="padb">
				<m:span styleClass="required">
					<h:outputText id="PRGCMGTOT1735" value="#{cont['label.ref.reqSymbol']}" />
				</m:span>
				<h:outputLabel id="PRGCMGTOLL685" for="CaseSearchAssignedTo">
					<h:outputText id="PRGCMGTOT1736" value="#{msg1['label.searchCase.assignedTo']}" />
				</h:outputLabel>
				<m:br />
				<h:selectOneMenu id="CaseSearchAssignedTo" onmousedown="javascript:flagWarn=false;"
				
					value="#{caseSearchDataBean.caseRecordSearchCriteriaVO.assignedTo}">

					<f:selectItems value="#{caseSearchDataBean.assignedTo}" />
				</h:selectOneMenu>
				<m:br />
				<h:message id="PRGCMGTM225" for="CaseSearchAssignedTo" styleClass="errorMessage" />
				<m:br />

			</m:div>
		</m:td>
		<m:td>
			<m:div styleClass="padb">
				<h:outputLabel id="PRGCMGTOLL686" for="CaseSearchStatus">
					<h:outputText id="PRGCMGTOT1737" value="#{msg1['label.searchCase.status']}" />
				</h:outputLabel>
				<m:br></m:br>
				<h:selectOneMenu id="CaseSearchStatus" onmousedown="javascript:flagWarn=false;"
				
					value="#{caseSearchDataBean.caseRecordSearchCriteriaVO.status}">

					<f:selectItems value="#{caseSearchDataBean.status}" />
				</h:selectOneMenu>

				<m:br></m:br>

			</m:div>
		</m:td>
		<m:td>
			<m:div styleClass="padb">
				<h:outputLabel id="PRGCMGTOLL687" for="CaseSearchCreatedBy">
					<h:outputText id="PRGCMGTOT1738" value="#{msg1['label.searchCase.createdBy']}" />
				</h:outputLabel>
				<m:br></m:br>
				<h:selectOneMenu id="CaseSearchCreatedBy" onmousedown="javascript:flagWarn=false;"
				
					value="#{caseSearchDataBean.caseRecordSearchCriteriaVO.createdBy}">

					<f:selectItems value="#{caseSearchDataBean.createdBy}" />
				</h:selectOneMenu>
				<m:br></m:br>

			</m:div>
		</m:td>
	</m:tr>
	<m:tr>
		<m:td>
			<m:div styleClass="padb">
				<h:outputLabel id="PRGCMGTOLL688" for="CaseSearchEntityType">
					<h:outputText id="PRGCMGTOT1739" value="#{msg1['label.searchCase.entityType']}" />
				</h:outputLabel>
				<m:br></m:br>
				<h:selectOneMenu id="CaseSearchEntityType" onmousedown="javascript:flagWarn=false;"
				
					value="#{caseSearchDataBean.caseRecordSearchCriteriaVO.entityType}">

					<f:selectItems value="#{caseSearchDataBean.entityType}" />
				</h:selectOneMenu>
				<m:br />
				<h:message id="PRGCMGTM226" for="CaseSearchEntityType" styleClass="errorMessage" />
			</m:div>
		</m:td>
		<m:td>
			<m:div styleClass="padb">
				<h:outputLabel id="PRGCMGTOLL689" for="CaseRecordNumber">
					<h:outputText id="PRGCMGTOT1740" value="#{msg1['label.searchCase.caseRecordNumber']}" />
				</h:outputLabel>
				<m:br></m:br>
				<h:inputText id="CaseRecordNumber" size="10" maxlength="80" onmousedown="javascript:flagWarn=false;"
				
					value="#{caseSearchDataBean.caseRecordSearchCriteriaVO.caseRecordNumber}" />
				<m:br></m:br>
				<h:message id="PROVIDERM20120731164811646" for="CaseRecordNumber" styleClass="errorMessage" />
			</m:div>
		</m:td>

		<m:td>
			<m:div styleClass="padb">
				<h:outputLabel id="PROVIDEROLL20120731164811647" for="CaseSearchStatusDate">
					<h:outputText id="PROVIDEROLT20120731164811648" value="#{msg1['label.searchCase.statusDate']}" />
				</h:outputLabel>
				<m:br />
				<m:inputCalendar id="CaseSearchStatusDate" size="10"
					monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
					currentDayCellClass="currentDayCell" renderAsPopup="true"
					addResources="true" popupDateFormat="MM/dd/yyyy"
					renderPopupButtonAsImage="true"
					value="#{caseSearchDataBean.caseRecordSearchCriteriaVO.statusDate}" />
				<m:br />
				<h:message id="PRGCMGTM228" for="CaseSearchStatusDate" styleClass="errorMessage" />
			</m:div>
		</m:td>
		<m:td>
			<m:div styleClass="padb">
				<h:outputLabel id="PRGCMGTOLL691" for="CaseSearchPriority">
					<h:outputText id="PRGCMGTOT1742" value="#{msg1['label.searchCase.priority']}" />
				</h:outputLabel>
				<m:br></m:br>
				<h:selectOneMenu id="CaseSearchPriority" onmousedown="javascript:flagWarn=false;"
				
					value="#{caseSearchDataBean.caseRecordSearchCriteriaVO.priority}">

					<f:selectItems value="#{caseSearchDataBean.priority}" />
				</h:selectOneMenu>
				<m:br></m:br>

			</m:div>
		</m:td>
	</m:tr>
	<m:tr>
		<m:td>
			<m:div styleClass="padb">
				<h:outputLabel id="PRGCMGTOLL692" for="caseTitle">
					<h:outputText id="PRGCMGTOT1743" value="#{msg1['label.searchCase.caseTitle']}" />
				</h:outputLabel>
				<m:br></m:br>
				<h:inputText id="caseTitle" size="10" maxlength="80" onmousedown="javascript:flagWarn=false;"
				
					value="#{caseSearchDataBean.caseRecordSearchCriteriaVO.caseTitle}" />
				<m:br></m:br>
				<h:message id="PROVIDERM20120731164811649" for="caseTitle" styleClass="errorMessage" />
			</m:div>
		</m:td>
	</m:tr>
</m:table>
<t:htmlTag value="h4">
	<t:commandLink action="#{caseSearchControllerBean.getAdvOptionsPlus}"
		onmousedown="javascript:flagWarn=false;" id="plusid" styleClass="plus"
		rendered="#{ not caseSearchDataBean.advOptionsFlag}">
		<h:outputText id="advancedSearch_textPlus"
			value="#{msg1['label.searchCase.advanceSearchOpeions']}" />
	</t:commandLink>

	<t:commandLink action="#{caseSearchControllerBean.getAdvOptionsMinus}"
		onmousedown="javascript:flagWarn=false;" id="minusid"
		styleClass="minus" rendered="#{caseSearchDataBean.advOptionsFlag}">
		<h:outputText id="advancedSearch_textMinus"
			value="#{msg1['label.searchCase.advanceSearchOpeions']}" />
	</t:commandLink>
	<h:inputHidden id="advancedSearchHiddenID"
		value="#{caseSearchDataBean.plus}" />
</t:htmlTag>
<m:div sid="showhide_AdvencedOpt" rendered="#{caseSearchDataBean.advOptionsFlag}">
	<m:table id="PROVIDERMMT20120731164811650" cellspacing="0" width="95%">
		<m:tr>
			<m:td>
				<m:div styleClass="padb">
					<h:outputLabel id="PRGCMGTOLL693" for="CaseSearchReportingUnit">
						<h:outputText id="PRGCMGTOT1744" value="#{msg1['label.searchCase.reportingUnit']}" />
					</h:outputLabel>
					<m:br></m:br>
					<h:selectOneMenu id="CaseSearchReportingUnit" onmousedown="javascript:flagWarn=false;"
					
						value="#{caseSearchDataBean.caseRecordSearchCriteriaVO.reportingUnit}"
						valueChangeListener="#{caseSearchControllerBean.onReportingUnitChange}"
						onchange="this.form.submit()">

						<f:selectItems value="#{caseSearchDataBean.reportingUnit}" />
					</h:selectOneMenu>

					<m:br></m:br>

				</m:div>
			</m:td>
			<m:td>
				<m:div styleClass="padb">
					<h:outputLabel id="PRGCMGTOLL694" for="CaseSearchCreatedFrom">
						<h:outputText id="PRGCMGTOT1745" value="#{msg1['label.searchCase.createdFrom']}" />
					</h:outputLabel>
					<m:br />
					<m:inputCalendar id="CaseSearchCreatedFrom" size="10"
						monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
						currentDayCellClass="currentDayCell" renderAsPopup="true"
						addResources="true" popupDateFormat="MM/dd/yyyy"
						renderPopupButtonAsImage="true"
						value="#{caseSearchDataBean.caseRecordSearchCriteriaVO.createdFrom}" />
					<m:br />
					<h:message id="PRGCMGTM230" for="CaseSearchCreatedFrom" styleClass="errorMessage" />
				</m:div>
			</m:td>
			<m:td>
				<m:div styleClass="padb">
					<h:outputLabel id="PRGCMGTOLL695" for="AdvCreatedBy">
						<h:outputText id="PRGCMGTOT1746" value="#{msg1['label.searchCase.createdBy']}" />
					</h:outputLabel>
					<m:br></m:br>
					<h:selectOneMenu id="AdvCreatedBy" onmousedown="javascript:flagWarn=false;"
					
						value="#{caseSearchDataBean.caseRecordSearchCriteriaVO.advCreatedBy}">

						<f:selectItems value="#{caseSearchDataBean.advCreatedBy}" />
					</h:selectOneMenu>
					<m:br></m:br>

				</m:div>
			</m:td>
			<m:td>
				<m:div styleClass="padb">
					<h:outputLabel id="PRGCMGTOLL696" for="BusinessGroup">
						<h:outputText id="PRGCMGTOT1747" value="#{msg1['label.searchCase.businessGroup']}" />
					</h:outputLabel>
					<m:br></m:br>
					<h:selectOneMenu id="BusinessGroup" onmousedown="javascript:flagWarn=false;"
					
						value="#{caseSearchDataBean.caseRecordSearchCriteriaVO.lineOfBusinessGroup}">
                       

						<f:selectItems value="#{caseSearchDataBean.lobVVList}" />
					</h:selectOneMenu>
					<m:br></m:br>

				</m:div>
			</m:td>
		</m:tr>
		<m:tr>
			<m:td>
				<m:div styleClass="padb">
					<h:outputLabel id="PRGCMGTOLL697" for="CaseSearchBusinessUnit">
						<h:outputText id="PRGCMGTOT1748" value="#{msg1['label.searchCase.businessUnit']}" />
					</h:outputLabel>
					<m:br></m:br>
					<h:selectOneMenu id="CaseSearchBusinessUnit" onmousedown="javascript:flagWarn=false;"
						value="#{caseSearchDataBean.caseRecordSearchCriteriaVO.businessUnit}"
						valueChangeListener="#{caseSearchControllerBean.onBusinessUnitChange}"
						onchange="this.form.submit()">

						<f:selectItems value="#{caseSearchDataBean.businessUnit}" />
					</h:selectOneMenu>
					<m:br></m:br>

				</m:div>
			</m:td>
			<m:td>
				<m:div styleClass="padb">
					<h:outputLabel id="PRGCMGTOLL698" for="CaseSearchCreatedTo">
						<h:outputText id="PRGCMGTOT1749" value="#{msg1['label.searchCase.createdTo']}" />
					</h:outputLabel>
					<m:br />
					<m:inputCalendar id="CaseSearchCreatedTo" size="10"
						monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
						currentDayCellClass="currentDayCell" renderAsPopup="true"
						addResources="true" popupDateFormat="MM/dd/yyyy"
						renderPopupButtonAsImage="true"
						value="#{caseSearchDataBean.caseRecordSearchCriteriaVO.createdTo}" />
					<m:br />
					<h:message id="PRGCMGTM231" for="CaseSearchCreatedTo" styleClass="errorMessage" />
				</m:div>
			</m:td>
			<m:td>
				<m:div styleClass="padb">
					<h:outputLabel id="PRGCMGTOLL699" for="CaseSearchCaseType">
						<h:outputText id="PRGCMGTOT1750" value="#{msg1['label.searchCase.caseType']}" />
					</h:outputLabel>
					<m:br></m:br>
					<h:selectOneMenu id="CaseSearchCaseType" onmousedown="javascript:flagWarn=false;"
						value="#{caseSearchDataBean.caseRecordSearchCriteriaVO.caseTypeSK}">
							<f:selectItem itemValue="" itemLabel="please select one" />
						<f:selectItems value="#{caseSearchDataBean.caseType}" />
					</h:selectOneMenu>
					<m:br></m:br>

				</m:div>
			</m:td>
			<m:td>
				<m:div styleClass="padb">
					<h:outputLabel id="PRGCMGTOLL700" for="ResponseExists">
						<h:outputText id="PRGCMGTOT1751" value="#{msg1['label.searchCase.responseExists']}" />
					</h:outputLabel>
					<m:br></m:br>
					<h:selectOneRadio id="ResponseExists"
						value="#{caseSearchDataBean.caseRecordSearchCriteriaVO.responseExists}"
						enabledClass="black">
						<f:selectItems value="#{caseSearchDataBean.responseExistsList}" />
					</h:selectOneRadio>

					<m:br></m:br>
				</m:div>
			</m:td>
		</m:tr>
		<m:tr>
			<m:td>
				<m:div styleClass="padb">
					<h:outputLabel id="PRGCMGTOLL701" for="CaseSearchDepartments">
<%--  modified for defect Id ESPRD00300140 --%>
					
<h:outputText id="PRGCMGTOT1752" value="#{msg1['label.searchCase.workUnit']}" />
<%-- EOF defect ID ESPRD00300140 --%>
					</h:outputLabel>
					<m:br></m:br>
					<h:selectOneMenu id="CaseSearchDepartments" onmousedown="javascript:flagWarn=false;"
						value="#{caseSearchDataBean.caseRecordSearchCriteriaVO.departments}"
						valueChangeListener="#{caseSearchControllerBean.onDepartmentChange}"
						onchange="this.form.submit()">

						<f:selectItems value="#{caseSearchDataBean.departments}" />
					</h:selectOneMenu>

					<m:br></m:br>

				</m:div>
			</m:td>
		</m:tr>
	</m:table>
</m:div>
