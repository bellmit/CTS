<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@taglib uri="http://www.acs-inc.com/custom" prefix="m"%>

<f:loadBundle
	basename="com.acs.enterprise.common.program.contactmanagement.view.nl.PGM_IN_ContactCorrespondenceLog"
	var="crspd" />

<f:subview id="logCrspdAssociatedSubview">
	
	
	<m:section styleClass="otherbg" id="logCrAssocSviewID1">
		<m:legend id="logCrAssocSviewID2">
			<t:commandLink				action="#{CorrespondenceControllerBean.getAssociateOptionsPlus}"				id="Associateplusid" styleClass="plus"				rendered="#{CorrespondenceDataBean.associatePlusMinusFlag}"  onmousedown="javascript:focusThis(this.id);flagWarn=false;">
				<h:outputText id="Associate_Crspd_SearchPlus" 					value="#{crspd['label.crspd.asscrspd']}">
				</h:outputText>

			</t:commandLink>

			<t:commandLink				action="#{CorrespondenceControllerBean.getAssociateOptionsMinus}"				id="Associateminusid" styleClass="minus"				rendered="#{not CorrespondenceDataBean.associatePlusMinusFlag}" onmousedown="javascript:focusThis(this.id);flagWarn=false;">
				<h:outputText id="Associate_Crspd_SearchMinus"					value="#{crspd['label.crspd.asscrspd']}" >
				</h:outputText>
			</t:commandLink>

			

		</m:legend>
		

			<m:div id="logCrAssocSviewID3" rendered="#{CorrespondenceDataBean.searchForCR}">
				<m:div rendered="#{CorrespondenceDataBean.renderSearchCRPage}">
					<h:inputHidden id="logCrAssocSviewID4" value="#{cs_searchCorrespondenceDataBean.validValues}"></h:inputHidden>
					<f:subview id="searchforAssCRSPDsubview">
						<jsp:include
							page="/jsp/logcorrespondence/inc_searchCorrespondence.jsp" />
					</f:subview>
				</m:div>


			<m:div id="logCrAssocSviewID5" styleClass="buttonRow">
				
				
				<h:commandButton styleClass="formBtn" id="logCrAssocSviewID6a" rendered="#{!CorrespondenceDataBean.updateMode}"					disabled="#{CorrespondenceDataBean.crClosed}"					action="#{CorrespondenceControllerBean.addOrRemoveExistingCRsFromAssociatedCRs}" 					value="Associate/Disassociate" onmousedown="javascript:focusThis(this.id);flagWarn=false;">
				</h:commandButton>
				<h:commandButton styleClass="formBtn" id="logCrAssocSviewID6"					disabled="#{CorrespondenceDataBean.crClosed ||!CorrespondenceDataBean.controlRequired}"					action="#{CorrespondenceControllerBean.searchForCR}" 					value="#{crspd['label.crspd.searchcr']}" onmousedown="javascript:focusThis(this.id);flagWarn=false;">
				</h:commandButton>
				
				<%--Below  code is modified to display valid pop up message when user will click cancel button in Associated Correspondence section For Defect ESPRD00802327--%>
				
				<%--h:commandButton styleClass="formBtn" id="logCrAssocSviewID7"					action="#{CorrespondenceControllerBean.cancelSearchForCR}"					value="#{crspd['label.crspd.cancel']}" onmousedown="javascript:focusThis(this.id);flagWarn=true;"--%>
				
				<h:commandButton styleClass="formBtn" id="logCrAssocSviewID7"					action="#{CorrespondenceControllerBean.cancelSearchForCR}"					value="#{crspd['label.crspd.cancel']}" onclick="javascript:focusThis(this.id);flagWarn=false;return cancelPopUp('true');">
				
				</h:commandButton>
			</m:div>
			<m:br />
			<m:div rendered="#{!CorrespondenceDataBean.updateMode}">
				<f:subview id="existingCRSPDsubview">
					<jsp:include
						page="/jsp/logcorrespondence/inc_existingCorrespondenceRes.jsp" />
				</f:subview>
			</m:div>

			<m:div id="assocCRSPDID">
				<f:subview id="assocCRSPD">
					<jsp:include
						page="/jsp/logcorrespondence/chk.jsp" />
				</f:subview>
			</m:div><%--
			
			<m:div>
				<h:outputText id="PRGCMGTOT635" styleClass="strong"					value="#{crspd['label.crspd.crasswiththis']}"></h:outputText>

				<h:dataTable cellspacing="0" styleClass="dataTable" width="99%"					rowClasses="row_alt,row"					value="#{CorrespondenceDataBean.correspondenceRecordVO.listOfAssociatedCRs}"					rows="5" id="SearchCorrespondenceTable" var="crList"					columnClasses="columnClass" headerClass="headerClass"					footerClass="footerClass"					onmouseover="return tableMouseOver(this, event);"					onmouseout="return tableMouseOut(this, event);">
					<h:column id="PRGCMGTC29">
						<f:facet name="header">
							<h:outputText id="PRGCMGTOT636" value="#{crspd['label.crspd.crn']}" />
						</f:facet>
						<h:commandLink  id="crnr2"							action="#{CorrespondenceControllerBean.showExistingRecords}">
							<h:outputText id="PRGCMGTOT637" value="#{crList.correspondenceRecordNumber}" ></h:outputText>
						</h:commandLink>
					</h:column>
					<h:column id="PRGCMGTC30">
						<f:facet name="header">
							<h:outputText id="PRGCMGTOT638" value="#{crspd['label.crspd.opendate']}" />
						</f:facet>
						<h:outputText id="PRGCMGTOT639" value="#{crList.openDate}" />
					</h:column>
					<h:column id="PRGCMGTC31">
						<f:facet name="header">
							<h:outputText id="PRGCMGTOT640" value="#{crspd['label.crspd.status']}" />
						</f:facet>
						<h:outputText id="PRGCMGTOT641" value="#{crList.status}" />
					</h:column>
					<h:column id="PRGCMGTC32">
						<f:facet name="header">
							<h:outputText id="PRGCMGTOT642" value="#{crspd['label.crspd.category']}" />
						</f:facet>
						<h:outputText id="PRGCMGTOT643" value="#{crList.category}" />
					</h:column>
					<h:column id="PRGCMGTC33">
						<f:facet name="header">
							<h:outputText id="PRGCMGTOT644" value="#{crspd['label.crspd.subject']}" />
						</f:facet>
						<h:outputText id="PRGCMGTOT645" value="#{crList.subject}" />
					</h:column>
					<h:column id="PRGCMGTC34">
						<f:facet name="header">
							<h:outputText id="PRGCMGTOT646" value="#{crspd['label.crspd.contact']}" />
						</f:facet>
						<h:outputText id="PRGCMGTOT647" value="#{crList.contact}" />
					</h:column>
					<f:facet name="footer">
						<m:div id="nodata" align="center"
							rendered="#{empty CorrespondenceDataBean.correspondenceRecordVO.listOfAssociatedCRs}">
							<h:outputText id="PRGCMGTOT648" value="#{ref['label.ref.noData']}" />
						</m:div>
					</f:facet>

				</h:dataTable>

			</m:div>
			<m:div styleClass="floatl">
			</m:div>
			<m:div styleClass="searchResults">

				<t:dataScroller id="CMGTTOMDS27" for="SearchCorrespondenceTable" paginator="true"					paginatorActiveColumnStyle='font-weight:bold;'					paginatorMaxPages="3" immediate="false" pageCountVar="pageCount"					pageIndexVar="pageIndex" firstRowIndexVar="firstRowIndex"					lastRowIndexVar="lastRowIndex" rowsCountVar="rowsCount"					style="float:right;position:relative;bottom:-6px;text-decoration:underline;">


					<f:facet name="previous">
						<h:outputText id="PRGCMGTOT649" style="text-decoration:underline;"							value="#{crspd['label.crspd.prev']}" rendered="#{pageIndex > 1}">
						</h:outputText>
					</f:facet>
					<f:facet name="next">
						<h:outputText id="PRGCMGTOT650" style="text-decoration:underline;"							value="#{crspd['label.crspd.nxt']}"							rendered="#{pageIndex < pageCount}">
						</h:outputText>
					</f:facet>
					<h:outputText id="PRGCMGTOT651" rendered="#{rowsCount > 0}"
						value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
						style="float:left;position:relative;bottom:-6px;font-weight:bold;" />
				</t:dataScroller>

			</m:div>
			--%><m:br />
		</m:div>
	</m:section>
</f:subview>
