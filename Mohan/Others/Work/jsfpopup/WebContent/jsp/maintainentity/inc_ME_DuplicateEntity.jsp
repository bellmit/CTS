<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>


<%-- AUDIT PART --%>
<m:section id="PROVIDERMMS20120731164811589" styleClass="expand">
	<m:legend>
		<f:verbatim>
			<h:outputLink				onclick="toggleTest('audit_plus',2);                     plusMinusForRefreshTest('audit_plus',this,'addlinfo_hidden');return false;"				id="plusMinus_Auditmore" styleClass="plus">
				<h:outputText id="audit" value="Audit" />
			</h:outputLink>
			<h:inputHidden id="addlinfo_hidden" value="plus"></h:inputHidden>
			<h:inputHidden id="audit_open" value="#{CMEntityMaintainDataBean.auditOpen}"></h:inputHidden>
		</f:verbatim>
	</m:legend>

	<m:div sid="audit_plus">
		<m:div id="log_detail" styleClass="">

			<t:dataTable cellspacing="0" styleClass="dataTable"				id="vvhistory_table" width="100%" rows="10"				value="#{CMEntityMaintainDataBean.auditHistoryList}"				var="entityDupAuditHistory">

				<h:column id="PRGCMGTC93">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT1387" value="Timestamp" />
					</f:facet>
					<t:commandLink id="PRGCMGTCL199"						action="#{CMEntityMaintainControllerBean.showColumnChange}">
						<h:outputText id="PRGCMGTOT1388" value="#{entityDupAuditHistory.auditTimeStamp}">
							<f:convertDateTime type="both" pattern="MM/dd/yyyy hh:mm:ss a" />
						</h:outputText>
						<f:param name="auditLogSK" value="#{entityDupAuditHistory.auditLogSK}" />
					</t:commandLink>
				</h:column>
				<h:column id="PRGCMGTC94">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT1389" value="User ID" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1390" value="#{entityDupAuditHistory.auditUserID}" />
				</h:column>
				<h:column id="PRGCMGTC95">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT1391" value="Table Name" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1392" value="#{entityDupAuditHistory.tableName}" />
				</h:column>
				<h:column id="PRGCMGTC96">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT1393" value="Activity Type" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1394" value="#{entityDupAuditHistory.dmlTypeCode}" />
				</h:column>
				<h:column id="PRGCMGTC97">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT1395" value="Original Entry Date/Time" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1396" value="#{entityDupAuditHistory.auditTimeStamp}">
						<f:convertDateTime type="both" pattern="MM/dd/yyyy hh:mm:ss a" />
					</h:outputText>
				</h:column>
				<h:column id="PRGCMGTC98">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT1397" value="Original Entry User ID" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1398" value="#{entityDupAuditHistory.auditUserID}" />
				</h:column>
				<h:column id="PRGCMGTC99">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT1399" value="Transaction ID" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1400" value="#{entityDupAuditHistory.cscnNumber}" />
				</h:column>
			</t:dataTable>
			
			<t:dataScroller id="CMGTTOMDS49" for="vvhistory_table" paginator="true"				paginatorActiveColumnStyle='font-weight:bold;' paginatorMaxPages="3"    				immediate="false" pageCountVar="pageCount" pageIndexVar="pageIndex"				firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"				rowsCountVar="rowsCount"				style="float:right;position:relative;bottom:-6px;text-decoration:underline;"> 
				<f:facet name="previous">
   					<t:commandLink styleClass="commandLink" id="previousLink"    						style="text-decoration:underline;" value=" << "   						action="#{CMEntityMaintainControllerBean.previous}"   						rendered="#{CMEntityMaintainDataBean.showPrevious}">
   								</t:commandLink>     
							</f:facet>
							<f:facet name="next">    
								<t:commandLink styleClass="commandLink" id="nextlink" 									style="text-decoration:underline;" value=" >> "
   									action="#{CMEntityMaintainControllerBean.next}"
   									rendered="#{CMEntityMaintainDataBean.showNext}">
   								</t:commandLink>     
						    </f:facet>   
				   <h:outputText id="PRGCMGTOT1401" rendered="#{rowsCount > 0}"
     			value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
     			style="float:left;position:relative;bottom:-6px;font-weight:bold;" />
			</t:dataScroller>
		</m:div>

		<m:br />
		<m:br />
		
		<m:div styleClass=""
			rendered="#{CMEntityMaintainDataBean.auditColumnHistoryRender}">
			<t:dataTable cellspacing="0" styleClass="dataTable"				id="vvcolumnhistory_table" width="90%" align="center"				value="#{CMEntityMaintainDataBean.selectedAuditLog.auditLogColumnValue}"				rowClasses="row,row_alt" var="colValue">
				<h:column id="colValueColName">
					<f:facet name="header">
						<h:outputText id="hColValueColName" value="Column Name" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1402" value="#{colValue.columnName}" />
				</h:column>
				<h:column id="colValueBefore">
					<f:facet name="header">
						<h:outputText id="hColValueBefore" value="Before" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1403" value="#{colValue.beforeValue}" />
				</h:column>
				<h:column id="colValueAfter">
					<f:facet name="header">
						<h:outputText id="hColValueAfter" value="After" />
					</f:facet>
					<h:outputText id="PRGCMGTOT1404" value="#{colValue.afterValue}" />
				</h:column>
			</t:dataTable>
		</m:div>

	</m:div>
</m:section>
<%-- AUDIT END --%>

