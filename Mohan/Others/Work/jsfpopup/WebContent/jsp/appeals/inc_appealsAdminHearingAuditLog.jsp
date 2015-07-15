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
<%-- <t:saveState id="CMGTTOMSS6" value="#{appealDataBean}"></t:saveState>--%>
<%--COMMENTED FOR DEFECT ESPRD00682817 ENDS--%>

<%-- AUDIT PART --%>
<m:section id="PROVIDERMMS2012073116481117" styleClass="expand">

	<m:legend>
			<h:outputLink				onclick="toggleTest('admin_hear_audit_plus',2);                     plusMinusForRefreshTest('admin_hear_audit_plus',this,'admin_hear_addlinfo_hidden');return false;"				id="admin_hear_plusMinus_Auditmore" styleClass="plus">
				<h:outputText id="admin_hear_audit" value="#{msg['label.appeals.audit.Audit']}" />
			</h:outputLink>
			<h:inputHidden id="admin_hear_addlinfo_hidden" value="plus"></h:inputHidden>
			<h:inputHidden id="admin_hear_audit_open"				value="#{appealDataBean.adminHearAuditOpen}"></h:inputHidden>

	</m:legend>
	<m:div sid="admin_hear_audit_plus">
		<m:table id="PROVIDERMMT2012073116481118" cellspacing="0" width="50%">
			<m:tr>
				<m:td colspan="2">
				   <h:outputText id="PRGCMGTOT40" value="#{msg['label.audit.appealInfoTable']}" styleClass="strong" />
				  </m:td>
			</m:tr>
			<m:tr styleClass="mousepointer">
				<m:td styleClass="dataLabel">
					<h:outputText id="PRGCMGTOT41" value="#{msg['label.audit.lastUpdateDateTime']}" styleClass="dataLabel" />
				</m:td>
				<m:td>
					<t:commandLink id="PRGCMGTCL14"						action="#{appealControllerBean.showAdminHearingAuditHistory}" onmousedown="javascript:flagWarn=false;">
						<h:outputText id="PRGCMGTOT42"							value="#{appealDataBean.appealVO.adminHearingVO.auditTimeStamp}" >
							<f:convertDateTime type="both" pattern="MM/dd/yyyy hh:mm:ss a" />
						</h:outputText>
					</t:commandLink>
				</m:td>
			</m:tr>
			<m:tr>
			<m:td styleClass="dataLabel">
					<h:outputText id="PRGCMGTOT43" value="#{msg['label.audit.lastUpdatedUserID']}" styleClass="dataLabel" />
			</m:td>
				<m:td>
					<h:outputText id="PRGCMGTOT44"						value="#{appealDataBean.appealVO.adminHearingVO.auditUserID}" />
					<m:br />
					<m:br />
				</m:td>
			</m:tr>
			<m:tr>
				<m:td styleClass="dataLabel">
					<h:outputText id="PRGCMGTOT45" value="#{msg['label.audit.originalEntryDate']}"						styleClass="dataLabel" />
				</m:td>
				<m:td>
					<h:outputText id="PRGCMGTOT46"						value="#{appealDataBean.appealVO.adminHearingVO.addedAuditTimeStamp}" >
						<f:convertDateTime type="both" pattern="MM/dd/yyyy hh:mm:ss a" />
					</h:outputText>
				</m:td>
			</m:tr>
			<m:tr>
				<m:td styleClass="dataLabel">
					<h:outputText id="PRGCMGTOT47" value="#{msg['label.audit.originalEntryUserID']}"						styleClass="dataLabel" />
				</m:td> 
				<m:td>
					<h:outputText id="PRGCMGTOT48"						value="#{appealDataBean.appealVO.adminHearingVO.addedAuditUserID}" />
				</m:td>
			</m:tr>
			<m:tr>
				<m:td colspan="2">
					<m:div></m:div>
				</m:td>
			</m:tr>
		</m:table>
		<m:div id="admin_hear_log_detail" styleClass=""
			rendered="#{appealDataBean.adminHearAuditHistoryRender}">

			<t:dataTable cellspacing="0" styleClass="dataTable"
				id="admin_hear_vvhistory_table" width="50%"
				value="#{appealDataBean.adminHearAuditHistoryList}" var="adminhistory">
				<h:column id="admin_hear_hiscolname">
					<f:facet name="header">
						<h:outputText id="admin_hear_hcolname"							value="#{msg['label.appeals.audit.fieldname']}" />
					</f:facet>
					<h:outputText id="PRGCMGTOT49" value="#{adminhistory.columnName}" />
				</h:column>
				<h:column id="admin_hear_hisbefore">
					<f:facet name="header">
						<h:outputText id="admin_hear_hbefore"							value="#{msg['label.appeals.audit.before']}" />
					</f:facet>
					<h:outputText id="PRGCMGTOT50" value="#{adminhistory.columnBeforeValue}" />
				</h:column>
				<h:column id="admin_hear_hisafter">
					<f:facet name="header">
						<h:outputText id="admin_hear_hafter" value="#{msg['label.appeals.audit.after']}" />
					</f:facet>
					<h:outputText id="PRGCMGTOT51" value="#{adminhistory.columnAfterValue}" />
				</h:column>
			</t:dataTable>

		</m:div>
	</m:div>
</m:section>
<%-- AUDIT END --%>
