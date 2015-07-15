<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>

<portlet:defineObjects />


<script>
	function textCounter(field, maxlimit) {
		var el = field;
		if (el.value.length > maxlimit) {
			el.value = el.value.substring(0, maxlimit);
		}
		var remChar = maxlimit - el.value.length;
		var ibox = document
				.getElementById('view<portlet:namespace/>:addNewAppealsViewForm:administrativeHearingSubViewId:notesSet:charRemaining');
		if (ibox != null)
			ibox.value = remChar;

	}
	/*This method finds an object from the DOM by acception a part of it's ID as parameter.*/
	function findObjectByPartOfIDForAppeal(partOfID)
	{
		for(i=0; i<document.forms.length; i++)
		{
			for(j=0; j<document.forms[i].elements.length; j++)
			{
				var idValue = document.forms[i].elements[j].id;
				if(idValue.indexOf(partOfID)!=-1)
				{
					G_isFirstTime = false;
					G_countObject = document.forms[i].elements[j];
					return document.forms[i].elements[j];
				}
			}
		}		
		return null;
	}

	function doPrint() {
		// grab all the div elements for parsing
		var tags = document.body.getElementsByTagName("div");
		var entityId=findObjectByPartOfIDForAppeal('HDNENTITYID');
		var entityType=findObjectByPartOfIDForAppeal('HDNENTITYTYPE');
		var entityName=findObjectByPartOfIDForAppeal('HDNENTITYNAME');
		var entId='';
		var entTy='';
		var entNm='';
		if(entityId!=null)
			entId=entityId.value;
		if(entityType!=null)
			entTy=entityType.value;
		if(entityName!=null)
			entNm=entityName.value;
		if(entTy!=null && entTy!=''){
			if(entTy=='M'|| entTy=='m')
				entTy='M-Member';
			if(entTy=='P'|| entTy=='p')
				entTy='P-Provider';
			if(entTy=='O'|| entTy=='o')
				entTy='O-Other';
		}

		var importStr = "<link rel='stylesheet' type='text/css' href='/wps/themes/html/ACSDefault/style/portal.css'></link>"
				+ "<link rel='stylesheet' type='text/css' href='/wps/themes/html/ACSDefault/style/selfservice.css'></link>"
				+ "<link rel='stylesheet' type='text/css' href='/wps/themes/html/ACSDefault/style/print.css' media='print'></link>"
				+ "<link rel='stylesheet' type='text/css' href='/wps/themes/html/ACSDefault/style/defaultStyles.css'></link>"
				+ "<link rel='stylesheet' type='text/css' href='/wps/themes/html/ACSDefault/style/theme.css'></link> ";
		var printableHTML = importStr
				+ "<html><body><center> <table border='0' cellspacing='0' width='100%' class='dataTable'>";

		printableHTML = printableHTML
				+ "<tr><TD class='otherbg'>Component: Appeal Record </TD><TD align='right' class='otherbg'><a style='text-decoration:none' onclick='self.print();' href='#'>Print&nbsp;&nbsp;</a></TD></tr>";
		printableHTML = printableHTML + "<tr><TD class='otherbg'>Entity ID: "+
		entId+ "</TD><TD class='otherbg'></TD></tr>";
		printableHTML = printableHTML + "<tr><TD class='otherbg'>Entity Type: "+
		entTy+ "</TD><TD class='otherbg'></TD></tr>";
		printableHTML = printableHTML + "<tr><TD class='otherbg'>Entity Name: "+
		entNm+ "</TD><TD class='otherbg'></TD></tr>";
		printableHTML = printableHTML
				+ "<tr><TD class='otherbg'>Functional Area: Case</TD><TD class='otherbg'></TD></tr>";

		/* printableHTML = printableHTML + "<tr><TD class='otherbg'>Key:</TD></tr>";*/

		printableHTML = printableHTML + "</table></center>"

		printableHTML = printableHTML
				+ "<center> <table border='0' cellspacing='0' width='100%' class='dataTable'>";
		for ( var i = 0; i < tags.length; i++) {
			var pHTMLElement = tags[i];
			if (pHTMLElement.id.indexOf("notesScrollTable") != -1) {
				var nodesize = pHTMLElement.childNodes.length;
				var tableLength = pHTMLElement.childNodes[0].childNodes.length;
				var tableNode = pHTMLElement.childNodes[0];
				for ( var j = 0; j < tableLength; j++) {

					if (j == 2) {
						var rows = tableNode.childNodes[j];
						var rowsLength = rows.childNodes.length;
						for ( var k = 0; k < rowsLength; k++) {
							var row = rows.childNodes[k];
							var elements = row.getElementsByTagName("input");
							var checkBox = elements[0];
							if (checkBox.checked) {
								var noteTxtHidden = elements[1];
								var fullNotes = noteTxtHidden.value;
								printableHTML = printableHTML + "<tr>";
								var columns = row.childNodes;
								for ( var col = 0; col < columns.length; col++) {
									if (col == 3) {
										printableHTML = printableHTML
												+ "<TD class='otherbg'>"
												+ fullNotes + "</TD>";
									} else {
										printableHTML = printableHTML
												+ "<TD class='otherbg'>"
												+ columns[col].innerHTML
												+ "</TD>";
									}
								}
								printableHTML = printableHTML + "</tr>";
							}
						}
					} else {
						printableHTML = printableHTML
								+ tableNode.childNodes[j].innerHTML;
					}
				}
			}
		}

		printableHTML = printableHTML + "</table></center></body></html>";

		/*	var div =   document.getElementById("printdic");   
		 div.innerHTML =printableHTML;
		 var txt =   document.getElementById("txt");   
		 txt.value =printableHTML;
		 */

		var disp_setting = "toolbar=false,location=no,directories=false,menubar=false,";
		disp_setting += "scrollbars=yes,width=800, height=300, left=100, top=25";
		var docprint = window.open("", "", disp_setting);
		docprint.document.open();
		docprint.document.write(printableHTML);
		docprint.document.close();
		//docprint.close();

	}
