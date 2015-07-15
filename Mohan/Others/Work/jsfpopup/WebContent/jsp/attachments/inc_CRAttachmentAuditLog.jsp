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

<%-- <t:saveState id="CMGTTOMSS17" value="#{AlertDataBean.alertAuditRender}"></t:saveState>
<t:saveState id="CMGTTOMSS18" value="#{AlertDataBean.alertAuditHistoryList}"></t:saveState>

<t:saveState id="CMGTTOMSS19" value="#{AttachmentDataBean.attachmentVO.auditTimeStamp}"></t:saveState>
<t:saveState id="CMGTTOMSS20" value="#{AttachmentDataBean.attachmentVO.auditUserID}"></t:saveState>
<t:saveState id="CMGTTOMSS21" value="#{AttachmentDataBean.attachmentVO.addedAuditTimeStamp}"></t:saveState>
<t:saveState id="CMGTTOMSS22" value="#{AttachmentDataBean.attachmentVO.addedAuditUserID}"></t:saveState> --%>
 
<t:saveState id="CMGTTOMSS23" value="#{AttachmentDataBean.attachAuditHistoryList}"></t:saveState> 
<t:saveState id="CMGTTOMSS24" value="#{AttachmentDataBean.attachAuditRender}"></t:saveState> 
<t:saveState id="CMGTTOMSS25" value="#{AttachmentDataBean.attachAuditOpen}"></t:saveState>
<t:saveState id="CMGTTOMSS26" value="#{AttachmentDataBean.columnValueRender}"></t:saveState> 
<t:saveState id="CMGTTOMSS27" value="#{AttachmentDataBean.count}"></t:saveState>
<t:saveState id="CMGTTOMSS28" value="#{AttachmentDataBean.showPrevious}"></t:saveState>
<t:saveState id="CMGTTOMSS29" value="#{AttachmentDataBean.showNext}"></t:saveState>
<t:saveState id="CMGTTOMSS30" value="#{AttachmentDataBean.numberOfPages}"></t:saveState>
<t:saveState id="CMGTTOMSS31" value="#{AttachmentDataBean.currentPage}"></t:saveState>
<t:saveState id="CMGTTOMSS32" value="#{AttachmentDataBean.startRecord}"></t:saveState>
<t:saveState id="CMGTTOMSS33" value="#{AttachmentDataBean.endRecord}"></t:saveState>

