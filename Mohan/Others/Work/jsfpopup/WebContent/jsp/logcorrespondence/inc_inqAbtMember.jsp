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
				<h:panelGroup id="PRGCMGTPGP63">
					<h:commandButton id="inqAbtMemDel" 					    disabled="#{!CorrespondenceDataBean.controlRequired}"						onclick="javascript:flagWarn=false;if (!(confirm('Are you sure you want to Delete?'))) return(false);focusThis('focusInqHereAftrAdding');"						style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:42px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal;position:relative;top:2px"						value="#{ent['label-sw-delete']}"						action="#{CorrespondenceControllerBean.deleteInquiringAbout}"  />
						
					<h:outputText id="PRGCMGTOT708" escape="false"						value="#{ref['label.ref.linkSeperator']}" />
					
					<h:commandButton id="inqAbtMemCancl" 						onclick="javascript:flagWarn=false;focusThis('focusInqHereAftrAdding');"						style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:42px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal;position:relative;top:2px"						value="#{ent['label-sw-cancel']}"						action="#{CorrespondenceControllerBean.cancelInquiringAbout}"  />	
						
				</h:panelGroup>
			</m:div>
		</m:div>

		<m:table id="PROVIDERMMT20120731164811322" styleClass="viewTable" width="90%">
			<m:tr>
				<m:td>
					<m:div styleClass="padx">
						<h:outputLabel id="PRGCMGTOLL338" for="inqAbtCodeMem">
							<h:outputText value="#{crspd['label.crspd.entitytype']}"								id="inqAbtCodeMem" />
						</h:outputLabel>
						<m:br />
						<h:outputText id="PRGCMGTOT709"							value="#{CorrespondenceDataBean.correspondenceForMemberVO.entityTypeDesc}">
						</h:outputText>
					</m:div>
				</m:td>
				<m:td>
					<m:div styleClass="padx">
						<h:outputLabel id="PRGCMGTOLL339" for="inqAbtMemberId">
							<h:outputText value="#{crspd['label.crspd.memberid']}"								id="inqAbtMemberId" />
						</h:outputLabel>
						<m:br />
							<%-- Code modified to replace the entityId with currentAlt id to display the correct member id for defect - ESPRD00888781 --%>
						<h:outputText id="PRGCMGTOT710"							value="#{CorrespondenceDataBean.correspondenceForMemberVO.currAltID}">
						</h:outputText>
					</m:div>
				</m:td>
				<m:td>
					<m:div styleClass="padx">
						<h:outputLabel id="PRGCMGTOLL340" for="inqAbtSSN">
							<h:outputText value="#{crspd['label.crspd.ssn']}" id="inqAbtSSN" />
						</h:outputLabel>
						<m:br />
						<h:outputText id="PRGCMGTOT711"							value="#{CorrespondenceDataBean.correspondenceForMemberVO.socialSecurityNumber}">
						</h:outputText>
					</m:div>
				</m:td>
				<m:td>
					<m:div styleClass="padx">
						<h:outputLabel id="PRGCMGTOLL341" for="inqAbtStatusMem">
							<h:outputText value="#{crspd['label.crspd.status']}"								id="inqAbtStatusMem" />
						</h:outputLabel>
						<m:br />
						<h:outputText id="PRGCMGTOT712"							value="#{CorrespondenceDataBean.correspondenceForMemberVO.statusStr}">
						</h:outputText>
					</m:div>
				</m:td>
				<m:td>
					<m:div styleClass="padx">
						<h:outputLabel id="PRGCMGTOLL342" for="inqAbtDTCMem">
							<h:outputText value="#{crspd['label.crspd.dtc']}"								id="inqAbtDTCMem" />
						</h:outputLabel>
						<m:br />
						<h:outputText id="PRGCMGTOT713"							value="#{CorrespondenceDataBean.correspondenceRecordVO.daysToClose}">
						</h:outputText>
					</m:div>
				</m:td>
				<m:br />
			</m:tr>

			<m:tr>
				<m:td>
					<m:div styleClass="padx">
						<h:outputLabel id="PRGCMGTOLL343" for="inqAbtNameMem">
							<h:outputText value="#{crspd['label.crspd.name']}"								id="inqAbtNameMem" />
						</h:outputLabel>
						<m:br />
						<h:outputText id="PRGCMGTOT714"							value="#{CorrespondenceDataBean.correspondenceForMemberVO.name}">
						</h:outputText>
					</m:div>
				</m:td>
				<m:td>
					<m:div styleClass="padx">
						<h:outputLabel id="PRGCMGTOLL344" for="inqAbtEmail">
							<h:outputText value="#{crspd['label.crspd.email']}"								id="inqAbtEmail" />
						</h:outputLabel>
						<m:br />
						<h:outputText id="PRGCMGTOT715"							value="#{CorrespondenceDataBean.correspondenceForMemberVO.email}">
						</h:outputText>
					</m:div>
				</m:td>
			</m:tr>
			<m:tr>
				<m:td>
					<m:div styleClass="padx">
						<h:outputLabel id="PRGCMGTOLL345" for="inqAbtAnb">
							<h:outputText value="#{crspd['label.crspd.coe']}" id="inqAbtAnb" />
						</h:outputLabel>
						<m:br />
						<h:outputText id="PRGCMGTOT716"							value="#{CorrespondenceDataBean.correspondenceForMemberVO.categoryOfEligibility}">
						</h:outputText>
					</m:div>
				</m:td>
				<m:td>
					<m:div styleClass="padx">
						<h:outputLabel id="PRGCMGTOLL346" for="inqAbtDO">
							<h:outputText value="#{crspd['label.crspd.distOffice']}"								id="inqAbtDO" />
						</h:outputLabel>
						<m:br />
						<h:outputText id="PRGCMGTOT717"							value="#{CorrespondenceDataBean.correspondenceForMemberVO.districtOfficedesc}">
						</h:outputText>
					</m:div>
				</m:td>
				<m:td>
					<m:div styleClass="padx">
						<h:outputLabel id="PRGCMGTOLL347" for="inqAbtDOB">
							<h:outputText value="#{crspd['label.crspd.dob']}" id="inqAbtDOB" />
						</h:outputLabel>
						<m:br />
						<h:outputText id="PRGCMGTOT718"							value="#{CorrespondenceDataBean.correspondenceForMemberVO.dob}">
						</h:outputText>
					</m:div>
				</m:td>
				<m:td>
					<m:div styleClass="padx">
						<h:outputLabel id="PRGCMGTOLL348" for="inqAbtPrevName">
							<h:outputText value="#{crspd['label.crspd.prevName']}"								id="inqAbtPrevName" />
						</h:outputLabel>
						<m:br />
						<h:outputText id="PRGCMGTOT719"							value="#{CorrespondenceDataBean.correspondenceForMemberVO.previousName}">
						</h:outputText>
					</m:div>
				</m:td>
			</m:tr>
			<m:tr>
				<m:td>
					<m:div styleClass="padx">
						<h:outputLabel id="PRGCMGTOLL349" for="inqAbtResAddr">
							<h:outputText value="#{crspd['label.crspd.residentialaddress']}"								id="inqAbtResAddr" />
						</h:outputLabel>
						<m:br />
						<h:outputText id="PRGCMGTOT720"							value="#{CorrespondenceDataBean.correspondenceForMemberVO.resAddress}">
						</h:outputText>
					</m:div>
				</m:td>
				<m:td>
					<m:div styleClass="padx">
						<h:outputLabel id="PRGCMGTOLL350" for="inqAbtmailAddr">
							<h:outputText value="#{crspd['label.crspd.mailingAddress']}"								id="inqAbtmailAddr" />
						</h:outputLabel>
						<m:br />
						<h:outputText id="PRGCMGTOT721"							value="#{CorrespondenceDataBean.correspondenceForMemberVO.mailAddress}">
						</h:outputText>
					</m:div>
				</m:td>
			</m:tr>
		</m:table>
	</m:div>
</m:div>
