<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>

<portlet:defineObjects />

<f:loadBundle
	basename="com.acs.enterprise.common.program.commonentities.nl.PGM_CE_CommonContact"
	var="cmnContactMsg" />
<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.ProgramMessages"
	var="ref" />

<f:subview id="logCrspdContactSubview">
	<m:section id="PROVIDERMMS20120731164811336" styleClass="otherbg">
		<m:legend>

			<h:outputLink				onclick="setHiddenValue('logCorrespondence:logCrspdConSubview:logCrspdContactSubview:contactHidden','minus');toggle('log_div_contact',2);							plusMinusToggle('log_div_contact',this,'logCorrespondence:logCrspdConSubview:logCrspdContactSubview:contactHidden');							 return false;"				id="plusMinusContact" styleClass="plus">

				<h:outputText id="log_Crspd_Contact"					value="#{crspd['label.crspd.contacts']}" />
				<h:inputHidden id="contactHidden"					value="#{CorrespondenceDataBean.contactHidden}"></h:inputHidden>
			</h:outputLink>

		</m:legend>

		<m:div sid="log_div_contact" >

			<m:table id="PROVIDERMMT20120731164811337" styleClass="tableBar" width="100%">
				<m:tr>
					<m:td styleClass="tableTitle">
					</m:td>
					<m:td styleClass="tableAction">
						<m:div styleClass="rightCell">
							<h:commandButton id="PRGCMGTCB24" styleClass="formBtn" disabled="true"								action="#{commonContactControllerBean.addContact}"								value="#{cmnContactMsg['label.commonContact.addContact']}"></h:commandButton>
						</m:div>
					</m:td>
				</m:tr>
			</m:table>
			<t:dataTable rows="5" cellspacing="0" styleClass="dataTable" width="100%" value="#{commonEntityDataBean.commonEntityVO.contactVOList}"				columnClasses="columnClass" headerClass="headerClass"				footerClass="footerClass" rowClasses="row_alt,row"				rowOnMouseOver="this.origClassName = this.className;this.className='row_mouse row_link';"				rowOnMouseOut="if (this.origClassName) this.className = this.origClassName;"				var="contact" id="contactHist" rowIndexVar="rowIndex">
				<t:column id="conatctHistClm1" styleClass="otherbg">
					<f:facet name="header">
						<h:panelGrid id="PRGCMGTPGD93" columns="2">
							<h:outputLabel id="fstname" for="contactHistpanel"								value="#{cmnContactMsg['label.commonContact.firstName']}" />

							<h:panelGroup id="contactHistpanel">
								<t:div style="width:px;align=left">
									<t:commandLink style="text-decoration: none;" id="cmd1"										actionListener="#{commonContactControllerBean.sortContact}">
										<h:graphicImage id="PROVIDERGI20120731164811338" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}" styleClass="sort_asc"
											width="8" url="/images/x.gif"/>
										<f:attribute name="columnName"
											value="#{cmnContactMsg['label.commonContact.firstName']}" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
								</t:div>
								<t:div style="width:px;align=left">
									<t:commandLink style="text-decoration: none;" id="cmd2"										actionListener="#{commonContactControllerBean.sortContact}">
										<h:graphicImage id="PROVIDERGI20120731164811339" alt="#{ref['label.ref.forDescending']}"  title="#{ref['label.ref.forDescending']}" styleClass="sort_desc"
											width="8" url="/images/x.gif"/>
										<f:attribute name="columnName"
											value="#{cmnContactMsg['label.commonContact.firstName']}" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
								</t:div>
							</h:panelGroup>
						</h:panelGrid>
					</f:facet>
					<h:outputText id="cmd3" value="#{contact.nameVO.firstName}" />
				</t:column>
				<t:column id="conatctHistClm2" styleClass="otherbg">
					<f:facet name="header">
						<h:panelGrid id="PRGCMGTPGD94" columns="2">

							<h:outputLabel id="PRGCMGTOLL369" for="panel1"								value="#{cmnContactMsg['label.commonContact.middleName']}" />
							<h:panelGroup id="panel1">
								<t:div style="width:px;align=left">
									<t:commandLink style="text-decoration: none;" id="cmd4"										actionListener="#{commonContactControllerBean.sortContact}">
										<h:graphicImage id="PROVIDERGI20120731164811340" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}" styleClass="sort_asc"
											width="8" url="/images/x.gif"/>
										<f:attribute name="columnName"
											value="#{cmnContactMsg['label.commonContact.middleName']}" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
								</t:div>
								<t:div style="width:px;align=left">
									<t:commandLink style="text-decoration: none;" id="cmd5"										actionListener="#{commonContactControllerBean.sortContact}">
										<h:graphicImage id="PROVIDERGI20120731164811341" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}" styleClass="sort_desc"
											width="8" url="/images/x.gif"/>
										<f:attribute name="columnName"
											value="#{cmnContactMsg['label.commonContact.middleName']}" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
								</t:div>
							</h:panelGroup>
						</h:panelGrid>
					</f:facet>
					<h:outputText id="PRGCMGTOT765" value="#{contact.nameVO.middleName}" />
				</t:column>
				<t:column id="conatctHistClm3" styleClass="otherbg">

					<f:facet name="header">
						<h:panelGrid id="PRGCMGTPGD95" columns="2">
							<h:outputLabel id="PRGCMGTOLL370" for="panel2"								value="#{cmnContactMsg['label.commonContact.lastName']}" />

							<h:panelGroup id="panel2">
								<t:div style="width:px;align=left">
									<t:commandLink style="text-decoration: none;" id="cnd"										actionListener="#{commonContactControllerBean.sortContact}">
										<h:graphicImage id="PROVIDERGI20120731164811342" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}" styleClass="sort_asc"
											width="8" url="/images/x.gif"/>
										<f:attribute name="columnName"
											value="#{cmnContactMsg['label.commonContact.lastName']}" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
								</t:div>
								<t:div style="width:px;align=left">
									<t:commandLink style="text-decoration: none;" id="cmd6"										actionListener="#{commonContactControllerBean.sortContact}">
										<h:graphicImage id="PROVIDERGI20120731164811343" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}" styleClass="sort_desc"
											width="8" url="/images/x.gif"/>
										<f:attribute name="columnName"
											value="#{cmnContactMsg['label.commonContact.lastName']}" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
								</t:div>
							</h:panelGroup>
						</h:panelGrid>
					</f:facet>
					<t:commandLink id="cmd7"						action="#{commonContactControllerBean.displayDetailedContactInfo}">
						<h:outputText id="valuelastName"							value="#{contact.nameVO.lastName}" />
						<f:param name="indexCode" value="#{rowIndex}"></f:param>
					</t:commandLink>
				</t:column>


				<t:column id="conatctHistClm4" styleClass="otherbg">
					<f:facet name="header">
						<h:panelGrid id="PRGCMGTPGD96" columns="2">
							<h:outputLabel id="PRGCMGTOLL371" for="entityTypeValue"								value="#{cmnContactMsg['label.commonContact.entityType']}" />
							<h:panelGroup id="entityTypeValue">

								<t:div style="width:px;align=left">
									<t:commandLink style="text-decoration: none;" id="aa"										actionListener="#{commonContactControllerBean.sortContact}">
										<h:graphicImage id="PROVIDERGI20120731164811344" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}" styleClass="sort_asc"
											width="8" url="/images/x.gif"/>
										<f:attribute name="columnName"
											value="#{cmnContactMsg['label.commonContact.entityType']}" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
								</t:div>
								<t:div style="width:px;align=left">
									<t:commandLink style="text-decoration: none;" id="cmd9"										actionListener="#{commonContactControllerBean.sortContact}">
										<h:graphicImage id="PROVIDERGI20120731164811345" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}" styleClass="sort_desc"
											width="8" url="/images/x.gif"/>
										<f:attribute name="columnName"
											value="#{cmnContactMsg['label.commonContact.entityType']}" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
								</t:div>
							</h:panelGroup>
						</h:panelGrid>
					</f:facet>
					<h:outputText id="cmd10" value="#{contact.entityTypeDesc}" />
				</t:column>
				<f:facet name="footer">
						<m:div id="nodata" align="center">
							<h:outputText id="PRGCMGTOT766" value="No Data"								rendered="#{empty commonEntityDataBean.commonEntityVO.contactVOList}"></h:outputText>
						</m:div>
				</f:facet>

			</t:dataTable>

			<m:div styleClass="searchResults" id="div5">
				<t:dataScroller id="CMGTTOMDS31" pageCountVar="pageCount" pageIndexVar="pageIndex"					paginator="true" paginatorActiveColumnStyle='font-weight:bold;'					paginatorMaxPages="3" immediate="false" for="contactHist"					firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"					rowsCountVar="rowsCount"					style="float:right;position:relative;bottom:-6px">

					<f:facet name="previous">
						<h:outputText id="PRGCMGTOT767" style="text-decoration:underline;" value=" << "							rendered="#{pageIndex > 1}"></h:outputText>
					</f:facet>
					<f:facet name="next">
						<h:outputText id="PRGCMGTOT768" style="text-decoration:underline;" value=" >> "
							rendered="#{pageIndex < pageCount}"></h:outputText>
					</f:facet>

					<h:outputText id="PRGCMGTOT769"						value="showing #{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"						style="float:left;position:relative;bottom:-6px"						rendered="#{rowsCount>0}">
					</h:outputText>

				</t:dataScroller>
				<m:div>
					<m:br></m:br>
				</m:div>
			</m:div>
			<m:div>
				<m:br></m:br>
			</m:div>

			<m:div styleClass="" id="newcontact"
				rendered="#{commonEntityDataBean.newContactRender}">

				<m:div styleClass="moreInfo">
					<m:div styleClass="moreInfoBar">
						<m:div styleClass="infoTitle">
							<h:outputText id="PRGCMGTOT770"								value="#{commonEntityDataBean.contactEdit? cmnContactMsg['label.commonContact.editContact'] : cmnContactMsg['label.commonContact.newContact'] }" />
						</m:div>
						<m:div styleClass="infoActions">
							<m:div rendered="#{CorrespondenceDataBean.controlRequired}">
								<t:commandLink id="PRGCMGTCL123" styleClass="strong" rendered="false"									action="#{commonContactControllerBean.saveContact}"									value="#{cmnContactMsg['label.commonContact.save']}"></t:commandLink>
								<h:outputText id="PRGCMGTOT771" escape="false"									value="#{ref['label.ref.linkSeperator']}" />
								<t:commandLink id="PRGCMGTCL124"									action="#{commonContactControllerBean.resetContact}"									value="#{cmnContactMsg['label.commonContact.reset']}"></t:commandLink>
								<h:outputText id="PRGCMGTOT772" escape="false"									value="#{ref['label.ref.linkSeperator']}" />
							</m:div>
							<t:commandLink id="PRGCMGTCL125"								action="#{commonContactControllerBean.cancelContact}"								value="#{cmnContactMsg['label.commonContact.cancel']}"></t:commandLink>
						</m:div>

					</m:div>
					<m:div styleClass="moreInfoContent">
						<m:div>
						</m:div>
						<m:div>
							<m:div>
							</m:div>
						</m:div>
						<m:table id="PROVIDERMMT20120731164811346" cellspacing="0" width="90%">

							<m:br />
							<m:div rendered="#{commonEntityDataBean.contactSaveMsg}"
								styleClass="msgbox">
								<h:outputText id="PRGCMGTOT773" value="#{cmnContactMsg['info.Contact_Save_Succ']}"></h:outputText>
							</m:div>
							<m:br />

							<m:tr>
								<m:td>
									<h:outputLabel id="PRGCMGTOLL372" for="title"										value="#{cmnContactMsg['label.commonContact.title']}"></h:outputLabel>
									<m:br></m:br>
									<h:inputText id="title" size="15" maxlength="20"										value="#{commonEntityDataBean.commonEntityVO.commonContactVO.nameVO.titleName}"></h:inputText>
								</m:td>

								<m:td>
									<h:outputLabel id="PRGCMGTOLL373" for="prfix"										value="#{cmnContactMsg['label.commonContact.prefix']}"></h:outputLabel>
									<m:br></m:br>
									<h:selectOneMenu id="prfix"										value="#{commonEntityDataBean.commonEntityVO.commonContactVO.prefix}">
										<f:selectItems value="#{commonEntityDataBean.namePrefixList}" />
									</h:selectOneMenu>
								</m:td>

								<m:td>
									<m:span styleClass="required">
									<h:outputText id="PRGCMGTOT774" value="*" ></h:outputText>	
									</m:span>
									<h:outputLabel id="PRGCMGTOLL374" for="fstNam"										value="#{cmnContactMsg['label.commonContact.firstName']}"></h:outputLabel>
									<m:br></m:br>
									<h:inputText id="fstNam" size="15" maxlength="25"										value="#{commonEntityDataBean.commonEntityVO.commonContactVO.nameVO.firstName}"></h:inputText>
								</m:td>

								<m:td>
									<h:outputLabel id="PRGCMGTOLL375" for="mdlName"										value="#{cmnContactMsg['label.commonContact.middleName']}"></h:outputLabel>
									<m:br></m:br>
									<h:inputText id="mdlName" size="15" maxlength="25"										value="#{commonEntityDataBean.commonEntityVO.commonContactVO.nameVO.middleName}"></h:inputText>
								</m:td>

								<m:td>
									<m:span styleClass="required">
										<h:outputText id="PRGCMGTOT775" value="*" ></h:outputText>	
									</m:span>
									<h:outputLabel id="PRGCMGTOLL376" for="lstNam"										value="#{cmnContactMsg['label.commonContact.lastName']}"></h:outputLabel>
									<m:br></m:br>
									<h:inputText id="lstNam" size="20" maxlength="35"										value="#{commonEntityDataBean.commonEntityVO.commonContactVO.nameVO.lastName}"></h:inputText>
								</m:td>

								<m:td>
									<h:outputLabel id="PRGCMGTOLL377" for="sffxname"										value="#{cmnContactMsg['label.commonContact.suffix']}"></h:outputLabel>
									<m:br></m:br>
									<h:selectOneMenu id="sffxname"										value="#{commonEntityDataBean.commonEntityVO.commonContactVO.nameVO.suffixName}">
										<f:selectItem id="jr" itemLabel="Jr" itemValue="0" />
										<f:selectItem id="sr" itemLabel="Sr" itemValue="1" />
									</h:selectOneMenu>
								</m:td>
							</m:tr>
							<m:tr>
								<m:td>
									<h:outputLabel id="PRGCMGTOLL378" for="ssn"										value="#{cmnContactMsg['label.commonContact.ssn']}"></h:outputLabel>
									<m:br></m:br>
									<h:inputText id="ssn" size="15" maxlength="11"										value="#{commonEntityDataBean.commonEntityVO.commonContactVO.SSN}"></h:inputText>
								</m:td>

								<m:td>
									<h:outputLabel id="PRGCMGTOLL379" for="dob2"										value="#{cmnContactMsg['label.commonContact.dateOfBirth']}"></h:outputLabel>
									<m:br></m:br>


									<m:div styleClass="padb">

										<m:inputCalendar monthYearRowClass="yearMonthHeader" size="10"
											weekRowClass="weekHeader" 
											currentDayCellClass="currentDayCell"
											value="#{commonEntityDataBean.commonEntityVO.commonContactVO.dateOfBirth}"
											renderAsPopup="true" addResources="true"
											renderPopupButtonAsImage="true" popupDateFormat="MM/dd/yyyy"
											readonly="false" id="dob2">
										</m:inputCalendar>

									</m:div>




								</m:td>


								<m:td>
									<h:outputLabel id="PRGCMGTOLL380" for="dod"										value="#{cmnContactMsg['label.commonContact.dateOfDeath']}"></h:outputLabel>
									<m:br></m:br>
									<m:inputCalendar monthYearRowClass="yearMonthHeader" size="10"
										weekRowClass="weekHeader" currentDayCellClass="currentDayCell"
										value="#{commonEntityDataBean.commonEntityVO.commonContactVO.dateOfDeath}"
										renderAsPopup="true" addResources="true" 
										renderPopupButtonAsImage="true" popupDateFormat="MM/dd/yyyy"
										readonly="false" id="dod">
									</m:inputCalendar>
								</m:td>


								<m:td>
									<h:outputLabel id="PRGCMGTOLL381" for="gender"										value="#{cmnContactMsg['label.commonContact.gender']}"></h:outputLabel>
									<m:br></m:br>
									<h:selectOneMenu id="gender"										value="#{commonEntityDataBean.commonEntityVO.commonContactVO.gender}">
										<f:selectItems value="#{commonEntityDataBean.genderList}" />
									</h:selectOneMenu>
								</m:td>


								<m:td>
									<m:span styleClass="required">
										<h:outputText id="PRGCMGTOT776" value="*" ></h:outputText>
									</m:span>
									<h:outputLabel id="PRGCMGTOLL382" for="entitytype"										value="#{cmnContactMsg['label.commonContact.entityType']}"></h:outputLabel>
									<m:br></m:br>
									<h:selectOneMenu id="entitytype"										value="#{commonEntityDataBean.commonEntityVO.commonContactVO.entityType}">
										<f:selectItems value="#{commonEntityDataBean.entityTypeList}" />
									</h:selectOneMenu>
								</m:td>
							</m:tr>

						</m:table>
					</m:div>
					<m:div
						rendered="#{not empty commonEntityDataBean.commonEntityVO.commonContactVO.contactSK}">
						<f:subview id="ContactAuditInc">
							<jsp:include
								page="/jsp/logcorrespondence/inc_ContactAuditLogCnI.jsp" />
						</f:subview>
					</m:div>

					<m:table id="PROVIDERMMT20120731164811347" styleClass="tableBar" width="98%">
						<m:tr>
							<m:td styleClass="tableTitle">
							</m:td>
							<m:td styleClass="tableAction">
								<m:div styleClass="rightCell">
									<h:commandButton id="PRGCMGTCB25" styleClass="formBtn"										action="#{commonContactControllerBean.addContactType}"										disabled="true"										value="#{cmnContactMsg['label.commonContact.addContactType']}"></h:commandButton>
								</m:div>
							</m:td>
						</m:tr>
					</m:table>
					<t:saveState id="CMGTTOMSS225"						value="#{commonEntityDataBean.commonEntityVO.commonContactVO.commonContactTypeVOList}" />
					<t:dataTable rows="5"						binding="#{commonContactControllerBean.contactTypDataTable}"						value="#{commonEntityDataBean.commonEntityVO.commonContactVO.commonContactTypeVOList}"						var="contactType" rowIndexVar="rowIndex" cellspacing="0"						columnClasses="columnClass" headerClass="headerClass"						footerClass="footerClass" rowClasses="row_alt,row"						rowOnMouseOver="this.origClassName = this.className;this.className='row_mouse row_link';"						rowOnMouseOut="if (this.origClassName) this.className = this.origClassName;"						styleClass="dataTable" id="contTypHist" width="98%">

						<t:column id="conatctHistTypClm1" styleClass="otherbg">

							<f:facet name="header">
								<h:panelGrid id="contpanel11" columns="2">
									<h:outputLabel id="PRGCMGTOLL383" for="contpanegrpl12"										value="#{cmnContactMsg['label.commonContact.void']}" />
									<h:panelGroup id="contpanegrpl12">

										<t:div style="width:px;align=left">
											<t:commandLink id="PRGCMGTCL126" style="text-decoration: none;"												actionListener="#{commonContactControllerBean.sortContactType}">
												<h:graphicImage id="PROVIDERGI20120731164811348" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}" styleClass="sort_asc"
													width="8" url="/images/x.gif"/>
												<f:attribute name="columnName"
													value="#{cmnContactMsg['label.commonContact.void']}" />
												<f:attribute name="sortOrder" value="asc" />
											</t:commandLink>
										</t:div>
										<t:div style="width:px;align=left">
											<t:commandLink id="PRGCMGTCL127" style="text-decoration: none;"												actionListener="#{commonContactControllerBean.sortContactType}">
												<h:graphicImage id="PROVIDERGI20120731164811349" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}" styleClass="sort_desc"
													width="8" url="/images/x.gif"/>
												<f:attribute name="columnName"
													value="#{cmnContactMsg['label.commonContact.void']}" />
												<f:attribute name="sortOrder" value="desc" />
											</t:commandLink>
										</t:div>
									</h:panelGroup>
								</h:panelGrid>
							</f:facet>
							<h:outputText id="PRGCMGTOT777"								value="#{contactType.voidIndicator == '1'? 'No' : 'Yes' }" />
						</t:column>


						<t:column id="conatctHistTypClm2" styleClass="otherbg">

							<f:facet name="header">
								<h:panelGrid columns="2" id="contpanegrpl13">

									<h:outputLabel id="PRGCMGTOLL384" for="contpanegrpl14"										value="#{cmnContactMsg['label.commonContact.contactType']}" />
									<h:panelGroup id="contpanegrpl14">
										<t:div style="width:px;align=left">
											<t:commandLink id="PRGCMGTCL128" style="text-decoration: none;"												actionListener="#{commonContactControllerBean.sortContactType}">
												<h:graphicImage id="PROVIDERGI20120731164811350" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}" styleClass="sort_asc"
													width="8" url="/images/x.gif"/>
												<f:attribute name="columnName"
													value="#{cmnContactMsg['label.commonContact.contactType']}" />
												<f:attribute name="sortOrder" value="asc" />
											</t:commandLink>
										</t:div>
										<t:div style="width:px;align=left">
											<t:commandLink id="PRGCMGTCL129" style="text-decoration: none;"												actionListener="#{commonContactControllerBean.sortContactType}">
												<h:graphicImage id="PROVIDERGI20120731164811351" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}" styleClass="sort_desc"
													width="8" url="/images/x.gif"/>
												<f:attribute name="columnName"
													value="#{cmnContactMsg['label.commonContact.contactType']}" />
												<f:attribute name="sortOrder" value="desc" />
											</t:commandLink>
										</t:div>
									</h:panelGroup>
								</h:panelGrid>
							</f:facet>
							<t:commandLink id="PRGCMGTCL130"								action="#{commonContactControllerBean.displayDetailedContactTypeInfo}">
								<h:outputText id="PRGCMGTOT778" value="#{contactType.contactTypeDesc}" />
								<f:param name="indexCode" value="#{rowIndex}"></f:param>
							</t:commandLink>
						</t:column>



						<t:column id="conatctHistTypClm3" styleClass="otherbg">

							<f:facet name="header">
								<h:panelGrid columns="2" id="contpanegrpl15">
									<h:outputLabel id="PRGCMGTOLL385" for="contpanegrpl16"										value="#{cmnContactMsg['label.commonContact.significance']}" />
									<h:panelGroup id="contpanegrpl16">

										<t:div style="width:px;align=left">
											<t:commandLink id="PRGCMGTCL131" style="text-decoration: none;"												actionListener="#{commonContactControllerBean.sortContactType}">
												<h:graphicImage id="PROVIDERGI20120731164811352" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}" styleClass="sort_asc"
													width="8" url="/images/x.gif"/>
												<f:attribute name="columnName"
													value="#{cmnContactMsg['label.commonContact.significance']}" />
												<f:attribute name="sortOrder" value="asc" />
											</t:commandLink>
										</t:div>
										<t:div style="width:px;align=left">
											<t:commandLink id="PRGCMGTCL132" style="text-decoration: none;"												actionListener="#{commonContactControllerBean.sortContactType}">
												<h:graphicImage id="PROVIDERGI20120731164811353" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}" styleClass="sort_desc"
													width="8" url="/images/x.gif"/>
												<f:attribute name="columnName"
													value="#{cmnContactMsg['label.commonContact.significance']}" />
												<f:attribute name="sortOrder" value="desc" />
											</t:commandLink>
										</t:div>
									</h:panelGroup>
								</h:panelGrid>
							</f:facet>
							<h:outputText id="PRGCMGTOT779" value="#{contactType.significanceDesc}" />
						</t:column>


						<t:column id="conatctHistTypClm4" styleClass="otherbg">

							<f:facet name="header">
								<h:panelGrid columns="2" id="contpanegrpl36">
									<h:outputLabel id="PRGCMGTOLL386" for="contpanegrpl17"										value="#{cmnContactMsg['label.commonContact.beginDate']}" />
									<h:panelGroup id="contpanegrpl17">

										<t:div style="width:px;align=left">
											<t:commandLink id="PRGCMGTCL133" style="text-decoration: none;"												actionListener="#{commonContactControllerBean.sortContactType}">
												<h:graphicImage id="PROVIDERGI20120731164811354" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}" styleClass="sort_asc"
													width="8" url="/images/x.gif"/>
												<f:attribute name="columnName"
													value="#{cmnContactMsg['label.commonContact.beginDate']}" />
												<f:attribute name="sortOrder" value="asc" />
											</t:commandLink>
										</t:div>
										<t:div style="width:px;align=left">
											<t:commandLink id="PRGCMGTCL134" style="text-decoration: none;"												actionListener="#{commonContactControllerBean.sortContactType}">
												<h:graphicImage id="PROVIDERGI20120731164811355" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}" styleClass="sort_desc"
													width="8" url="/images/x.gif"/>
												<f:attribute name="columnName"
													value="#{cmnContactMsg['label.commonContact.beginDate']}" />
												<f:attribute name="sortOrder" value="desc" />
											</t:commandLink>
										</t:div>
									</h:panelGroup>
								</h:panelGrid>
							</f:facet>
							<h:outputText id="PRGCMGTOT780" value="#{contactType.strBeginDate}" />
						</t:column>

						<t:column id="conatctHistTypClm5" styleClass="otherbg">

							<f:facet name="header">
								<h:panelGrid columns="2" id="contpanegrpl18">
									<h:outputLabel id="PRGCMGTOLL387" for="contpanegrpl19"										value="#{cmnContactMsg['label.commonContact.endDate']}" />
									<h:panelGroup id="contpanegrpl19">

										<t:div style="width:px;align=left">
											<t:commandLink id="PRGCMGTCL135" style="text-decoration: none;"												actionListener="#{commonContactControllerBean.sortContactType}">
												<h:graphicImage id="PROVIDERGI20120731164811356" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}" styleClass="sort_asc"
													width="8" url="/images/x.gif"/>
												<f:attribute name="columnName"
													value="#{cmnContactMsg['label.commonContact.endDate']}" />
												<f:attribute name="sortOrder" value="asc" />
											</t:commandLink>
										</t:div>
										<t:div style="width:px;align=left">
											<t:commandLink id="PRGCMGTCL136" style="text-decoration: none;"												actionListener="#{commonContactControllerBean.sortContactType}">
												<h:graphicImage id="PROVIDERGI20120731164811357" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}" styleClass="sort_desc"
													width="8" url="/images/x.gif"/>
												<f:attribute name="columnName"
													value="#{cmnContactMsg['label.commonContact.endDate']}" />
												<f:attribute name="sortOrder" value="desc" />
											</t:commandLink>
										</t:div>
									</h:panelGroup>
								</h:panelGrid>
							</f:facet>
							<h:outputText id="PRGCMGTOT781" value="#{contactType.strEndDate}" />
						</t:column>


						<f:facet name="footer">
								<m:div id="nodata" align="center">
									<h:outputText id="PRGCMGTOT782" value="No Data"										rendered="#{empty commonEntityDataBean.commonEntityVO.commonContactVO.commonContactTypeVOList}"></h:outputText>
								</m:div>
						</f:facet>

					</t:dataTable>
					<m:div styleClass="searchResults">
						<t:dataScroller id="CMGTTOMDS32" pageCountVar="pageCount" pageIndexVar="pageIndex"							paginator="true" paginatorActiveColumnStyle='font-weight:bold;'							paginatorMaxPages="3" immediate="false" for="contTypHist"							firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"							rowsCountVar="rowsCount"							style="float:right;position:relative;bottom:-6px">

							<f:facet name="previous">
								<h:outputText id="PRGCMGTOT783" style="text-decoration:underline;" value=" << "									rendered="#{pageIndex > 1}"></h:outputText>
							</f:facet>
							<f:facet name="next">
								<h:outputText id="PRGCMGTOT784" style="text-decoration:underline;" value=" >> "
									rendered="#{pageIndex < pageCount}"></h:outputText>
							</f:facet>

							<h:outputText id="PRGCMGTOT785"								value="showing #{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"								style="float:left;position:relative;bottom:-6px"								rendered="#{rowsCount>0}">
							</h:outputText>
						</t:dataScroller>

						<m:div>
							<m:br></m:br>
						</m:div>
					</m:div>


					<m:div>
						<m:br></m:br>
					</m:div>

					<m:div styleClass="" id="nType2Contact1_table"
						rendered="#{commonEntityDataBean.newContactTypeRender}">

						<m:div styleClass="moreInfo">
							<m:div styleClass="moreInfoBar">
								<m:div styleClass="infoTitle">
									<h:outputText id="PRGCMGTOT786"										value="#{commonEntityDataBean.contactTypeEdit? cmnContactMsg['label.commonContact.editContactType'] : cmnContactMsg['label.commonContact.newContactType'] }" />
								</m:div>

								<m:div styleClass="infoActions">
									<m:div rendered="#{CorrespondenceDataBean.controlRequired}">
										<t:commandLink id="PRGCMGTCL137" styleClass="strong"											action="#{commonContactControllerBean.saveContactType}"											rendered="false"											value="#{cmnContactMsg['label.commonContact.save']}"></t:commandLink>
										<h:outputText id="PRGCMGTOT787" escape="false"											value="#{ref['label.ref.linkSeperator']}" />
										<t:commandLink id="PRGCMGTCL138"											action="#{commonContactControllerBean.resetContactType}"											value="#{cmnContactMsg['label.commonContact.reset']}"></t:commandLink>
										<h:outputText id="PRGCMGTOT788" escape="false"											value="#{ref['label.ref.linkSeperator']}" />
										</m:div>
									<t:commandLink id="PRGCMGTCL139"										action="#{commonContactControllerBean.cancelContactType}"										value="#{cmnContactMsg['label.commonContact.cancel']}"></t:commandLink>
								</m:div>
							</m:div>
							<m:div styleClass="moreInfoContent">
								<m:div>
								</m:div>
								<m:div>
									<m:div>
									</m:div>
									<m:table id="PROVIDERMMT20120731164811358" cellspacing="0" width="90%">

										<m:br></m:br>
										<m:div rendered="#{commonEntityDataBean.contactTypeSaveMsg}"
											styleClass="msgbox">
											<h:outputText id="PRGCMGTOT789"												value="#{cmnContactMsg['info.Contact_Save_Succ']}"></h:outputText>
										</m:div>
										<m:br></m:br>

										<m:tr>
											<m:td>
												<m:div styleClass="padb">
													
														<h:outputLabel id="PRGCMGTOLL388" for="voidIndicatorValue"															value="#{cmnContactMsg['label.commonContact.voidIndicator']}" />
													
													<m:br></m:br>
													<h:selectOneRadio														disabled="#{(commonEntityDataBean.commonEntityVO.commonContactVO.commonContactTypeVO.voidIndicator==0 || commonEntityDataBean.addcontactVoidIndicatorRender) || (empty commonEntityDataBean.commonEntityVO.commonContactVO.commonContactTypeVO.contactSK)}"														onclick="this.form.submit( );" id="voidIndicatorValue"														valueChangeListener="#{commonContactControllerBean.voidChange}"														value="#{commonEntityDataBean.commonEntityVO.commonContactVO.commonContactTypeVO.voidIndicator}">
														<f:selectItem id="voidIndicator1" itemValue="0"
															itemLabel="Yes" />
														<f:selectItem id="voidIndicator2" itemValue="1"
															itemLabel="No" />
													</h:selectOneRadio>
												</m:div>
											</m:td>

											<m:td>
												<m:span styleClass="required">
													<h:outputText id="PRGCMGTOT790" value="*" ></h:outputText>
												</m:span>
												<h:outputLabel id="PRGCMGTOLL389" for="cntcttype"													value="#{cmnContactMsg['label.commonContact.contactType']}"></h:outputLabel>
												<m:br></m:br>
												<h:selectOneMenu immediate="true" id="cntcttype"													disabled="#{commonEntityDataBean.commonEntityVO.commonContactVO.commonContactTypeVO.voidIndicator==0 or commonEntityDataBean.disableContactType}"													value="#{commonEntityDataBean.commonEntityVO.commonContactVO.commonContactTypeVO.contactType}">
													<f:selectItems
														value="#{commonEntityDataBean.contactTypeList}" />
												</h:selectOneMenu>

											</m:td>

											<m:td>
												<h:outputLabel id="beginDate1" for="contactBeginDt"													value="#{cmnContactMsg['label.commonContact.beginDate']}">
													<m:span styleClass="required">
														<h:outputText id="PRGCMGTOT791" value="*" ></h:outputText>
													</m:span>
												</h:outputLabel>
												<m:br></m:br>

												<m:inputCalendar monthYearRowClass="yearMonthHeader"
													size="10" weekRowClass="weekHeader"
													currentDayCellClass="currentDayCell"
													value="#{commonEntityDataBean.commonEntityVO.commonContactVO.commonContactTypeVO.beginDate}"
													renderAsPopup="true" addResources="true"
													renderPopupButtonAsImage="true"
													popupDateFormat="MM/dd/yyyy" readonly="false"
													id="contactBeginDt"
													disabled="#{commonEntityDataBean.commonEntityVO.commonContactVO.commonContactTypeVO.voidIndicator==0 or commonEntityDataBean.disableContactType}">
												</m:inputCalendar>


											</m:td>

											<m:td>
												<h:outputLabel id="enddate" for="contactEndDt"													value="#{cmnContactMsg['label.commonContact.endDate']}">
													<m:span styleClass="required">
														<h:outputText id="PRGCMGTOT792" value="*" ></h:outputText>
													</m:span>
												</h:outputLabel>
												<m:br></m:br>
												<m:inputCalendar monthYearRowClass="yearMonthHeader"
													size="10" weekRowClass="weekHeader"
													currentDayCellClass="currentDayCell"
													value="#{commonEntityDataBean.commonEntityVO.commonContactVO.commonContactTypeVO.endDate}"
													renderAsPopup="true" addResources="true"
													renderPopupButtonAsImage="true"
													popupDateFormat="MM/dd/yyyy" readonly="false"
													id="contactEndDt"
													disabled="#{commonEntityDataBean.commonEntityVO.commonContactVO.commonContactTypeVO.voidIndicator==0 or commonEntityDataBean.disableContactType}">
												</m:inputCalendar>

											</m:td>

											<m:td>
												<h:outputLabel id="PRGCMGTOLL390" for="signfnce"													value="#{cmnContactMsg['label.commonContact.significance']}"></h:outputLabel>
												<m:br></m:br>
												<h:selectOneMenu id="signfnce"													disabled="#{commonEntityDataBean.commonEntityVO.commonContactVO.commonContactTypeVO.voidIndicator==0 or commonEntityDataBean.disableContactType}"													value="#{commonEntityDataBean.commonEntityVO.commonContactVO.commonContactTypeVO.significance}">
													<f:selectItems
														value="#{commonEntityDataBean.contactSigList}" />
												</h:selectOneMenu>
											</m:td>

										</m:tr>


									</m:table>
									<t:saveState id="CMGTTOMSS226"										value="#{commonEntityDataBean.commonEntityVO.commonContactVO.commonContactTypeVO.contactSK}" />
									<m:div
										rendered="#{not empty commonEntityDataBean.commonEntityVO.commonContactVO.commonContactTypeVO.contactSK}">
										<f:subview id="ContactTypeAuditInc">
											<jsp:include
												page="/jsp/logcorrespondence/inc_ContactTypeAuditLogCnI.jsp" />
										</f:subview>
									</m:div>
								</m:div>
							</m:div>
						</m:div>

					</m:div>
				</m:div>
			</m:div>
		</m:div>
	</m:section>
