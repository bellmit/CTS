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

<t:saveState id="CMGTTOMSS382" value="#{customFieldDataBean.customFieldVO.auditTimeStamp}"></t:saveState>
<t:saveState id="CMGTTOMSS383" value="#{customFieldDataBean.customFieldVO.auditUserID}"></t:saveState>
<t:saveState id="CMGTTOMSS384"	value="#{customFieldDataBean.customFieldVO.addedAuditTimeStamp}"></t:saveState>
<t:saveState id="CMGTTOMSS385"	value="#{customFieldDataBean.customFieldVO.addedAuditUserID}"></t:saveState>
<t:saveState id="CMGTTOMSS386" value="#{customFieldDataBean.cfAuditHistoryRender}"></t:saveState>
<t:saveState id="CMGTTOMSS387" value="#{customFieldDataBean.auditColumnHistoryRender}"></t:saveState>

<m:section id="PROVIDERMMS20120731164811544" styleClass="expand">

	<m:legend>
		<f:verbatim>
			<h:outputLink				onclick="toggleTest('audit_plus_customField',2);                     plusMinusForRefreshTest('audit_plus_customField',this,'addlinfo_hidden_customField');return false;"				id="plusMinus_Auditmore" styleClass="plus">
				<h:outputText id="audit" value="#{msg['label.customFields.audit']}" />
			</h:outputLink>
			<h:inputHidden id="addlinfo_hidden_customField" value="plus" />
			<h:inputHidden id="audit_open_cf"				value="#{customFieldDataBean.cfAuditOpen}" />
		</f:verbatim>
	</m:legend>
	<m:div sid="audit_plus_customField">
		<m:div id="log_detail" styleClass="">

			<t:dataTable cellspacing="0" styleClass="dataTable"				id="vvhistory_table" width="100%" rows="10"				value="#{customFieldDataBean.cfAuditHistoryList}" var="customHistory">
				<h:column id="PRGCMGTC72">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT1287" value="Timestamp" />
					</f:facet>
					<t:commandLink id="PRGCMGTCL187"						action="#{customFieldControllerBean.showColumnChange}">
						<h:outputText id="PRGCMGTOT1288" value="#{customHistory.auditTimeStamp}">
							<f:convertDateTime type="both" pattern="MM/dd/yyyy hh:mm:ss a" />
						</h:outputText>
						<f:param name="auditLogSK" value="#{customHistory.auditLogSK}" />
					</t:commandLink>
				</h:column>
				<h:column id="PRGCMGTC73">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT1289" value="User ID" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1290" value="#{customHistory.auditUserID}" />
				</h:column>
				<h:column id="PRGCMGTC74">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT1291" value="Table Name" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1292" value="#{customHistory.tableName}" />
				</h:column>
				<h:column id="PRGCMGTC75">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT1293" value="Activity Type" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1294" value="#{customHistory.dmlTypeCode}" />
				</h:column>
				<h:column id="PRGCMGTC76">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT1295" value="Original Entry Date/Time" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1296" value="#{customFieldDataBean.customFieldVO.auditTimeStamp}">
						<f:convertDateTime type="both" pattern="MM/dd/yyyy hh:mm:ss a" />
					</h:outputText>
				</h:column>
				<h:column id="PRGCMGTC77">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT1297" value="Original Entry User ID" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1298" value="#{customFieldDataBean.customFieldVO.auditUserID}" />
				</h:column>
				<h:column id="PRGCMGTC78">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT1299" value="Transaction ID" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1300" value="#{customHistory.cscnNumber}" />
				</h:column>
			</t:dataTable>

			<t:dataScroller id="CMGTTOMDS45" for="vvhistory_table" paginator="true"				paginatorActiveColumnStyle='font-weight:bold;' paginatorMaxPages="3"				immediate="false" pageCountVar="pageCount" pageIndexVar="pageIndex"				firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"				rowsCountVar="rowsCount"				style="float:right;position:relative;bottom:-6px;text-decoration:underline;">
				<f:facet name="previous">
					<t:commandLink styleClass="commandLink" id="previousLink"						style="text-decoration:underline;" value=" << "						action="#{customFieldControllerBean.previous}"						rendered="#{customFieldDataBean.showPrevious}">
					</t:commandLink>
				</f:facet>
				<f:facet name="next">
					<t:commandLink styleClass="commandLink" id="nextlink"						style="text-decoration:underline;" value=" >> "
						action="#{customFieldControllerBean.next}"
						rendered="#{customFieldDataBean.showNext}">
					</t:commandLink>
				</f:facet>
				<h:outputText id="PRGCMGTOT1301" rendered="#{rowsCount > 0}"
					value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
					style="float:left;position:relative;bottom:-6px;font-weight:bold;" />
			</t:dataScroller>
		</m:div>
		
		<m:br/><m:br/>
		<m:div styleClass="fd_Title" 
			rendered="#{customFieldDataBean.auditColumnHistoryRender}" 
			style="height:18px;display:block;">
			<m:table id="PROVIDERMMT20120731164811545" width="100%">
				<m:tbody>
					<m:tr valign="center">
						<m:td align="left">
							<m:div styleClass="float1">
								<h:outputText id="PRGCMGTOT1302" value="Custom Field Info Table" styleClass="strong"/>
							</m:div>
						</m:td>
						<m:td align="right">
							<m:div styleClass="floatr">
								<t:commandLink id="PRGCMGTCL188" action="#{customFieldControllerBean.closeColumnChange}" 									value="Close" style="color:#fff;"/>
							</m:div>
						</m:td>
					</m:tr>
				</m:tbody>
			</m:table>
		</m:div>

		<m:div rendered="#{customFieldDataBean.auditColumnHistoryRender}">
			<m:table id="PROVIDERMMT20120731164811546" width="100%">
				<m:tr>
					<m:td>
						<h:outputText id="PRGCMGTOT1303" value="#{msg['javax.portlet.title']}" styleClass="strong" />
					</m:td>
				</m:tr>
			</m:table>
		</m:div>

		<m:div styleClass=""
			rendered="#{customFieldDataBean.auditColumnHistoryRender}">
			<t:dataTable cellspacing="0" styleClass="dataTable"				id="vvcolumnhistory_table" width="100%" align="center"				value="#{customFieldDataBean.selectedAuditLog.auditLogColumnValue}"				rowClasses="row,row_alt" var="colValue">
				<h:column id="colValueColName">
					<f:facet name="header">
						<h:outputText id="hColValueColName" value="Column Name" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1304" value="#{colValue.columnName}" />
				</h:column>
				<h:column id="colValueBefore">
					<f:facet name="header">
						<h:outputText id="hColValueBefore" value="Before" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1305" value="#{colValue.beforeValue}" />
				</h:column>
				<h:column id="colValueAfter">
					<f:facet name="header">
						<h:outputText id="hColValueAfter" value="After" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1306" value="#{colValue.afterValue}" />
				</h:column>
			</t:dataTable>
		</m:div>
	</m:div>
</m:section>
<%-- AUDIT END --%>
