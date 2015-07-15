<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<portlet:defineObjects />

<m:table id="CTMGMT_20120731164811586" cellspacing="0" width="100%">
	<m:tr>
		<m:td>
			<m:span styleClass="required">
				<h:outputText id="CTMGMT1377"
					value="#{ref['label.ref.reqSymbol']}" />
			</m:span>
			<h:outputLabel id="CTMGMTOLL568" for="entTypeDC"
				value="#{msg['label.entity.entityType']}" />
			<m:br></m:br>
			<%-- dropDown will be rendered in ADD Entity Page --%>
			<h:selectOneMenu id="entTypeDC"
				disabled="#{!CMEntityMaintainControllerBean.controlRequired}"
				rendered="#{CMEntityMaintainDataBean.renderEntityTypeDropDown}"
				value="#{CMEntityMaintainDataBean.cmEnityDetailVO.entityType}"
				onchange="javascript:entityTypeChangeAction(this.value);">

				<f:selectItem itemLabel="#{msg['label.entity.Please.Select.One']}"
					itemValue="" />
				<f:selectItems value="#{CMEntityMaintainDataBean.entityTypeListME}" />
			</h:selectOneMenu>

			<%-- dropDown will be rendered in Maintain Entity Page --%>
			<h:outputText id="CTMGMTOT1378"
				value="#{CMEntityMaintainDataBean.cmEnityDetailVO.entityTypeWithDesc}"
				rendered="#{CMEntityMaintainDataBean.renderEntityTypeText}"></h:outputText>
			<m:br />
			<h:message id="CTMGMT187" for="entTypeDC" showDetail="true"
				styleClass="errorMessage" />
			<m:br></m:br>

		</m:td>

		<m:td>
			<h:outputLabel id="CTMGMT569" for="dcEID"
				value="#{msg['label.entity.cmEntityID']}" />
			<m:br></m:br>
			<h:outputText id="dcEID"
				value="#{CMEntityMaintainDataBean.cmEnityDetailVO.cmEntityID}" />
			<m:br></m:br>
			<m:br></m:br>
		</m:td>

		<m:td>
			<m:span styleClass="required">
				<h:outputText id="CTMGMT1379"
					value="#{ref['label.ref.reqSymbol']}" />
			</m:span>
			<h:outputLabel id="PRGCMGTOLL570" for="orgNCD11"
				value="#{msg['label.entity.orgName']}" />
			<m:br></m:br>
			<h:inputText id="orgNCD11" size="40" maxlength="40"
				value="#{CMEntityMaintainDataBean.cmEnityDetailVO.organizationName}"></h:inputText>
			<m:br />
			<h:message id="PRGCMGTM188" for="orgNCD11" showDetail="true"
				styleClass="errorMessage" />
		</m:td>
	</m:tr>
</m:table>
