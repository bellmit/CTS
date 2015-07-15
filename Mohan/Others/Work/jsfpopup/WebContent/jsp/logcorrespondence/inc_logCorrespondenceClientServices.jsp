<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%-- 
	<m:legend>
		<h:outputText id="PRGCMGTOT764" value="#{clientServ['label.clientservices.clientservices']}" />
	</m:legend>
	--%>

<m:div>
	<m:section id="PROVIDERMMS20120731164811330" styleClass="otherbg">
		<m:div>
			<m:table id="PROVIDERMMT20120731164811331" cellspacing="0" width="94%">
				<m:tr>
					<m:td>
						<m:div styleClass="padb">
							<h:outputLabel id="vip" for="yesOrNo" value="VIP" for="yesOrNo"/>
							<h:selectOneRadio id="yesOrNo"								disabled="#{CorrespondenceDataBean.crClosed  || !CorrespondenceDataBean.controlRequired}"								enabledClass="black"								value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceDetailsVO.vipPresent}">
								<f:selectItem itemLabel="#{ref['label.ref.yes']}"
									itemValue="Yes" />
								<f:selectItem itemLabel="#{ref['label.ref.no']}" itemValue="No" />
							</h:selectOneRadio>
						</m:div>
					</m:td>
					<m:td>
						<h:outputLabel id="PRGCMGTOLL366" for="apptMadeByList">
							<h:outputText id="dentalAppt" value="Dental Appointment Made By" />
						</h:outputLabel>
						<m:br />
						<h:selectOneMenu id="apptMadeByList"							disabled="#{CorrespondenceDataBean.crClosed  || !CorrespondenceDataBean.controlRequired}"							value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceDetailsVO.dentalApptMadeBy}">
							<f:selectItem itemValue=" " itemLabel="" />
							<f:selectItems
								value="#{CorrespondenceDataBean.dentalApptMadeByList}" />
						</h:selectOneMenu>
					</m:td>
					<m:td>
					<%--Note:fixing for DEFECT ESPRD00576206 --%>
						<h:panelGroup id="referredToGroup">
							<m:table id="PROVIDERMMT20120731164811332">
								<m:tr>
									<m:td valign="top">
										<h:outputLabel id="CMGTOLL5" for="referredTo">
											<%-- for fixing defect:ESPRD00576206   --%>
											<h:outputText id="referredTo" value="Referred To:" />
										</h:outputLabel>
										<m:br />
									</m:td>
								</m:tr>
								<m:tr>
									<m:td valign="top">
										<h:outputLabel id="CMGTOLL6" for="availableCR">
											<%-- for fixing defect:ESPRD00576206   --%>
											<h:outputText id="availableTo" value="Available:" />
										</h:outputLabel>
										<m:br />
									</m:td>
									<m:td></m:td>
									<m:td valign="top">
										<h:outputLabel id="CMGTOLL7" for="selectedCR">
											<%-- for fixing defect:ESPRD00576206   --%>
											<h:outputText id="selectedTo" value="Selected:" />
										</h:outputLabel>
										<m:br />
									</m:td>								
								</m:tr>
								<m:tr>
									<m:td>
										<t:selectManyListbox size="5" id="availableCR"											disabled="#{CorrespondenceDataBean.crClosed  || !CorrespondenceDataBean.controlRequired}"											value="#{CorrespondenceDataBean.selectedList}">
											<f:selectItems
												value="#{CorrespondenceDataBean.referredToList}" />
										</t:selectManyListbox>
									</m:td>
									<m:td>
										<m:br />
										<m:table id="PROVIDERMMT20120731164811333">
											<m:tr>
												<m:td align="center">
													<h:commandButton id="selectedList"														disabled="#{CorrespondenceDataBean.crClosed ||  !CorrespondenceDataBean.controlRequired}"														styleClass="formBtn" value="#{ref['label.ref.gt']}"														actionListener="#{CorrespondenceControllerBean.moveSelectedList}">
														<%--action="#{CorrespondenceControllerBean.moveSelectedList}" --%>
														<hx:behavior event="onclick" behaviorAction="get;stop"
															targetAction="referredToGroup;"></hx:behavior>
													</h:commandButton>
												</m:td>
											</m:tr>
											<m:tr>
												<m:td align="center">
													<h:commandButton id="removeList"														disabled="#{CorrespondenceDataBean.crClosed ||  !CorrespondenceDataBean.controlRequired}"														styleClass="formBtn" value="#{ref['label.ref.lt']}"														actionListener="#{CorrespondenceControllerBean.removeSelectedList}">

														<hx:behavior event="onclick" behaviorAction="get;stop"
															targetAction="referredToGroup;"></hx:behavior>
													</h:commandButton>
													<%--action="#{CorrespondenceControllerBean.removeSelectedList}"> --%>
												</m:td>
											</m:tr>
										</m:table>
									</m:td>
									<m:td align="left">
										<t:selectManyListbox size="5" id="selectedCR"									    	disabled="#{CorrespondenceDataBean.crClosed ||  !CorrespondenceDataBean.controlRequired}"											value="#{CorrespondenceDataBean.removedList}">
											<f:selectItems
												value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceDetailsVO.selectedReferredToList}" />
										</t:selectManyListbox>
									</m:td>
								</m:tr>
							</m:table>
						</h:panelGroup>
					<%--Note:Commented for DEFECT ESPRD00576206 --%>
					<%-- <h:panelGroup id="referredToGroup">
							<m:table id="PROVIDERMMT20120731164811334">
								<m:tr>
									<m:td valign="top">
										<h:outputLabel id="PRGCMGTOLL367" for="referredTo">
											<h:outputText id="referredTo" value="Referred To:" />
										</h:outputLabel>
										<m:br />
										<t:selectManyListbox size="5" id="availableCR"											disabled="#{CorrespondenceDataBean.crClosed  || !CorrespondenceDataBean.controlRequired}"											value="#{CorrespondenceDataBean.selectedList}">
											<f:selectItems
												value="#{CorrespondenceDataBean.referredToList}" />
										</t:selectManyListbox>
									</m:td>
									<m:td>
										<m:br />
										<m:table id="PROVIDERMMT20120731164811335">
											<m:tr>
												<m:td align="center">
													<h:commandButton id="selectedList"														disabled="#{CorrespondenceDataBean.crClosed ||  !CorrespondenceDataBean.controlRequired}"														styleClass="formBtn" value="#{ref['label.ref.gt']}"														actionListener="#{CorrespondenceControllerBean.moveSelectedList}">
														<%--action="#{CorrespondenceControllerBean.moveSelectedList}" 
														<hx:behavior event="onclick" behaviorAction="get;stop"
															targetAction="referredToGroup;"></hx:behavior>
													</h:commandButton>
												</m:td>
											</m:tr>
											<m:tr>
												<m:td align="center">
													<h:commandButton id="removeList"														disabled="#{CorrespondenceDataBean.crClosed ||  !CorrespondenceDataBean.controlRequired}"														styleClass="formBtn" value="#{ref['label.ref.lt']}"														actionListener="#{CorrespondenceControllerBean.removeSelectedList}">

														<hx:behavior event="onclick" behaviorAction="get;stop"
															targetAction="referredToGroup;"></hx:behavior>
													</h:commandButton>
													<%--action="#{CorrespondenceControllerBean.removeSelectedList}"> 
												</m:td>
											</m:tr>
										</m:table>
									</m:td>
								
									<m:td>
									<m:br />
										<t:selectManyListbox size="5" id="selectedCR"									    	disabled="#{CorrespondenceDataBean.crClosed ||  !CorrespondenceDataBean.controlRequired}"											value="#{CorrespondenceDataBean.removedList}">
											<f:selectItems
												value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceDetailsVO.selectedReferredToList}" />
										</t:selectManyListbox>
									</m:td>
								</m:tr>
							</m:table>
						</h:panelGroup> --%>
						<hx:ajaxRefreshSubmit id="ajaxReferredToRequest"
							target="referredToGroup">
						</hx:ajaxRefreshSubmit>
					</m:td>
					<m:td>
						<m:div rendered="#{CorrespondenceDataBean.renderCrspdMemberForVO}">
							<h:outputLabel id="PRGCMGTOLL368" for="langSpoken">
								<h:outputText id="langSpoken" value="Language Spoken" />
							</h:outputLabel>
							<m:br />
							<h:outputText id="languageSpoken"								value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForMemberVO.primaryLanguage}"></h:outputText>


						</m:div>
					</m:td>
					<m:td>
						<m:div styleClass="padb">
							<h:outputLabel id="lep" value="Limited English Proficiency" for="ltdEnglishProf"/>
							<m:br />
							<h:selectOneRadio id="ltdEnglishProf"								disabled="#{CorrespondenceDataBean.crClosed  || !CorrespondenceDataBean.controlRequired}"								enabledClass="black"								value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceDetailsVO.ltdEngProficiency}">
								<f:selectItem itemLabel="#{ref['label.ref.yes']}"
									itemValue="Yes" />
								<f:selectItem itemLabel="#{ref['label.ref.no']}" itemValue="No" />
							</h:selectOneRadio>
						</m:div>
					</m:td>
				</m:tr>
			</m:table>
		</m:div>
	</m:section>

</m:div>








