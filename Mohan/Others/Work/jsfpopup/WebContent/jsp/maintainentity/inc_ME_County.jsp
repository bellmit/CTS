<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<portlet:defineObjects />

<m:table id="PROVIDERMMT20120731164811587" cellspacing="0" width="100%">
	<m:tr>
		<m:td>
			<m:span styleClass="required">
				<h:outputText id="PRGCMGTOT1380" value="#{ref['label.ref.reqSymbol']}" />
			</m:span>
			<h:outputLabel id="PRGCMGTOLL573" for="entTypeCT" value="#{msg['label.entity.entityType']}" />
			<m:br></m:br>
			<%-- dropDown will be rendered in ADD Entity Page --%>
			<h:selectOneMenu id="entTypeCT"		disabled="#{!CMEntityMaintainControllerBean.controlRequired}"	rendered="#{CMEntityMaintainDataBean.renderEntityTypeDropDown}"				value="#{CMEntityMaintainDataBean.cmEnityDetailVO.entityType}"				onchange="javascript:entityTypeChangeAction(this.value);">
				
			    <f:selectItem itemLabel="#{msg['label.entity.Please.Select.One']}" itemValue="" />
			   <f:selectItems value="#{CMEntityMaintainDataBean.entityTypeListME}" /> 
              </h:selectOneMenu>  
				
			<%-- dropDown will be rendered in Maintain Entity Page --%>
			<h:outputText id="PRGCMGTOT1381"				value="#{CMEntityMaintainDataBean.cmEnityDetailVO.entityTypeWithDesc}"				rendered="#{CMEntityMaintainDataBean.renderEntityTypeText}"></h:outputText>
			 <m:br/>
			<h:message id="PRGCMGTM190" for="entTypeCT" showDetail="true"			   styleClass="errorMessage" />	
		</m:td>
		
		
		<m:td>
			<m:span styleClass="required">
				<h:outputText id="PRGCMGTOT1382" value="#{ref['label.ref.reqSymbol']}" />
			</m:span>
			<h:outputLabel id="PRGCMGTOLL574" for="countyCD" value="#{msg['label.entity.CountyCode']}" />
			<m:br></m:br>
			<h:selectOneMenu id="countyCD"	disabled="#{!CMEntityMaintainControllerBean.controlRequired}"		value="#{CMEntityMaintainDataBean.cmEnityDetailVO.countyCode}"				onchange="javascript:countyCodeChangeAction(this.value);">
				<f:selectItem itemValue="" itemLabel="#{msg['label.entity.Please.Select.One']}" />
				<f:selectItems value="#{CMEntityMaintainDataBean.countyCodeListME}" />
			</h:selectOneMenu>
			<h:message id="PRGCMGTM191" for="countyCD" showDetail="true" styleClass="errorMessage" />
		</m:td>
		
		<m:td>
			<h:outputLabel id="PRGCMGTOLL575" for="cmEID" value="#{msg['label.entity.cmEntityID']}" />
			<m:br></m:br>
			<h:outputText id="cmEID"				value="#{CMEntityMaintainDataBean.cmEnityDetailVO.cmEntityID}" />
			<m:br></m:br>
		</m:td>
		
			</m:tr>
	<m:tr>
		<m:td>
			<h:outputLabel id="PRGCMGTOLL576" for="countyName" value="#{msg['label.entity.CountyName']}" />
			<m:br></m:br>
			<h:outputText id="countyName" 				value="#{CMEntityMaintainDataBean.cmEnityDetailVO.countyName}">
			</h:outputText>
				<m:br/>
		</m:td>
	</m:tr>
</m:table>
