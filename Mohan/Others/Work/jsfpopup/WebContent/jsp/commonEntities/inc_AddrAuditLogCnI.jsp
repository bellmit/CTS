<%-- 
  - Author(s): Wipro
  - Date: Mon Jan 29 14:37:45 IST 2007
  - Copyright Notice: Copyright (c) 2006 Affiliated Computer Services, Inc.
  - @ 2006
  - Description: 
  --%>
<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>


<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>

<t:saveState id="CMGTTOMSS99"	value="#{commonEntityDataBean.commonEntityVO.addressVO.auditTimeStamp}"></t:saveState>
<t:saveState id="CMGTTOMSS100"	value="#{commonEntityDataBean.commonEntityVO.addressVO.auditUserID}"></t:saveState>
<t:saveState id="CMGTTOMSS101"	value="#{commonEntityDataBean.commonEntityVO.addressVO.addedAuditTimeStamp}"></t:saveState>
<t:saveState id="CMGTTOMSS102"	value="#{commonEntityDataBean.commonEntityVO.addressVO.addedAuditUserID}"></t:saveState>
<t:saveState id="CMGTTOMSS103" value="#{commonEntityDataBean.addressAuditHistList}"></t:saveState>
<t:saveState id="CMGTTOMSS104" value="#{commonEntityDataBean.addressHistRndr}"></t:saveState>
<f:loadBundle
	basename="com.acs.enterprise.common.program.commonentities.nl.PGM_CE_CommonAddress"
	var="cmnAddMsg" />

