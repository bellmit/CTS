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


<m:section id="PROVIDERMMS20120731164811370" styleClass="otherbg">
	<m:legend>
		<h:outputText id="PRGCMGTOT831" value="#{crspd['label.crspd.crspdfor']}"></h:outputText>
	</m:legend>

	<m:br></m:br>

	<h:outputLabel id="PRGCMGTOLL432" for="provCRN">
				<h:outputText value="#{crspd['label.crspd.corresNum']}" id="provCRN" />
	</h:outputLabel>
	<m:br/>
	<h:outputText id="PRGCMGTOT832"		value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceRecordNumber}"></h:outputText>
	<m:div styleClass="clear">
	</m:div>
	<m:div>
		<h:graphicImage id="PROVIDERGI20120731164811371" alt="" url="/images/x.gif" width="1" height="10" styleClass="blankImage" />
	</m:div>
		
	<m:table id="PROVIDERMMT20120731164811372" styleClass="viewTable" width="100%">


		<m:tr>

			<m:td>

				<m:div styleClass="padx">
					<h:outputLabel id="PRGCMGTOLL433" for="entityTypeCodeProv">
						<h:outputText value="#{crspd['label.crspd.entitytype']}" id="entityTypeCodeProv" />
					</h:outputLabel>
					<m:br />
					<h:outputText id="PRGCMGTOT833"						value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForProviderVO.entityTypeDesc}">
					</h:outputText>
				</m:div>
			</m:td>


			<m:td>
				<m:div styleClass="padx">
					<h:outputLabel id="PRGCMGTOLL434" for="providerID">
						<h:outputText value="#{crspd['label.crspd.providerid']}" id="providerID" />
					</h:outputLabel>
					<m:br />
					<%-- for fixing defect:ESPRD00576206   --%>
					<h:outputText id="PRGCMGTOT834" style="color:blue"						value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForProviderVO.currAltID}">
					</h:outputText>
				</m:div>
			</m:td>
			<m:td>
				<m:div styleClass="padx">
					<h:outputLabel id="PRGCMGTOLL435" for="provIdType">
						<h:outputText value="#{crspd['label.crspd.idType']}" id="provIdType" />
					</h:outputLabel>
					<m:br />
					<h:outputText id="PRGCMGTOT835"						value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForProviderVO.idTypeStr}">
					</h:outputText>
				</m:div>
			</m:td>
			
			<m:td>
				<m:div styleClass="padx">
					<h:outputLabel id="PRGCMGTOLL436" for="payerID">
						<h:outputText value="#{crspd['label.crspd.payerid']}" id="payerID"/>
					</h:outputLabel>
					<m:br />
					<h:outputText id="PRGCMGTOT836"						value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForProviderVO.payerID}">
					</h:outputText>
				</m:div>

			</m:td>
			<m:td>
				<m:div styleClass="padx">
					<h:outputLabel id="PRGCMGTOLL437" for="statusProv">
						<h:outputText value="#{crspd['label.crspd.status']}" id="statusProv" />
					</h:outputLabel>
					<m:br />
					<h:outputText id="PRGCMGTOT837"						value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForProviderVO.statsuSstr}">
					</h:outputText>
				</m:div>

			</m:td>
	
			<m:br />
		</m:tr>

		<m:tr>
			<m:td>
				<m:div styleClass="padx">
					<h:outputLabel id="PRGCMGTOLL438" for="providerName">
						<h:outputText value="#{crspd['label.crspd.name']}" id="providerName" />
					</h:outputLabel>
					<m:br />
					<h:outputText id="PRGCMGTOT838"						value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForProviderVO.name}">
					</h:outputText>
				</m:div>

			</m:td>
			<m:td>
				<m:div styleClass="padx">
					<h:outputLabel id="PRGCMGTOLL439" for="specialty">
						<h:outputText value="#{crspd['label.crspd.specialty']}" id="specialty"/>
					</h:outputLabel>
					<m:br />
					<h:outputText id="PRGCMGTOT839"						value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForProviderVO.specialtyStr}">
					</h:outputText>
				</m:div>

			</m:td>

			<m:td>
				<m:div styleClass="padx">
					<h:outputLabel id="PRGCMGTOLL440" for="providerType">
						<h:outputText value="#{crspd['label.crspd.providertype']}" id="providerType"/>
					</h:outputLabel>
					<m:br />

					<h:outputText id="PRGCMGTOT840"						value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForProviderVO.providerTypeStr}">
					</h:outputText>
				</m:div>

			</m:td>


			<m:td>
				<m:div styleClass="padx">
					<h:outputLabel id="PRGCMGTOLL441" for="provContact" >
					<h:outputText value="#{crspd['label.crspd.contact']}" id="providercontact"/>
					</h:outputLabel>
					<m:br></m:br>

					<h:selectOneMenu id="provContact" disabled="#{CorrespondenceDataBean.crClosed || CorrespondenceDataBean.updateMode}"						value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForProviderVO.contact}">
						<f:selectItem itemValue=" " itemLabel="" />
						<f:selectItems value="#{CorrespondenceDataBean.listOfContacts}" />
					</h:selectOneMenu>
				</m:div>

			</m:td>

		</m:tr>
	</m:table>



	
	<f:subview id="logCrspdForProviderSubview">
	<m:section styleClass="otherbg" id="provAltId">
		<m:legend>
			<h:outputLink					onclick="setHiddenValue('logCorrespondence:logCrspdForProvSubview:logCrspdForProviderSubview:providerHidden','minus');toggle('corrForProv',2);							plusMinusToggle('corrForProv',this,'logCorrespondence:logCrspdForProvSubview:logCrspdForProviderSubview:providerHidden');							 return false;"					id="plusMinusProvider" styleClass="plus">
				<h:outputText id="PRGCMGTOT841" value="#{crspd['label.crspd.alternativeIdentifiers']}" />
				<h:inputHidden id="providerHidden"						value="#{CorrespondenceDataBean.crspdProvHidden}"></h:inputHidden>
			</h:outputLink>
		</m:legend>
	<m:div sid="corrForProv">
		<h:dataTable id="provAltIdTb" width="100%" 				styleClass="dataTable" rows="10" var="altIdDetails"				columnClasses="columnClass" headerClass="headerClass"				footerClass="footerClass" rowClasses="row_alt,row"				onmouseover="return tableMouseOver(this, event);"				onmouseout="return tableMouseOut(this, event);"				value="#{CorrespondenceDataBean.listOfAlternateIds}">

				<h:column id="typeCol">
					<f:facet name="header">
						<h:panelGrid id="PRGCMGTPGD103" columns="2">
							<h:outputLabel for="typeLabelGRVal" id="typeLabel" value="#{crspd['label.crspd.type']}" />
							<h:panelGroup id="typeLabelGRVal">
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
						<h:panelGrid id="PRGCMGTPGD104" columns="2">
							<h:outputLabel for="altIdLabelPanVal" id="altIdLabel" value="#{crspd['label.crspd.altId']}" />
							<h:panelGroup id="altIdLabelPanVal">
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
						<h:panelGrid id="PRGCMGTPGD105" columns="2">
							<h:outputLabel for="lobLabelPanGrpVal" id="lobLabel" value="#{crspd['label.crspd.lob']}" />
							<h:panelGroup id="lobLabelPanGrpVal">
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




