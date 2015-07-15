
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<portlet:defineObjects />

<m:div styleClass="clear">
</m:div>
<m:div>
	<m:br />
	<m:br />
</m:div>

<m:section id="PROVIDERMMS2012073116481132" styleClass="otherbg">
	<m:legend>
		<h:outputText id="PRGCMGTOT62"
			value="#{msg['label.appeals.dhhsform.dhhsformalreview']}" />
	</m:legend>

	<m:table id="PROVIDERMMT2012073116481133" cellspacing="0" width="100%">
		<m:tr>
			<m:td>
				<m:div styleClass="padb">
					<h:outputLabel id="PRGCMGTOLL40" for="DHHS_SentToDHHSDt"
						value="#{msg['label.appeals.dhhsform.snttodhhsdate']}">
					</h:outputLabel>
					<m:br />
					<m:inputCalendar monthYearRowClass="yearMonthHeader"
						weekRowClass="weekHeader" id="DHHS_SentToDHHSDt"
						currentDayCellClass="currentDayCell" size="10"
						renderAsPopup="true" addResources="true"
						renderPopupButtonAsImage="true"
						value="#{appealDataBean.appealVO.dhhsSentDt}"
						popupDateFormat="MM/dd/yyyy">
						<%-- disabled="true">  commented to enable field--%>
					</m:inputCalendar>
					<m:br />
					<h:message id="PRGCMGTM22" for="DHHS_SentToDHHSDt"
						styleClass="errorMessage" />
				</m:div>
			</m:td>

			<m:td>
				<m:div styleClass="padb">
					<h:outputLabel id="PRGCMGTOLL41" for="DHHS_DHHSDecDue"
						value="#{msg['label.appeals.dhhsform.decisiondue']}">
					</h:outputLabel>
					<m:br />
					<m:inputCalendar monthYearRowClass="yearMonthHeader"
						weekRowClass="weekHeader" id="DHHS_DHHSDecDue"
						currentDayCellClass="currentDayCell" size="10"
						renderAsPopup="true" addResources="true"
						renderPopupButtonAsImage="true"
						value="#{appealDataBean.appealVO.dhhsDecisDueDt}"
						popupDateFormat="MM/dd/yyyy">
						<%-- disabled="true">  commented to enable field--%>
					</m:inputCalendar>
					<m:br />
					<h:message id="PRGCMGTM23" for="DHHS_DHHSDecDue"
						styleClass="errorMessage" />
				</m:div>
			</m:td>

			<m:td>
				<m:div styleClass="padb">
					<h:outputLabel id="PRGCMGTOLL42" for="DHHS_DHHSDecision"
						value="#{msg['label.appeals.dhhsform.dhhsdecision']}">
					</h:outputLabel>
					<m:br />
					<h:selectOneMenu id="DHHS_DHHSDecision"
						value="#{appealDataBean.appealVO.dhhsDecisCd}"
						onchange="javascript:focusThis(this.id);javascript:addeditrow('memberAppealsViewForm'); this.form.submit();javascript:flagWarn=false;">
						<f:selectItems value="#{appealDataBean.appealDHHSDecisionList}" />
					</h:selectOneMenu>
				</m:div>
			</m:td>

			<m:td>
				<m:div styleClass="padb">
					<h:outputLabel id="PRGCMGTOLL43" for="DHHS_NotificSentDt"
						value="#{msg['label.appeals.dhhsform.notifylettersentdate']}">
					</h:outputLabel>
					<m:br />
					<m:inputCalendar monthYearRowClass="yearMonthHeader"
						weekRowClass="weekHeader" id="DHHS_NotificSentDt"
						currentDayCellClass="currentDayCell" size="10"
						renderAsPopup="true" addResources="true"
						renderPopupButtonAsImage="true"
						value="#{appealDataBean.appealVO.dhhsNotfyLtrSentDt}"
						popupDateFormat="MM/dd/yyyy">
						<%-- disabled="true">  commented to enable field--%>
					</m:inputCalendar>
					<m:br />
					<h:message id="PRGCMGTM24" for="DHHS_NotificSentDt"
						styleClass="errorMessage" />
				</m:div>
			</m:td>
		</m:tr>
	</m:table>
</m:section>
