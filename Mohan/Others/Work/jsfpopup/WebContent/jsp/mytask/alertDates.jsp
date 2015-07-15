<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>

<portlet:defineObjects />


<f:view>
	<h:inputHidden id="PRGCMGTIH29" value="#{myTaskControllerBean.alertDates}"></h:inputHidden>

	<m:div>
		<m:table id="PROVIDERMMT20120731164811638">
			<m:tbody>
				<m:tr>
					<m:td>
						<br />
						<h:outputLabel id="PRGCMGTOLL642" for="firstdate"							style="color: red; font-weight: bold;"							value="#{messageBean.alertDtMap['label.alertDate.firstDate']}" />
						<h:outputText style="color: red; font-weight: bold;"							id="firstdate" value="#{myTaskDataBean.firstCount}" />
						<br />

					</m:td>
				</m:tr>
				<m:tr>
					<m:td>
						<h:outputLabel id="PRGCMGTOLL643" for="secondDate"							value="#{messageBean.alertDtMap['label.alertDate.secondDate']}" />
						<h:outputText id="secondDate"							value="#{myTaskDataBean.secondCount}" />
						<br />

					</m:td>
				</m:tr>

				<m:tr>
					<m:td>
						<h:outputLabel id="PRGCMGTOLL644" for="thirdDate"							value="#{messageBean.alertDtMap['label.alertDate.thirdDate']}" />
						<h:outputText id="thirdDate" value="#{myTaskDataBean.thirdCount}" />

						<br />
					</m:td>

				</m:tr>
				<m:tr>
					<m:td>
						<h:outputLabel id="PRGCMGTOLL645" for="fourthDate"							value="#{messageBean.alertDtMap['label.alertDate.fourthDate']}" />
						<h:outputText id="fourthDate" value="#{myTaskDataBean.fourCount}" />
						<br />
						<br />
					</m:td>
				</m:tr>
			</m:tbody>
		</m:table>
	</m:div>
</f:view>

