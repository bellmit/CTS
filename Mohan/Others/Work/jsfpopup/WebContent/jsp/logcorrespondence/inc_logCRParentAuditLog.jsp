<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
   
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<portlet:defineObjects />

<m:JSFFrame rendered="#{CorrespondenceDataBean.auditLogRendered}">

	<m:div onmouseover="over=true;" onmouseout="over=false;" 
		styleClass="fd_Title" style="cursor:move;height:18px;display:block;">
		<m:table id="PROVIDERMMT20120731164811389" width="100%">
			<m:tbody>
				<m:tr valign="center">
					<m:td align="left">
						<m:div styleClass="float1" style="width:100%;">
							<h:outputText id="PRGCMGTOT899" value="#{crspd['label.ref.audit']}" 								styleClass="strong"></h:outputText>
						</m:div>
					</m:td>
					<m:td align="right">
						<m:div styleClass="floatr" style="width:100%;">
							<t:commandLink id="PRGCMGTCL143"								action="#{CorrespondenceControllerBean.closeAuditLog}"								style="color:#fff;">
								<h:outputText id="PRGCMGTOT900" value="#{crspd['label.ref.Cross']}" ></h:outputText>
							</t:commandLink>
						</m:div>
					</m:td>
				</m:tr>
			</m:tbody>
		</m:table>
	</m:div>

