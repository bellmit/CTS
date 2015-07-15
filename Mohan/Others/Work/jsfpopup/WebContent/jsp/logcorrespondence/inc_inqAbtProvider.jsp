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
				<h:panelGroup id="PRGCMGTPGP64">
					
					<h:commandButton id="inqAbtPrvDel" 					    disabled="#{!CorrespondenceDataBean.controlRequired}"						onclick="javascript:flagWarn=false;if (!(confirm('Are you sure you want to Delete?'))) return(false);focusThis('focusInqHereAftrAdding');"						style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:42px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal;position:relative;top:2px"						value="#{ent['label-sw-delete']}"						action="#{CorrespondenceControllerBean.deleteInquiringAbout}"  />
					
					<h:outputText id="PRGCMGTOT722" escape="false"						value="#{ref['label.ref.linkSeperator']}" />
											
					<h:commandButton id="inqAbtPrvCanl" 						onclick="javascript:flagWarn=false;focusThis('focusInqHereAftrAdding');"						style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:42px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal;position:relative;top:2px"						value="#{ent['label-sw-cancel']}"						action="#{CorrespondenceControllerBean.cancelInquiringAbout}"  />	
					
					
				</h:panelGroup>
			</m:div>
		</m:div>

		<m:table id="PROVIDERMMT20120731164811323" styleClass="viewTable" width="90%">
			<m:tr>
				<m:td>
					<m:div styleClass="padx">
						<h:outputLabel id="PRGCMGTOLL351" for="inqAbtCodeProv">
							<h:outputText value="#{crspd['label.crspd.entitytype']}"								id="inqAbtCodeProv" />
						</h:outputLabel>
						<m:br />
						<h:outputText id="PRGCMGTOT723"							value="#{CorrespondenceDataBean.correspondenceForProviderVO.entityTypeDesc}">
						</h:outputText>
					</m:div>
				</m:td>
				<m:td>
					<m:div styleClass="padx">
						<h:outputLabel id="PRGCMGTOLL352" for="inqAbtProvID">
							<h:outputText value="#{crspd['label.crspd.providerid']}"								id="inqAbtProvID" />
						</h:outputLabel>
						<m:br />
						<h:outputText id="PRGCMGTOT724"							value="#{CorrespondenceDataBean.correspondenceForProviderVO.entityId}">
						</h:outputText>
					</m:div>
				</m:td>
				<m:td>
					<m:div styleClass="padx">
						<h:outputLabel id="PRGCMGTOLL353" for="inqAbtPayerID">
							<h:outputText value="#{crspd['label.crspd.payerid']}"								id="inqAbtPayerID" />
						</h:outputLabel>
						<m:br />
						<h:outputText id="PRGCMGTOT725"							value="#{CorrespondenceDataBean.correspondenceForProviderVO.payerID}">
						</h:outputText>
					</m:div>
				</m:td>
				<m:td>
					<m:div styleClass="padx">
						<h:outputLabel id="PRGCMGTOLL354" for="inqAbtStatusProv">
							<h:outputText value="#{crspd['label.crspd.status']}"								id="inqAbtStatusProv" />
						</h:outputLabel>
						<m:br />
						<h:outputText id="PRGCMGTOT726"							value="#{CorrespondenceDataBean.correspondenceForProviderVO.statsuSstr}">
						</h:outputText>
					</m:div>
				</m:td>
				<m:td>
					<m:div styleClass="padx">
						<h:outputLabel id="PRGCMGTOLL355" for="inqAbtDTCProv">
							<h:outputText value="#{crspd['label.crspd.dtc']}"								id="inqAbtDTCProv" />
						</h:outputLabel>
						<m:br />
						<h:outputText id="PRGCMGTOT727"							value="#{CorrespondenceDataBean.correspondenceRecordVO.daysToClose}">
						</h:outputText>
					</m:div>
				</m:td>
				<m:br />
			</m:tr>

			<m:tr>
				<m:td>
					<m:div styleClass="padx">
						<h:outputLabel id="PRGCMGTOLL356" for="inqAbtProvName">
							<h:outputText value="#{crspd['label.crspd.name']}"								id="inqAbtProvName" />
						</h:outputLabel>
						<m:br />
						<h:outputText id="PRGCMGTOT728"							value="#{CorrespondenceDataBean.correspondenceForProviderVO.name}">
						</h:outputText>
					</m:div>
				</m:td>
				<m:td>
					<m:div styleClass="padx">
						<h:outputLabel id="PRGCMGTOLL357" for="inqAbtSpecialty">
							<h:outputText value="#{crspd['label.crspd.specialty']}"								id="inqAbtSpecialty" />
						</h:outputLabel>
						<m:br />
						<h:outputText id="PRGCMGTOT729"							value="#{CorrespondenceDataBean.correspondenceForProviderVO.specialtyStr}">
						</h:outputText>
					</m:div>
				</m:td>
				<m:td>
					<m:div styleClass="padx">
						<h:outputLabel id="PRGCMGTOLL358" for="inqAbtProvType">
							<h:outputText value="#{crspd['label.crspd.providertype']}"								id="inqAbtProvType" />
						</h:outputLabel>
						<m:br />
						<h:outputText id="PRGCMGTOT730"							value="#{CorrespondenceDataBean.correspondenceForProviderVO.providerTypeStr}">
						</h:outputText>
					</m:div>
				</m:td>
				<m:td>
					<m:div styleClass="padx">
						<h:outputLabel id="PRGCMGTOLL359" for="inqAbtProvCntct">
							<h:outputText value="#{crspd['label.crspd.contact']}"								id="inqAbtProvContact" />
						</h:outputLabel>
						<m:br />
						<h:selectOneMenu id="inqAbtProvCntct"							value="#{CorrespondenceDataBean.correspondenceForProviderVO.contact}">
							<f:selectItem itemValue=" " itemLabel="" />
							<f:selectItems value="#{CorrespondenceDataBean.listOfContacts}" />
						</h:selectOneMenu>
					</m:div>
				</m:td>
			</m:tr>
		</m:table>
	</m:div>
</m:div>
