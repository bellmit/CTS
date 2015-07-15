/*
	This can be used for to insert the global NOTES component into pages
	place in html page where 'path' is defined in page
	<script language="javascript">
		writeNotes(path)
	</script>


*/

var note1 = "This is the most recent note.  The most recent note will always be displayed here in its entirety. All other notes will be displayed in reverse chronological order. Each note will only show partial text in the table.  Once the note is selected the entire note will be displayed. The notes portlet does not have pagination, so all notes are visible in this single summary table. A vertical scroll bar will appear within the table frame if the notes cause the display height to be more than 500px."
//var note2 = "I wrote this note to show how multiple notes work.  This is just filler text.  I wrote this note to show how multiple notes work.  This is just filler text.  I wrote this note to show how multiple notes work.  This is just filler text.  I wrote this note to show how multiple notes work.  This is just filler text.  I wrote this note to show how multiple notes work.  This is just filler text."
//var note3 = "Do you like the notes feature.  This is just filler text.  Do you like the notes feature.  This is just filler text.  Do you like the notes feature.  This is just filler text.  Do you like the notes feature.  This is just filler text.  Do you like the notes feature.  This is just filler text.  Do you like the notes feature.  This is just filler text.  Do you like the notes feature.  This is just filler text."
var note2 = "BCCP patient called because BCCP card does not work.  Researched and patient is no longer eligible.  Sent letter."
var note3 = "Need to request medical records for DDU patient to approve specialized treatment plan."
var note4 = "Send an alert to AAU to mail out case exhibits before scheduled hearing date."

function strLeft(str, n)
  /***
          IN: str - the string we are LEFTing
              n - the number of characters we want to return

          RETVAL: n characters from the left side of the string
  ***/
  {
	//alert(str.length)
	if (n <= 0)     // Invalid bound, return blank string
			document.write("");
	else if (n > String(str).length)   // Invalid bound, return
			document.write(str);                // entire string
	else // Valid bound, return appropriate substring
			document.write(String(str).substring(0,n))
  }

function passNote(note){
	document.getElementById('currentNote').innerHTML = note;
}

