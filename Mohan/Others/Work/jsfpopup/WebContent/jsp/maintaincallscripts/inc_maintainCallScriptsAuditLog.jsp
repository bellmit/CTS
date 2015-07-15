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

<t:saveState id="CMGTTOMSS287" value="#{CallScriptDataBean.callScriptAuditRender}"></t:saveState>
<t:saveState id="CMGTTOMSS288" value="#{CallScriptDataBean.callScriptAuditHistoryList}"></t:saveState>
<t:saveState id="CMGTTOMSS289" value="#{CallScriptDataBean.callScriptVO.auditTimeStamp}"></t:saveState>
<t:saveState id="CMGTTOMSS290" value="#{CallScriptDataBean.callScriptVO.auditUserID}"></t:saveState>
<t:saveState id="CMGTTOMSS291" value="#{CallScriptDataBean.callScriptVO.addedAuditTimeStamp}"></t:saveState>
<t:saveState id="CMGTTOMSS292" value="#{CallScriptDataBean.callScriptVO.addedAuditUserID}"></t:saveState>
	
	<!-- AUDIT START -->
 
    <m:section id="PROVIDERMMS20120731164811418" styleClass="expand">
    
    
   <m:legend>
		
			<h:outputLink				onclick="toggleTest('audit_plus',2);                     plusMinusForRefreshTest('audit_plus',this,'addlinfo_hidden');return false;"				id="plusMinus_Auditmore" styleClass="plus">
				<h:outputText id="audit" value="#{msg['label.ref.audit']}" />
			</h:outputLink>
			<h:inputHidden id="addlinfo_hidden" value="plus"></h:inputHidden>
			<h:inputHidden id="audit_open"				value="#{CallScriptDataBean.auditOpen}"></h:inputHidden>

	

	</m:legend>
    


	<m:div sid="audit_plus">
							
							
								<!-- Audit log details -->
								<m:div id="log_detail" styleClass="">
								
											<t:dataTable cellspacing="0" styleClass="dataTable"												id="vvhistory_table" width="100%" rows="10"												value="#{CallScriptDataBean.callScriptAuditHistoryList}"												var="auditHistory">
								
												<h:column id="PRGCMGTC36">
													<f:facet name="header">
														<h:outputText id="PRGCMGTOT996" value="#{msgAudit['label.timestamp']}" />
													</f:facet>
													<t:commandLink id="PRGCMGTCL150"														action="#{CallScriptControllerBean.showColumnChange}">
														<h:outputText id="PRGCMGTOT997" value="#{auditHistory.auditTimeStamp}" >
															<f:convertDateTime type="both" pattern="MM/dd/yyyy hh:mm:ss a" />
														</h:outputText>
														<f:param name="auditLogSK" value="#{auditHistory.auditLogSK}" />
													</t:commandLink>
												</h:column>
												<h:column id="PRGCMGTC37">
													<f:facet name="header">
														<h:outputText id="PRGCMGTOT998" value="#{msgAudit['label.userId']}" />
													</f:facet>
													<h:outputText id="PRGCMGTOT999" value="#{auditHistory.auditUserID}" />
												</h:column>
												<h:column id="PRGCMGTC38">
													<f:facet name="header">
														<h:outputText id="PRGCMGTOT1000" value="#{msgAudit['label.tableName']}" />
													</f:facet>
													<h:outputText id="PRGCMGTOT1001" value="#{auditHistory.tableName}" />
												</h:column>
												<h:column id="PRGCMGTC39">
													<f:facet name="header">
														<h:outputText id="PRGCMGTOT1002" value="#{msgAudit['label.activityType']}" />
													</f:facet>
													<h:outputText id="PRGCMGTOT1003" value="#{auditHistory.dmlTypeCode}" />
												</h:column>
												<h:column id="PRGCMGTC40">
													<f:facet name="header">
														<h:outputText id="PRGCMGTOT1004" value="#{msgAudit['label.origEntryDate']}" />
													</f:facet>
													<h:outputText id="PRGCMGTOT1005" value="#{auditHistory.auditTimeStamp}" >
														<f:convertDateTime type="both" pattern="MM/dd/yyyy hh:mm:ss a" />
													</h:outputText>
												</h:column>
												<h:column id="PRGCMGTC41">
													<f:facet name="header">
														<h:outputText id="PRGCMGTOT1006" value="#{msgAudit['label.origEntryUser']}" />
													</f:facet>
													<h:outputText id="PRGCMGTOT1007" value="#{auditHistory.auditUserID}" />
												</h:column>
												<h:column id="PRGCMGTC42">
													<f:facet name="header">
														<h:outputText id="PRGCMGTOT1008" value="#{msgAudit['label.tranId']}" />
													</f:facet>
													<h:outputText id="PRGCMGTOT1009" value="#{auditHistorycscnNumber}" />
												</h:column>
											</t:dataTable>
											
											
														
											<t:dataScroller id="CMGTTOMDS37" for="vvhistory_table" paginator="true"												paginatorActiveColumnStyle='font-weight:bold;' paginatorMaxPages="3"    												immediate="false" pageCountVar="pageCount" pageIndexVar="pageIndex"												firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"												rowsCountVar="rowsCount"												style="float:right;position:relative;bottom:-6px;text-decoration:underline;"> 
												<f:facet name="previous">
								   					<t:commandLink styleClass="commandLink" id="previousLink" 								   						style="text-decoration:underline;" value=" << "								   						action="#{CallScriptControllerBean.previous}"								   						rendered="#{CallScriptDataBean.showPrevious}">
								   								</t:commandLink>     
															</f:facet>
															<f:facet name="next">    
																<t:commandLink styleClass="commandLink" id="nextlink" 																	style="text-decoration:underline;" value=" >> "
								   									action="#{CallScriptControllerBean.next}"
								   									rendered="#{CallScriptDataBean.showNext}">
								   								</t:commandLink>     
														    </f:facet>   
												   <h:outputText id="PRGCMGTOT1010" rendered="#{rowsCount > 0}"
								     			value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
								     			style="float:left;position:relative;bottom:-6px;font-weight:bold;" />
											</t:dataScroller>
											
									</m:div>
									
									<m:br/><m:br/>
									<m:div styleClass="fd_Title" 
										rendered="#{CallScriptDataBean.callScriptAuditRender}" 
										style="height:18px;display:block;">
										<m:table id="PROVIDERMMT20120731164811419" width="100%">
											<m:tbody>
												<m:tr valign="center">
													<m:td align="left">
														<m:div styleClass="float1">
															<h:outputText id="PRGCMGTOT1011" value="#{msgAudit['label.auditHistory']}" styleClass="strong"/>
														</m:div>
													</m:td>
													<m:td align="right">
														<m:div styleClass="floatr">
															<t:commandLink id="PRGCMGTCL151" action="#{CallScriptControllerBean.closeColumnChange}" 																value="#{msgAudit['label.close']}" style="color:#fff;"/>
														</m:div>
													</m:td>
												</m:tr>
											</m:tbody>
										</m:table>
									</m:div>
									
									<m:div rendered="#{CallScriptDataBean.callScriptAuditRender}"> 
										<m:table id="PROVIDERMMT20120731164811420" width="100%">
											<m:tr>
												<m:td>
													<h:outputText id="PRGCMGTOT1012" value="#{msg['label.ref.callscriptInfoTable']}"														styleClass="strong" />
												</m:td>
											</m:tr> 
										</m:table>
									</m:div>
						
									<m:div styleClass=""
										rendered="#{CallScriptDataBean.callScriptAuditRender}">
										<t:dataTable cellspacing="0" styleClass="dataTable"											id="vvcolumnhistory_table" width="100%" align="center"											value="#{CallScriptDataBean.selectedAuditLog.auditLogColumnValue}"											rowClasses="row,row_alt" var="colValue">
											<h:column id="colValueColName">
												<f:facet name="header">
													<h:outputText id="hColValueColName" value="#{msgAudit['label.colName']}" />
												</f:facet>
												<h:outputText id="PRGCMGTOT1013" value="#{colValue.columnName}" />
											</h:column>
											<h:column id="colValueBefore">
												<f:facet name="header">
													<h:outputText id="hColValueBefore" value="#{msgAudit['label.before']}" />
												</f:facet>
												<h:outputText id="PRGCMGTOT1014" value="#{colValue.beforeValue}" />
											</h:column>
											<h:column id="colValueAfter">
												<f:facet name="header">
													<h:outputText id="hColValueAfter" value="#{msgAudit['label.after']}" />
												</f:facet>
												<h:outputText id="PRGCMGTOT1015" value="#{colValue.afterValue}" />
											</h:column>
										</t:dataTable>
									</m:div>
						
						
						
						 		
					   </m:div>
					 </m:section>															
	                  <!-- AUDIT END -->




	
