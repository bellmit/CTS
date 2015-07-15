<%--
  Portlet 8.0 Migration Activity: h:commandLink is not supported in portal 8.0, replacing with myfaces t:commandLink tag
  Portlet 8.0 Migration Activity: m:dataScroller is not supported in portal 8.0, replacing with myfaces t:dataScroller tag
--%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://www.acs-inc.com/custom" prefix="m"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>

<portlet:defineObjects />

<script>

  function textNotesCounter(field,maxlimit) { 
	var el = field;	 	
	if (el.value.length > maxlimit) {
	 el.value = el.value.substring(0,maxlimit);
	 }
	 
	 var remChar = maxlimit - el.value.length;	 
	 var ibox = document.getElementById('view<portlet:namespace/>:CMlogCase:commonNotes:charRemaining');	
	 ibox.value = remChar;	
}
  //Below Code Modified for ESPRD00802065 to display pop up message on click of filter : Start
 function doCommonNotesClickAction(link)
		{		
	 var isCommonNotesSaved;
	  if(document.getElementById('view<portlet:namespace/>:CMlogCase:commonNotes:commonNoteSaved') != null)
	  {
	 	isCommonNotesSaved = document.getElementById('view<portlet:namespace/>:CMlogCase:commonNotes:commonNoteSaved').value;
	  }				
				if(isCommonNotesSaved == 'true')
				{		
					var answer = confirm("Unsaved changes will be lost.  Are you sure that you want to filter?");				
					if(answer)
					{
						//isCommonNotesSaved = true;
						flagWarn=false;
						link.fireEvent('onclick');
					}
				}else{
					flagWarn=false;
					link.fireEvent('onclick');
				}
						
		}
//Below Code Modified for ESPRD00802065 to display pop up message on click of filter : End
 
 // Begin - Modified the code for the PrintSelected link for the defect ESPRD00689748
function doPrint()
{
  
   var tags = document.body.getElementsByTagName("div");
		// Begin - Added the code for the defect id ESPRD00725239_16NOV2011
		var entityID = document
			.getElementById('view<portlet:namespace/>:CMlogCase:commonNotes:id11').value;
		var entityName = document
			.getElementById('view<portlet:namespace/>:CMlogCase:commonNotes:id22').value;
		// End - Added the code for the defect id ESPRD00725239_16NOV2011

  		var importStr = "<link rel='stylesheet' type='text/css' href='/wps/themes/html/ACSDefault/style/portal.css'></link>"
					+ "<link rel='stylesheet' type='text/css' href='/wps/themes/html/ACSDefault/style/selfservice.css'></link>"
					+ "<link rel='stylesheet' type='text/css' href='/wps/themes/html/ACSDefault/style/print.css' media='print'></link>"
					+	"<link rel='stylesheet' type='text/css' href='/wps/themes/html/ACSDefault/style/defaultStyles.css'></link>"
				    + "<link rel='stylesheet' type='text/css' href='/wps/themes/html/ACSDefault/style/theme.css'></link> ";
       var printableHTML =   importStr +   "<html><body><center> <table border='0' cellspacing='0' width='100%' class='dataTable'>";
   
		printableHTML = printableHTML
				+ "<tr><TD class='otherbg'>Component: Case Record</TD><TD align='right' class='otherbg'><a style='text-decoration:none' onclick='self.print();' href='#'>Print&nbsp;&nbsp;</a></TD></tr>";
		printableHTML = printableHTML + "<tr><TD class='otherbg'>Entity ID: "
				+ entityID + "</TD><TD class='otherbg'></TD></tr>"; // Modified for the defect id ESPRD00725239_16NOV2011
		printableHTML = printableHTML + "<tr><TD class='otherbg'>Entity Name: "
				+ entityName + "</TD><TD class='otherbg'></TD></tr>"; // Modified for the defect id ESPRD00725239_16NOV2011
		printableHTML = printableHTML
				+ "<tr><TD class='otherbg'>Functional Area: Case</TD><TD class='otherbg'></TD></tr>";

		/* printableHTML = printableHTML + "<tr><TD class='otherbg'>Key:</TD></tr>";*/

		printableHTML = printableHTML + "</table></center>";

		printableHTML = printableHTML
				+ "<center> <table border='0' cellspacing='0' width='100%' class='dataTable'>";
   for (var i = 0; i < tags.length; i++)
   {
       var pHTMLElement = tags[i];       
       if(pHTMLElement.id.indexOf("notesScrollTable")!= -1 ){       
         var nodesize = pHTMLElement.childNodes.length;		 
		  var tableLength = pHTMLElement.childNodes[0].childNodes.length;
		  var tableNode = pHTMLElement.childNodes[0];
		  for(var j = 0; j < tableLength; j++){
		  
		    if(j==2){
		     var rows = tableNode.childNodes[j];
		     var rowsLength= rows.childNodes.length;
     		  for(var k = 0; k < rowsLength; k++){
	 		  		     var row = rows.childNodes[k];
	 		  		     var elements = row.getElementsByTagName("input");     		  		     
	 		  		     var checkBox = elements[0];     		  		    
     		  		     if(checkBox.checked){
	     		  		     var noteTxtHidden = elements[1];
	     		  		     var fullNotes = noteTxtHidden.value.replace(
										/\n/g, '<br />');
	     		  		     printableHTML = printableHTML + "<tr>";     		  		     
              		  		 var columns = row.childNodes;
							 for(var col = 0; col < columns.length; col++){							 
								 if(col==3){
									 printableHTML = printableHTML + "<TD class='otherbg'>" + fullNotes + "</TD>" ;  				      							 
								 }
								 else{
									 printableHTML = printableHTML + "<TD class='otherbg'>" + columns[col].innerHTML + "</TD>" ;  				      							 							 
								 }						 
							 }		
							 printableHTML = printableHTML + "</tr>";  				      
     		  		     }     		  		     
     		   }		     
		    }
		    else
		    {
			    printableHTML = printableHTML + tableNode.childNodes[j].innerHTML;     		  		     
		    }		    
		 }
       }
   }   
   
	printableHTML = printableHTML + "</table></center></body></html>"   
	
	  var disp_setting="toolbar=false,location=no,directories=false,menubar=false,"; 
	      disp_setting+="scrollbars=yes,width=800, height=300, left=100, top=25,resizable=yes"; 	  
   	  var docprint=window.open("","",disp_setting); 
	   docprint.document.open(); 
	   docprint.document.write(printableHTML);          
	   docprint.document.close(); 
	   //docprint.close();
}
//End - Modified the code for the PrintSelected link for the defect ESPRD00689748

 </script>