function writeNotes(path){	

notes = 

'<div id="notesman" class="hide">' +
	'<div><img src="' + path + 'images/x.gif" border="0" width="1" height="10" alt="" /></div>' +
	'<div class="floatContainer">' +
		'<script>writePortletBegin(\'' + path + '\',\'Notes\',1,1,1,\'<span class="strong" nowrap><a href="javascript:void(0)" onclick="hideMe(\\\'notes_new\\\');">Save</span></a>&nbsp;|&nbsp;<a href="javascript:void(0)" onclick="showMe(\\\'notes_new\\\',\\\'notes_\\\');hideMe(\\\'filter\\\');">Add  Note</span></a>&nbsp;|&nbsp;<span id="filter"><a href="javascript:void(0)" onclick="showMe(\\\'notes_filter\\\',\\\'notes_\\\')">Filter</a>&nbsp;&#124;&nbsp;</span><a href="javascript:void(0)">Print Selected</a>&nbsp;&#124;&nbsp;<a href="javascript:radioToggle(\\\'notesman\\\',0)">Cancel</a>\')</script>' +
		'<div id="notes_new" class="hide">' +
		'<form name="addnote">' +
			'<h3>Add Note</h3>' +
			'<table width="50%">' +
				'<tr>' +
					'<td>' +
						'<div class="padb">' +
							'<label><span class="required">*</span>Usage Type Code</label><br/>' +
							'<select>' +
								'<option></option>' +
								'<option>CR-Corresp</option>' +
								'<option>CS-Case</option>' +
								'<option>E-External</option>' +
								'<option>EF-EFT Failed</option>' +
								'<option>Elig-MbrElig</option>' +
								'<option>I-Internal</option>' +
								'<option>MEnr-MbrEnrl</option>' +
								'<option>SH-SpclHdl</option>' +
							'</select>' +
						'</div>' +
					'</td>' +
				'</tr>' +
			'</table>' +
			'<div><img src="' + path + 'images/x.gif" border="0" width="1" height="10" alt="" /></div>' +
			'<div>' +
				'<label><span class="required">*</span>Note</label><br />' +
				'<textarea style="width:99%" name="boko" id="boko" rows="6"  onKeyUp="textCounter2(\'boko\',\'remLen1\',4000)"></textarea>' +
				'<input class="countText" size="1" type="text" name="remLen1" id"remLen1" value="4000" />Characters Remaining' +																									   
			'</div>' +
			'<div><img src="' + path + 'images/x.gif" border="0" width="1" height="10" alt="" /></div>' +
		'</form>' +
		'</div>' +
		'<div id="notes_filter" class="hide">' +
			'<h3>Filters</h3>' +
			'<script>writePodBegin(\'no\')</script>' +
				'<form id="filterForm" name="filterForm">' +
				'<table cellspacing="0" width="100%">' +
					'<tr>' +
						'<td>' +
							'<label>Begin Date</label><br />' +
							'<input type="text" size="10" name="date1x" />&nbsp;<img src="' + path + 'images/cal.gif" alt="Select Date" align="absmiddle" />' +
						'</td>' +
						'<td>' +
							'<label>End Date</label><br />' +
							'<input type="text" size="10" name="date1x" />&nbsp;<img src="' + path + 'images/cal.gif" alt="Select Date" align="absmiddle" />' +
						'</td>' +
						'<td>' +
							'<label>User ID</label><br />' +
							'<input type="text" size="15" id="" />' +
						'</td>' +
						'<td>' +
							'<label>Usage Type Code</label><br />' +
							'<select>' +
								'<option></option>' +
								'<option>CR-Corresp</option>' +
								'<option>CS-Case</option>' +
								'<option>E-External</option>' +
								'<option>I-Internal</option>' +
								'<option>Elig-MbrElig</option>' +
								'<option>MEnr-MbrEnrl</option>' +
								'<option>Elig-MbrElig</option>' +
								'<option>SH-SpclHdl</option>' +
							'</select>' +
						'</td>' +
					'</tr>' +
					'<tr>' +
						'<td colspan="4">' +
							'<div class="buttonRow">' +
								'<input type="button" class="formBtn" value="Apply Filter" />&nbsp;<input type="button" class="formBtn" value="Reset" onclick="resetForm(\'filterForm\')" />' +
							'</div>' +
						'</td>' +
					'</tr>' +
				'</table>' +
				'</form>' +
			'<script>writePodEnd()</script>' +
			'<div><img src="' + path + 'images/x.gif" border="0" width="1" height="10" alt="" /></div>' +
		'</div>' +
		'<h3>Current Note</h3>' +
		'<script>writePodBegin(\'no\')</script>' +
			'<div id="currentNote"></div>' +
		'<script>writePodEnd()</script>' +
		'<div><img src="' + path + 'images/x.gif" border="0" width="1" height="10" alt="" /></div>' +
		'<h3>Notes History</h3>' +
		'<div id="scrollTable">' +
		'<form>' +
			'<div class="floatContent bordert">' +
    		'<div class="floatExcTable" style="height:500px">' +
    		//this statement above will display a vertical scroll bar inside the table if there are more notes than 500 pixels can display - this is okay - do not remove
	        '<table id="notesTable" class="dataTable" cellspacing="0" cellpadding="0" width="99%">' +
				'<tr>' +
					'<th><input type="checkbox" title="Select All Notes" name="ckNoteAll" onClick="this.value=check(this.form.ckNote)" /></th>' +
					'<th>Date / Time&nbsp;<img src="' + path + 'images/sort_asc.gif" alt="Sort Ascending" align="absmiddle" /></th>' +
					'<th>User ID&nbsp;<img src="' + path + 'images/sort_asds.gif" alt="Sort" align="absmiddle" /></th>' +
					'<th>Note&nbsp;<img src="' + path + 'images/sort_asds.gif" alt="Sort" align="absmiddle" /></th>' +
					'<th>Usage Type Code&nbsp;<img src="' + path + 'images/sort_asds.gif" alt="Sort" align="absmiddle" /></th>' +
				'</tr>' +
				'<tr onclick="passNote(note1)">' +
					'<td ><input type="checkbox" name="ckNote" /></td>' +
					'<td><a href="#">01/26/2013 08:34:21</a></td>' +
					'<td>Lawson, David-ACS9014</td>' +
					'<td>This is the most recent note.  The most recent note will always be displayed here in its entirety. All other notes will be displayed in reverse chronological order. Each note will only show partial text in the table.  Once the note is selected the entire note will be displayed. The notes portlet does not have pagination, so all notes are visible in this single summary table. A vertical scroll bar will appear within the table frame if the notes cause the display height to be more than 500px.</td>' +
					'<td>SH-SpclHdl</td>' +
				'</tr>' +
				'<tr onclick="passNote(note1)">' +
					'<td ><input type="checkbox" name="ckNote" /></td>' +
					'<td><a href="#">01/17/2012 09:01:18 AM</a></td>' +
					'<td>Lawson, David-ACS9014</td>' +
					'<td>Mr. Clarke has been repeatedly verbally abusive.  Please refer all calls from this Provider to the Supervisor.</td>' +
					'<td>SH-SpclHdl</td>' +
				'</tr>' +
				'<tr onclick="passNote(note1)">' +
					'<td ><input type="checkbox" name="ckNote" /></td>' +
					'<td><a href="#">01/13/2013 11:26:07 AM</a></td>' +
					'<td>Lawson, David-ACS9014</td>' +
					'<td>Mr. Clarke is hearing impaired. Speak slowly and enunciate clearly.</td>' +
					'<td>SH-SpclHdl</td>' +
				'</tr>' +
				'<tr onclick="passNote(note1)">' +
					'<td ><input type="checkbox" name="ckNote" /></td>' +
					'<td><a href="#">01/10/2013 11:26:07 AM</a></td>' +
					'<td>Lawson, David-ACS9014</td>' +
					'<td>Mr. Clarke screamed and cursed at me before abruptly hanging up the phone.</td>' +
					'<td>SH-SpclHdl</td>' +
				'</tr>' +
				'<tr onclick="passNote(note1)">' +
					'<td ><input type="checkbox" name="ckNote" /></td>' +
					'<td><a href="#">08/18/2011 06:25:15 PM</a></td>' +
					'<td>Lawson, David-ACS9014</td>' +
					'<td><script>strLeft(note1,215)</script></td>' +
					'<td>CA-Case</td>' +
				'</tr>' +
				'<tr onclick="passNote(note2)">' +
					'<td ><input type="checkbox" name="ckNote" /></td>' +
					'<td><a href="#">05/13/2011 08:14:52 AM</a></td>' +
					'<td>Doyle, Robert-ACS0007</td>' +
					'<td><script>strLeft(note2,215)</script></td>' +
					'<td>D1-DDU</td>' +
				'</tr>' +
				'<tr onclick="passNote(note3)">' +
					'<td ><input type="checkbox" name="ckNote" /></td>' +
					'<td><a href="#">01/24/2011 03:25:15 PM</a></td>' +
					'<td>Williams, Catherine-ACS0089</td>' +
					'<td><script>strLeft(note3,215)</script></td>' +
					'<td>EC-MbrCase</td>' +
				'</tr>' +
				'<tr onclick="passNote(note4)">' +
					'<td ><input type="checkbox" name="ckNote" /></td>' +
					'<td><a href="#">08/18/2010 06:25:15 PM</a></td>' +
					'<td>Davidson, Caroline-ACS0003</td>' +
					'<td><script>strLeft(note4,215)</script></td>' +
					'<td>P-PrvdEnroll</td>' +
				'</tr>' +
				'<tr onclick="passNote(note2)">' +
					'<td ><input type="checkbox" name="ckNote" /></td>' +
					'<td><a href="#">05/13/2010 08:14:52 AM</a></td>' +
					'<td>Doyle, Rob-ACS0007</td>' +
					'<td><script>strLeft(note2,215)</script></td>' +
					'<td>EC-MbrCase</td>' +
				'</tr>' +
				'<tr onclick="passNote(note3)">' +
					'<td ><input type="checkbox" name="ckNote" /></td>' +
					'<td><a href="#">01/24/2010 03:25:15 PM</a></td>' +
					'<td>Williams, Catherine-ACS0089</td>' +
					'<td><script>strLeft(note3,215)</script></td>' +
					'<td>EC-MbrCase</td>' +
				'</tr>' +
				'<tr onclick="passNote(note1)">' +
					'<td ><input type="checkbox" name="ckNote" /></td>' +
					'<td><a href="#">08/18/2009 06:25:15 AM</a></td>' +
					'<td>Lawson, David-ACS9014</td>' +
					'<td><script>strLeft(note1,215)</script></td>' +
					'<td>CA-Case</td>' +
				'</tr>' +
				'<tr onclick="passNote(note2)">' +
					'<td ><input type="checkbox" name="ckNote" /></td>' +
					'<td><a href="#">05/13/2009 08:14:52 AM</a></td>' +
					'<td>Doyle, Robert-ACS0007</td>' +
					'<td><script>strLeft(note2,215)</script></td>' +
					'<td>D1-DDU</td>' +
				'</tr>' +
				'<tr onclick="passNote(note3)">' +
					'<td ><input type="checkbox" name="ckNote" /></td>' +
					'<td><a href="#">01/24/2009 03:25:15 PM</a></td>' +
					'<td>Williams, Catherine-ACS0089</td>' +
					'<td><script>strLeft(note3,215)</script></td>' +
					'<td>EC-MbrCase</td>' +
				'</tr>' +
				'<tr onclick="passNote(note4)">' +
					'<td ><input type="checkbox" name="ckNote" /></td>' +
					'<td><a href="#">08/18/2008 06:25:15 AM</a></td>' +
					'<td>Davidson, Caroline-ACS0003</td>' +
					'<td><script>strLeft(note4,215)</script></td>' +
					'<td>P-PrvdEnroll</td>' +
				'</tr>' +
				'<tr onclick="passNote(note2)">' +
					'<td ><input type="checkbox" name="ckNote" /></td>' +
					'<td><a href="#">05/13/2008 08:14:52 AM</a></td>' +
					'<td>Doyle, Rob-ACS0007</td>' +
					'<td><script>strLeft(note2,215)</script></td>' +
					'<td>EC-MbrCase</td>' +
				'</tr>' +
				'<tr onclick="passNote(note3)">' +
					'<td ><input type="checkbox" name="ckNote" /></td>' +
					'<td><a href="#">01/24/2008 03:25:15 PM</a></td>' +
					'<td>Williams, Catherine-ACS0089</td>' +
					'<td><script>strLeft(note3,215)</script></td>' +
					'<td>EC-MbrCase</td>' +
				'</tr>' +
				'<tr onclick="passNote(note1)">' +
					'<td ><input type="checkbox" name="ckNote" /></td>' +
					'<td><a href="#">08/18/2007 06:25:15 AM</a></td>' +
					'<td>Lawson, David-ACS9014</td>' +
					'<td><script>strLeft(note1,215)</script></td>' +
					'<td>CA-Case</td>' +
				'</tr>' +
				'<tr onclick="passNote(note2)">' +
					'<td ><input type="checkbox" name="ckNote" /></td>' +
					'<td><a href="#">05/13/2007 08:14:52 PM</a></td>' +
					'<td>Doyle, Robert-ACS0007</td>' +
					'<td><script>strLeft(note2,215)</script></td>' +
					'<td>D1-DDU</td>' +
				'</tr>' +
				'<tr onclick="passNote(note3)">' +
					'<td ><input type="checkbox" name="ckNote" /></td>' +
					'<td><a href="#">01/24/2007 03:25:15 PM</a></td>' +
					'<td>Williams, Catherine-ACS0089</td>' +
					'<td><script>strLeft(note3,215)</script></td>' +
					'<td>EC-MbrCase</td>' +
				'</tr>' +
				'<tr onclick="passNote(note4)">' +
					'<td ><input type="checkbox" name="ckNote" /></td>' +
					'<td><a href="#">08/18/2012 06:25:15 AM</a></td>' +
					'<td>Davidson, Caroline-ACS0003</td>' +
					'<td><script>strLeft(note4,215)</script></td>' +
					'<td>P-PrvdEnroll</td>' +
				'</tr>' +
				'<tr onclick="passNote(note2)">' +
					'<td ><input type="checkbox" name="ckNote" /></td>' +
					'<td><a href="#">05/13/2012 08:14:52 AM</a></td>' +
					'<td>Doyle, Rob-ACS0007</td>' +
					'<td><script>strLeft(note2,215)</script></td>' +
					'<td>EC-MbrCase</td>' +
				'</tr>' +
				'<tr onclick="passNote(note3)">' +
					'<td ><input type="checkbox" name="ckNote" /></td>' +
					'<td><a href="#">01/24/2012 09:25:15 AM</a></td>' +
					'<td>Williams, Catherine-ACS0089</td>' +
					'<td><script>strLeft(note3,215)</script></td>' +
					'<td>EC-MbrCase</td>' +
				'</tr>' +				
				'</table>' +
			'</div>' +
			'</div>' +
			'<div class="strong">1-18 of 18</div>' +
			//this portlet has no pagination and must show all notes on one view in order to support printing. This is not a defect although
			//it does violate the standard pagination of 10 rows per table view
			'<div class="clear"></div>' +
		'</form>' +
		'</div>' +
		'<script>writePortletEnd();</script>' +
	'</div>' +
'</div>';


document.write(notes)

tigra_tables('notesTable', 1, 0, 'row_alt', 'row', 'row_mouse', 'row_selected');
document.getElementById('currentNote').innerHTML = note1

}


