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

<t:saveState id="CMGTTOMSS117"	value="#{commonEntityDataBean.commonEntityVO.eaddressVO.auditTimeStamp}"></t:saveState>
<t:saveState id="CMGTTOMSS118"	value="#{commonEntityDataBean.commonEntityVO.eaddressVO.auditUserID}"></t:saveState>
<t:saveState id="CMGTTOMSS119"	value="#{commonEntityDataBean.commonEntityVO.eaddressVO.addedAuditTimeStamp}"></t:saveState>
<t:saveState id="CMGTTOMSS120"	value="#{commonEntityDataBean.commonEntityVO.eaddressVO.addedAuditUserID}"></t:saveState>
<t:saveState id="CMGTTOMSS121" value="#{commonEntityDataBean.auditEAddressHistRndr}"></t:saveState>
<t:saveState id="CMGTTOMSS122" value="#{commonEntityDataBean.auditEAddressAuditHistList}"></t:saveState>
<f:loadBundle
	basename="com.acs.enterprise.common.program.commonentities.nl.PGM_CE_CommonEAddress"
	var="cmnEAddMsg" />

<%-- AUDIT PART --%>
<m:section id="PROVIDERMMS20120731164811185" styleClass="expand">
	<m:legend>

		<h:outputLink			onclick="toggleTest('audit_plus1',2);                     plusMinusForRefreshTest('audit_plus1',this,'addlinfoEAdd_hidden1');return false;"			id="plusMinus_AuditmoreDisp" styleClass="plus">
			<h:outputText id="audit"				value="#{cmnEAddMsg['label.eAddress.audit']}" />
		</h:outputLink>
		<h:inputHidden id="addlinfoEAdd_hidden1" value="plus"></h:inputHidden>
		<h:inputHidden id="audit_open"			value="#{commonEntityDataBean.auditEAddress}"></h:inputHidden>

	</m:legend>
	<m:div sid="audit_plus1">


		<m:div id="log_detail" styleClass="">

			<t:dataTable cellspacing="0" styleClass="dataTable"				id="vvhistory_table2" width="100%" rows="10"				value="#{commonEntityDataBean.auditEAddressAuditHistList}"				var="sysParamDethistory3">

				<h:column id="PRGCMGTC15">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT455" value="Timestamp" />
					</f:facet>
					<t:commandLink id="PRGCMGTCL78"						action="#{eAddressControllerBean.showColumnChange}">
						<h:outputText id="PRGCMGTOT456" value="#{sysParamDethistory3.auditTimeStamp}">
							<f:convertDateTime type="both" pattern="MM/dd/yyyy hh:mm:ss a" />
						</h:outputText>
						<f:param name="auditLogSK" value="#{sysParamDethistory3.auditLogSK}" />
					</t:commandLink>
				</h:column>
				<h:column id="PRGCMGTC16">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT457" value="User ID" />
					</f:facet>
					<h:outputText id="PRGCMGTOT458" value="#{sysParamDethistory3.auditUserID}" />
				</h:column>
				<h:column id="PRGCMGTC17">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT459" value="Table Name" />
					</f:facet>
					<h:outputText id="PRGCMGTOT460" value="#{sysParamDethistory3.tableName}" />
				</h:column>
				<h:column id="PRGCMGTC18">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT461" value="Activity Type" />
					</f:facet>
					<h:outputText id="PRGCMGTOT462" value="#{sysParamDethistory3.dmlTypeCode}" />
				</h:column>
				<h:column id="PRGCMGTC19">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT463" value="Original Entry Date/Time" />
					</f:facet>
					<h:outputText id="PRGCMGTOT464" value="#{commonEntityDataBean.commonEntityVO.eaddressVO.addedAuditTimeStamp}">
						<f:convertDateTime type="both" pattern="MM/dd/yyyy hh:mm:ss a" />
					</h:outputText>
				</h:column>
				<h:column id="PRGCMGTC20">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT465" value="Original Entry User ID" />
					</f:facet>
					<h:outputText id="PRGCMGTOT466" value="#{commonEntityDataBean.commonEntityVO.eaddressVO.addedAuditUserID}" />
				</h:column>
				<h:column id="PRGCMGTC21">
					<f:facet name="header">
						<h:outputText id="PRGCMGTOT467" value="Transaction ID" />
					</f:facet>
					<h:outputText id="PRGCMGTOT468" value="#{sysParamDethistory3.cscnNumber}" />
				</h:column>
			</t:dataTable>
			<t:dataScroller id="CMGTTOMDS15" for="vvhistory_table2" paginator="true"				paginatorActiveColumnStyle='font-weight:bold;' paginatorMaxPages="3"    				immediate="false" pageCountVar="pageCount" pageIndexVar="pageIndex2"				firstRowIndexVar="firstRowIndex2" lastRowIndexVar="lastRowIndex2"				rowsCountVar="rowsCount2"				style="float:right;position:relative;bottom:-6px;text-decoration:underline;"> 
				<f:facet name="previous">
   					<t:commandLink styleClass="commandLink" id="previousLink"    						style="text-decoration:underline;" value=" << "   						action="#{eAddressControllerBean.previous}"   						rendered="#{commonEntityDataBean.showPrevious}">
   					</t:commandLink>     
				</f:facet>
				<f:facet name="next">    
					<t:commandLink styleClass="commandLink" id="nextlink" 						style="text-decoration:underline;" value=" >> "
   						action="#{eAddressControllerBean.next}"
   						rendered="#{commonEntityDataBean.showNext}">
   					</t:commandLink>     
			    </f:facet>   
				<h:outputText id="PRGCMGTOT469" rendered="#{rowsCount2 > 0}"
     				value="#{firstRowIndex2} - #{lastRowIndex2} of #{rowsCount2}"
     				style="float:left;position:relative;bottom:-6px;font-weight:bold;" />
			</t:dataScroller>

		</m:div>
		<m:br/><m:br/>
		<m:div styleClass="fd_Title" 
			rendered="#{commonEntityDataBean.auditEAddressHistRndr}" 
			style="height:18px;display:block;">
			<m:table id="PROVIDERMMT20120731164811186" width="100%">
				<m:tbody>
					<m:tr valign="center">
						<m:td align="left">
							<m:div styleClass="float1">
								<h:outputText id="PRGCMGTOT470" value="Audit History" styleClass="strong"/>
							</m:div>
						</m:td>
						<m:td align="right">
							<m:div styleClass="floatr">
								<t:commandLink id="PRGCMGTCL79" action="#{eAddressControllerBean.closeColumnChange}" 									value="Close" style="color:#fff;"/>
							</m:div>
						</m:td>
					</m:tr>
				</m:tbody>
			</m:table>
		</m:div>
		<m:div rendered="#{commonEntityDataBean.auditEAddressHistRndr}"> 
			<m:table id="PROVIDERMMT20120731164811187" width="100%">
					<m:tr>
						<m:td>
							<h:outputText id="PRGCMGTOT471"								value="#{cmnEAddMsg['label.eAddress.addressinfotable']}"								styleClass="strong" />
						</m:td>
					</m:tr>						
			</m:table>
		</m:div>
		<m:div styleClass=""
			rendered="#{commonEntityDataBean.auditEAddressHistRndr}">
			<t:dataTable cellspacing="0" styleClass="dataTable"				id="vvcolumnhistory_table" width="100%" align="center"				value="#{commonEntityDataBean.selectedEAdrAuditLog.auditLogColumnValue}"				rowClasses="row,row_alt" var="colValue3">
				<h:column id="colValueColName">
					<f:facet name="header">
						<h:outputText id="hColValueColName" value="Column Name" />
					</f:facet>
					<h:outputText id="PRGCMGTOT472" value="#{colValue3.columnName}" />
				</h:column>
				<h:column id="colValueBefore">
					<f:facet name="header">
						<h:outputText id="hColValueBefore" value="Before" />
					</f:facet>
					<h:outputText id="PRGCMGTOT473" value="#{colValue3.beforeValue}" />
				</h:column>
				<h:column id="colValueAfter">
					<f:facet name="header">
						<h:outputText id="hColValueAfter" value="After" />
					</f:facet>
					<h:outputText id="PRGCMGTOT474" value="#{colValue3.afterValue}" />
				</h:column>
			</t:dataTable>
		</m:div>

	</m:div>
</m:section>
<%-- AUDIT END --%>

