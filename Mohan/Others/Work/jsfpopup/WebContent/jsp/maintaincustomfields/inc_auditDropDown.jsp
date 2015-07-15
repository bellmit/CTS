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

<t:saveState id="CMGTTOMSS388"	value="#{customFieldDataBean.customFieldVO.dropDownVO.auditTimeStamp}"></t:saveState>
<t:saveState id="CMGTTOMSS389"	value="#{customFieldDataBean.customFieldVO.dropDownVO.auditUserID}"></t:saveState>
<t:saveState id="CMGTTOMSS390"	value="#{customFieldDataBean.customFieldVO.dropDownVO.addedAuditTimeStamp}"></t:saveState>
<t:saveState id="CMGTTOMSS391"	value="#{customFieldDataBean.customFieldVO.dropDownVO.addedAuditUserID}"></t:saveState>
<t:saveState id="CMGTTOMSS392" value="#{customFieldDataBean.ddAuditHistoryRender}"></t:saveState>
<t:saveState id="CMGTTOMSS393" value="#{customFieldDataBean.ddAuditColumnHistoryRender}"></t:saveState>


<m:section id="PROVIDERMMS20120731164811547" styleClass="expand">

	<m:legend>
		<f:verbatim>
			<h:outputLink				onclick="toggleTest('audit_plus_dropDown',2);                     plusMinusForRefreshTest('audit_plus_dropDown',this,'addlinfo_hidden_dropDown');return false;"				id="plusMinus_AuditDdmore" styleClass="plus">
				<h:outputText id="audit" value="#{msg['label.customFields.audit']}" />
			</h:outputLink>
			<h:inputHidden id="addlinfo_hidden_dropDown" value="plus"></h:inputHidden>
			<h:inputHidden id="audit_open_dd"				value="#{customFieldDataBean.ddAuditOpen}"></h:inputHidden>
		</f:verbatim>
	</m:legend>
	<m:div sid="audit_plus_dropDown">
		<m:div id="log_detail_dd" styleClass="">

			<t:dataTable cellspacing="0" styleClass="dataTable"				id="ddhistory_table" width="100%" rows="10"				value="#{customFieldDataBean.ddAuditHistoryList}" var="ddHistory">
				<h:column id="PRGCMGTC79">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT1307" value="Timestamp" />
					</f:facet>
					<t:commandLink id="PRGCMGTCL189"						action="#{customFieldControllerBean.showDdColumnChange}">
						<h:outputText id="PRGCMGTOT1308" value="#{ddHistory.auditTimeStamp}">
							<f:convertDateTime type="both" pattern="MM/dd/yyyy hh:mm:ss a" />
						</h:outputText>
						<f:param name="auditLogSK" value="#{ddHistory.auditLogSK}" />
					</t:commandLink>
				</h:column>
				<h:column id="PRGCMGTC80">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT1309" value="User ID" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1310" value="#{ddHistory.auditUserID}" />
				</h:column>
				<h:column id="PRGCMGTC81">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT1311" value="Table Name" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1312" value="#{ddHistory.tableName}" />
				</h:column>
				<h:column id="PRGCMGTC82">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT1313" value="Activity Type" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1314" value="#{ddHistory.dmlTypeCode}" />
				</h:column>
				<h:column id="PRGCMGTC83">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT1315" value="Original Entry Date/Time" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1316" value="#{customFieldDataBean.customFieldVO.dropDownVO.auditTimeStamp}">
						<f:convertDateTime type="both" pattern="MM/dd/yyyy hh:mm:ss a" />
					</h:outputText>
				</h:column>
				<h:column id="PRGCMGTC84">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT1317" value="Original Entry User ID" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1318" value="#{customFieldDataBean.customFieldVO.dropDownVO.auditUserID}" />
				</h:column>
				<h:column id="PRGCMGTC85">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT1319" value="Transaction ID" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1320" value="#{ddHistory.cscnNumber}" />
				</h:column>
			</t:dataTable>

			<t:dataScroller id="CMGTTOMDS46" for="ddhistory_table" paginator="true"				paginatorActiveColumnStyle='font-weight:bold;' paginatorMaxPages="3"				immediate="false" pageCountVar="ddPageCount"				pageIndexVar="ddPageIndex" firstRowIndexVar="ddFirstRowIndex"				lastRowIndexVar="ddLastRowIndex" rowsCountVar="ddRowsCount"				style="float:right;position:relative;bottom:-6px;text-decoration:underline;">
				<f:facet name="previous">
					<t:commandLink styleClass="commandLink" id="previousLink"						style="text-decoration:underline;" value=" << "						action="#{customFieldControllerBean.ddprevious}">
						<%--rendered="#{customFieldDataBean.ddShowPrevious}"--%>
					</t:commandLink>
				</f:facet>
				<f:facet name="next">
					<t:commandLink styleClass="commandLink" id="nextlink"						style="text-decoration:underline;" value=" >> "
						action="#{customFieldControllerBean.ddNext}">
						<%--rendered="#{customFieldDataBean.ddShowNext}"--%>
					</t:commandLink>
				</f:facet>
				<h:outputText id="PRGCMGTOT1321" rendered="#{ddRowsCount > 0}"
					value="#{ddFirstRowIndex} - #{ddLastRowIndex} of #{ddRowsCount}"
					style="float:left;position:relative;bottom:-6px;font-weight:bold;" />
			</t:dataScroller>
		</m:div>

		<m:br />
		<m:br />
		<m:div styleClass="dd_Title"
			rendered="#{customFieldDataBean.ddAuditColumnHistoryRender}"
			style="height:18px;display:block;">
			<m:table id="PROVIDERMMT20120731164811548" width="100%">
				<m:tbody>
					<m:tr valign="center">
						<m:td align="left">
							<m:div styleClass="float1">
								<h:outputText id="PRGCMGTOT1322" value="Drop Down Info Table"									styleClass="strong" />
							</m:div>
						</m:td>
						<m:td align="right">
							<m:div styleClass="floatr">
								<t:commandLink id="PRGCMGTCL190"									action="#{customFieldControllerBean.ddCloseColumnChange}"									value="#{msgAudit['label.close']}" style="color:#fff;" />
							</m:div>
						</m:td>
					</m:tr>
				</m:tbody>
			</m:table>
		</m:div>

		<m:div rendered="#{customFieldDataBean.ddAuditHistoryRender}">
			<m:table id="PROVIDERMMT20120731164811549" width="100%">
				<m:tr>
					<m:td>
						<h:outputText id="PRGCMGTOT1323" value="#{msg['javax.portlet.title']}"							styleClass="strong" />
					</m:td>
				</m:tr>
			</m:table>
		</m:div>

		<m:div styleClass=""
			rendered="#{customFieldDataBean.ddAuditColumnHistoryRender}">
			<t:dataTable cellspacing="0" styleClass="dataTable"				id="ddcolumnhistory_table" width="100%" align="center"				value="#{customFieldDataBean.ddSelectedAuditLog.auditLogColumnValue}"				rowClasses="row,row_alt" var="colValue">
				<h:column id="colValueColName">
					<f:facet name="header">
						<h:outputText id="hColValueColName" value="Column Name" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1324" value="#{colValue.columnName}" />
				</h:column>
				<h:column id="colValueBefore">
					<f:facet name="header">
						<h:outputText id="hColValueBefore" value="Before" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1325" value="#{colValue.beforeValue}" />
				</h:column>
				<h:column id="colValueAfter">
					<f:facet name="header">
						<h:outputText id="hColValueAfter" value="After" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1326" value="#{colValue.afterValue}" />
				</h:column>
			</t:dataTable>
		</m:div>
	</m:div>
</m:section>
