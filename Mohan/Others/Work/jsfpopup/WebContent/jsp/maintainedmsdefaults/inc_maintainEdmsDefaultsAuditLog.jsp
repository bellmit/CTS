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
<t:saveState id="CMGTTOMSS395" value="#{EDMSDefaultsDataBean}"></t:saveState>


<t:saveState id="CMGTTOMSS396" value="#{EDMSDefaultsDataBean.edmsDefaultsAuditRender}"></t:saveState>
<t:saveState id="CMGTTOMSS397" value="#{EDMSDefaultsDataBean.edmsDefaultsAuditHistoryList}"></t:saveState>

<t:saveState id="CMGTTOMSS398" value="#{EDMSDefaultsDataBean.edmsDefaultsVO.auditTimeStamp}"></t:saveState>
<t:saveState id="CMGTTOMSS399" value="#{EDMSDefaultsDataBean.edmsDefaultsVO.auditUserID}"></t:saveState>
<t:saveState id="CMGTTOMSS400" value="#{EDMSDefaultsDataBean.edmsDefaultsVO.addedAuditTimeStamp}"></t:saveState>
<t:saveState id="CMGTTOMSS401" value="#{EDMSDefaultsDataBean.edmsDefaultsVO.addedAuditUserID}"></t:saveState>


<%-- AUDIT PART --%>  



