

<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>

<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.ProgramMessages"
	var="ref" />

<m:table id="PROVIDERMMT20120731164811592" cellspacing="0" width="100%">
	<m:tr>
		<m:td>
			<m:span styleClass="required">
				<h:outputText id="PRGCMGTOT1409" value="#{ref['label.ref.reqSymbol']}" />
			</m:span>
			<h:outputLabel id="PRGCMGTOLL591" for="entTypUNProv"				value="#{msg['label.entity.entityType']}" />
			<m:br></m:br>
			<%-- dropDown will be rendered in ADD Entity Page --%>
			<h:selectOneMenu id="entTypUNProv"				rendered="#{CMEntityMaintainDataBean.renderEntityTypeDropDown}"				value="#{CMEntityMaintainDataBean.cmEnityDetailVO.entityType}"				onchange="javascript:entityTypeChangeAction(this.value);">

				<f:selectItem itemLabel="#{msg['label.entity.Please.Select.One']}" itemValue="" />
				<f:selectItems value="#{CMEntityMaintainDataBean.entityTypeListME}" />
			</h:selectOneMenu>

			<%-- dropDown will be rendered in Maintain Entity Page --%>
			<h:outputText id="PRGCMGTOT1410"				value="#{CMEntityMaintainDataBean.cmEnityDetailVO.entityTypeWithDesc}"				rendered="#{CMEntityMaintainDataBean.renderEntityTypeText}"></h:outputText>
			<m:br />
			<h:message id="PRGCMGTM202" for="entTypUNProv" showDetail="true"				styleClass="errorMessage" />
		</m:td>
		<m:td>
			<h:outputLabel id="PRGCMGTOLL592" for="cmEID" value="#{msg['label.entity.cmEntityID']}" />
			<m:br></m:br>
			<h:outputText id="cmEID"				value="#{CMEntityMaintainDataBean.cmEnityDetailVO.cmEntityID}" />
			<m:br></m:br>
		</m:td>


		<%--  render only in update mode --%>
		<m:td rendered="#{CMEntityMaintainDataBean.renderUpdateEntitySave}">

			<h:outputLabel id="PRGCMGTOLL593" for="EntIDType"				value="#{msg['label.entity.entIDType']}" />
			<m:br></m:br>
			<h:selectOneMenu id="EntIDType"		disabled="#{!CMEntityMaintainControllerBean.controlRequired}"		value="#{CMEntityMaintainDataBean.cmEnityDetailVO.entityIDType}">
				<f:selectItem itemValue="" itemLabel="#{msg['label.entity.Please.Select.One']}" />
				<f:selectItems value="#{CMEntityMaintainDataBean.entityIDTypeME}" />
			</h:selectOneMenu>
			<m:br />
			<h:message id="PRGCMGTM203" for="EntIDType" showDetail="true"				styleClass="errorMessage" />
		</m:td>
		<%--  render only in update mode --%>
		<m:td rendered="#{CMEntityMaintainDataBean.renderUpdateEntitySave}">
			<h:outputLabel id="PRGCMGTOLL594" for="EntIDSrch" value="#{msg['label.entity.entID']}" />
			<m:br></m:br>
			<h:inputText id="EntIDSrch" size="15" maxlength="15"				value="#{CMEntityMaintainDataBean.cmEnityDetailVO.entityID}"></h:inputText>
			<h:outputText id="PRGCMGTOT1411" escape="false" value="#{ref['label.ref.singleSpace']}" />
 
			<h:commandButton id="PRGCMGTCB63" styleClass="formBtn"			    rendered = "#{CMEntityMaintainDataBean.renderUpdateEntitySave}"			    disabled="#{!CMEntityMaintainControllerBean.controlRequired}"				value="#{msg['label.entity.validate']}" onclick="javascript:flagWarn=false;"				action="#{CMEntityMaintainControllerBean.validateProviderEntityID}">
			</h:commandButton>
			<m:br />
			<h:message id="PRGCMGTM204" for="EntIDSrch" showDetail="true"				styleClass="errorMessage" />
		</m:td>
		<m:td></m:td>
	</m:tr>

	<m:tr>
		<m:td>
			<m:span styleClass="required">
				<h:outputText id="PRGCMGTOT1412" value="#{ref['label.ref.reqSymbol']}" />
			</m:span>
			<h:outputLabel id="PRGCMGTOLL595" for="orgty" value="#{msg['label.entity.orgType']}" />
			<m:br></m:br>
			<h:selectOneMenu id="orgty" immediate="true"	disabled="#{!CMEntityMaintainControllerBean.controlRequired}"			value="#{CMEntityMaintainDataBean.cmEnityDetailVO.organizationType}"					valueChangeListener="#{CMEntityMaintainControllerBean.checkOrganizaionType}"							onchange="flagWarn=false; this.form.submit();">
				<f:selectItem itemValue="" itemLabel="#{msg['label.entity.Please.Select.One']}" />
				<f:selectItems value="#{CMEntityMaintainDataBean.orgTypeListME}" />
			</h:selectOneMenu>			
			<m:br />
			<h:message id="PRGCMGTM205" for="orgty" showDetail="true" styleClass="errorMessage" />
		</m:td>
		<m:td>
			<h:outputLabel id="PRGCMGTOLL596" for="provty" value="#{msg['label.entity.provType']}" />
			<m:br></m:br>
			<h:selectOneMenu id="provty"	disabled="#{!CMEntityMaintainControllerBean.controlRequired}"			value="#{CMEntityMaintainDataBean.cmEnityDetailVO.providerType}">
				<f:selectItem itemValue="" itemLabel="#{msg['label.entity.Please.Select.One']}" />
				<f:selectItems value="#{CMEntityMaintainDataBean.provTypeListME}" />
			</h:selectOneMenu>
		</m:td>
		<m:td>
			<h:outputLabel id="PRGCMGTOLL597" for="npi" value="#{msg['label.entity.NPI']}" />
			<m:br></m:br>
			<h:inputText size="10" id="npi" maxlength="10"	disabled="#{!CMEntityMaintainControllerBean.controlRequired}"			value="#{CMEntityMaintainDataBean.cmEnityDetailVO.nationalProviderID}"></h:inputText>
		</m:td>
		<m:td></m:td>
		<m:td></m:td>
	</m:tr>

	<%-- This row should be renderd if only Organization Type is individual --%>
	<m:tr rendered="#{CMEntityMaintainDataBean.renderName}">

		<m:td>
			<h:outputLabel id="PRGCMGTOLL598" for="prfex" value="#{msg['label.entity.Prefix']}" />

			<m:br></m:br>
			<h:selectOneMenu id="prfex"		disabled="#{!CMEntityMaintainControllerBean.controlRequired}"		value="#{CMEntityMaintainDataBean.cmEnityDetailVO.prefix}">
				<f:selectItem itemValue="" itemLabel="#{msg['label.entity.Please.Select.One']}" />
				<f:selectItems value="#{CMEntityMaintainDataBean.prefixListME}" />
			</h:selectOneMenu>
		</m:td>
		<m:td>
			<m:span styleClass="required">
				<h:outputText id="PRGCMGTOT1413" value="#{ref['label.ref.reqSymbol']}" />
			</m:span>
			<h:outputLabel id="PRGCMGTOLL599" for="ffnameUP"				value="#{msg['label.entity.firstName']}" />
			<m:br></m:br>
			<h:inputText id="ffnameUP" size="25" maxlength="25"		disabled="#{!CMEntityMaintainControllerBean.controlRequired}"		value="#{CMEntityMaintainDataBean.cmEnityDetailVO.nameVO.firstName}"></h:inputText>

			<m:br />
			<h:message id="PRGCMGTM206" for="ffnameUP" showDetail="true" styleClass="errorMessage" />
		</m:td>
		<m:td>
			<h:outputLabel id="PRGCMGTOLL600" for="miIn" value="#{msg['label.entity.MI']}" />
			<m:br></m:br>
			<h:inputText id="miIn" size="1" maxlength="25"	 disabled="#{!CMEntityMaintainControllerBean.controlRequired}"			value="#{CMEntityMaintainDataBean.cmEnityDetailVO.nameVO.middleName}"></h:inputText>
			<m:br />
			<h:message id="PRGCMGTM207" for="miIn" showDetail="true" styleClass="errorMessage" />
		</m:td>
		<m:td>
			<m:span styleClass="required">
				<h:outputText id="PRGCMGTOT1414" value="#{ref['label.ref.reqSymbol']}" />
			</m:span>
			<h:outputLabel id="PRGCMGTOLL601" for="llnameUP" value="#{msg['label.entity.lastName']}" />
			<m:br></m:br>

			<h:inputText id="llnameUP" size="25" maxlength="35"	 disabled="#{!CMEntityMaintainControllerBean.controlRequired}"			value="#{CMEntityMaintainDataBean.cmEnityDetailVO.nameVO.lastName}"></h:inputText>

			<m:br />
			<h:message id="PRGCMGTM208" for="llnameUP" showDetail="true" styleClass="errorMessage" />
		</m:td>
		<m:td>
			<h:outputLabel id="PRGCMGTOLL602" for="suffIn" value="#{msg['label.entity.suffix']}" />
			<m:br></m:br>
			<h:inputText id="suffIn" size="5" maxlength="10"	disabled="#{!CMEntityMaintainControllerBean.controlRequired}"			value="#{CMEntityMaintainDataBean.cmEnityDetailVO.nameVO.suffixName}"></h:inputText>
			<m:br />
			<h:message id="PRGCMGTM209" for="suffIn" showDetail="true" styleClass="errorMessage" />
		</m:td>
	</m:tr>

	<%-- This row will be rendered if organization type is Individual --%>
	<m:tr rendered="#{CMEntityMaintainDataBean.renderOrgName}">
		<m:td>
			<m:span styleClass="required">
				<h:outputText id="PRGCMGTOT1415" value="#{ref['label.ref.reqSymbol']}" />
			</m:span>
			<h:outputLabel id="PRGCMGTOLL603" for="orgNCD" value="#{msg['label.entity.orgName']}" />
			<m:br></m:br>
			<h:inputText id="orgNCD" size="40" maxlength="50"		disabled="#{!CMEntityMaintainControllerBean.controlRequired}"		value="#{CMEntityMaintainDataBean.cmEnityDetailVO.organizationName}"></h:inputText>	
			<m:br/>	
			<h:message id="PRGCMGTM210" for="orgNCD" showDetail="true" styleClass="errorMessage" />			
		</m:td>
	</m:tr>

	<m:tr>
		<m:td>
			<h:outputLabel id="PRGCMGTOLL604" for="ssnInp" value="#{msg['label.entity.SSN']}" />
			<m:br></m:br>
			<h:inputText id="ssnInp" size="10" maxlength="11"		disabled="#{!CMEntityMaintainControllerBean.controlRequired}"		value="#{CMEntityMaintainDataBean.cmEnityDetailVO.ssnNumber}"></h:inputText>
			<m:br/>
				<h:message id="PRGCMGTM211" for="ssnInp" showDetail="true" styleClass="errorMessage" />
			<m:br/>

		</m:td>
		<m:td>
			<h:outputLabel id="PRGCMGTOLL605" for="einInp" value="#{msg['label.entity.EIN']}" />
			<m:br></m:br>
			<h:inputText id="einInp" size="10" maxlength="9"	disabled="#{!CMEntityMaintainControllerBean.controlRequired}"			value="#{CMEntityMaintainDataBean.cmEnityDetailVO.employeeIdentificationNumber}"></h:inputText>
			<m:br/>
				<h:message id="PRGCMGTM212" for="einInp" showDetail="true" styleClass="errorMessage" />
			<m:br/>
		</m:td>
		<m:td>
			<h:outputLabel id="PRGCMGTOLL606" for="loblist"				value="#{msg['label.entity.lineOFBusiness']}" />
			<m:br></m:br>
			<h:selectOneMenu id="loblist"	disabled="#{!CMEntityMaintainControllerBean.controlRequired}"			value="#{CMEntityMaintainDataBean.cmEnityDetailVO.lineOfBusiness}">
				<f:selectItem itemValue="" itemLabel="#{msg['label.entity.Please.Select.One']}" />
				<f:selectItems value="#{CMEntityMaintainDataBean.lobListME}" />
			</h:selectOneMenu>
		</m:td>
		<m:td></m:td>
		<m:td></m:td>
	</m:tr>
</m:table>
