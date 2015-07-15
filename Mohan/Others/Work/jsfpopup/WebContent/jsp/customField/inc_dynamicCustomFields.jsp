<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<portlet:defineObjects />

<f:loadBundle
	basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_CMContactManagementCase"
	var="msg" />
	
<body>
<m:br />
<h:panelGrid id="PROVIDERPGD20120731164811207">
<table cellpadding="0" cellspacing="0">
<t:dataList id="cfDataListId" value="#{logCaseDataBean.customFieldVOList}" var="customVODetails">
	<m:div rendered="#{customVODetails.dataType == 'DD'}">
	<tr>
		<td>
			<h:outputText id="PROVIDEROLT20120731164811208" styleClass="required" rendered="#{customVODetails.requiredFlag}" value="#{msg['label.case.astrisk']}" />
			<h:outputLabel id="PROVIDEROLL20120731164811209" for="dropDownId11" value="#{customVODetails.labelName} "></h:outputLabel>
		</td>
		<td>
			<h:selectOneMenu id="dropDownId11" value="#{customVODetails.componentInputData}" disabled="#{customVODetails.protectedFlag}">
				<f:selectItem itemValue=" " itemLabel="" />
				<f:selectItems value="#{customVODetails.customFieldDDList}" />
			</h:selectOneMenu>
		</td>
	</tr>
	</m:div>
	<tr>
	<m:div rendered="#{customVODetails.dataType == 'CB'}">
	<tr>
		<td>
			<h:outputText id="PROVIDEROLT20120731164811210" styleClass="required" rendered="#{customVODetails.requiredFlag}" value="#{msg['label.case.astrisk']}" />
			<h:outputLabel id="PROVIDEROLL20120731164811211" for="checkBoxId" value="#{customVODetails.labelName} "></h:outputLabel>
		</td>
		<td>
			<h:selectBooleanCheckbox id="checkBoxId" value="#{customVODetails.checkBoxValue}" disabled="#{customVODetails.protectedFlag}">
			</h:selectBooleanCheckbox>
		</td>
	</tr>
	</m:div>
	</tr>
	<tr>
	<m:div rendered="#{customVODetails.dataType == 'D'}">
		<tr>
			<td>
				<h:outputText id="PROVIDEROLT20120731164811212" styleClass="required" rendered="#{customVODetails.requiredFlag}" value="#{msg['label.case.astrisk']}" />
				<h:outputLabel id="PROVIDEROLL20120731164811213" for="supDate" value="#{customVODetails.labelName} "></h:outputLabel>
			</td>
			<td>
				<m:inputCalendar id="supDate" size="10"
							monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
							currentDayCellClass="currentDayCell" renderAsPopup="true"
							addResources="true" popupDateFormat="MM/dd/yyyy"
							renderPopupButtonAsImage="true"
							disabled="#{customVODetails.protectedFlag}"
							value="#{customVODetails.componentInputData}" />
			</td>
		</tr>
	</m:div>
	</tr>
	<tr>
	<m:div rendered="#{customVODetails.dataType == 'T' ||
						customVODetails.dataType == 'N' ||
						customVODetails.dataType == 'DA'}">
		<tr>
			<td>
				<h:outputText id="PROVIDEROLT20120731164811214" styleClass="required" rendered="#{customVODetails.requiredFlag}" value="#{msg['label.case.astrisk']}" />
				<h:outputLabel id="PROVIDEROLL20120731164811215" for="inputTextId" value="#{customVODetails.labelName} "></h:outputLabel>
			</td>
			<td>
				<h:inputText id="inputTextId" size="#{customVODetails.length}" 
						value="#{customVODetails.componentInputData}" disabled="#{customVODetails.protectedFlag}">
				</h:inputText>
			</td>
		</tr>
	</m:div>
	</tr>
</t:dataList>
</table>
<h:message id="PRGCMGTM108" for="cfDataListId" styleClass="errorMessage" rendered="#{logCaseDataBean.logCaseErrMsgFlag}"/>
</h:panelGrid>
</body>
