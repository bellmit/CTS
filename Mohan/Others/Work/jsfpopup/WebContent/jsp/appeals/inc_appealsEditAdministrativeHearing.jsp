<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@ taglib uri="http://www.acs-inc.com/auditLog" prefix="audit"%>
<script type="text/javascript">
function conformDeleteMemberAppeal(){

	if(confirm('Are you sure you want to Delete?')){
		flagWarn=false;
		fileSavedFlag=true;
		//alert('deleting');
		return true;
	}else{
		flagWarn=true;
		return false;
	}
	
}
</script>
<portlet:defineObjects />


<m:div styleClass="moreInfo">
	<m:div styleClass="moreInfoBar">
		<m:div styleClass="infoTitle">
			<h:outputText id="PRGCMGTOT63"
				value="#{msg['title.appeals.editadministrativehearing']}" />
		</m:div>
		<m:div>
			<m:div styleClass="infoActions">
				<t:commandLink id="PRGCMGTCL16"
					action="#{appealControllerBean.cancelAdminHearing}"
					onmousedown="javascript:focusThis('adminHearingTable');">
					<h:outputText id="PRGCMGTOT73"
						value="#{msg['label.appeals.cancel']}" />
				</t:commandLink>
			</m:div>
			<m:div styleClass="infoActions">
				<h:outputText id="PRGCMGTOT69" value="#{msg['label.appeals.pipe']}" />
			</m:div>
			<m:div rendered="#{appealDataBean.notesListIndicator}"
				styleClass="infoActions">
				<h:outputText id="PROVIDEROLT2012073116481134" value="("></h:outputText>
				<m:img id="appealAdminNotesImageID" src="/wps/themes/html/ACSDefault/images/icn_check.gif" alt="#{msg['label.appeals.yes']}" title="#{msg['label.appeals.yes']}"></m:img>
				<h:outputText id="PROVIDEROLT2012073116481135" value=")"></h:outputText>
			</m:div>
			<m:div styleClass="infoActions">

				<t:commandLink action="#{detailedNotesControllerBean.showNotes}"
					id="editAdminHrngNotesLink"
					onmousedown="javascript:focusThis(this.id);javascript:flagWarn=false;" onclick="javascript:flagWarn=false;">
					<h:outputText id="PRGCMGTOT71"
						value="#{msg['label.appeals.notes']}" />

					<%--h:outputText value="#{msg['label.appeals.notes']}(#{appealDataBean.notesCount})" /--%>
				</t:commandLink>
			</m:div>
			<m:div styleClass="infoActions">
				<h:outputText id="PRGCMGTOT66" value="#{msg['label.appeals.pipe']}" />
			</m:div>
			<m:div styleClass="infoActions">
				<t:commandLink id="PRGCMGTCB1"
					rendered="#{!appealDataBean.disableAppDelAdminHear}"
					onclick="javascript:flagWarn=false;return conformDeleteMemberAppeal();"
					action="#{appealControllerBean.deleteAdminHearing}"
					value="#{msg['label.appeals.delete']}" />
				<h:outputText value="#{msg['label.appeals.delete']}" id="APPEALDELTXTDSBL"
					rendered="#{appealDataBean.disableAppDelAdminHear}"></h:outputText>
			</m:div>
			<m:div styleClass="infoActions">
				<h:outputText id="PRGCMGTOT72" value="#{msg['label.appeals.pipe']}" />
			</m:div>
			<m:div styleClass="infoActions">
				<t:commandLink
					rendered="#{!appealDataBean.disableAppResetAdminHear}"
					action="#{appealControllerBean.resetEditAdminHearing}"
					id="editAdminHrngResetLink"
					onmousedown="javascript:focusThis(this.id);javascript:flagWarn=false;" onclick="javascript:flagWarn=false;">
					<h:outputText id="PRGCMGTOT67"
						value="#{msg['label.appeals.reset']}" />
				</t:commandLink>
				<h:outputText id="PRGCMGTOT68" value="#{msg['label.appeals.reset']}"
					rendered="#{appealDataBean.disableAppResetAdminHear}" />
			</m:div>
			<m:div styleClass="infoActions">
				<h:outputText id="PRGCMGTOT70" value="#{msg['label.appeals.pipe']}" />
			</m:div>
			<m:div styleClass="infoActions">
				<t:commandLink styleClass="strong"
					rendered="#{!appealDataBean.disableAppSaveAdminHear}"
					action="#{appealControllerBean.saveAdminHearing}"
					id="editAdminHrngSaveLink"
					onmousedown="javascript:focusThis('adminHearingTable');javascript:flagWarn=false;" onclick="javascript:flagWarn=false;">
					<h:outputText id="PRGCMGTOT64" value="#{msg['label.appeals.save']}" />
				</t:commandLink>
				<h:outputText id="PRGCMGTOT65" value="#{msg['label.appeals.save']}"
					rendered="#{appealDataBean.disableAppSaveAdminHear}" />
			</m:div>
		</m:div>
		<m:div styleClass="clear"></m:div>
	</m:div>
	<m:div styleClass="moreInfoContent">
		<m:div id="editStarts">
			<%-- Starts To fix Defect ESPRD00696684--%>
			<m:table id="PROVIDERMMT2012073116481136" width="90%">
				<m:tr>
					<m:td>
						<m:div id="message_success2">
						</m:div>
					</m:td>
				</m:tr>
			</m:table>
			<m:div rendered="#{appealDataBean.showSuccessMsgFlag}"
				styleClass="msgBox">
				<h:outputText id="Edit1" escape="false"
					value="#{msg['display.appeals.save.message']}">
				</h:outputText>
				<!--<h:messages layout="table" showDetail="true" styleClass="successMsg"
					id="edit_AdminHrngSuccessMessage" showSummary="false"></h:messages> -->
			</m:div>

			<m:div styleClass="moreInfoContent">
				<m:div styleClass="padb">
					<m:table id="PROVIDERMMT2012073116481137" cellspacing="0" width="80%">
						<m:tr>
							<m:td>
								<m:div styleClass="padb">
									<h:outputLabel id="PRGCMGTOLL44" for="edit_AdminHrngDT"
										value="#{msg['label.appeals.administrativehearingdate']}" />
									<m:br />

									<m:inputCalendar monthYearRowClass="yearMonthHeader"
										weekRowClass="weekHeader" id="edit_AdminHrngDT"
										currentDayCellClass="currentDayCell" size="10"
										renderAsPopup="true" addResources="true"
										renderPopupButtonAsImage="true"
										value="#{appealDataBean.appealVO.adminHearingVO.strAdminHearingDate}"
										popupDateFormat="MM/dd/yyyy">
									</m:inputCalendar>
									<m:br id="appealEditAdminBrID"/>
									<h:message id="PRGCMGTM25" for="edit_AdminHrngDT"
										styleClass="errorMessage" />
									<m:br />
								</m:div>
							</m:td>
							<m:td>
								<m:div styleClass="padb">
									<h:outputLabel id="PRGCMGTOLL45" for="edit_HrngRslts"
										value="#{msg['label.appeals.hearingresults']}">
									</h:outputLabel>
									<m:br />
									<h:selectOneMenu id="edit_HrngRslts"
										value="#{appealDataBean.appealVO.adminHearingVO.hearingResults}"
										onchange="javascript:flagWarn=false;javascript:focusThis(this.id);javascript:addeditrow('memberAppealsViewForm'); this.form.submit();">
										<f:selectItems value="#{appealDataBean.hearingResultsList}" />
									</h:selectOneMenu>
								</m:div>
							</m:td>

							<m:td>
								<m:div styleClass="padb">
									<h:outputLabel id="PRGCMGTOLL46" for="edit_HrngSts"
										value="#{msg['label.appeals.hearingstatus']}">
									</h:outputLabel>
									<m:br />

									<h:selectOneMenu id="edit_HrngSts"
										value="#{appealDataBean.appealVO.adminHearingVO.hearingStatus}"
										onchange="javascript:flagWarn=false;javascript:focusThis(this.id);javascript:addeditrow('memberAppealsViewForm'); this.form.submit();">
										<f:selectItems value="#{appealDataBean.hearingStatusList}" />
									</h:selectOneMenu>
								</m:div>
							</m:td>

							<m:td>
								<m:div styleClass="padb">
									<h:outputLabel id="PRGCMGTOLL47" for="edit_DcktNum"
										value="#{msg['label.appeals.docketnumber']}">
									</h:outputLabel>
									<m:br />
									<h:inputText id="edit_DcktNum" size="10"
										value="#{appealDataBean.appealVO.adminHearingVO.docketNumber}" />
								</m:div>
							</m:td>

						</m:tr>
						<m:tr>
							<m:td>
								<m:div styleClass="padb">
									<h:outputLabel id="PRGCMGTOLL48" for="edit_HrngOffNum"
										value="#{msg['label.appeals.hearingofficername']}">
									</h:outputLabel>
									<m:br />
									<h:inputText id="edit_HrngOffNum" size="15"
										value="#{appealDataBean.appealVO.adminHearingVO.hearingOfficerName}" />
								</m:div>
							</m:td>

							<m:td colspan="2">
								<m:div styleClass="padb">
									<h:outputLabel id="PRGCMGTOLL49" for="edit_HrngCit"
										value="#{msg['label.appeals.hearingcitation']}">
									</h:outputLabel>
									<m:br />
									<h:inputText id="edit_HrngCit" size="15"
										value="#{appealDataBean.appealVO.adminHearingVO.hearingCitation}"
										disabled="#{appealDataBean.editAdminHearingFlag}" />
								</m:div>
							</m:td>

						</m:tr>
					</m:table>

					<m:div rendered="#{appealDataBean.enableAppealDetailAudit}">
						<f:subview id="adminHearAuditlogDetails">
							<%--						<jsp:include page="/jsp/appeals/inc_appealsAdminHearingAuditLog.jsp" /> ---%>
							<audit:auditTableSet id="adminHearAuditId"
								value="#{appealDataBean.appealVO.adminHearingVO.auditKeyList}"
								numOfRecPerPage="10">
							</audit:auditTableSet>
						</f:subview>
					</m:div>
					<m:div>
						<m:br />
						<m:br />
					</m:div>
				</m:div>
			</m:div>
		</m:div>
	</m:div>
	<%-- Ends  To fix Defect ESPRD00696684--%>
</m:div>

