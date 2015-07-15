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

<%-- <t:saveState id="CMGTTOMSS204" value="#{RoutingDataBean.routingAuditRender}"></t:saveState>


<t:saveState id="CMGTTOMSS205" value="#{RoutingDataBean.cmRoutingVO.auditTimeStamp}"></t:saveState>
<t:saveState id="CMGTTOMSS206" value="#{RoutingDataBean.cmRoutingVO.auditUserID}"></t:saveState>
<t:saveState id="CMGTTOMSS207" value="#{RoutingDataBean.cmRoutingVO.addedAuditTimeStamp}"></t:saveState>
<t:saveState id="CMGTTOMSS208" value="#{RoutingDataBean.cmRoutingVO.addedAuditUserID}"></t:saveState> --%>


<t:saveState id="CMGTTOMSS209" value="#{RoutingDataBean.routingAuditHistoryList}"></t:saveState> 
<t:saveState id="CMGTTOMSS210" value="#{RoutingDataBean.routingAuditOpen}"></t:saveState>
<t:saveState id="CMGTTOMSS211" value="#{RoutingDataBean.routingAuditRender}"></t:saveState> 
<t:saveState id="CMGTTOMSS212" value="#{RoutingDataBean.columnValueRender}"></t:saveState>
<t:saveState id="CMGTTOMSS213" value="#{RoutingDataBean.count}"></t:saveState>
<t:saveState id="CMGTTOMSS214" value="#{RoutingDataBean.showPrevious}"></t:saveState>
<t:saveState id="CMGTTOMSS215" value="#{RoutingDataBean.showNext}"></t:saveState>
<t:saveState id="CMGTTOMSS216" value="#{RoutingDataBean.numberOfPages}"></t:saveState>
<t:saveState id="CMGTTOMSS217" value="#{RoutingDataBean.currentPage}"></t:saveState>
<t:saveState id="CMGTTOMSS218" value="#{RoutingDataBean.startRecord}"></t:saveState>
<t:saveState id="CMGTTOMSS219" value="#{RoutingDataBean.endRecord}"></t:saveState>

