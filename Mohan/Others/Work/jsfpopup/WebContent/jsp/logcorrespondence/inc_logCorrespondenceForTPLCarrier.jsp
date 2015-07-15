
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
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



<m:section id="PROVIDERMMS20120731164811373" styleClass="otherbg">
	<m:legend>
		<h:outputText id="PRGCMGTOT842" value="#{crspd['label.crspd.crspdfor']}"></h:outputText>
	</m:legend>

	<m:br></m:br>

	<h:outputLabel id="PRGCMGTOLL442" for="tplCRN">
	<h:outputText value="#{crspd['label.crspd.corresNum']}" id="tplCRN" />
	</h:outputLabel>
	<m:br/>
	<h:outputText id="PRGCMGTOT843"		value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceRecordNumber}"></h:outputText>
	
	<m:div styleClass="clear">
	</m:div>
	<m:div>
		<h:graphicImage id="PROVIDERGI20120731164811374" alt="" url="/images/x.gif" width="1" height="10" styleClass="blankImage"/>
	</m:div>

	<m:table id="PROVIDERMMT20120731164811375" styleClass="viewTable" width="100%">


		<m:tr>

			<m:td>
				<m:div styleClass="padx">
					<h:outputLabel id="PRGCMGTOLL443" for="tplEntityTypeCode">
						<h:outputText value="#{crspd['label.crspd.entitytype']}" id="tplEntityTypeCode" />
					</h:outputLabel>
					<m:br />
					<h:outputText id="PRGCMGTOT844"						value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForVO.entityTypeDesc}">
					</h:outputText>
				</m:div>
			</m:td>




			<m:td>

				<m:div styleClass="padx"  rendered="#{!CorrespondenceDataBean.tplPolicyHolder}">
					<h:outputLabel id="PRGCMGTOLL444" for="tplCarrierID">
						<h:outputText value="#{crspd['label.crspd.tplCarrierID']}" id="tplCarrierID" />
					</h:outputLabel>
					<m:br />
					<h:outputText id="PRGCMGTOT845"						value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForVO.carrierID}">
					</h:outputText>

				</m:div>
				<m:div styleClass="padx" rendered="#{CorrespondenceDataBean.tplPolicyHolder}">
					<h:outputLabel id="PRGCMGTOLL445" for="tplPolicyholderID">
						<h:outputText value="TPL Policyholder ID" id="tplPolicyholderID" />
					</h:outputLabel>
					<m:br />
					<h:outputText id="PRGCMGTOT846"						value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForVO.tplPolicyHolderId}">
					</h:outputText>

				</m:div>

			</m:td>


			<m:td>
				<m:div styleClass="padx"  rendered="#{!CorrespondenceDataBean.tplPolicyHolder}">
					<h:outputLabel id="PRGCMGTOLL446" for="tplCarrName">
						<h:outputText value="#{crspd['label.crspd.carrierName']}" id="tplCarrName" />
					</h:outputLabel>
					<m:br />
					<h:outputText id="PRGCMGTOT847"						value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForVO.carrierName}">
					</h:outputText>

				</m:div>
				<m:div styleClass="padx"  rendered="#{CorrespondenceDataBean.tplPolicyHolder}">
					<h:outputLabel id="PRGCMGTOLL447" for="PolicyholderName">
						<h:outputText value="Policyholder Name" id="PolicyholderName" />
					</h:outputLabel>
					<m:br />
					<h:outputText id="PRGCMGTOT848"						value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForVO.policyHolderName}">
					</h:outputText>

				</m:div>

			</m:td>
			
			<m:td>

				<m:div styleClass="padx"  rendered="#{!CorrespondenceDataBean.tplPolicyHolder}">
					<h:outputLabel id="PRGCMGTOLL448" for="PRGCMGTSOM2">
					<h:outputText value="#{crspd['label.crspd.contact']}" id="contactForTPL"/>
					</h:outputLabel>
					<m:br></m:br>

					<h:selectOneMenu id="PRGCMGTSOM2"  disabled="#{CorrespondenceDataBean.crClosed || CorrespondenceDataBean.updateMode}"						value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForVO.contact}">
						<f:selectItem itemValue=" " itemLabel="" />
						<f:selectItems value="#{CorrespondenceDataBean.contactList}" />
					</h:selectOneMenu>
				</m:div>

				<m:div styleClass="padx"  rendered="#{CorrespondenceDataBean.tplPolicyHolder}">
					<h:outputLabel id="PRGCMGTOLL449" for="TPLpolicyGroupID" >
					<h:outputText value="TPL policy Group ID" id="TPLpolicyGroupID"/>
</h:outputLabel>
					<m:br></m:br>

					<h:outputText value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForVO.tplPolicyGroupId}"						 id="TPLpolicyGroupIDValueID"/>
				</m:div>


			</m:td>
			<m:br />
		</m:tr>
	</m:table>
</m:section>





