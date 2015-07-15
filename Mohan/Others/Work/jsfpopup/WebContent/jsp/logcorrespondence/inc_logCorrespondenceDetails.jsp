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
<%@ taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>


<m:div>
	<m:section id="PROVIDERMMS20120731164811359" styleClass="otherbg">
		<m:legend>
			<h:outputText id="PRGCMGTOT793"
				value="#{crspd['label.crspd.crspddetails']}"></h:outputText>
		</m:legend>
		<h:panelGroup id="workUnitGroup">
			<m:table id="PROVIDERMMT20120731164811360" styleClass="viewTable" width="100%">
				<m:tr>
					<m:td>
						<m:div styleClass="padb">
							<h:outputLabel id="PRGCMGTOLL391" for="createdByName">
								<h:outputText value="#{crspd['label.crspd.createdby']}"
									id="createdByName" />
							</h:outputLabel>
							<m:br />
							<h:outputText id="PRGCMGTOT794"
								value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceDetailsVO.createdByName}">
							</h:outputText>
							<h:outputText id="PRGCMGTOT795"
								value="-#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceDetailsVO.createdByUserID}"
								rendered="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceDetailsVO.createdByUserID!=null && CorrespondenceDataBean.correspondenceRecordVO.correspondenceDetailsVO.createdByUserID!=''}">
							</h:outputText>
						</m:div>
					</m:td>
					<m:td>
						<m:div styleClass="padb">
							<h:outputLabel id="PRGCMGTOLL392" for="assignedToWorkUnitName">
								<h:outputText value="#{crspd['label.crspd.assignedto']}"
									id="assignedToWorkUnitName" />
							</h:outputLabel>
							<m:br />
							<h:outputText id="PRGCMGTOT796"
								value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceDetailsVO.assignedToWorkUnitName}">
							</h:outputText>
						</m:div>
					</m:td>
					<m:td>
						<m:div styleClass="padb">
							<h:outputLabel id="PRGCMGTOLL393" for="openDate">
								<h:outputText value="#{crspd['label.crspd.createddate']}"
									id="openDate" />
							</h:outputLabel>
							<m:br />
							<h:outputText id="PRGCMGTOT797"
								value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceDetailsVO.openDate}">
							</h:outputText>
						</m:div>
					</m:td>
					<m:td>
						<m:div styleClass="padb">
							<h:outputLabel id="PRGCMGTOLL394" for="reportingUnitName">
								<h:outputText value="#{crspd['label.crspd.reportingunit']}"
									id="reportingUnitName" />
							</h:outputLabel>
							<m:br />
							<h:outputText id="PRGCMGTOT798"
								value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceDetailsVO.reportingUnitName}">
							</h:outputText>
						</m:div>
					</m:td>
					<m:td>
						<m:div styleClass="padb">
							<h:outputLabel id="PRGCMGTOLL395" for="businessUnitName">
								<h:outputText value="#{crspd['label.crspd.businessunit']}"
									id="businessUnitName" />
							</h:outputLabel>
							<m:br />
							<h:outputText id="PRGCMGTOT799"
								value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceDetailsVO.businessUnitName}">
							</h:outputText>
						</m:div>
					</m:td>
					<m:td>
						<m:div styleClass="padb">
							<m:span styleClass="required">
								<h:outputText id="PRGCMGTOT800"
									value="#{ref['label.ref.reqSymbol']}" />
							</m:span>
							<h:outputLabel id="PRGCMGTOLL396" for="deptName">
								<h:outputText id="deptName11"
									value="#{crspd['label.crspd.workUnit']}" />
							</h:outputLabel>
							<m:br />

							<h:selectOneMenu id="deptName"
								disabled="#{CorrespondenceDataBean.updateMode || CorrespondenceDataBean.autoPopulatedDept || !CorrespondenceDataBean.controlRequired}"
								valueChangeListener="#{CorrespondenceControllerBean.getReportingAndBusinessUnit}"
								value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceDetailsVO.department}">
								<f:selectItem itemValue=" " itemLabel="" />
								<f:selectItems value="#{CorrespondenceDataBean.departmentsList}" />
							</h:selectOneMenu>
							<hx:behavior event="onchange" behaviorAction="get"
								targetAction="workUnitGroup" target="test3"></hx:behavior>
							<m:br />
							<%--rendered attribute is added below for not retaining the error messages on Log Correspondence page for the Defect ESPRD00844471--%>
							<h:message id="PRGCMGTM141" for="deptName" showDetail="true"
								style="color: red"  rendered="#{CorrespondenceDataBean.logCRErrMsgFlag}"/>
						</m:div>
					</m:td>
					<m:br />
				</m:tr>
			</m:table>
		</h:panelGroup>
		<hx:ajaxRefreshSubmit id="workUnitRefreshRequest"
			target="workUnitGroup"></hx:ajaxRefreshSubmit>


		<m:table id="PROVIDERMMT20120731164811361" styleClass="viewTable" width="100%">
			<m:tr>
				<m:td>
					<m:div styleClass="padb">
						<m:span styleClass="required">
							<h:outputText id="PRGCMGTOT801"
								value="#{ref['label.ref.reqSymbol']}" />
						</m:span>
						<h:outputLabel id="PRGCMGTOLL397" for="srcName">
							<h:outputText id="srcName11" value="#{crspd['label.crspd.source']}" />
						</h:outputLabel>
						<m:br />
						<h:selectOneMenu id="srcName"
							disabled="#{CorrespondenceDataBean.crClosed || !CorrespondenceDataBean.controlRequired}"
							value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceDetailsVO.sourceCode}">
							<f:selectItem itemValue=" " itemLabel="" />
							<f:selectItems value="#{CorrespondenceDataBean.sourceVVList}" />
						</h:selectOneMenu>
						<m:br />
						<%--rendered attribute is added below for not retaining the error messages on Log Correspondence page for the Defect ESPRD00844471--%>
						<h:message id="PRGCMGTM142" for="srcName" showDetail="true"
							style="color: red"  rendered="#{CorrespondenceDataBean.logCRErrMsgFlag}"/>
					</m:div>
				</m:td>
				<m:td>
					<m:div styleClass="padb">
						<m:span styleClass="required">
							<h:outputText id="PRGCMGTOT802"
								value="#{ref['label.ref.reqSymbol']}" />
						</m:span>
						<h:outputLabel id="PRGCMGTOLL398" for="catName">
							<h:outputText id="catName11"
								value="#{crspd['label.crspd.category']}" />
						</h:outputLabel>
						<m:br />

						<h:selectOneMenu id="catName"
							disabled="#{CorrespondenceDataBean.updateMode || !CorrespondenceDataBean.controlRequired}"
							onchange="flagWarn=false;focusThis('cat1');this.form.submit();"
							valueChangeListener="#{CorrespondenceControllerBean.getSubjectsForCategory}"
							value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceDetailsVO.categorySK}">
							<f:selectItem itemValue=" " itemLabel="" />
							<f:selectItems value="#{CorrespondenceDataBean.categoryList}" />
						</h:selectOneMenu>

						<m:br />
						<%--rendered attribute is added below for not retaining the error messages on Log Correspondence page for the Defect ESPRD00844471--%>
						<h:message id="PRGCMGTM143" for="catName" showDetail="true"
							style="color: red"  rendered="#{CorrespondenceDataBean.logCRErrMsgFlag}"/>
					</m:div>
				</m:td>
				<m:td>
					<m:div styleClass="padb">
						<m:span styleClass="required">
							<h:outputText id="PRGCMGTOT803"
								value="#{ref['label.ref.reqSymbol']}" />
						</m:span>
						<h:outputLabel id="PRGCMGTOLL399" for="subName">
							<h:outputText id="subName11"
								value="#{crspd['label.crspd.subject']}" />
						</h:outputLabel>
						<m:br />

						<h:selectOneMenu id="subName"
							disabled="#{CorrespondenceDataBean.crClosed || !CorrespondenceDataBean.enableSubjects || !CorrespondenceDataBean.controlRequired}"
							onchange="flagWarn=false;focusThis('test2');this.form.submit();"
							valueChangeListener="#{CorrespondenceControllerBean.setSubjectCallScriptDetails}"
							value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceDetailsVO.subjectCode}">
							<f:selectItem itemValue=" " itemLabel="" />
							<f:selectItems
								value="#{CorrespondenceDataBean.categorySubjectValues}" />
						</h:selectOneMenu>
						<%--<hx:behavior event="onchange" behaviorAction="get" targetAction="logCrspdCallScriptSection" target="test2"></hx:behavior>--%>
						<m:br />
						<%--rendered attribute is added below for not retaining the error messages on Log Correspondence page for the Defect ESPRD00852896--%>
						<h:message id="PRGCMGTM144" for="subName" showDetail="true"
							style="color: red"  rendered="#{CorrespondenceDataBean.logCRErrMsgFlag}"/>
					</m:div>
				</m:td>
			</m:tr>
			<m:tr>
				<m:td>
					<m:div styleClass="padb">
						<m:span styleClass="required">
							<h:outputText id="PRGCMGTOT804"
								value="#{ref['label.ref.reqSymbol']}" />
						</m:span>
						<h:outputLabel id="PRGCMGTOLL400" for="priorityType">
							<h:outputText id="priorityType11"
								value="#{crspd['label.crspd.priority']}" />
						</h:outputLabel>
						<m:br />
						<h:selectOneMenu id="priorityType"
							disabled="#{CorrespondenceDataBean.crClosed || !CorrespondenceDataBean.controlRequired}"
							value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceDetailsVO.priorityCode}">
							<f:selectItem itemValue=" " itemLabel="" />
							<f:selectItems value="#{CorrespondenceDataBean.priorityVVList}" />
						</h:selectOneMenu>
						<m:br />
						<%--rendered attribute is added below for not retaining the error messages on Log Correspondence page for the Defect ESPRD00844471--%>
						<h:message id="PRGCMGTM145" for="priorityType" showDetail="true"
							style="color: red" rendered="#{CorrespondenceDataBean.logCRErrMsgFlag}"/>
					</m:div>
				</m:td>
				<m:td>
					<m:div styleClass="padb">
						<m:span styleClass="required">
							<h:outputText id="PRGCMGTOT805"
								value="#{ref['label.ref.reqSymbol']}" />
						</m:span>
						<h:outputLabel id="PRGCMGTOLL401" for="statusType">
							<h:outputText id="statusType11"
								value="#{crspd['label.crspd.status']}" />
						</h:outputLabel>
						<m:br />


						<h:selectOneMenu id="statusType"
							disabled="#{CorrespondenceDataBean.underReview || CorrespondenceDataBean.crClosed || !CorrespondenceDataBean.controlRequired}"
							valueChangeListener="#{CorrespondenceControllerBean.setStatusDate}"
							value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceDetailsVO.statusCode}">
							<f:selectItem itemValue=" " itemLabel="" />
							<f:selectItems value="#{CorrespondenceDataBean.statValidValues}" />
						</h:selectOneMenu>
						<hx:behavior event="onchange" behaviorAction="get"
							targetAction="statusCodeGroup" target="test41"></hx:behavior>

						<m:br />
						<%--rendered attribute is added below for not retaining the error messages on Log Correspondence page for the Defect ESPRD00844471--%>
						<h:message id="PRGCMGTM146" for="statusType" showDetail="true"
							style="color: red" rendered="#{CorrespondenceDataBean.logCRErrMsgFlag}"/>
					</m:div>
				</m:td>
				<m:td>
					<h:panelGroup id="statusCodeGroup">
						<m:div styleClass="padb">
							<h:outputLabel id="PRGCMGTOLL402" for="statusdate">
								<h:outputText value="#{crspd['label.crspd.statusdate']}"
									id="statusdate" />
							</h:outputLabel>
							<m:br />
							<h:outputText id="PRGCMGTOT806"
								value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceDetailsVO.statusDate}">
							</h:outputText>
						</m:div>
					</h:panelGroup>
					<hx:ajaxRefreshSubmit id="statusCodeRefreshRequest"
						target="statusCodeGroup"></hx:ajaxRefreshSubmit>
				</m:td>
				<m:td>
					<m:div styleClass="padb"
						rendered="#{CorrespondenceDataBean.crClosed}">
						<h:outputLabel id="PRGCMGTOLL403" for="crmDetDaysToClose">
							<h:outputText value="#{crspd['label.crspd.dtc']}"
								id="crmDetDaysToClose" />
						</h:outputLabel>
						<m:br />
						<h:outputText id="PRGCMGTOT807"
							value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceDetailsVO.daysToClose}">
						</h:outputText>
					</m:div>

				</m:td>
				<m:td>
					<m:div styleClass="padb"
						rendered="#{!CorrespondenceDataBean.crClosed}">
						<h:outputLabel id="PRGCMGTOLL404" for="crmDetDaysOpen">
							<h:outputText value="#{crspd['label.crspd.daysOpen']}"
								id="crmDetDaysOpen" />
						</h:outputLabel>
						<m:br />
						<h:outputText id="PRGCMGTOT808"
							value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceDetailsVO.daysOpen}">
						</h:outputText>
					</m:div>

				</m:td>
			</m:tr>
			<m:tr>
				<m:td>
					<m:div styleClass="padb">
						<h:outputLabel id="PRGCMGTOLL405" for="rslnType">
							<h:outputText id="rslnType11"
								value="#{crspd['label.crspd.resolution']}" />
						</h:outputLabel>
						<m:br />
						<h:selectOneMenu id="rslnType"
							disabled="#{CorrespondenceDataBean.crClosed || !CorrespondenceDataBean.controlRequired}"
							value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceDetailsVO.resolutionCode}">
							<f:selectItem itemValue=" " itemLabel="" />
							<f:selectItems value="#{CorrespondenceDataBean.resolnVVList}" />
						</h:selectOneMenu>
					</m:div>
				</m:td>
				<m:td>
					<m:div styleClass="padb">
						<h:outputLabel id="PRGCMGTOLL406" for="lobType">
							<h:outputText id="lobType11" value="#{crspd['label.crspd.lob']}" />
						</h:outputLabel>
						<m:br />
						<h:selectOneMenu id="lobType"
							disabled="#{CorrespondenceDataBean.crClosed || !CorrespondenceDataBean.controlRequired}"
							value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceDetailsVO.anLobCode}">
							<f:selectItem itemValue=" " itemLabel="" />
							<f:selectItems value="#{CorrespondenceDataBean.lobVVList}" />
						</h:selectOneMenu>
					</m:div>
				</m:td>
			</m:tr>
			<m:tr>
				<m:td>
					<m:div styleClass="padb">
						<h:outputLabel id="PRGCMGTOLL407" for="crRecDate"
							value="#{crspd['label.crspd.receivedDate']}">
						</h:outputLabel>
						<m:br />
						<m:inputCalendar monthYearRowClass="yearMonthHeader"
							weekRowClass="weekHeader" id="crRecDate"
							currentDayCellClass="currentDayCell" size="10"
							renderAsPopup="true" addResources="true"
							renderPopupButtonAsImage="true"
							value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceDetailsVO.receivedDate}"
							popupDateFormat="MM/dd/yyyy"
							onmouseover="javascript:flagWarn=false;"
							onmouseout="javascript:flagWarn=false;"
							disabled="#{CorrespondenceDataBean.crClosed || !CorrespondenceDataBean.controlRequired}">
						</m:inputCalendar>
						<m:br />
						<%--rendered attribute is added below for not retaining the error messages on Log Correspondence page for the Defect ESPRD00844471--%>
						<h:message id="PRGCMGTM147" for="crRecDate" showDetail="true"
							style="color: red" rendered="#{CorrespondenceDataBean.logCRErrMsgFlag}"/>
					</m:div>
				</m:td>

				<m:td>
					<m:div styleClass="padb">
						<h:outputLabel id="PRGCMGTOLL408" for="crTCN"
							value="#{crspd['label.crspd.tcn']}">
						</h:outputLabel>
						<m:br />
						<h:inputText id="crTCN"
							disabled="#{CorrespondenceDataBean.crClosed || !CorrespondenceDataBean.controlRequired}"
							size="10"
							value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceDetailsVO.tcn}"
							maxlength="17" />
						<m:br />
						<%--rendered attribute is added below for not retaining the error messages on Log Correspondence page for the Defect ESPRD00844471--%>
						<h:message id="PRGCMGTM148" for="crTCN" showDetail="true"
							style="color: red" rendered="#{CorrespondenceDataBean.logCRErrMsgFlag}"/>
					</m:div>

				</m:td>

				<m:td>
					<m:div styleClass="padb">
						<h:outputLabel id="PRGCMGTOLL409" for="crSANbr"
							value="#{crspd['label.crspd.saNbr']}">
						</h:outputLabel>
						<m:br />
						<h:inputText id="crSANbr"
							disabled="#{CorrespondenceDataBean.crClosed || !CorrespondenceDataBean.controlRequired}"
							size="10"
							value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceDetailsVO.authNumber}"
							maxlength="12" />
					</m:div>
				</m:td>

			</m:tr>

			<m:tr>
				<m:td>
					<m:table id="PROVIDERMMT20120731164811362">
						<m:tr>
							<m:td>

								<h:outputLabel id="PRGCMGTOLL410" for="sar">
									<h:outputText value="#{crspd['label.crspd.sar']}" id="sar" />
								</h:outputLabel>
							</m:td>
							<m:td>
								<%-- <h:graphicImage title="false" styleClass="ind_check_no" width="15" alt="#{crspd['title.crspd.false']}"
							rendered="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceDetailsVO.sprvsrRevwReqdIndicator=='false'}" url="/wps/themes/html/ACSDefault/images/icn_x.gif"/>--%>
								<t:div
									rendered="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceDetailsVO.sprvsrRevwReqdIndicator=='false'}">
									<m:img id="logCorresSupervisorImageID" src="/wps/themes/html/ACSDefault/images/icn_x.gif" alt="#{ref['label.ref.no']}" title="#{ref['label.ref.no']}"></m:img>
								</t:div>

								<h:graphicImage id="PROVIDERGI20120731164811363" title="true" styleClass="ind_check_yes"
									width="15" alt="#{crspd['title.crspd.true']}"
									rendered="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceDetailsVO.sprvsrRevwReqdIndicator=='true'}"
									url="/images/x.gif" />
								<m:br />

							</m:td>
						</m:tr>
					</m:table>
				</m:td>
			</m:tr>
		</m:table>

		<m:br />

		<m:div rendered="#{CorrespondenceDataBean.renderClientServices}">
			<t:htmlTag value="h4">
				<h:outputLabel id="PRGCMGTOLL411" for="clientServices">
					<h:outputText id="clientServices"
						value="#{clientServ['label.clientservices.clientservices']}" />
				</h:outputLabel>
			</t:htmlTag>
			<f:subview id="logCrspdClntServices">
				<jsp:include
					page="/jsp/logcorrespondence/inc_logCorrespondenceClientServices.jsp" />
			</f:subview>
		</m:div>
		<m:br />

		<%-- For Custom Fields  --%>

		<m:div id="crdetailDiv15Id" rendered="#{CorrespondenceDataBean.renderCustomFlds}">
		<t:htmlTag value="h4">
				<h:outputLabel id="PRGCMGTOLL412" for="customFldValues">
					<h:outputText id="customFldValues"
						value="#{crspd['label.crspd.customfields']}" />
				</h:outputLabel>
			</t:htmlTag>
		<m:table width="100%" id="crdetailDiv15Tab1Id">
			<m:tr id="crdetailDiv15Tab1Row1Id">
				<m:td id="crdetailDiv15Tab1Row1Col1Id">
					<m:div id="customFieldDivId">
						<f:subview id="customFieldViewId">
							<jsp:include page="../customField/inc_dynamicCRCustomFields.jsp" />
						</f:subview>
					</m:div>
				</m:td>

			</m:tr>
		</m:table>
		</m:div>

		<h:inputHidden value="placeToFocus" id="focusInqHereAftrAdding" />
		<m:br />
		<m:br />

		<t:htmlTag value="h4">
			<h:outputText id="PRGCMGTOT809"
				value="#{crspd['label.crspd.inquiringabt']}" />
		</t:htmlTag>

		<t:dataTable id="inqAbtDataTable" width="100%" styleClass="dataTable"
			rows="10" var="inqAbtDetails" columnClasses="columnClass"
			headerClass="headerClass" footerClass="footerClass"
			rowClasses="row_alt,row" rowIndexVar="rowIndex"
			onmouseover="return tableMouseOver(this, event);"
			onmouseout="return tableMouseOut(this, event);"
			first="#{CorrespondenceDataBean.startIndexForInqAbt}"
			value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceDetailsVO.listOfEnquiryAboutEntities}">

			<h:column id="inqAbtIdCol">
				<f:facet name="header">
					<h:panelGrid id="PRGCMGTPGD97" columns="2">
						<h:outputLabel id="inqAbtIdLabel" for="inqAbtIdLabelGrp"
							value="#{crspd['label.crspd.entityid']}" />
						<h:panelGroup id="inqAbtIdLabelGrp">
							<t:div style="width x;align=left">
								<%--Note:fixing for DEFECT ESPRD00576206 --%>
								<t:commandLink id="inqAbtIdAscCmdLink"
									style="text-decoration: none;"
									actionListener="#{CorrespondenceControllerBean.sortInqAbtEntity}"
									onmousedown="javascript:flagWarn=false;"
									rendered="#{CorrespondenceDataBean.imageRender !='inqAbtIdAscCmdLink'}">
									<m:div title="#{crspd['title.crspd.forAscd']}"
										styleClass="sort_asc" />
									<f:attribute name="#{crspd['id.crspd.columnName']}"
										value="#{crspd['label.crspd.entityid']}" />
									<f:attribute name="#{crspd['id.crspd.sortOrder']}"
										value="#{crspd['value.crspd.ascending']}" />
								</t:commandLink>
							</t:div>
							<t:div style="width x;align=left">
								<%--Note:fixing for DEFECT ESPRD00576206 --%>
								<t:commandLink id="inqAbtIdDscCmdLink"
									style="text-decoration: none;"
									actionListener="#{CorrespondenceControllerBean.sortInqAbtEntity}"
									onmousedown="javascript:flagWarn=false;"
									rendered="#{CorrespondenceDataBean.imageRender !='inqAbtIdDscCmdLink'}">
									<m:div title="#{crspd['title.crspd.forDsnd']}"
										styleClass="sort_desc" />
									<f:attribute name="#{crspd['id.crspd.columnName']}"
										value="#{crspd['label.crspd.entityid']}" />
									<f:attribute name="#{crspd['id.crspd.sortOrder']}"
										value="#{crspd['value.crspd.descending']}" />
								</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>
				</f:facet>
				<h:outputText id="valInqAbtId" value="#{inqAbtDetails.entityId}" />
			</h:column>
			<h:column id="inqAbtNameCol">
				<f:facet name="header">
					<h:panelGrid id="PRGCMGTPGD98" columns="2">
						<h:outputLabel id="inqAbtNameLabel" for="inqAbtNameLabelGrp"
							value="#{crspd['label.crspd.name']}" />
						<h:panelGroup id="inqAbtNameLabelGrp">
							<t:div style="width x;align=left">
								<%--Note:fixing for DEFECT ESPRD00576206 --%>
								<t:commandLink id="inqAbtNameAscCmdLink"
									style="text-decoration: none;"
									actionListener="#{CorrespondenceControllerBean.sortInqAbtEntity}"
									onmousedown="javascript:flagWarn=false;"
									rendered="#{CorrespondenceDataBean.imageRender !='inqAbtNameAscCmdLink'}">
									<m:div title="#{crspd['title.crspd.forAscd']}"
										styleClass="sort_asc" />
									<f:attribute name="#{crspd['id.crspd.columnName']}"
										value="#{crspd['label.crspd.name']}" />
									<f:attribute name="#{crspd['id.crspd.sortOrder']}"
										value="#{crspd['value.crspd.ascending']}" />
								</t:commandLink>
							</t:div>
							<t:div style="width x;align=left">
								<%--Note:fixing for DEFECT ESPRD00576206 --%>
								<t:commandLink id="inqAbtNameDscCmdLink"
									style="text-decoration: none;"
									actionListener="#{CorrespondenceControllerBean.sortInqAbtEntity}"
									onmousedown="javascript:flagWarn=false;"
									rendered="#{CorrespondenceDataBean.imageRender !='inqAbtNameDscCmdLink'}">
									<m:div title="#{crspd['title.crspd.forDsnd']}"
										styleClass="sort_desc" />
									<f:attribute name="#{crspd['id.crspd.columnName']}"
										value="#{crspd['label.crspd.name']}" />
									<f:attribute name="#{crspd['id.crspd.sortOrder']}"
										value="#{crspd['value.crspd.descending']}" />
								</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>
				</f:facet>
				<t:commandLink id="getInqAbt"
					action="#{CorrespondenceControllerBean.getInquiringAboutEntityDetails}"
					onmousedown="javascript:flagWarn=false;focusThis(this.id);">
					<h:outputText id="valInqAbtName" value="#{inqAbtDetails.name}" />
					<f:param name="indexCode" value="#{rowIndex}"></f:param>
				</t:commandLink>
			</h:column>
			<h:column id="inqAbtTpCol">
				<f:facet name="header">
					<h:panelGrid id="PRGCMGTPGD99" columns="2">
						<h:outputLabel id="inqAbtTpLabel" for="inqAbtTpLabelGrp"
							value="#{crspd['label.crspd.entitytype']}" />
						<h:panelGroup id="inqAbtTpLabelGrp">
							<t:div style="width x;align=left">
								<%--Note:fixing for DEFECT ESPRD00576206 --%>
								<t:commandLink id="inqAbtTpAscCmdLink"
									style="text-decoration: none;"
									actionListener="#{CorrespondenceControllerBean.sortInqAbtEntity}"
									onmousedown="javascript:flagWarn=false;"
									rendered="#{CorrespondenceDataBean.imageRender !='inqAbtTpAscCmdLink'}">
									<m:div title="#{crspd['title.crspd.forAscd']}"
										styleClass="sort_asc" />
									<f:attribute name="#{crspd['id.crspd.columnName']}"
										value="#{crspd['label.crspd.entitytype']}" />
									<f:attribute name="#{crspd['id.crspd.sortOrder']}"
										value="#{crspd['value.crspd.ascending']}" />
								</t:commandLink>
							</t:div>
							<t:div style="width x;align=left">
								<%--Note:fixing for DEFECT ESPRD00576206 --%>
								<t:commandLink id="inqAbtTpDscCmdLink"
									style="text-decoration: none;"
									actionListener="#{CorrespondenceControllerBean.sortInqAbtEntity}"
									onmousedown="javascript:flagWarn=false;"
									rendered="#{CorrespondenceDataBean.imageRender !='inqAbtTpDscCmdLink'}">
									<m:div title="#{crspd['title.crspd.forDsnd']}"
										styleClass="sort_desc" />
									<f:attribute name="#{crspd['id.crspd.columnName']}"
										value="#{crspd['label.crspd.entitytype']}" />
									<f:attribute name="#{crspd['id.crspd.sortOrder']}"
										value="#{crspd['value.crspd.descending']}" />
								</t:commandLink>
							</t:div>
						</h:panelGroup>
					</h:panelGrid>
				</f:facet>
				<h:outputText id="valInqAbtTp"
					value="#{inqAbtDetails.entityTypeDesc}" />
			</h:column>
			<f:facet name="footer">
				<m:div id="nodataInqAbt" align="center"
					rendered="#{empty CorrespondenceDataBean.correspondenceRecordVO.correspondenceDetailsVO.listOfEnquiryAboutEntities}">
					<h:outputText id="PRGCMGTOT810" value="#{ref['label.ref.noData']}" />
				</m:div>
			</f:facet>
		</t:dataTable>
        <%-- DataScroller for Defect ESPRD00733017 --%>
		<t:dataScroller id="CMGTTOMDS70" for="inqAbtDataTable" paginator="true" onclick="flagWarn=false;"
			paginatorActiveColumnStyle='font-weight:bold;' paginatorMaxPages="3"
			immediate="false" pageCountVar="pageCount" pageIndexVar="pageIndex"
			firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
			rowsCountVar="rowsCount" styleClass="scroller">
			<f:facet name="previous">
				<h:outputText id="PRGCMGTOT1841" value="#{ref['label.ref.lt']}"
					rendered="#{pageIndex > 1}">
				</h:outputText>
			</f:facet>
			<f:facet name="next">
				<h:outputText id="PRGCMGTOT1842" value="#{ref['label.ref.gt']}"
					rendered="#{pageIndex < pageCount}">
				</h:outputText>
			</f:facet>
			<h:outputText id="PRGCMGTOT1843" rendered="#{rowsCount > 0}"
				value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
				style="float:left;position:relative;bottom:-6px;font-weight:bold;" />
		</t:dataScroller>

		<m:br />
		<m:br />
		<m:div styleClass="Col100"
			rendered="#{CorrespondenceDataBean.renderInqAbtForVO}">
			<jsp:include page="/jsp/logcorrespondence/inc_inqAbtFor.jsp" />
		</m:div>
		<m:div styleClass="Col100"
			rendered="#{CorrespondenceDataBean.renderInqAbtForMemberVO}">
			<jsp:include page="/jsp/logcorrespondence/inc_inqAbtMember.jsp" />
		</m:div>
		<m:div styleClass="Col100"
			rendered="#{CorrespondenceDataBean.renderInqAbtForProviderVO}">
			<jsp:include page="/jsp/logcorrespondence/inc_inqAbtProvider.jsp" />
		</m:div>
		<m:div styleClass="Col100"
			rendered="#{CorrespondenceDataBean.renderInqAbtForTradingPartnerVO}">
			<jsp:include
				page="/jsp/logcorrespondence/inc_inqAbtTradingPartner.jsp" />
		</m:div>

	</m:section>
</m:div>
