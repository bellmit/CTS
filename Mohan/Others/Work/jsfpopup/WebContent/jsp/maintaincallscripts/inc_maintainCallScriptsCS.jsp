

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>

<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<portlet:defineObjects />

<t:saveState id="CMGTTOMSS293" value="#{CallScriptDataBean.callScriptVO}"></t:saveState>
<t:saveState id="CMGTTOMSS294" value="#{CallScriptDataBean.callScriptSearchCriteriaVO}"></t:saveState>
<%--<t:saveState id="CMGTTOMSS295" value="#{CallScriptDataBean.maintainCallScriptList}"></t:saveState>--%>

<m:div id="texttab">
	<m:span styleClass="required">
		<h:outputText id="CMGTOT66" value="#{cont['label.ref.reqSymbol']}" />
	</m:span>
	<h:outputLabel id="CMGTOLL8" 		value="#{msg['label.callscript.callScript']}" for="callscrptext"></h:outputLabel>
	<m:br></m:br>
	<h:inputTextarea cols="60" id="callscrptext" 		disabled="#{CallScriptDataBean.callScriptVO.inactive}" rows="20"		value="#{CallScriptDataBean.callScriptVO.callScriptFullText}"/>
	<m:br></m:br>
	<h:message id="CMGTM12" for="callscrptext" showDetail="true"				styleClass="errorMessage" />
</m:div>
