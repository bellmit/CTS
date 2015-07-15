<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>

<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>

<%--  For Display address History Summary --%>
<m:div id="AddrHistDispDet" styleClass="moreInfo"
	rendered="#{commonEntityDataBean.addressHistorydisp}">							
			<m:div styleClass="floatr">
				<m:table id="PROVIDERMMT20120731164811179" cellspacing="0" styleClass="dataTable" width="40%">

					<m:tr styleClass="tableHead">
					</m:tr>
					<m:tr>
						<m:td align="right" styleClass="portletActionBar" colspan="3">
							<t:commandLink id="PRGCMGTCL74" styleClass="weak"								action="#{addressControllerBean.cancelAddressHistorySummary}">
								<h:outputText id="PRGCMGTOT379" value="#{cmnAddMsg['label.addressHistory.cancel']}"></h:outputText>
							</t:commandLink>
						</m:td>
					</m:tr>
					<m:tr>
						<m:td colspan="3">

							<m:div>
								<m:tr>
									<m:td>
										<h:outputText id="PRGCMGTOT380"											value="#{cmnAddMsg['label.address.invalidAddress']}">
										</h:outputText>
									</m:td>
								</m:tr>
								<m:tr>
									<m:td>
										<h:outputText id="PRGCMGTOT381"											value="#{cmnAddMsg['label.address.addressStatus']}:">
										</h:outputText>
									</m:td>
									<m:td>
										<h:outputText id="PRGCMGTOT382"											value="#{cmnAddMsg['label.address.addressType']}:"></h:outputText>
									</m:td>
									<m:tr>
										<m:td>
											<h:outputText id="PRGCMGTOT383"												value="#{commonEntityDataBean.commonEntityVO.addressVO.addressStatusDesc}">
											</h:outputText>
										</m:td>
										<m:td>
											<h:outputText id="PRGCMGTOT384"												value="#{commonEntityDataBean.commonEntityVO.addressVO.addressTypeDesc}">
											</h:outputText>
										</m:td>
									</m:tr>
									<m:tr>
										<m:td>
											<h:outputText id="PRGCMGTOT385"												value="#{cmnAddMsg['label.address.lineOfBusiness']}">
											</h:outputText>
										</m:td>
									</m:tr>
									<m:tr>
										<m:td>
											<h:outputText id="PRGCMGTOT386"												value="#{commonEntityDataBean.commonEntityVO.addressVO.businessTypeDesc}">
											</h:outputText>
										</m:td>

									</m:tr>
									<m:tr>
										<m:td>
											<h:outputText id="PRGCMGTOT387"												value="#{cmnAddMsg['label.address.addressLabel']}:"></h:outputText>
										</m:td>
									</m:tr>
									<m:tr>
										<m:td>
											<h:outputText id="PRGCMGTOT388"												value="#{commonEntityDataBean.commonEntityVO.addressVO.addressline1},"></h:outputText>
										</m:td>
									</m:tr>
									<m:tr>
										<m:td>
											<h:outputText id="PRGCMGTOT389"												value="#{commonEntityDataBean.commonEntityVO.addressVO.addressline2}."></h:outputText>
										</m:td>
									</m:tr>

									<m:tr>
										<m:td>
											<h:outputText id="PRGCMGTOT390" value="#{cmnAddMsg['label.address.city']}:"></h:outputText>
											
											<h:outputText id="PRGCMGTOT391" escape="false"								                 value="#{cmnContactMsg['label.address.eightSpace']}" />
								                 <h:outputText id="PRGCMGTOT392" escape="false"								                 value="#{cmnContactMsg['label.address.eightSpace']}" />
								                 <h:outputText id="PRGCMGTOT393" escape="false"								                 value="#{cmnContactMsg['label.address.eightSpace']}" />
								                 <h:outputText id="PRGCMGTOT394" escape="false"								                 value="#{cmnContactMsg['label.address.singleSpace']}" />
								                 
											<h:outputText id="PRGCMGTOT395" value="#{cmnAddMsg['label.address.state']}:"></h:outputText>
										</m:td>
									</m:tr>
									<m:tr>
										<m:td>
											<h:outputText id="PRGCMGTOT396"												value="#{commonEntityDataBean.commonEntityVO.addressVO.city}">
											</h:outputText>
										</m:td>
									<h:outputText id="PRGCMGTOT397" escape="false"								                 value="#{cmnContactMsg['label.address.eightSpace']}" />
								                 <h:outputText id="PRGCMGTOT398" escape="false"								                 value="#{cmnContactMsg['label.address.eightSpace']}" />
								                 <h:outputText id="PRGCMGTOT399" escape="false"								                 value="#{cmnContactMsg['label.address.eightSpace']}" />
								                 <h:outputText id="PRGCMGTOT400" escape="false"								                 value="#{cmnContactMsg['label.address.singleSpace']}" />
										<m:td>
											<h:outputText id="PRGCMGTOT401"												value="#{commonEntityDataBean.commonEntityVO.addressVO.stateDesc}"></h:outputText>
										</m:td>
									</m:tr>

									<m:tr>
										<m:td>
											<h:outputText id="PRGCMGTOT402" value="#{cmnAddMsg['label.address.zipCode']}:"></h:outputText>
										
										</m:td>
									<h:outputText id="PRGCMGTOT403" escape="false"								                 value="#{cmnContactMsg['label.address.fourSpace']}" />
								                 <h:outputText id="PRGCMGTOT404" escape="false"								                 value="#{cmnContactMsg['label.address.singleSpace']}" />

										<m:td>
											<h:outputText id="PRGCMGTOT405" value="#{cmnAddMsg['label.addresss.county1']}:"></h:outputText>
										</m:td>
										<h:outputText id="PRGCMGTOT406" escape="false"								                 value="#{cmnContactMsg['label.address.eightSpace']}" />
								                 <h:outputText id="PRGCMGTOT407" escape="false"								                 value="#{cmnContactMsg['label.address.fourSpace']}" />
								                 <h:outputText id="PRGCMGTOT408" escape="false"								                 value="#{cmnContactMsg['label.address.doubleSpace']}" />
								                 
										<m:td>
											<h:outputText id="PRGCMGTOT409" value="#{cmnAddMsg['label.addresss.county1']}:"></h:outputText>
										</m:td>
									</m:tr>

									<m:tr>
										<m:td>
											<h:outputText id="PRGCMGTOT410"												value="#{commonEntityDataBean.commonEntityVO.addressVO.zipCode5}-">
											</h:outputText>
											<h:outputText id="PRGCMGTOT411"												value="#{commonEntityDataBean.commonEntityVO.addressVO.zipCode4}">
											</h:outputText>
										</m:td>
									<h:outputText id="PRGCMGTOT412" escape="false"								                 value="#{cmnContactMsg['label.address.fourSpace']}" />
								                 <h:outputText id="PRGCMGTOT413" escape="false"								                 value="#{cmnContactMsg['label.address.doubleSpace']}" />
										<m:td>
											<h:outputText id="PRGCMGTOT414"												value="#{commonEntityDataBean.commonEntityVO.addressVO.countyDesc}"></h:outputText>
										</m:td>
								<h:outputText id="PRGCMGTOT415" escape="false"								                 value="#{cmnContactMsg['label.address.fourSpace']}" />
								                 <h:outputText id="PRGCMGTOT416" escape="false"								                 value="#{cmnContactMsg['label.address.doubleSpace']}" />
										<m:td>
											<h:outputText id="PRGCMGTOT417"												value="#{commonEntityDataBean.commonEntityVO.addressVO.countryDesc}"></h:outputText>
										</m:td>

									</m:tr>
									<m:tr>
										<m:td>
											<h:outputText id="PRGCMGTOT418"												value="#{cmnAddMsg['label.address.ReturnMail1']}"></h:outputText>
										<h:outputText id="PRGCMGTOT419" escape="false"								                 value="#{cmnContactMsg['label.address.fourSpace']}" />
								                 <h:outputText id="PRGCMGTOT420" escape="false"								                 value="#{cmnContactMsg['label.address.doubleSpace']}" />
										</m:td>
										<m:td>
											<h:outputText id="PRGCMGTOT421"												value="#{cmnAddMsg['label.address.ReturnMail2']}"></h:outputText>
										</m:td>

										<m:td>
											<h:outputText id="PRGCMGTOT422"												value="#{cmnAddMsg['label.address.ReturnMail3']}"></h:outputText>
										</m:td>
									</m:tr>

								</m:tr>
							</m:div>
						</m:td>
					</m:tr>



				</m:table>

			</m:div>
		</m:div>


