<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<portlet:defineObjects />

<m:table id="PROVIDERMMT20120731164811588" cellspacing="0" width="100%">
	<m:tr>
		<m:td>
			<m:span styleClass="required">
				<h:outputText id="PRGCMGTOT1383" value="#{ref['label.ref.reqSymbol']}" />
			</m:span>
			<h:outputLabel id="PRGCMGTOLL577" for="entTypeDO"				value="#{msg['label.entity.entityType']}" />
			<m:br></m:br>
			<%-- dropDown will be rendered in ADD Entity Page --%>
			<h:selectOneMenu id="entTypeDO"		disabled="#{!CMEntityMaintainControllerBean.controlRequired}"		rendered="#{CMEntityMaintainDataBean.renderEntityTypeDropDown}"				value="#{CMEntityMaintainDataBean.cmEnityDetailVO.entityType}"				onchange="javascript:entityTypeChangeAction(this.value);">

				<f:selectItem itemLabel="#{msg['label.entity.Please.Select.One']}" itemValue="" />
				<f:selectItems value="#{CMEntityMaintainDataBean.entityTypeListME}" />
			</h:selectOneMenu>



			<%-- dropDown will be rendered in Maintain Entity Page --%>
			<h:outputText id="PRGCMGTOT1384"				value="#{CMEntityMaintainDataBean.cmEnityDetailVO.entityTypeWithDesc}"				rendered="#{CMEntityMaintainDataBean.renderEntityTypeText}"></h:outputText>
			<m:br />
			<h:message id="PRGCMGTM192" for="entTypeDO" showDetail="true" styleClass="errorMessage" />
		</m:td>
		<m:td>
			<m:span styleClass="required">
				<h:outputText id="PRGCMGTOT1385" value="#{ref['label.ref.reqSymbol']}" />
			</m:span>
			<h:outputLabel id="PRGCMGTOLL578" for="distCD" value="#{msg['label.entity.DOC']}" />
			<m:br></m:br>

			<h:selectOneMenu id="distCD" immediate="true"				value="#{CMEntityMaintainDataBean.cmEnityDetailVO.districtOfficeCode}">
				<f:selectItem itemLabel="#{msg['label.entity.Please.Select.One']}" itemValue="" />
				<f:selectItems value="#{CMEntityMaintainDataBean.distOffCodeListME}" />
			</h:selectOneMenu>

			<m:br />
			<h:message id="PRGCMGTM193" for="distCD" showDetail="true" styleClass="errorMessage" />

		</m:td>
		<m:td>
			<h:outputLabel id="PRGCMGTOLL579" for="cidDO" value="#{msg['label.entity.cmEntityID']}" />
			<m:br></m:br>
			<h:outputText id="cidDO"				value="#{CMEntityMaintainDataBean.cmEnityDetailVO.cmEntityID}" />
			<m:br></m:br>
		</m:td>



	</m:tr>



	<m:tr>


		<m:td>
			<m:span styleClass="required">
				<h:outputText id="PRGCMGTOT1386" value="#{ref['label.ref.reqSymbol']}" />
			</m:span>
			<h:outputLabel id="PRGCMGTOLL580" for="doOrgName" value="#{msg['label.entity.orgName']}" />
			<m:br></m:br>
			<h:inputText id="doOrgName" size="40" maxlength="50"	 disabled="#{!CMEntityMaintainControllerBean.controlRequired}"			value="#{CMEntityMaintainDataBean.cmEnityDetailVO.organizationName}"></h:inputText>
			<m:br />
			<h:message id="PRGCMGTM194" for="doOrgName" showDetail="true" styleClass="errorMessage" />
		</m:td>

	</m:tr>



</m:table>
