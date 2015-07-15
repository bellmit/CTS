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
<!-- <script>

    function getSearch(e)
    {
	  var keyCode=e.keyCode;
	  if(keyCode == '13')
      {
      var searchBt = findObjectByPartOfID('searchCr');
	  searchBt.click();
      }

    }

   function getSearchentity(e)
    {
	  var keyCode=e.keyCode;
	  if(keyCode == '13')
        {
		var searchBt = findObjectByPartOfID('searchentity2');
	 	searchBt.click();
        }
    }

   function findObjectByPartOfID(partOfID)
   {
	for(i=0; i<document.forms.length; i++)
	 {
		for(j=0; j<document.forms[i].elements.length; j++)
		{
			var idValue = document.forms[i].elements[j].id;
			if(idValue.indexOf(partOfID)!=-1)
			{
				G_isFirstTime = false;
				G_countObject = document.forms[i].elements[j];
				return document.forms[i].elements[j];
			}
		}
	}		
	return null;
  }

</script> -->

<m:div styleClass="floatContainer">

	<m:pod title="Search For Correspondence" styleClass="otherbg">

		<m:table id="PROVIDERMMT20120731164811680" width="98%" cellspacing="5">
			<m:tr>
				<m:td>

					<m:section id="PROVIDERMMS20120731164811681">
						<m:legend styleClass="otherbg">
							<h:outputText id="PRGCMGTOT1806"
								value="#{srCrspd['label.crspd.crspdfor']}"></h:outputText>
						</m:legend>

						<m:table id="PROVIDERMMT20120731164811682">
							<m:tr>
								<m:td>
									<h:outputLabel id="PRGCMGTOLL739" for="entityType"
										style="color:black; background:transparent">
										<h:outputText value="#{srCrspd['label.crspd.entityType']}"
											id="entityTypeForCRSPD"></h:outputText>
									</h:outputLabel>
									<m:br />
									<t:selectOneMenu id="entityType"
										value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.entityType}"
										valueChangeListener="#{cs_searchCorrespondenceControllerBean.onEntityTypeChange}"
										onchange="flagWarn=false; " immediate="true">
										<f:selectItem itemValue=" " itemLabel="" />
										<f:selectItems
											value="#{cs_searchCorrespondenceDataBean.entityTypeVVList}" />
									</t:selectOneMenu>
									<hx:behavior event="onchange" behaviorAction="get"
										targetAction="group1" target="entityType"></hx:behavior>
								</m:td>
							</m:tr>

							<m:tr>
								<m:td>
									<h:panelGroup id="group1">
										<h:outputLabel id="PRGCMGTOLL740" for="entityIDType"
											style="color:black; background:transparent">
											<h:outputText value="#{srCrspd['label.crspd.entityIDType']}"
												id="entityIDTypeForCRSPD">
											</h:outputText>
										</h:outputLabel>
										<m:br />
										<%-- Below empty space added for defect ESPRD00883838 to avoid validation error --%>
										<%-- New attribute added for defect ESPRD00885871 to avoid validation error --%>
										<t:selectOneMenu id="entityIDType" immediate="true"
											value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.entityIDType}">
											<f:selectItem itemValue=" " itemLabel="" />
											<f:selectItems
												value="#{cs_searchCorrespondenceDataBean.entityIDTypeList}" />
										</t:selectOneMenu>
									</h:panelGroup>
									<hx:ajaxRefreshSubmit id="ajaxRefreshRequest1" target="group1"></hx:ajaxRefreshSubmit>
								</m:td>
							</m:tr>

							<m:tr>
								<m:td>
									<h:outputLabel id="PRGCMGTOLL741" for="entityID"
										style="color:black; background:transparent">
										<h:outputText value="#{srCrspd['label.crspd.entityID']}"
											id="entityIDForCRSPD"></h:outputText>
									</h:outputLabel>
									<m:br />
									<t:inputText id="entityID" size="10" maxlength="20"
										value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.entityID}"
										onkeyup="this.value=this.value.toUpperCase();"
										onblur="this.value=this.value.toUpperCase();">
									</t:inputText>
									<h:outputText id="PRGCMGTOT1807" escape="false"
										value="#{ref['label.ref.singleSpace']}" />

									<h:commandButton id="PRGCMGTCB90" styleClass="formBtn"
										value="Search Entity"
										action="#{cs_searchCorrespondenceControllerBean.doCorrSearchEntityAction}">
									</h:commandButton>

									<m:inputHidden name="SrchCREntSrch_ACTION"
										value="SrchCREntSrch_SOURCE_ACTION"></m:inputHidden>

								</m:td>
							</m:tr>
						</m:table>
					</m:section>

				</m:td>



				<m:td>

					<m:section id="PROVIDERMMS20120731164811683">
						<m:legend styleClass="otherbg">
							<h:outputText id="PRGCMGTOT1808"
								value="#{srCrspd['label.crspd.inquiringAboutID']}"></h:outputText>
						</m:legend>

						<m:table id="PROVIDERMMT20120731164811684">
							<m:tr>
								<m:td>
									<h:outputLabel id="PRGCMGTOLL742" for="inqEntityType"
										style="color:black; background:transparent">
										<h:outputText value="#{srCrspd['label.crspd.entityType']}"
											id="entityTypeForINQ"></h:outputText>
									</h:outputLabel>
									<m:br />
									<t:selectOneMenu id="inqEntityType"
										value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.inqEntityType}"
										valueChangeListener="#{cs_searchCorrespondenceControllerBean.onInqEntityTypeChange}"
										immediate="true">
										<f:selectItem itemValue=" " itemLabel="" />
										<f:selectItems
											value="#{cs_searchCorrespondenceDataBean.entityTypeVVList}" />
									</t:selectOneMenu>
									<hx:behavior event="onchange" behaviorAction="get"
										targetAction="group6" target="inqEntityType"></hx:behavior>
								</m:td>
							</m:tr>

							<m:tr>
								<m:td>
									<h:panelGroup id="group6">
										<h:outputLabel id="PRGCMGTOLL743" for="inqEntityIDType"
											style="color:black; background:transparent">
											<h:outputText value="#{srCrspd['label.crspd.entityIDType']}"
												id="entityIDTypeForINQ"></h:outputText>
										</h:outputLabel>
										<m:br />
										<%-- Below empty space added for defect ESPRD00883838 to avoid validation error --%>
										<%-- New attribute added for defect ESPRD00885871 to avoid validation error --%>
										<t:selectOneMenu id="inqEntityIDType" immediate="true"
											value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.inqEntityIDType}">
											<f:selectItem itemValue=" " itemLabel="" />
											<f:selectItems
												value="#{cs_searchCorrespondenceDataBean.inqEntityIDTypeList}" />
										</t:selectOneMenu>
									</h:panelGroup>
									<hx:ajaxRefreshSubmit id="ajaxRefreshRequest6" target="group6"></hx:ajaxRefreshSubmit>

								</m:td>
							</m:tr>

							<m:tr>
								<m:td>
									<h:outputLabel id="PRGCMGTOLL744" for="inqEntityID"
										style="color:black; background:transparent">
										<h:outputText value="#{srCrspd['label.crspd.entityID']}"
											id="entityIDForINQ"></h:outputText>
									</h:outputLabel>
									<m:br />
									<t:inputText id="inqEntityID" size="10" maxlength="20"
										value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.inqEntityID}">
									</t:inputText>
									<h:outputText id="PRGCMGTOT1809" escape="false"
										value="#{ref['label.ref.singleSpace']}" />

									<h:commandButton styleClass="formBtn" value="Search Entity"
										id="searchentity2"
										action="#{cs_searchCorrespondenceControllerBean.doInqSearchEntityAction}">
									</h:commandButton>

								</m:td>
							</m:tr>
						</m:table>
					</m:section>

				</m:td>


				<m:td>
					<m:table id="PROVIDERMMT20120731164811685" cellspacing="5">
						<m:tr>
							<m:td>
								<h:outputLabel id="PRGCMGTOLL745" for="CRN"
									style="color:black; background:transparent">
									<h:outputText value="#{srCrspd['label.crspd.crn']}"
										id="crnForSERCHcr"></h:outputText>
								</h:outputLabel>
								<m:br />

								<t:inputText id="CRN" size="15" maxlength="10"
									value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.crn}">
								</t:inputText>
							</m:td>

							<m:td>
								<h:outputLabel id="PRGCMGTOLL746" for="statusList"
									style="color:black; background:transparent">
									<h:outputText value="#{srCrspd['label.crspd.status']}"
										id="statusForSERCHcr"></h:outputText>
								</h:outputLabel>
								<m:br />
								<%-- Below empty space added for defect ESPRD00883838 to avoid validation error --%>
								<t:selectOneMenu id="statusList" onchange="flagWarn=false;"
									value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.status}">
									<f:selectItem itemValue=" " itemLabel="" />
									<f:selectItems
										value="#{cs_searchCorrespondenceDataBean.statusVVList}" />
								</t:selectOneMenu>
							</m:td>
							<m:td>
								<h:outputLabel id="PRGCMGTOLL747" for="category"
									style="color:black; background:transparent">
									<h:outputText value="#{srCrspd['label.crspd.category']}"
										id="categoryForSERCHcr"></h:outputText>
								</h:outputLabel>
								<m:br />
								<%-- Below empty space added for defect ESPRD00883838 to avoid validation error --%>
								<t:selectOneMenu id="category"
									value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.category}"
									valueChangeListener="#{cs_searchCorrespondenceControllerBean.onCategoryChange}"
									onchange="flagWarn=false; this.form.submit()" immediate="true">
									<f:selectItem itemValue=" " itemLabel="" />
									<f:selectItems
										value="#{cs_searchCorrespondenceDataBean.categoryList}" />
								</t:selectOneMenu>
							</m:td>
						</m:tr>

						<m:tr>
							<m:td>
								<h:outputLabel id="PRGCMGTOLL748" for="priority"
									style="color:black; background:transparent">
									<h:outputText value="#{srCrspd['label.crspd.priority']}"
										id="priorityForSERCHcr"></h:outputText>
								</h:outputLabel>
								<m:br />
								<%-- Below empty space added for defect ESPRD00883838 to avoid validation error --%>
								<t:selectOneMenu id="priority"
									value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.priority}">
									<f:selectItem itemValue=" " itemLabel="" />
									<f:selectItems
										value="#{cs_searchCorrespondenceDataBean.priorityVVList}" />
								</t:selectOneMenu>
							</m:td>
							<m:td>
								<h:outputLabel id="PRGCMGTOLL749" for="StsDt"
									style="color:black; background:transparent">
									<h:outputText value="#{srCrspd['label.crspd.statusDate']}"
										id="statusDateForSERCHcr"></h:outputText>
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
			<h:graphicImage id="PROVIDERGI20120731164811686" styleClass="blankImage" width="1" height="5" alt=""
				url="/images/x.gif" />
		</m:div>
		<m:div>
			<h:graphicImage id="PROVIDERGI20120731164811687" styleClass="blankImage" width="1" height="5" alt=""
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
					<m:table id="PROVIDERMMT20120731164811688" width="100%">
						<m:tr>
							<m:td>
								<h:panelGroup id="group1"
									rendered="#{cs_searchCorrespondenceControllerBean.ajaxEntityTypeChange}">
									<h:outputLabel id="PRGCMGTOLL750"
										for="providerType"
										style="color:black; background:transparent">
										<h:outputText value="#{srCrspd['label.crspd.providerTypes']}"
											id="providerTypesForAsvScrch"></h:outputText>
									</h:outputLabel>
									<m:br />
									<%-- Below empty space added for defect ESPRD00883838 to avoid validation error --%>
									<t:selectOneMenu id="providerType"
										value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.advSearchCriteria.providerType}"
										onchange="flagWarn=false;">
										<f:selectItem itemValue=" " itemLabel="" />
										<f:selectItems
											value="#{cs_searchCorrespondenceDataBean.provTypeVVList}" />
									</t:selectOneMenu>
								</h:panelGroup>
								<hx:ajaxRefreshRequest id="ajaxRefreshRequest1" target="group1"
									params="entityType"></hx:ajaxRefreshRequest>
							</m:td>
							<m:td>
								<h:outputLabel id="PRGCMGTOLL751" for="CMGTTOMIT1"
									style="color:black; background:transparent">
									<h:outputText id="payerID"
										value="#{srCrspd['label.crspd.payerID']}"></h:outputText>
								</h:outputLabel>
								<m:br />
								<t:inputText id="CMGTTOMIT1" size="15" maxlength="15"
									value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.advSearchCriteria.payerID}"></t:inputText>
							</m:td>
							<m:td>
								<h:outputLabel id="PRGCMGTOLL752" for="createdBy"
									style="color:black; background:transparent">
									<h:outputText value="#{srCrspd['label.crspd.createdby']}"
										id="createdbyForAsvScrch"></h:outputText>
								</h:outputLabel>
								<m:br />
								<%-- Below empty space added for defect ESPRD00883838 to avoid validation error --%>
								<t:selectOneMenu id="createdBy"
									value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.advSearchCriteria.createdBY}"
									onchange="flagWarn=false;">
									<f:selectItem itemValue=" " itemLabel="" />
									<f:selectItems
										value="#{cs_searchCorrespondenceDataBean.createdByList}" />
								</t:selectOneMenu>
							</m:td>
							<m:td>
								<h:outputLabel id="PRGCMGTOLL753" for="subject"
									style="color:black; background:transparent">
									<h:outputText value="#{srCrspd['label.crspd.subject']}"
										id="subjectForAsvScrch"></h:outputText>
								</h:outputLabel>
								<m:br />
								<%-- Below empty space added for defect ESPRD00883838 to avoid validation error --%>
								<t:selectOneMenu id="subject"
									value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.advSearchCriteria.subjectCode}"
									onchange="flagWarn=false;">
									<f:selectItem itemValue=" " itemLabel="" />
									<f:selectItems
										value="#{cs_searchCorrespondenceDataBean.subjectVVList}" />
								</t:selectOneMenu>
							</m:td>
						</m:tr>
						<m:tr>
							<m:td>
								<h:panelGroup id="group2"
									rendered="#{cs_searchCorrespondenceControllerBean.ajaxLobChange}">

									<h:outputLabel id="PRGCMGTOLL754"
										for="reportingUnit"
										style="color:black; background:transparent">
										<h:outputText value="#{srCrspd['label.crspd.reportingUnit']}"
											id="reportingUnitForAsvScrch"></h:outputText>
									</h:outputLabel>
									<m:br />
									<%-- Below empty space added for defect ESPRD00883838 to avoid validation error --%>
									<t:selectOneMenu id="reportingUnit"
										value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.advSearchCriteria.reportingUnit}"
										onchange="flagWarn=false;">
										<f:selectItem itemValue=" " itemLabel="" />
										<f:selectItems
											value="#{cs_searchCorrespondenceDataBean.listOfRepUnits}" />
									</t:selectOneMenu>
									<hx:behavior event="onchange" behaviorAction="get;get;get"
										targetAction="group3;group4;group5" target="reportingUnit"></hx:behavior>
								</h:panelGroup>
								<hx:ajaxRefreshRequest id="ajaxRefreshRequest2" target="group2"
									params="lob"></hx:ajaxRefreshRequest>
							</m:td>
							<m:td>
								<h:outputLabel id="PRGCMGTOLL755" for="CMGTTOMIT2"
									style="color:black; background:transparent">
									<h:outputText id="contact"
										value="#{srCrspd['label.crspd.contact']}"></h:outputText>
								</h:outputLabel>
								<m:br />
								<t:inputText id="CMGTTOMIT2" size="15"
									value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.advSearchCriteria.contact}"></t:inputText>
							</m:td>
							<m:td>
								<h:outputLabel id="PRGCMGTOLL756" for="source"
									style="color:black; background:transparent">
									<h:outputText value="#{srCrspd['label.crspd.source']}"
										id="sourceForAsvScrch"></h:outputText>
								</h:outputLabel>
								<m:br />
								<%-- Below empty space added for defect ESPRD00883838 to avoid validation error --%>
								<t:selectOneMenu id="source"
									value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.advSearchCriteria.source}"
									onchange="flagWarn=false;">
									<f:selectItem itemValue=" " itemLabel="" />
									<f:selectItems
										value="#{cs_searchCorrespondenceDataBean.sourceVVList}" />
								</t:selectOneMenu>
							</m:td>
							<m:td>
								<h:outputLabel id="PRGCMGTOLL757" for="resoln"
									style="color:black; background:transparent">
									<h:outputText value="#{srCrspd['label.crspd.resolutions']}"
										id="resolutionsForAsvScrch"></h:outputText>
								</h:outputLabel>
								<m:br />
								<%-- Below empty space added for defect ESPRD00883838 to avoid validation error --%>
								<t:selectOneMenu id="resoln"
									value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.advSearchCriteria.resolutionCode}"
									onchange="flagWarn=false;">
									<f:selectItem itemValue=" " itemLabel="" />
									<f:selectItems
										value="#{cs_searchCorrespondenceDataBean.resolnVVList}" />
								</t:selectOneMenu>
							</m:td>
						</m:tr>
						<m:tr>
							<m:td>
								<h:panelGroup id="group3"
									rendered="#{cs_searchCorrespondenceControllerBean.ajaxReportingUnitChange}">
									<h:outputLabel id="PRGCMGTOLL758" for="businessUnit"
										style="color:black; background:transparent">
										<h:outputText value="#{srCrspd['label.crspd.businessUnit']}"
											id="businessUnitForAsvScrch"></h:outputText>
									</h:outputLabel>
									<m:br />
									<%-- Below empty space added for defect ESPRD00883838 to avoid validation error --%>
									<t:selectOneMenu id="businessUnit"
										value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.advSearchCriteria.businessUnit}"
										onchange="flagWarn=false;">
										<f:selectItem itemValue=" " itemLabel="" />
										<f:selectItems
											value="#{cs_searchCorrespondenceDataBean.listOfRefBusUnits}" />
									</t:selectOneMenu>
									<hx:behavior event="onchange" behaviorAction="get;get"
										targetAction="group4;group5" target="businessUnit"></hx:behavior>
								</h:panelGroup>
								<hx:ajaxRefreshRequest id="ajaxRefreshRequest3" target="group3"
									params="lob;reportingUnit"></hx:ajaxRefreshRequest>
							</m:td>
							<m:td>
								<h:outputLabel id="PRGCMGTOLL759" for="createdFrm"
									value="#{srCrspd['label.crspd.createdFrom']}"
									style="color:black; background:transparent"></h:outputLabel>
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
								<h:message id="PRGCMGTM242" for="createdFrm" showDetail="true"
									style="color: red" />
							</m:td>
							<m:td>
								<h:panelGroup id="group4"
									rendered="#{cs_searchCorrespondenceControllerBean.ajaxDepartmentChange}">
									<h:outputLabel id="PRGCMGTOLL760" for="assignedTo"
										style="color:black; background:transparent">
										<h:outputText value="#{srCrspd['label.crspd.assignedTo']}"
											id="assignedToForAsvScrch"></h:outputText>
									</h:outputLabel>
									<m:br />
									<%-- Below empty space added for defect ESPRD00883838 to avoid validation error --%>
									<t:selectOneMenu id="assignedTo"
										value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.advSearchCriteria.assignedTo}"
										onchange="flagWarn=false;">
										<f:selectItem itemValue=" " itemLabel="" />
										<f:selectItems
											value="#{cs_searchCorrespondenceDataBean.assignedToList}" />
									</t:selectOneMenu>
								</h:panelGroup>
								<hx:ajaxRefreshRequest id="ajaxRefreshRequest4" target="group4"
									params="lob;reportingUnit;businessUnit;department"></hx:ajaxRefreshRequest>
							</m:td>
							<m:td colspan="2">
								<h:outputLabel id="PRGCMGTOLL761" for="lob"
									style="color:black; background:transparent">
									<h:outputText value="#{srCrspd['label.crspd.lob']}"
										id="lobForAsvScrch"></h:outputText>
								</h:outputLabel>
								<m:br />
								<%-- Below empty space added for defect ESPRD00883838 to avoid validation error --%>
								<t:selectOneMenu id="lob"
									value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.advSearchCriteria.lineOfBusinessGroup}"
									onchange="flagWarn=false;">
									<f:selectItem itemValue=" " itemLabel="" />
									<f:selectItems
										value="#{cs_searchCorrespondenceDataBean.lobVVList}" />
								</t:selectOneMenu>
								<hx:behavior event="onchange" behaviorAction="get;get;get;get;"
									targetAction="group2;group3;group4;group5" target="lob"></hx:behavior>
								<m:br />
							</m:td>
						</m:tr>
						<m:tr>
							<m:td>
								<h:panelGroup id="group5"
									rendered="#{cs_searchCorrespondenceControllerBean.ajaxBusinessUnitChange}">
									<h:outputLabel id="PRGCMGTOLL762" for="department"
										style="color:black; background:transparent">
										<h:outputText value="#{srCrspd['label.crspd.workunits']}"
											id="workunitsForAsvScrch"></h:outputText>
									</h:outputLabel>
									<m:br />
									<%-- Below empty space added for defect ESPRD00883838 to avoid validation error --%>
									<t:selectOneMenu id="department"
										value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.advSearchCriteria.department}"
										onchange="flagWarn=false;">
										<f:selectItem itemValue=" " itemLabel="" />
										<f:selectItems
											value="#{cs_searchCorrespondenceDataBean.listOfRefDeptUnits}" />
									</t:selectOneMenu>
									<hx:behavior event="onchange" behaviorAction="get"
										targetAction="group4" target="department"></hx:behavior>
								</h:panelGroup>
								<hx:ajaxRefreshRequest id="ajaxRefreshRequest5" target="group5"
									params="lob;reportingUnit;businessUnit"></hx:ajaxRefreshRequest>
							</m:td>
							<m:td>
								<h:outputLabel id="PRGCMGTOLL763" for="createdTo"
									value="#{srCrspd['label.crspd.createdTo']}"
									style="color:black; background:transparent">
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
								<h:message id="PRGCMGTM243" for="createdTo" showDetail="true"
									style="color: red" />
							</m:td>
							<m:td>
								<h:outputLabel id="PRGCMGTOLL764" for="CMGTTOMSOR1"
									style="color:black; background:transparent">
									<h:outputText id="responseExists"
										value="#{srCrspd['label.crspd.responseExists']}"></h:outputText>
								</h:outputLabel>
								<t:selectOneRadio id="CMGTTOMSOR1" enabledClass="black"
									value="#{cs_searchCorrespondenceDataBean.correspondenceSearchCriteriaVO.advSearchCriteria.responseExistIndicator}">
									<f:selectItem itemLabel="Yes" itemValue="Y" />
									<f:selectItem itemLabel="No" itemValue="N" />
								</t:selectOneRadio>
							</m:td>
						</m:tr>
					</m:table>
				</m:div>
			</f:subview>
		</m:div>

	<%--Changed action name from getCorrespondence to searchCorrespondence for CR:ESPRD00798895 --%>
		<m:div align="right">
			<h:commandButton styleClass="formBtn" value="Search" id="searchCr"
				action="#{cs_searchCorrespondenceControllerBean.searhCorrespondence}"
				onmousedown="javascript:flagWarn=false;" />


			<h:outputText id="PRGCMGTOT1810" escape="false"
				value="#{ref['label.ref.singleSpace']}" />
			<h:commandButton id="PRGCMGTCB91" styleClass="formBtn" value="Reset"
				action="#{cs_searchCorrespondenceControllerBean.resetCorrespondence}"
				onmousedown="javascript:flagWarn=false;" />
			<h:outputText id="PRGCMGTOT1811" escape="false"
				value="#{ref['label.ref.singleSpace']}" />
		</m:div>

	</m:pod>

</m:div>
