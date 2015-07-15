<%--
  
  Portlet 8.0 Migration Activity: h:commandButton with param is not supported in portal 8.0, replacing with myfaces t:commandButton tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<portlet:defineObjects />


<m:table width="100%" cellspacing="5" id="mainCaseSearchTab1Id">
	<m:tr id="mainCaseSearchTab1R1Id">
		<m:td id="mainCaseSearchTab1R1C1Id">
			<m:section id="mainCaseSearchSec1Id">
				<m:legend id="mainCaseSearchLeg1Id">
					<h:outputText value="#{msg1['label.searchCase.caseRegarding']}"  id="mainCaseSearchOutTxt1Id"						style="font-size:11px;font-family:arial;" />
				</m:legend>

				<m:table id="mainCaseSearchTab2Id">
					<m:tr id="mainCaseSearchTab2R1Id">
						<m:td id="mainCaseSearchTab2R1C1Id">
							<h:outputLabel for="CaseSearchEntityType" value="#{msg1['label.searchCase.entityType']}" id="mainCaseSearchOutTxt2Id"								style="font-size:11px;font-family:arial;"></h:outputLabel>
							<m:br id="mainCaseSearchBr1Id"/>
							<h:selectOneMenu id="CaseSearchEntityType"								value="#{caseSearchDataBean.caseRecordSearchCriteriaVO.entityType}"								valueChangeListener="#{caseSearchControllerBean.onEntityTypeChange}"								style="font-size:11px;font-family:arial;" immediate="true">
								<f:selectItems value="#{caseSearchDataBean.entityType}"  id="mainCaseSearchSelItm1Id"/>
							</h:selectOneMenu>
							<hx:behavior event="onchange" behaviorAction="get;"
								target="CaseSearchEntityType" targetAction="PanelGrpCase"></hx:behavior>
							<m:br id="mainCaseSearchTypeBrId"/>
							<h:message for="CaseSearchEntityType" styleClass="errorMessage" id="mainCaseSearcTypehMsgId"/>
						</m:td>
					</m:tr>

					<m:tr id="mainCaseSearchTab2R2Id">

						<m:td id="mainCaseSearchTab2R2C1Id">
							<h:panelGroup id="PanelGrpCase">
								<h:outputLabel for="CaseSearchEntityIDType" value="#{msg1['label.searchCase.entityIdType']}" id="mainCaseSearchOutTxt3Id"									style="font-size:11px;font-family:arial;"></h:outputLabel>
								<m:br id="mainCaseSearchBr2Id"/>
								<h:selectOneMenu id="CaseSearchEntityIDType"									value="#{caseSearchDataBean.caseRecordSearchCriteriaVO.entityIDType}"									style="font-size:11px;font-family:arial;">
									<f:selectItem itemValue="" itemLabel="Please Select One" id="mainCaseSearchSelItm2Id"/>
									<f:selectItems value="#{caseSearchDataBean.entityIDTypeList}" id="mainCaseSearchSelItm3Id"/>
								</h:selectOneMenu>
							</h:panelGroup>
							<hx:ajaxRefreshSubmit id="ajaxRefreshRequestCase"
								target="PanelGrpCase"></hx:ajaxRefreshSubmit>
							<m:br id="mainCaseSearchIDTypeBrId"/>
							<h:message for="CaseSearchEntityIDType" styleClass="errorMessage" id="mainCaseSearcIDTypehMsgId"/>
						</m:td>

					</m:tr>

					<m:tr id="mainCaseSearchTab2R3Id">
						<m:td id="mainCaseSearchTab2R3C1Id">
							<h:outputLabel for="EntityID" value="#{msg1['label.searchCase.entityID']}" id="mainCaseSearchOutTxt4Id"								style="font-size:11px;font-family:arial;"></h:outputLabel>
							<m:br id="mainCaseSearchBr3Id"/>
							<h:inputText id="EntityID" size="10"								value="#{caseSearchDataBean.caseRecordSearchCriteriaVO.entityId}"								onkeyup="this.value=this.value.toUpperCase();"								onblur="this.value=this.value.toUpperCase();"								style="font-size:10px;font-family:arial;" />
							<h:outputText escape="false" id="mainCaseSearchOutTxt5Id"								value="#{ref['label.ref.singleSpace']}" />



							<t:commandButton								action="#{caseSearchControllerBean.doCaseSearchEntityAction}"	
							onmousedown="javascript:document.getElementsByName('com.ibm.portal.propertybroker.action')[0].value='';"
								styleClass="formBtn" value="#{msg1['label.searchCase.srchEntity']}"							id="createLetterBtn">
								<%--h:outputText id="displyCreateBtTextRender" 
									value="#{msg1['label.searchCase.srchEntity']}"></h:outputText--%>
								
								<f:param id="SrchCaseEntSrch" name="SrchCaseEntSrch_ACTION"
									value="SrchCaseEntSrch_SOURCE_ACTION"/>
							</t:commandButton>
							<m:br id="mainCaseSearchBr4Id"/>
							<h:message for="EntityID" styleClass="errorMessage" id="mainCaseSearchMsg2Id"/>
						</m:td>
					</m:tr>
				</m:table>
			</m:section>

		</m:td>
		<m:td id="mainCaseSearchTab1R1C2Id">


			<m:section id="mainCaseSearchTab1R1C2Sec1Id">
				<m:legend styleClass="otherbg" id="mainCaseSearchTab1R1C2Sec1Leg1Id">
					<h:outputText value="#{msg1['label.searchCase.addtionalCaseEnts']}" id="mainCaseSearchOutTxt6Id"						style="font-size:11px;font-family:arial;"></h:outputText>
				</m:legend>

				<m:table id="mainCaseSearchTab3Id">
					<m:tr id="mainCaseSearchTab3R1Id">
						<m:td id="mainCaseSearchTab3R1C1Id">
							<h:outputLabel for="AddCaseSearchEntityType" value="#{msg1['label.searchCase.entityType']}" id="mainCaseSearchOutTxt7Id"								style="font-size:11px;font-family:arial;"></h:outputLabel>
							<m:br id="mainCaseSearchBr5Id"/>

							<h:selectOneMenu id="AddCaseSearchEntityType"								value="#{caseSearchDataBean.caseRecordSearchCriteriaVO.additionalEntityType}"								valueChangeListener="#{caseSearchControllerBean.onAdditionalEntityTypeChange}"								style="font-size:11px;font-family:arial;" immediate="true">
								<f:selectItems value="#{caseSearchDataBean.entityType}" id="mainCaseSearchSelItm4Id"/>
							</h:selectOneMenu>
							<hx:behavior event="onchange" behaviorAction="get;"
								target="AddCaseSearchEntityType"
								targetAction="PanelGrpCaseAddnl"></hx:behavior>
							<m:br id="AddCaseSearchTypeBrId"/>
							<h:message for="AddCaseSearchEntityType" styleClass="errorMessage" id="AddCaseSearcTypehMsgId"/>
						</m:td>
					</m:tr>
					<m:tr id="mainCaseSearchTab3R2Id">
						<m:td id="mainCaseSearchTab3R2C1Id">
							<h:panelGroup id="PanelGrpCaseAddnl">
								<h:outputLabel for="CaseSearchEntityIDType2" value="#{msg1['label.searchCase.entityIdType']}" id="mainCaseSearchOutTxt8Id"									style="font-size:11px;font-family:arial;"></h:outputLabel>
								<m:br id="mainCaseSearchBr6Id"/>
								<h:selectOneMenu id="CaseSearchEntityIDType2"									value="#{caseSearchDataBean.caseRecordSearchCriteriaVO.additionalEntityIDType}"									style="font-size:11px;font-family:arial;">
									<f:selectItem itemValue="" itemLabel="Please Select One" id="mainCaseSearchSelItm5Id"/>
									<f:selectItems
										value="#{caseSearchDataBean.additionalEntityIDTypeList}" id="mainCaseSearchSelItm6Id"/>
								</h:selectOneMenu>
							</h:panelGroup>
							<hx:ajaxRefreshSubmit id="ajaxRefreshSubmitCaseAddn"
								target="PanelGrpCaseAddnl"></hx:ajaxRefreshSubmit>
							<m:br id="AddCaseSearchIDTypeBrId"/>
							<h:message for="CaseSearchEntityIDType2" styleClass="errorMessage" id="AddCaseSearcIDTypehMsgId"/>
						</m:td>
					</m:tr>
					<m:tr id="mainCaseSearchTab3R3Id">
						<m:td id="mainCaseSearchTab3R3C1Id">
							<h:outputLabel for="AddEntityID" value="#{msg1['label.searchCase.entityID']}" id="mainCaseSearchOutTxt10Id"								style="font-size:11px;font-family:arial;"></h:outputLabel>
							<m:br id="mainCaseSearchBr7Id"/>
							<h:inputText id="AddEntityID" size="10"								value="#{caseSearchDataBean.caseRecordSearchCriteriaVO.additionalEntityID}"								onkeyup="this.value=this.value.toUpperCase();"								onblur="this.value=this.value.toUpperCase();"								style="font-size:10px;font-family:arial;" />
							<h:outputText escape="false" id="mainCaseSearchOutTxt11Id"								value="#{ref['label.ref.singleSpace']}" />


							<t:commandButton								action="#{caseSearchControllerBean.doAdditionalCaseSearchEntityAction}"	
							onmousedown="javascript:document.getElementsByName('com.ibm.portal.propertybroker.action')[0].value='';"
							styleClass="formBtn"		value="#{msg1['label.searchCase.srchEntity']}"						id="createLetterBtn1">
								<%--h:outputText id="displyCreateBtTextRender1" 
									value="#{msg1['label.searchCase.srchEntity']}"></h:outputText--%>
								
								<f:param id="SrchCaseEntSrch1" name="SrchCaseEntSrch_ACTION"
									value="SrchCaseEntSrch_SOURCE_ACTION" /><%--UC-PGM-CRM-033_ESPRD00624909_09jun2011 --%>
							</t:commandButton>
							<m:br id="mainCaseSearchBr8Id"/>
							<h:message for="AddEntityID" styleClass="errorMessage" id="mainCaseSearchmsg1Id"/>
						</m:td>
					</m:tr>
				</m:table>
			</m:section>
		</m:td>
