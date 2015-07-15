<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
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

<m:section id="PROVIDERMMS20120731164811367">

	<m:legend>
		<h:outputText id="PRGCMGTOT816" value="#{crspd['label.crspd.crspdfor']}"></h:outputText>
	</m:legend>

	<m:br></m:br>

	<h:outputLabel id="PRGCMGTOLL418" for="memCRN">
		<h:outputText value="#{crspd['label.crspd.corresNum']}" id="memCRN" />
	</h:outputLabel>
	<m:br/>
	<h:outputText id="PRGCMGTOT817"		value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceRecordNumber}"></h:outputText>
	<m:div styleClass="clear">
	</m:div>
	<m:div>
		<h:graphicImage id="PROVIDERGI20120731164811368" alt="" url="/images/x.gif" width="1" height="10" styleClass="blankImage" />
	</m:div>

		<m:table id="PROVIDERMMT20120731164811369" styleClass="viewTable" width="100%">


			<m:tr>

				<m:td>

					<m:div styleClass="padx">
						<h:outputLabel id="PRGCMGTOLL419" for="entityTypeCodeMem">
							<h:outputText value="#{crspd['label.crspd.entitytype']}" id="entityTypeCodeMem" />
						</h:outputLabel>
						<m:br />
						<h:outputText id="PRGCMGTOT818"							value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForMemberVO.entityTypeDesc}">
						</h:outputText>
					</m:div>
				</m:td>


				<m:td>
					<m:div styleClass="padx">
						<h:outputLabel id="PRGCMGTOLL420" for="memberId">
							<h:outputText value="#{crspd['label.crspd.memberid']}" id="memberId" />
						</h:outputLabel>
						<m:br />

						<h:outputText id="PRGCMGTOT819" value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForMemberVO.currAltID}">
						</h:outputText>
				
					</m:div>

				</m:td>
				<m:td>
					<m:div styleClass="padx">
						<h:outputLabel id="PRGCMGTOLL421" for="socialSecurityNumber">
							<h:outputText value="#{crspd['label.crspd.ssn']}" id="socialSecurityNumber" />
						</h:outputLabel>
						<m:br />
						<h:outputText id="PRGCMGTOT820"							value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForMemberVO.socialSecurityNumber}">
						</h:outputText>
					</m:div>

				</m:td>
				<m:td>
					<m:div styleClass="padx">
						<h:outputLabel id="PRGCMGTOLL422" for="memIdType">
							<h:outputText value="#{crspd['label.crspd.idType']}" id="memIdType" />
						</h:outputLabel>
						<m:br />
						<h:outputText id="PRGCMGTOT821"							value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForMemberVO.idTypeDesc}">
						</h:outputText>
					</m:div>
				</m:td>
				
				<m:br />
			</m:tr>

			<m:tr>
				<m:td>
					<m:div styleClass="padx">
						<h:outputLabel id="PRGCMGTOLL423" for="nameMem">
							<h:outputText value="#{crspd['label.crspd.name']}" id="nameMem" />
						</h:outputLabel>
						<m:br />
						<h:outputText id="PRGCMGTOT822"							value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForMemberVO.name}">
						</h:outputText>
					</m:div>

				</m:td>
				<m:td>
					<m:div styleClass="padx">
						<h:outputLabel id="PRGCMGTOLL424" for="email">
							<h:outputText value="#{crspd['label.crspd.email']}" id="email" />
						</h:outputLabel>
						<m:br />
						<h:outputText id="PRGCMGTOT823"							value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForMemberVO.email}">
						</h:outputText>
					</m:div>

				</m:td>
			</m:tr>
			<m:tr>
				<m:td>
					<m:div styleClass="padx">
						<h:outputLabel id="PRGCMGTOLL425" for="anb">
							<h:outputText value="#{crspd['label.crspd.coe']}"								id="anb" />
						</h:outputLabel>
						<m:br />

						<h:outputText id="PRGCMGTOT824"							value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForMemberVO.categoryOfEligibility}">
						</h:outputText>
					</m:div>

				</m:td>
				<m:td>
				</m:td>


				<m:td>
					<m:div styleClass="padx">
						<h:outputLabel id="PRGCMGTOLL426" for="districtOffice">
							<h:outputText value="#{crspd['label.crspd.distOffice']}" id="districtOffice" />
						</h:outputLabel>
						<m:br />

						<h:outputText id="PRGCMGTOT825"							value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForMemberVO.districtOfficedesc}">
						</h:outputText>
					</m:div>

				</m:td>



				<m:td>
					<m:div styleClass="padx">
						<h:outputLabel id="PRGCMGTOLL427" for="dob">
							<h:outputText value="#{crspd['label.crspd.dob']}" id="dob" />
						</h:outputLabel>
						<m:br />

						<h:outputText id="PRGCMGTOT826"							value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForMemberVO.dob}">
						</h:outputText>
					</m:div>

				</m:td>

				<m:td>
					<m:div styleClass="padx">
						<h:outputLabel id="PRGCMGTOLL428" for="previousName">
							<h:outputText value="#{crspd['label.crspd.prevName']}" id="previousName" />
						</h:outputLabel>
						<m:br />

						<h:outputText id="PRGCMGTOT827"							value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForMemberVO.previousName}">
						</h:outputText>
					</m:div>

				</m:td>





			</m:tr>

			<m:tr>
				<m:td>
					<m:div styleClass="padx">
						<h:outputLabel id="PRGCMGTOLL429" for="resAddress">
							<h:outputText value="#{crspd['label.crspd.residentialaddress']}"								id="resAddress" />
						</h:outputLabel>
						<m:br />

						<h:outputText id="PRGCMGTOT828"		escape="false"		value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForMemberVO.resAddress}">
						</h:outputText>
					</m:div>

				</m:td>



				<m:td>
					<m:div styleClass="padx">
						<h:outputLabel id="PRGCMGTOLL430" for="mailingAddress">
							<h:outputText value="#{crspd['label.crspd.mailingAddress']}"								id="mailingAddress" />
						</h:outputLabel>
						<m:br />

						<h:outputText id="PRGCMGTOT829"		escape="false"		value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForMemberVO.mailAddress}">
						</h:outputText>
					</m:div>

				</m:td>
				
				<m:td>
					<m:div styleClass="padx">
					<h:outputLabel id="PRGCMGTOLL431" for="memContact">
					<h:outputText value="#{crspd['label.crspd.contact']}" id="membercontact"/>
					</h:outputLabel>
					<m:br></m:br>

					<h:selectOneMenu id="memContact"						value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForMemberVO.contact}">
						<f:selectItem itemValue=" " itemLabel=" " />
						<f:selectItems value="#{CorrespondenceDataBean.listOfContacts}" />
					</h:selectOneMenu>
					</m:div>

				</m:td>

			</m:tr>
		</m:table>

