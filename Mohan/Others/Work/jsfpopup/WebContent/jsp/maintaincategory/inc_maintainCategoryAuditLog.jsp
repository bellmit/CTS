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

<t:saveState id="CMGTTOMSS368" value="#{CategoryDataBean.categoryAuditRender}"></t:saveState>
<t:saveState id="CMGTTOMSS369" value="#{CategoryDataBean.categoryVO.auditTimeStamp}"></t:saveState>
<t:saveState id="CMGTTOMSS370" value="#{CategoryDataBean.categoryVO.auditUserID}"></t:saveState>
<t:saveState id="CMGTTOMSS371" value="#{CategoryDataBean.categoryVO.addedAuditTimeStamp}"></t:saveState>
<t:saveState id="CMGTTOMSS372" value="#{CategoryDataBean.categoryVO.addedAuditUserID}"></t:saveState>
<t:saveState id="CMGTTOMSS373" value="#{CategoryDataBean.categoryAuditHistoryList}"></t:saveState>
<t:saveState id="CMGTTOMSS374" value="#{CategoryDataBean.categoryHistoryRender}"></t:saveState>

	<%-- AUDIT PART --%>


<m:section id="PROVIDERMMS20120731164811515" styleClass="expand">
	<m:legend>
		<f:verbatim>
			<h:outputLink				onclick="toggleTest('audit_plus',2);	                 plusMinusForRefreshTest('audit_plus',this,'addlinfo_hidden');return false;"				id="plusMinus_Auditmore" styleClass="plus">
				<h:outputText id="audit" value="#{ctg['label.ref.audit']}" />
			</h:outputLink>
			<h:inputHidden id="addlinfo_hidden" value="plus"></h:inputHidden>
			<h:inputHidden id="audit_open"				value="#{CategoryDataBean.auditOpen}"></h:inputHidden>
		</f:verbatim>
	</m:legend>
	<m:div sid="audit_plus">
		<m:div id="log_detail" styleClass="">
	
				<t:dataTable cellspacing="0" styleClass="dataTable"					id="cshistory_table" width="100%" rows="10"					value="#{CategoryDataBean.categoryAuditHistoryList}" 					var="cathistory">
					<h:column id="PRGCMGTC60">
						<f:facet name="header">
							<h:outputText id="Timestamp"								value="Timestamp" />
						</f:facet>
						<t:commandLink id="PRGCMGTCL176" action="#{CategoryControllerBean.showColumnChange}">
							<h:outputText id="PRGCMGTOT1192" value="#{cathistory.auditTimeStamp}" >
								<f:convertDateTime type="both" pattern="MM/dd/yyyy hh:mm:ss a" />
							</h:outputText>
							<f:param name="auditLogSK" value="#{cathistory.auditLogSK}" />
						</t:commandLink>
					</h:column>
					<h:column id="PRGCMGTC61">
						<f:facet name="header">
							<h:outputText id="PRGCMGTOT1193" value="User ID" />
						</f:facet>
						<h:outputText id="PRGCMGTOT1194"  value="#{cathistory.auditUserID}" />
					</h:column>
					<h:column id="PRGCMGTC62">
						<f:facet name="header">
							<h:outputText id="PRGCMGTOT1195" value="Table Name" />
						</f:facet>
						<h:outputText id="PRGCMGTOT1196"  value="#{cathistory.tableName}" />
					</h:column>
					<h:column id="PRGCMGTC63">
						<f:facet name="header">
							<h:outputText id="PRGCMGTOT1197" value="Activity Type" />
						</f:facet>
						<h:outputText id="PRGCMGTOT1198" value="#{cathistory.dmlTypeCode}" />
					</h:column>
					<h:column id="PRGCMGTC64">
						<f:facet name="header">
							<h:outputText id="PRGCMGTOT1199" value="Original Entry Date/Time" />
						</f:facet>
						<h:outputText id="PRGCMGTOT1200" value="#{CategoryDataBean.categoryVO.auditTimeStamp}">
							<f:convertDateTime type="both" pattern="MM/dd/yyyy hh:mm:ss a" />
						</h:outputText>
					</h:column>
					<h:column id="PRGCMGTC65">
						<f:facet name="header">
							<h:outputText id="PRGCMGTOT1201" value="Original Entry User ID" />
						</f:facet>
						<h:outputText id="PRGCMGTOT1202" value="#{CategoryDataBean.categoryVO.auditUserID}" />
					</h:column>
					<h:column id="PRGCMGTC66">
						<f:facet name="header">
							<h:outputText id="PRGCMGTOT1203" value="Transaction ID" />
						</f:facet>
					<h:outputText id="PRGCMGTOT1204" value="#{cathisotry.cscnNumber}" />
				</h:column>
			</t:dataTable>
			<t:dataScroller id="CMGTTOMDS43" for="cshistory_table" paginator="true"				paginatorActiveColumnStyle='font-weight:bold;' paginatorMaxPages="3"    				immediate="false" pageCountVar="pageCount" pageIndexVar="pageIndex"				firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"				rowsCountVar="rowsCount"				style="float:right;position:relative;bottom:-6px;text-decoration:underline;"> 
				<f:facet name="previous">
   					<t:commandLink styleClass="commandLink" id="previousLink"    						style="text-decoration:underline;" value=" << "   						action="#{CategoryControllerBean.previous}"   						rendered="#{CategoryDataBean.showPrevious}">
   								</t:commandLink>     
							</f:facet>
							<f:facet name="next">    
								<t:commandLink styleClass="commandLink" id="nextlink" 									style="text-decoration:underline;" value=" >> "
   									action="#{CategoryControllerBean.next}"
   									rendered="#{CategoryDataBean.showNext}">
   								</t:commandLink>     
						    </f:facet>   
				   <h:outputText id="PRGCMGTOT1205" rendered="#{rowsCount > 0}"
     			value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
     			style="float:left;position:relative;bottom:-6px;font-weight:bold;" />
			</t:dataScroller>
		</m:div>
		<m:br/><m:br/>
		<m:div styleClass="fd_Title" 
			rendered="#{CategoryDataBean.auditColumnHistoryRender}" 
			style="height:18px;display:block;">
			<m:table id="PROVIDERMMT20120731164811516" width="100%">
				<m:tbody>
					<m:tr valign="center">
						<m:td align="left">
							<m:div styleClass="float1">
								<h:outputText id="PRGCMGTOT1206" value="Category Info Table" styleClass="strong"/>
							</m:div>
						</m:td>
						<m:td align="right">
							<m:div styleClass="floatr">
								<t:commandLink id="PRGCMGTCL177" action="#{CategoryControllerBean.closeColumnChange}" 									value="Close" style="color:#fff;"/>
							</m:div>
						</m:td>
					</m:tr>
				</m:tbody>
			</m:table>
		</m:div>

		<m:div rendered="#{CategoryDataBean.auditColumnHistoryRender}">
			<m:table id="PROVIDERMMT20120731164811517" width="100%">
				<m:tr>
					<m:td>
						<h:outputText id="PRGCMGTOT1207" value="#{msg['javax.portlet.title']}" styleClass="strong" />
					</m:td>
				</m:tr>
			</m:table>
		</m:div>
		<m:div styleClass="" rendered="#{CategoryDataBean.auditColumnHistoryRender}">
			<t:dataTable cellspacing="0" styleClass="dataTable" id="vvcolumnhistory_table" width="100%" align="center"				value="#{CategoryDataBean.selectedAuditLog.auditLogColumnValue}"				rowClasses="row,row_alt" var="colValue">
				<h:column id="colValueColName">
					<f:facet name="header">
						<h:outputText id="hColValueColName" value="Column Name" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1208" value="#{colValue.columnName}" />
				</h:column>
				<h:column id="colValueBefore">
					<f:facet name="header">
						<h:outputText id="hColValueBefore" value="Before" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1209" value="#{colValue.beforeValue}" />
				</h:column>
				<h:column id="colValueAfter">
					<f:facet name="header">
						<h:outputText id="hColValueAfter" value="After" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1210" value="#{colValue.afterValue}" />
				</h:column>
			</t:dataTable>
		</m:div>
	</m:div>
</m:section>
	
	<%-- AUDIT END --%>
