<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://www.acs-inc.com/caf" prefix="caf" %>
<%@ taglib uri="http://www.acs-inc.com/auditLog" prefix="audit"%>

<%-- Holds the name of the property file to be reffered --%>
<f:loadBundle
	basename="com.acs.enterprise.common.program.commonentities.nl.PGM_CE_CommonAddress"
	var="cmnAddMsg" />

<m:div>
	<f:subview id="maintainEntComAddressSubView">
		<m:section id="PROVIDERMMS20120731164811149" styleClass="otherbg">
			<m:legend>
					<h:outputText id="CMGTOT1" value="#{cmnAddMsg['label.address.addressLabel']}" />
			</m:legend>
			<m:div id="maintainE_common_address">
				<m:div  id="showhide_address">
					<caf:addressHistoryTable id="addrTable" value="#{commonEntityDataBean.commonEntityVO.addressVOList}"
							disabled="#{!CMEntityMaintainControllerBean.controlRequired}" binding="#{CMEntityMaintainControllerBean.addressTable}"
							addrClass="com.acs.enterprise.common.program.commonentities.view.vo.AddressVO"
							externalDisplay="false"
							inquiryMode="#{!CMEntityMaintainControllerBean.controlRequired}">
						<f:facet name="fieldSet">
							<caf:addressFieldSet id="fieldSet" beginDateDefault="true" sigTypeDisplay="required" statusDisplay="required"  uspsVerifiedDisplay="invisible" />
						</f:facet>
						<f:facet name="auditTable">
							<audit:auditTableSet id="addressAuditId" rendered="#{CMEntityMaintainDataBean.auditLogFlag}" value="#{commonEntityDataBean.commonEntityVO.addressVO.auditKeyList}" numOfRecPerPage="10" />
						</f:facet>
					</caf:addressHistoryTable>
				</m:div>
			</m:div>
		</m:section>
	</f:subview>
</m:div>

