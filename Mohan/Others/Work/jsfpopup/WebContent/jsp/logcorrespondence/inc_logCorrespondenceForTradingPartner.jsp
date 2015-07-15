<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>



<script>
	function plusMinusToggleWithoutHidden(id, obj) {

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

<m:section id="PROVIDERMMS20120731164811376">

	<m:legend>
		<h:outputText id="PRGCMGTOT849" value="#{crspd['label.crspd.crspdfor']}"></h:outputText>
	</m:legend>

	<m:br></m:br>

	<h:outputLabel id="PRGCMGTOLL450" for="trdPatCRN">
		<h:outputText value="#{crspd['label.crspd.corresNum']}" id="trdPatCRN" />
	</h:outputLabel>
	<m:br />
	<h:outputText id="PRGCMGTOT850"		value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceRecordNumber}"></h:outputText>
	<m:div styleClass="clear">
	</m:div>
	<m:div>
		<h:graphicImage id="PROVIDERGI20120731164811377" alt="" url="/images/x.gif" width="1" height="10"
			styleClass="blankImage" />
	</m:div>
	<m:table id="PROVIDERMMT20120731164811378" styleClass="viewTable" width="100%">
		<m:tr>
			<m:td>
				<m:div styleClass="padx">
					<h:outputLabel id="PRGCMGTOLL451" for="entityTypeCodeTrdPat">
						<h:outputText value="#{crspd['label.crspd.entitytype']}"							id="entityTypeCodeTrdPat" />
					</h:outputLabel>
					<m:br />
					<h:outputText id="PRGCMGTOT851"						value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForTradingPartnerVO.entityTypeDesc}">
					</h:outputText>
				</m:div>
			</m:td>
			<m:td>
				<m:div styleClass="padx">
					<h:outputLabel id="PRGCMGTOLL452" for="trdPatId">
						<h:outputText value="#{crspd['label.crspd.trdPatID']}"							id="trdPatId" />
					</h:outputLabel>
					<m:br />
					<h:outputText id="PRGCMGTOT852"						value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForTradingPartnerVO.tradingPartnerId}">
					</h:outputText>
				</m:div>
			</m:td>
			<m:td>
				<m:div styleClass="padx">
					<h:outputLabel id="PRGCMGTOLL453" for="trdPatName">
						<h:outputText value="#{crspd['label.crspd.trdPatName']}"							id="trdPatName" />
					</h:outputLabel>
					<m:br />
					<h:outputText id="PRGCMGTOT853"						value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForTradingPartnerVO.name}">
					</h:outputText>
				</m:div>
			</m:td>
			<m:td>
				<m:div styleClass="padx">
					<h:outputLabel id="PRGCMGTOLL454" for="trdPatClassification">
						<h:outputText value="#{crspd['label.crspd.trdPatClassification']}"							id="trdPatClassification" />
					</h:outputLabel>
					<m:br />
					<h:outputText id="PRGCMGTOT854"						value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForTradingPartnerVO.classificationstr}">
					</h:outputText>
				</m:div>
			</m:td>
			<m:td>
				<m:div styleClass="padx">
					<h:outputLabel id="PRGCMGTOLL455" for="trdPatStatus">
						<h:outputText value="#{crspd['label.crspd.trdPatStatus']}" id="trdPatStatus" />
					</h:outputLabel>
					<m:br />
					<h:outputText id="PRGCMGTOT855"						value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForTradingPartnerVO.statusstr}">
					</h:outputText>
				</m:div>
			</m:td>
			<m:td>
				<m:div styleClass="padx">
					<h:outputLabel id="PRGCMGTOLL456" for="trdPatContact">
					<h:outputText value="#{crspd['label.crspd.trdPatContact']}"						id="tpcontact" />
					</h:outputLabel>
					<m:br></m:br>
					<h:selectOneMenu id="trdPatContact"						value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForTradingPartnerVO.contact}">
						<f:selectItem itemValue=" " itemLabel=" " />
						<f:selectItems value="#{CorrespondenceDataBean.listOfContacts}" />
					</h:selectOneMenu>
				</m:div>
			</m:td>
		</m:tr>		
	</m:table>	
</m:section>