<%-- AUDIT PART --%>
<m:div>

	<m:section id="PROVIDERMMS20120731164811317" styleClass="expand">

		<m:legend>
			<h:outputLink				onclick="toggleTest('audit_plus_routing',2);                     plusMinusForRefreshTest('audit_plus_routing',this,'addlinfo_hidden');return false;"				id="plusMinus_Auditmore" styleClass="plus">
				<h:outputText id="audit" value="#{crspd['label.ref.audit']}" />
			</h:outputLink>
			<h:inputHidden id="addlinfo_hidden" value="plus"></h:inputHidden>
			<h:inputHidden id="audit_open_routing"				value="#{RoutingDataBean.routingAuditOpen}"></h:inputHidden>
	</m:legend>


	<!-- AUDIT LOG ROW start HERE -->
		<m:div sid="audit_plus_routing">
		<m:div styleClass="" rendered="#{RoutingDataBean.routingAuditRender}">					
				<t:dataTable cellspacing="0" styleClass="dataTable"				id="tblRoutingAuditList" width="100%" align="center" rows="10"				value="#{RoutingDataBean.routingAuditHistoryList}"				rowClasses="row,row_alt" var="routingAuditList">
				<h:column id="colHeadeAudit">
					<f:facet name="header">									
						<h:outputText id="hHeadeAudit" value="Timestamp" />
					</f:facet>
					
					<!--  ON TIMESTAMP CLICK -->
					<t:commandLink id="PRGCMGTCL117"						action="#{RoutingControllerBean.showColValHistory}">
						<h:outputText id="PRGCMGTOT689"							value="#{routingAuditList.auditTimeStamp}" >
							<f:convertDateTime type="both" pattern="MM/dd/yyyy hh:mm:ss a" />
						</h:outputText>
						<f:param name="auditLogSK" value="#{routingAuditList.auditLogSK}"/>
					</t:commandLink>
					<!--  ENDS HERE -->
					
				</h:column>
				
				<h:column id="colAuditUI">
					<f:facet name="header">
						<h:outputText id="hAuditUI" value="User ID" />
					</f:facet>
					<h:outputText id="PRGCMGTOT690" value="#{routingAuditList.auditUserID}" />
				</h:column>
				<h:column id="colAuditTN">
					<f:facet name="header">
						<h:outputText id="hAuditTN" value="Table Name" />
					</f:facet>
					<h:outputText id="PRGCMGTOT691" value="#{routingAuditList.tableName}" />
				</h:column>
				<h:column id="colAuditAT">
					<f:facet name="header">
						<h:outputText id="hAuditAT" value="Activity Type" />
					</f:facet>
					<h:outputText id="PRGCMGTOT692" value="#{routingAuditList.dmlTypeCode}" />
				</h:column>
				<h:column id="colAuditOED">
					<f:facet name="header">
						<h:outputText id="hAuditOED" value="Original Entry Date/Time" />
					</f:facet>
					<h:outputText id="PRGCMGTOT693" value="#{RoutingDataBean.cmRoutingVO.addedAuditTimeStamp}" >
						<f:convertDateTime type="both" pattern="MM/dd/yyyy hh:mm:ss a" />
					</h:outputText>
				</h:column>
				<h:column id="colAuditOEU">
					<f:facet name="header">
						<h:outputText id="hAuditOEU" value="Original Entry User ID" />
					</f:facet>
					<h:outputText id="PRGCMGTOT694" value="#{RoutingDataBean.cmRoutingVO.addedAuditUserID}" />
				</h:column>
				<h:column id="colAuditTI">
					<f:facet name="header">
						<h:outputText id="hparenttranid" value="Transaction ID" />
					</f:facet>
					<h:outputText id="PRGCMGTOT695" value="#{routingAuditList.cscnNumber}" />
				</h:column>
			</t:dataTable>			
			
			<!-- AUDIT LOG ROW ENDS HERE -->
			
			
			
			<!--  This is for PAGING -->
						
				<t:dataScroller id="CMGTTOMDS29" for="tblRoutingAuditList" paginator="true"						paginatorActiveColumnStyle='font-weight:bold;' paginatorMaxPages="3"    					    immediate="false" pageCountVar="pageCount" pageIndexVar="pageIndex"					    firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"					    rowsCountVar="rowsCount"					    style="float:right;position:relative;bottom:-6px;text-decoration:underline;"> 
						<f:facet name="previous">
	   								<t:commandLink styleClass="commandLink" id="previousLink" 	   									style="text-decoration:underline;" value=" << "	   									action="#{RoutingControllerBean.previous}"	   									rendered="#{RoutingDataBean.showPrevious}">
	   								</t:commandLink>     
						</f:facet>
						<f:facet name="next">    
							<t:commandLink styleClass="commandLink" id="nextlink" 										style="text-decoration:underline;" value=" >> "
	   									action="#{RoutingControllerBean.next}"
	   									rendered="#{RoutingDataBean.showNext}">
	   								</t:commandLink>     
					    </f:facet>   
					    <h:outputText id="PRGCMGTOT696" rendered="#{rowsCount > 0}"
	 								value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
	 							style="float:left;position:relative;bottom:-6px;font-weight:bold;" />
	   							</t:dataScroller>  
	   							
	   							<!--  PAGING ends here -->

			</m:div>
					
					<!--  This is for COLUMN VALUE Details (BEFORE AND AFTER VALUE) -->
		
		<m:br/><m:br/>
		<m:div styleClass="fd_Title" rendered="#{RoutingDataBean.columnValueRender}" 
			style="height:18px;display:block;">
			<m:table id="PROVIDERMMT20120731164811318" width="100%">
				<m:tbody>
					<m:tr valign="center">
						<m:td align="left">
							<m:div styleClass="float1">
								<h:outputText id="PRGCMGTOT697" value="Audit History" styleClass="strong"/>
							</m:div>
						</m:td>
						<m:td align="right">
							<m:div styleClass="floatr">
								<t:commandLink id="PRGCMGTCL118" action="#{RoutingControllerBean.closeColValHistory}" 									value="Close" style="color:#fff;"/>
							</m:div>
						</m:td>
					</m:tr>
				</m:tbody>
			</m:table>
		</m:div> 
		
		<m:div rendered="#{RoutingDataBean.columnValueRender}"> 
			<m:table id="PROVIDERMMT20120731164811319" width="100%">
				<m:tr>
					<m:td>
						<h:outputText id="PRGCMGTOT698" value="Correspondence Routing Table Info"							styleClass="strong" />
					</m:td>
				</m:tr> 
			</m:table>
		</m:div>
	
		<m:div styleClass="" rendered="#{RoutingDataBean.columnValueRender}">					
			<t:dataTable cellspacing="0" styleClass="dataTable" id="tblRoutingColVal" width="100%" align="center"				value="#{RoutingDataBean.selectedAuditLog.auditLogColumnValue}"				rowClasses="row,row_alt" var="colValue">
				<h:column id="colValueColName">
					<f:facet name="header">
						<h:outputText id="hColValueColName" value="Column Name" />
					</f:facet>
					<h:outputText id="PRGCMGTOT699" value="#{colValue.columnName}" />
				</h:column>
				<h:column id="colValueBefore">
					<f:facet name="header">
						<h:outputText id="hColValueBefore" value="Before" />
					</f:facet>
					<h:outputText id="PRGCMGTOT700" value="#{colValue.beforeValue}" />
				</h:column>
				<h:column id="colValueAfter">
					<f:facet name="header">
						<h:outputText id="hColValueAfter" value="After" />
					</f:facet>
					<h:outputText id="PRGCMGTOT701" value="#{colValue.afterValue}" />
				</h:column>
			</t:dataTable>
		</m:div>
		</m:div>
	</m:section>

</m:div>
<%-- AUDIT END --%>