<m:section id="PROVIDERMMS20120731164811554" styleClass="expand">
	<m:legend>
		<f:verbatim>
			<h:outputLink				onclick="toggleTest('audit_plus',2);                     plusMinusForRefreshTest('audit_plus',this,'addlinfo_hidden');return false;"				id="plusMinus_Auditmore" styleClass="plus">
				<h:outputText id="audit" value="#{edmsmtn['label.edmsdefaults.audit']}" />
			</h:outputLink>
			<h:inputHidden id="addlinfo_hidden" value="plus" ></h:inputHidden>
			<h:inputHidden id="audit_open"				value="#{EDMSDefaultsDataBean.auditOpen}"></h:inputHidden>

		</f:verbatim>

	</m:legend>


	<m:div sid="audit_plus">

								<!-- Audit log details -->
								<m:div id="log_detail" styleClass="">
								
											<t:dataTable cellspacing="0" styleClass="dataTable"												id="vvhistory_table" width="100%" rows="10"												value="#{EDMSDefaultsDataBean.edmsDefaultsAuditHistoryList}"												var="edmsDefaults">
								
												<h:column id="PRGCMGTC86">
													<f:facet name="header">
														<h:outputText id="PRGCMGTOT1357" value="#{msgAudit['label.timestamp']}" />
													</f:facet>
													<t:commandLink id="PRGCMGTCL197"														action="#{EDMSDefaultsControllerBean.showColumnChange}">
														<h:outputText id="PRGCMGTOT1358" value="#{edmsDefaults.auditTimeStamp}" />
														<f:param name="auditLogSK" value="#{edmsDefaults.auditLogSK}" />
													</t:commandLink>
												</h:column>
												<h:column id="PRGCMGTC87">
													<f:facet name="header">
														<h:outputText id="PRGCMGTOT1359" value="#{msgAudit['label.userId']}" />
													</f:facet>
													<h:outputText id="PRGCMGTOT1360" value="#{edmsDefaults.auditUserID}" />
												</h:column>
												<h:column id="PRGCMGTC88">
													<f:facet name="header">
														<h:outputText id="PRGCMGTOT1361" value="#{msgAudit['label.tableName']}" />
													</f:facet>
													<h:outputText id="PRGCMGTOT1362" value="#{edmsDefaults.tableName}" />
												</h:column>
												<h:column id="PRGCMGTC89">
													<f:facet name="header">
														<h:outputText id="PRGCMGTOT1363" value="#{msgAudit['label.activityType']}" />
													</f:facet>
													<h:outputText id="PRGCMGTOT1364" value="#{edmsDefaults.dmlTypeCode}" />
												</h:column>
												<h:column id="PRGCMGTC90">
													<f:facet name="header">
														<h:outputText id="PRGCMGTOT1365" value="#{msgAudit['label.origEntryDate']}" />
													</f:facet>
													<h:outputText id="PRGCMGTOT1366" value="#{EDMSDefaultsDataBean.edmsDefaultsVO.addedAuditTimeStamp}" />
												</h:column>
												<h:column id="PRGCMGTC91">
													<f:facet name="header">
														<h:outputText id="PRGCMGTOT1367" value="#{msgAudit['label.origEntryUser']}" />
													</f:facet>
													<h:outputText id="PRGCMGTOT1368" value="#{EDMSDefaultsDataBean.edmsDefaultsVO.addedAuditUserID}" />
												</h:column>
												<h:column id="PRGCMGTC92">
													<f:facet name="header">
														<h:outputText id="PRGCMGTOT1369" value="#{msgAudit['label.tranId']}"/>
													</f:facet>
													<h:outputText id="PRGCMGTOT1370" value="#{edmsDefaults.cscnNumber}" />
												</h:column>
											</t:dataTable>
											
											
											 		
											<t:dataScroller id="CMGTTOMDS48" for="vvhistory_table" paginator="true"												paginatorActiveColumnStyle='font-weight:bold;' paginatorMaxPages="3"    												immediate="false" pageCountVar="pageCount" pageIndexVar="pageIndex"												firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"												rowsCountVar="rowsCount"												style="float:right;position:relative;bottom:-6px;text-decoration:underline;"> 
												<f:facet name="previous">
								   					<t:commandLink styleClass="commandLink" id="previousLink" 								   						style="text-decoration:underline;" value=" << "								   						action="#{EDMSDefaultsControllerBean.previous}"								   						rendered="#{EDMSDefaultsDataBean.showPrevious}">
								   								</t:commandLink>     
															</f:facet>
															<f:facet name="next">    
																<t:commandLink styleClass="commandLink" id="nextlink" 																	style="text-decoration:underline;" value=" >> "
								   									action="#{EDMSDefaultsControllerBean.next}"
								   									rendered="#{EDMSDefaultsDataBean.showNext}">
								   								</t:commandLink>     
														    </f:facet>   
												   <h:outputText id="PRGCMGTOT1371" rendered="#{rowsCount > 0}"
								     			value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
								     			style="float:left;position:relative;bottom:-6px;font-weight:bold;" />
											</t:dataScroller> 
											
											
									</m:div>
									
									
									<m:br/><m:br/>
									<m:div styleClass="fd_Title" 
										rendered="#{EDMSDefaultsDataBean.auditColumnHistoryRender}" 
										style="height:18px;display:block;">
										<m:table id="PROVIDERMMT20120731164811555" width="100%">
											<m:tbody>
												<m:tr valign="center">
													<m:td align="left">
														<m:div styleClass="float1">
															<h:outputText id="PRGCMGTOT1372" value="#{msgAudit['label.auditHistory']}" styleClass="strong"/>
														</m:div>
													</m:td>
													<m:td align="right">
														<m:div styleClass="floatr">
															<t:commandLink id="PRGCMGTCL198" action="#{EDMSDefaultsControllerBean.closeColumnChange}" 																value="#{msgAudit['label.close']}" style="color:#fff;"/>
														</m:div>
													</m:td>
												</m:tr>
											</m:tbody>
										</m:table>
									</m:div>
									
									<m:div rendered="#{EDMSDefaultsDataBean.auditColumnHistoryRender}"> 
										<m:table id="PROVIDERMMT20120731164811556" width="100%">
											<m:tr>
												<m:td>
													<h:outputText id="PRGCMGTOT1373" value="#{edmsmtn['label.edmsdefaults.edmsdefaultsInfoTable']}"														styleClass="strong" />
												</m:td>
											</m:tr> 
										</m:table>
									</m:div>
									
									<m:div styleClass=""
										rendered="#{EDMSDefaultsDataBean.auditColumnHistoryRender}">
										<t:dataTable cellspacing="0" styleClass="dataTable"											id="vvcolumnhistory_table" width="100%" align="center"											value="#{EDMSDefaultsDataBean.selectedAuditLog.auditLogColumnValue}"											rowClasses="row,row_alt" var="colValue">
											<h:column id="colValueColName">
												<f:facet name="header">
													<h:outputText id="hColValueColName" value="#{msgAudit['label.colName']}" />
												</f:facet>
												<h:outputText id="PRGCMGTOT1374" value="#{colValue.columnName}" />
											</h:column>
											<h:column id="colValueBefore">
												<f:facet name="header">
													<h:outputText id="hColValueBefore" value="#{msgAudit['label.before']}" />
												</f:facet>
												<h:outputText id="PRGCMGTOT1375" value="#{colValue.beforeValue}" />
											</h:column>
											<h:column id="colValueAfter">
												<f:facet name="header">
													<h:outputText id="hColValueAfter" value="#{msgAudit['label.after']}" />
												</f:facet>
												<h:outputText id="PRGCMGTOT1376" value="#{colValue.afterValue}" />
											</h:column>
										</t:dataTable>
									</m:div>
						
						
						
						 		
					   </m:div>
					 </m:section>															
	                  <%-- AUDIT END --%>

