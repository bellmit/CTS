<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<portlet:defineObjects />


	<m:div styleClass="moreInfo" >
		<m:div styleClass="moreInfoBar">
			<m:div styleClass="infoTitle">
				<h:outputText id="PRGCMGTOT31" value="#{msg['title.appeals.addadministrativehearing']}" />
			</m:div>
			<m:div styleClass="infoActions">

				<t:commandLink styleClass="strong"					action="#{appealControllerBean.saveAdminHearing}"					id="addAdminHrngSaveLink"					 onmousedown="javascript:focusThis('adminHearingButton');javascript:flagWarn=false;" onclick="javascript:flagWarn=false;">
					<h:outputText id="PRGCMGTOT32" value="#{msg['label.appeals.save']}" />
				</t:commandLink>
				
				<h:outputText id="PRGCMGTOT33" value="#{msg['label.appeals.pipe']}" />
				

				<t:commandLink					action="#{appealControllerBean.resetAddAdminHearing}"					id="addAdminHrngResetLink" 					onmousedown="javascript:focusThis(this.id);javascript:flagWarn=false;" onclick="javascript:flagWarn=false;">
					<h:outputText id="PRGCMGTOT34" value="#{msg['label.appeals.reset']}" />
				</t:commandLink>
				
				<h:outputText id="PRGCMGTOT35" value="#{msg['label.appeals.pipe']}" />
				
				
				<t:commandLink					action="#{detailedNotesControllerBean.showNotes}"					id="addAdminHrngNotesLink" 					onmousedown="javascript:focusThis(this.id);javascript:flagWarn=false;" onclick="javascript:flagWarn=false;">
				<h:outputText id="PRGCMGTOT36" value="#{msg['label.appeals.notes']}"/>
          		
				</t:commandLink>
				
				<h:outputText id="PRGCMGTOT37" value="#{msg['label.appeals.pipe']}" />
				

				<t:commandLink id="addcancelAdminHearingID"					action="#{appealControllerBean.cancelAdminHearing}"					onmousedown="javascript:focusThis('adminHearingButton');">
					<h:outputText id="PRGCMGTOT38" value="#{msg['label.appeals.cancel']}" />
				</t:commandLink>	
			</m:div>
			<m:div styleClass="clear"></m:div>
		</m:div>
		<m:div styleClass="moreInfoContent">
		<m:div id="starts"><%-- Starts To fix Defect ESPRD00696684--%>
			<m:table id="PROVIDERMMT2012073116481113" width="90%">
							<m:tr>
								<m:td>
									<m:div id="message_success1">
									</m:div>
								</m:td>
							</m:tr>
			</m:table>
			<m:div rendered="#{appealDataBean.showSuccessMsgFlag}"
				styleClass="msgBox">
				<h:outputText id="Add1" escape="false"
							  value="#{msg['display.appeals.save.message']}">
				</h:outputText>
				<%-- <h:messages layout="table" showDetail="true" styleClass="successMsg"
					id="add_AdminHrngSuccessMessage" showSummary="false"></h:messages> --%>
			</m:div>
								
				
		<m:div styleClass="moreInfoContent">
			<m:div styleClass="padb">
				<m:table id="PROVIDERMMT2012073116481114" cellspacing="0" width="80%">
						<m:tr>
							<m:td>
							<m:div styleClass="padb">
								<h:outputLabel id="PRGCMGTOLL21" for="add_AdminHrngDT"									value="#{msg['label.appeals.administrativehearingdate']}" />
								<m:br />

								<m:inputCalendar monthYearRowClass="yearMonthHeader"
									weekRowClass="weekHeader" id="add_AdminHrngDT"
									currentDayCellClass="currentDayCell" size="10"
									renderAsPopup="true" addResources="true"
									renderPopupButtonAsImage="true"
									value="#{appealDataBean.appealVO.adminHearingVO.strAdminHearingDate}"
									popupDateFormat="MM/dd/yyyy" >
								</m:inputCalendar>
							<m:br/>
							<h:message id="PRGCMGTM10" for="add_AdminHrngDT" styleClass="errorMessage"/>
							</m:div>
						</m:td>
						<m:td>
						<m:div styleClass="padb">
							<h:outputLabel id="PRGCMGTOLL22" for="add_HrngRslts" value="#{msg['label.appeals.hearingresults']}" >
							</h:outputLabel>
							<m:br />
							<h:selectOneMenu id="add_HrngRslts" value="#{appealDataBean.appealVO.adminHearingVO.hearingResults}" 							onchange="javascript:flagWarn=false;javascript:focusThis(this.id);javascript:addeditrow('memberAppealsViewForm'); this.form.submit();">
								<f:selectItems
										value="#{appealDataBean.hearingResultsList}" />
							</h:selectOneMenu>	
						</m:div>
					</m:td>

					<m:td>
						<m:div styleClass="padb">
							<h:outputLabel id="PRGCMGTOLL23" for="add_HrngStatus" value="#{msg['label.appeals.hearingstatus']}">
							</h:outputLabel>
							<m:br />

						
					<%-- Added by ics GAP-11022 --%>	
						 <h:selectOneMenu id="add_HrngStatus" value="#{appealDataBean.appealVO.adminHearingVO.hearingStatus}"						 onchange="javascript:flagWarn=false;javascript:focusThis(this.id);javascript:addeditrow('memberAppealsViewForm'); this.form.submit();">
								<f:selectItems
										value="#{appealDataBean.hearingStatusList}" />
							</h:selectOneMenu>	
					<%-- END by ics GAP-11022 --%>
			
						</m:div>
					</m:td>

					<m:td>
						<m:div styleClass="padb">
							<h:outputLabel id="PRGCMGTOLL24" for="add_DcktNum" value="#{msg['label.appeals.docketnumber']}">
							</h:outputLabel>
							<m:br />
							<h:inputText id="add_DcktNum" size="10" value="#{appealDataBean.appealVO.adminHearingVO.docketNumber}" />
						</m:div>
					</m:td>
									
						</m:tr>
						<m:tr>
						<m:td>
							<m:div styleClass="padb">
								<h:outputLabel id="PRGCMGTOLL25" for="add_HrngOffNum" value="#{msg['label.appeals.hearingofficername']}">
								</h:outputLabel>
								<m:br />
								<h:inputText id="add_HrngOffNum" size="15" value="#{appealDataBean.appealVO.adminHearingVO.hearingOfficerName}" />
							</m:div>
						</m:td>
						
						<m:td colspan="2">
							<m:div styleClass="padb">
								<h:outputLabel id="PRGCMGTOLL26" for="add_HrngCit" value="#{msg['label.appeals.hearingcitation']}">
								</h:outputLabel>
								<m:br />
								<h:inputText id="add_HrngCit" size="15" value="#{appealDataBean.appealVO.adminHearingVO.hearingCitation}" />
							</m:div>
						</m:td>
				
						</m:tr>
					</m:table>
			</m:div>
		</m:div>
	</m:div>
	</m:div><%-- Ends To fix Defect ESPRD00696684 --%>
</m:div>

