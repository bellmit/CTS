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

<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.ProgramMessages"
	var="ref" />
<script>
	var isCommonNotesSaved;
	function textNotesCounter(field, maxlimit) {
		var el = field;
		if (el.value.length > maxlimit) {
			el.value = el.value.substring(0, maxlimit);
		}
		var remChar = maxlimit - el.value.length;
		var ibox = document
				.getElementById('view<portlet:namespace/>:logCorrespondence:commonNotes:charRemaining');
		ibox.value = remChar;

	}
	//Below Code Modified for ESPRD00802065 to display pop up message on click of filter 
	function doCommonNotesClickAction(link) 
	{
		var isCommonNotesSave;
		if (document.getElementById('view<portlet:namespace/>:logCorrespondence:commonNotes:commonNoteSaved') != null)
		{
			isCommonNotesSave = document.getElementById('view<portlet:namespace/>:logCorrespondence:commonNotes:commonNoteSaved').value;
		}
		if (isCommonNotesSave == 'true') 
		{
			var answer = confirm("Unsaved changes will be lost.  Are you sure that you want to filter?");
			
			if (answer) 
			{
				//isCommonNotesSaved = true;
				link.fireEvent('onclick');
				//Code added below not to display Cancel pop up message
				flagWarn=false;
			}
		}
		else
			{
			link.fireEvent('onclick');
			//Code added below not to display Cancel pop up message
			flagWarn=false;
  			}
	}
  		
	var dataCheck = false;
	function doCommonNotesClickCancelAction(link) {
		if (document
				.getElementById('view<portlet:namespace/>:logCorrespondence:commonNotes:commonNoteSaved') != null) {
			isCommonNotesSaved = document
					.getElementById('view<portlet:namespace/>:logCorrespondence:commonNotes:commonNoteSaved').value;
			if (isCommonNotesSaved != true && dataCheck == true) {
				var answer = confirm("Unsaved changes will be lost.  Are you sure that you want to cancel?");
				if (answer) {
					isCommonNotesSaved = true;
					link.fireEvent('onclick');
				}
			}
		}
	}
	var childWindow=null;
	function doPrint() {

		   // grab all the div elements for parsing
		   var tags = document.body.getElementsByTagName("div");

		   var entityID = document.getElementById('view<portlet:namespace/>:logCorrespondence:commonNotes:id1').value;
		   var entityName = document.getElementById('view<portlet:namespace/>:logCorrespondence:commonNotes:id2').value;
		   
		   var importStr = "<link rel='stylesheet' type='text/css' href='/wps/themes/html/ACSDefault/style/portal.css'></link>"
							+ "<link rel='stylesheet' type='text/css' href='/wps/themes/html/ACSDefault/style/selfservice.css'></link>"
							+ "<link rel='stylesheet' type='text/css' href='/wps/themes/html/ACSDefault/style/print.css' media='print'></link>"
							+	"<link rel='stylesheet' type='text/css' href='/wps/themes/html/ACSDefault/style/defaultStyles.css'></link>"
						    + "<link rel='stylesheet' type='text/css' href='/wps/themes/html/ACSDefault/style/theme.css'></link> ";
						    
		   
		   var printableHTML =   importStr +   "<html><body>";
		   
		   printableHTML =   printableHTML +  "<center> <table border='0' cellspacing='0' width='100%' class='dataTable'>"; 
		     
		   printableHTML = printableHTML + "<tr><TD class='otherbg'>Component: Correspondence Record</TD><TD align='right' class='otherbg'><a style='text-decoration:none' onclick='self.print();' href='#'>Print&nbsp;&nbsp;</a></TD></tr>";
		   printableHTML = printableHTML + "<tr><TD class='otherbg'>Entity ID: "+ entityID +"</TD><TD class='otherbg'></TD></tr>";
		   printableHTML = printableHTML + "<tr><TD class='otherbg'>Entity Name: "+entityName+"</TD><TD class='otherbg'></TD></tr>";
		   printableHTML = printableHTML + "<tr><TD class='otherbg'>Functional Area: Correspondence</TD><TD class='otherbg'></TD></tr>";
		 
		   /* printableHTML = printableHTML + "<tr><TD class='otherbg'>Key:</TD></tr>";*/
		    
		   printableHTML = printableHTML + "</table></center>"
		   
		   printableHTML =   printableHTML +   "<center> <table border='0' cellspacing='0' width='100%' class='dataTable'>";
		   
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
			     		  		     var fullNotes = noteTxtHidden.value.replace(/\n/g, '<br />');
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

		/*	var div =   document.getElementById("printdic");   
			div.innerHTML =printableHTML;
			var txt =   document.getElementById("txt");   
			txt.value =printableHTML;
			*/
			
			  var disp_setting="toolbar=false,location=no,directories=false,menubar=false,"; 
			      disp_setting+="scrollbars=yes,width=800, height=300, left=100, top=25,resizable=yes"; 	  
		   	  var docprint=window.open("","",disp_setting); 
			   docprint.document.open(); 
			   docprint.document.write(printableHTML);          
			  docprint.document.close(); 
			/*docprint.close();*/
}

	function tickChkboxes(){
		if(childWindow!=null)
			for(var i=0;i<childWindow.document.getElementsByTagName('input').length;i++){
				if(childWindow.document.getElementsByTagName('input')[i].type=='checkbox'){
					childWindow.document.getElementsByTagName('input')[i].checked=true;
				}
			}
    }
