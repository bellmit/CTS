<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<portlet:defineObjects />

<m:table id="PROVIDERMMT20120731164811590" width="100%">
	<m:tr>
		<m:td>
		</m:td>
	</m:tr>
</m:table>
<m:table id="PROVIDERMMT20120731164811591" cellspacing="0" width="100%">
	<m:tr>
		<m:td>
			<m:span styleClass="required">
				<h:outputText id="PRGCMGTOT1405" value="#{ref['label.ref.reqSymbol']}" />
			</m:span>
			<h:outputLabel id="PRGCMGTOLL581" for="entTydropdownMem"				value="#{msg['label.entity.entityType']}" />
			<m:br />
			<%-- dropDown will be rendered in ADD Entity Page --%>
			<h:selectOneMenu id="entTydropdownMem"				rendered="#{CMEntityMaintainDataBean.renderEntityTypeDropDown}"				value="#{CMEntityMaintainDataBean.cmEnityDetailVO.entityType}"				onchange="javascript:entityTypeChangeAction(this.value);">

				<f:selectItem itemLabel="#{msg['label.entity.Please.Select.One']}" itemValue="" />
				<f:selectItems value="#{CMEntityMaintainDataBean.entityTypeListME}" />
			</h:selectOneMenu>

			<%-- dropDown will be rendered in Maintain Entity Page --%>
			<h:outputText id="PRGCMGTOT1406"				value="#{CMEntityMaintainDataBean.cmEnityDetailVO.entityTypeWithDesc}"				rendered="#{CMEntityMaintainDataBean.renderEntityTypeText}"></h:outputText>
			<m:br />
			<h:message id="PRGCMGTM195" for="entTydropdownMem" showDetail="true"				styleClass="errorMessage" />
		</m:td>
		<m:td>
			<h:outputLabel id="PRGCMGTOLL582" for="cmetyi" value="#{msg['label.entity.cmEntityID']}" />
			<m:br></m:br>
			<h:outputText id="cmetyi"				value="#{CMEntityMaintainDataBean.cmEnityDetailVO.cmEntityID}" />
			<m:br></m:br>
		</m:td>

		<m:td></m:td>
		<m:td></m:td>
		<m:td></m:td>


	</m:tr>



	<m:tr>

		<m:td>
			<h:outputLabel id="PRGCMGTOLL583" for="prf" value="#{msg['label.entity.Prefix']}" />

			<m:br></m:br>
			<h:selectOneMenu id="prf"	disabled="#{!CMEntityMaintainControllerBean.controlRequired}"			value="#{CMEntityMaintainDataBean.cmEnityDetailVO.prefix}">
				<f:selectItem itemValue="" itemLabel="Please Select One" />
				<f:selectItems value="#{CMEntityMaintainDataBean.prefixListME}" />
			</h:selectOneMenu>
		</m:td>
		<m:td>
			<m:span styleClass="required">
				<h:outputText id="PRGCMGTOT1407" value="#{ref['label.ref.reqSymbol']}" />
			</m:span>
			<h:outputLabel id="PRGCMGTOLL584" for="ffnamUM" value="#{msg['label.entity.firstName']}" />
			<m:br></m:br>
			<h:inputText id="ffnamUM" size="25" maxlength="25"		disabled="#{!CMEntityMaintainControllerBean.controlRequired}"		value="#{CMEntityMaintainDataBean.cmEnityDetailVO.nameVO.firstName}"></h:inputText>
			<m:br />

			<h:message id="PRGCMGTM196" for="ffnamUM" showDetail="true" styleClass="errorMessage" />
		</m:td>
		<m:td>
			<h:outputLabel id="PRGCMGTOLL585" for="miInput" value="#{msg['label.entity.MI']}" />
			<m:br></m:br>
			<h:inputText id="miInput" size="1" maxlength="25"	disabled="#{!CMEntityMaintainControllerBean.controlRequired}"			value="#{CMEntityMaintainDataBean.cmEnityDetailVO.nameVO.middleName}"></h:inputText>
			<m:br />

			<h:message id="PRGCMGTM197" for="miInput" showDetail="true" styleClass="errorMessage" />
		</m:td>
		<m:td>
			<m:span styleClass="required">
				<h:outputText id="PRGCMGTOT1408" value="#{ref['label.ref.reqSymbol']}" />
			</m:span>
			<h:outputLabel id="PRGCMGTOLL586" for="llnamUM" value="#{msg['label.entity.lastName']}" />
			<m:br></m:br>

			<h:inputText id="llnamUM" size="25" maxlength="35"	disabled="#{!CMEntityMaintainControllerBean.controlRequired}"			value="#{CMEntityMaintainDataBean.cmEnityDetailVO.nameVO.lastName}"></h:inputText>
			<m:br />

			<h:message id="PRGCMGTM198" for="llnamUM" showDetail="true" styleClass="errorMessage" />
		</m:td>
		<m:td>
			<h:outputLabel id="PRGCMGTOLL587" for="suffInput" value="#{msg['label.entity.suffix']}" />
			<m:br></m:br>
			<h:inputText id="suffInput" size="5" maxlength="10"	 disabled="#{!CMEntityMaintainControllerBean.controlRequired}"			value="#{CMEntityMaintainDataBean.cmEnityDetailVO.nameVO.suffixName}"></h:inputText>
			<m:br />

			<h:message id="PRGCMGTM199" for="suffInput" showDetail="true" styleClass="errorMessage" />
		</m:td>
	</m:tr>


	<m:tr>
		<m:td>
			<h:outputLabel id="PRGCMGTOLL588" for="ssnInput" value="#{msg['label.entity.SSN']}" />
			<m:br></m:br>
			<h:inputText id="ssnInput" size="10" maxlength="11"	 disabled="#{!CMEntityMaintainControllerBean.controlRequired}"			value="#{CMEntityMaintainDataBean.cmEnityDetailVO.ssnNumber}"></h:inputText>
			<m:br/>	
			<h:message id="PRGCMGTM200" for="ssnInput" showDetail="true" styleClass="errorMessage" />
		</m:td>
		<m:td>
			<h:outputLabel id="PRGCMGTOLL589" for="einInput" value="#{msg['label.entity.EIN']}" />
			<m:br></m:br>
			<h:inputText id="einInput" size="10" maxlength="9"	disabled="#{!CMEntityMaintainControllerBean.controlRequired}"			value="#{CMEntityMaintainDataBean.cmEnityDetailVO.employeeIdentificationNumber}"></h:inputText>
			<m:br/>	
			<h:message id="PRGCMGTM201" for="einInput" showDetail="true" styleClass="errorMessage" />	
		</m:td>
		<m:td>
			<h:outputLabel id="PRGCMGTOLL590" for="lobdropdwn"				value="#{msg['label.entity.lineOFBusiness']}" />
			<m:br></m:br>
			<h:selectOneMenu id="lobdropdwn"	disabled="#{!CMEntityMaintainControllerBean.controlRequired}"			value="#{CMEntityMaintainDataBean.cmEnityDetailVO.lineOfBusiness}">
				<f:selectItem itemValue="" itemLabel="Please select One" />
				<f:selectItems value="#{CMEntityMaintainDataBean.lobListME}" />
			</h:selectOneMenu>
		</m:td>
		<m:td></m:td>
		<m:td></m:td>
	</m:tr>
</m:table>

