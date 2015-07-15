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

<t:saveState id="CMGTTOMSS123"	value="#{commonEntityDataBean.commonEntityVO.phoneVO.auditTimeStamp}"></t:saveState>
<t:saveState id="CMGTTOMSS124"	value="#{commonEntityDataBean.commonEntityVO.phoneVO.auditUserID}"></t:saveState>
<t:saveState id="CMGTTOMSS125"	value="#{commonEntityDataBean.commonEntityVO.phoneVO.addedAuditTimeStamp}"></t:saveState>
<t:saveState id="CMGTTOMSS126"	value="#{commonEntityDataBean.commonEntityVO.phoneVO.addedAuditUserID}"></t:saveState>
<t:saveState id="CMGTTOMSS127" value="#{commonEntityDataBean.phoneAuditHistList}"></t:saveState>
<t:saveState id="CMGTTOMSS128" value="#{commonEntityDataBean.phoneHistRndr}"></t:saveState>

<f:loadBundle
	basename="com.acs.enterprise.common.program.commonentities.nl.PGM_CE_Phone"
	var="cmnPhoneMsg" />

<%-- AUDIT PART --%>
<m:section id="PROVIDERMMS20120731164811188" styleClass="expand">
	<m:legend>

		<h:outputLink			onclick="toggleTest('audit_plus2',2);                     plusMinusForRefreshTest('audit_plus2',this,'addlinfoPhone_hidden2');return false;"			id="plusMinus_AuditmoreDisp" styleClass="plus">
			<h:outputText id="audit" value="#{cmnPhoneMsg['label.phone.audit']}" />

		</h:outputLink>
		<h:inputHidden id="addlinfoPhone_hidden2" value="plus"></h:inputHidden>
		<h:inputHidden id="audit_open"			value="#{commonEntityDataBean.auditPhone}"></h:inputHidden>

	</m:legend>

	<m:div sid="audit_plus2">


		<m:div id="log_detail" styleClass="">

			<t:dataTable cellspacing="0" styleClass="dataTable"				id="vvhistory_table4" width="100%" rows="10"				value="#{commonEntityDataBean.phoneAuditHistList}"				var="sysParamDethistory4">

				<h:column id="PRGCMGTC22">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT475" value="Timestamp" />
					</f:facet>
					<t:commandLink id="PRGCMGTCL80"						action="#{phoneControllerBean.showColumnChange}">
						<h:outputText id="PRGCMGTOT476" value="#{sysParamDethistory4.auditTimeStamp}">
							<f:convertDateTime type="both" pattern="MM/dd/yyyy hh:mm:ss a" />
						</h:outputText>
						<f:param name="auditLogSK" value="#{sysParamDethistory4.auditLogSK}" />
					</t:commandLink>
				</h:column>
				<h:column id="PRGCMGTC23">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT477" value="User ID" />
					</f:facet>
					<h:outputText id="PRGCMGTOT478" value="#{sysParamDethistory4.auditUserID}" />
				</h:column>
				<h:column id="PRGCMGTC24">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT479" value="Table Name" />
					</f:facet>
					<h:outputText id="PRGCMGTOT480" value="#{sysParamDethistory4.tableName}" />
				</h:column>
				<h:column id="PRGCMGTC25">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT481" value="Activity Type" />
					</f:facet>
					<h:outputText id="PRGCMGTOT482" value="#{sysParamDethistory4.dmlTypeCode}" />
				</h:column>
				<h:column id="PRGCMGTC26">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT483" value="Original Entry Date/Time" />
					</f:facet>
					<h:outputText id="PRGCMGTOT484" value="#{commonEntityDataBean.commonEntityVO.phoneVO.addedAuditTimeStamp}">
						<f:convertDateTime type="both" pattern="MM/dd/yyyy hh:mm:ss a" />
					</h:outputText>
				</h:column>
				<h:column id="PRGCMGTC27">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT485" value="Original Entry User ID" />
					</f:facet>
					<h:outputText id="PRGCMGTOT486" value="#{commonEntityDataBean.commonEntityVO.phoneVO.addedAuditUserID}" />
				</h:column>
				<h:column id="PRGCMGTC28">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT487" value="Transaction ID" />
					</f:facet>
					<h:outputText id="PRGCMGTOT488" value="#{sysParamDethistory4.cscnNumber}" />
				</h:column>
			</t:dataTable>
			<t:dataScroller id="CMGTTOMDS16" for="vvhistory_table4" paginator="true"				paginatorActiveColumnStyle='font-weight:bold;' paginatorMaxPages="3"    				immediate="false" pageCountVar="pageCount" pageIndexVar="pageIndex4"				firstRowIndexVar="firstRowIndex4" lastRowIndexVar="lastRowIndex4"				rowsCountVar="rowsCount4"				style="float:right;position:relative;bottom:-6px;text-decoration:underline;"> 
				<f:facet name="previous">
   					<t:commandLink styleClass="commandLink" id="previousLink"    						style="text-decoration:underline;" value=" << "   						action="#{addressControllerBean.previous}"   						rendered="#{commonEntityDataBean.showPrevious}">
   					</t:commandLink>     
				</f:facet>
				<f:facet name="next">    
					<t:commandLink styleClass="commandLink" id="nextlink" 						style="text-decoration:underline;" value=" >> "
   						action="#{addressControllerBean.next}"
   						rendered="#{commonEntityDataBean.showNext}">
   					</t:commandLink>     
			    </f:facet>   
				<h:outputText id="PRGCMGTOT489" rendered="#{rowsCount4 > 0}"
     				value="#{firstRowIndex4} - #{lastRowIndex4} of #{rowsCount4}"
     				style="float:left;position:relative;bottom:-6px;font-weight:bold;" />
			</t:dataScroller>

		</m:div>
		<m:br/><m:br/>
		<m:div styleClass="fd_Title" 
			rendered="#{commonEntityDataBean.phoneHistRndr}" 
			style="height:18px;display:block;">
			<m:table id="PROVIDERMMT20120731164811189" width="100%">
				<m:tbody>
					<m:tr valign="center">
						<m:td align="left">
							<m:div styleClass="float1">
								<h:outputText id="PRGCMGTOT490" value="Audit History" styleClass="strong"/>
							</m:div>
						</m:td>
						<m:td align="right">
							<m:div styleClass="floatr">
								<t:commandLink id="PRGCMGTCL81" action="#{phoneControllerBean.closeColumnChange}" 									value="Close" style="color:#fff;"/>
							</m:div>
						</m:td>
					</m:tr>
				</m:tbody>
			</m:table>
		</m:div>
		<m:div rendered="#{commonEntityDataBean.phoneHistRndr}"> 
			<m:table id="PROVIDERMMT20120731164811190" width="100%">
					<m:tr>
						<m:td>
							<h:outputText id="PRGCMGTOT491"								value="#{cmnPhoneMsg['label.phone.addressinfotable']}"								styleClass="strong" />
						</m:td>
					</m:tr>						
			</m:table>
		</m:div>

		<m:div styleClass=""
			rendered="#{commonEntityDataBean.phoneHistRndr}">
			<t:dataTable cellspacing="0" styleClass="dataTable"				id="vvcolumnhistory_table" width="100%" align="center"				value="#{commonEntityDataBean.selectedPhnAuditLog.auditLogColumnValue}"				rowClasses="row,row_alt" var="colValue4">
				<h:column id="colValueColName">
					<f:facet name="header">
						<h:outputText id="hColValueColName" value="Column Name" />
					</f:facet>
					<h:outputText id="PRGCMGTOT492" value="#{colValue4.columnName}" />
				</h:column>
				<h:column id="colValueBefore">
					<f:facet name="header">
						<h:outputText id="hColValueBefore" value="Before" />
					</f:facet>
					<h:outputText id="PRGCMGTOT493" value="#{colValue4.beforeValue}" />
				</h:column>
				<h:column id="colValueAfter">
					<f:facet name="header">
						<h:outputText id="hColValueAfter" value="After" />
					</f:facet>
					<h:outputText id="PRGCMGTOT494" value="#{colValue4.afterValue}" />
				</h:column>
			</t:dataTable>
		</m:div>

	</m:div>
</m:section>
<%-- AUDIT END --%>

