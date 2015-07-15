<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>


<f:loadBundle var="srCrspd"
	basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_ContactCorrespondenceSearch" />
<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.ProgramMessages"
	var="ref" />
<m:div styleClass="floatContainer">

	<m:pod title="Search For Correspondence" styleClass="otherbg">

		<m:table id="PROVIDERMMT20120731164811409" width="100%" cellspacing="5">
			<m:tr>
				<m:td>

					<m:section id="PROVIDERMMS20120731164811410">
						<m:legend styleClass="otherbg">
							<h:outputText id="PRGCMGTOT920"
								value="#{srCrspd['label.crspd.crspdfor']}"></h:outputText>
						</m:legend>

						<m:table id="PROVIDERMMT20120731164811411">
							<m:tr>
								<m:td>
								<h:outputLabel id="CTMGT508L" for="entityType">
									<h:outputText id="PRGCMGTOT921"
										value="#{srCrspd['label.crspd.entityType']}"></h:outputText>
								</h:outputLabel>
									<m:br />
									<h:selectOneMenu id="entityType"
										value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.entityType}"
										valueChangeListener="#{cs_searchCorrespondenceControllerBean.onEntityTypeChange}"
										onchange="flagWarn=false; " immediate="true">
										<f:selectItem itemValue="" itemLabel="" />
										<f:selectItems
											value="#{cs_searchCorrespondenceDataBean.entityTypeVVList}" />
									</h:selectOneMenu>
									<hx:behavior event="onchange" behaviorAction="get"
										targetAction="group1" target="entityType"></hx:behavior>
								</m:td>
							</m:tr>

							<m:tr>
								<m:td>
									<h:panelGroup id="group1">
									<h:outputLabel id="CTMGT508L11" for="entityIDType">
										<h:outputText id="PRGCMGTOT922"
											value="#{srCrspd['label.crspd.entityIDType']}"></h:outputText>
											</h:outputLabel>
										<m:br />
										<h:selectOneMenu id="entityIDType"
											value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.entityIDType}">
											<f:selectItem itemValue="" itemLabel="" />
											<f:selectItems
												value="#{cs_searchCorrespondenceDataBean.entityIDTypeList}" />
										</h:selectOneMenu>
									</h:panelGroup>
									<hx:ajaxRefreshSubmit id="ajaxRefreshRequest1" target="group1"></hx:ajaxRefreshSubmit>
								</m:td>
							</m:tr>

							<m:tr>
								<m:td>
								<h:outputLabel id="CTMGT508L12" for="entityID">
									<h:outputText id="PRGCMGTOT923"
										value="#{srCrspd['label.crspd.entityID']}"></h:outputText>
										</h:outputLabel>
									<m:br />
									<h:inputText id="entityID" size="10" maxlength="20"
										value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.entityID}"></h:inputText>
									<h:outputText id="PRGCMGTOT924" escape="false"
										value="#{ref['label.ref.singleSpace']}" />
									<t:commandLink id="PrimaryEntitySearchID"
										onmousedown="javascript:flagWarn=false;"
										action="#{cs_searchCorrespondenceControllerBean.doCorrSearchEntityAction}"
										styleClass="formBtn"
										style="cursor:default;color:white;height:20px;text-align:center;padding:2px 4px 2px 4px;margin:0px 0px 5px 5px;border-right: 0px solid #999999;text-decoration:none;width:100px;">
										<h:outputText id="PRGCMGTOT925" value="Search Entity" />
										<f:param name="LogCrsSrchEntity_ACTION_NAME"
											value="LogCrsEnt_SOURCE_ACTION" />
									</t:commandLink>
									<%--<h:commandButton id="PRGCMGTCB27" styleClass="formBtn"										value="Search Entity"										action="#{cs_searchCorrespondenceControllerBean.doCorrSearchEntityAction}">										
									</h:commandButton>
									<m:inputHidden name="LogCrsSrchEntity_ACTION_NAME" 
										value="LogCrsEnt_SOURCE_ACTION"></m:inputHidden>
									<m:inputHidden name="SrchCREntSrch_ACTION" 
										value="SrchCREntSrch_SOURCE_ACTION"></m:inputHidden>--%>

								</m:td>
							</m:tr>
						</m:table>
					</m:section>

				</m:td>



				<m:td>

					<m:section id="PROVIDERMMS20120731164811412">
						<m:legend styleClass="otherbg">
							<h:outputText id="PRGCMGTOT926"
								value="#{srCrspd['label.crspd.inquiringAboutID']}"></h:outputText>
						</m:legend>

						<m:table id="PROVIDERMMT20120731164811413">
							<m:tr>
								<m:td>
								<h:outputLabel id="CTMGT508L13" for="inqEntityType">
									<h:outputText id="PRGCMGTOT927"
										value="#{srCrspd['label.crspd.entityType']}"></h:outputText>
										</h:outputLabel>
									<m:br />
									<h:selectOneMenu id="inqEntityType"
										value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.inqEntityType}"
										valueChangeListener="#{cs_searchCorrespondenceControllerBean.onInqEntityTypeChange}"
										immediate="true">
										<f:selectItem itemValue="" itemLabel="" />
										<f:selectItems
											value="#{cs_searchCorrespondenceDataBean.entityTypeVVList}" />
									</h:selectOneMenu>
									<hx:behavior event="onchange" behaviorAction="get"
										targetAction="group6" target="inqEntityType"></hx:behavior>
								</m:td>
							</m:tr>

							<m:tr>
								<m:td>
									<h:panelGroup id="group6">
									<h:outputLabel id="CTMGT508L1314" for="inqEntityIDType">
										<h:outputText id="PRGCMGTOT928"
											value="#{srCrspd['label.crspd.entityIDType']}"></h:outputText>
											</h:outputLabel>
										<m:br />
										<h:selectOneMenu id="inqEntityIDType"
											value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.inqEntityIDType}">
											<f:selectItem itemValue="" itemLabel="" />
											<f:selectItems
												value="#{cs_searchCorrespondenceDataBean.inqEntityIDTypeList}" />
										</h:selectOneMenu>
									</h:panelGroup>
									<hx:ajaxRefreshSubmit id="ajaxRefreshRequest6" target="group6"></hx:ajaxRefreshSubmit>

								</m:td>
							</m:tr>

							<m:tr>
								<m:td>
								<h:outputLabel id="CTMGT508L15" for="inqEntityID">
									<h:outputText id="PRGCMGTOT929"
										value="#{srCrspd['label.crspd.entityID']}"></h:outputText>
										</h:outputLabel>
									<m:br />
									<h:inputText id="inqEntityID" size="10" maxlength="20"
										value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.inqEntityID}"></h:inputText>
									<h:outputText id="PRGCMGTOT930" escape="false"
										value="#{ref['label.ref.singleSpace']}" />

									<t:commandLink id="InquiringEntitySearchID"
										onmousedown="javascript:flagWarn=false;"
										action="#{cs_searchCorrespondenceControllerBean.doInqSearchEntityAction}"
										styleClass="formBtn"
										style="cursor:default;color:white;height:20px;text-align:center;padding:2px 4px 2px 4px;margin:0px 0px 5px 5px;border-right: 0px solid #999999;text-decoration:none;width:100px;">
										<h:outputText id="PRGCMGTOT931" value="Search Entity" />
										<f:param name="LogCrsSrchEntity_ACTION_NAME"
											value="LogCrsEnt_SOURCE_ACTION" />
									</t:commandLink>
									<%--<h:commandButton id="PRGCMGTCB28" styleClass="formBtn"										value="Search Entity"										action="#{cs_searchCorrespondenceControllerBean.doInqSearchEntityAction}">										
									</h:commandButton>--%>

								</m:td>
							</m:tr>
						</m:table>
					</m:section>

				</m:td>


				<m:td>
					<m:table id="PROVIDERMMT20120731164811414" cellspacing="5">
						<m:tr>
							<m:td>
							<h:outputLabel id="CTMGT508L16" for="CRN">
								<h:outputText id="PRGCMGTOT932"
									value="#{srCrspd['label.crspd.crn']}"></h:outputText>
							</h:outputLabel>
								<m:br />

								<h:inputText id="CRN" size="15" maxlength="10"
									value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.crn}"></h:inputText>
							</m:td>

							<m:td>
							<h:outputLabel id="CTMGT508L17" for="statusList">
								<h:outputText id="PRGCMGTOT933"
									value="#{srCrspd['label.crspd.status']}"></h:outputText>
									</h:outputLabel>
								<m:br />
								<h:selectOneMenu id="statusList"
									value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.status}">
									<f:selectItem itemValue="" itemLabel="" />
									<f:selectItems
										value="#{cs_searchCorrespondenceDataBean.statusVVList}" />
								</h:selectOneMenu>
							</m:td>
							<m:td>
							<h:outputLabel id="CTMGT508L18" for="category">
								<h:outputText id="PRGCMGTOT934"
									value="#{srCrspd['label.crspd.category']}"></h:outputText>
							</h:outputLabel>
								<m:br />
								<h:selectOneMenu id="category"
									value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.category}"
									valueChangeListener="#{cs_searchCorrespondenceControllerBean.onCategoryChange}"
									onchange="flagWarn=false;">
									<f:selectItem itemValue="" itemLabel="" />
									<f:selectItems
										value="#{cs_searchCorrespondenceDataBean.categoryList}" />
								</h:selectOneMenu>
								<hx:behavior event="onchange" behaviorAction="get"
									targetAction="subjectpanid" target="category"></hx:behavior>
							</m:td>
						</m:tr>

						<m:tr>
							<m:td>
							<h:outputLabel id="CTMGT508L19" for="priority">
								<h:outputText id="PRGCMGTOT935"
									value="#{srCrspd['label.crspd.priority']}"></h:outputText>
							</h:outputLabel>
								<m:br />
								<h:selectOneMenu id="priority"
									value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.priority}">
									<f:selectItem itemValue="" itemLabel="" />
									<f:selectItems
										value="#{cs_searchCorrespondenceDataBean.priorityVVList}" />
								</h:selectOneMenu>
							</m:td>
							<m:td>
							<h:outputLabel id="CTMGT508L20" for="StsDt">
								<h:outputText id="PRGCMGTOT936"
									value="#{srCrspd['label.crspd.statusDate']}"></h:outputText>
							</h:outputLabel>
								<m:br />
								<m:inputCalendar monthYearRowClass="yearMonthHeader"
									weekRowClass="weekHeader" id="StsDt"
									popupDateFormat="MM/dd/yyyy"
									currentDayCellClass="currentDayCell" renderAsPopup="true"
									addResources="true" size="10" maxlength="10"
									renderPopupButtonAsImage="true"
									value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.statusDate}">
								</m:inputCalendar>
							</m:td>
						</m:tr>

					</m:table>

				</m:td>
			</m:tr>
		</m:table>

		<m:div>
			<h:graphicImage id="PROVIDERGI20120731164811415" styleClass="blankImage" width="1" height="5" alt=""
				url="/images/x.gif" />
		</m:div>
		<m:div>
			<h:graphicImage id="PROVIDERGI20120731164811416" styleClass="blankImage" width="1" height="5" alt=""
				url="/images/x.gif" />
		</m:div>
		<m:div id="t1_ClaimData">
			<f:subview id="advancedCrspdSrchSubview">

				<t:htmlTag value="h4">

					<t:commandLink
						action="#{cs_searchCorrespondenceControllerBean.getAdvOptionsPlus}"
						onmousedown="javascript:flagWarn=false;" id="plusid"
						styleClass="plus"
						rendered="#{ not cs_searchCorrespondenceDataBean.advOptionsFl}">
						<h:outputText id="adv_Crspd_SearchPlus"
							value="Advanced Search Options">
						</h:outputText>

					</t:commandLink>

					<t:commandLink
						action="#{cs_searchCorrespondenceControllerBean.getAdvOptionsMinus}"
						onmousedown="javascript:flagWarn=false;" id="minusid"
						styleClass="minus"
						rendered="#{cs_searchCorrespondenceDataBean.advOptionsFl}">
						<h:outputText id="adv_Crspd_SearchMinus"
							value="Advanced Search Options">
						</h:outputText>
					</t:commandLink>

					<h:inputHidden id="advancedOptionsHidden"
						value="#{cs_searchCorrespondenceDataBean.advancedOptionsHidden}"></h:inputHidden>

				</t:htmlTag>

				<m:div id="srchCrspd_div_advcSrchOptns"
					rendered="#{cs_searchCorrespondenceDataBean.advOptionsFl}">
					<m:table id="PROVIDERMMT20120731164811417" width="100%">
						<m:tr>
							<m:td>
								<h:panelGroup id="group1"
									rendered="#{cs_searchCorrespondenceControllerBean.ajaxEntityTypeChange}">
									<h:outputLabel id="CTMGT508L21" for="providerType">
									<h:outputText id="PRGCMGTOT937"
										value="#{srCrspd['label.crspd.providerTypes']}"></h:outputText>
									</h:outputLabel>
									<m:br />
									<h:selectOneMenu id="providerType"
										value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.advSearchCriteria.providerType}"
										onchange="flagWarn=false;">
										<f:selectItem itemValue="" itemLabel="" />
										<f:selectItems
											value="#{cs_searchCorrespondenceDataBean.provTypeVVList}" />
									</h:selectOneMenu>
								</h:panelGroup>
								<hx:ajaxRefreshRequest id="ajaxRefreshRequest1" target="group1"
									params="entityType"></hx:ajaxRefreshRequest>
							</m:td>
							<m:td>
								<h:outputLabel id="CTMGT508L22" for="PRGCMGTIT3">
								<h:outputText id="payerID"
									value="#{srCrspd['label.crspd.payerID']}"></h:outputText>
									</h:outputLabel>
								<m:br />
								<h:inputText id="PRGCMGTIT3" size="15" maxlength="15"
									value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.advSearchCriteria.payerID}"></h:inputText>
							</m:td>
							<m:td>
							<h:outputLabel id="CTMGT508L23" for="createdBy">
								<h:outputText id="PRGCMGTOT938"
									value="#{srCrspd['label.crspd.createdby']}"></h:outputText>
							</h:outputLabel>
								<m:br />
								<h:selectOneMenu id="createdBy"
									value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.advSearchCriteria.createdBY}"
									onchange="flagWarn=false;">
									<f:selectItem itemValue="" itemLabel="" />
									<f:selectItems
										value="#{cs_searchCorrespondenceDataBean.createdByList}" />
								</h:selectOneMenu>
							</m:td>
							<m:td>
							<h:outputLabel id="CTMGT508L24" for="subject">
								<h:outputText id="PRGCMGTOT939"
									value="#{srCrspd['label.crspd.subject']}"></h:outputText>
									</h:outputLabel>
								<m:br />
								<h:panelGroup id="subjectpanid">
									<h:selectOneMenu id="subject"
										value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.advSearchCriteria.subjectCode}"
										onchange="flagWarn=false;">
										<f:selectItem itemValue="" itemLabel="" />
										<f:selectItems
											value="#{cs_searchCorrespondenceDataBean.subjectVVList}" />
									</h:selectOneMenu>
								</h:panelGroup>
								<hx:ajaxRefreshSubmit id="ajaRefReqSub" target="subjectpanid"></hx:ajaxRefreshSubmit>
							</m:td>
						</m:tr>
						<m:tr>
							<m:td>
								<h:panelGroup id="group2"
									rendered="#{cs_searchCorrespondenceControllerBean.ajaxLobChange}">
									<h:outputLabel id="CTMGT508L25" for="reportingUnit">
									<h:outputText id="PRGCMGTOT940"
										value="#{srCrspd['label.crspd.reportingUnit']}"></h:outputText>
									</h:outputLabel>
									<m:br />
									<h:selectOneMenu id="reportingUnit"
										value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.advSearchCriteria.reportingUnit}"
										onchange="flagWarn=false;">
										<f:selectItem itemValue="" itemLabel="" />

										<f:selectItems
											value="#{cs_searchCorrespondenceDataBean.listOfRepUnits}" />
									</h:selectOneMenu>
									<hx:behavior event="onchange" behaviorAction="get;get;get"
										targetAction="group3;group4;group5" target="reportingUnit"></hx:behavior>
								</h:panelGroup>
								<hx:ajaxRefreshRequest id="ajaxRefreshRequest2" target="group2"
									params="lob"></hx:ajaxRefreshRequest>
							</m:td>
							<m:td>
							<h:outputLabel id="CTMGT508L26" for="PRGCMGTIT4">
								<h:outputText id="contact"
									value="#{srCrspd['label.crspd.contact']}"></h:outputText>
							</h:outputLabel>
								<m:br />
								<h:inputText id="PRGCMGTIT4" size="15"
									value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.advSearchCriteria.contact}"></h:inputText>
							</m:td>
							<m:td>
							<h:outputLabel id="CTMGT508L27" for="source">
								<h:outputText id="PRGCMGTOT941"
									value="#{srCrspd['label.crspd.source']}"></h:outputText>
							</h:outputLabel>
								<m:br />
								<h:selectOneMenu id="source"
									value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.advSearchCriteria.source}"
									onchange="flagWarn=false;">
									<f:selectItem itemValue="" itemLabel="" />
									<f:selectItems
										value="#{cs_searchCorrespondenceDataBean.sourceVVList}" />
								</h:selectOneMenu>
							</m:td>
							<m:td>
							<h:outputLabel id="CTMGT508L28" for="resoln">
								<h:outputText id="PRGCMGTOT942"
									value="#{srCrspd['label.crspd.resolutions']}"></h:outputText>
							</h:outputLabel>
								<m:br />
								<h:selectOneMenu id="resoln"
									value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.advSearchCriteria.resolutionCode}"
									onchange="flagWarn=false;">
									<f:selectItem itemValue="" itemLabel="" />
									<f:selectItems
										value="#{cs_searchCorrespondenceDataBean.resolnVVList}" />
								</h:selectOneMenu>
							</m:td>
						</m:tr>
						<m:tr>
							<m:td>
								<h:panelGroup id="group3"
									rendered="#{cs_searchCorrespondenceControllerBean.ajaxReportingUnitChange}">
									<h:outputLabel id="CTMGT508L29" for="businessUnit">
									<h:outputText id="PRGCMGTOT943"
										value="#{srCrspd['label.crspd.businessUnit']}"></h:outputText>
									</h:outputLabel>
									<m:br />
									<h:selectOneMenu id="businessUnit"
										value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.advSearchCriteria.businessUnit}"
										onchange="flagWarn=false;">
										<f:selectItem itemValue="" itemLabel="" />

										<f:selectItems
											value="#{cs_searchCorrespondenceDataBean.listOfRefBusUnits}" />
									</h:selectOneMenu>
									<hx:behavior event="onchange" behaviorAction="get;get"
										targetAction="group4;group5" target="businessUnit"></hx:behavior>
								</h:panelGroup>
								<hx:ajaxRefreshRequest id="ajaxRefreshRequest3" target="group3"
									params="lob;reportingUnit"></hx:ajaxRefreshRequest>
							</m:td>
							<m:td>
								<h:outputLabel id="PRGCMGTOLL475" for="createdFrm"
									value="#{srCrspd['label.crspd.createdFrom']}"></h:outputLabel>
								<m:br />
								<m:inputCalendar monthYearRowClass="yearMonthHeader"
									weekRowClass="weekHeader" id="createdFrm"
									popupDateFormat="MM/dd/yyyy"
									currentDayCellClass="currentDayCell" renderAsPopup="true"
									addResources="true" size="10" maxlength="10"
									renderPopupButtonAsImage="true"
									value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.advSearchCriteria.createdFromDate}"
									onmouseover="javascript:flagWarn=false;"
									onmouseout="javascript:flagWarn=false;">
								</m:inputCalendar>
								<m:br />
								<h:message id="PRGCMGTM158" for="createdFrm" showDetail="true"
									style="color: red" />
							</m:td>
							<m:td>
								<h:panelGroup id="group4"
									rendered="#{cs_searchCorrespondenceControllerBean.ajaxDepartmentChange}">
									<h:outputLabel id="CTMGT508L30" for="assignedTo">
									<h:outputText id="PRGCMGTOT944"
										value="#{srCrspd['label.crspd.assignedTo']}"></h:outputText>
									</h:outputLabel>
									<m:br />
									<h:selectOneMenu id="assignedTo"
										value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.advSearchCriteria.assignedTo}"
										onchange="flagWarn=false;">
										<f:selectItem itemValue="" itemLabel="" />
										<f:selectItems
											value="#{cs_searchCorrespondenceDataBean.assignedToList}" />
									</h:selectOneMenu>
								</h:panelGroup>
								<hx:ajaxRefreshRequest id="ajaxRefreshRequest4" target="group4"
									params="lob;reportingUnit;businessUnit;department"></hx:ajaxRefreshRequest>
							</m:td>
							<m:td colspan="2">
							<h:outputLabel id="CTMGT508L31" for="lob">
								<h:outputText id="PRGCMGTOT945"
									value="#{srCrspd['label.crspd.lob']}"></h:outputText>
							</h:outputLabel>
								<m:br />
								<h:selectOneMenu id="lob"
									value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.advSearchCriteria.lineOfBusinessGroup}"
									onchange="flagWarn=false;">
									<f:selectItem itemValue="" itemLabel="" />

									<f:selectItems
										value="#{cs_searchCorrespondenceDataBean.lobVVList}" />
								</h:selectOneMenu>
								<hx:behavior event="onchange" behaviorAction="get;get;get;get;"
									targetAction="group2;group3;group4;group5" target="lob"></hx:behavior>
								<m:br />
							</m:td>
						</m:tr>
						<m:tr>
							<m:td>
								<h:panelGroup id="group5"
									rendered="#{cs_searchCorrespondenceControllerBean.ajaxBusinessUnitChange}">
									<h:outputLabel id="CTMGT508L32" for="department">
									<h:outputText id="PRGCMGTOT946"
										value="#{srCrspd['label.crspd.workunits']}"></h:outputText>
									</h:outputLabel>
									<m:br />
									<h:selectOneMenu id="department"
										value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.advSearchCriteria.department}"
										onchange="flagWarn=false;">
										<f:selectItem itemValue="" itemLabel="" />

										<f:selectItems
											value="#{cs_searchCorrespondenceDataBean.listOfRefDeptUnits}" />
									</h:selectOneMenu>
									<hx:behavior event="onchange" behaviorAction="get"
										targetAction="group4" target="department"></hx:behavior>
								</h:panelGroup>
								<hx:ajaxRefreshRequest id="ajaxRefreshRequest5" target="group5"
									params="lob;reportingUnit;businessUnit"></hx:ajaxRefreshRequest>
							</m:td>
							<m:td>
								<h:outputLabel id="PRGCMGTOLL476" for="createdTo"
									value="#{srCrspd['label.crspd.createdTo']}">
								</h:outputLabel>
								<m:br />
								<m:inputCalendar monthYearRowClass="yearMonthHeader"
									weekRowClass="weekHeader" id="createdTo"
									popupDateFormat="MM/dd/yyyy"
									currentDayCellClass="currentDayCell" renderAsPopup="true"
									addResources="true" size="10" maxlength="10"
									renderPopupButtonAsImage="true"
									value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.advSearchCriteria.createdToDate}"
									onmouseover="javascript:flagWarn=false;"
									onmouseout="javascript:flagWarn=false;">
								</m:inputCalendar>
								<m:br />
								<h:message id="PRGCMGTM159" for="createdTo" showDetail="true"
									style="color: red" />
							</m:td>
							<m:td>


								<h:outputLabel id="CTMGT508L33" for="PRGCMGTSOR3">
								<h:outputText id="responseExists"
									value="#{srCrspd['label.crspd.responseExists']}"></h:outputText>
								</h:outputLabel>
								<h:selectOneRadio id="PRGCMGTSOR3"
									value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.advSearchCriteria.responseExistIndicator}">
									<f:selectItem itemLabel="Yes" itemValue="Y" />
									<f:selectItem itemLabel="No" itemValue="N" />
								</h:selectOneRadio>
							</m:td>
						</m:tr>
					</m:table>
				</m:div>
			</f:subview>
		</m:div>

		<%-- Changed action name From getCorrespondence To searhCorrespondence for CR:ESPRD00798895 --%>
		<m:div align="right">
			<h:commandButton id="PRGCMGTCB29" styleClass="formBtn"
				value="Associate/Disassociate"
				action="#{CorrespondenceControllerBean.addOrRemoveSearchCRsFromAssociatedCRs}"
				onmousedown="javascript:flagWarn=false;" />
			<h:outputText id="PRGCMGTOT947" escape="false"
				value="#{ref['label.ref.singleSpace']}" />
			<h:commandButton id="PRGCMGTCB30" styleClass="formBtn" value="Search"
				action="#{cs_searchCorrespondenceControllerBean.searhCorrespondence}"
				onmousedown="javascript:flagWarn=false;" />


			<h:outputText id="PRGCMGTOT948" escape="false"
				value="#{ref['label.ref.singleSpace']}" />
			<h:commandButton id="PRGCMGTCB31" styleClass="formBtn" value="Reset"
				action="#{cs_searchCorrespondenceControllerBean.resetCorrespondence}"
				onmousedown="javascript:flagWarn=false;" />
			<h:outputText id="PRGCMGTOT949" escape="false"
				value="#{ref['label.ref.singleSpace']}" />
		</m:div>

	</m:pod>

</m:div>
