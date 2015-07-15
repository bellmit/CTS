<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<portlet:defineObjects />

<m:table id="PROVIDERMMT20120731164811586" cellspacing="0" width="100%">
	<m:tr>
		<m:td>
			<m:span styleClass="required">
				<h:outputText id="PRGCMGTOT1377" value="#{ref['label.ref.reqSymbol']}" />
			</m:span>
			<h:outputLabel id="PRGCMGTOLL568" for="entTypeBO" value="#{msg['label.entity.entityType']}" />
			<m:br></m:br>
			<%-- dropDown will be rendered in ADD Entity Page --%>
			<h:selectOneMenu id="entTypeBO"		disabled="#{!CMEntityMaintainControllerBean.controlRequired}"		rendered="#{CMEntityMaintainDataBean.renderEntityTypeDropDown}"				value="#{CMEntityMaintainDataBean.cmEnityDetailVO.entityType}"				onchange="javascript:entityTypeChangeAction(this.value);">
				
			    <f:selectItem itemLabel="#{msg['label.entity.Please.Select.One']}" itemValue="" />
			   <f:selectItems value="#{CMEntityMaintainDataBean.entityTypeListME}" /> 
              </h:selectOneMenu>  
				
			<%-- dropDown will be rendered in Maintain Entity Page --%>
			<h:outputText id="PRGCMGTOT1378"				value="#{CMEntityMaintainDataBean.cmEnityDetailVO.entityTypeWithDesc}"				rendered="#{CMEntityMaintainDataBean.renderEntityTypeText}"></h:outputText>
			 <m:br/>
			<h:message id="PRGCMGTM187" for="entTypeBO" showDetail="true"			   styleClass="errorMessage" />	
			   <m:br></m:br>
			   
		</m:td>
		
		<m:td>
			<h:outputLabel id="PRGCMGTOLL569" for="boEID" value="#{msg['label.entity.cmEntityID']}" />
			<m:br></m:br>
			<h:outputText id="boEID"				value="#{CMEntityMaintainDataBean.cmEnityDetailVO.cmEntityID}" />
			<m:br></m:br>
			<m:br></m:br>
		</m:td>
	
		<m:td>
			<m:span styleClass="required">
				<h:outputText id="PRGCMGTOT1379" value="#{ref['label.ref.reqSymbol']}" />
			</m:span>
			<h:outputLabel id="PRGCMGTOLL570" for="orgNCD" value="#{msg['label.entity.orgName']}" />
			<m:br></m:br>
			<h:inputText id="orgNCD" size="40" maxlength="40"				value="#{CMEntityMaintainDataBean.cmEnityDetailVO.organizationName}"></h:inputText>	
			<m:br/>	
			<h:message id="PRGCMGTM188" for="orgNCD" showDetail="true" styleClass="errorMessage" />			
		</m:td>
		<m:td>
			<h:outputLabel id="PRGCMGTOLL571" for="npi" value="#{msg['label.entity.NPI']}" />
			<m:br></m:br>
			<h:inputText id="npi" size="40" maxlength="40"	disabled="#{!CMEntityMaintainControllerBean.controlRequired}"			value="#{CMEntityMaintainDataBean.cmEnityDetailVO.nationalProviderID}"></h:inputText>
				<m:br/>
			
		</m:td>
	<m:td>
			<h:outputLabel id="PRGCMGTOLL572" for="einInpforBO" value="#{msg['label.entity.EIN']}" />
			<m:br></m:br>
			<h:inputText id="einInpforBO" size="10" maxlength="9"	disabled="#{!CMEntityMaintainControllerBean.controlRequired}"		value="#{CMEntityMaintainDataBean.cmEnityDetailVO.employeeIdentificationNumber}"></h:inputText>
			<m:br/>
			<h:message id="PRGCMGTM189" for="einInpforBO" showDetail="true" styleClass="errorMessage" />	
			<m:br/>
		</m:td>
	</m:tr>
	
	
	
	
</m:table>
