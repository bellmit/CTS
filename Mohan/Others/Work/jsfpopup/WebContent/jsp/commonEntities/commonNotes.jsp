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
  function textNotesCounter(field,maxlimit) { 
	var el = field;	 	
	if (el.value.length > maxlimit) {
	 el.value = el.value.substring(0,maxlimit);
	 }
	var remChar = maxlimit - el.value.length;	 
	 var ibox = document.getElementById('view<portlet:namespace/>:srEntFrm:globalNotes:charRemaining');	
	 ibox.value = remChar;
}

function doPrint()
{
   // grab all the div elements for parsing
   var tags = document.body.getElementsByTagName("div");
    
   var importStr = "<link rel='stylesheet' type='text/css' href='/wps/themes/html/ACSDefault/style/portal.css'></link>"
					+ "<link rel='stylesheet' type='text/css' href='/wps/themes/html/ACSDefault/style/selfservice.css'></link>"
					+ "<link rel='stylesheet' type='text/css' href='/wps/themes/html/ACSDefault/style/print.css' media='print'></link>"
					+	"<link rel='stylesheet' type='text/css' href='/wps/themes/html/ACSDefault/style/defaultStyles.css'></link>"
				    + "<link rel='stylesheet' type='text/css' href='/wps/themes/html/ACSDefault/style/theme.css'></link> ";
       var printableHTML =   importStr +   "<html><body><center> <table border='0' cellspacing='0' width='100%' class='dataTable'>";
   for (var i = 0; i < tags.length; i++)
   {
       var pHTMLElement = tags[i];       
       if(pHTMLElement.id.indexOf("notesScrollTable")!= -1 ){       
         var nodesize = pHTMLElement.childNodes.length;		 
		  var tableLength = pHTMLElement.childNodes[0].childNodes.length;
		  var tableNode = pHTMLElement.childNodes[0];
		  var rows1 = tableNode.childNodes[2];
		  var rowsLength1 = rows1.childNodes.length;
		  var row1 = rows1.childNodes[0];
		  var elements1 = row1.getElementsByTagName("input");
		  
		 printableHTML = printableHTML + "<tr>";
		  printableHTML = printableHTML + "<TD class='otherbg'>Entity Id: " + elements1[2].value;
		  printableHTML = printableHTML + "</TD><TD align='right' class='otherbg'><a style='text-decoration:none;text-align:right' onclick='self.print();' href='#'>Print&nbsp;&nbsp;</a></tr>"; 
		  printableHTML = printableHTML + "</TD><tr>";
		  printableHTML = printableHTML + "<TD class='otherbg'>Entity Type: " + elements1[4].value;
		  printableHTML = printableHTML + "</TD></tr>";
		  printableHTML = printableHTML + "<tr>";
		  printableHTML = printableHTML + "<TD class='otherbg'>Entity Name: " + elements1[3].value;
		  printableHTML = printableHTML + "</TD></tr></table></center><center> <table border='0' cellspacing='0' width='100%' class='dataTable'>";
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
	     		  		     var fullNotes = noteTxtHidden.value.replace(/\n/g, '<br />');;
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
    var disp_setting="toolbar=false,location=no,directories=false,menubar=false,scrollbars=yes,width=800, height=300, left=100, top=25"; 
   	  var docprint=window.open("","",disp_setting); 
	   docprint.document.open(); 
	   docprint.document.write(printableHTML);          
	   docprint.document.close();
		<%--docprint.close();--%>
}

function pagePrint()
{

   // grab all the div elements for parsing
   var tags = document.body.getElementsByTagName("div");
   
   var importStr = "<link rel='stylesheet' type='text/css' href='/wps/themes/html/ACSDefault/style/portal.css'></link>"
					+ "<link rel='stylesheet' type='text/css' href='/wps/themes/html/ACSDefault/style/selfservice.css'></link>"
					+ "<link rel='stylesheet' type='text/css' href='/wps/themes/html/ACSDefault/style/print.css' media='print'></link>"
					+	"<link rel='stylesheet' type='text/css' href='/wps/themes/html/ACSDefault/style/defaultStyles.css'></link>"
				    + "<link rel='stylesheet' type='text/css' href='/wps/themes/html/ACSDefault/style/theme.css'></link> ";
				    
   
       var printableHTML =   importStr +   "<html><body onLoad='self.print()'>";
       printableHTML = printableHTML + "<h3 align='left'>Current Note</h3>";
   
   for (var i = 0; i < tags.length; i++)
   {
  
       var pHTMLElement = tags[i];       
        
       if(pHTMLElement.id.indexOf("currentNote")!= -1 ){
       		var currNote = pHTMLElement.childNodes[0].innerHTML;
       		// alert("Inside if" + pHTMLElement.childNodes[0].innerHTML);
       		// alert("Inside if" + pHTMLElement.childNodes[0].nodeValue);       		
       		printableHTML = printableHTML + "<fieldset> <p>" + currNote + "</p></fieldset>" ;
       }
       
       if(pHTMLElement.id.indexOf("notesScrollTable")!= -1){
	       printableHTML = printableHTML + "<h3 align='left'>Notes History</h3>" ;
	       printableHTML = printableHTML + "<table border='1' cellspacing='0' width='100%' class='dataTable'>";
       
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
     		  		    
	     		  		     var noteTxtHidden = elements[1];
	     		  		     var fullNotes = noteTxtHidden.value;
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
		    else
		    {
			    printableHTML = printableHTML + tableNode.childNodes[j].innerHTML;     		  		     
		    }		    
		 }
		 printableHTML = printableHTML + "</table>";
       }
   }   
   
	printableHTML = printableHTML + "</body></html>"   

/*	var div =   document.getElementById("printdic");   
	div.innerHTML =printableHTML;
	var txt =   document.getElementById("txt");   
	txt.value =printableHTML;
	*/
	
	  var disp_setting="toolbar=false,location=no,directories=false,menubar=false,"; 
	      disp_setting+="scrollbars=yes,width=800, height=300, left=100, top=25"; 	  
   	  var docprint=window.open("","",disp_setting); 
	   docprint.document.open(); 
	   docprint.document.write(printableHTML);          
	   docprint.document.close(); 
}






</script>
<%-- Holds the name of the property file to be reffered --%>
<f:loadBundle
	basename="com.acs.enterprise.common.program.commonentities.nl.PGM_CE_CommonNotes"
	var="cmnNotesMsg" />

<f:loadBundle
	basename="com.acs.enterprise.common.util.helper.nl.EnterpriseMessages"
	var="ent" />

<m:div rendered="#{commonEntityDataBean.mainNotesRender}" id="notesman">

	<m:br />
	<m:br />

	<m:inputHidden name="notesSecurity"
		value="#{commonNotesControllerBean.securityfield}"></m:inputHidden>

	
		<m:div style="float:right; padding-top:2px; color: white">
		
			<%--<h:outputLink value="#{facesContext.externalContext.requestContextPath}/jsp/searchnotes/searchNotesHelp.jsp" target="PortalHelpWindow">
				<f:verbatim><font color="white" style="font-weight:bold">Help</font></f:verbatim>
			</h:outputLink>--%>
			<%-- 
*  Release # : NH Defect Fix
*  Date :6/23/2011
*  Author : CTS
*  Defect ID # :ESPRD00615836 & ESPRD00671310
*  Purpose : Multiple context sensitive browsers appearing for Search Notes and Notes UI. & Help Topic is not being displyed 
*  Note : Code has been consolidated to resolve Defect No.ESPRD00615836 & ESPRD00671310
*  Last Modification Date : 08/11/2011


			<f:verbatim>
				<a href="#"
					onclick="isNotesDetHelp = true; window.open('<portlet:actionURL><portlet:param name="helpFor" value="notesDetailsHelp"/></portlet:actionURL>','newWindow','toolbars=no,resizable=yes,status=no,menubar=no,scrollbars=yes,width=700,height=500'); return false;"><font
					color="white" style="font-weight: bold">&nbsp;Help</font></a>
			</f:verbatim> --%>

		<%-- Added for ESPRD00629192 --%>
		<%-- Commented for DEFECT ESPRD00789787 to disable the HELP & PRINT links from Notes page. START --%>
		<%--<h:outputLink
				value="#{facesContext.externalContext.requestContextPath}/jsp/searchnotes/notesDetailsHelp.jsp"
				target="MCWebHelp">
				<f:verbatim>
					<font color="white" style="font-weight:bold">&nbsp;Help</font>
				</f:verbatim>
			</h:outputLink>--%>

		<%-- End of modification: Author CTS --%>
		<%--</m:div>

		<m:div style="float:right; padding-top:2px; color: white">
			<h:outputLink id="PRGCMGTOLK18" value="javascript:pagePrint();">
				<f:verbatim>
					<font color="white" style="font-weight: bold">Print&nbsp;</font>
				</f:verbatim>
				<f:verbatim>
					<font color="white" style="font-weight: bold">| 
				</f:verbatim>

			</h:outputLink>--%>
			<%-- Commented for DEFECT ESPRD00789787 to disable the HELP & PRINT links from Notes page. END --%>
			<%-- Modified for DEFECT ESPRD00794002 for proper display of title bar in Firefox --%>
			<m:div styleClass="portletTitleBar" style="height:18px">
		<m:div styleClass="portletTitle">
			<f:verbatim>Notes </f:verbatim>
		</m:div>
</m:div>
		


	

	<m:div styleClass="floatContainer">

		<m:div styleClass="moreInfoBar">
			<m:table id="PROVIDERMMT20120731164811163" styleClass="tableBar" width="100%">
				<m:div id="actionsDiv1" styleClass="actions">
					<m:div styleClass="infoTitle" align="left">
						<m:span styleClass="required">
							<h:outputText id="PRGCMGTOT323"
								value="#{cmnNotesMsg['label.commonNotes.requiredField']}"
								styleClass="colorMaroon" />
						</m:span>
					</m:div>


					<m:div styleClass="rightCell">
						<t:commandLink id="PRGCMGTCL68"
							onmousedown="javascript:flagWarn=false;"
							rendered="#{commonEntityDataBean.newNotesRender}"
							action="#{commonNotesControllerBean.saveNotes}">
							<h:outputText id="PRGCMGTOT324"
								value="#{cmnNotesMsg['label.commonNotes.save']}">
							</h:outputText>
						</t:commandLink>
						<h:outputText id="cmnNotesar00"
							rendered="#{commonEntityDataBean.newNotesRender}" value="|">
						</h:outputText>
						<t:commandLink id="PRGCMGTCL69"
							onmousedown="javascript:flagWarn=false;"
							rendered="#{commonEntityDataBean.commonEntityVO.noteSetVO.addNewLinkRender}"
							action="#{commonNotesControllerBean.addNewNotes}">
							<h:outputText id="PRGCMGTOT325"
								value="#{cmnNotesMsg['label.commonNotes.addNewNote']}">
							</h:outputText>
							<h:outputText id="cmnAddNewNote2" value="|">
							</h:outputText>
						</t:commandLink>
						<t:commandLink id="PRGCMGTCL70"
							rendered="#{commonEntityDataBean.commonEntityVO.noteSetVO.filterLinkRender}"
							action="#{commonNotesControllerBean.filterNotes}"
							onmousedown="javascript:flagWarn=false;"
							style="font-weight:lighter;word-spacing:1px;">
							<h:outputText id="PRGCMGTOT326"
								value="#{cmnNotesMsg['label.commonNotes.filter']}">
							</h:outputText>
						</t:commandLink>

						<h:outputText id="PRGCMGTOT327" escape="false"
							value="#{ref['label.ref.linkSeperator']}"></h:outputText>

						<h:outputLink id="PRGCMGTOLK19" value="javascript:doPrint();"
							onmousedown="javascript:flagWarn=false;"
							style="font-weight:lighter;">
							<h:outputText id="PRGCMGTOT328"
								rendered="#{commonEntityDataBean.commonEntityVO.noteSetVO.printLinkRender}"
								value="#{cmnNotesMsg['label.commonNotes.printSelected']}">

							</h:outputText>
						</h:outputLink>

						<h:outputText id="PRGCMGTOT329" escape="false"
							value="#{ref['label.ref.linkSeperator']}"></h:outputText>

						<t:commandLink id="PRGCMGTCL71"
							onclick="flagWarn=true;" 
							action="#{commonNotesControllerBean.cancelNotes}"
							style="font-weight:lighter;">
							<h:outputText id="PRGCMGTOT330"
								value="#{cmnNotesMsg['label.commonNotes.cancel']}">
							</h:outputText>
						</t:commandLink>
						
					</m:div>
				</m:div>
			</m:table>
		</m:div>
		<m:div>
		</m:div>
			<m:br></m:br>
		</m:div>
		<m:div rendered="#{commonEntityDataBean.notesSaveMsg}"
			styleClass="msgbox">
			<h:outputText id="PRGCMGTOT331"
				value="#{cmnNotesMsg['info.Notes_Save_Succ']}"
				rendered="#{commonEntityDataBean.notesSaveMsg}"></h:outputText>
		</m:div>
		<m:div rendered="#{commonEntityDataBean.newNotesRender}"
			id="notes_new">
			<t:htmlTag value="h3">
				<h:outputText id="lblAddNote"
					value="#{cmnNotesMsg['label.commonNotes.addNote']}"></h:outputText>
			</t:htmlTag>

			<m:table id="PROVIDERMMT20120731164811164" width="50%">
				<m:tr>
					<m:td>
						<m:div styleClass="padb">
							<m:span styleClass="required">
								<f:verbatim>
									<h:outputText id="PRGCMGTOT332"
										value="#{cmnNotesMsg['label.commonNotes.required']}"></h:outputText>
								</f:verbatim>
							</m:span>
							<h:outputLabel id="PRGCMGTOLL166" for="usageNoteTypeCode">
								<h:outputText id="lblUsageTypeCode"
									value="#{cmnNotesMsg['label.commonNotes.usageTypeCode']}"></h:outputText>
							</h:outputLabel>

							<m:br></m:br>
							<h:selectOneMenu id="usageNoteTypeCode"
								onmousedown="javascript:flagWarn=false;"
								value="#{commonEntityDataBean.commonEntityVO.commonNotesVO.usageTypeCode}">
								<f:selectItems value="#{commonEntityDataBean.noteTypeList}" />
							</h:selectOneMenu>
						</m:div>
					</m:td>
				</m:tr>
			</m:table>

			<m:div>
				<m:br></m:br>
			</m:div>

			<m:div>
				<m:span styleClass="required">
					<f:verbatim>
						<h:outputText id="PRGCMGTOT333"
							value="#{cmnNotesMsg['label.commonNotes.required']}"></h:outputText>
					</f:verbatim>
				</m:span>
				<h:outputLabel id="PRGCMGTOLL167" for="noteText">
					<h:outputText id="lblNotes"
						value="#{cmnNotesMsg['label.commonNotes.note']}"></h:outputText>
				</h:outputLabel>
				<m:br></m:br>
				<h:inputTextarea id="noteText" rows="7" styleClass="width"
					onchange="javascript:flagWarn=false;"
					onkeyup="textNotesCounter(this,4000);"
					value="#{commonEntityDataBean.commonEntityVO.commonNotesVO.noteText}">
				</h:inputTextarea>
			</m:div>

			<m:div>
				<m:br></m:br>
			</m:div>

			<m:div>

				<h:inputText id="charRemaining"
					onmousedown="javascript:flagWarn=false;" size="4" maxlength="4"
					value="4000"></h:inputText>
				<h:outputLabel id="PRGCMGTOLL508M" for="charRemaining">
					<h:outputText id="lblCharRemaining"
								value="#{cmnNotesMsg['label.commonNotes.charRemaining']}">
					</h:outputText>
				</h:outputLabel>
				<m:br></m:br>
			</m:div>
			<h:inputHidden id="commonNoteSaved"
				value="#{commonEntityDataBean.commonNoteSaved}"></h:inputHidden>
		</m:div>
		<%--outputLabel code added for defect:ESPRD00877925 to display the Filters Heading --%>
		<m:div rendered="#{commonEntityDataBean.filterNotesRender}"
			id="notes_filter">

			<t:htmlTag value="h3">
			<h:outputLabel id="PRGCMGTOLL17255" for="lblFilter" >
				<h:outputText id="lblFilter"
					value="#{cmnNotesMsg['label.commonNotes.filters']}"></h:outputText>
			</h:outputLabel>		
			</t:htmlTag>
			<m:section id="PROVIDERMMS20120731164811165" styleClass="otherbg">
				<m:table id="PROVIDERMMT20120731164811166" width="95%" align="center">
					<m:tr>
						<m:td>
							<%--<m:span styleClass="required">
						   <f:verbatim>
							<h:outputText id="note1"								value="#{cmnNotesMsg['label.commonNotes.required']}">
							</h:outputText>
							</f:verbatim>
						</m:span>--%>
							<h:outputLabel id="PRGCMGTOLL168" for="filterBeginDt">
								<h:outputText id="lblBeginDate"
									value="#{cmnNotesMsg['label.commonNotes.beginDate']}"></h:outputText>
							</h:outputLabel>
							<m:br></m:br>
							<m:div styleClass="padb">
								<m:inputCalendar monthYearRowClass="yearMonthHeader"
									weekRowClass="weekHeader" currentDayCellClass="currentDayCell"
									value="#{commonEntityDataBean.commonNoteSearchVO.strBeginDate}"
									renderAsPopup="true" addResources="true"
									renderPopupButtonAsImage="true" popupDateFormat="MM/dd/yyyy"
									readonly="false" id="filterBeginDt">
								</m:inputCalendar>
							</m:div>
							<h:message id="PROVIDERM20120731164811167" for="filterBeginDt" showDetail="true"
								styleClass="errorMessage"></h:message>

						</m:td>
						<m:td>
							<%--<m:span styleClass="required">
							<h:outputText id="star1"								value="#{cmnNotesMsg['label.commonNotes.required']}"></h:outputText>
						</m:span>--%>
							<h:outputLabel id="PRGCMGTOLL169" for="filterEndDt">
								<h:outputText id="lblEndDate"
									value="#{cmnNotesMsg['label.commonNotes.endDate']}"></h:outputText>
							</h:outputLabel>
							<m:br></m:br>
							<m:div styleClass="padb">
								<m:inputCalendar monthYearRowClass="yearMonthHeader"
									weekRowClass="weekHeader" currentDayCellClass="currentDayCell"
									value="#{commonEntityDataBean.commonNoteSearchVO.strEndDate}"
									renderAsPopup="true" addResources="true"
									renderPopupButtonAsImage="true" popupDateFormat="MM/dd/yyyy"
									readonly="false" id="filterEndDt">
								</m:inputCalendar>
							</m:div>
							<h:message id="PROVIDERM20120731164811168" for="filterEndDt" showDetail="true"
								styleClass="errorMessage"></h:message>

						</m:td>
						<m:td>

							<h:outputLabel id="PRGCMGTOLL170" for="cmnNoteUserIdTxt">
								<h:outputText id="lblUserId"
									value="#{cmnNotesMsg['label.commonNotes.userID']}">
								</h:outputText>
							</h:outputLabel>
							<m:br></m:br>
							<h:inputText id="cmnNoteUserIdTxt" size="15"
								value="#{commonEntityDataBean.commonNoteSearchVO.userId}"></h:inputText>
						</m:td>

						<m:td>
							<h:outputLabel id="PRGCMGTOLL171" for="fltrUsageTypeCode">
								<h:outputText id="lblUsageTypeCode2"
									value="#{cmnNotesMsg['label.commonNotes.usageTypeCode']}"></h:outputText>
							</h:outputLabel>
							<m:br></m:br>
							<h:selectOneMenu id="fltrUsageTypeCode"
								onmousedown="javascript:flagWarn=false;"
								value="#{commonEntityDataBean.commonNoteSearchVO.usageTypeCode}">
								<f:selectItem itemValue="" itemLabel="" />
								<f:selectItems value="#{commonEntityDataBean.noteTypeList}" />
							</h:selectOneMenu>
						</m:td>
					</m:tr>
					<m:tr>
						<m:td colspan="4">
							<m:div styleClass="buttonRow">
								<h:commandButton id="submitBtn" styleClass="formBtn"
									onmousedown="javascript:flagWarn=false;"
									value="#{cmnNotesMsg['label.commonNotes.applyFilter']}"
									action="#{commonNotesControllerBean.getFilteredNotes}"></h:commandButton>
								<f:verbatim>&nbsp;</f:verbatim>
								<h:commandButton id="resetBtn" styleClass="formBtn"
									onmousedown="javascript:flagWarn=false;"
									action="#{commonNotesControllerBean.reset}"
									value="#{cmnNotesMsg['label.commonNotes.reset']}">
								</h:commandButton>
							</m:div>
						</m:td>
					</m:tr>
				</m:table>
			</m:section>
			<m:div>
				<m:br></m:br>
			</m:div>
		</m:div>
		<%-- Modified for DEFECT ESPRD00794002 for proper allignment. --%>
		<m:div style="text-align:center">
		<t:htmlTag value="h3" >
		
		
		<h:outputLabel id="PRGCMGTOLL172" for="currNoteTxt" >
				<h:outputText id="lblcurrentNote" 
					value="#{cmnNotesMsg['label.commonNotes.currentNote']}"></h:outputText>
			</h:outputLabel>
			
		</t:htmlTag>
		</m:div>
		<m:table id="PROVIDERMMT20120731164811169" width="98%" align="center">
			<m:tr>
				<m:td>
					<m:div rendered="#{commonEntityDataBean.currentNoteRender}"
						id="currentNote">
						<h:inputTextarea id="currNoteTxt" rows="7" cols="110"
							onmousedown="javascript:flagWarn=false;" readonly="true"
							style="width=900px; background-color:lightyellow;white-space: pre;"
							value="#{commonEntityDataBean.commonEntityVO.commonNotesVO.currentNote}"></h:inputTextarea>
					</m:div>
					<m:div>
						<m:br></m:br>
					</m:div>
				</m:td>
			</m:tr>
		</m:table>
		<m:div>
			<m:br></m:br>
		</m:div>
		<%-- Modified for DEFECT ESPRD00794002 for proper allignment. --%>
		<m:div style="text-align:center">
		<t:htmlTag value="h3">
			<h:outputLabel id="PRGCMGTOLL173">
				<h:outputText id="lblNotesHistory" 
					value="#{cmnNotesMsg['label.commonNotes.notesHistory']}"></h:outputText>
			</h:outputLabel>
		</t:htmlTag>
		</m:div>
		
		<%-- Modified for Defect ESPRD00794002 for displaying proper alignment of Notes pages 
		rendered="#{commonEntityDataBean.renderedreccordsnum}" attribute of the below  data table
		had been removed--%>
		<m:div id="notesScrollTable" style="dataTable; width:950px">
			<t:dataTable border="0" id="cmnNoteHist"
				onmousedown="javascript:flagWarn=false;"
				rowOnClick="javascript:flagWarn=false;"
				first="#{commonEntityDataBean.startIndexForCmnNotes}"
				styleClass="dataTable" width="98%" cellspacing="0" align="center"
				value="#{commonEntityDataBean.commonEntityVO.noteSetVO.notesList}"
				columnClasses="columnClass" headerClass="headerClass"
				footerClass="footerClass" rowClasses="row_alt,row" var="note"
				rowIndexVar="rowIndex">
				<t:column id="CMGTTOMCS1">
					<f:facet name="header">
						<t:commandLink id="linkOsbe"
							onmousedown="javascript:flagWarn=false;"
							action="#{commonNotesControllerBean.checkBoxChanged}"
							title="#{cmnNotesMsg['label.selectAll.notes']}">
							<h:outputLabel id="checkboxColumnLabelCR12" for="ckNotSeAll" 
							styleClass="hide" value="#{ent['label.ent.selectAll']}" ></h:outputLabel>
							<t:selectBooleanCheckbox id="ckNotSeAll"
								value="#{commonEntityDataBean.commonEntityVO.noteSetVO.checkAll}">
							</t:selectBooleanCheckbox>
						</t:commandLink>
					</f:facet>
					<h:outputLabel id="checkboxColumnLabelCR13" for="noteschk1" 
							styleClass="hide" value="#{ent['label.ent.select']}" ></h:outputLabel>
					<t:selectBooleanCheckbox id="noteschk1" value="#{note.checked}">
					</t:selectBooleanCheckbox>

				</t:column>

				<t:column id="CMGTTOMCS2" styleClass="otherbg">
					<%-- start of sorting code --%>
					<f:facet name="header">
						<h:panelGrid id="PRGCMGTPGD49" columns="2">
							<h:outputLabel id="PRGCMGTOLL174" for="usg">
								<h:outputText id="usg"
									value="#{cmnNotesMsg['label.commonNotes.dateTime']}"></h:outputText>
							</h:outputLabel>
							<h:panelGroup id="notespanel1">
								<t:div styleClass="widthXLeft">
									<t:commandLink styleClass="textNone" id="notescmd1"
										onmousedown="javascript:flagWarn=false;"
										actionListener="#{commonNotesControllerBean.sort}">
										<h:graphicImage id="sortdescid1" alt="#{ref['label.ref.forAscending']}"
											title="#{ref['label.ref.forAscending']}" styleClass="sort_asc" width="8" url="/images/x.gif" />
										<f:attribute name="columnName"
											value="#{cmnNotesMsg['label.commonNotes.dateTime']}" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
								</t:div>
								<t:div styleClass="widthXLeft">

									<t:commandLink styleClass="textNone" id="notescmd2"
										onmousedown="javascript:flagWarn=false;"
										actionListener="#{commonNotesControllerBean.sort}">
										<h:graphicImage id="sortdescid2" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}"
											styleClass="sort_desc" width="8" url="/images/x.gif" />
										<f:attribute name="columnName"
											value="#{cmnNotesMsg['label.commonNotes.dateTime']}" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
								</t:div>
							</h:panelGroup>
						</h:panelGrid>
					</f:facet>
					<t:commandLink id="notescmd3"
						onmousedown="javascript:flagWarn=false;"
						action="#{commonNotesControllerBean.displaySelectedNote}">
						<h:outputText id="PRGCMGTOT334" value="#{note.strBeginDate}" />
						<f:param name="indexCode" value="#{rowIndex}"></f:param>
					</t:commandLink>
				</t:column>

				<t:column id="CMGTTOMCS3" styleClass="otherbg">
					<%-- start of sorting code --%>
					<f:facet name="header">
						<h:panelGrid id="PRGCMGTPGD50" columns="2">
							<h:outputLabel id="PRGCMGTOLL175" for="useid">
								<h:outputText id="useid"
									value="#{cmnNotesMsg['label.commonNotes.userID']}"></h:outputText>
							</h:outputLabel>
							<h:panelGroup id="notespanel2">

								<t:div styleClass="widthXLeft">

									<t:commandLink styleClass="textNone" id="notescmd4"
										onmousedown="javascript:flagWarn=false;"
										actionListener="#{commonNotesControllerBean.sort}">
										<h:graphicImage id="sortdescid3" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}"
											styleClass="sort_asc" width="8" url="/images/x.gif" />
										<f:attribute name="columnName"
											value="#{cmnNotesMsg['label.commonNotes.userID']}" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
								</t:div>
								<t:div styleClass="widthXLeft">

									<t:commandLink styleClass="textNone" id="notescmd5"
										onmousedown="javascript:flagWarn=false;"
										actionListener="#{commonNotesControllerBean.sort}">
										<h:graphicImage id="sortdescid4" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}"
											styleClass="sort_desc" width="8" url="/images/x.gif" />
										<f:attribute name="columnName"
											value="#{cmnNotesMsg['label.commonNotes.userID']}" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
								</t:div>
							</h:panelGroup>
						</h:panelGrid>
					</f:facet>
					<%--<h:commandLink id="notescmd6"						action="#{commonNotesControllerBean.displaySelectedNote}">--%>
					<h:outputText id="useridtext1" value="#{note.userId}" />
					<f:param name="indexCode" value="#{rowIndex}"></f:param>
					<%--</h:commandLink>--%>
				</t:column>

				<t:column id="CMGTTOMCS4" width="55%" styleClass="otherbg">
					<%-- start of sorting code --%>
					<f:facet name="header">
						<h:panelGrid id="PRGCMGTPGD51" columns="2">
							<h:outputLabel id="PRGCMGTOLL176" for="notetxt">
								<h:outputText id="notetxt"
									value="#{cmnNotesMsg['label.commonNotes.note']}"></h:outputText>
							</h:outputLabel>
							<h:panelGroup id="notespanel3">

								<t:div styleClass="widthXLeft">

									<t:commandLink styleClass="textNone" id="notescmd7"
										onmousedown="javascript:flagWarn=false;"
										actionListener="#{commonNotesControllerBean.sort}">
										<h:graphicImage id="sortdescid5" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}"
											styleClass="sort_asc" width="8" url="/images/x.gif" />
										<f:attribute name="columnName"
											value="#{cmnNotesMsg['label.commonNotes.note']}" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
								</t:div>
								<t:div styleClass="widthXLeft">

									<t:commandLink styleClass="textNone" id="notescmd8"
										onmousedown="javascript:flagWarn=false;"
										actionListener="#{commonNotesControllerBean.sort}">
										<h:graphicImage id="sortdescid6" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}"
											styleClass="sort_desc" width="8" url="/images/x.gif" />
										<f:attribute name="columnName"
											value="#{cmnNotesMsg['label.commonNotes.note']}" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
								</t:div>
							</h:panelGroup>
						</h:panelGrid>
					</f:facet>
					<%--<h:commandLink id="notescmd9"						action="#{commonNotesControllerBean.displaySelectedNote}">--%>
					<h:outputText id="PRGCMGTOT335" value="#{note.shortNotes}" />
					<f:param name="indexCode" value="#{rowIndex}"></f:param>
					<%--</h:commandLink>--%>
					<h:inputHidden id="PRGCMGTIH14" value="#{note.noteText}"></h:inputHidden>
					<h:inputHidden id="PRGCMGTIH15" value="#{note.entityId}"></h:inputHidden>
					<h:inputHidden id="PRGCMGTIH16" value="#{note.commonEntityName}"></h:inputHidden>
					<h:inputHidden id="PRGCMGTIH17"
						value="#{note.commonEntityTypeCode}">
					</h:inputHidden>
				</t:column>

				<t:column id="CMGTTOMCS5" styleClass="otherbg">
					<%-- start of sorting code --%>
					<f:facet name="header">
						<h:panelGrid id="PRGCMGTPGD52" columns="2">
							<h:outputLabel id="PRGCMGTOLL177"
								value="#{cmnNotesMsg['label.commonNotes.component']}" />
							<h:panelGroup id="component4">

								<t:div styleClass="widthXLeft">

									<t:commandLink styleClass="textNone" id="component5"
										onmousedown="javascript:flagWarn=false;"
										actionListener="#{commonNotesControllerBean.sort}">
										<h:graphicImage id="sortcomponent" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}"
											styleClass="sort_asc" width="8" url="/images/x.gif" />
										<f:attribute name="columnName"
											value="#{cmnNotesMsg['label.commonNotes.component']}" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
								</t:div>
								<t:div styleClass="widthXLeft">

									<t:commandLink styleClass="textNone" id="component11"
										onmousedown="javascript:flagWarn=false;"
										actionListener="#{commonNotesControllerBean.sort}">
										<h:graphicImage id="sortcomponentid9" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}"
											styleClass="sort_desc" width="8" url="/images/x.gif" />
										<f:attribute name="columnName"
											value="#{cmnNotesMsg['label.commonNotes.component']}" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
								</t:div>
							</h:panelGroup>
						</h:panelGrid>
					</f:facet>
					<h:outputText id="PRGCMGTOT336" value="#{note.tableName}" />
					<f:param name="indexCode" value="#{rowIndex}"></f:param>
				</t:column>

				<t:column id="CMGTTOMCS6" styleClass="otherbg">
					<%-- start of sorting code --%>
					<f:facet name="header">
						<h:panelGrid id="PRGCMGTPGD53" columns="2">
							<h:outputLabel id="PRGCMGTOLL178"
								value="#{cmnNotesMsg['label.commonNotes.usageTypeCode']}" />
							<h:panelGroup id="notespanel4">

								<t:div styleClass="widthXLeft">

									<t:commandLink styleClass="textNone" id="notescmd10"
										onmousedown="javascript:flagWarn=false;"
										actionListener="#{commonNotesControllerBean.sort}">
										<h:graphicImage id="sortdescid8" alt="#{ref['label.ref.forAscending']}" title="#{ref['label.ref.forAscending']}"
											styleClass="sort_asc" width="8" url="/images/x.gif" />
										<f:attribute name="columnName"
											value="#{cmnNotesMsg['label.commonNotes.usageTypeCode']}" />
										<f:attribute name="sortOrder" value="asc" />
									</t:commandLink>
								</t:div>
								<t:div styleClass="widthXLeft">
									<t:commandLink styleClass="textNone" id="notescmd11"
										onmousedown="javascript:flagWarn=false;"
										actionListener="#{commonNotesControllerBean.sort}">
										<h:graphicImage id="sortdescid9" alt="#{ref['label.ref.forDescending']}" title="#{ref['label.ref.forDescending']}"
											styleClass="sort_desc" width="8" url="/images/x.gif" />
										<f:attribute name="columnName"
											value="#{cmnNotesMsg['label.commonNotes.usageTypeCode']}" />
										<f:attribute name="sortOrder" value="desc" />
									</t:commandLink>
								</t:div>
							</h:panelGroup>
						</h:panelGrid>
					</f:facet>
					<h:outputText id="PRGCMGTOT337" value="#{note.usageTypeDesc}" />
					<f:param name="indexCode" value="#{rowIndex}"></f:param>
				</t:column>

				<f:facet name="footer">
					<f:verbatim>
						<m:div
							rendered="#{empty commonEntityDataBean.commonEntityVO.noteSetVO.notesList}"
							align="center">
							<h:outputText id="PRGCMGTOT338"
								value="#{ent['err-sw-search-no-record-found']}"></h:outputText>
						</m:div>
					</f:verbatim>
				</f:facet>

			</t:dataTable>
			

            <m:div styleClass="searchResults">
			<%-- <t:dataScroller id="CMGTTOMDS11" pageCountVar="pageCount"
				pageIndexVar="pageIndex" onclick="flagWarn=false;" paginator="true"
				paginatorActiveColumnStyle='font-weight:bold;' paginatorMaxPages="3"
				immediate="false" for="cmnNoteHist" firstRowIndexVar="firstRowIndex"
				lastRowIndexVar="lastRowIndex" rowsCountVar="rowsCount"
				actionListener="#{commonNotesControllerBean.dataScrollerAction}"
				styleClass="scrollerRight">

				<f:facet name="previous">
					<h:outputText id="PRGCMGTOT339" 
						style="text-decoration:underline;" value=" << "
						rendered="#{pageIndex > 1}"></h:outputText>
				</f:facet>
				<f:facet name="next">
					<h:outputText id="PRGCMGTOT340" 
						style="text-decoration:underline;" value=" >> "
						rendered="#{pageIndex < pageCount}"></h:outputText>
				</f:facet>

				<h:outputText id="PRGCMGTOT341"
					value="#{firstRowIndex} - #{lastRowIndex} of #{rowsCount}"
					styleClass="scrollerBold" rendered="#{rowsCount>0}">
				</h:outputText>
				</t:dataScroller> Commenter for the defect ESPRD00542028
				--%>
				<%-- Modified for DEFECT ESPRD00794002 to display the proper Row Count --%>
				<h:panelGroup id="PROVIDERPGP20120731164811170" >
                     <h:outputText id="commonnotesindex" rendered="#{commonEntityDataBean.renderedreccordsnum}"
                           value="#{commonEntityDataBean.startRecord} - #{commonEntityDataBean.endRecord} of #{commonEntityDataBean.count}"
                              style="font-weight:bold;float:left;COLOR:#000000;BACKGROUND-COLOR:transparent;" >
                     </h:outputText>
                </h:panelGroup>
			</m:div>
			<f:verbatim>
				<m:br />
				<m:br />
			</f:verbatim>
		</m:div>
	</m:div>