</script>
<%-- Holds the name of the property file to be reffered --%>
<f:loadBundle
	basename="com.acs.enterprise.common.program.commonentities.nl.PGM_CE_CommonNotes"
	var="cmnNotesMsg" />

<t:saveState id="CMGTTOMSS174"
	value="#{commonEntityDataBean.newNotesRender}" />
<t:saveState id="CMGTTOMSS175"
	value="#{commonEntityDataBean.mainNotesRender}" />
<t:saveState id="CMGTTOMSS176"
	value="#{commonEntityDataBean.filterNotesRender}" />
<t:saveState id="CMGTTOMSS177"
	value="#{commonEntityDataBean.commonEntityVO.noteSetVO.notesList}" />
<t:saveState id="CMGTTOMSS178"
	value="#{commonEntityDataBean.commonEntityVO.noteSetVO.checkAll}" />
<t:saveState id="CMGTTOMSS179"
	value="#{commonEntityDataBean.commonEntityVO.noteSetVO.noteSetSK}" />
<t:saveState id="CMGTTOMSS180"
	value="#{commonEntityDataBean.commonEntityVO.commonNotesVO.checked}" />
<t:saveState id="CMGTTOMSS181"
	value="#{commonEntityDataBean.noteTypeList}" />


<h:inputHidden id="id1"
	value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForVO.entityTypeCodeForNote}"></h:inputHidden>
<h:inputHidden id="id2"
	value="#{CorrespondenceDataBean.correspondenceRecordVO.correspondenceForVO.nameForNote}"></h:inputHidden>
<h:inputHidden id="commonNoteSaved"
					value="#{commonEntityDataBean.commonNoteSaved}"></h:inputHidden>

