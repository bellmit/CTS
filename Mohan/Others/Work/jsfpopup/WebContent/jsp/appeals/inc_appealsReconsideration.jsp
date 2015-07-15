
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<portlet:defineObjects />

<m:div>
	<m:br/>
	<m:br/>
</m:div>

<m:section id="PROVIDERMMS2012073116481140" styleClass="otherbg">
	<m:legend>
		    	<h:outputText id="PRGCMGTOT75" value="#{msg['label.appeals.recons.reconsideration']}"/>
   	</m:legend>
	<m:table id="PROVIDERMMT2012073116481141" cellspacing="0" width="100%">
		<m:tr>
			<m:td>
				<m:div styleClass="padb">
					<h:outputLabel id="PRGCMGTOLL54" for="reconsider_SentDt" value="#{msg['label.appeals.recons.sentdate']}" >
					</h:outputLabel>
					<m:br/>													
					<m:inputCalendar monthYearRowClass="yearMonthHeader"
						weekRowClass="weekHeader" id="reconsider_SentDt"
						currentDayCellClass="currentDayCell" size="10"
						renderAsPopup="true" addResources="true"
						renderPopupButtonAsImage="true"
						value="#{appealDataBean.appealVO.rcnsdrtnSentDt}"
						popupDateFormat="MM/dd/yyyy" >
					</m:inputCalendar>
					<m:br/>
					<h:message id="PRGCMGTM30" for="reconsider_SentDt" styleClass="errorMessage"/>
				</m:div>
			</m:td>
			
			<m:td>
				<m:div styleClass="padb">
					<h:outputLabel id="PRGCMGTOLL55" for="reconsider_ReturndDt" value="#{msg['label.appeals.recons.returneddate']}" >
					</h:outputLabel>
					<m:br/>													
					<m:inputCalendar monthYearRowClass="yearMonthHeader"
						weekRowClass="weekHeader" id="reconsider_ReturndDt"
						currentDayCellClass="currentDayCell" size="10"
						renderAsPopup="true" addResources="true"
						renderPopupButtonAsImage="true"
						value="#{appealDataBean.appealVO.rcnsdrtnRtrnDt}"
						popupDateFormat="MM/dd/yyyy" >
					</m:inputCalendar>
					<m:br/>
					<h:message id="PRGCMGTM31" for="reconsider_ReturndDt" styleClass="errorMessage"/>
				</m:div>
			</m:td>

			<m:td>
				<m:div styleClass="padb">
					<h:outputLabel id="PRGCMGTOLL56" for="reconsider_RevName" value="#{msg['label.appeals.recons.reviewername']}">
					</h:outputLabel>
					<m:br />
					<h:inputText id="reconsider_RevName" size="10" value="#{appealDataBean.appealVO.rcnsdrtnRevwrNam}" />
					<m:br />
					<h:message id="PRGCMGTM32" for="reconsider_RevName" styleClass="errorMessage" />
				</m:div>
			</m:td>
		</m:tr>

		<m:tr>
			<m:td>
				<m:div styleClass="padb">
					<h:outputLabel id="PRGCMGTOLL57" for="reconsider_Dec" value="#{msg['label.appeals.recons.decision']}" >
					</h:outputLabel>
					<m:br />
					<h:selectOneMenu id="reconsider_Dec" value="#{appealDataBean.appealVO.rcnsdrtnDecisCd}" 					onchange="javascript:focusThis(this.id);javascript:addeditrow('memberAppealsViewForm'); this.form.submit();"						onclick="javascript:flagWarn=false;">
						<f:selectItems
										value="#{appealDataBean.decisionList}" /> 
					</h:selectOneMenu>	
				</m:div>
			</m:td>
			
			<m:td>
				<m:div styleClass="padb">
					<h:outputLabel id="PRGCMGTOLL58" for="reconsider_DecDt" value="#{msg['label.appeals.recons.decisiondate']}" >
					</h:outputLabel>
					<m:br/>													
					<m:inputCalendar monthYearRowClass="yearMonthHeader"
						weekRowClass="weekHeader" id="reconsider_DecDt"
						currentDayCellClass="currentDayCell" size="10"
						renderAsPopup="true" addResources="true"
						renderPopupButtonAsImage="true"
						value="#{appealDataBean.appealVO.rcnsdrtnDecisDt}"
						popupDateFormat="MM/dd/yyyy" >
					</m:inputCalendar>
					<m:br/>
					<h:message id="PRGCMGTM33" for="reconsider_DecDt" styleClass="errorMessage"/>
				</m:div>
			</m:td>
			
			<m:td>
				<m:div styleClass="padb">
					<h:outputLabel id="PRGCMGTOLL59" for="reconsider_NotLetterSntDt" value="#{msg['label.appeals.recons.notifyltrsentdate']}" >
					</h:outputLabel>
					<m:br/>													
					<m:inputCalendar monthYearRowClass="yearMonthHeader"
						weekRowClass="weekHeader" id="reconsider_NotLetterSntDt"
						currentDayCellClass="currentDayCell" size="10"
						renderAsPopup="true" addResources="true"
						renderPopupButtonAsImage="true"
						value="#{appealDataBean.appealVO.rcnsdrtnNotfyLtrSentDt}"
						popupDateFormat="MM/dd/yyyy" >
					</m:inputCalendar>
					<m:br/>
					<h:message id="PRGCMGTM34" for="reconsider_NotLetterSntDt" styleClass="errorMessage"/>
				</m:div>
			</m:td>			

		</m:tr>
	</m:table>
</m:section>	
<m:div>
	<m:br/>
	<m:br/>
</m:div>	