<!--  This is for AUDIT LOG ROW -->
					
	<m:div styleClass="" rendered="#{CorrespondenceDataBean.auditLogRendered}">					
		<t:dataTable cellspacing="0" styleClass="dataTable"			id="tblCorrespondenceAuditList" width="90%" align="center" rows="10"			value="#{CorrespondenceDataBean.auditParentHistoryList}"			rowClasses="row,row_alt" var="correspondenceAuditList">
			<h:column id="colHeadeAudit">
				<f:facet name="header">									
					<h:outputText id="hHeadeAudit" value="Timestamp" />
				</f:facet>
				
				<!--  ON TIMESTAMP CLICK -->
				<t:commandLink id="PRGCMGTCL144"					action="#{CorrespondenceControllerBean.showColValHistory}">
					<h:outputText id="PRGCMGTOT901"						value="#{correspondenceAuditList.auditTimeStamp}">
						<f:convertDateTime type="both" pattern="MM/dd/yyyy hh:mm:ss a" />
					</h:outputText>
					<f:param name="auditLogSK" value="#{correspondenceAuditList.auditLogSK}"/>
				</t:commandLink>
				<!--  ENDS HERE -->
				
			</h:column>
			
			<h:column id="colAuditUI">
				<f:facet name="header">
					<h:outputText id="hAuditUI" value="User ID" />
				</f:facet>
				<h:outputText id="PRGCMGTOT902" value="#{correspondenceAuditList.auditUserID}" />
			</h:column>
			<h:column id="colAuditTN">
				<f:facet name="header">
					<h:outputText id="hAuditTN" value="Table Name" />
				</f:facet>
				<h:outputText id="PRGCMGTOT903" value="#{correspondenceAuditList.tableName}" />
			</h:column>
			<h:column id="colAuditAT">
				<f:facet name="header">
					<h:outputText id="hAuditAT" value="Activity Type" />
				</f:facet>
				<h:outputText id="PRGCMGTOT904" value="#{correspondenceAuditList.dmlTypeCode}" />
			</h:column>
			<h:column id="colAuditOED">
				<f:facet name="header">
					<h:outputText id="hAuditOED" value="Original Entry Date/Time" />
				</f:facet>
				<h:outputText id="PRGCMGTOT905" value="#{CorrespondenceDataBean.correspondenceRecordVO.addedAuditTimeStamp}" >
						<f:convertDateTime type="both" pattern="MM/dd/yyyy hh:mm:ss a" />
				</h:outputText>
			</h:column>
			<h:column id="colAuditOEU">
				<f:facet name="header">
					<h:outputText id="hAuditOEU" value="Original Entry User ID" />
				</f:facet>
				<h:outputText id="PRGCMGTOT906" value="#{CorrespondenceDataBean.correspondenceRecordVO.addedAuditUserID}" />
			</h:column>
			<h:column id="colAuditTI">
				<f:facet name="header">
					<h:outputText id="hparenttranid" value="Transaction ID" />
				</f:facet>
				<h:outputText id="PRGCMGTOT907" value="#{correspondenceAuditList.cscnNumber}" />
			</h:column>
		</t:dataTable>			
		
		<!-- AUDIT LOG ROW ENDS HERE -->
		
		
		
		<!--  This is for PAGING -->
					
			<t:dataScroller id="CMGTTOMDS35" for="tblCorrespondenceAuditList" paginator="true"					paginatorActiveColumnStyle='font-weight:bold;' paginatorMaxPages="3"    				    immediate="false" pageCountVar="pageCount" pageIndexVar="pageIndex"				    firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"				    rowsCountVar="rowsCount"				    style="float:right;position:relative;bottom:-6px;text-decoration:underline;"> 
					<f:facet name="previous">
   								<t:commandLink styleClass="commandLink" id="previousLink"    									style="text-decoration:underline;" value=" << "   									action="#{CorrespondenceControllerBean.previous}"   									rendered="#{CorrespondenceDataBean.showPrevious}">
   								</t:commandLink>     
					</f:facet>
					<f:facet name="next">    
						<t:commandLink styleClass="commandLink" id="nextlink" 									style="text-decoration:underline;" value=" >> "
   									action="#{CorrespondenceControllerBean.next}"
   									rendered="#{CorrespondenceDataBean.showNext}">
   								</t:commandLink>     
				    </f:facet>   
				    <h:outputText id="PRGCMGTOT908" rendered="#{rowsCount > 0}"
 								value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
 							style="float:left;position:relative;bottom:-6px;font-weight:bold;" />
   							</t:dataScroller>  
   							
   							<!--  PAGING ends here -->
   							
	</m:div>
				
				<!--  This is for COLUMN VALUE Details (BEFORE AND AFTER VALUE) -->
	
	<m:br/><m:br/>
	<m:div styleClass="fd_Title" rendered="#{CorrespondenceDataBean.auditParentHistoryRender}" 
			style="height:18px;display:block;">
			<m:table id="PROVIDERMMT20120731164811390" width="100%">
				<m:tbody>
					<m:tr valign="center">
						<m:td align="left">
							<m:div styleClass="float1">
								<h:outputText id="PRGCMGTOT909" value="Audit History" styleClass="strong"/>
							</m:div>
						</m:td>
						<m:td align="right">
							<m:div styleClass="floatr">
								<t:commandLink id="PRGCMGTCL145" action="#{CorrespondenceControllerBean.closeColValHistory}" 									value="Close" style="color:#fff;"/>
							</m:div>
						</m:td>
					</m:tr>
				</m:tbody>
			</m:table>
		</m:div> 
	<m:br/><m:br/>
	<m:div rendered="#{CorrespondenceDataBean.auditParentHistoryRender}"> 
		<m:table id="PROVIDERMMT20120731164811391" width="100%">
			<m:tr>
				<m:td>
					<h:outputText id="PRGCMGTOT910" value="#{crspd['label.crspd.cr']}"						styleClass="strong" />
				</m:td>
			</m:tr> 
		</m:table>
	</m:div>
	
	<m:div styleClass="" rendered="#{CorrespondenceDataBean.auditParentHistoryRender}">					
		<t:dataTable cellspacing="0" styleClass="dataTable" id="tblCorrespondenceColVal" width="100%" align="center"			value="#{CorrespondenceDataBean.selectedAuditLog.auditLogColumnValue}"			rowClasses="row,row_alt" var="colValue">
			<h:column id="colValueColName">
				<f:facet name="header">
					<h:outputText id="hColValueColName" value="Column Name" />
				</f:facet>
				<h:outputText id="PRGCMGTOT911" value="#{colValue.columnName}" />
			</h:column>
			<h:column id="colValueBefore">
				<f:facet name="header">
					<h:outputText id="hColValueBefore" value="Before" />
				</f:facet>
				<h:outputText id="PRGCMGTOT912" value="#{colValue.beforeValue}" />
			</h:column>
			<h:column id="colValueAfter">
				<f:facet name="header">
					<h:outputText id="hColValueAfter" value="After" />
				</f:facet>
				<h:outputText id="PRGCMGTOT913" value="#{colValue.afterValue}" />
			</h:column>
		</t:dataTable>
	</m:div>
	
	<!--  COLUMN VALUE ENDS HERE  -->

</m:JSFFrame>

<t:saveState id="CMGTTOMSS250" value="#{CorrespondenceDataBean.auditParentHistoryRender}"></t:saveState>
<t:saveState id="CMGTTOMSS251" value="#{CorrespondenceDataBean.auditParentHistoryList}"></t:saveState>
<t:saveState id="CMGTTOMSS252" value="#{CorrespondenceDataBean.count}"></t:saveState>
<t:saveState id="CMGTTOMSS253" value="#{CorrespondenceDataBean.showPrevious}"></t:saveState>
<t:saveState id="CMGTTOMSS254" value="#{CorrespondenceDataBean.showNext}"></t:saveState>
<t:saveState id="CMGTTOMSS255" value="#{CorrespondenceDataBean.numberOfPages}"></t:saveState>
<t:saveState id="CMGTTOMSS256" value="#{CorrespondenceDataBean.currentPage}"></t:saveState>
<t:saveState id="CMGTTOMSS257" value="#{CorrespondenceDataBean.startRecord}"></t:saveState>
<t:saveState id="CMGTTOMSS258" value="#{CorrespondenceDataBean.endRecord}"></t:saveState>
