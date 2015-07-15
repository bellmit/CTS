<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>



<script>
function plusMinusToggleWithoutHidden(id,obj) {

		 var el = document.getElementById(id);
		 if (el.style.display == 'none') {
		  obj.className = 'plus';
		 } else if (el.style.display == 'block') {
		  obj.className = 'minus';
		 } else if (el.style.display == '') {
		  obj.className = 'minus';
		 }
	}

</script>

<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.ProgramMessages"
	var="ref" />

<m:div>
	<m:div styleClass="moreInfo">
		<m:div styleClass="moreInfoBar">
			<m:div styleClass="infoActions">
				<h:panelGroup id="PRGCMGTPGP62">
					<h:commandButton id="inqAbtForDel" 				    	disabled="#{!CorrespondenceDataBean.controlRequired}"						onclick="javascript:flagWarn=false;if (!(confirm('Are you sure you want to Delete?'))) return(false);focusThis('focusInqHereAftrAdding');"						style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:42px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal;position:relative;top:2px"						value="#{ent['label-sw-delete']}"						action="#{CorrespondenceControllerBean.deleteInquiringAbout}"  />
					<h:outputText id="PRGCMGTOT702" escape="false"							value="#{ref['label.ref.linkSeperator']}" />
					<h:commandButton id="inqAbtForCanl"						onclick="javascript:flagWarn=false;focusThis('focusInqHereAftrAdding');"						style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:42px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal;position:relative;top:2px"						value="#{ent['label-sw-cancel']}"						action="#{CorrespondenceControllerBean.cancelInquiringAbout}"  />
				</h:panelGroup>
			</m:div>
		</m:div>

		<m:table id="PROVIDERMMT20120731164811321" styleClass="viewTable" width="90%">
			<m:tr>
				<m:td>
					<m:div styleClass="padx">
						<h:outputLabel id="PRGCMGTOLL333" for="inqAbtTypeCode">
							<h:outputText value="#{crspd['label.crspd.entitytype']}"								id="inqAbtTypeCode" />
						</h:outputLabel>
						<m:br />
						<h:outputText id="PRGCMGTOT703"							value="#{CorrespondenceDataBean.correspondenceForVO.entityTypeDesc}">
						</h:outputText>
					</m:div>
				</m:td>
				<m:td>
					<m:div styleClass="padx">
						<h:outputLabel id="PRGCMGTOLL334" for="inqAbtCMId">
							<h:outputText value="#{crspd['label.crspd.cmentityid']}"								id="inqAbtCMId" />
						</h:outputLabel>
						<m:br />
						<h:outputText id="PRGCMGTOT704"							value="#{CorrespondenceDataBean.correspondenceForVO.entityId}">
						</h:outputText>
					</m:div>
				</m:td>
				<m:td>
					<m:div styleClass="padx">
						<h:outputLabel id="PRGCMGTOLL335" for="inqAbtName">
							<h:outputText value="#{crspd['label.crspd.name']}"								id="inqAbtName" />
						</h:outputLabel>
						<m:br />
						<h:outputText id="PRGCMGTOT705"							value="#{CorrespondenceDataBean.correspondenceForVO.name}">
						</h:outputText>
					</m:div>
				</m:td>
				<m:td>
					<m:div styleClass="padx">
						<h:outputLabel id="PRGCMGTOLL336" for="inqAbtStatus">
							<h:outputText value="#{crspd['label.crspd.status']}"								id="inqAbtStatus" />
						</h:outputLabel>
						<m:br />
						<h:outputText id="PRGCMGTOT706"							value="#{CorrespondenceDataBean.correspondenceForVO.status}">
						</h:outputText>
					</m:div>
				</m:td>
				<m:td>
					<m:div styleClass="padx">
						<h:outputLabel id="PRGCMGTOLL337" for="inqAbtDTC">
							<h:outputText value="#{crspd['label.crspd.dtc']}" id="inqAbtDTC" />
						</h:outputLabel>
						<m:br />
						<h:outputText id="PRGCMGTOT707"							value="#{CorrespondenceDataBean.correspondenceRecordVO.daysToClose}">
						</h:outputText>
					</m:div>
				</m:td>
				<m:br />
			</m:tr>
		</m:table>
	</m:div>
</m:div>