<%--  AUDIT PART --%>
<m:section id="PROVIDERMMS20120731164811176" styleClass="expand">
	<m:legend>	
	
			<h:outputLink				onclick="toggleTest('audit_plus3',2);                     plusMinusForRefreshTest('audit_plus3',this,'addlinfoAdd_hidden3');return false;"				id="plusMinus_AuditmoreDisp" styleClass="plus">
				<h:outputText id="audit" value="Audit" />
			</h:outputLink>
			<h:inputHidden id="addlinfoAdd_hidden3" value="plus"></h:inputHidden>
			<h:inputHidden id="audit_open"				value="#{commonEntityDataBean.auditAddress}"></h:inputHidden>
		
	</m:legend>
	
	<m:div sid="audit_plus3">


		<m:div id="log_detail" styleClass="">

			<t:dataTable cellspacing="0" styleClass="dataTable"				id="vvhistory_table3" width="100%" rows="10"				value="#{commonEntityDataBean.addressAuditHistList}"				var="sysParamDethistory1">

				<h:column id="PRGCMGTC1">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT359" value="Timestamp" />
					</f:facet>
					<t:commandLink id="PRGCMGTCL72"						action="#{addressControllerBean.showColumnChange}">
						<h:outputText id="PRGCMGTOT360" value="#{sysParamDethistory1.auditTimeStamp}">
							<f:convertDateTime type="both" pattern="MM/dd/yyyy hh:mm:ss a" />
						</h:outputText>
						<f:param name="auditLogSK" value="#{sysParamDethistory1.auditLogSK}" />
					</t:commandLink>
				</h:column>
				<h:column id="PRGCMGTC2">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT361" value="User ID" />
					</f:facet>
					<h:outputText id="PRGCMGTOT362" value="#{sysParamDethistory1.auditUserID}" />
				</h:column>
				<h:column id="PRGCMGTC3">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT363" value="Table Name" />
					</f:facet>
					<h:outputText id="PRGCMGTOT364" value="#{sysParamDethistory1.tableName}" />
				</h:column>
				<h:column id="PRGCMGTC4">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT365" value="Activity Type" />
					</f:facet>
					<h:outputText id="PRGCMGTOT366" value="#{sysParamDethistory1.dmlTypeCode}" />
				</h:column>
				<h:column id="PRGCMGTC5">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT367" value="Original Entry Date/Time" />
					</f:facet>
					<h:outputText id="PRGCMGTOT368" value="#{commonEntityDataBean.commonEntityVO.addressVO.addedAuditTimeStamp}">
						<f:convertDateTime type="both" pattern="MM/dd/yyyy hh:mm:ss a" />
					</h:outputText>
				</h:column>
				<h:column id="PRGCMGTC6">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT369" value="Original Entry User ID" />
					</f:facet>
					<h:outputText id="PRGCMGTOT370" value="#{commonEntityDataBean.commonEntityVO.addressVO.addedAuditUserID}" />
				</h:column>
				<h:column id="PRGCMGTC7">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT371" value="Transaction ID" />
					</f:facet>
					<h:outputText id="PRGCMGTOT372" value="#{sysParamDethistory1.cscnNumber}" />
				</h:column>
			</t:dataTable>
			<t:dataScroller id="CMGTTOMDS13" for="vvhistory_table3" paginator="true"				paginatorActiveColumnStyle='font-weight:bold;' paginatorMaxPages="3"    				immediate="false" pageCountVar="pageCount" pageIndexVar="pageIndex3"				firstRowIndexVar="firstRowIndex3" lastRowIndexVar="lastRowIndex3"				rowsCountVar="rowsCount3"				style="float:right;position:relative;bottom:-6px;text-decoration:underline;"> 
				<f:facet name="previous">
   					<t:commandLink styleClass="commandLink" id="previousLink"    						style="text-decoration:underline;" value=" << "   						action="#{addressControllerBean.previous1}"   						rendered="#{commonEntityDataBean.showPrevious}">
   					</t:commandLink>     
				</f:facet>
				<f:facet name="next">    
					<t:commandLink styleClass="commandLink" id="nextlink" 						style="text-decoration:underline;" value=" >> "
   						action="#{addressControllerBean.next1}"
   						rendered="#{commonEntityDataBean.showNext}">
   					</t:commandLink>     
			    </f:facet>   
				<h:outputText id="PRGCMGTOT373" rendered="#{rowsCount3 > 0}"
     				value="#{firstRowIndex3} - #{lastRowIndex3} of #{rowsCount3}"
     				style="float:left;position:relative;bottom:-6px;font-weight:bold;" />
			</t:dataScroller>
		</m:div>
		<m:br/><m:br/>
		<m:div styleClass="fd_Title" 
			rendered="#{commonEntityDataBean.addressHistRndr}" 
			style="height:18px;display:block;">
			<m:table id="PROVIDERMMT20120731164811177" width="100%">
				<m:tbody>
					<m:tr valign="center">
						<m:td align="left">
							<m:div styleClass="float1">
								<h:outputText id="PRGCMGTOT374" value="Audit History" styleClass="strong"/>
							</m:div>
						</m:td>
						<m:td align="right">
							<m:div styleClass="floatr">
								<t:commandLink id="PRGCMGTCL73" action="#{addressControllerBean.closeColumnChange}" 									value="Close" style="color:#fff;"/>
							</m:div>
						</m:td>
					</m:tr>
				</m:tbody>
			</m:table>
		</m:div>
		<m:div rendered="#{commonEntityDataBean.addressHistRndr}"> 
			<m:table id="PROVIDERMMT20120731164811178" width="100%">
					<m:tr>
						<m:td>
							<h:outputText id="PRGCMGTOT375"								value="#{cmnAddMsg['label.address.contactinfotable']}"								styleClass="strong" />
						</m:td>
					</m:tr>						
			</m:table>
		</m:div>

		<m:div styleClass=""
			rendered="#{commonEntityDataBean.addressHistRndr}">
			<t:dataTable cellspacing="0" styleClass="dataTable"				id="vvcolumnhistory_table" width="100%" align="center"				value="#{commonEntityDataBean.selectedAdrAuditLog.auditLogColumnValue}"				rowClasses="row,row_alt" var="colValue1">
				<h:column id="colValueColName">
					<f:facet name="header">
						<h:outputText id="hColValueColName" value="Column Name" />
					</f:facet>
					<h:outputText id="PRGCMGTOT376" value="#{colValue1.columnName}" />
				</h:column>
				<h:column id="colValueBefore">
					<f:facet name="header">
						<h:outputText id="hColValueBefore" value="Before" />
					</f:facet>
					<h:outputText id="PRGCMGTOT377" value="#{colValue1.beforeValue}" />
				</h:column>
				<h:column id="colValueAfter">
					<f:facet name="header">
						<h:outputText id="hColValueAfter" value="After" />
					</f:facet>
					<h:outputText id="PRGCMGTOT378" value="#{colValue1.afterValue}" />
				</h:column>
			</t:dataTable>
		</m:div>

	</m:div>
</m:section>
<%-- AUDIT END --%>
