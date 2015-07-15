<%-- 
  - Author(s): Wipro
  - Date: Mon Jan 29 14:37:45 IST 2007
  - Copyright Notice: Copyright (c) 2006 Affiliated Computer Services, Inc.
  - @ 2006
  - Description: 
  --%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>

<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>

<m:div id="defaultsTab">
	<m:table id="PROVIDERMMT20120731164811523" styleClass="dataTable" width="100%">
		<m:tr>
			<m:td>
				<h:outputLabel id="priorityLabel" for="priority"					value="#{ctg['label.category.priority']}" />
				<m:br />
				
				<%--disabled attribute is modified below for fixing the defect ESPRD00802214 on 03/07/2012--%>
				
				<%--<h:selectOneMenu id="priority" disabled="#{CategoryDataBean.categoryVO.inactive}"					value="#{CategoryDataBean.categoryVO.priority}" > --%>
				<h:selectOneMenu id="priority" disabled="#{!CategoryControllerBean.controlRequired}"					value="#{CategoryDataBean.categoryVO.priority}" >
					<f:selectItem itemLabel="Please Select One" itemValue="" />
					<f:selectItems value="#{CategoryDataBean.refPriorityList}" />
				</h:selectOneMenu>
			</m:td>
			<m:td>
				<h:outputLabel id="dtkciiwLabel" for="dtkciiw"					value="#{ctg['label.category.dtkciiw']}" />
				<m:br />
				
				<%--disabled attribute is modified below for fixing the defect ESPRD00802214 on 03/07/2012--%>
				
				<%--<h:inputText id="dtkciiw" size="4" maxlength="4"					disabled="#{CategoryDataBean.categoryVO.inactive}"					value="#{CategoryDataBean.categoryVO.numOfDaysToKeep}" > --%>
				<h:inputText id="dtkciiw" size="4" maxlength="4"					disabled="#{!CategoryControllerBean.controlRequired}"					value="#{CategoryDataBean.categoryVO.numOfDaysToKeep}" >
				</h:inputText>
				<m:br />
				<h:message id="PRGCMGTM172" for="dtkciiw" showDetail="true" styleClass="errorMessage"/>
			</m:td>
			</m:tr>
			<m:tr>
			<m:td>
			</m:td>
			<m:td>
				<h:outputLabel id="nodbetmLabel" for="nodbetm"					value="#{ctg['label.category.nodbetm']}" />
				<m:br />
				
				<%--disabled attribute is modified below for fixing the defect ESPRD00802214 on 03/07/2012--%>
				
				<%--<h:inputText id="nodbetm" size="4" maxlength="4"					disabled="#{CategoryDataBean.categoryVO.inactive}"					value="#{CategoryDataBean.categoryVO.numOfDaysBeforeEscToMed}" > --%>
				<h:inputText id="nodbetm" size="4" maxlength="4"					disabled="#{!CategoryControllerBean.controlRequired}"					value="#{CategoryDataBean.categoryVO.numOfDaysBeforeEscToMed}" >
				</h:inputText>
				<m:br />
				<h:message id="PRGCMGTM171" for="nodbetm" showDetail="true" styleClass="errorMessage"/>
			</m:td>
			</m:tr>
			<m:tr>
			<m:td>
			</m:td>
			<m:td>
				<h:outputLabel id="nodbethLabel" for="nodbeth"					value="#{ctg['label.category.nodbeth']}" />
				<m:br />
				
				<%--disabled attribute is modified below for fixing the defect ESPRD00802214 on 03/07/2012--%>
				
				<%--<h:inputText id="nodbeth" size="4" maxlength="4"					disabled="#{CategoryDataBean.categoryVO.inactive}"					value="#{CategoryDataBean.categoryVO.numOfDaysBeforeEscToHigh}" > --%>
				<h:inputText id="nodbeth" size="4" maxlength="4"					disabled="#{!CategoryControllerBean.controlRequired}"					value="#{CategoryDataBean.categoryVO.numOfDaysBeforeEscToHigh}" >
				</h:inputText>
				<m:br />
				<h:message id="PRGCMGTM173" for="nodbeth" showDetail="true" styleClass="errorMessage"/>
			</m:td>
		</m:tr>
		<m:tr>
		
			<m:td>
			</m:td>
			<m:td>
				<h:outputLabel id="nodbetuLabel" for="nodbetu"					value="#{ctg['label.category.nodbetu']}" />
				<m:br />
				
				<%--disabled attribute is modified below for fixing the defect ESPRD00802214 on 03/07/2012--%>
				
				<%--<h:inputText id="nodbetu" size="4" maxlength="4"					disabled="#{CategoryDataBean.categoryVO.inactive}"					value="#{CategoryDataBean.categoryVO.numOfDaysBeforeEscToUrg}" > --%>
				<h:inputText id="nodbetu" size="4" maxlength="4"					disabled="#{!CategoryControllerBean.controlRequired}"					value="#{CategoryDataBean.categoryVO.numOfDaysBeforeEscToUrg}" >
				</h:inputText>
				<m:br />
				<h:message id="PRGCMGTM174" for="nodbetu" showDetail="true" styleClass="errorMessage"/>
			</m:td>
		</m:tr>
	</m:table>
</m:div>
