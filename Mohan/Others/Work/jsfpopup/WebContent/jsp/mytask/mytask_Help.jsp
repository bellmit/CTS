
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://acs.enterprise.com/help" prefix="e"%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<portlet:defineObjects />
<f:view>
	<f:loadBundle
		basename="com.acs.enterprise.common.util.config.ServerLocation"
		var="helpserver" />
		<script type="text/javascript">		  
<%-- 
 - Release : NH Defect PE MMIS Fix
 - Date : 8/11/2011
 - Author : CTS
 - Defect # : ESPRD00646939
 - Purpose : Modification for Defect fix ESPRD00646939
--%>
    	  <%-- var url = '<e:helpPath helpServer="#{helpserver['httpProviderURL']}" helpJunctionUrl="#{helpserver['internalHelpJunction']}" mapFile="#{messageBean.myTaskMap['map.File']}" helpRoot="#{messageBean.alertDtMap['help.Root']}" helpContentPath="#{messageBean.alertDtMap['help.ContentPath']}" helpId="#{messageBean.alertDtMap['help.Id']}"/>'; --%>

		  var url = '<e:helpPath helpServer="#{helpserver['httpProviderURL']}" helpJunctionUrl="#{helpserver['internalHelpJunction']}" mapFile="#{messageBean.myTaskMap['map.File']}" helpRoot="#{messageBean.myTaskMap['help.Root']}" helpContentPath="#{messageBean.myTaskMap['help.ContentPath']}" helpId="#{messageBean.myTaskMap['help.Id']}"/>';
        			location.href=url;
<%-- Defect No.ESPRD00646939 End --%>
        </script>
</f:view>
