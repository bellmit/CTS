
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<portlet:defineObjects />
<m:div id="showhide_AdvencedOpt">                  

	<m:table width="100%" id="advCaseSearchTab1Id">
		<m:tr id="advCaseSearchTab1R1d">
			<m:td id="advCaseSearchTab1R1C1d">
				<h:outputLabel for="CaseSearchReportingUnit" value="#{msg1['label.searchCase.reportingUnit']}" id="advCaseSearchOutTxt1Id"/>
				<m:br id="advCaseSearchBr1Id"/>
				<h:selectOneMenu id="CaseSearchReportingUnit"
					onmousedown="javascript:flagWarn=false;"
					value="#{caseSearchDataBean.caseRecordSearchCriteriaVO.reportingUnit}"
					valueChangeListener="#{caseSearchControllerBean.onReportingUnitChange}"
					onchange="this.form.submit()">
					<f:selectItems value="#{caseSearchDataBean.reportingUnit}" />
				</h:selectOneMenu>
				<m:br id="advCaseSearchBr2Id"/>
			</m:td>
			<m:td id="advCaseSearchTab1R1C2d">
			<m:div style="width:140px;">
				<h:outputLabel for="CaseSearchCreatedFrom" value="#{msg1['label.searchCase.createdFrom']}" id="advCaseSearchOutTxt2Id"/>
				<m:br id="advCaseSearchBr3Id"/>
				<m:inputCalendar id="CaseSearchCreatedFrom" size="10"
					monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
					currentDayCellClass="currentDayCell" renderAsPopup="true"
					addResources="true" popupDateFormat="MM/dd/yyyy"
					renderPopupButtonAsImage="true"
					value="#{caseSearchDataBean.caseRecordSearchCriteriaVO.createdFrom}" />
					<m:br id="advCaseSearchBr17Id"/>
					<h:message id="PRGCMGTM52" for="CaseSearchCreatedFrom" styleClass="errorMessage" />
			</m:div>
			</m:td>
			<m:td id="advCaseSearchTab1R1C3d">
				<h:outputLabel for="advCreatedBy" value="#{msg1['label.searchCase.createdBy']}" id="advCaseSearchOutTxt3Id"/>
				<m:br id="advCaseSearchBr4Id"/>
				<h:selectOneMenu id="advCreatedBy"					value="#{caseSearchDataBean.caseRecordSearchCriteriaVO.advCreatedBy}" >

					<f:selectItems value="#{caseSearchDataBean.advCreatedBy}" id="advCaseSearchSelItm1Id"/>
				</h:selectOneMenu>
				<m:br id="advCaseSearchBr5Id"/>
			</m:td>
			<m:td id="advCaseSearchTab1R1C4d">
				<h:outputLabel for="BusinessGroup" value="#{msg1['label.searchCase.businessGroup']}" id="advCaseSearchOutTxt4Id"/>
				<m:br id="advCaseSearchBr6Id"/>
				<h:selectOneMenu id="BusinessGroup"					value="#{caseSearchDataBean.caseRecordSearchCriteriaVO.lineOfBusinessGroup}">
					<f:selectItems value="#{caseSearchDataBean.lobVVList}" id="advCaseSearchSelItm2Id"/>
				</h:selectOneMenu>
				<m:br id="advCaseSearchBr18Id"/>
			</m:td>
		</m:tr>
		<m:tr id="advCaseSearchTab1R2Id">
			<m:td id="advCaseSearchTab1R2C1Id">
				<h:outputLabel for="CaseSearchBusinessUnit" value="#{msg1['label.searchCase.businessUnit']}" id="advCaseSearchOutTxt5Id"/>
				<m:br id="advCaseSearchBr7Id"/>
				<h:selectOneMenu id="CaseSearchBusinessUnit"
					onmousedown="javascript:flagWarn=false;"
					value="#{caseSearchDataBean.caseRecordSearchCriteriaVO.businessUnit}"
					valueChangeListener="#{caseSearchControllerBean.onBusinessUnitChange}"
					onchange="this.form.submit()">
					
					<f:selectItems value="#{caseSearchDataBean.businessUnit}" id="advCaseSearchSelItm3Id"/>
				</h:selectOneMenu>
				<m:br id="advCaseSearchBr8Id"/>
			</m:td>
			<m:td id="advCaseSearchTab1R2C2Id">
			<m:div style="width:140px;">
				<h:outputLabel for="CaseSearchCreatedTo" value="#{msg1['label.searchCase.createdTo']}" id="advCaseSearchOutTxt6Id"/>
				<m:br id="advCaseSearchBr9Id"/>
				<m:inputCalendar id="CaseSearchCreatedTo" size="10"
					monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
					currentDayCellClass="currentDayCell" renderAsPopup="true"
					addResources="true" popupDateFormat="MM/dd/yyyy"
					renderPopupButtonAsImage="true"
					value="#{caseSearchDataBean.caseRecordSearchCriteriaVO.createdTo}" />
					<m:br id="advCaseSearchBr10Id"/>
					<h:message id="PRGCMGTM53" for="CaseSearchCreatedTo" styleClass="errorMessage" />
			</m:div>		
			</m:td>
			<m:td id="advCaseSearchTab1R2C3Id">
				<h:outputLabel for="CaseSearchCaseType" value="#{msg1['label.searchCase.caseType']}" id="advCaseSearchOutTxt7Id"/>
				<m:br id="advCaseSearchBr11Id"/>
				<h:selectOneMenu id="CaseSearchCaseType"					value="#{caseSearchDataBean.caseRecordSearchCriteriaVO.caseTypeSK}">
					<f:selectItem itemValue="" itemLabel="Please Select One" />
					<f:selectItems value="#{caseSearchDataBean.caseType}" id="advCaseSearchSelItm4Id"/>
				</h:selectOneMenu>
				<m:br id="advCaseSearchBr12Id"/>
			</m:td>
			<m:td id="advCaseSearchTab1R2C4Id">
				<h:outputLabel for="ResponseExists" value="#{msg1['label.searchCase.responseExists']}" id="advCaseSearchOutTxt8Id"/>
				<m:br id="advCaseSearchBr13Id"/>
				<h:selectOneRadio id="ResponseExists"					value="#{caseSearchDataBean.caseRecordSearchCriteriaVO.responseExists}">
					<f:selectItem itemLabel="Yes" itemValue="YES" id="advCaseSearchSelItm5Id"/>
					<f:selectItem itemLabel="No" itemValue="NO" id="advCaseSearchSelItm6Id"/>
				</h:selectOneRadio>
				<m:br id="advCaseSearchBr14Id"/>
			</m:td>
		</m:tr>
		<m:tr id="advCaseSearchTab1R3Id">
			<m:td id="advCaseSearchTab1R3C1Id">
				<h:outputLabel for="CaseSearchDepartments" value="#{msg1['label.searchCase.workunit']}" id="advCaseSearchOutTxt9Id"/>
				<m:br id="advCaseSearchBr15Id"/>
				<h:selectOneMenu id="CaseSearchDepartments"
					onmousedown="javascript:flagWarn=false;"
					value="#{caseSearchDataBean.caseRecordSearchCriteriaVO.departments}"
					valueChangeListener="#{caseSearchControllerBean.onDepartmentChange}"
					onchange="this.form.submit()">
					

					<f:selectItems value="#{caseSearchDataBean.departments}" id="advCaseSearchSelItm7Id"/>
				</h:selectOneMenu>
				<m:br id="advCaseSearchBr16Id"/>
			</m:td>
		</m:tr>
	</m:table>
</m:div>