</f:subview>


<t:saveState id="CMGTTOMSS227"	value="#{commonEntityDataBean.commonEntityVO.contactVOList}" />
<t:saveState id="CMGTTOMSS228" value="#{commonEntityDataBean.mainContactRender}" />
<t:saveState id="CMGTTOMSS229"	value="#{commonEntityDataBean.commonEntityVO.commonEntitySK}" />

<t:saveState id="CMGTTOMSS230" value="#{commonEntityDataBean.newContactRender}" />
<t:saveState id="CMGTTOMSS231" value="#{commonEntityDataBean.contactTypeEdit}" />
<t:saveState id="CMGTTOMSS232" value="#{commonEntityDataBean.contactEdit}" />
<t:saveState id="CMGTTOMSS233" value="#{commonEntityDataBean.newContactTypeRender}" />
<t:saveState id="CMGTTOMSS234" value="#{commonEntityDataBean.selectedConatctIndex}" />
<t:saveState id="CMGTTOMSS235" value="#{commonEntityDataBean.selectedConatctTypIndex}" />
<t:saveState id="CMGTTOMSS236" value="#{commonEntityDataBean.saveContactTypeChk}" />
<t:saveState id="CMGTTOMSS237" value="#{commonEntityDataBean.disableContactType}" />

<t:saveState id="CMGTTOMSS238" value="#{commonEntityDataBean.namePrefixList}" />
<t:saveState id="CMGTTOMSS239" value="#{commonEntityDataBean.genderList}" />
<t:saveState id="CMGTTOMSS240" value="#{commonEntityDataBean.entityTypeList}" />
<t:saveState id="CMGTTOMSS241" value="#{commonEntityDataBean.contactTypeList}" />
<t:saveState id="CMGTTOMSS242" value="#{commonEntityDataBean.contactSigList}" />
<t:saveState id="CMGTTOMSS243"	value="#{commonEntityDataBean.commonEntityVO.commonContactVO.commonContactTypeVO.voidIndicator}" />
<t:saveState id="CMGTTOMSS244"	value="#{commonEntityDataBean.addcontactVoidIndicatorRender}" />
<t:saveState id="CMGTTOMSS245"	value="#{commonEntityDataBean.commonEntityVO.commonContactVO.commonContactTypeVO.beginDate}" />
<t:saveState id="CMGTTOMSS246"	value="#{commonEntityDataBean.commonEntityVO.commonContactVO.commonContactTypeVO.endDate}" />
<t:saveState id="CMGTTOMSS247"	value="#{commonEntityDataBean.commonEntityVO.commonContactVO.commonContactTypeVO.significance}" />
<t:saveState id="CMGTTOMSS248"	value="#{commonEntityDataBean.commonEntityVO.commonContactVO.commonContactTypeVO.contactType}" />
<t:saveState id="CMGTTOMSS249"	value="#{commonEntityDataBean.commonEntityVO.commonContactVO.contactSK}" />