function writeReadOnlyNotes(path){	

notes = 

'<div id="notesman" class="hide">' +
	'<div><img src="' + path + 'images/x.gif" border="0" width="1" height="10" alt="" /></div>' +
	'<div class="floatContainer">' +
		'<script>writePortletBegin(\'' + path + '\',\'Notes\',1,1,1,\'<a href="javascript:void(0)" onclick="showMe(\\\'notes_filter\\\',\\\'notes_\\\')">Filter</a>&nbsp;&#124;&nbsp;<a href="javascript:void(0)">Print Selected</a>&nbsp;&#124;&nbsp;<a href="javascript:radioToggle(\\\'notesman\\\',0)">Cancel</a>\')</script>' +
		'<div id="notes_new" class="hide">' +
			'<h3>Add Note</h3>' +
			'<table width="50%">' +
				'<tr>' +
					'<td>' +
						'<div class="padb">' +
							'<label><span class="required">*</span>Usage Type Code</label><br/>' +
							'<select>' +
								'<option></option>' +
								'<option></option>' +
								'<option>CR</option>' +
								'<option>Case Record</option>' +
								'<option>E-External</option>' +
								'<option>EF-EFT Failed</option>' +
								'<option>I-Internal</option>' +
								'<option>Member Eligibility</option>' +
								'<option>Member Enrollment</option>' +
								'<option>SH-SpclHdl</option>' +
							'</select>' +
						'</div>' +
					'</td>' +
				'</tr>' +
			'</table>' +
			'<div><img src="' + path + 'images/x.gif" border="0" width="1" height="10" alt="" /></div>' +
			'<div>' +
				'<label><span class="required">*</span>Note</label><br />' +
				'<textarea rows="7" style="width:99%"></textarea>' +
			'</div>' +
			'<div><img src="' + path + 'images/x.gif" border="0" width="1" height="10" alt="" /></div>' +
			'<div class="buttonRow">' +
				'<input type="button" class="formBtn" value="Add" onclick="hideMe(\'notes_new\')" />' +
			'</div>' +
			'<div><img src="' + path + 'images/x.gif" border="0" width="1" height="10" alt="" /></div>' +
		'</div>' +
		'<div id="notes_filter" class="hide">' +
			'<h3>Filters</h3>' +
			'<script>writePodBegin(\'no\')</script>' +
				'<form id="filterForm" name="filterForm">' +
				'<table cellspacing="0" width="100%">' +
					'<tr>' +
						'<td>' +
							'<label><span class="required">*</span>Begin Date</label><br />' +
							'<input type="text" size="10" name="date1x" />&nbsp;<img src="' + path + 'images/cal.gif" alt="Select Date" align="absmiddle" />' +
						'</td>' +
						'<td>' +
							'<label><span class="required">*</span>End Date</label><br />' +
							'<input type="text" size="10" name="date1x" />&nbsp;<img src="' + path + 'images/cal.gif" alt="Select Date" align="absmiddle" />' +
						'</td>' +
						'<td>' +
							'<label>User ID</label><br />' +
							'<input type="text" size="15" id="" />' +
						'</td>' +
						'<td>' +
							'<label>Usage Type Code</label><br />' +
							'<select>' +
								'<option></option>' +
								'<option></option>' +
								'<option>CR</option>' +
								'<option>Case Record</option>' +
								'<option>E-External</option>' +
								'<option>EF-EFT Failed</option>' +
								'<option>I-Internal</option>' +
								'<option>Member Eligibility</option>' +
								'<option>Member Enrollment</option>' +
								'<option>SH-SpclHdl</option>' +
							'</select>' +
						'</td>' +
					'</tr>' +
					'<tr>' +
						'<td colspan="4">' +
							'<div class="buttonRow">' +
								'<input type="button" class="formBtn" value="Apply Filter" />&nbsp;<input type="button" class="formBtn" value="Reset" onclick="resetForm(\'filterForm\',\'notes_filter\')" />' +
							'</div>' +
						'</td>' +
					'</tr>' +
				'</table>' +
				'</form>' +
			'<script>writePodEnd()</script>' +
			'<div><img src="' + path + 'images/x.gif" border="0" width="1" height="10" alt="" /></div>' +
		'</div>' +
		'<h3>Current Note</h3>' +
		'<script>writePodBegin(\'no\')</script>' +
			'<div id="currentNote"></div>' +
		'<script>writePodEnd()</script>' +
		'<div><img src="' + path + 'images/x.gif" border="0" width="1" height="10" alt="" /></div>' +
		'<h3>Notes History</h3>' +
		'<div id="scrollTable">' +
		'<form>' +
			'<table id="notesTable" cellspacing="0" width="100%" class="dataTable">' +
				'<tr>' +
					'<th><input type="checkbox" title="Select All Notes" name="ckNoteAll" onClick="this.value=check(this.form.ckNote)" /></th>' +
					'<th nowrap="nowrap">Date / Time&nbsp;<img src="' + path + 'images/sort_desc.gif" alt="Sort Descending" align="absmiddle" /></th>' +
					'<th nowrap="nowrap">User ID&nbsp;<img src="' + path + 'images/sort_asds.gif" alt="Sort" align="absmiddle" /></th>' +
					'<th>Note&nbsp;<img src="' + path + 'images/sort_asds.gif" alt="Sort" align="absmiddle" /></th>' +
					'<th nowrap="nowrap">Usage Type Code&nbsp;<img src="' + path + 'images/sort_asds.gif" alt="Sort" align="absmiddle" /></th>' +
				'</tr>' +
				'<tr onclick="passNote(note1)">' +
					'<td ><input type="checkbox" name="ckNote" /></td>' +
					'<td><a href="#">08/18/2010 06:25:15</a></td>' +
					'<td>rkowalske</td>' +
					'<td><script>strLeft(note1,215)</script></td>' +
					'<td>Internal</td>' +
				'</tr>' +
				'<tr onclick="passNote(note2)">' +
					'<td ><input type="checkbox" name="ckNote" /></td>' +
					'<td><a href="#">05/13/2010 08:14:52</a></td>' +
					'<td>rbordeaux</td>' +
					'<td><script>strLeft(note2,215)</script></td>' +
					'<td>Internal</td>' +
				'</tr>' +
				'<tr onclick="passNote(note3)">' +
					'<td ><input type="checkbox" name="ckNote" /></td>' +
					'<td><a href="#">01/24/2010 15:25:15</a></td>' +
					'<td>kschwartz</td>' +
					'<td><script>strLeft(note3,215)</script></td>' +
					'<td>Member</td>' +
				'</tr>' +
				'<tr onclick="passNote(note1)">' +
					'<td ><input type="checkbox" name="ckNote" /></td>' +
					'<td><a href="#">08/18/2010 06:25:15</a></td>' +
					'<td>rkowalske</td>' +
					'<td><script>strLeft(note1,215)</script></td>' +
					'<td>Provider</td>' +
				'</tr>' +
				'<tr onclick="passNote(note2)">' +
					'<td ><input type="checkbox" name="ckNote" /></td>' +
					'<td><a href="#">05/13/2010 08:14:52</a></td>' +
					'<td>rbordeaux</td>' +
					'<td><script>strLeft(note2,215)</script></td>' +
					'<td>Provider</td>' +
				'</tr>' +
				'<tr onclick="passNote(note3)">' +
					'<td ><input type="checkbox" name="ckNote" /></td>' +
					'<td><a href="#">01/24/2010 15:25:15</a></td>' +
					'<td>kschwartz</td>' +
					'<td><script>strLeft(note3,215)</script></td>' +
					'<td>Internal</td>' +
				'</tr>' +
			'</table>' +
		'</form>' +
		'</div>' +
		'<script>writePortletEnd();</script>' +
	'</div>' +
'</div>';


document.write(notes)

tigra_tables('notesTable', 1, 0, 'row_alt', 'row', 'row_mouse', 'row_selected');
document.getElementById('currentNote').innerHTML = note1

}
