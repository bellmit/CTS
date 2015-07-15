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
				<h:panelGroup id="PRGCMGTPGP65">
					<h:commandButton id="inqAbtTrdPat" 					    disabled="#{!CorrespondenceDataBean.controlRequired}"						onclick="javascript:flagWarn=false;if (!(confirm('Are you sure you want to Delete?'))) return(false);focusThis('focusInqHereAftrAdding');"						style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:42px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal;position:relative;top:2px"						value="#{ent['label-sw-delete']}"						action="#{CorrespondenceControllerBean.deleteInquiringAbout}"  />
						
					<h:outputText id="PRGCMGTOT731" escape="false"						value="#{ref['label.ref.linkSeperator']}" />
					
					<h:commandButton id="inqAbtTrdPatCancl" 						onclick="javascript:flagWarn=false;focusThis('focusInqHereAftrAdding');"						style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:42px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal;position:relative;top:2px"						value="#{ent['label-sw-cancel']}"						action="#{CorrespondenceControllerBean.cancelInquiringAbout}"  />	
						
				</h:panelGroup>
			</m:div>
		</m:div>

		<m:table id="PROVIDERMMT20120731164811324" styleClass="viewTable" width="100%">
		<m:tr>
			<m:td>
				<m:div styleClass="padx">
					<h:outputLabel id="PRGCMGTOLL360" for="entityTypeCodeTrdPat">
						<h:outputText value="#{crspd['label.crspd.entitytype']}"							id="entityTypeCodeTrdPat" />
					</h:outputLabel>
					<m:br />
					<h:outputText id="PRGCMGTOT732"						value="#{CorrespondenceDataBean.correspondenceForTradingPartnerVO.entityTypeDesc}">
					</h:outputText>
				</m:div>
			</m:td>
			<m:td>
				<m:div styleClass="padx">
					<h:outputLabel id="PRGCMGTOLL361" for="trdPatId">
						<h:outputText value="#{crspd['label.crspd.trdPatID']}"							id="trdPatId" />
					</h:outputLabel>
					<m:br />
					<h:outputText id="PRGCMGTOT733"						value="#{CorrespondenceDataBean.correspondenceForTradingPartnerVO.tradingPartnerId}">
					</h:outputText>
				</m:div>
			</m:td>
			<m:td>
				<m:div styleClass="padx">
					<h:outputLabel id="PRGCMGTOLL362" for="trdPatName">
						<h:outputText value="#{crspd['label.crspd.trdPatName']}"							id="trdPatName" />
					</h:outputLabel>
					<m:br />
					<h:outputText id="PRGCMGTOT734"						value="#{CorrespondenceDataBean.correspondenceForTradingPartnerVO.name}">
					</h:outputText>
				</m:div>
			</m:td>
			<m:td>
				<m:div styleClass="padx">
					<h:outputLabel id="PRGCMGTOLL363" for="trdPatClassification">
						<h:outputText value="#{crspd['label.crspd.trdPatClassification']}"							id="trdPatClassification" />
					</h:outputLabel>
					<m:br />
					<h:outputText id="PRGCMGTOT735"						value="#{CorrespondenceDataBean.correspondenceForTradingPartnerVO.classification}">
					</h:outputText>
				</m:div>
			</m:td>
			<m:td>
				<m:div styleClass="padx">
					<h:outputLabel id="PRGCMGTOLL364" for="trdPatStatus">
						<h:outputText value="#{crspd['label.crspd.trdPatStatus']}" id="trdPatStatus" />
					</h:outputLabel>
					<m:br />
					<h:outputText id="PRGCMGTOT736"						value="#{CorrespondenceDataBean.correspondenceForTradingPartnerVO.status}">
					</h:outputText>
				</m:div>
			</m:td>
			<m:td>
				<m:div styleClass="padx">
					<h:outputLabel id="PRGCMGTOLL365" for="trdPatContact" />
					<h:outputText value="#{crspd['label.crspd.trdPatContact']}"						id="tpcontact" />
					<m:br></m:br>
					<h:selectOneMenu id="trdPatContact"						value="#{CorrespondenceDataBean.correspondenceForTradingPartnerVO.contact}">
						<f:selectItem itemValue=" " itemLabel=" " />
						<f:selectItems value="#{CorrespondenceDataBean.listOfContacts}" />
					</h:selectOneMenu>
				</m:div>
			</m:td>
		</m:tr>		
	</m:table>
	</m:div>
</m:div>
