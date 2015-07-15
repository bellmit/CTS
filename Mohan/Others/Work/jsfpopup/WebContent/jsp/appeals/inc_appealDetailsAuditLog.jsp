<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>

<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>


<script type="text/javascript">

function toggleTest(obj,state){
 var el = document.getElementById(obj);

 if (state==1){
  el.style.display = 'block';
 }
 else if (state==0){
  el.style.display = 'none';
 }
 else if (state==2){
  if (el.style.display == 'none'){
   el.style.display = 'block'; 
  }
  else if ((el.style.display == 'block') || (el.style.display == '')){
   el.style.display = 'none';
  }
 }
}
 
/*
 * Used to display either '+' image or '-' image when a section 
 * is closed or expanded respectively
 */
function plusMinusForRefreshTest(id,obj,hiddenTextId)
{
 var hiddenTxt = 'plus';
 var el = document.getElementById(id);


 if (el.style.display == 'none')
 {
  obj.className = 'plus';
  hiddenTxt.value = 'plus';
 }
 else if ((el.style.display == 'block')  || (el.style.display == ''))
 {
  obj.className = 'minus';
  hiddenTxt.value = 'minus';
 }
 else if (el.style.display == '')
 {
  obj.className = 'minus';
  hiddenTxt.value = 'minus';
 }
}
</script>
<%--COMMENTED FOR DEFECT ESPRD00682817 STARTS--%>
<%-- <t:saveState id="CMGTTOMSS5" value="#{appealDataBean}"></t:saveState>--%>
<%--COMMENTED FOR DEFECT ESPRD00682817 ENDS--%>
<%-- AUDIT PART --%>
<m:section id="PROVIDERMMS2012073116481111" styleClass="expand">

	<m:legend>
			<h:outputLink				onclick="toggleTest('audit_plus',2);                     plusMinusForRefreshTest('audit_plus',this,'addlinfo_hidden');return false;"				id="plusMinus_Auditmore" styleClass="plus">
				<h:outputText id="audit" value="#{msg['label.appeals.audit.Audit']}" />
			</h:outputLink>
			<h:inputHidden id="addlinfo_hidden" value="plus"></h:inputHidden>
			<h:inputHidden id="audit_open"				value="#{appealDataBean.appealAuditOpen}"></h:inputHidden>
	</m:legend>	
	<m:div sid="audit_plus">
		<m:table id="PROVIDERMMT2012073116481112" cellspacing="0" width="50%">
			<m:tr>
				<m:td colspan="2">
				   <h:outputText id="PRGCMGTOT19" value="#{msg['label.audit.appealInfoTable']}" styleClass="strong" />
				  </m:td>
			</m:tr>
			<m:tr styleClass="mousepointer">
				<m:td styleClass="dataLabel">
					<h:outputText id="PRGCMGTOT20" value="#{msg['label.audit.lastUpdateDateTime']}" styleClass="dataLabel" />
				</m:td>
				<m:td>
					<t:commandLink id="PRGCMGTCL13"						action="#{appealControllerBean.showAppealsAuditHistory}" onmousedown="javascript:flagWarn=false;">
						<h:outputText id="PRGCMGTOT21"							value="#{appealDataBean.appealVO.auditTimeStamp}">
							<f:convertDateTime type="both" pattern="MM/dd/yyyy hh:mm:ss a" />
						</h:outputText>
					</t:commandLink>
				</m:td>
			</m:tr>
			<m:tr>
				<m:td styleClass="dataLabel">
					<h:outputText id="PRGCMGTOT22" value="#{msg['label.audit.lastUpdatedUserID']}" styleClass="dataLabel" />
				</m:td>
				<m:td>
					<h:outputText id="PRGCMGTOT23"						value="#{appealDataBean.appealVO.auditUserID}" />
					<m:br />
					<m:br />
				</m:td>
			</m:tr>
			<m:tr>
				<m:td styleClass="dataLabel">
					<h:outputText id="PRGCMGTOT24" value="#{msg['label.audit.originalEntryDate']}"						styleClass="dataLabel" />
				</m:td>
				<m:td>
				<h:outputText id="PRGCMGTOT25"						value="#{appealDataBean.appealVO.addedAuditTimeStamp}" >
					<f:convertDateTime type="both" pattern="MM/dd/yyyy hh:mm:ss a" />
   				</h:outputText>
				</m:td>
			</m:tr>
			<m:tr>
				<m:td styleClass="dataLabel">
					<h:outputText id="PRGCMGTOT26" value="#{msg['label.audit.originalEntryUserID']}"						styleClass="dataLabel" />
				</m:td>
				<m:td>
					<h:outputText id="PRGCMGTOT27"						value="#{appealDataBean.appealVO.addedAuditUserID}" />
				</m:td>
			</m:tr>
			<m:tr>
				<m:td colspan="2">
					<m:div></m:div>
				</m:td>
			</m:tr>
		</m:table>
		<m:div id="log_detail" styleClass=""
			rendered="#{appealDataBean.appealAuditHistoryRender}">

			<t:dataTable cellspacing="0" styleClass="dataTable"				id="vvhistory_table" width="50%"				value="#{appealDataBean.appealAuditHistoryList}" var="vvhistory">
				<h:column id="hiscolname">
					<f:facet name="header">
						<h:outputText id="hcolname"							value="#{msg['label.appeals.audit.fieldname']}" />
					</f:facet>
					<h:outputText id="PRGCMGTOT28" value="#{vvhistory.columnName}" />
				</h:column>
				<h:column id="hisbefore">
					<f:facet name="header">
						<h:outputText id="hbefore"							value="#{msg['label.appeals.audit.before']}" />
					</f:facet>
					<h:outputText id="PRGCMGTOT29" value="#{vvhistory.columnBeforeValue}" />
				</h:column>
				<h:column id="hisafter">
					<f:facet name="header">
						<h:outputText id="hafter" value="#{msg['label.appeals.audit.after']}" />
					</f:facet>
					<h:outputText id="PRGCMGTOT30" value="#{vvhistory.columnAfterValue}" />
				</h:column>
			</t:dataTable>

		</m:div>
	</m:div>
</m:section>
<%-- AUDIT END --%>