<m:td id="incMainCaseSearchEmptySpace"><f:verbatim>&nbsp;</f:verbatim></m:td><%--ESPRD00523833_UC-PGM-CRM-020_16SEP2010 --%>
		<m:td id="mainCaseSearchTab1R1C3Id">
			<m:table width="100%" cellspacing="3" id="mainCaseSearchTab4Id">
				<m:tr id="mainCaseSearchTab4R1Id">
					<m:td id="mainCaseSearchTab4R1C1Id">
						<h:outputLabel for="CaseRecordNumber" value="#{msg1['label.searchCase.caseRecordNumber']}" id="mainCaseSearchOutTxt13Id"							style="font-size:11px;font-family:arial;" />
						<m:br id="mainCaseSearchBr9Id"/>
						<h:inputText id="CaseRecordNumber" size="10" maxlength="80"							value="#{caseSearchDataBean.caseRecordSearchCriteriaVO.caseRecordNumber}"							style="font-size:11px;font-family:arial;" />
						<m:br id="mainCaseSearchBr10Id"/>
						<h:message for="CaseRecordNumber" styleClass="errorMessage" id="mainCaseSearchCrnmsg1Id"/>
					</m:td>
					<m:td id="mainCaseSearchTab4R1C2Id">
						<h:outputLabel for="caseTitle" value="#{msg1['label.searchCase.caseTitle']}" id="mainCaseSearchOutTxt14Id"							style="font-size:11px;font-family:arial;" />
						<m:br id="mainCaseSearchBr11Id"/>
						<h:inputText id="caseTitle" size="10" maxlength="80"							value="#{caseSearchDataBean.caseRecordSearchCriteriaVO.caseTitle}"							style="font-size:11px;font-family:arial;" />
						<m:br id="mainCaseSearchBr22Id"/>
						<h:message for="caseTitle" styleClass="errorMessage" id="mainCaseSearchCaseTitlemsg1Id"/>
					</m:td>
				</m:tr>
				<%--Modified for CR ESPRD00807028 --%>
				<m:tr id="mainCaseSearchTab4R2Id">
					<m:td id="mainCaseSearchTab4R1C3Id" colspan="3">
						<h:outputLabel for="AdvCreatedBy" value="#{msg1['label.searchCase.createdBy']}" id="mainCaseSearchOutTxt15Id"							style="font-size:11px;font-family:arial;" />
						<m:br id="mainCaseSearchBr12Id"/>
						<%-- modified for defect ESPRD00336198--%>

						<h:selectOneMenu id="AdvCreatedBy"							value="#{caseSearchDataBean.caseRecordSearchCriteriaVO.createdBy}"							style="font-size:11px;font-family:arial;">
							<f:selectItems value="#{caseSearchDataBean.createdBy}" id="mainCaseSearchSelItm7Id"/>
						</h:selectOneMenu>
						<%--EOF defect ESPRD00336198 --%>
						<m:br id="mainCaseSearchBr13Id"/>
					</m:td>
				</m:tr>
				<%--Modified for CR ESPRD00807028 --%>
				<m:tr id="mainCaseSearchTab4R3Id">
					<m:td id="mainCaseSearchTab4R1C4Id" colspan="3">
						<h:outputLabel for="CaseSearchAssignedTo" value="#{msg1['label.searchCase.assignedTo']}" id="mainCaseSearchOutTxt16Id"							style="font-size:11px;font-family:arial;" />
						<m:br id="mainCaseSearchBr14Id"/>
						<h:selectOneMenu id="CaseSearchAssignedTo"							value="#{caseSearchDataBean.caseRecordSearchCriteriaVO.assignedTo}"							style="font-size:11px;font-family:arial;" >
							<f:selectItems value="#{caseSearchDataBean.assignedTo}" id="mainCaseSearchSelItm8Id"/>
						</h:selectOneMenu>
						<m:br id="mainCaseSearchBr15Id"/>
					</m:td>
				</m:tr>
				<m:tr id="mainCaseSearchTab4R4Id">
					<m:td id="mainCaseSearchTab4R2C1Id">
						<h:outputLabel for="CaseSearchStatus" value="#{msg1['label.searchCase.status']}" id="mainCaseSearchOutTxt17Id"							style="font-size:11px;font-family:arial;" />
						<m:br id="mainCaseSearchBr16Id"/>
						<h:selectOneMenu id="CaseSearchStatus"							value="#{caseSearchDataBean.caseRecordSearchCriteriaVO.status}"							style="font-size:11px;font-family:arial;">

							<f:selectItems value="#{caseSearchDataBean.status}" id="mainCaseSearchSelItm9Id"/>
						</h:selectOneMenu>
						<m:br id="mainCaseSearchBr17Id"/>
					</m:td>
					<m:td id="mainCaseSearchTab4R2C2Id">
						<h:outputLabel for="CaseSearchStatusDate" value="#{msg1['label.searchCase.statusDate']}" id="mainCaseSearchOutTxt18Id"							style="font-size:11px;font-family:arial;" />
						<m:br id="mainCaseSearchBr18Id"/>
						<m:inputCalendar id="CaseSearchStatusDate" size="10"
							monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
							currentDayCellClass="currentDayCell" renderAsPopup="true"
							addResources="true" popupDateFormat="MM/dd/yyyy"
							renderPopupButtonAsImage="true"
							value="#{caseSearchDataBean.caseRecordSearchCriteriaVO.statusDate}"
							style="font-size:11px;font-family:arial;" />
							<m:br id="mainCaseSearchBr19Id"/>
							<h:message id="PRGCMGTM54" for="CaseSearchStatusDate" styleClass="errorMessage" />
							

					</m:td>
					<m:td id="mainCaseSearchTab4R2C3Id">
						<h:outputLabel for="CaseSearchPriority" value="#{msg1['label.searchCase.priority']}" id="mainCaseSearchOutTxt19Id"							style="font-size:11px;font-family:arial;" />
						<m:br id="mainCaseSearchBr20Id"/>
						<h:selectOneMenu id="CaseSearchPriority"							value="#{caseSearchDataBean.caseRecordSearchCriteriaVO.priority}"							style="font-size:11px;font-family:arial;">

							<f:selectItems value="#{caseSearchDataBean.priority}" id="mainCaseSearchSelItm10Id"/>
						</h:selectOneMenu>
						<m:br id="mainCaseSearchBr21Id"/>
					</m:td>
				</m:tr>

			</m:table>
		</m:td>

	</m:tr>
</m:table>
<m:div id="mainCaseSearchDiv1Id">
	<h:graphicImage styleClass="blankImage" width="1" height="5" alt=""
		url="/images/x.gif" id="mainCaseSearchDiv1Img1Id"/>
</m:div>
<m:div id="mainCaseSearchDiv2Id">
	<h:graphicImage styleClass="blankImage" width="1" height="5" alt=""
		url="/images/x.gif" id="mainCaseSearchDiv2Img1Id"/>
</m:div>