<%-- AUDIT PART --%>
<m:div>

	<m:section id="PROVIDERMMS20120731164811144" styleClass="expand">

		<m:legend>

			<h:outputLink				onclick="toggleTest('audit_plus_attach',2);                     plusMinusForRefreshTest('audit_plus_attach',this,'addlinfo_hidden');return false;"				id="plusMinus_Auditmore" styleClass="plus">
				<h:outputText id="audit" value="#{crspd['label.ref.audit']}" />
			</h:outputLink>
			<h:inputHidden id="addlinfo_hidden" value="plus"></h:inputHidden>
			<h:inputHidden id="audit_open_attach"				value="#{AttachmentDataBean.attachAuditOpen}"></h:inputHidden>
	</m:legend>
	
	
	
	
		<!--  This is for AUDIT LOG ROW -->
		<m:div sid="audit_plus_attach" style="display:none">				
		<m:div rendered="#{AttachmentDataBean.attachAuditRender}">					
			<t:dataTable cellspacing="0" styleClass="dataTable"				id="tblAttachmentAuditList" width="100%" align="center" rows="10"				value="#{AttachmentDataBean.attachAuditHistoryList}"				rowClasses="row,row_alt" var="attachmentAuditList">
				<h:column id="colHeadeAudit">
					<f:facet name="header">									
						<h:outputText id="hHeadeAudit" value="Timestamp" />
					</f:facet>
					
					<!--  ON TIMESTAMP CLICK -->
					<t:commandLink id="PRGCMGTCL48"						action="#{AttachmentControllerBean.showColValHistory}">
						<h:outputText id="PRGCMGTOT243"							value="#{attachmentAuditList.auditTimeStamp}" >
							<f:convertDateTime type="both" pattern="MM/dd/yyyy hh:mm:ss a" />
						</h:outputText>
						<f:param name="auditLogSK" value="#{attachmentAuditList.auditLogSK}"/>
					</t:commandLink>
					<!--  ENDS HERE -->
					
				</h:column>
				
				<h:column id="colAuditUI">
					<f:facet name="header">
						<h:outputText id="hAuditUI" value="User ID" />
					</f:facet>
					<h:outputText id="PRGCMGTOT244" value="#{attachmentAuditList.auditUserID}" />
				</h:column>
				<h:column id="colAuditTN">
					<f:facet name="header">
						<h:outputText id="hAuditTN" value="Table Name" />
					</f:facet>
					<h:outputText id="PRGCMGTOT245" value="#{attachmentAuditList.tableName}" />
				</h:column>
				<h:column id="colAuditAT">
					<f:facet name="header">
						<h:outputText id="hAuditAT" value="Activity Type" />
					</f:facet>
					<h:outputText id="PRGCMGTOT246" value="#{attachmentAuditList.dmlTypeCode}" />
				</h:column>
				<h:column id="colAuditOED">
					<f:facet name="header">
						<h:outputText id="hAuditOED" value="Original Entry Date/Time" />
					</f:facet>
					<h:outputText id="PRGCMGTOT247" value="#{AttachmentDataBean.attachmentVO.addedAuditTimeStamp}">
						<f:convertDateTime type="both" pattern="MM/dd/yyyy hh:mm:ss a" />
					</h:outputText>
				</h:column>
				<h:column id="colAuditOEU">
					<f:facet name="header">
						<h:outputText id="hAuditOEU" value="Original Entry User ID" />
					</f:facet>
					<h:outputText id="PRGCMGTOT248" value="#{AttachmentDataBean.attachmentVO.addedAuditUserID}" />
				</h:column>
				<h:column id="colAuditTI">
					<f:facet name="header">
						<h:outputText id="hparenttranid" value="Transaction ID" />
					</f:facet>
					<h:outputText id="PRGCMGTOT249" value="#{attachmentAuditList.cscnNumber}" />
				</h:column>
			</t:dataTable>			
			
			<!-- AUDIT LOG ROW ENDS HERE -->
			
			
			
			<!--  This is for PAGING -->
						
				<t:dataScroller id="CMGTTOMDS8" for="tblAttachmentAuditList" paginator="true"						paginatorActiveColumnStyle='font-weight:bold;' paginatorMaxPages="3"    					    immediate="false" pageCountVar="pageCount" pageIndexVar="pageIndex"					    firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"					    rowsCountVar="rowsCount"					    style="float:right;position:relative;bottom:-6px;text-decoration:underline;"> 
						<f:facet name="previous">
	   								<t:commandLink styleClass="commandLink" id="previousLink" 	   									style="text-decoration:underline;" value=" << "	   									action="#{AttachmentControllerBean.previous}"	   									rendered="#{AttachmentDataBean.showPrevious}">
	   								</t:commandLink>     
						</f:facet>
						<f:facet name="next">    
							<t:commandLink styleClass="commandLink" id="nextlink" 										style="text-decoration:underline;" value=" >> "
	   									action="#{AttachmentControllerBean.next}"
	   									rendered="#{AttachmentDataBean.showNext}">
	   								</t:commandLink>     
					    </f:facet>   
					    <h:outputText id="PRGCMGTOT250" rendered="#{rowsCount > 0}"
	 								value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
	 							style="float:left;position:relative;bottom:-6px;font-weight:bold;" />
	   							</t:dataScroller>  
	   							
	   							<!--  PAGING ends here -->
	   							
		</m:div>
					
					<!--  This is for COLUMN VALUE Details (BEFORE AND AFTER VALUE) -->
					
		<m:br/><m:br/>
		<m:div styleClass="fd_Title" rendered="#{AttachmentDataBean.columnValueRender}" 
			style="height:18px;display:block;">
			<m:table id="PROVIDERMMT20120731164811145" width="100%">
				<m:tbody>
					<m:tr valign="center">
						<m:td align="left">
							<m:div styleClass="float1">
								<h:outputText id="PRGCMGTOT251" value="Audit History" styleClass="strong"/>
							</m:div>
						</m:td>
						<m:td align="right">
							<m:div styleClass="floatr">
								<t:commandLink id="PRGCMGTCL49" action="#{AttachmentControllerBean.closeColValHistory}" 									value="Close" style="color:#fff;"/>
							</m:div>
						</m:td>
					</m:tr>
				</m:tbody>
			</m:table>
		</m:div> 
		
		<m:div rendered="#{AttachmentDataBean.columnValueRender}"> 
			<m:table id="PROVIDERMMT20120731164811146" width="100%">
				<m:tr>
					<m:td>
						<h:outputText id="PRGCMGTOT252" value="Correspondence Attachment Table Info"							styleClass="strong" />
					</m:td>
				</m:tr> 
			</m:table>
		</m:div>
	
		<m:div styleClass="" rendered="#{AttachmentDataBean.columnValueRender}">					
			<t:dataTable cellspacing="0" styleClass="dataTable" id="tblAttachmentColVal" width="100%" align="center"				value="#{AttachmentDataBean.selectedAuditLog.auditLogColumnValue}"				rowClasses="row,row_alt" var="colValue">
				<h:column id="colValueColName">
					<f:facet name="header">
						<h:outputText id="hColValueColName" value="Column Name" />
					</f:facet>
					<h:outputText id="PRGCMGTOT253" value="#{colValue.columnName}" />
				</h:column>
				<h:column id="colValueBefore">
					<f:facet name="header">
						<h:outputText id="hColValueBefore" value="Before" />
					</f:facet>
					<h:outputText id="PRGCMGTOT254" value="#{colValue.beforeValue}" />
				</h:column>
				<h:column id="colValueAfter">
					<f:facet name="header">
						<h:outputText id="hColValueAfter" value="After" />
					</f:facet>
					<h:outputText id="PRGCMGTOT255" value="#{colValue.afterValue}" />
				</h:column>
			</t:dataTable>
		</m:div>
		
		<!--  COLUMN VALUE ENDS HERE  -->
	
	</m:div>
	</m:section>

</m:div>
<%-- AUDIT END --%>

