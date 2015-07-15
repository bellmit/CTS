<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
  Portlet 8.0 Migration Activity: h:commandButton with param is not supported in portal 8.0, replacing with myfaces t:commandButton tag
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
<script>

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

</script>

<m:div id="logcasSrchCrDiv1" styleClass="floatContainer">

	<m:pod id="logcasSrchCrPod1" title="Search For Correspondence" styleClass="otherbg">

		<m:table  id="logcasSrchCrTb1" width="100%" cellspacing="5">
			<m:tr id="logcasSrchCrTr1">
				<m:td id="logcasSrchCrTd1">

					<m:section id="logcasSrchCrSec1">
						<m:legend  id="logcasSrchCrLeg1">
							<h:outputText  id="logcasSrchCrOutTxt1" value="#{srCrspd['label.crspd.crspdfor']}"></h:outputText>
						</m:legend>

						<m:table  id="logcasSrchCrTb4">
							<m:tr id="logcasSrchCrTr2">
								<m:td id="logcasSrchCrTd2">
									<h:outputLabel for="CorrSearchEntityType" id="logcasSrchCrOutTxt2" value="#{srCrspd['label.crspd.entityType']}"></h:outputLabel>
									<m:br id="logcasSrchCrOutBr1" />
									<%--<h:selectOneMenu id="CorrSearchEntityType"										value="#{logCaseDataBean.correspondenceSearchCriteriaVO.entityType}"										valueChangeListener="#{logCaseControllerBean.onEntityTypeChange}"										onchange="javascript:focusThis(this.id);this.form.submit()">
										<f:selectItem itemValue=" " itemLabel="" />
										<f:selectItems value="#{logCaseDataBean.entityTypeVVList}" />
									</h:selectOneMenu>--%>
									
									<h:selectOneMenu id="CorrSearchEntityType"
										value="#{logCaseDataBean.correspondenceSearchCriteriaVO.entityType}"
										valueChangeListener="#{logCaseControllerBean.onEntityTypeChange}"
										onchange="javascript:flagWarn=false;focusThis(this.id);" immediate="true">
										<f:selectItem itemValue=" " itemLabel="" />
										<f:selectItems value="#{logCaseDataBean.entityTypeVVList}" />
									</h:selectOneMenu>
									<hx:behavior event="onchange" behaviorAction="get;get" targetAction="groupCr1;advGroup" target="CorrSearchEntityType"></hx:behavior>
									<m:br/>
									<h:message for="CorrSearchEntityType" styleClass="errorMessage" id="logCaseSearchCrrTypeMsgId"/>
								</m:td>
							</m:tr>

							<m:tr id="logcasSrchCrTr3">
								<m:td id="logcasSrchCrTd3">
									<h:outputLabel for="CorrSearchEntityIDType" id="logcasSrchCrOutTxt3" value="#{srCrspd['label.crspd.entityIDType']}"></h:outputLabel>
									<m:br id="logcasSrchCrOutBr2" />
									<h:panelGroup id="groupCr1">
									<h:selectOneMenu id="CorrSearchEntityIDType"										value="#{logCaseDataBean.correspondenceSearchCriteriaVO.entityIDType}">
										<f:selectItem itemValue=" " itemLabel="" />
										<f:selectItems value="#{logCaseDataBean.entityIDTypeList}" />
									</h:selectOneMenu>
									</h:panelGroup>
									<hx:ajaxRefreshSubmit id="CorrSearchEntityIDTypeRefresh" target="groupCr1"></hx:ajaxRefreshSubmit>
									<m:br/>
									<h:message for="CorrSearchEntityIDType" styleClass="errorMessage" id="logCaseSearchCrrIDTypeMsgId"/>
								</m:td>
							</m:tr>

							<m:tr id="logcasSrchCrTr4">
								<m:td id="logcasSrchCrTd4">
									<h:outputLabel for="CorrEntityID" id="logcasSrchCrOutTxt4" value="#{srCrspd['label.crspd.entityID']}"></h:outputLabel>
									<m:br id="logcasSrchCrOutBr3" />
									<h:inputText id="CorrEntityID" size="10" maxlength="10"	value="#{logCaseDataBean.correspondenceSearchCriteriaVO.entityID}"	/>
									<%--onkeyup and onblur commented for defect ESPRD00754727--%>								
									<%-- onkeyup="this.value=this.value.toUpperCase();" onblur="this.value=this.value.toUpperCase();"--%>
									<h:outputText id="logcasSrchCrOutTxt5" escape="false"										value="#{ref['label.ref.singleSpace']}" />
									<%--Modified for the defect ESPRD00849890---%>	 
									<t:commandButton	styleClass="formBtn"	action="#{logCaseControllerBean.doCorrSearchEntityAction}"		onmousedown="javascript:flagWarn=false;"	value="#{srCrspd['label.searchCase.srchEntity']}"	id="assCorrSrchEntity">
										<f:param id="assSrchCorrEntSrch" name="SrchCorrEntSrch_ACTION"
											value="SrchCorrEntSrch_SOURCE_ACTION" />
									</t:commandButton>
									<m:br/>
									<h:message for="CorrEntityID" styleClass="errorMessage" id="logCaseSearchCrrMsgId"/>
								</m:td>
							</m:tr>
						</m:table>
					</m:section>
				</m:td>
					
					<m:td id="logcasSrchCrTd5">

					<m:section id="logcasSrchCrSec2">
						<m:legend  id="logcasSrchCrLeg2" styleClass="otherbg">
							<h:outputText id="logcasSrchCrOutTxt7" value="#{srCrspd['label.crspd.inquiringAboutID']}"></h:outputText>
						</m:legend>

						<m:table id="logcasSrchCrTb5">
							<m:tr id="logcasSrchCrTr5">
								<m:td id="logcasSrchCrTd6">
									<h:outputLabel for="corrInqEntityType" id="logcasSrchCrOutTxt8" value="#{srCrspd['label.crspd.entityType']}"></h:outputLabel>
									<m:br id="logcasSrchCrOutBr4" />
									
									<%--<h:selectOneMenu id="corrInqEntityType"										value="#{logCaseDataBean.correspondenceSearchCriteriaVO.inqEntityType}"										valueChangeListener="#{logCaseControllerBean.onInqEntityTypeChange}"										onchange="javascript:focusThis(this.id);this.form.submit()">
                                        <f:selectItem itemValue=" " itemLabel="" />
										<f:selectItems value="#{logCaseDataBean.entityTypeVVList}" />
									</h:selectOneMenu> --%>
									
									<h:selectOneMenu id="corrInqEntityType"
										value="#{logCaseDataBean.correspondenceSearchCriteriaVO.inqEntityType}"
										valueChangeListener="#{logCaseControllerBean.onInqEntityTypeChange}"
										onchange="javascript:flagWarn=false;focusThis(this.id);" immediate="true">
                                        <f:selectItem itemValue=" " itemLabel="" />
										<f:selectItems value="#{logCaseDataBean.entityTypeVVList}" />
									</h:selectOneMenu>
									<hx:behavior event="onchange" behaviorAction="get;" targetAction="groupCr2;" target="corrInqEntityType"></hx:behavior>
									<m:br/>
									<h:message for="corrInqEntityType" styleClass="errorMessage" id="logCaseSearchCrrinqTypeMsgId"/>
								</m:td>
							</m:tr>

							<m:tr id="logcasSrchCrTr6">
								<m:td id="logcasSrchCrTd7">
								 	<h:outputLabel for="corrInqEntityIDType" id="logcasSrchCrOutTxt9" value="#{srCrspd['label.crspd.entityIDType']}"></h:outputLabel>
									<m:br id="logcasSrchCrOutBr5" />
									<h:panelGroup id="groupCr2">
									<h:selectOneMenu id="corrInqEntityIDType"										value="#{logCaseDataBean.correspondenceSearchCriteriaVO.inqEntityIDType}">
										<f:selectItem itemValue=" " itemLabel="" />
										<f:selectItems
											value="#{logCaseDataBean.inqEntityIDTypeList}" />
									</h:selectOneMenu>
									</h:panelGroup>
									<hx:ajaxRefreshSubmit id="CorrInqSearchEntityIDTypeRefresh" target="groupCr2"></hx:ajaxRefreshSubmit>
									<m:br/>
									<h:message for="corrInqEntityIDType" styleClass="errorMessage" id="logCaseSearchCrrinqIDTypeMsgId"/>
						        </m:td>
							</m:tr>

							<m:tr id="logcasSrchCrTr7">
								<m:td id="logcasSrchCrTd8">
									<h:outputLabel for="corrInqEntityID" id="logcasSrchCrOutTxt10" value="#{srCrspd['label.crspd.entityID']}"></h:outputLabel>
									<m:br id="logcasSrchCrOutBr6" />
									<h:inputText id="corrInqEntityID" size="10" maxlength="20" onchange="javascript:flagWarn=false;" onkeypress="getSearchentity(event);"										value="#{logCaseDataBean.correspondenceSearchCriteriaVO.inqEntityID}"></h:inputText>
									<h:outputText id="logcasSrchCrOutTxt11" escape="false"										value="#{ref['label.ref.singleSpace']}" />
									<%--Modified for the defect ESPRD00849890---%>										
									<t:commandButton	styleClass="formBtn"		action="#{logCaseControllerBean.doCorrSearchInqEntityAction}"				onmousedown="javascript:flagWarn=false;"			value="#{srCrspd['label.searchCase.srchEntity']}"	id="corrsearchentity3">
										<f:param id="assSrchCorrEntSrch1" name="SrchCorrEntSrch_ACTION"
											value="SrchCorrEntSrch_SOURCE_ACTION" />
									</t:commandButton>
									<m:br/>
									<h:message for="corrInqEntityID" styleClass="errorMessage" id="logCaseSearchCrrinqIDMsgId"/>
								</m:td>
							</m:tr>
						</m:table>
					</m:section>
				</m:td>
        
						
				<m:td id="logcasSrchCrTd9">
					<m:table  id="logcasSrchCrTb2" cellspacing="5">
						<m:tr id="logcasSrchCrTr8">
							<m:td id="logcasSrchCrTd10">
								<h:outputLabel for="CRN" id="logcasSrchCrOutTxt12" value="#{srCrspd['label.crspd.crn']}"></h:outputLabel>
								<m:br id="logcasSrchCrOutBr7" />

								<h:inputText id="CRN" size="15" maxlength="10" onchange="javascript:flagWarn=false;" onkeypress="getSearch(event);"									value="#{logCaseDataBean.correspondenceSearchCriteriaVO.crn}"></h:inputText>
								<m:br/>
								<h:message for="CRN" styleClass="errorMessage" id="logCaseSearchCrrCrnMsgId"/>
							</m:td>

							<m:td id="logcasSrchCrTd11">
								<h:outputLabel for="statusList" id="logcasSrchCrOutTxt13" value="#{srCrspd['label.crspd.status']}"></h:outputLabel>
								<m:br id="logcasSrchCrOutBr8" />
								<h:selectOneMenu id="statusList" onkeypress="getSearch(event);"									value="#{logCaseDataBean.correspondenceSearchCriteriaVO.status}">
									<f:selectItem itemValue=" " itemLabel="" />
									<f:selectItems
										value="#{logCaseDataBean.statusVVList}" />
								</h:selectOneMenu>
							</m:td>
							<m:td id="logcasSrchCrTd12">
								<h:outputLabel for="category" id="logcasSrchCrOutTxt14" value="#{srCrspd['label.crspd.category']}"></h:outputLabel>
								<m:br id="logcasSrchCrOutBr9" />
								<%--<h:selectOneMenu id="category"									value="#{logCaseDataBean.correspondenceSearchCriteriaVO.category}"									valueChangeListener="#{logCaseControllerBean.onCategoryChange}"									onchange="javascript:focusThis(this.id);flagWarn=false; this.form.submit()" immediate="true">
									<f:selectItem itemValue=" " itemLabel="" />
									<f:selectItems
										value="#{logCaseDataBean.categoryList}" />
								</h:selectOneMenu>--%>
								
								<h:selectOneMenu id="category"									value="#{logCaseDataBean.correspondenceSearchCriteriaVO.category}"									valueChangeListener="#{logCaseControllerBean.onCategoryChange}"									onchange="javascript:focusThis(this.id);flagWarn=false;" immediate="true">
									<f:selectItem itemValue=" " itemLabel="" />
									<f:selectItems
										value="#{logCaseDataBean.categoryList}" />
								</h:selectOneMenu>
								<hx:behavior event="onchange" behaviorAction="get" targetAction="subjectCodeForCorrpanid" target="category"></hx:behavior>
							</m:td>
						</m:tr>

						<m:tr id="logcasSrchCrTr9">
							<m:td id="logcasSrchCrTd13">
								<h:outputLabel for="priority" id="logcasSrchCrOutTxt15" value="#{srCrspd['label.crspd.priority']}"></h:outputLabel>
								<m:br id="logcasSrchCrOutBr10" />
								<h:selectOneMenu id="priority" onkeypress="getSearch(event);"									value="#{logCaseDataBean.correspondenceSearchCriteriaVO.priority}">
									<f:selectItem itemValue=" " itemLabel="" />
									<f:selectItems
										value="#{logCaseDataBean.priorityVVList}" />
								</h:selectOneMenu>
							</m:td>
							<m:td id="logcasSrchCrTd14">
							<m:div style="width:145px;">
							<h:outputLabel for="StsDt" id="logcasSrchCrOutTxt16" value="#{srCrspd['label.crspd.statusDate']}"></h:outputLabel>
								<m:br id="logcasSrchCrOutBr11" />
								<m:inputCalendar monthYearRowClass="yearMonthHeader"
									weekRowClass="weekHeader" id="StsDt"
									popupDateFormat="MM/dd/yyyy"
									currentDayCellClass="currentDayCell" renderAsPopup="true"
									addResources="true" size="10" maxlength="10"
									renderPopupButtonAsImage="true"
									value="#{logCaseDataBean.correspondenceSearchCriteriaVO.statusDate}">
								</m:inputCalendar>
								<m:br id="STSDTBRK"/>
								<h:message id="PRGCMGTMSTSDT" for="StsDt" styleClass="errorMessage" />
								</m:div>
							</m:td>
						</m:tr>
					</m:table>
				</m:td>
			</m:tr>
		</m:table>
		
		<m:div id="logcasSrchCrDiv2">
			<h:graphicImage id="PROVIDERGI20120731164811304" styleClass="blankImage" width="1" height="5" alt="" url="/images/x.gif"/>
		</m:div>
		<m:div id="logcasSrchCrDiv3">
			<h:graphicImage id="PROVIDERGI20120731164811305" styleClass="blankImage" width="1" height="5" alt="" url="/images/x.gif"/>
		</m:div>
		<m:div id="t1_ClaimData">
			<f:subview id="advancedCrspdSrchSubview">

				<t:htmlTag value="h4">
				
					<t:commandLink						action="#{logCaseControllerBean.getAdvOptionsPlus}" onmousedown="javascript:flagWarn=false;focusPage('caseAscCrspondenceSearch');"						id="plusid" styleClass="plus" rendered="#{ not logCaseDataBean.advOptionsFl}">
						<h:outputText id="adv_Crspd_SearchPlus"							value="Advanced Search Options"  >
						</h:outputText>
						
				 </t:commandLink>

				<t:commandLink						action="#{logCaseControllerBean.getAdvOptionsMinus}" onmousedown="javascript:flagWarn=false;focusPage('caseAscCrspondenceSearch');"						id="minusid" styleClass="minus" rendered="#{logCaseDataBean.advOptionsFl}">
						<h:outputText id="adv_Crspd_SearchMinus"							value="Advanced Search Options"  >
						</h:outputText>						
				 </t:commandLink>
				
				<h:inputHidden id="advancedOptionsHidden"							value="#{logCaseDataBean.advancedOptionsHidden}"></h:inputHidden>

				</t:htmlTag>
				
				<m:div id="srchCrspd_div_advcSrchOptns" rendered="#{logCaseDataBean.advOptionsFl}">
					<m:table  id="logcasSrchCrTb3" width="100%">
						<m:tr id="logcasSrchCrTr10">
							<m:td id="logcasSrchCrTd15">
							<h:panelGroup id="advGroup">
								<h:outputLabel for="providerType" id="logcasSrchCrOutTxt17" value="#{srCrspd['label.crspd.providerTypes']}"></h:outputLabel>
								<m:br id="logcasSrchCrOutBr12" />
								<h:selectOneMenu id="providerType"									value="#{logCaseDataBean.correspondenceSearchCriteriaVO.advSearchCriteria.providerType}"									onchange="javascript:focusThis(this.id);flagWarn=false;">
									<f:selectItem itemValue=" " itemLabel="" />
									<f:selectItems
										value="#{logCaseDataBean.provTypeVVList}" />
								</h:selectOneMenu>
							</h:panelGroup> 
							<hx:ajaxRefreshSubmit id="providerTypeRefresh" target="advGroup"></hx:ajaxRefreshSubmit>	
							</m:td>
							<m:td id="logcasSrchCrTd16">
								<h:outputLabel for="PRGCMGTIT1" id="payerID"									value="#{srCrspd['label.crspd.payerID']}"></h:outputLabel>
								<m:br id="logcasSrchCrOutBr13" />
								<h:inputText id="PRGCMGTIT1" size="15" maxlength="15"									value="#{logCaseDataBean.correspondenceSearchCriteriaVO.advSearchCriteria.payerID}"></h:inputText>
								<m:br id="payerIDBR"/>
								<h:message id="payerIDErrMesg" for="PRGCMGTIT1" styleClass="errorMessage" />
							</m:td>
							<m:td id="logcasSrchCrTd17">
								<h:outputLabel for="createdBy" id="logcasSrchCrOutTxt18" value="#{srCrspd['label.crspd.createdby']}"></h:outputLabel>
								<m:br id="logcasSrchCrOutBr14" />
								<h:selectOneMenu id="createdBy"									value="#{logCaseDataBean.correspondenceSearchCriteriaVO.advSearchCriteria.createdBY}"									onchange="javascript:focusThis(this.id);flagWarn=false;">
									<f:selectItem itemValue=" " itemLabel="" />
									<f:selectItems
										value="#{logCaseDataBean.createdByList}" />
								</h:selectOneMenu>
							</m:td>
							<m:td id="logcasSrchCrTd18">
								<h:outputLabel for="subject" id="logcasSrchCrOutTxt19" value="#{srCrspd['label.crspd.subject']}"></h:outputLabel>
								<m:br id="logcasSrchCrOutBr15" />
								<h:panelGroup id="subjectCodeForCorrpanid">
								<h:selectOneMenu id="subject"									value="#{logCaseDataBean.correspondenceSearchCriteriaVO.advSearchCriteria.subjectCode}"									onchange="javascript:focusThis(this.id);flagWarn=false;" immediate="true">
									<f:selectItem itemValue=" " itemLabel="" />
									<f:selectItems
										value="#{logCaseDataBean.subjectVVList}" />
								</h:selectOneMenu>
								</h:panelGroup>
								<hx:ajaxRefreshSubmit id="ajaRefSubjCodeCorr" target="subjectCodeForCorrpanid" ></hx:ajaxRefreshSubmit>
							</m:td>
						</m:tr>
						<m:tr id="logcasSrchCrTr11">
							<m:td id="logcasSrchCrTd19">
						
								<h:outputLabel for="reportingUnit" id="logcasSrchCrOutTxt20" value="#{srCrspd['label.crspd.reportingUnit']}"></h:outputLabel>
								<m:br id="logcasSrchCrOutBr16" />
								<h:selectOneMenu id="reportingUnit"									value="#{logCaseDataBean.correspondenceSearchCriteriaVO.advSearchCriteria.reportingUnit}"																		onchange="javascript:focusThis(this.id);flagWarn=false;">
									<f:selectItem itemValue=" " itemLabel="" />
									<f:selectItems
										value="#{logCaseDataBean.listOfRepUnits}" />
								</h:selectOneMenu>
							
							 
							</m:td>
							<m:td id="logcasSrchCrTd20">
								<h:outputLabel for="PRGCMGTIT2" id="contact"									value="#{srCrspd['label.crspd.contact']}"></h:outputLabel>
								<m:br id="logcasSrchCrOutBr17" />
								<h:inputText id="PRGCMGTIT2" size="15"									value="#{logCaseDataBean.correspondenceSearchCriteriaVO.advSearchCriteria.contact}"></h:inputText>
								<m:br id="contactBRID"/>
								<h:message id="contactErrMesg" for="PRGCMGTIT2" styleClass="errorMessage" />
							</m:td>
							<m:td id="logcasSrchCrTd21">
								<h:outputLabel for="source" id="logcasSrchCrOutTxt21" value="#{srCrspd['label.crspd.source']}"></h:outputLabel>
								<m:br id="logcasSrchCrOutBr18" />
								<h:selectOneMenu id="source"									value="#{logCaseDataBean.correspondenceSearchCriteriaVO.advSearchCriteria.source}"									onchange="javascript:focusThis(this.id);flagWarn=false;">
									<f:selectItem itemValue=" " itemLabel="" />
									<f:selectItems
										value="#{logCaseDataBean.sourceVVList}" />
								</h:selectOneMenu>
							</m:td>
							<m:td id="logcasSrchCrTd22">
								<h:outputLabel for="resoln" id="logcasSrchCrOutTxt22" value="#{srCrspd['label.crspd.resolutions']}"></h:outputLabel>
								<m:br id="logcasSrchCrOutBr19" />
								<h:selectOneMenu id="resoln"									value="#{logCaseDataBean.correspondenceSearchCriteriaVO.advSearchCriteria.resolutionCode}"									onchange="javascript:focusThis(this.id);flagWarn=false;">
									<f:selectItem itemValue=" " itemLabel="" />
									<f:selectItems
										value="#{logCaseDataBean.resolnVVList}" />
								</h:selectOneMenu>
							</m:td>
						</m:tr>
						<m:tr id="logcasSrchCrTr12">
							<m:td id="logcasSrchCrTd23">
							
							 	<h:outputLabel for="businessUnit" id="logcasSrchCrOutTxt23" value="#{srCrspd['label.crspd.businessUnit']}"></h:outputLabel>
								<m:br id="logcasSrchCrOutB20r" />
								<h:selectOneMenu id="businessUnit"									value="#{logCaseDataBean.correspondenceSearchCriteriaVO.advSearchCriteria.businessUnit}"																		onchange="javascript:focusThis(this.id);flagWarn=false;" >
									<f:selectItem itemValue=" " itemLabel="" />
									<f:selectItems
										value="#{logCaseDataBean.listOfRefBusUnits}" />
								</h:selectOneMenu>
					     
							 
							</m:td>
							<m:td id="logcasSrchCrTd24">
							<m:div style="width:145px;">
								<h:outputLabel id="PRGCMGTOLL322" for="createdFrm" value="#{srCrspd['label.crspd.createdFrom']}"></h:outputLabel>
								<m:br id="logcasSrchCrOutBr21" />
								<m:inputCalendar monthYearRowClass="yearMonthHeader"
									weekRowClass="weekHeader" id="createdFrm"
									popupDateFormat="MM/dd/yyyy"
									currentDayCellClass="currentDayCell" renderAsPopup="true"
									addResources="true" size="10" maxlength="10"
									renderPopupButtonAsImage="true"
									value="#{logCaseDataBean.correspondenceSearchCriteriaVO.advSearchCriteria.createdFromDate}"
									onmouseover="javascript:flagWarn=false;" 
									onmouseout="javascript:flagWarn=false;">
								</m:inputCalendar>
								<m:br id="logcasSrchCrOutBr22" />
								<h:message id="PRGCMGTM133" for="createdFrm" showDetail="true" style="color: red" />
							</m:div>
							</m:td>
							<m:td id="logcasSrchCrTd25">
						
								<h:outputLabel for="assignedTo" id="logcasSrchCrOutTxt24" value="#{srCrspd['label.crspd.assignedTo']}"></h:outputLabel>
								<m:br id="logcasSrchCrOutBr23" />
								<h:selectOneMenu id="assignedTo"									value="#{logCaseDataBean.correspondenceSearchCriteriaVO.advSearchCriteria.assignedTo}"									onchange="javascript:focusThis(this.id);flagWarn=false;">
									<f:selectItem itemValue=" " itemLabel="" />
									<f:selectItems
										value="#{logCaseDataBean.assignedToList}" />
								</h:selectOneMenu>
								
							</m:td>
							<m:td id="logcasSrchCrTd26" colspan="2">
								<h:outputLabel for="lob" id="logcasSrchCrOutTxt25" value="#{srCrspd['label.crspd.lob']}"></h:outputLabel>
								<m:br id="logcasSrchCrOutBr24" />
								<h:selectOneMenu id="lob"									value="#{logCaseDataBean.correspondenceSearchCriteriaVO.advSearchCriteria.lineOfBusinessGroup}"																		onchange="javascript:focusThis(this.id);flagWarn=false;">
									<f:selectItem itemValue=" " itemLabel="" />
									<f:selectItems
										value="#{logCaseDataBean.lobVVList}" />
								</h:selectOneMenu>
								 
								<m:br id="logcasSrchCrOutBr25" />
							</m:td>
						</m:tr>
						<m:tr id="logcasSrchCrTr13">
							<m:td id="logcasSrchCrTd27">
						
								<h:outputLabel for="department" id="logcasSrchCrOutTxt26" value="#{srCrspd['label.crspd.workunits']}"></h:outputLabel>
								<m:br id="logcasSrchCrOutBr26" />
								<h:selectOneMenu id="department"									value="#{logCaseDataBean.correspondenceSearchCriteriaVO.advSearchCriteria.department}"																		onchange="javascript:focusThis(this.id);flagWarn=false;" >
									<f:selectItem itemValue=" " itemLabel="" />
									<f:selectItems
										value="#{logCaseDataBean.listOfRefDeptUnits}" />
								</h:selectOneMenu>
								 
								
							 
							</m:td>
							<m:td id="logcasSrchCrTd28">
							<m:div style="width:145px;">
								<h:outputLabel id="PRGCMGTOLL323" for="createdTo" value="#{srCrspd['label.crspd.createdTo']}">
								</h:outputLabel>								
								<m:br id="logcasSrchCrOutBr27" />
								<m:inputCalendar monthYearRowClass="yearMonthHeader"
									weekRowClass="weekHeader" id="createdTo"
									popupDateFormat="MM/dd/yyyy"
									currentDayCellClass="currentDayCell" renderAsPopup="true"
									addResources="true" size="10" maxlength="10"
									renderPopupButtonAsImage="true"
									value="#{logCaseDataBean.correspondenceSearchCriteriaVO.advSearchCriteria.createdToDate}"
									onmouseover="javascript:flagWarn=false;" 
									onmouseout="javascript:flagWarn=false;">
								</m:inputCalendar>
								<m:br id="logcasSrchCrOutBr28"/>
								<h:message id="PRGCMGTM134" for="createdTo" showDetail="true" style="color: red" />
							</m:div>
							</m:td>
							<m:td id="logcasSrchCrTd29">
								<h:outputLabel for="PRGCMGTSOR2" id="responseExists"									value="#{srCrspd['label.crspd.responseExists']}"></h:outputLabel>
								<h:selectOneRadio id="PRGCMGTSOR2"									value="#{logCaseDataBean.correspondenceSearchCriteriaVO.advSearchCriteria.responseExistIndicator}">
									<f:selectItem itemLabel="Yes" itemValue="Y" />
									<f:selectItem itemLabel="No" itemValue="N" />
								</h:selectOneRadio>
							</m:td>
						</m:tr>
					</m:table>
				</m:div>
				
			</f:subview>
		</m:div>
        
		
		<m:div id="logcasSrchCrDiv6" align="right">
			<h:commandButton styleClass="formBtn" value="Search" id="searchAssCr"				action="#{logCaseControllerBean.getCorrespondence}" 				onmousedown="javascript:flagWarn=false;focusPage('caseAscCrspondenceSearch');"/>


			<h:outputText id="logcasSrchCrOutTxt27" escape="false"	value="#{ref['label.ref.singleSpace']}" />
			<h:commandButton id="logcasSrchCrOutButton1" styleClass="formBtn" value="Reset"				action="#{logCaseControllerBean.resetCorrespondence}" 				onmousedown="javascript:flagWarn=false;focusPage('caseAscCrspondenceSearch');"/>
			<h:outputText id="logcasSrchCrOutTxt28" escape="false"	value="#{ref['label.ref.singleSpace']}" />
		</m:div>
        
	</m:pod>

</m:div>