<f:loadBundle
	basename="com.acs.enterprise.common.program.commonentities.nl.PGM_CE_CommonNotes"
	var="cmnNotesMsg" />

<t:saveState value="#{commonEntityDataBean.newNotesRender}"
	id="commonNotesSaveStat1Id" />
<t:saveState value="#{commonEntityDataBean.mainNotesRender}"
	id="commonNotesSaveStat2Id" />
<t:saveState value="#{commonEntityDataBean.filterNotesRender}"
	id="commonNotesSaveStat3Id" />
<t:saveState
	value="#{commonEntityDataBean.commonEntityVO.noteSetVO.notesList}"
	id="commonNotesSaveStat4Id" />
<t:saveState
	value="#{commonEntityDataBean.commonEntityVO.noteSetVO.checkAll}"
	id="commonNotesSaveStat5Id" />
<t:saveState
	value="#{commonEntityDataBean.commonEntityVO.noteSetVO.noteSetSK}"
	id="commonNotesSaveStat16Id" />
<t:saveState
	value="#{commonEntityDataBean.commonEntityVO.commonNotesVO.checked}"
	id="commonNotesSaveStat7Id" />
<t:saveState value="#{commonEntityDataBean.noteTypeList}"
	id="commonNotesSaveStat8Id" />

<%-- Begin - Added the code for the defect id ESPRD00725239_16NOV2011 --%>
<h:inputHidden id="entityIDandNameForNotes"
	value="#{logCaseControllerBean.entityTypeIdForNotesPrint}"></h:inputHidden>
<h:inputHidden id="id11"
	value="#{logCaseDataBean.entityIdForNote}"></h:inputHidden>
<h:inputHidden id="id22"
	value="#{logCaseDataBean.entityNameForNote}"></h:inputHidden>
	<h:inputHidden id="commonNoteSaved"
					value="#{commonEntityDataBean.commonNoteSaved}"></h:inputHidden>
