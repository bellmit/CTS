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

<t:saveState id="CMGTTOMSS194" value="#{AlertDataBean.alertAuditHistoryList}"></t:saveState> 
<t:saveState id="CMGTTOMSS195" value="#{AlertDataBean.alertAuditOpen}"></t:saveState> 
<t:saveState id="CMGTTOMSS196" value="#{AlertDataBean.columnValueRender}"></t:saveState> 
<t:saveState id="CMGTTOMSS197" value="#{AlertDataBean.count}"></t:saveState>
<t:saveState id="CMGTTOMSS198" value="#{AlertDataBean.showPrevious}"></t:saveState>
<t:saveState id="CMGTTOMSS199" value="#{AlertDataBean.showNext}"></t:saveState>
<t:saveState id="CMGTTOMSS200" value="#{AlertDataBean.numberOfPages}"></t:saveState>
<t:saveState id="CMGTTOMSS201" value="#{AlertDataBean.currentPage}"></t:saveState>
<t:saveState id="CMGTTOMSS202" value="#{AlertDataBean.startRecord}"></t:saveState>
<t:saveState id="CMGTTOMSS203" value="#{AlertDataBean.endRecord}"></t:saveState>


<%-- AUDIT PART --%>
<m:div>

	<m:section id="PROVIDERMMS20120731164811314" styleClass="expand">

		<m:legend>
			<h:outputLink				onclick="toggleTest('audit_plus_alert',2);                     plusMinusForRefreshTest('audit_plus_alert',this,'addlinfo_hidden');return false;"									id="plusMinus_Auditmore" styleClass="plus">
				<h:outputText id="audit" value="#{crspd['label.ref.audit']}" />
			</h:outputLink>
			<h:inputHidden id="addlinfo_hidden" value="plus"></h:inputHidden>
			<h:inputHidden id="audit_open_alert"				value="#{AlertDataBean.alertAuditOpen}"></h:inputHidden>
		</m:legend>


	<!-- AUDIT LOG ROW start HERE -->
	
		<m:div styleClass="" rendered="#{AlertDataBean.alertAuditRender}">					
				<t:dataTable cellspacing="0" styleClass="dataTable"				id="tblAlertAuditList" width="100%" align="center" rows="10"				value="#{AlertDataBean.alertAuditHistoryList}"				rowClasses="row,row_alt" var="alertAuditList">
				<h:column id="colHeadeAudit">
					<f:facet name="header">									
						<h:outputText id="hHeadeAudit" value="Timestamp" />
					</f:facet>
					
					<!--  ON TIMESTAMP CLICK -->
					<t:commandLink id="PRGCMGTCL115"						action="#{AlertControllerBean.showColValHistory}">
						<h:outputText id="PRGCMGTOT676"							value="#{alertAuditList.auditTimeStamp}" >
							<f:convertDateTime type="both" pattern="MM/dd/yyyy hh:mm:ss a" />
						</h:outputText>
						<f:param name="auditLogSK" value="#{alertAuditList.auditLogSK}"/>
					</t:commandLink>
					<!--  ENDS HERE -->
					
				</h:column>
				
				<h:column id="colAuditUI">
					<f:facet name="header">
						<h:outputText id="hAuditUI" value="User ID" />
					</f:facet>
					<h:outputText id="PRGCMGTOT677" value="#{alertAuditList.auditUserID}" />
				</h:column>
				<h:column id="colAuditTN">
					<f:facet name="header">
						<h:outputText id="hAuditTN" value="Table Name" />
					</f:facet>
					<h:outputText id="PRGCMGTOT678" value="#{alertAuditList.tableName}" />
				</h:column>
				<h:column id="colAuditAT">
					<f:facet name="header">
						<h:outputText id="hAuditAT" value="Activity Type" />
					</f:facet>
					<h:outputText id="PRGCMGTOT679" value="#{alertAuditList.dmlTypeCode}" />
				</h:column>
				<h:column id="colAuditOED">
					<f:facet name="header">
						<h:outputText id="hAuditOED" value="Original Entry Date/Time" />
					</f:facet>
					<h:outputText id="PRGCMGTOT680" value="#{AlertDataBean.alertVO.addedAuditTimeStamp}" >
						<f:convertDateTime type="both" pattern="MM/dd/yyyy hh:mm:ss a" />
					</h:outputText>
				</h:column>
				<h:column id="colAuditOEU">
					<f:facet name="header">
						<h:outputText id="hAuditOEU" value="Original Entry User ID" />
					</f:facet>
					<h:outputText id="PRGCMGTOT681" value="#{AlertDataBean.alertVO.addedAuditUserID}" />
				</h:column>
				<h:column id="colAuditTI">
					<f:facet name="header">
						<h:outputText id="hparenttranid" value="Transaction ID" />
					</f:facet>
					<h:outputText id="PRGCMGTOT682" value="#{alertAuditList.cscnNumber}" />
				</h:column>
			</t:dataTable>			
			
			<!-- AUDIT LOG ROW ENDS HERE -->
			
			
			
			<!--  This is for PAGING -->
						
				<t:dataScroller id="CMGTTOMDS28" for="tblAlertAuditList" paginator="true"						paginatorActiveColumnStyle='font-weight:bold;' paginatorMaxPages="3"    					    immediate="false" pageCountVar="pageCount" pageIndexVar="pageIndex"					    firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"					    rowsCountVar="rowsCount"					    style="float:right;position:relative;bottom:-6px;text-decoration:underline;"> 
						<f:facet name="previous">
	   								<t:commandLink styleClass="commandLink" id="previousLink" 	   									style="text-decoration:underline;" value=" << "	   									action="#{AlertControllerBean.previous}"	   									rendered="#{AlertDataBean.showPrevious}">
	   								</t:commandLink>     
						</f:facet>
						<f:facet name="next">    
							<t:commandLink styleClass="commandLink" id="nextlink" 										style="text-decoration:underline;" value=" >> "
	   									action="#{AlertControllerBean.next}"
	   									rendered="#{AlertDataBean.showNext}">
	   								</t:commandLink>     
					    </f:facet>   
					    <h:outputText id="PRGCMGTOT683" rendered="#{rowsCount > 0}"
	 								value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
	 							style="float:left;position:relative;bottom:-6px;font-weight:bold;" />
	   				 			</t:dataScroller>  
	   							
	   							<!--  PAGING ends here -->
	   							
		</m:div>
					
					<!--  This1 is for COLUMN VALUE Details (BEFORE AND AFTER VALUE) -->
		
		<m:br/><m:br/>			
		<m:div styleClass="fd_Title" rendered="#{AlertDataBean.columnValueRender}" 
			style="height:18px;display:block;">
			<m:table id="PROVIDERMMT20120731164811315" width="100%">
				<m:tbody>
					<m:tr valign="center">
						<m:td align="left">
							<m:div styleClass="float1">
								<h:outputText id="PRGCMGTOT684" value="Audit History" styleClass="strong"/>
							</m:div>
						</m:td>
						<m:td align="right">
							<m:div styleClass="floatr">
								<t:commandLink id="PRGCMGTCL116" action="#{AlertControllerBean.closeColValHistory}" 									value="Close" style="color:#fff;"/>
							</m:div>
						</m:td>
					</m:tr>
				</m:tbody>
			</m:table>
		</m:div> 

		<m:br/><m:br/>
		<m:div rendered="#{AlertDataBean.columnValueRender}"> 
			<m:table id="PROVIDERMMT20120731164811316" width="100%">
				<m:tr>
					<m:td>
						<h:outputText id="PRGCMGTOT685" value="#{alert['label.alert.alerts']}"							styleClass="strong" />
					</m:td>
				</m:tr> 
			</m:table>
		</m:div>
		
		<m:div styleClass="" rendered="#{AlertDataBean.columnValueRender}">					
			<t:dataTable cellspacing="0" styleClass="dataTable" id="tblAlertColVal" width="100%" align="center"				value="#{AlertDataBean.selectedAuditLog.auditLogColumnValue}"				rowClasses="row,row_alt" var="colValue">
				<h:column id="colValueColName">
					<f:facet name="header">
						<h:outputText id="hColValueColName" value="Column Name" />
					</f:facet>
					<h:outputText id="PRGCMGTOT686" value="#{colValue.columnName}" />
				</h:column>
				<h:column id="colValueBefore">
					<f:facet name="header">
						<h:outputText id="hColValueBefore" value="Before" />
					</f:facet>
					<h:outputText id="PRGCMGTOT687" value="#{colValue.beforeValue}" />
				</h:column>
				<h:column id="colValueAfter">
					<f:facet name="header">
						<h:outputText id="hColValueAfter" value="After" />
					</f:facet>
					<h:outputText id="PRGCMGTOT688" value="#{colValue.afterValue}" />
				</h:column>
			</t:dataTable>
		</m:div>
	
	</m:section>

</m:div>
<%-- AUDIT END --%>