</script>
<%-- Holds the name of the property file to be reffered --%>
<f:loadBundle
	basename="com.acs.enterprise.common.program.commonentities.nl.PGM_CE_CommonNotes"
	var="cmnNotesMsg" />
<m:div rendered="#{commonEntityDataBean.detailMainNotesRender}"
	id="notesman">

	<m:br />
	<m:br />

	<m:div styleClass="portletTitleBar">
		<m:div styleClass="portletTitle">
			<f:verbatim>Notes </f:verbatim>
		</m:div>
	</m:div>
	<m:div styleClass="floatContainer">

		<m:div styleClass="moreInfoBar">
			<m:table id="PROVIDERMMT20120731164811171" styleClass="tableBar" width="100%">
				<m:div id="actionsDiv1" styleClass="actions">
					<m:div styleClass="infoTitle" style="color:darkred">
						<h:outputText id="PRGCMGTOT342"
							value="#{cmnNotesMsg['label.commonNotes.requiredField']}" />
					</m:div>

					<m:div styleClass="rightCell">
						<t:commandLink id="commonNotesSave" styleClass="strong"
							action="#{detailedNotesControllerBean.saveNotes}"
							onmousedown="javascript:focusThis(this.id);javascript:flagWarn=false;">
							<h:outputText id="PRGCMGTOT343"
								value="#{cmnNotesMsg['label.commonNotes.save']}">
							</h:outputText>
							<f:param name="NotesLevel" value="Inner"></f:param>
						</t:commandLink>


						<h:outputText id="cmnNotesar2"
							rendered="#{commonEntityDataBean.filterEnabled && commonEntityDataBean.commonEntityVO.noteSetVO.filterLinkRender}"
							value="|">
						</h:outputText>

						<t:commandLink id="commonNotesFilter"
							onmousedown="javascript:focusThis(this.id);javascript:flagWarn=false;"
							rendered="#{commonEntityDataBean.filterEnabled && commonEntityDataBean.commonEntityVO.noteSetVO.filterLinkRender}"
							action="#{detailedNotesControllerBean.filterNotes}">
							<h:outputText id="PRGCMGTOT345"
								value="#{cmnNotesMsg['label.commonNotes.filter']}">
							</h:outputText>
							<f:param name="NotesLevel" value="Inner"></f:param>
						</t:commandLink>

						<h:outputText id="cmnNotesbar3" value="|">
						</h:outputText>

						<h:outputLink id="PRGCMGTOLK20" value="javascript:doPrint();"
							onmousedown="javascript:flagWarn=false;">
							<h:outputText id="PRGCMGTOT346"
								rendered="#{commonEntityDataBean.commonEntityVO.detailNoteSetVO.printLinkRender}"
								value="#{cmnNotesMsg['label.commonNotes.printSelected']}">
							</h:outputText>
						</h:outputLink>

						<h:outputText id="cmnNotesar00" value="|">
						</h:outputText>

						<t:commandLink id="commonNotesAddNewNotes"
							onmousedown="javascript:focusThis(this.id);javascript:flagWarn=false;"
							rendered="#{commonEntityDataBean.commonEntityVO.detailNoteSetVO.addNewLinkRender}"
							action="#{detailedNotesControllerBean.addNewNotes}">
							<h:outputText id="PRGCMGTOT344"
								value="#{cmnNotesMsg['label.commonNotes.addNewNote']}">
							</h:outputText>
							<f:param name="NotesLevel" value="Inner"></f:param>
						</t:commandLink>

						<h:outputText id="cmnNotesbar4" value="|"></h:outputText>

						<t:commandLink action="#{detailedNotesControllerBean.cancelNotes}"
							id="commonNotesCancelID2"
							onmousedown="javascript:focusThis(this.id);">
							<h:outputText id="PRGCMGTOT347"
								value="#{cmnNotesMsg['label.commonNotes.cancel']}">
							</h:outputText>
							<f:param name="NotesLevel" value="Inner"></f:param>
						</t:commandLink>

					</m:div>
				</m:div>
			</m:table>
		</m:div>

		<m:div>
			<m:br></m:br>
		</m:div>

		<m:div rendered="#{commonEntityDataBean.detailNotesSaveMsg}"
			styleClass="msgbox">
			<h:outputText id="PRGCMGTOT348"
				value="#{cmnNotesMsg['err.pgm.cm.note.save.at.parent']}"
				rendered="#{commonEntityDataBean.detailNotesSaveMsg}"></h:outputText>
		</m:div>

		<m:div rendered="#{commonEntityDataBean.detailNewNotesRender}"
			id="notes_new">
			<t:htmlTag value="h3">
				<h:outputText id="lblAddNote"
					value="#{cmnNotesMsg['label.commonNotes.addNote']}"></h:outputText>
			</t:htmlTag>

			<m:table id="PROVIDERMMT20120731164811172" width="50%">
				<m:tr>
					<m:td>
						<m:div styleClass="padb">
							<m:span styleClass="required">
								<f:verbatim>*</f:verbatim>
							</m:span>
							<h:outputLabel id="lblUsageTypeCode" for="usageTypeCode"
								value="#{cmnNotesMsg['label.commonNotes.usageTypeCode']}"></h:outputLabel>

							<m:br></m:br>
							<h:selectOneMenu id="usageTypeCode"
								value="#{commonEntityDataBean.commonEntityVO.detailCommonNotesVO.usageTypeCode}"
								onmousedown="javascript:flagWarn=false;">
								<f:selectItem itemValue="" itemLabel="" />
								<f:selectItems value="#{commonEntityDataBean.noteTypeList}" />
							</h:selectOneMenu>
							<m:br id="noteBRID5"></m:br>
							<h:message for="lblUsageTypeCode" showDetail="true"
								style="color: red" id="AplnotesID26" />
						</m:div>
					</m:td>
				</m:tr>
			</m:table>

			<m:div>
				<m:br></m:br>
			</m:div>

			<m:div>
				<m:span styleClass="required">
					<f:verbatim>*</f:verbatim>
				</m:span>
				<h:outputLabel id="lblNotes" for="noteText"
					value="#{cmnNotesMsg['label.commonNotes.note']}"></h:outputLabel>
				<m:br></m:br>
				<h:inputTextarea id="noteText" rows="7" style="width:99%"
					onkeyup="textCounter(this,4000);"
					value="#{commonEntityDataBean.noteText}">
				</h:inputTextarea>
				<m:br id="lblNotesLinId"></m:br>
				<h:message for="lblNotes" showDetail="true" style="color: red"
					id="AplnotesID30" />
			</m:div>

			<m:div>
				<m:br></m:br>
			</m:div>
			<m:div>
				<h:inputText id="charRemaining" size="4" maxlength="4" value="4000"></h:inputText>
				<h:outputLabel id="lblCharRemaining" for="charRemaining"
					value="#{cmnNotesMsg['label.commonNotes.charRemaining']}"></h:outputLabel>
				<h:inputHidden id="commonNoteSaved"
					value="#{commonEntityDataBean.commonNoteSaved}"></h:inputHidden>
				<m:br></m:br>
			</m:div>
		</m:div>


		<m:div rendered="#{commonEntityDataBean.detailFilterNotesRender}"
			id="notes_filter">

			<t:htmlTag value="h3">
				<h:outputText id="lblFilter"
					value="#{cmnNotesMsg['label.commonNotes.filter']}"></h:outputText>
			</t:htmlTag>

			<m:table id="PROVIDERMMT20120731164811173" cellspacing="0" width="100%">
				<m:tr>
					<m:td>
						<m:span styleClass="required">
							<h:outputText id="note1" value="*">
							</h:outputText>
						</m:span>
						<h:outputLabel id="lblBeginDate" for="filterBeginDt"
							value="#{cmnNotesMsg['label.commonNotes.beginDate']}"></h:outputLabel>
						<m:br></m:br>

						<m:inputCalendar monthYearRowClass="yearMonthHeader"
							weekRowClass="weekHeader" currentDayCellClass="currentDayCell"
							value="#{commonEntityDataBean.commonNoteSearchVO.strBeginDate}"
							renderAsPopup="true" addResources="true"
							renderPopupButtonAsImage="true" popupDateFormat="MM/dd/yyyy"
							readonly="false" id="filterBeginDt">
						</m:inputCalendar>



					</m:td>

					<m:td>
						<m:span styleClass="required">
							<h:outputText id="star1" value="*"></h:outputText>
						</m:span>
						<h:outputLabel id="lblEndDate" for="filterEndDt"
							value="#{cmnNotesMsg['label.commonNotes.endDate']}"></h:outputLabel>
						<m:br></m:br>
						<m:inputCalendar monthYearRowClass="yearMonthHeader"
							weekRowClass="weekHeader" currentDayCellClass="currentDayCell"
							value="#{commonEntityDataBean.commonNoteSearchVO.strEndDate}"
							renderAsPopup="true" addResources="true"
							renderPopupButtonAsImage="true" popupDateFormat="MM/dd/yyyy"
							readonly="false" id="filterEndDt">
						</m:inputCalendar>

					</m:td>

					<m:td>
						<h:outputLabel id="lblUserId" for="cmnNoteUserIdTxt"
							value="#{cmnNotesMsg['label.commonNotes.userID']}">
						</h:outputLabel>
						<m:br></m:br>
						<h:inputText id="cmnNoteUserIdTxt" size="15"
							value="#{commonEntityDataBean.commonNoteSearchVO.userId}"></h:inputText>
					</m:td>

					<m:td>
						<h:outputLabel id="lblUsageTypeCode2" for="fltrUsageTypeCode"
							value="#{cmnNotesMsg['label.commonNotes.usageTypeCode']}"></h:outputLabel>
						<m:br></m:br>
						<h:selectOneMenu id="fltrUsageTypeCode"
							value="#{commonEntityDataBean.commonNoteSearchVO.usageTypeCode}"
							onmousedown="javascript:flagWarn=false;">
							<f:selectItem itemValue="" itemLabel="" />
							<f:selectItems value="#{commonEntityDataBean.noteTypeList}" />
						</h:selectOneMenu>
					</m:td>
				</m:tr>

				<m:tr>
					<m:td colspan="4">
						<m:div styleClass="buttonRow">
							<h:commandButton id="submitBtn" styleClass="formBtn"
								value="#{cmnNotesMsg['label.commonNotes.applyFilter']}"
								action="#{detailedNotesControllerBean.getAppealFilterNotes}"
								onmousedown="javascript:flagWarn=false;"></h:commandButton>
							<f:verbatim>&nbsp;</f:verbatim>
							<h:commandButton id="resetBtn" styleClass="formBtn"
								action="#{detailedNotesControllerBean.reset}"
								value="#{cmnNotesMsg['label.commonNotes.reset']}"
								onmousedown="javascript:flagWarn=false;">
							</h:commandButton>
						</m:div>
					</m:td>
				</m:tr>
			</m:table>

			<m:div>
				<m:br></m:br>
			</m:div>
		</m:div>

		<t:htmlTag value="h3">
			<h:outputText id="lblcurrentNote"
				value="#{cmnNotesMsg['label.commonNotes.currentNote']}"></h:outputText>
		</t:htmlTag>


		<m:table id="PROVIDERMMT20120731164811174" width="98%" align="center">
			<m:tr>
				<m:td>
					<m:section id="PROVIDERMMS20120731164811175" styleClass="otherbg">
						<m:div id="currentNote">
							<h:outputText id="currNoteTxt"
								value="#{commonEntityDataBean.commonEntityVO.detailCommonNotesVO.currentNote}"></h:outputText>
						</m:div>
						<m:div>
							<m:br></m:br>
						</m:div>
					</m:section>
				</m:td>
			</m:tr>
		</m:table>
		<m:div>
			<m:br></m:br>
		</m:div>

		<t:htmlTag value="h3">
			<h:outputText id="lblNotesHistory"
				value="#{cmnNotesMsg['label.commonNotes.notesHistory']}"></h:outputText>
		</t:htmlTag>



		<m:div id="notesScrollTable">

			<%-- Begin - Modified the dataTable for defect ESPRD00689748 --%>
			<t:dataTable border="0" id="cmnNoteHist"
				first="#{commonEntityDataBean.startIndexForCmnNotes}"
				styleClass="dataTable" width="98%" cellspacing="0" align="center"
				value="#{commonEntityDataBean.noteList}" columnClasses="columnClass"
				headerClass="headerClass" footerClass="footerClass"
				rowClasses="row_alt,row"
				rowOnClick="javascript:flagWarn=false;"
				rowOnMouseOver="this.origClassName = this.className;this.className='row_mouse row_link';"
				rowOnMouseOut="if (this.origClassName) this.className = this.origClassName;"
				var="note" rowIndexVar="rowIndex">

				<t:column>
					<f:facet name="header">
						<t:commandLink id="linkOsbe"
							action="#{detailedNotesControllerBean.checkBoxChanged}"
							onmousedown="javascript:flagWarn=false;">
							<h:outputLabel id="checkboxAPCNColumnLabel"  for="ckNotSeAll" styleClass="hide" value="#{ent['label.ent.selectAll']}" ></h:outputLabel>
							<t:selectBooleanCheckbox id="ckNotSeAll"
								value="#{commonEntityDataBean.commonEntityVO.detailNoteSetVO.checkAll}"></t:selectBooleanCheckbox>
						</t:commandLink>
					</f:facet>
					<h:outputLabel id="checkboxAPCNColumnLabel1"  for="noteschk1" styleClass="hide" value="#{ent['label.ent.select']}" ></h:outputLabel>
					<t:selectBooleanCheckbox id="noteschk1" value="#{note.checked}"></t:selectBooleanCheckbox>
				</t:column>

				<t:column id="CMGTTOMCS8" styleClass="otherbg">
					<%-- start of sorting code --%>
					<f:facet name="header">
						<h:panelGrid columns="2" id="incCommonNotes72">
							<h:outputLabel id="usg" for="notescmd1"
								value="#{cmnNotesMsg['label.commonNotes.dateTime']}" />
							<h:panelGroup id="notespanel1">

								<t:commandLink style="text-decoration: none;" id="notescmd1"
									actionListener="#{detailedNotesControllerBean.sort}"
									rendered="#{commonEntityDataBean.imageRender != 'notescmd1'}"
									onmousedown="javascript:flagWarn=false;">
									<m:div id="sortascid1" title="for ascending"
										styleClass="sort_asc" />
									<f:attribute name="columnName"
										value="DateTime" />
									<f:attribute name="sortOrder" value="asc" />
								</t:commandLink>

								<t:commandLink style="text-decoration: none;" id="notescmd2"
								rendered="#{commonEntityDataBean.imageRender != 'notescmd2'}"
									actionListener="#{detailedNotesControllerBean.sort}"
									onmousedown="javascript:flagWarn=false;">
									<m:div id="sortdescid1" title="for descending"
										styleClass="sort_desc" />
									<f:attribute name="columnName"
										value="DateTime" />
									<f:attribute name="sortOrder" value="desc" />
								</t:commandLink>
							</h:panelGroup>
						</h:panelGrid>
					</f:facet>
					<t:commandLink id="notescmd3"
						onclick="javascript:flagWarn=false;"
						action="#{detailedNotesControllerBean.displaySelectedNote}">
						<h:outputText id="PRGCMGTOT349" value="#{note.strBeginDate}" />
						<f:param name="indexCode" value="#{rowIndex}"></f:param>
					</t:commandLink>
				</t:column>

				<t:column id="CMGTTOMCS9" styleClass="otherbg">
					<f:facet name="header">
						<h:panelGrid columns="2" id="incCommonNotes73">
							<h:outputLabel id="PRGCMGTOT350" for="notescmd4"
								value="#{cmnNotesMsg['label.commonNotes.userID']}" />
							<h:panelGroup id="notespanel2">

								<t:commandLink style="text-decoration: none;" id="notescmd4"
								rendered="#{commonEntityDataBean.imageRender != 'notescmd4'}"
									actionListener="#{detailedNotesControllerBean.sort}"
									onmousedown="javascript:flagWarn=false;">
									<m:div id="sortascid2" title="for ascending"
										styleClass="sort_asc" />
									<f:attribute name="columnName"
										value="UserID" />
									<f:attribute name="sortOrder" value="asc" />
								</t:commandLink>

								<t:commandLink style="text-decoration: none;" id="notescmd5"
								rendered="#{commonEntityDataBean.imageRender != 'notescmd5'}"
									actionListener="#{detailedNotesControllerBean.sort}"
									onmousedown="javascript:flagWarn=false;">
									<m:div id="sortdescid2" title="for descending"
										styleClass="sort_desc" />
									<f:attribute name="columnName"
										value="UserID" />
									<f:attribute name="sortOrder" value="desc" />
								</t:commandLink>
							</h:panelGroup>
						</h:panelGrid>
					</f:facet>
					<h:outputText id="useridtext1" value="#{note.userId}" />
				</t:column>

				<t:column id="CMGTTOMCS10" width="55%" styleClass="otherbg">
					<f:facet name="header">
						<h:panelGrid columns="2" id="incCommonNotes74">
							<h:outputLabel for="notescmd7" id="PRGCMGTOT351"
								value="#{cmnNotesMsg['label.commonNotes.note']}" />
							<h:panelGroup id="notespanel3">

								<t:commandLink style="text-decoration: none;" id="notescmd7"
								rendered="#{commonEntityDataBean.imageRender != 'notescmd7'}"
									actionListener="#{detailedNotesControllerBean.sort}"
									onmousedown="javascript:flagWarn=false;">
									<m:div id="sortascid3" title="for ascending"
										styleClass="sort_asc" />
									<f:attribute name="columnName"
										value="Note" />
									<f:attribute name="sortOrder" value="asc" />
								</t:commandLink>

								<t:commandLink style="text-decoration: none;" id="notescmd8"
								rendered="#{commonEntityDataBean.imageRender != 'notescmd8'}"
									actionListener="#{detailedNotesControllerBean.sort}"
									onmousedown="javascript:flagWarn=false;">
									<m:div id="sortdescid3" title="for descending"
										styleClass="sort_desc" />
									<f:attribute name="columnName"
										value="Note" />
									<f:attribute name="sortOrder" value="desc" />
								</t:commandLink>
							</h:panelGroup>
						</h:panelGrid>
					</f:facet>
					<h:outputText id="PRGCMGTOT352" value="#{note.shortNotes}" />
					<h:inputHidden id="PRGCMGTIH19" value="#{note.noteText}"></h:inputHidden>
				</t:column>

				<t:column id="CMGTTOMCS11" styleClass="otherbg">
					<%-- start of sorting code --%>
					<f:facet name="header">
						<h:panelGrid columns="2" id="incCommonNotes75">
							<h:outputLabel for="notescmd10" id="PRGCMGTOT353"
								value="#{cmnNotesMsg['label.commonNotes.usageTypeCode']}" />
							<h:panelGroup id="notespanel4">

								<t:commandLink style="text-decoration: none;" id="notescmd10"
								rendered="#{commonEntityDataBean.imageRender != 'notescmd10'}"
									actionListener="#{detailedNotesControllerBean.sort}"
									onmousedown="javascript:flagWarn=false;">
									<m:div id="sortascid4" title="for ascending"
										styleClass="sort_asc" />
									<f:attribute name="columnName"
										value="UsageTypeCode" />
									<f:attribute name="sortOrder" value="asc" />
								</t:commandLink>

								<t:commandLink style="text-decoration: none;" id="notescmd11"
								rendered="#{commonEntityDataBean.imageRender != 'notescmd11'}"
									actionListener="#{detailedNotesControllerBean.sort}"
									onmousedown="javascript:flagWarn=false;">
									<m:div id="sortdescid4" title="for descending"
										styleClass="sort_desc" />
									<f:attribute name="columnName"
										value="UsageTypeCode" />
									<f:attribute name="sortOrder" value="desc" />
								</t:commandLink>
							</h:panelGroup>
						</h:panelGrid>
					</f:facet>
					<h:outputText id="PRGCMGTOT354" value="#{note.usageTypeDesc}" />
					<f:param name="indexCode" value="#{rowIndex}"></f:param>
				</t:column>

				<f:facet name="footer">
					<f:verbatim>
						<m:div>
							<h:outputText id="PRGCMGTOT355" value="No Data"
								rendered="#{empty commonEntityDataBean.noteList}"></h:outputText>
						</m:div>
					</f:verbatim>
				</f:facet>

			</t:dataTable>
			<%-- End - Modified the dataTable for defect ESPRD00689748 
			commented for defect ESPRD00844022
			<t:dataScroller id="CMGTTOMDS12" pageCountVar="pageCount"
				pageIndexVar="pageIndex" paginator="true"
				paginatorActiveColumnStyle='font-weight:bold;' paginatorMaxPages="3"
				immediate="false" for="cmnNoteHist" firstRowIndexVar="firstRowIndex"
				lastRowIndexVar="lastRowIndex" rowsCountVar="rowsCount"
				actionListener="#{detailedNotesControllerBean.dataScrollerAction}"
				style="float:right;position:relative;bottom:-6px"
				onclick="javascript:flagWarn=false;">

				<f:facet name="previous">
					<h:outputText id="PRGCMGTOT356" style="text-decoration:underline;"
						value="#{cmnNotesMsg['label.commonNotes.previous']}"
						rendered="#{pageIndex > 1}"></h:outputText>
				</f:facet>
				<f:facet name="next">
					<h:outputText id="PRGCMGTOT357" style="text-decoration:underline;"
						value=">>" rendered="#{pageIndex < pageCount}"></h:outputText>
				</f:facet>

				<h:outputText id="PRGCMGTOT358"
					value="showing #{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
					style="float:left;position:relative;bottom:-6px"
					rendered="#{rowsCount>0}">
				</h:outputText>
			</t:dataScroller>--%>
			<h:panelGroup id="panghearnotecountId11" >
                     <h:outputText id="testnotescountId119" rendered="#{not empty commonEntityDataBean.noteList}"
                           value="#{commonEntityDataBean.startRecord} - #{commonEntityDataBean.endRecord} of #{commonEntityDataBean.count}"
                              style="font-weight:bold;float:left;">
                     </h:outputText>
                </h:panelGroup>
			<f:verbatim>
				<m:br />
				<m:br />
			</f:verbatim>


		</m:div>






	</m:div>

</m:div>

<t:saveState id="CMGTTOMSS91"
	value="#{commonEntityDataBean.detailNewNotesRender}" />
<t:saveState id="CMGTTOMSS92"
	value="#{commonEntityDataBean.detailMainNotesRender}" />
<t:saveState id="CMGTTOMSS93"
	value="#{commonEntityDataBean.detailFilterNotesRender}" />
<t:saveState id="CMGTTOMSS94"
	value="#{commonEntityDataBean.commonEntityVO.detailNoteSetVO.notesList}" />
<t:saveState id="CMGTTOMSS95"
	value="#{commonEntityDataBean.commonEntityVO.detailNoteSetVO.checkAll}" />
<t:saveState id="CMGTTOMSS96"
	value="#{commonEntityDataBean.commonEntityVO.detailNoteSetVO.noteSetSK}" />
<t:saveState id="CMGTTOMSS97"
	value="#{commonEntityDataBean.commonEntityVO.detailCommonNotesVO.checked}" />
<t:saveState id="CMGTTOMSS98"
	value="#{commonEntityDataBean.noteTypeList}" />