<%-- End - Added the code for the defect id ESPRD00725239_16NOV2011 --%>

<m:div id="notesman">
	<m:br id="incCommonNotes1" />
	<m:br id="incCommonNotes2" />

	<m:div id="incCommonNotes3" styleClass="portletTitleBar"
		style="height:18px">
		<m:div styleClass="portletTitle" id="incCommonNotes4">
			<h:outputText id="incCommonNotes5" value="Notes"></h:outputText>
		</m:div>
	</m:div>

	<m:div id="NotesId" styleClass="floatContainer">
		<m:div id="incCommonNotes6" styleClass="moreInfoBar">
			<m:table id="incCommonNotes7" styleClass="tableBar" width="100%">
				<m:div id="actionsDiv1" styleClass="actions">
					<m:div id="incCommonNotes8" styleClass="infoTitle"
						style="color:darkred">
						<h:outputText id="incCommonNotes9"
							value="#{cmnNotesMsg['label.commonNotes.requiredField']}" />
					</m:div>
					<m:div styleClass="rightCell" id="incCommonNotes10">
						<t:commandLink id="incCommonNotes11" styleClass="strong"
							action="#{commonNotesControllerBean.saveNotes}"
							onmousedown="javascript:flagWarn=false;javascript:focusThis(this.id);focusPage('caseNotesHeader');"
							disabled="#{logCaseDataBean.disableLogCaseSave}"> 
							<h:outputText id="incCommonNotes12"
								value="#{cmnNotesMsg['label.commonNotes.save']}">
							</h:outputText>
						</t:commandLink>
						<h:outputText id="logCaseNoteOutTx11Id" escape="false"
							value="&nbsp;" />
						<h:outputText id="cmnFilterNotes"
							rendered="#{commonEntityDataBean.filterEnabled && commonEntityDataBean.commonEntityVO.noteSetVO.filterLinkRender}"
							value="|">
						</h:outputText>
						<h:outputText id="logCaseNoteOutTx115" escape="false"
							value="&nbsp;" />
						<t:commandLink id="incCommonNotes15"
							onmousedown="javascript:flagWarn=false;javascript:focusThis(this.id);focusPage('caseNotesHeader');doCommonNotesClickAction(this);"
							rendered="#{commonEntityDataBean.filterEnabled && commonEntityDataBean.commonEntityVO.noteSetVO.filterLinkRender}"
							action="#{commonNotesControllerBean.filterNotes}">
							<h:outputText id="incCommonNotes16"
								value="#{cmnNotesMsg['label.commonNotes.filter']}">
							</h:outputText>
						</t:commandLink>
						<h:outputText id="logCaseNoteOutTx216" escape="false"
							value="&nbsp;" />
						<h:outputText id="cmnNotesbar3" value="|">
						</h:outputText>
						<h:outputText id="logCaseNoteOutTx116" escape="false"
							value="&nbsp;" />
						<%-- <h:outputLink value="javascript:doPrint();"
							onclick="javascript:flagWarn=false;" id="incCommonNotes17">
							<h:outputText id="incCommonNotes18"
								rendered="#{commonEntityDataBean.commonEntityVO.noteSetVO.printLinkRender}"
								value="#{cmnNotesMsg['label.commonNotes.printSelected']}">
							</h:outputText>
						</h:outputLink>--%>
						
						<f:verbatim> <a href="javascript:doPrint();" onclick="javascript:flagWarn=false;" id="incCommonNotes18" ></f:verbatim>
						<h:outputText id="incCommonNotes18"
								rendered="#{commonEntityDataBean.commonEntityVO.noteSetVO.printLinkRender}"
								value="#{cmnNotesMsg['label.commonNotes.printSelected']}">
							</h:outputText>
						<f:verbatim></a></f:verbatim>
						<h:outputText id="logCaseNoteOutTx118" escape="false"
							value="&nbsp;" />
						<h:outputText id="cmnNotesar00" value="|">
						</h:outputText>
						<h:outputText id="logCaseNoteOutTx119" escape="false"
							value="&nbsp;" />

						<t:commandLink id="incCommonNotes13"
							action="#{commonNotesControllerBean.addNewNotes}"
							rendered="#{!commonEntityDataBean.disableaddNewNote}"
							onmousedown="javascript:flagWarn=false;javascript:focusThis(this.id);focusPage('caseNotesHeader');">
							<h:outputText id="incCommonNotes14"
								value="#{cmnNotesMsg['label.commonNotes.addNewNote']}">
							</h:outputText>
						</t:commandLink>
						<h:outputText id="incCommonNote15"
							value="#{cmnNotesMsg['label.commonNotes.addNewNote']}"
							rendered="#{commonEntityDataBean.disableaddNewNote}">
						</h:outputText>

						<h:outputText id="logCaseNoteOutTx120Id" escape="false"
							value="&nbsp;" />
						<h:outputText id="cmnNotesbar4" value="|"></h:outputText>
						<h:outputText id="logCaseNoteOutTx1121Id" escape="false"
							value="&nbsp;" />
						<t:commandLink id="incCommonNotes19"
							onmousedown="javascript:focusThis(this.id);focusPage('caseNotesHeader');"
							action="#{commonNotesControllerBean.cancelNotes}">
							<h:outputText id="incCommonNotes20"
								value="#{cmnNotesMsg['label.commonNotes.cancel']}">
							</h:outputText>
						</t:commandLink>
					</m:div>
				</m:div>
			</m:table>
		</m:div>
		<m:div id="incCommonNotes21">
			<m:br id="incCommonNotes22"></m:br>
		</m:div>
		<m:div rendered="#{commonEntityDataBean.notesSaveMsg}"
			styleClass="msgbox" id="incCommonNotes23">
			<h:outputText id="incCommonNotes24"
				value="#{ent['err.pgm.cm.note.save.at.parent']}"
				rendered="#{commonEntityDataBean.notesSaveMsg}"></h:outputText>
		</m:div>
		<m:div rendered="#{commonEntityDataBean.newNotesRender}"
			id="notes_new">
			<t:htmlTag id="commonNotesHtmlTag1" value="h3">
				<h:outputText id="lblAddNote"
					value="#{cmnNotesMsg['label.commonNotes.addNote']}"></h:outputText>
			</t:htmlTag>
			<m:table width="50%" id="incCommonNotes26">
				<m:tr id="incCommonNotes27">
					<m:td id="incCommonNotes28">
						<m:div styleClass="padb" id="incCommonNotes29">
							<m:span styleClass="required" id="incCommonNotes30">
								<h:outputText id="incCommonNotes31" value="*"></h:outputText>

							</m:span>
							<h:outputLabel id="lblUsageTypeCode" for="usageTypeCode"
								value="#{cmnNotesMsg['label.commonNotes.usageTypeCode']}"></h:outputLabel>
							<m:br id="incCommonNotes32"></m:br>
							<h:selectOneMenu id="usageTypeCode"
								value="#{commonEntityDataBean.commonEntityVO.commonNotesVO.usageTypeCode}">
								<f:selectItem itemValue=" " itemLabel="" id="notesSelItm1Id" />
								<f:selectItems value="#{commonEntityDataBean.noteTypeList}"
									id="notesSelItm2Id" />
							</h:selectOneMenu>
							<m:br id="noteBRID55987"></m:br>
							<h:message for="lblUsageTypeCode" showDetail="true"
								style="color: red" id="logCrnotesID26974" />

						</m:div>
					</m:td>
				</m:tr>
			</m:table>
			<m:div id="incCommonNotes33">
				<m:br id="incCommonNotes34"></m:br>
			</m:div>
			<m:div id="incCommonNotes35">
				<m:span id="incCommonNotes36" styleClass="required">
					<h:outputText id="incCommonNotes37" value="*"></h:outputText>

				</m:span>
				<h:outputLabel id="lblNotes" for="noteText"
					value="#{cmnNotesMsg['label.commonNotes.note']}"></h:outputLabel>
				<m:br id="incCommonNotes377"></m:br>
				<h:inputTextarea id="noteText" rows="7" style="width:99%"
					onkeyup="textNotesCounter(this,4000);"
					value="#{commonEntityDataBean.commonEntityVO.commonNotesVO.noteText}">
				</h:inputTextarea>
				<m:br id="lblNotesLinId245"></m:br>
				<h:message for="lblNotes" showDetail="true" style="color: red"
					id="logCrnotesID30456" />
			</m:div>
			<m:div id="incCommonNotes38">
				<m:br id="incCommonNotes39"></m:br>
			</m:div>

			<m:div id="incCommonNotes40">
				<h:inputText id="charRemaining" size="4" maxlength="4" value="4000"></h:inputText>
				<h:outputLabel id="lblCharRemaining" for="charRemaining"
					value="#{cmnNotesMsg['label.commonNotes.charRemaining']}"></h:outputLabel>
				
				<m:br id="incCommonNotes41"></m:br>
			</m:div>
		</m:div>
		<m:div rendered="#{commonEntityDataBean.filterNotesRender}"
			id="notes_filter">
			<t:htmlTag id="commonNotesHtmlTag2" value="h3">
				<h:outputText id="lblFilter"
					value="#{cmnNotesMsg['label.commonNotes.filter']}"></h:outputText>
			</t:htmlTag>
			<m:table cellspacing="0" width="100%" id="incCommonNotes43">
				<m:tr id="incCommonNotes44">
					<m:td id="incCommonNotes45">

						<h:outputLabel id="lblBeginDate" for="filterBeginDt"
							value="#{cmnNotesMsg['label.commonNotes.beginDate']}"></h:outputLabel>
						<m:br id="incCommonNotes46"></m:br>
						<m:inputCalendar monthYearRowClass="yearMonthHeader"
							weekRowClass="weekHeader" currentDayCellClass="currentDayCell"
							value="#{commonEntityDataBean.commonNoteSearchVO.strBeginDate}"
							renderAsPopup="true" addResources="true"
							renderPopupButtonAsImage="true" popupDateFormat="MM/dd/yyyy"
							readonly="false" id="filterBeginDt">
						</m:inputCalendar>
					</m:td>
					<m:td id="incCommonNotes48">

						<h:outputLabel id="lblEndDate" for="filterEndDt"
							value="#{cmnNotesMsg['label.commonNotes.endDate']}"></h:outputLabel>
						<m:br id="incCommonNotes49"></m:br>
						<m:inputCalendar monthYearRowClass="yearMonthHeader"
							weekRowClass="weekHeader" currentDayCellClass="currentDayCell"
							value="#{commonEntityDataBean.commonNoteSearchVO.strEndDate}"
							renderAsPopup="true" addResources="true"
							renderPopupButtonAsImage="true" popupDateFormat="MM/dd/yyyy"
							readonly="false" id="filterEndDt">
						</m:inputCalendar>

					</m:td>
					<m:td id="incCommonNotes50">
						<h:outputLabel id="lblUserId" for="cmnNoteUserIdTxt"
							value="#{cmnNotesMsg['label.commonNotes.userID']}">
						</h:outputLabel>
						<m:br id="incCommonNotes51"></m:br>
						<h:inputText id="cmnNoteUserIdTxt" size="15"
							value="#{commonEntityDataBean.commonNoteSearchVO.userId}"></h:inputText>
					</m:td>
					<m:td id="incCommonNotes52">
						<h:outputLabel id="lblUsageTypeCode2" for="fltrUsageTypeCode"
							value="#{cmnNotesMsg['label.commonNotes.usageTypeCode']}"></h:outputLabel>
						<m:br id="incCommonNotes53"></m:br>
						<h:selectOneMenu id="fltrUsageTypeCode"
							value="#{commonEntityDataBean.commonNoteSearchVO.usageTypeCode}">
							<f:selectItem itemValue=" " itemLabel="" id="notesSelItm3Id" />
							<f:selectItems value="#{commonEntityDataBean.noteTypeList}"
								id="notesSelItm4Id" />
						</h:selectOneMenu>
					</m:td>
				</m:tr>
				<m:tr id="incCommonNotes54">
					<m:td colspan="4" id="incCommonNotes55">
						<m:div styleClass="buttonRow" id="incCommonNotes56">
							<h:commandButton id="submitBtn" styleClass="formBtn"
								onmousedown="javascript:flagWarn=false;focusPage('caseNotesHeader');"
								value="#{cmnNotesMsg['label.commonNotes.applyFilter']}"
								action="#{commonNotesControllerBean.getCRFilterNotes}"></h:commandButton>
							<h:outputText escape="false" value="&nbsp;" id="incCommonNotes57"></h:outputText>
							<h:commandButton id="resetBtn" styleClass="formBtn"
								action="#{commonNotesControllerBean.reset}"
								onmousedown="javascript:flagWarn=false;focusPage('caseNotesHeader');"
								value="#{cmnNotesMsg['label.commonNotes.reset']}">
							</h:commandButton>
						</m:div>
					</m:td>
				</m:tr>
			</m:table>
			<m:div id="incCommonNotes58">
				<m:br id="incCommonNotes59"></m:br>
			</m:div>
		</m:div>

		<t:htmlTag id="commonNotesHtmlTag3" value="h3">
			<h:outputLabel id="lblcurrentNote" for="currNoteTxt"
				value="#{cmnNotesMsg['label.commonNotes.currentNote']}"></h:outputLabel>
		</t:htmlTag>

		<m:table width="98%" align="center" id="incCommonNotes61">
			<m:tr id="incCommonNotes611">
				<m:td id="incCommonNotes62">
					<m:div id="currentNote">
						<h:inputTextarea id="currNoteTxt" rows="7" cols="110"
							readonly="true"
							style="width=900px; wrapText; background-color:lightyellow; "
							value="#{commonEntityDataBean.commonEntityVO.commonNotesVO.currentNote}">
						</h:inputTextarea>
					</m:div>
					<m:div id="incCommonNotes65">
						<m:br id="incCommonNotes66"></m:br>
					</m:div>
				</m:td>
			</m:tr>
		</m:table>
		<m:div id="incCommonNotes67">
			<m:br id="incCommonNotes68"></m:br>
		</m:div>

		<t:htmlTag id="commonNotesHtmlTag4" value="h3">
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
				rowOnMouseOver="this.origClassName = this.className;this.className='row_mouse row_link';"
				rowOnMouseOut="if (this.origClassName) this.className = this.origClassName;"
				rowOnClick="javascript:flagWarn=false;"
				onmousedown="javascript:flagWarn=false;focusPage('caseNotesHeader');"
				var="note" rowIndexVar="rowIndex">
				<t:column id="incCommonNotes70">
					<f:facet name="header">

						<t:commandLink id="linkOsbe"
							action="#{commonNotesControllerBean.checkBoxCaseChanged}"
							onmousedown="javascript:flagWarn=false;">
							<h:outputLabel id="checkboxLCCMColumnLabel"  for="ckNotSeAll" styleClass="hide" value="#{ent['label.ent.selectAll']}" ></h:outputLabel>
							<t:selectBooleanCheckbox id="ckNotSeAll"
								value="#{commonEntityDataBean.commonEntityVO.noteSetVO.checkAll}"></t:selectBooleanCheckbox>
						</t:commandLink>
					</f:facet>
					<h:outputLabel id="checkboxLCCMColumnLabel1"  for="noteschk1" styleClass="hide" value="#{ent['label.ent.select']}" ></h:outputLabel>
					<t:selectBooleanCheckbox id="noteschk1" value="#{note.checked}"></t:selectBooleanCheckbox>
				</t:column>
				<t:column styleClass="otherbg" id="incCommonNotes71">
					<f:facet name="header">
						<h:panelGrid columns="2" id="incCommonNotes72">
							<h:outputLabel id="usg" for="notescmd1"
								value="#{cmnNotesMsg['label.commonNotes.dateTime']}" />
							<h:panelGroup id="notespanel1">
								<t:div style="width:px;align=left" id="incCommonNotes73">
									<%-- Added the focusPage() in onmousedown for the defect id ESPRD00697332_07DEC2011--%>
									<t:commandLink style="text-decoration: none;" id="notescmd1"
										actionListener="#{commonNotesControllerBean.sort}"
										rendered="#{commonEntityDataBean.imageRender != 'notescmd1'}"
										onmousedown="javascript:flagWarn=false;focusPage('caseNotesHeader');">
										<m:div id="sortdescid1" title="for ascending"
											styleClass="sort_asc"  />
										<f:attribute name="columnName"
											         value="Date \/ Time" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
								</t:div>
								<t:div id="incCommonNotes74" style="width:px;align=left">
									<%-- Added the focusPage() in onmousedown for the defect id ESPRD00697332_07DEC2011--%>
									<t:commandLink style="text-decoration: none;" id="notescmd2"
										actionListener="#{commonNotesControllerBean.sort}"
										rendered="#{commonEntityDataBean.imageRender != 'notescmd2'}"
										onmousedown="javascript:flagWarn=false;focusPage('caseNotesHeader');">
										<m:div id="sortdescid2" title="for descending"
											styleClass="sort_desc"  />
										<f:attribute name="columnName"
											         value="Date \/ Time" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
								</t:div>
							</h:panelGroup>
						</h:panelGrid>
					</f:facet>
					<t:commandLink id="notescmd3"
						action="#{commonNotesControllerBean.displaySelectedNote}"
						rendered="#{commonEntityDataBean.imageRender != 'notescmd3'}"
						onmousedown="javascript:flagWarn=false;">
						<h:outputText value="#{note.strBeginDate}" id="incCommonNotes75" />
						<f:param name="indexCode" value="#{rowIndex}" id="notesParam5Id"></f:param>
					</t:commandLink>
				</t:column>
				<t:column styleClass="otherbg" id="incCommonNotes76">
					<f:facet name="header">
						<h:panelGrid columns="2" id="incCommonNotes77">
							<h:outputLabel id="incCommonNotes778"
								value="#{cmnNotesMsg['label.commonNotes.userID']}"
								for="notescmd4" />
							<h:panelGroup id="notespanel2">
								<t:div style="width:px;align=left" id="incCommonNotes78">
									<%-- Added the focusPage() in onmousedown for the defect id ESPRD00697332_07DEC2011--%>
									<t:commandLink style="text-decoration: none;" id="notescmd4"
										actionListener="#{commonNotesControllerBean.sort}"
										rendered="#{commonEntityDataBean.imageRender != 'notescmd4'}"
										onmousedown="javascript:flagWarn=false;focusPage('caseNotesHeader');">
										<m:div id="sortdescid3" title="for ascending"
											styleClass="sort_asc" />
										<f:attribute name="columnName"
											             value="User ID" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
								</t:div>
								<t:div style="width:px;align=left" id="incCommonNotes79">
									<%-- Added the focusPage() in onmousedown for the defect id ESPRD00697332_07DEC2011--%>
									<t:commandLink style="text-decoration: none;" id="notescmd5"
										actionListener="#{commonNotesControllerBean.sort}"
										rendered="#{commonEntityDataBean.imageRender != 'notescmd5'}"
										onmousedown="javascript:flagWarn=false;focusPage('caseNotesHeader');">
										<m:div id="sortdescid4" title="for descending"
											styleClass="sort_desc"  />
										<f:attribute name="columnName"
											         value="User ID" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
								</t:div>
							</h:panelGroup>
						</h:panelGrid>
					</f:facet>
					<h:outputText id="useridtext1" value="#{note.userId}" />
				</t:column>
				<t:column width="55%" styleClass="otherbg" id="incCommonNotes80">
					<f:facet name="header">
						<h:panelGrid columns="2" id="incCommonNotes81">
							<h:outputLabel id="incCommonNotes82"
								value="#{cmnNotesMsg['label.commonNotes.note']}" for="notescmd7" />
							<h:panelGroup id="notespanel3">
								<t:div style="width:px;align=left" id="incCommonNotes83">
									<%-- Added the focusPage() in onmousedown for the defect id ESPRD00697332_07DEC2011--%>
									<t:commandLink style="text-decoration: none;" id="notescmd7"
										actionListener="#{commonNotesControllerBean.sort}"
										rendered="#{commonEntityDataBean.imageRender != 'notescmd7'}"
										onmousedown="javascript:flagWarn=false;focusPage('caseNotesHeader');">
										<m:div id="sortdescid5" title="for ascending"
											styleClass="sort_asc"  />
										<f:attribute name="columnName"
											         value="Note" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
								</t:div>
								<t:div id="incCommonNotes84" style="width:px;align=left">
									<%-- Added the focusPage() in onmousedown for the defect id ESPRD00697332_07DEC2011--%>
									<t:commandLink style="text-decoration: none;" id="notescmd8"
										actionListener="#{commonNotesControllerBean.sort}"
										rendered="#{commonEntityDataBean.imageRender != 'notescmd8'}"
										onmousedown="javascript:flagWarn=false;focusPage('caseNotesHeader');">
										<m:div id="sortdescid6" title="for descending"
											styleClass="sort_desc"  />
										<f:attribute name="columnName"
											         value="Note" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
								</t:div>
							</h:panelGroup>
						</h:panelGrid>
					</f:facet>
					<h:outputText value="#{note.shortNotes}" id="incCommonNotes885" />
					<h:inputHidden value="#{note.noteText}" id="incCommonNotes86"></h:inputHidden>
				</t:column>
				<t:column styleClass="otherbg" id="incCommonNotes87">
					<f:facet name="header">
						<h:panelGrid columns="2" id="incCommonNotes877">
							<h:outputLabel for="notescmd10" id="incCommonNotes88"
								value="#{cmnNotesMsg['label.commonNotes.usageTypeCode']}" />
							<h:panelGroup id="notespanel4">
								<t:div style="width:px;align=left" id="incCommonNotes89">
									<%-- Added the focusPage() in onmousedown for the defect id ESPRD00697332_07DEC2011--%>
									<t:commandLink style="text-decoration: none;" id="notescmd10"
										actionListener="#{commonNotesControllerBean.sort}"
										rendered="#{commonEntityDataBean.imageRender != 'notescmd10'}"
										onmousedown="javascript:flagWarn=false;focusPage('caseNotesHeader');">
										<m:div id="sortdescid8" title="for ascending"
											styleClass="sort_asc"  />
										<f:attribute name="columnName"
											         value="Usage Type Code" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
								</t:div>
								<t:div style="width:px;align=left" id="incCommonNotes90">
									<%-- Added the focusPage() in onmousedown for the defect id ESPRD00697332_07DEC2011--%>
									<t:commandLink style="text-decoration: none;" id="notescmd11"
										actionListener="#{commonNotesControllerBean.sort}"
										rendered="#{commonEntityDataBean.imageRender != 'notescmd11'}"
										onmousedown="javascript:flagWarn=false;focusPage('caseNotesHeader');">
										<m:div id="sortdescid9" title="for descending"
											styleClass="sort_desc"  />
										<f:attribute name="columnName"
										             value="Usage Type Code" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
								</t:div>
							</h:panelGroup>
						</h:panelGrid>
					</f:facet>
					<h:outputText value="#{note.usageTypeDesc}" id="incCommonNotes91" />
					<f:param name="indexCode" value="#{rowIndex}" id="notesParam4Id"></f:param>
				</t:column>

				<f:facet name="footer">
					<m:div id="incCommonNotes92">
						<h:outputText value="No Data" id="incCommonNotes93"
							rendered="#{empty commonEntityDataBean.noteList}"></h:outputText>
					</m:div>
				</f:facet>
			</t:dataTable>
			<%-- End - Modified the dataTable for defect ESPRD00689748 
			commented for defect ESPRD00844022
			<t:dataScroller id="incCommonNotes933" pageCountVar="pageCount"
				pageIndexVar="pageIndex" onclick="javascript:flagWarn=false;focusPage('caseNotesHeader');"
				paginator="true" paginatorActiveColumnStyle='font-weight:bold;'
				paginatorMaxPages="3" immediate="false" for="cmnNoteHist"
				firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex"
				rowsCountVar="rowsCount"
				actionListener="#{commonNotesControllerBean.dataScrollerAction}"
				style="float:right;position:relative;bottom:-6px">
				<f:facet name="previous">
					<h:outputText id="incCommonNotes94"
						style="text-decoration:underline;" value=" << "
						rendered="#{pageIndex > 1}"></h:outputText>
				</f:facet>
				<f:facet name="next">
					<h:outputText id="incCommonNotes95"
						style="text-decoration:underline;" value=" >> "
						rendered="#{pageIndex < pageCount}"></h:outputText>
				</f:facet>
				<h:outputText id="incCommonNotes96"
					value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
					style="float:left;position:relative;bottom:-6px"
					rendered="#{rowsCount>0}">
				</h:outputText>
			</t:dataScroller>--%>
			<h:panelGroup id="pangId1196325">
                     <h:outputText id="testId1174120" rendered="#{not empty commonEntityDataBean.noteList}"
                           value="#{commonEntityDataBean.startRecord} - #{commonEntityDataBean.endRecord} of #{commonEntityDataBean.count}"
                              style="font-weight:bold;float:left;">
                     </h:outputText>
                </h:panelGroup>
			<m:br id="incCommonNotes98" />
			<m:br id="incCommonNotes99" />
		</m:div>
	</m:div>
</m:div>