<m:div id="notesman">
	<m:br id="noteBRID1" />
	<m:br id="noteBRID2" />
	<m:div styleClass="portletTitleBar" style="height:18px"
		id="logCrnotesID1">
		<m:div styleClass="portletTitle" id="logCrnotesID2">
			<h:outputText value="Notes" id="logCrnotesID3"></h:outputText>
		</m:div>
	</m:div>

	<m:div id="logCrnotesID4">
		<m:div id="moreInfoBarid" styleClass="moreInfoBar">
			<m:div styleClass="infoTitle" align="left" id="logCrnotesID5">
				<m:span styleClass="required" id="logCrnotesID6">
					<h:outputText id="logCrnotesID7" value="#{ent['label-sw-reqFld']}"></h:outputText>
				</m:span>
			</m:div>
			<m:div id="logCrnotesID8" align="right">
				<m:div id="logCrnotesID18" styleClass="infoActions">
					<t:commandLink action="#{commonNotesControllerBean.cancelNotes}"
						id="logCrnotesID171" onmousedown="javascript:focusThis(this.id);">
						<h:outputText value="#{cmnNotesMsg['label.commonNotes.cancel']}"
							id="logCrnotesID19">
						</h:outputText>
					</t:commandLink>
				</m:div>
				<m:div id="logCrnotesID10" styleClass="infoActions">
					<h:outputText id="cmnNotesar00" escape="false"
						value="#{ref['label.ref.linkSeperator']}"></h:outputText>
				</m:div>
				<%-- for fixing defect:ESPRD00576206
				<m:div id="logCrnotesID11" styleClass="infoActions">
					<h:commandButton						rendered="#{commonEntityDataBean.commonEntityVO.noteSetVO.addNewLinkRender}"						onmousedown="javascript:flagWarn=false;javascript:focusThis(this.id);"						style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:80px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal;position:relative;top:2px"						disabled="#{!CorrespondenceDataBean.controlRequired}"						value="#{cmnNotesMsg['label.commonNotes.addNewNote']}"						action="#{commonNotesControllerBean.addNewNotes}"						id="logCrnotesID111" />
				</m:div>
				
				<m:div id="logCrnotesID12" styleClass="infoActions">
					<h:outputText id="cmnNotwwesar2" escape="false"						value="#{ref['label.ref.linkSeperator']}">
					</h:outputText>
				</m:div> --%>
				<m:div id="logCrnotesID15" styleClass="infoActions">
					<h:outputLink value="javascript:doPrint();" id="logCrnotesID151"
						onclick="javascript:flagWarn=false;">
						<h:outputText id="logCrnotesID16"
							rendered="#{commonEntityDataBean.commonEntityVO.noteSetVO.printLinkRender}"
							value="#{cmnNotesMsg['label.commonNotes.printSelected']}">
						</h:outputText>
					</h:outputLink>
				</m:div>
				<m:div id="logCrnotesID17" styleClass="infoActions">
					<h:outputText id="cmnNotesbar4" escape="false"
						value="#{ref['label.ref.linkSeperator']}"></h:outputText>
				</m:div>
				<%--Code added below for ESPRD00802065 to display filter pop up message --%>
				<m:div id="logCrnotesID131" styleClass="infoActions">
					<t:commandLink id="logCrnotesID13"
						rendered="#{commonEntityDataBean.filterEnabled && commonEntityDataBean.commonEntityVO.noteSetVO.filterLinkRender}"
						action="#{commonNotesControllerBean.filterNotes}"
						onmousedown="javascript:flagWarn=false;javascript:focusThis(this.id);doCommonNotesClickAction(this);">
						<h:outputText value="#{cmnNotesMsg['label.commonNotes.filter']}"
							id="logCrnotesID141">
						</h:outputText>
					</t:commandLink>
				</m:div>
				<m:div id="logCrnotesID14" styleClass="infoActions">
					<h:outputText id="cmnNotesbar3" escape="false"
						value="#{ref['label.ref.linkSeperator']}"
						rendered="#{commonEntityDataBean.filterEnabled}"></h:outputText>
				</m:div>
				<%-- for fixing defect:ESPRD00576206   --%>
				<m:div id="logCrnotesID11" styleClass="infoActions">
					<h:commandButton
						rendered="#{commonEntityDataBean.commonEntityVO.noteSetVO.addNewLinkRender}"
						onmousedown="javascript:flagWarn=false;javascript:focusThis(this.id);"
						style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:82px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal;position:relative;top:2px"
						disabled="#{!CorrespondenceDataBean.controlRequired}"
						value="#{cmnNotesMsg['label.commonNotes.addNewNote']}"
						action="#{commonNotesControllerBean.addNewNotes}"
						id="logCrnotesID111" />
				</m:div>

				<m:div id="logCrnotesID12" styleClass="infoActions">
					<h:outputText id="cmnNotwwesar2" escape="false"
						value="#{ref['label.ref.linkSeperator']}">
					</h:outputText>
				</m:div>

				<m:div id="logCrnotesID9" styleClass="infoActions">
					<h:commandButton
						onmousedown="javascript:flagWarn=false;javascript:focusThis(this.id);"
						id="logCrnotesSessionID1"
						style="COLOR:#00f;BACKGROUND-COLOR:transparent;CURSOR:hand;FONT-SIZE:12px;WIDTH:35px;BORDER:0;text-align:left;font-family: Verdana; font-size: 11px;clip: rect(auto, auto, 2px, auto);  vertical-align: bottom;word-spacing: normal;position:relative;top:2px"
						disabled="#{!CorrespondenceDataBean.controlRequired}"
						value="#{cmnNotesMsg['label.commonNotes.save']}"
						action="#{commonNotesControllerBean.saveNotes}"
						styleClass="strong" />
				</m:div>
			</m:div>
		</m:div>

		<m:div id="logCrnotesID191">
			<m:br id="noteBRID3"></m:br>
		</m:div>

		<m:div rendered="#{commonEntityDataBean.notesSaveMsg}"
			id="logCrnotesID20" styleClass="msgbox">
			<h:outputText value="#{ent['err.pgm.cm.note.save.at.parent']}"
				id="logCrnotesID21" rendered="#{commonEntityDataBean.notesSaveMsg}"></h:outputText>
		</m:div>

		<m:div rendered="#{commonEntityDataBean.newNotesRender}"
			id="notes_new">
			<t:htmlTag value="h3" id="notes_new_h3">
				<h:outputText id="lblAddNote"
					value="#{cmnNotesMsg['label.commonNotes.addNote']}"></h:outputText>
			</t:htmlTag>

			<m:table width="50%" id="noteTableID1">
				<m:tr id="noteTableID2">
					<m:td id="noteTableID3">
						<m:div styleClass="padb" id="logCrnotesID22">
							<m:span styleClass="required" id="logCrnotesID23">
								<h:outputText value="*" id="logCrnotesID24"></h:outputText>
							</m:span>
							<h:outputLabel id="PRGCMGTOLL324" for="usageTypeCode">
								<h:outputText id="lblUsageTypeCode"
									value="#{cmnNotesMsg['label.commonNotes.usageTypeCode']}"></h:outputText>
							</h:outputLabel>
							<m:br id="noteBRID4"></m:br>
							<h:selectOneMenu id="usageTypeCode"
								value="#{commonEntityDataBean.commonEntityVO.commonNotesVO.usageTypeCode}"
								onchange="flagWarn= true ;dataCheck=true;">
								<f:selectItem id="usageTypeCode1" itemValue=" " itemLabel="" />
								<f:selectItems id="usageTypeCode2"
									value="#{commonEntityDataBean.noteTypeList}" />
							</h:selectOneMenu>
							<m:br id="noteBRID5"></m:br>
							<h:message for="lblUsageTypeCode" showDetail="true"
								style="color: red" id="logCrnotesID26" />
						</m:div>
					</m:td>
				</m:tr>
			</m:table>
			<m:div id="logCrnotesID64">
				<m:br id="noteBRID6"></m:br>
			</m:div>

			<m:div id="logCrnotesID65">
				<m:span styleClass="required" id="logCrnotesID28">
					<h:outputText value="*" id="logCrnotesID29" />
				</m:span>
				<h:outputLabel id="PRGCMGTOLL325" for="noteText">
					<h:outputText id="lblNotes"
						value="#{cmnNotesMsg['label.commonNotes.note']}"></h:outputText>
				</h:outputLabel>
				<m:br id="noteBRID7"></m:br>
				<h:inputTextarea id="noteText" rows="7" cols="110"
					styleClass="wrapText" onkeyup="textNotesCounter(this,4000);"
					value="#{commonEntityDataBean.commonEntityVO.commonNotesVO.noteText}"
					onchange="dataCheck=true;">
				</h:inputTextarea>
				<m:br id="lblNotesLinId"></m:br>
				<h:message for="lblNotes" showDetail="true" style="color: red"
					id="logCrnotesID30" />
			</m:div>

			<m:div id="logCrnotesID66">
				<m:br id="noteBRID8"></m:br>
			</m:div>
			<m:div id="logCrnotesID67">
				<h:inputText id="charRemaining" size="4" maxlength="4" value="4000"></h:inputText>
				<h:outputLabel id="charRemainlabel1"  for="charRemaining" styleClass="hide" value="#{cmnNotesMsg['label.commonNotes.charRemaining']}" ></h:outputLabel>
				<h:outputText id="lblCharRemaining"
					value="#{cmnNotesMsg['label.commonNotes.charRemaining']}"></h:outputText>
				<m:br id="noteBRID9"></m:br>
			</m:div>
		</m:div>

		<m:div rendered="#{commonEntityDataBean.filterNotesRender}"
			id="notes_filter">
			<t:htmlTag value="h3" id="notes_filter_h3">
				<h:outputText id="lblFilter"
					value="#{cmnNotesMsg['label.commonNotes.filters']}"></h:outputText>
			</t:htmlTag>
			<m:section id="PROVIDERMMS20120731164811307">
				<m:table cellspacing="0" width="100%" id="noteTableID4">
					<m:tr id="noteTableID5">
						<m:td id="noteTableID6">
							<h:outputLabel id="PRGCMGTOLL326" for="filterBeginDt">
								<h:outputText id="lblBeginDate"
									value="#{cmnNotesMsg['label.commonNotes.beginDate']}"></h:outputText>
							</h:outputLabel>
							<m:br id="noteBRID10"></m:br>
							<m:inputCalendar monthYearRowClass="yearMonthHeader"
								weekRowClass="weekHeader" currentDayCellClass="currentDayCell"
								value="#{commonEntityDataBean.commonNoteSearchVO.strBeginDate}"
								renderAsPopup="true" addResources="true"
								renderPopupButtonAsImage="true" popupDateFormat="MM/dd/yyyy"
								readonly="false" id="filterBeginDt"
								onmouseover="javascript:flagWarn=false;"
								onmouseout="javascript:flagWarn=false;">
							</m:inputCalendar>
							<m:br />
							<h:message id="PROVIDERM20120731164811308" for="filterBeginDt" showDetail="true"
								style="color: red" />
							<m:br />
						</m:td>
						<m:td id="noteTableID7">
							<h:outputLabel id="PRGCMGTOLL327" for="filterEndDt">
								<h:outputText id="lblEndDate"
									value="#{cmnNotesMsg['label.commonNotes.endDate']}"></h:outputText>
							</h:outputLabel>
							<m:br id="noteBRID11"></m:br>
							<m:inputCalendar monthYearRowClass="yearMonthHeader"
								weekRowClass="weekHeader" currentDayCellClass="currentDayCell"
								value="#{commonEntityDataBean.commonNoteSearchVO.strEndDate}"
								renderAsPopup="true" addResources="true"
								renderPopupButtonAsImage="true" popupDateFormat="MM/dd/yyyy"
								readonly="false" id="filterEndDt"
								onmouseover="javascript:flagWarn=false;"
								onmouseout="javascript:flagWarn=false;">
							</m:inputCalendar>
							<m:br />
							<h:message id="PROVIDERM20120731164811309" for="filterEndDt" showDetail="true" style="color: red" />
							<m:br />
						</m:td>
						<m:td id="noteTableID8">
							<h:outputLabel id="PRGCMGTOLL328" for="cmnNoteUserIdTxt">
								<h:outputText id="lblUserId"
									value="#{cmnNotesMsg['label.commonNotes.userID']}">
								</h:outputText>
							</h:outputLabel>
							<m:br id="noteBRID12"></m:br>
							<h:inputText id="cmnNoteUserIdTxt" size="15"
								value="#{commonEntityDataBean.commonNoteSearchVO.userId}"></h:inputText>
						</m:td>
						<m:td id="noteTableID9">
							<h:outputLabel id="PRGCMGTOLL329" for="fltrUsageTypeCode">
								<h:outputText id="lblUsageTypeCode2"
									value="#{cmnNotesMsg['label.commonNotes.usageTypeCode']}"></h:outputText>
							</h:outputLabel>
							<m:br id="noteBRID13"></m:br>
							<h:selectOneMenu id="fltrUsageTypeCode"
								value="#{commonEntityDataBean.commonNoteSearchVO.usageTypeCode}">
								<f:selectItem itemValue=" " itemLabel="" id="fltrUsageTypeCode1" />
								<f:selectItems value="#{commonEntityDataBean.noteTypeList}"
									id="fltrUsageTypeCode2" />
							</h:selectOneMenu>
						</m:td>
					</m:tr>
					<m:tr id="noteTableID10">
						<m:td colspan="4" id="noteTableID11">
							<m:div styleClass="buttonRow" id="logCrnotesID31">
								<h:commandButton id="submitBtn" styleClass="formBtn"
									onclick="javascript:flagWarn=false;javascript:focusThis(this.id);"
									value="#{cmnNotesMsg['label.commonNotes.applyFilter']}"
									action="#{commonNotesControllerBean.getCRFilterNotes}"></h:commandButton>
								<h:outputText escape="false" id="logCrnotesID32"
									value="#{ref['label.ref.singleSpace']}" />
								<h:commandButton id="resetBtn" styleClass="formBtn"
									onclick="javascript:flagWarn=false;javascript:focusThis(this.id);"
									action="#{commonNotesControllerBean.reset}"
									value="#{cmnNotesMsg['label.commonNotes.reset']}">
								</h:commandButton>
							</m:div>
						</m:td>
					</m:tr>
				</m:table>
			</m:section>
			<m:div id="logCrnotesID33">
				<m:br id="noteBRID14"></m:br>
			</m:div>
		</m:div>

		<t:htmlTag value="h3" id="lblcurrentNote_h3">
			<h:outputLabel id="PRGCMGTOLL330" for="currNoteTxt">
				<h:outputText id="lblcurrentNote"
					value="#{cmnNotesMsg['label.commonNotes.currentNote']}"></h:outputText>
			</h:outputLabel>
		</t:htmlTag>
		<m:table width="98%" align="center" id="noteTableID12">
			<m:tr id="noteTableID13">
				<m:td width="20%" id="noteTableID14">
					<m:div id="currentNote">
					<%--Added wrapText style for the defect:ESPRD00795902--%>
						<h:inputTextarea id="currNoteTxt" rows="7" cols="110"
							readonly="true"
							style="width=900px; wrapText; background-color:lightyellow;"
							value="#{commonEntityDataBean.commonEntityVO.commonNotesVO.currentNote}"></h:inputTextarea>
					</m:div>
					<m:div id="logCrnotesID35">
						<m:br id="noteBRID15"></m:br>
					</m:div>
				</m:td>
			</m:tr>
		</m:table>
		<m:div id="logCrnotesID36">
			<m:br id="noteBRID16"></m:br>
		</m:div>
		<t:htmlTag value="h3" id="lblNotesHistory_h3">
			<%--Note:fixing for DEFECT ESPRD00576308 --%>
			<h:outputLabel id="CMGTOLL4" for="lblNotesHistory">
				<h:outputText id="lblNotesHistory"
					value="#{cmnNotesMsg['label.commonNotes.notesHistory']}"></h:outputText>
			</h:outputLabel>
		</t:htmlTag>

		<%--<m:div id="notesFocusflagID" rendered="#{commonEntityDataBean.noteFlag}">
        <h:inputText id="notesFocusflag" style="display:none"></h:inputText>
        </m:div>--%>
        <%-- Removed first attribute from below data table to fix defect ESPRD00912138 --%> 
        <%-- Added new attribute to highlight the row in data table to fix defect ESPRD00920192 --%>
		<m:div id="notesScrollTable">
			<t:dataTable border="0" id="cmnNoteHist"
				styleClass="dataTable" width="98%" cellspacing="0" align="center"
				value="#{commonEntityDataBean.noteList}" columnClasses="columnClass"
				headerClass="headerClass" footerClass="footerClass"
				rowClasses="row_alt,row" var="note" rowIndexVar="rowIndex"
				rowStyleClass="#{commonEntityDataBean.itemSelectedRowIndeNotes == rowIndex ? 'row_selected' : 'row'}">

				<t:column id="logCrnotesID37">
					<f:facet name="header">
						<t:commandLink id="linkOsbe"
							action="#{commonNotesControllerBean.checkBoxForCRChanged}"
							title="#{cmnNotesMsg['label.selectAll.notes']}">
							<h:outputLabel id="checkboxColumnLabelCR" for="ckNotSeAll" 
							styleClass="hide" value="#{ent['label.ent.selectAll']}" ></h:outputLabel>
							<t:selectBooleanCheckbox id="ckNotSeAll"
								onmousedown="javascript:flagWarn=false;javascript:focusThis(this.id);"
								value="#{commonEntityDataBean.commonEntityVO.noteSetVO.checkAll}"></t:selectBooleanCheckbox>
						</t:commandLink>
					</f:facet>

					<%--<h:commandLink id="notescmd" action="#{commonNotesControllerBean.displaySelectedNote}">
						--%>
						<h:outputLabel id="checkboxColumnLabelCR11" for="noteschk1" 
							styleClass="hide" value="#{ent['label.ent.select']}" ></h:outputLabel>
					<t:selectBooleanCheckbox id="noteschk1" value="#{note.checked}"></t:selectBooleanCheckbox>
					<%--<f:param name="indexCode" value="#{rowIndex}"></f:param>
						<f:param name="indexCodeCHK" value="#{rowIndex}"></f:param>
					</h:commandLink>
				--%>
				</t:column>
				<%--Below Code modified for ESPRD00802081 to resolve sorting and undesired pop up message in data table--%>
				<t:column styleClass="otherbg" id="logCrnotesID38">
					<f:facet name="header">
						<h:panelGrid columns="2" id="logCrnotesID39">
							<h:outputLabel id="usg" for="notespanel1"
								value="#{cmnNotesMsg['label.commonNotes.dateTime']}" />
							<h:panelGroup id="notespanel1">
								<t:div style="width:px;align=left" id="logCrnotesID40">
									<t:commandLink style="text-decoration: none;" id="notescmd1"
										actionListener="#{commonNotesControllerBean.sort}"
										onmousedown="javascript:flagWarn=false;javascript:focusThis(this.id);"
										rendered="#{commonEntityDataBean.imageRender != 'notescmd1'}">
										
										<m:div title="#{ref['label.ref.forAscending']}"
															styleClass="sort_asc" />
										<f:attribute name="columnName"
											value="Date / Time" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
								</t:div>
								<t:div style="width:px;align=left" id="logCrnotesID41">

									<t:commandLink style="text-decoration: none;" id="notescmd2"
										actionListener="#{commonNotesControllerBean.sort}"
										onmousedown="javascript:flagWarn=false;javascript:focusThis(this.id);"
										rendered="#{commonEntityDataBean.imageRender != 'notescmd2'}">
										
										<m:div title="#{ref['label.ref.forDescending']}"
															styleClass="sort_desc" />
										<f:attribute name="columnName"
											value="Date / Time" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
								</t:div>
							</h:panelGroup>
						</h:panelGrid>
					</f:facet>
					<t:commandLink id="notescmd6"
						action="#{commonNotesControllerBean.displaySelectedNoteForCR}"
						onmousedown="javascript:flagWarn=false;javascript:focusThis(this.id);">
						<h:outputText value="#{note.strBeginDate}" id="logCrnotesID42" />
						<f:param id="logCrnotesID37c" name="indexCode" value="#{rowIndex}"></f:param>
					</t:commandLink>

				</t:column>

				<t:column styleClass="otherbg" id="logCrnotesID43">
					<f:facet name="header">
						<h:panelGrid columns="2" id="logCrnotesID44">
							<h:outputLabel for="notespanel2"
								value="#{cmnNotesMsg['label.commonNotes.userID']}"
								id="logCrnotesID45" />
							<h:panelGroup id="notespanel2">
								<t:div style="width:px;align=left" id="logCrnotesID46">
									<t:commandLink style="text-decoration: none;" id="notescmd4"
										actionListener="#{commonNotesControllerBean.sort}" 
										onmousedown="javascript:flagWarn=false;javascript:focusThis(this.id);"
										rendered="#{commonEntityDataBean.imageRender != 'notescmd4'}">
										
										<m:div title="#{ref['label.ref.forAscending']}"
															styleClass="sort_asc" />
										<f:attribute name="columnName"
											value="User ID" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
								</t:div>
								<t:div style="width:px;align=left" id="logCrnotesID47">
									<t:commandLink style="text-decoration: none;" id="notescmd5"
										actionListener="#{commonNotesControllerBean.sort}"
										onmousedown="javascript:flagWarn=false;javascript:focusThis(this.id);"
										rendered="#{commonEntityDataBean.imageRender != 'notescmd5'}">
										
										<m:div title="#{ref['label.ref.forDescending']}"
															styleClass="sort_desc" />
										<f:attribute name="columnName"
											value="User ID" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
								</t:div>
							</h:panelGroup>
						</h:panelGrid>
					</f:facet>
					<%--<h:commandLink id="notescmd6"						action="#{commonNotesControllerBean.displaySelectedNote}" >--%>
					<h:outputText id="logCrnotesID62" value="#{note.userIdName}" />
					<%--<f:param name="indexCode" value="#{rowIndex}" ></f:param>
					</h:commandLink>--%>
				</t:column>

				<t:column width="55%" styleClass="otherbg" id="logCrnotesID49">
					<f:facet name="header">
						<h:panelGrid columns="2" id="logCrnotesID50">
							<h:outputLabel id="notespanel3ID" for="notespanel3"
								value="#{cmnNotesMsg['label.commonNotes.note']}" />
							<h:panelGroup id="notespanel3">
								<t:div style="width:px;align=left" id="logCrnotesID51">
									<t:commandLink style="text-decoration: none;" id="notescmd7"
										actionListener="#{commonNotesControllerBean.sort}"
										onmousedown="javascript:flagWarn=false;javascript:focusThis(this.id);"
										rendered="#{commonEntityDataBean.imageRender != 'notescmd7'}">
										<%-- <h:graphicImage id="sortdescid5" alt="for ascending"
											styleClass="sort_asc" width="8" url="/images/x.gif" />--%>
											
											<m:div title="#{ref['label.ref.forAscending']}"
															styleClass="sort_asc" />
										<f:attribute name="columnName"
											value="Note" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
								</t:div>
								<t:div style="width:px;align=left" id="logCrnotesID52">
									<t:commandLink style="text-decoration: none;" id="notescmd8"
										actionListener="#{commonNotesControllerBean.sort}"
										onmousedown="javascript:flagWarn=false;javascript:focusThis(this.id);"
										rendered="#{commonEntityDataBean.imageRender != 'notescmd8'}">
										<%--<h:graphicImage id="sortdescid6" alt="for descending"
											styleClass="sort_desc" width="8" url="/images/x.gif" />--%>
											
											<m:div title="#{ref['label.ref.forDescending']}"
															styleClass="sort_desc" />
										<f:attribute name="columnName"
											value="Note" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
								</t:div>
							</h:panelGroup>
						</h:panelGrid>
					</f:facet>
					<h:outputText value="#{note.shortNotes}" id="logCrnotesID53" />
					<h:inputHidden id="noteTextID" value="#{note.noteText}"></h:inputHidden>
				</t:column>

				<t:column styleClass="otherbg" id="logCrnotesID54">
					<f:facet name="header">
						<h:panelGrid columns="2" id="logCrnotesID55">
							<h:outputLabel for="notespanel4" id="logCrnotesID56"
								value="#{cmnNotesMsg['label.commonNotes.usageTypeCode']}" />
							<h:panelGroup id="notespanel4">
								<t:div style="width:px;align=left" id="logCrnotesID57">
									<t:commandLink style="text-decoration: none;" id="notescmd10"
										actionListener="#{commonNotesControllerBean.sort}"
										onmousedown="javascript:flagWarn=false;javascript:focusThis(this.id);"
										rendered="#{commonEntityDataBean.imageRender != 'notescmd10'}">
											<%--<h:graphicImage id="sortdescid8" alt="for ascending"
											styleClass="sort_asc" width="8" url="/images/x.gif" />--%>
											
											<m:div title="#{ref['label.ref.forAscending']}"
															styleClass="sort_asc" />
										<f:attribute name="columnName"
											value="Usage Type Code" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
								</t:div>
								<t:div style="width:px;align=left" id="logCrnotesID58">
									<t:commandLink style="text-decoration: none;" id="notescmd11"
										actionListener="#{commonNotesControllerBean.sort}"
										onmousedown="javascript:flagWarn=false;javascript:focusThis(this.id);"
										rendered="#{commonEntityDataBean.imageRender != 'notescmd11'}">
									 <%--<h:graphicImage id="sortdescid9" alt="for descending"
											styleClass="sort_desc" width="8" url="/images/x.gif" />--%>
											
											<m:div title="#{ref['label.ref.forDescending']}"
															styleClass="sort_desc" />
										<f:attribute name="columnName"
											value="Usage Type Code" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
								</t:div>
							</h:panelGroup>
						</h:panelGrid>
					</f:facet>
					<h:outputText value="#{note.usageTypeDesc}" id="logCrnotesID59" />
					<f:param id="indexCodeID" name="indexCode" value="#{rowIndex}"></f:param>
				</t:column>
				
				<f:facet name="footer">
					<m:div id="logCrnotesID60" align="center">
						<h:outputText value="No Data" id="logCrnotesID61"
							rendered="#{empty commonEntityDataBean.noteList}"></h:outputText>
					</m:div>
				</f:facet>
			</t:dataTable>
			<%-- <t:dataScroller id="cmnNoteHistID" pageCountVar="pageCount"
				onclick="flagWarn=false;" pageIndexVar="pageIndex" paginator="true"
				paginatorActiveColumnStyle='font-weight:bold;' paginatorMaxPages="3"
				immediate="false" for="cmnNoteHist" firstRowIndexVar="firstRowIndex"
				lastRowIndexVar="lastRowIndex" rowsCountVar="rowsCount"
				actionListener="#{commonNotesControllerBean.dataScrollerAction}"
				style="float:right;position:relative;bottom:-6px">

				<f:facet name="previousID">
					<h:outputText id="cmnNoteHistID1"
						style="text-decoration:underline;" value=" << "
						rendered="#{pageIndex > 1}"></h:outputText>
				</f:facet>
				<f:facet name="nextID">
					<h:outputText id="cmnNoteHistID2"
						style="text-decoration:underline;" value=" >> "
						rendered="#{pageIndex < pageCount}"></h:outputText>
				</f:facet>

				<h:outputText id="cmnNoteHistID3"
					value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
					style="float:left;position:relative;bottom:-6px"
					rendered="#{rowsCount>0}">
				</h:outputText>
			</t:dataScroller>--%>
			
				<%--Added rendered attribute for the defect:ESPRD00795893--%>
			<h:panelGroup id="pangId11" >
                     <h:outputText id="testId11" rendered="#{commonEntityDataBean.renderedreccordsnum}"
                           value="#{commonEntityDataBean.startRecord} - #{commonEntityDataBean.endRecord} of #{commonEntityDataBean.count}"
                              style="font-weight:bold;float:left;">
                     </h:outputText>
                </h:panelGroup>
			<m:br id="noteBRID17" />
			<m:br id="noteBRID18" />
		</m:div>
	</m:div>
</m:div>
