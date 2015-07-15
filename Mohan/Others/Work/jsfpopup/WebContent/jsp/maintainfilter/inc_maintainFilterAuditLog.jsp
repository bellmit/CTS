<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>


<%--<t:saveState id="CMGTTOMSS449" value="#{FilterDataBean.filterAuditRender}" />
<t:saveState id="CMGTTOMSS450" value="#{FilterDataBean.tempFilterVO.auditTimeStamp}"></t:saveState>
<t:saveState id="CMGTTOMSS451" value="#{FilterDataBean.tempFilterVO.auditUserID}"></t:saveState>
<t:saveState id="CMGTTOMSS452" value="#{FilterDataBean.tempFilterVO.addedAuditTimeStamp}"></t:saveState>
<t:saveState id="CMGTTOMSS453" value="#{FilterDataBean.tempFilterVO.addedAuditUserID}"></t:saveState>
<t:saveState id="CMGTTOMSS454"	value="#{FilterDataBean.tempFilterVO.filterAuditHistoryList}"></t:saveState>
	 --%>

<%-- AUDIT PART --%>


<m:section id="PROVIDERMMS20120731164811598" styleClass="expand">
	<m:legend>
		<h:outputLink			onclick="toggleTest('maintainFilterAuditLog',2);	                     plusMinusForRefreshTest('maintainFilterAuditLog',this,'addlinfo_hidden');return false;"			id="plusMinus_Auditmore" styleClass="plus">
			<h:outputText id="audit" value="#{filter['label.filter.audit']}" />
		</h:outputLink>
		<h:inputHidden id="addlinfo_hidden"			value="plus"></h:inputHidden>
		<h:inputHidden id="audit_open" value="#{FilterDataBean.auditOpen}"></h:inputHidden>
	</m:legend>

	<m:div sid="maintainFilterAuditLog">
		<!-- Audit log details -->
		<m:div styleClass="">

			<!-- Table Headers -->
			<t:dataTable cellspacing="0" styleClass="dataTable"				id="vvhistory_table" width="100%"				value="#{FilterDataBean.filterAuditHistoryList}" var="filters">

				<h:column id="PRGCMGTC100">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT1451" value="#{msgAudit['label.timestamp']}" />
					</f:facet>
					<t:commandLink id="PRGCMGTCL215" action="#{FilterControllerBean.showColumnChange}">
						<h:outputText id="PRGCMGTOT1452" value="#{filters.auditTimeStamp}" />
						<f:param name="auditLogSK" value="#{filters.auditLogSK}" />
					</t:commandLink>
				</h:column>
				<h:column id="PRGCMGTC101">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT1453" value="#{msgAudit['label.userId']}" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1454" value="#{filters.auditUserID}" />
				</h:column>
				<h:column id="PRGCMGTC102">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT1455" value="#{msgAudit['label.tableName']}" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1456" value="#{filters.tableName}" />
				</h:column>
				<h:column id="PRGCMGTC103">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT1457" value="#{msgAudit['label.activityType']}" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1458" value="#{filters.dmlTypeCode}" />
				</h:column>
				<h:column id="PRGCMGTC104">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT1459" value="#{msgAudit['label.origEntryDate']}" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1460"						value="#{FilterDataBean.filterVO.addedAuditTimeStamp}" />
				</h:column>
				<h:column id="PRGCMGTC105">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT1461" value="#{msgAudit['label.origEntryUser']}" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1462" value="#{FilterDataBean.filterVO.addedAuditUserID}" />
				</h:column>
				<h:column id="PRGCMGTC106">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT1463" value="#{msgAudit['label.tranId']}" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1464" value="#{filters.cscnNumber}" />
				</h:column>
			</t:dataTable>

			<!-- Audit log details -->

			<t:dataScroller id="CMGTTOMDS51" for="vvhistory_table" paginator="true"				paginatorActiveColumnStyle='font-weight:bold;' paginatorMaxPages="3"				immediate="false" pageCountVar="pageCount" pageIndexVar="pageIndex"				firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"				rowsCountVar="rowsCount"				style="float:right;position:relative;bottom:-6px;text-decoration:underline;"				rendered="#{rowsCount > 0}">
				<f:facet name="previous">
					<t:commandLink styleClass="commandLink" id="previousLink"						style="text-decoration:underline;" value=" << "						action="#{FilterControllerBean.previous}"						rendered="#{FilterDataBean.showPrevious}">
					</t:commandLink>
				</f:facet>
				<f:facet name="next">
					<t:commandLink styleClass="commandLink" id="nextlink"						style="text-decoration:underline;" value=" >> "
						action="#{FilterControllerBean.next}"
						rendered="#{FilterDataBean.showNext}">
					</t:commandLink>
				</f:facet>
				<h:outputText id="PRGCMGTOT1465" rendered="#{rowsCount > 0}"
					value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
					style="float:left;position:relative;bottom:-6px;font-weight:bold;" />
			</t:dataScroller>
		</m:div>

		<m:br />
		<m:br />
		<m:div styleClass="fd_Title"
			rendered="#{FilterDataBean.filterAuditRender}"
			style="height:18px;display:block;">
			<m:table id="PROVIDERMMT20120731164811599" width="100%">
				<m:tbody>
					<m:tr valign="center">
						<m:td align="left">
							<m:div styleClass="float1">
								<h:outputText id="PRGCMGTOT1466" value="Audit History" styleClass="strong" />
							</m:div>
						</m:td>
						<m:td align="right">
							<m:div styleClass="floatr">
								<t:commandLink id="PRGCMGTCL216"									action="#{FilterControllerBean.closeColumnChange}"									value="#{msgAudit['label.close']}" style="color:#fff;" />
							</m:div>
						</m:td>
					</m:tr>
				</m:tbody>
			</m:table>
		</m:div>

		<m:div styleClass="" rendered="#{FilterDataBean.filterAuditRender}">
			<t:dataTable cellspacing="0" styleClass="dataTable"				id="vvcolumnhistory_table" width="90%" align="center"				value="#{FilterDataBean.selectedAuditLog.auditLogColumnValue}"				rowClasses="row,row_alt" var="colValue">
				<h:column id="colValueColName">
					<f:facet name="header">
						<h:outputText id="hColValueColName"							value="#{msgAudit['label.colName']}" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1467" value="#{colValue.columnName}" />
				</h:column>
				<h:column id="colValueBefore">
					<f:facet name="header">
						<h:outputText id="hColValueBefore"							value="#{msgAudit['label.before']}" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1468" value="#{colValue.beforeValue}" />
				</h:column>
				<h:column id="colValueAfter">
					<f:facet name="header">
						<h:outputText id="hColValueAfter"							value="#{msgAudit['label.after']}" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1469" value="#{colValue.afterValue}" />
				</h:column>
			</t:dataTable>
		</m:div>
	</m:div>
</m:section>
<%-- AUDIT END --%>

