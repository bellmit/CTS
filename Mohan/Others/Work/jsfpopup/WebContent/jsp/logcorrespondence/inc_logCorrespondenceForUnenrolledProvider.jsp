

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



<m:section id="PROVIDERMMS20120731164811382" styleClass="otherbg">
	<m:legend>
		<h:outputText id="PRGCMGTOT864" value="#{crspd['label.crspd.crspdfor']}"></h:outputText>
	</m:legend>

	<m:br></m:br>

	<h:outputLabel id="PRGCMGTOLL464" for="EdtCRNforUP">
	<h:outputText value="#{crspd['label.crspd.corresNum']}" id="EdtCRNforUP" />
	</h:outputLabel>
	<m:br />
	<h:outputText id="PRGCMGTOT865"		value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceRecordNumber}">
	</h:outputText>
	
	<m:div styleClass="clear">
	</m:div>
	<m:div>
		<h:graphicImage id="PROVIDERGI20120731164811383" alt="" url="/images/x.gif" width="1" height="10" />
	</m:div>	
				
	<m:table id="PROVIDERMMT20120731164811384" styleClass="viewTable" width="100%">
		<m:tr>
			<m:td>
				<m:div styleClass="padx">
					<h:outputLabel id="PRGCMGTOLL465" for="cmEntityforUP">
						<h:outputText value="#{crspd['label.crspd.entityid']}" id="cmEntityforUP" />
					</h:outputLabel>
					<m:br />
					<h:outputText id="PRGCMGTOT866"						value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForVO.entitySysId}">
					</h:outputText>
				</m:div>
			</m:td>
			
			<m:td>
				<m:div styleClass="padx">
					<h:outputLabel id="PRGCMGTOLL466" for="entityTypeCodeforUP">
						<h:outputText value="#{crspd['label.crspd.entitytype']}" id="entityTypeCodeforUP" />
					</h:outputLabel>
					<m:br />
					<h:outputText id="PRGCMGTOT867"						value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForVO.entityTypeDesc}">
					</h:outputText>
				</m:div>
			</m:td>

			<m:td>
				<m:div styleClass="padx">
					<h:outputLabel id="PRGCMGTOLL467" for="statusforUP">
						<h:outputText value="#{crspd['label.crspd.status']}" id="statusforUP" />
					</h:outputLabel>
					<m:br />
					<h:outputText id="PRGCMGTOT868"						value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForVO.status}">
					</h:outputText>
				</m:div>
			</m:td>

			<m:td>
				<m:div styleClass="padx">
					<h:outputLabel id="PRGCMGTOLL468" for="nameforUP">
						<h:outputText value="#{crspd['label.crspd.name']}" id="nameforUP" />
					</h:outputLabel>
					<m:br />
					<h:outputText id="PRGCMGTOT869"						value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForVO.name}">
					</h:outputText>
				</m:div>
			</m:td>
			
			<m:td>
				<m:div styleClass="padx">
					<h:outputLabel id="PRGCMGTOLL469" for="SpeciforUP">
						<h:outputText value="#{crspd['label.crspd.specialty']}" id="SpeciforUP" />
					</h:outputLabel>
					<m:br />
					<h:outputText id="PRGCMGTOT870"						value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForVO.specialityCode}">
					</h:outputText>
				</m:div>
			</m:td>
			
			<m:td>
				<m:div styleClass="padx">
					<h:outputLabel id="PRGCMGTOLL470" for="PTypeforUP">
						<h:outputText value="#{crspd['label.crspd.providertype']}" id="PTypeforUP" />
					</h:outputLabel>
					<m:br />
					<h:outputText id="PRGCMGTOT871"						value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForVO.providerTypeStr}">
					</h:outputText>
				</m:div>
			</m:td>
			
			 <m:td>
				<m:div styleClass="padx">
					<h:outputLabel id="PRGCMGTOLL471" for="contactListForUP">
					<h:outputText value="#{crspd['label.crspd.contact']}" id="contactForUP"/>
					</h:outputLabel>
					<m:br></m:br>

					<h:selectOneMenu id="contactListForUP" disabled="#{CorrespondenceDataBean.crClosed || CorrespondenceDataBean.updateMode}"						value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForVO.contact}">
						<f:selectItem itemValue=" " itemLabel="" />
						<f:selectItems value="#{CorrespondenceDataBean.listOfContacts}" />
					</h:selectOneMenu>
				</m:div>

			</m:td>
			
		</m:tr>
	</m:table>

</m:section>







