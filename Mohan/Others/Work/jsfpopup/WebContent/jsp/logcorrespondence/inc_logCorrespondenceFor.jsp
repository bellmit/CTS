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



<m:section id="PROVIDERMMS20120731164811364" styleClass="otherbg">
	<m:legend>
		<h:outputText id="PRGCMGTOT811" value="#{crspd['label.crspd.crspdfor']}"></h:outputText>
	</m:legend>

	<m:br></m:br>

	<h:outputLabel id="PRGCMGTOLL413" for="specCRN">
		<h:outputText value="#{crspd['label.crspd.corresNum']}" id="specCRN" />
	</h:outputLabel>
	<m:br/>
	<h:outputText id="PRGCMGTOT812"		value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceRecordNumber}"></h:outputText>
	<m:div styleClass="clear">
	</m:div>
	<m:div>
		<h:graphicImage id="PROVIDERGI20120731164811365" alt="" url="/images/x.gif" width="1" height="10" styleClass="blankImage" />
	</m:div>
	
	<m:table id="PROVIDERMMT20120731164811366" styleClass="viewTable" width="100%">


		<m:tr>

			<m:td>
				<m:div styleClass="padx">
					<h:outputLabel id="PRGCMGTOLL414" for="entityTypeCode">
						<h:outputText value="#{crspd['label.crspd.entitytype']}" id="entityTypeCode" />
					</h:outputLabel>
					<m:br />
<%--modified for CR_ESPRD00439068_UC-PGM-CRM-033_13APR2010 --%>
					<h:outputText id="PRGCMGTOT813" value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForVO.entityTypeDesc}">
					</h:outputText>
				<%--		value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForVO.entityTypeDesc}"
EOF CR_ESPRD00439068_UC-PGM-CRM-033_13APR2010--%>

				</m:div>
			</m:td>




			<m:td>

				<m:div styleClass="padx">
					<h:outputLabel id="PRGCMGTOLL415" for="cmEntityI">
						<h:outputText value="#{crspd['label.crspd.cmentityid']}" id="cmEntityI" />
					</h:outputLabel>
					<m:br />
					<h:outputText id="PRGCMGTOT814"						value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForVO.entitySysId}">
					</h:outputText>

				</m:div>
			</m:td>


			<m:td>
				<m:div styleClass="padx">
					<h:outputLabel id="PRGCMGTOLL416" for="name">
						<h:outputText value="#{crspd['label.crspd.name']}" id="name" />
					</h:outputLabel>
					<m:br />
					<h:outputText id="PRGCMGTOT815"						value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForVO.name}">
					</h:outputText>

				</m:div>

			</m:td>
			
						 <m:td>
				<m:div styleClass="padx">
					<h:outputLabel id="PRGCMGTOLL417" for="contactListForOth" >
					<h:outputText value="#{crspd['label.crspd.contact']}" id="contactForOth"/>
					</h:outputLabel>
					<m:br></m:br>

					<h:selectOneMenu id="contactListForOth" disabled="#{CorrespondenceDataBean.crClosed || CorrespondenceDataBean.updateMode}"						value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForVO.contact}">
						<f:selectItem itemValue=" " itemLabel=" " />
						<f:selectItems value="#{CorrespondenceDataBean.listOfContacts}" />
					</h:selectOneMenu>
				</m:div>

			</m:td>
			
			<m:br />
		</m:tr>


	</m:table>



</m:section>







