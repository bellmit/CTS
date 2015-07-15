<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<portlet:defineObjects />

<t:saveState id="CMGTTOMSS490" value="#{lobHierarchyDataBean.auditLogRendered}" />
<t:saveState id="CMGTTOMSS491" value="#{lobHierarchyDataBean.lobHieracVO.auditTimeStamp}"></t:saveState>
<t:saveState id="CMGTTOMSS492" value="#{lobHierarchyDataBean.lobHieracVO.auditUserID}"></t:saveState>
<t:saveState id="CMGTTOMSS493" value="#{lobHierarchyDataBean.lobHieracVO.addedAuditTimeStamp}"></t:saveState>
<t:saveState id="CMGTTOMSS494" value="#{lobHierarchyDataBean.lobHieracVO.addedAuditUserID}"></t:saveState>
<t:saveState id="CMGTTOMSS495" value="#{lobHierarchyDataBean.auditHistoryRender}"></t:saveState>
<t:saveState id="CMGTTOMSS496" value="#{lobHierarchyDataBean.auditColumnHistoryRender}"></t:saveState>
<t:saveState id="CMGTTOMSS497" value="#{lobHierarchyDataBean.auditHistoryList}"></t:saveState>

<script>
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
	 var hiddenTxt = find(hiddenTextId);
	 var el = document.getElementById(id);
	 if (el.style.display == 'none'){
		  obj.className = 'plus';
		  hiddenTxt.value = 'plus';
	 }
	 else if ((el.style.display == 'block')  || (el.style.display == '')){
		  obj.className = 'minus';
		  hiddenTxt.value = 'minus';
	 }
	 else if (el.style.display == ''){
		  obj.className = 'minus';
		  hiddenTxt.value = 'minus';
	 }
}
</script>

<%-- AUDIT PART --%>
<m:section id="PROVIDERMMS20120731164811637" styleClass="expand">
	<m:legend>
		<f:verbatim>
			<h:outputLink				onclick="toggleTest('audit_plus',2);                     plusMinusForRefreshTest('audit_plus',this,'addlinfo_hidden');return false;"				id="plusMinus_Auditmore" styleClass="plus">
				<h:outputText id="audit" value="#{ref['label.ref.audit']}" />
			</h:outputLink>
			<h:inputHidden id="addlinfo_hidden" value="plus"></h:inputHidden>
			<h:inputHidden id="audit_open"				value="#{lobHierarchyDataBean.auditHistoryRender}"></h:inputHidden>
		</f:verbatim>
	</m:legend>
		<m:div sid="audit_plus">
		<m:div id="log_detail" styleClass="">
			<t:dataTable cellspacing="0" styleClass="dataTable"				id="vvhistory_table" width="100%" rows="10"				value="#{lobHierarchyDataBean.auditHistoryList}"				var="lobHistory">

				<h:column id="PRGCMGTC107">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT1594" value="Timestamp" />
					</f:facet>
					<t:commandLink id="PRGCMGTCL224"						action="#{lobHierarchyControlBean.showColumnChange}">
						<h:outputText id="PRGCMGTOT1595" value="#{lobHistory.auditTimeStamp}">
							<f:convertDateTime type="both" pattern="MM/dd/yyyy hh:mm:ss a" />
						</h:outputText>
						<f:param name="auditLogSK" value="#{lobHistory.auditLogSK}" />
					</t:commandLink>
				</h:column>
				<h:column id="PRGCMGTC108">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT1596" value="User ID" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1597" value="#{lobHistory.auditUserID}" />
				</h:column>
				<h:column id="PRGCMGTC109">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT1598" value="Table Name" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1599" value="#{lobHistory.tableName}" />
				</h:column>
				<h:column id="PRGCMGTC110">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT1600" value="Activity Type" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1601" value="#{lobHistory.dmlTypeCode}" />
				</h:column>
				<h:column id="PRGCMGTC111">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT1602" value="Original Entry Date/Time" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1603" value="#{lobHierarchyDataBean.lobHieracVO.addedAuditTimeStamp}">
						<f:convertDateTime type="both" pattern="MM/dd/yyyy hh:mm:ss a" />
					</h:outputText>
				</h:column>
				<h:column id="PRGCMGTC112">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT1604" value="Original Entry User ID" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1605" value="#{lobHierarchyDataBean.lobHieracVO.addedAuditUserID}" />
				</h:column>
				<h:column id="PRGCMGTC113">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT1606" value="Transaction ID" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1607" value="#{lobHistory.cscnNumber}" />
				</h:column>
			</t:dataTable>
			
			<t:dataScroller id="CMGTTOMDS55" for="vvhistory_table" paginator="true"				paginatorActiveColumnStyle='font-weight:bold;' paginatorMaxPages="3"    				immediate="false" pageCountVar="pageCount" pageIndexVar="pageIndex"				firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"				rowsCountVar="rowsCount"				style="float:right;position:relative;bottom:-6px;text-decoration:underline;"> 
				<f:facet name="previous">
   					<t:commandLink styleClass="commandLink" id="previousLink"    						style="text-decoration:underline;" value=" << "   						action="#{lobHierarchyControlBean.previous}"   						rendered="#{lobHierarchyDataBean.showPrevious}">
   								</t:commandLink>     
							</f:facet>
							<f:facet name="next">    
								<t:commandLink styleClass="commandLink" id="nextlink" 									style="text-decoration:underline;" value=" >> "
   									action="#{lobHierarchyControlBean.next}"
   									rendered="#{lobHierarchyDataBean.showNext}">
   								</t:commandLink>     
						    </f:facet>   
				   <h:outputText id="PRGCMGTOT1608" rendered="#{rowsCount > 0}"
     			value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
     			style="float:left;position:relative;bottom:-6px;font-weight:bold;" />
			</t:dataScroller>
		</m:div>

		<m:div styleClass=""
			rendered="#{lobHierarchyDataBean.auditColumnHistoryRender}">
			<t:dataTable cellspacing="0" styleClass="dataTable"				id="vvcolumnhistory_table" width="90%" align="center"				value="#{lobHierarchyDataBean.selectedAuditLog.auditLogColumnValue}"				rowClasses="row,row_alt" var="colValue">
				<h:column id="colValueColName">
					<f:facet name="header">
						<h:outputText id="hColValueColName" value="Column Name" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1609" value="#{colValue.columnName}" />
				</h:column>
				<h:column id="colValueBefore">
					<f:facet name="header">
						<h:outputText id="hColValueBefore" value="Before" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1610" value="#{colValue.beforeValue}" />
				</h:column>
				<h:column id="colValueAfter">
					<f:facet name="header">
						<h:outputText id="hColValueAfter" value="After" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1611" value="#{colValue.afterValue}" />
				</h:column>
			</t:dataTable>
		</m:div>
	</m:div>
</m:section>
<%-- AUDIT END --%>

	