<f:subview id="logCrspdForMemberSubview">
	<m:section styleClass="otherbg" id="memAltId">
		<m:legend>
			<h:outputLink					onclick="setHiddenValue('logCorrespondence:logCrspdForMemSubview:logCrspdForMemberSubview:memeberHidden','minus');toggle('corrForMem',2);							plusMinusToggle('corrForMem',this,'logCorrespondence:logCrspdForMemSubview:logCrspdForMemberSubview:memeberHidden');							 return false;"					id="plusMinusMember" styleClass="plus">
				<h:outputText id="PRGCMGTOT830" value="#{crspd['label.crspd.alternativeIdentifiers']}"  />
				<h:inputHidden id="memeberHidden"						value="#{CorrespondenceDataBean.crspdMemHidden}"></h:inputHidden>			
			</h:outputLink>
		</m:legend>
	<m:div sid="corrForMem">
		<h:dataTable id="memAltIdTb" width="100%"				styleClass="dataTable" rows="10" var="altIdDetails"				columnClasses="columnClass" headerClass="headerClass"				footerClass="footerClass" rowClasses="row_alt,row"				onmouseover="return tableMouseOver(this, event);"				onmouseout="return tableMouseOut(this, event);"				value="#{CorrespondenceDataBean.listOfAlternateIds}">

				<h:column id="typeCol">
					<f:facet name="header">
						<h:panelGrid id="PRGCMGTPGD100" columns="2">
							<h:outputLabel for="typeLabelGrp" id="typeLabel" value="#{crspd['label.crspd.type']}" />
							<h:panelGroup id="typeLabelGrp">
								<t:div style="width x;align=left">
									<t:commandLink id="typeAscCmdLink"										rendered="#{CorrespondenceDataBean.imageRender != 'typeAscCmdLink'}"										actionListener="#{CorrespondenceControllerBean.sortAssociatedIdentifiers}" 										style="text-decoration: none;">
										<h:graphicImage  id="sortascid1" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}"
											styleClass="sort_asc" width="8" url="/images/x.gif"/>
										<f:attribute name="#{crspd['id.crpsd.columnName']}"
											value="#{crspd['label.crspd.type']}" />
										<f:attribute name="#{crspd['id.crpsd.sortOrder']}"
											value="#{crspd['value.crpsd.ascending']}" />
									</t:commandLink>
								</t:div>
								<t:div style="width x;align=left">
									<t:commandLink id="typeDscCmdLink"										rendered="#{CorrespondenceDataBean.imageRender != 'typeDscCmdLink'}"										actionListener="#{CorrespondenceControllerBean.sortAssociatedIdentifiers}" 										style="text-decoration: none;">
										<h:graphicImage  id="sortdescid1" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}"
											styleClass="sort_desc" width="8" url="/images/x.gif" />
										<f:attribute name="#{crspd['id.crpsd.columnName']}"
											value="#{crspd['label.crspd.type']}" />
										<f:attribute name="#{crspd['id.crpsd.sortOrder']}"
											value="#{crspd['value.crpsd.descending']}" />
									</t:commandLink>
								</t:div>
							</h:panelGroup>
						</h:panelGrid>
					</f:facet>

					<h:outputText id="valType"						value="#{altIdDetails.alternateIDTypeCodestr}" />

				</h:column>
				
				<h:column id="altIdCol">
					<f:facet name="header">
						<h:panelGrid id="PRGCMGTPGD101" columns="2">
							<h:outputLabel for="altIdLabelGrpVal" id="altIdLabel" value="#{crspd['label.crspd.altId']}" />
							<h:panelGroup id="altIdLabelGrpVal">
								<t:div style="width x;align=left">
									<t:commandLink id="altIdAscCmdLink"										rendered="#{CorrespondenceDataBean.imageRender != 'altIdAscCmdLink'}"										actionListener="#{CorrespondenceControllerBean.sortAssociatedIdentifiers}"										style="text-decoration: none;">
										<h:graphicImage  id="sortascid2" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}"
											styleClass="sort_asc" width="8" url="/images/x.gif"/>
										<f:attribute name="#{crspd['id.crpsd.columnName']}"
											value="#{crspd['label.crspd.altId']}" />
										<f:attribute name="#{crspd['id.crpsd.sortOrder']}"
											value="#{crspd['value.crpsd.ascending']}" />
									</t:commandLink>
								</t:div>
								<t:div style="width x;align=left">
									<t:commandLink id="altIdDscCmdLink"										rendered="#{CorrespondenceDataBean.imageRender != 'altIdDscCmdLink'}"										actionListener="#{CorrespondenceControllerBean.sortAssociatedIdentifiers}"										style="text-decoration: none;">
										<h:graphicImage  id="sortdescid2" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}"
											styleClass="sort_desc" width="8" url="/images/x.gif" />
										<f:attribute name="#{crspd['id.crpsd.columnName']}"
											value="#{crspd['label.crspd.altId']}" />
										<f:attribute name="#{crspd['id.crpsd.sortOrder']}"
											value="#{crspd['value.crpsd.descending']}" />
									</t:commandLink>
								</t:div>
							</h:panelGroup>
						</h:panelGrid>
					</f:facet>

					<h:outputText id="valaltId"						value="#{altIdDetails.alternateID}" />

				</h:column>
				
				<h:column id="altlobCol">
					<f:facet name="header">
						<h:panelGrid id="PRGCMGTPGD102" columns="2">
							<h:outputLabel for="lobLabelVal" id="lobLabel" value="#{crspd['label.crspd.lob']}" />
							<h:panelGroup id="lobLabelVal">
								<t:div style="width x;align=left">
									<t:commandLink id="lobAscCmdLink"										rendered="#{CorrespondenceDataBean.imageRender != 'lobAscCmdLink'}"										actionListener="#{CorrespondenceControllerBean.sortAssociatedIdentifiers}"										style="text-decoration: none;">
										<h:graphicImage  id="sortascid3" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}"
											styleClass="sort_asc" width="8" url="/images/x.gif"/>
										<f:attribute name="#{crspd['id.crpsd.columnName']}"
											value="#{crspd['label.crspd.lineOfBusiness']}" />
										<f:attribute name="#{crspd['id.crpsd.sortOrder']}"
											value="#{crspd['value.crpsd.ascending']}" />
									</t:commandLink>
								</t:div>
								<t:div style="width x;align=left">
									<t:commandLink id="lobDscCmdLink"										rendered="#{CorrespondenceDataBean.imageRender != 'lobDscCmdLink'}"										actionListener="#{CorrespondenceControllerBean.sortAssociatedIdentifiers}"										style="text-decoration: none;">
										<h:graphicImage  id="sortdescid3" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}"
											styleClass="sort_desc" width="8" url="/images/x.gif" />
										<f:attribute name="#{crspd['id.crpsd.columnName']}"
											value="#{crspd['label.crspd.lineOfBusiness']}" />
										<f:attribute name="#{crspd['id.crpsd.sortOrder']}"
											value="#{crspd['value.crpsd.descending']}" />
									</t:commandLink>
								</t:div>
							</h:panelGroup>
						</h:panelGrid>
					</f:facet>

					<h:outputText id="valAltlob"						value="#{altIdDetails.lineOfBusiness}" />

				</h:column>
				
			</h:dataTable>
		</m:div>
	</m:section>
</f:subview>

</m:section>


