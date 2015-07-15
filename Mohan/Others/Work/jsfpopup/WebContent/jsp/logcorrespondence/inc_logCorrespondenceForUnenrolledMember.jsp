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



<m:section id="PROVIDERMMS20120731164811379" styleClass="otherbg">
	<m:legend>
		<h:outputText id="PRGCMGTOT856" value="#{crspd['label.crspd.crspdfor']}"></h:outputText>
	</m:legend>

	<m:br></m:br>

	<h:outputLabel id="PRGCMGTOLL457" for="EdtCRNforUM">
	<h:outputText value="#{crspd['label.crspd.corresNum']}" id="EdtCRNforUM" />
	</h:outputLabel>
	<m:br />
	<h:outputText id="PRGCMGTOT857"		value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceRecordNumber}">
	</h:outputText>
	
	<m:div styleClass="clear">
	</m:div>
	<m:div>
		<h:graphicImage id="PROVIDERGI20120731164811380" alt="" url="/images/x.gif" width="1" height="10" styleClass="blankImage"/>
	</m:div>	
				
	<m:table id="PROVIDERMMT20120731164811381" styleClass="viewTable" width="100%">
		<m:tr>
			<m:td>
				<m:div styleClass="padx">
					<h:outputLabel id="PRGCMGTOLL458" for="entityTypeCodeforUM">
						<h:outputText value="#{crspd['label.crspd.entitytype']}" id="entityTypeCodeforUM" />
					</h:outputLabel>
					<m:br />
					<h:outputText id="PRGCMGTOT858"						value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForVO.entityTypeDesc}">
					</h:outputText>
				</m:div>
			</m:td>
			
			<m:td>
				<m:div styleClass="padx">
					<h:outputLabel id="PRGCMGTOLL459" for="cmEntityforUM">
						<h:outputText value="#{crspd['label.crspd.cmentityid']}" id="cmEntityforUM" />
					</h:outputLabel>
					<m:br />
<%--modified for CR_ESPRD00439068_UC-PGM-CRM-033_13APR2010
 					<h:outputText id="PRGCMGTOT859"						value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForVO.entitySysId}">--%>
 					<%--Note:Added for DEFECT ESPRD00576206 --%>
 					<h:outputText id="PRGCMGTOT860"	style="color:blue"					value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForVO.entityId}">
<%--EOF CR_ESPRD00439068_UC-PGM-CRM-033_13APR2010 --%>
					</h:outputText>				</m:div>
			</m:td>
			
			<m:td>
				<m:div styleClass="padx">
					<h:outputLabel id="PRGCMGTOLL460" for="SSNforUM">
						<h:outputText value="#{crspd['label.crspd.ssn']}" id="SSNforUM" />
					</h:outputLabel>
					<m:br />
					<h:outputText id="PRGCMGTOT861"						value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForVO.ssn}">
					</h:outputText>
				</m:div>
			</m:td>		
		</m:tr>
		
		<m:tr>
			
			<m:td>
				<m:div styleClass="padx">
					<h:outputLabel id="PRGCMGTOLL461" for="nameforUM">
						<h:outputText value="#{crspd['label.crspd.name']}" id="nameforUM" />
					</h:outputLabel>
					<m:br />
					<h:outputText id="PRGCMGTOT862"						value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForVO.name}">
					</h:outputText>
				</m:div>
			</m:td>
			
			<m:td>
				<m:div styleClass="padx">
					<h:outputLabel id="PRGCMGTOLL462" for="emailforUM">
						<h:outputText value="#{crspd['label.crspd.email']}" id="emailforUM" />
					</h:outputLabel>
					<m:br />
					<h:outputText id="PRGCMGTOT863"						value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForVO.email}">
					</h:outputText>
				</m:div>
			</m:td>
			
			<m:td>
				<m:div styleClass="padx">
					<h:outputLabel id="PRGCMGTOLL463" for="contactListForUM">
					<h:outputText value="#{crspd['label.crspd.contact']}" id="contactForUM"/>
					</h:outputLabel>
					<m:br></m:br>

					<h:selectOneMenu id="contactListForUM" disabled="#{CorrespondenceDataBean.crClosed || CorrespondenceDataBean.updateMode}"						value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForVO.contact}">
						<f:selectItem itemValue=" " itemLabel="" />
						<f:selectItems value="#{CorrespondenceDataBean.contactList}" />
					</h:selectOneMenu>
				</m:div>

			</m:td>
		</m:tr>
	</m:table>

</m:section>
