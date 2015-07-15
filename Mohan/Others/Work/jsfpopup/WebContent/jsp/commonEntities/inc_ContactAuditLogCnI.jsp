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

<t:saveState id="CMGTTOMSS105"	value="#{commonEntityDataBean.commonEntityVO.commonContactVO.auditTimeStamp}"></t:saveState>
<t:saveState id="CMGTTOMSS106"	value="#{commonEntityDataBean.commonEntityVO.commonContactVO.auditUserID}"></t:saveState>
<t:saveState id="CMGTTOMSS107"	value="#{commonEntityDataBean.commonEntityVO.commonContactVO.addedAuditTimeStamp}"></t:saveState>
<t:saveState id="CMGTTOMSS108"	value="#{commonEntityDataBean.commonEntityVO.commonContactVO.addedAuditUserID}"></t:saveState>
<t:saveState id="CMGTTOMSS109" value="#{commonEntityDataBean.contactAuditHistList}"></t:saveState>
<t:saveState id="CMGTTOMSS110" value="#{commonEntityDataBean.contactHistRndr}"></t:saveState>
<f:loadBundle
	basename="com.acs.enterprise.common.program.commonentities.nl.PGM_CE_CommonContact"
	var="cmnContactMsg" />

<%-- AUDIT PART --%>
<m:section id="PROVIDERMMS20120731164811180" styleClass="expand">
	<m:legend>

	
			<h:outputLink				onclick="toggleTest('audit_plus',2);                     plusMinusForRefreshTest('audit_plus',this,'addlinfoCon_hidden');return false;"				id="plusMinus_AuditmoreDisp" styleClass="plus">
				<h:outputText id="audit"					value="#{cmnContactMsg['label.commonContact.audit']}" />
			</h:outputLink>
			<h:inputHidden id="addlinfoCon_hidden" value="plus"></h:inputHidden>
			<h:inputHidden id="audit_open"				value="#{commonEntityDataBean.auditCon}"></h:inputHidden>
		
	</m:legend>
	<m:div sid="audit_plus">


		<m:div id="log_detail" styleClass="">

			<t:dataTable cellspacing="0" styleClass="dataTable"				id="vvhistory_table1" width="100%" rows="10"				value="#{commonEntityDataBean.contactAuditHistList}"				var="sysParamDethistory2">

				<h:column id="PRGCMGTC8">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT423" value="Timestamp" />
					</f:facet>
					<t:commandLink id="PRGCMGTCL75"						action="#{commonContactControllerBean.showColumnChange}">
						<h:outputText id="PRGCMGTOT424" value="#{sysParamDethistory2.auditTimeStamp}">
							<f:convertDateTime type="both" pattern="MM/dd/yyyy hh:mm:ss a" />
						</h:outputText>
						<f:param name="auditLogSK" value="#{sysParamDethistory2.auditLogSK}" />
					</t:commandLink>
				</h:column>
				<h:column id="PRGCMGTC9">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT425" value="User ID" />
					</f:facet>
					<h:outputText id="PRGCMGTOT426" value="#{sysParamDethistory2.auditUserID}" />
				</h:column>
				<h:column id="PRGCMGTC10">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT427" value="Table Name" />
					</f:facet>
					<h:outputText id="PRGCMGTOT428" value="#{sysParamDethistory2.tableName}" />
				</h:column>
				<h:column id="PRGCMGTC11">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT429" value="Activity Type" />
					</f:facet>
					<h:outputText id="PRGCMGTOT430" value="#{sysParamDethistory2.dmlTypeCode}" />
				</h:column>
				<h:column id="PRGCMGTC12">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT431" value="Original Entry Date/Time" />
					</f:facet>
					<h:outputText id="PRGCMGTOT432" value="#{commonEntityDataBean.commonEntityVO.commonContactVO.addedAuditTimeStamp}">
						<f:convertDateTime type="both" pattern="MM/dd/yyyy hh:mm:ss a" />
					</h:outputText>
				</h:column>
				<h:column id="PRGCMGTC13">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT433" value="Original Entry User ID" />
					</f:facet>
					<h:outputText id="PRGCMGTOT434" value="#{commonEntityDataBean.commonEntityVO.commonContactVO.addedAuditUserID}" />
				</h:column>
				<h:column id="PRGCMGTC14">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT435" value="Transaction ID" />
					</f:facet>
					<h:outputText id="PRGCMGTOT436" value="#{sysParamDethistory2.cscnNumber}" />
				</h:column>
			</t:dataTable>
			<t:dataScroller id="CMGTTOMDS14" for="vvhistory_table1" paginator="true"				paginatorActiveColumnStyle='font-weight:bold;' paginatorMaxPages="3"    				immediate="false" pageCountVar="pageCount" pageIndexVar="pageIndex1"				firstRowIndexVar="firstRowIndex1" lastRowIndexVar="lastRowIndex1"				rowsCountVar="rowsCount1"				style="float:right;position:relative;bottom:-6px;text-decoration:underline;"> 
				<f:facet name="previous">
   					<t:commandLink styleClass="commandLink" id="previousLink"    						style="text-decoration:underline;" value=" << "   						action="#{commonContactControllerBean.previous}"   						rendered="#{commonEntityDataBean.showPrevious}">
   					</t:commandLink>     
				</f:facet>
				<f:facet name="next">    
					<t:commandLink styleClass="commandLink" id="nextlink" 						style="text-decoration:underline;" value=" >> "
   						action="#{commonContactControllerBean.next}"
   						rendered="#{commonEntityDataBean.showNext}">
   					</t:commandLink>     
			    </f:facet>   
				<h:outputText id="PRGCMGTOT437" rendered="#{rowsCount1 > 0}"
     				value="#{firstRowIndex1} - #{lastRowIndex1} of #{rowsCount1}"
     				style="float:left;position:relative;bottom:-6px;font-weight:bold;" />
			</t:dataScroller>
		</m:div>
		<m:br/><m:br/>
		<m:div styleClass="fd_Title" 
			rendered="#{commonEntityDataBean.contactHistRndr}" 
			style="height:18px;display:block;">
			<m:table id="PROVIDERMMT20120731164811181" width="100%">
				<m:tbody>
					<m:tr valign="center">
						<m:td align="left">
							<m:div styleClass="float1">
								<h:outputText id="PRGCMGTOT438" value="Audit History" styleClass="strong"/>
							</m:div>
						</m:td>
						<m:td align="right">
							<m:div styleClass="floatr">
								<t:commandLink id="PRGCMGTCL76" action="#{commonContactControllerBean.closeColumnChange}" 									value="Close" style="color:#fff;"/>
							</m:div>
						</m:td>
					</m:tr>
				</m:tbody>
			</m:table>
		</m:div>
		<m:div rendered="#{commonEntityDataBean.contactHistRndr}"> 
			<m:table id="PROVIDERMMT20120731164811182" width="100%">
					<m:tr>
						<m:td>
							<h:outputText id="PRGCMGTOT439"								value="#{cmnContactMsg['label.commonContact.contactinfotable']}"								styleClass="strong" />
						</m:td>
					</m:tr>						
			</m:table>
		</m:div>
		<m:div styleClass=""
			rendered="#{commonEntityDataBean.contactHistRndr}">
			<t:dataTable cellspacing="0" styleClass="dataTable"				id="vvcolumnhistory_table" width="100%" align="center"				value="#{commonEntityDataBean.selectedCntAuditLog.auditLogColumnValue}"				rowClasses="row,row_alt" var="colValue2">
				<h:column id="colValueColName">
					<f:facet name="header">
						<h:outputText id="hColValueColName" value="Column Name" />
					</f:facet>
					<h:outputText id="PRGCMGTOT440" value="#{colValue2.columnName}" />
				</h:column>
				<h:column id="colValueBefore">
					<f:facet name="header">
						<h:outputText id="hColValueBefore" value="Before" />
					</f:facet>
					<h:outputText id="PRGCMGTOT441" value="#{colValue2.beforeValue}" />
				</h:column>
				<h:column id="colValueAfter">
					<f:facet name="header">
						<h:outputText id="hColValueAfter" value="After" />
					</f:facet>
					<h:outputText id="PRGCMGTOT442" value="#{colValue2.afterValue}" />
				</h:column>
			</t:dataTable>
		</m:div>

	</m:div>
</m:section>
<%-- AUDIT END --%>