</m:div>

<t:saveState id="CMGTTOMSS81"
	value="#{commonEntityDataBean.newNotesRender}" />
<t:saveState id="CMGTTOMSS82"
	value="#{commonEntityDataBean.mainNotesRender}" />
<t:saveState id="CMGTTOMSS83"
	value="#{commonEntityDataBean.filterNotesRender}" />
<t:saveState id="CMGTTOMSS84"
	value="#{commonEntityDataBean.globalDataRender}" />
<t:saveState id="CMGTTOMSS85"
	value="#{commonEntityDataBean.commonEntityVO.noteSetVO.notesList}" />
<t:saveState id="CMGTTOMSS86"
	value="#{commonEntityDataBean.commonEntityVO.noteSetVO.globalNotesList}" />
<t:saveState id="CMGTTOMSS87"
	value="#{commonEntityDataBean.commonEntityVO.noteSetVO.checkAll}" />
<t:saveState id="CMGTTOMSS88"
	value="#{commonEntityDataBean.commonEntityVO.noteSetVO.noteSetSK}" />
<t:saveState id="CMGTTOMSS89"
	value="#{commonEntityDataBean.commonEntityVO.commonNotesVO.checked}" />
<t:saveState id="CMGTTOMSS90"
	value="#{commonEntityDataBean.noteTypeList}" />

