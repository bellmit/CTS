
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<portlet:defineObjects />

		<m:div>
			<m:br />
			<m:br />
		</m:div>
		<m:section id="PROVIDERMMS2012073116481115" styleClass="otherbg">
			<m:legend>
	    	 <h:outputText id="PRGCMGTOT39" value="#{msg['label.appeals.addinfo.additionalinformation']}"/>
    		</m:legend>
			<m:table id="PROVIDERMMT2012073116481116" cellspacing="0" width="100%">
				<m:tr>
					<m:td>
						<m:div styleClass="padb">
							<h:outputLabel id="PRGCMGTOLL27" for="adlInfo_ReqDt" value="#{msg['label.appeals.addinfo.requesteddate']}" >
							</h:outputLabel>
							<m:br/>													
							<m:inputCalendar monthYearRowClass="yearMonthHeader"
								weekRowClass="weekHeader" id="adlInfo_ReqDt"
								currentDayCellClass="currentDayCell" size="10"
								renderAsPopup="true" addResources="true"
								renderPopupButtonAsImage="true"
								value="#{appealDataBean.appealVO.addlInfoReqDt}"
								popupDateFormat="MM/dd/yyyy" >
							</m:inputCalendar>
							<m:br/>
							<h:message id="PRGCMGTM11" for="adlInfo_ReqDt" styleClass="errorMessage"/>
						</m:div>
				
						<m:div styleClass="padb">
							<h:outputLabel id="PRGCMGTOLL28" for="adlInfo_NotifyLtrSntDt" value="#{msg['label.appeals.addinfo.notifyltrsntdate']}" >
							</h:outputLabel>
							<m:br/>													
							<m:inputCalendar monthYearRowClass="yearMonthHeader"
								weekRowClass="weekHeader" id="adlInfo_NotifyLtrSntDt"
								currentDayCellClass="currentDayCell" size="10"
								renderAsPopup="true" addResources="true"
								renderPopupButtonAsImage="true"
								value="#{appealDataBean.appealVO.addlInfoNotfyLtrSentDt}"
								popupDateFormat="MM/dd/yyyy" >
							</m:inputCalendar>
							<m:br/>
							<h:message id="PRGCMGTM12" for="adlInfo_NotifyLtrSntDt" styleClass="errorMessage"/>
						</m:div>
					</m:td>
			
					<m:td>
						<m:div styleClass="padb">
							<h:outputLabel id="PRGCMGTOLL29" for="adlInfo_DueDt" value="#{msg['label.appeals.addinfo.duedate']}" >
							</h:outputLabel>
							<m:br/>													
							<m:inputCalendar monthYearRowClass="yearMonthHeader"
								weekRowClass="weekHeader" id="adlInfo_DueDt"
								currentDayCellClass="currentDayCell" size="10"
								renderAsPopup="true" addResources="true"
								renderPopupButtonAsImage="true"
								value="#{appealDataBean.appealVO.addlInfoDueDt}"
								popupDateFormat="MM/dd/yyyy" >
							</m:inputCalendar>
							<m:br/>
							<h:message id="PRGCMGTM13" for="adlInfo_DueDt" styleClass="errorMessage"/>
						</m:div>
		
						<m:div styleClass="padb">
							<h:outputLabel id="PRGCMGTOLL30" for="adlInfo_Rcvd2ndReqDt" value="#{msg['label.appeals.addinfo.rcvd2ndrqstdate']}" >
							</h:outputLabel>
							<m:br/>													
							<m:inputCalendar monthYearRowClass="yearMonthHeader"
								weekRowClass="weekHeader" id="adlInfo_Rcvd2ndReqDt"
								currentDayCellClass="currentDayCell" size="10"
								renderAsPopup="true" addResources="true"
								renderPopupButtonAsImage="true"
								value="#{appealDataBean.appealVO.addlInfoRecd2NdReqDt}"
								popupDateFormat="MM/dd/yyyy" >
							</m:inputCalendar>
							<m:br/>
							<h:message id="PRGCMGTM14" for="adlInfo_Rcvd2ndReqDt" styleClass="errorMessage"/>
						</m:div>
					</m:td>
			
					<m:td>
						<m:div styleClass="padb">
							<h:outputLabel id="PRGCMGTOLL31" for="adlInfo_RcvdDt" value="#{msg['label.appeals.addinfo.rcvddate']}" >
							</h:outputLabel>
							<m:br/>													
							<m:inputCalendar monthYearRowClass="yearMonthHeader"
								weekRowClass="weekHeader" id="adlInfo_RcvdDt"
								currentDayCellClass="currentDayCell" size="10"
								renderAsPopup="true" addResources="true"
								renderPopupButtonAsImage="true"
								value="#{appealDataBean.appealVO.addlInfoRecdDt}"
								popupDateFormat="MM/dd/yyyy" >
							</m:inputCalendar>
							<m:br/>
							<h:message id="PRGCMGTM15" for="adlInfo_RcvdDt" styleClass="errorMessage"/>
						</m:div>
						<m:div styleClass="padb">
							<h:outputLabel id="PRGCMGTOLL32" for="adlInfo_2ndRevDueDt" value="#{msg['label.appeals.addinfo.2ndrvsdduedate']}" >
							</h:outputLabel>
							<m:br/>													
							<m:inputCalendar monthYearRowClass="yearMonthHeader"
								weekRowClass="weekHeader" id="adlInfo_2ndRevDueDt"
								currentDayCellClass="currentDayCell" size="10"
								renderAsPopup="true" addResources="true"
								renderPopupButtonAsImage="true"
								value="#{appealDataBean.appealVO.addlInfo2NdRvsdDueDt}"
								popupDateFormat="MM/dd/yyyy" >
							</m:inputCalendar>
							<m:br/>
							<h:message id="PRGCMGTM16" for="adlInfo_2ndRevDueDt" styleClass="errorMessage"/>
						</m:div>
					</m:td>
					
					<m:td>
						<m:div styleClass="padb">
								<h:outputLabel id="PRGCMGTOLL33" for="adlInfo_RevisedRevDue" value="#{msg['label.appeals.addinfo.rvsdrviewdue']}" >
								</h:outputLabel>
								<m:br/>													
								<m:inputCalendar monthYearRowClass="yearMonthHeader"
									weekRowClass="weekHeader" id="adlInfo_RevisedRevDue"
									currentDayCellClass="currentDayCell" size="10"
									renderAsPopup="true" addResources="true"
									renderPopupButtonAsImage="true"
									value="#{appealDataBean.appealVO.addlInfoRvsdRevwDueDt}"
									popupDateFormat="MM/dd/yyyy" >
								</m:inputCalendar>
								<m:br/>
								<h:message id="PRGCMGTM17" for="adlInfo_RevisedRevDue" styleClass="errorMessage"/>
							</m:div>
					</m:td>		
				</m:tr>
				<m:tr>
					<m:td>
						<m:div styleClass="padb">
							<h:outputLabel id="PRGCMGTOLL34" for="adlInfo_AAUOfficer" value="#{msg['label.appeals.addinfo.aauofficer']}">
							</h:outputLabel>
							<m:br />
							<h:inputText id="adlInfo_AAUOfficer" size="10" value="#{appealDataBean.appealVO.addlInfoAAUOfficerNam}" />
							<m:br />
							<h:message id="PRGCMGTM18" for="adlInfo_AAUOfficer" styleClass="errorMessage" />
						</m:div>
					</m:td>
					
					<m:td>
						<m:div styleClass="padb">
							<h:outputLabel id="PRGCMGTOLL35" for="adlInfo_ResReqBy" value="#{msg['label.appeals.addinfo.rsprqstdby']}" >
							</h:outputLabel>
							<m:br/>													
							<m:inputCalendar monthYearRowClass="yearMonthHeader"
								weekRowClass="weekHeader" id="adlInfo_ResReqBy"
								currentDayCellClass="currentDayCell" size="10"
								renderAsPopup="true" addResources="true"
								renderPopupButtonAsImage="true"
								value="#{appealDataBean.appealVO.addlInfoRespReqDueDt}"
								popupDateFormat="MM/dd/yyyy" >
							</m:inputCalendar>
							<m:br/>
							<h:message id="PRGCMGTM19" for="adlInfo_ResReqBy" styleClass="errorMessage"/>
						</m:div>
					</m:td>
					<m:td>
						<m:div styleClass="padb">
							<h:outputLabel id="PRGCMGTOLL36" for="adlInfo_MotType" value="#{msg['label.appeals.addinfo.motiontype']}" >
							</h:outputLabel>
							<m:br />
							<h:selectOneMenu id="adlInfo_MotType" value="#{appealDataBean.appealVO.addlInfoMotionTyCd}"							onchange="javascript:focusThis(this.id);javascript:addeditrow('memberAppealsViewForm'); this.form.submit();"											onclick="javascript:flagWarn=false;">
								<f:selectItems
										value="#{appealDataBean.motionTypeList}" />
							</h:selectOneMenu>	
						</m:div>
					</m:td>

					<m:td>
						<m:div styleClass="padb">
							<h:outputLabel id="PRGCMGTOLL37" for="adlInfo_DtMotFail" value="#{msg['label.appeals.addinfo.dtmotionfiled']}" >
							</h:outputLabel>
							<m:br/>													
							<m:inputCalendar monthYearRowClass="yearMonthHeader"
								weekRowClass="weekHeader" id="adlInfo_DtMotFail"
								currentDayCellClass="currentDayCell" size="10"
								renderAsPopup="true" addResources="true"
								renderPopupButtonAsImage="true"
								value="#{appealDataBean.appealVO.addlInfoFiledDt}"
								popupDateFormat="MM/dd/yyyy" >
							</m:inputCalendar>
							<m:br/>
							<h:message id="PRGCMGTM20" for="adlInfo_DtMotFail" styleClass="errorMessage"/>
						</m:div>
					</m:td>	
				</m:tr>
				
				<m:tr>
					<m:td>
						<m:div styleClass="padb">
							<h:outputLabel id="PRGCMGTOLL38" for="adlInfo_CaseFileLoc" value="#{msg['label.appeals.addinfo.casefileloc']}" >
							</h:outputLabel>
							<m:br />
							
							<h:selectOneMenu id="adlInfo_CaseFileLoc" value="#{appealDataBean.appealVO.addlInfoFileLocnCd}" 							onchange="javascript:focusThis(this.id);javascript:addeditrow('memberAppealsViewForm'); this.form.submit();"							onclick="javascript:flagWarn=false;">
								<f:selectItems
									value="#{appealDataBean.addlInfoFileList}" />
							</h:selectOneMenu>
						</m:div>
					</m:td>
					
					<m:td colspan="2">
						<m:div styleClass="padb">
							<h:outputLabel id="PRGCMGTOLL39" for="adlInfo_ClientRep" value="#{msg['label.appeals.addinfo.clntrep']}">
							</h:outputLabel>
							<m:br />
							<h:inputText id="adlInfo_ClientRep" size="10" value="#{appealDataBean.appealVO.addlInfoClientRepNam}" />
							<m:br/>
							<h:message id="PRGCMGTM21" for="adlInfo_ClientRep" styleClass="errorMessage"/>
						</m:div>
					</m:td>					

				</m:tr>		
					</m:table>
			</m:section>
