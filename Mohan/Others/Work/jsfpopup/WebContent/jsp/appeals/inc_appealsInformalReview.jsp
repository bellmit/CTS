
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<portlet:defineObjects />

<m:div>
	<m:br />
	<m:br />
</m:div>
<m:section id="PROVIDERMMS2012073116481138" styleClass="otherbg">
	<m:legend>

		<h:outputText id="PRGCMGTOT74"
			value="#{msg['label.appeals.infrev.informalreview']}" />

	</m:legend>

	<m:table id="PROVIDERMMT2012073116481139" cellspacing="0" width="100%">
		<m:tr>
			<m:td>
				<m:div styleClass="padb">
					<h:outputLabel id="PRGCMGTOLL50" for="infReview_ReqDt"
						value="#{msg['label.appeals.infrev.requesteddate']}">
					</h:outputLabel>
					<m:br />
					<m:inputCalendar monthYearRowClass="yearMonthHeader"
						weekRowClass="weekHeader" id="infReview_ReqDt"
						currentDayCellClass="currentDayCell" size="10"
						renderAsPopup="true" addResources="true"
						renderPopupButtonAsImage="true"
						value="#{appealDataBean.appealVO.infrmlRevwReqDt}"
						popupDateFormat="MM/dd/yyyy">
						<%-- disabled="true">  commented to enable field--%>
					</m:inputCalendar>
					<m:br />
					<h:message id="PRGCMGTM26" for="infReview_ReqDt"
						styleClass="errorMessage" />
				</m:div>
			</m:td>

			<m:td>
				<m:div styleClass="padb">
					<h:outputLabel id="PRGCMGTOLL51" for="infReview_AckDt"
						value="#{msg['label.appeals.infrev.acksentdate']}">
					</h:outputLabel>
					<m:br />
					<m:inputCalendar monthYearRowClass="yearMonthHeader"
						weekRowClass="weekHeader" id="infReview_AckDt"
						currentDayCellClass="currentDayCell" size="10"
						renderAsPopup="true" addResources="true"
						renderPopupButtonAsImage="true"
						value="#{appealDataBean.appealVO.infrmlRevwAckSentDt}"
						popupDateFormat="MM/dd/yyyy">
						<%-- disabled="true">  commented to enable field--%>
					</m:inputCalendar>
					<m:br />
					<h:message id="PRGCMGTM27" for="infReview_AckDt"
						styleClass="errorMessage" />
				</m:div>
			</m:td>

			<m:td>
				<m:div styleClass="padb">
					<h:outputLabel id="PRGCMGTOLL52" for="infReview_SntReviewDt"
						value="#{msg['label.appeals.infrev.sentforrevdate']}">
					</h:outputLabel>
					<m:br />
					<m:inputCalendar monthYearRowClass="yearMonthHeader"
						weekRowClass="weekHeader" id="infReview_SntReviewDt"
						currentDayCellClass="currentDayCell" size="10"
						renderAsPopup="true" addResources="true"
						renderPopupButtonAsImage="true"
						value="#{appealDataBean.appealVO.infrmlRevwSentDt}"
						popupDateFormat="MM/dd/yyyy">
						<%-- disabled="true">  commented to enable field--%>
					</m:inputCalendar>
					<m:br />
					<h:message id="PRGCMGTM28" for="infReview_SntReviewDt"
						styleClass="errorMessage" />
				</m:div>
			</m:td>

			<m:td>
				<m:div styleClass="padb">
					<h:outputLabel id="PRGCMGTOLL53" for="infReview_DueDt"
						value="#{msg['label.appeals.infrev.duedate']}">
					</h:outputLabel>
					<m:br />
					<m:inputCalendar monthYearRowClass="yearMonthHeader"
						weekRowClass="weekHeader" id="infReview_DueDt"
						currentDayCellClass="currentDayCell" size="10"
						renderAsPopup="true" addResources="true"
						renderPopupButtonAsImage="true"
						value="#{appealDataBean.appealVO.infrmlRevwDueDt}"
						popupDateFormat="MM/dd/yyyy">
						<%-- disabled="true">  commented to enable field--%>
					</m:inputCalendar>
					<m:br />
					<h:message id="PRGCMGTM29" for="infReview_DueDt"
						styleClass="errorMessage" />
				</m:div>
			</m:td>

		</m:tr>
	</m:table>
</m:section>
