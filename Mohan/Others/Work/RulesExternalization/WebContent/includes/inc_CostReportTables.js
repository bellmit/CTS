/*
	str is a variable to attach a unique id to each table
	each function attaches 'str' to each table section requiring a unique id.
	str is incremented by one at the end of each function
	all functions for writing tables should be added to this file
*/

str = 1;

/*
	The things that need editing per table function are
	 -- the display table columns
	 -- form fields for edit and new sections
	 -- 'add' button text
	 -- edit and new section titles
	 
	 -- determine if you want the fieldset to be written or if it should be hard coded on the page
	    there are examples of both below

*/
//  SCHEDULE E
function writeProviderDebt(path,action,listVar){
	
	var eTable = 'e'+ str + '_table';
	var eForm = 'eForm'+ str;
	var eSave = 'eSave'+ str;
	var delBox = 'del' + str;
	
	var nTable = 'n'+ str + '_table';
	var nForm = 'nForm'+ str;
	var nSave = 'nSave'+ str;
	
	var tabId = 'demTable' + str;
	var toggleBox = 'tBox' + str
	
	
debtTable =
	'<div class="padb">' +
		'Please click \'Add Provider Debt\' to add a row.  To edit row, click the row to display fields.' +
	'</div>' +
	'<table width="100%">' +
		'<tr>' +
			'<td>' +
				'<script language="javascript">' +
					'writeMessage(path, "' + delBox+ '")' +
				'</script>' +
			'</td>' +
		'</tr>' +
	'</table>' +
	'<table width="100%" class="tableBar">' +
		'<tr>' +
			'<td class="tableTitle">Provider Debt Summary</td>' +
			'<td class="tableAction"><input type="button" class="formBtn" value="Add Provider Debt" onClick="radioToggle(\'' + nTable + '\', 1);radioToggle(\'' + eTable + '\', 0)" /></td>' +
		'</tr>' +
	'</table>' +
	'<table width="100%" id="' + tabId + '" cellspacing="0" class="dataTable">' +
		'<tr class="tableHead">' +
			'<th scope="col" title="Debt Obligee">Obligee&nbsp;<img src="' + path + 'images/sort_desc.gif" alt="Sort Descending" align="absmiddle" /></th>' +
			'<th scope="col" title="Purpose of Debt">Purpose&nbsp;<img src="' + path + 'images/sort_asds.gif" alt="Sort" align="absmiddle" /></th>' +
			'<th class="rightCell" title="Monthly Payment">Monthly Paymt&nbsp;<img src="' + path + 'images/sort_asds.gif" alt="Sort" align="absmiddle" /></th>' +
			'<th scope="col" title="Original Date of Debt/Note">Orig Date&nbsp;<img src="' + path + 'images/sort_asds.gif" alt="Sort" align="absmiddle" /></th>' +
			'<th class="rightCell" title="Original Amount of Debt">Orig Amt&nbsp;<img src="' + path + 'images/sort_asds.gif" alt="Sort" align="absmiddle" /></th>' +
			'<th class="rightCell" title="Balance of Debt">Bal&nbsp;<img src="' + path + 'images/sort_asds.gif" alt="Sort" align="absmiddle" /></th>' +
			'<th class="rightCell" title="Interest Rate">Int Rate&nbsp;<img src="' + path + 'images/sort_asds.gif" alt="Sort" align="absmiddle" /></th>' +
			'<th scope="col" title="Debt Maturity Date">Mat Date&nbsp;<img src="' + path + 'images/sort_asds.gif" alt="Sort" align="absmiddle" /></th>' +
			'<th class="rightCell" title="Interest for Period">Int for Per&nbsp;<img src="' + path + 'images/sort_asds.gif" alt="Sort" align="absmiddle" /></th>' +
			'<th scope="col" title="Related">Rel&nbsp;<img src="' + path + 'images/sort_asds.gif" alt="Sort" align="absmiddle" /></th>' +	
		'</tr>' +	
		'<tr onclick="radioToggle(\'' + eTable + '\',1);radioToggle(\'' + nTable + '\',0)">' +
			'<td><a href="javascript:radioToggle(\'' + eTable + '\',1);radioToggle(\'' + nTable + '\',0)">some nursing home</a></td>' +
			'<td>purpose 1</td>' +
			'<td class="rightCell">$100.00</td>' +
			'<td>06/20/2005</td>' +
			'<td class="rightCell">$100.00</td>' +
			'<td class="rightCell">$100.00</td>' +
			'<td class="rightCell">11%</td>' +
			'<td>06/20/2010</td>' +
			'<td class="rightCell">$100.00</td>' +
			'<td class="rightCell">Yes</td>' +
		'</tr>' +
		'<tr class="row rowStrong">' +
			'<td>Totals</td>' +
			'<td>&nbsp;</td>' +
			'<td class="rightCell">$100.00</td>' +
			'<td>&nbsp;</td>' +
			'<td class="rightCell">$100.00</td>' +
			'<td class="rightCell">$100.00</td>' +
			'<td colspan="2">&nbsp;</td>' +
			'<td class="rightCell">$100.00</td>' +
			'<td>&nbsp;</td>' +
		'</tr>' +
	'</table>' +
	'<div class="searchResults">' +
		'<div class="floatl">n - n of n</div>' +
		'<div class="floatr"><a href="javascript:void(0)"><< </a>&nbsp;&nbsp;<a href="javascript:void(0)">1</a>&nbsp;<a href="javascript:void(0)">2</a>&nbsp;<a href="javascript:void(0)">3</a>&nbsp;&nbsp;<a href="javascript:void(0)">>></a></div>' +
		'<div class="clear"></div>' +
	'</div>' +
	'<div><img src="'+ path + 'images/x.gif" border="0" width="1" height="10" /></div>' +
	'<div id="'+ eTable + '" class="hide">' +
		'<form name="' + eForm + '">' +
		'<div class="moreInfo">' +
			'<div class="moreInfoBar">' +
				'<div class="infoTitle">Edit Provider Debt</div>' +
				'<div class="infoActions">' +
					'<a href="javascript:success(\'' + eSave + '\');" class="strong">Save</a>&nbsp;&#124;&nbsp;<a href="javascript:resetForm(\'' + eForm + '\');hideMe(\'message_\')">Reset</a>&nbsp;&#124;&nbsp;<a href="javascript:deleteRow(\'' + delBox + '\',\'' + eTable + '\')">Delete</a>&nbsp;&#124;&nbsp;<a href="javascript:radioToggle(\'' + eTable + '\', 0);resetForm(\'' + eForm + '\');">Cancel</a>' +
				'</div>' +
				'<div class="clear"></div>' +
			'</div>' +
			'<div class="moreInfoContent">' +
				'<div>' +
					'<script language="javascript">' +
						'writeMessage(path, "' + eSave + '")' +
					'</script>' +
				'</div>' +
				'<div style="width:100%">' +
					'<table cellspacing="0" width="100%">' +
						'<tr>' +
							'<td>' +
								'<div class="padb">' +
									'<span class="required">*</span><label>Debt Obligee</label><br />' +
									'<input type="text" size="25 maxlength="10" value="06/20/2006" />' +
								'</div>' +
							'</td>' +
							'<td>' +
								'<div class="padb">' +
									'<span class="required">*</span><label>Purpose of Debt</label><br />' +
									'<input type="text" size="25 maxlength="10" value="purpose 1" />' +
								'</div>' +
							'</td>' +
							'<td>' +
								'<div class="padb">' +
									'<label>Monthly Payment</label><br />' +
									'$<input class="money" type="text" size="15" name="" value="100.00"/>' +
								'</div>' +
							'</td>' +
							'<td>' +
								'<div class="padb">' +
									'<label>Original Date of Debt/Note</label><br />' +
									'<input type="text" size="10 maxlength="10" value="06/20/2005" />&nbsp;<img src="' + path + 'images/cal.gif" alt="Enter Batch Date" align="absmiddle" height="16" width="16" />' +
								'</div>' +
							'</td>' +
						'</tr>' +
						'<tr>' +
							'<td>' +
								'<div class="padb">' +
									'<label>Origianl Amount of Debt</label><br />' +
									'$<input class="money" type="text" size="15" name="" value="100.00"/>' +
								'</div>' +
							'</td>' +
							'<td>' +
								'<div class="padb">' +
									'<label>Balance of Debt</label><br />' +
									'$<input class="money" type="text" size="15" name="" value="100.00"/>' +
								'</div>' +
							'</td>' +
							'<td>' +
								'<div class="padb">' +
									'<label>Debt Maturity Date</label><br />' +
									'<input type="text" size="10 maxlength="10" value="06/20/2005" />&nbsp;<img src="' + path + 'images/cal.gif" alt="Enter Batch Date" align="absmiddle" height="16" width="16" />' +
								'</div>' +
							'</td>' +
							'<td>' +
								'<div class="padb">' +
									'<label>Interest for Period</label><br />' +
									'<input class="money" type="text" size="5" name="" value="11"/>%' +
								'</div>' +
							'</td>' +
						'</tr>' +
						'<tr>' +
							'<td colspan="5">' +
								'<div><img src="' + path + 'images/x.gif" border="0" width="1" height="10" alt="" /></div>' +
								'<div class="question">Are the obligee and the provider related to each other through common ownership and/or control?</div>' +
								'<div class="answer"><input type="radio" name="related" checked="checked" />&nbsp;Yes&nbsp;&nbsp;&nbsp;<input type="radio" name="related" />&nbsp;No</div>' +
							'</td>' +
						'</tr>' +
					'</table>' +
					'<script language="javascript">' +
						'writeAuditSection(path, \'liAudit' + str + '\')' +
					'</script>' +
				'</div>' +
			'</div>' +
		'</div>' +
	'</form>' +
'</div>' +
'<div id="'+ nTable + '" class="hide">' +
		'<form name="' + nForm + '">' +
		'<div class="moreInfo">' +
			'<div class="moreInfoBar">' +
				'<div class="infoTitle">New Provider Debt</div>' +
				'<div class="infoActions">' +
					'<a href="javascript:success(\'' + nSave + '\');" class="strong">Save</a>&nbsp;&#124;&nbsp;<a href="javascript:resetForm(\'' + nForm + '\');hideMe(\'message_\')">Reset</a>&nbsp;&#124;&nbsp;<a href="javascript:radioToggle(\'' + nTable + '\', 0);resetForm(\'' + nForm + '\');">Cancel</a>' +
				'</div>' +
				'<div class="clear"></div>' +
			'</div>' +
			'<div class="moreInfoContent">' +
				'<div>' +
					'<script language="javascript">' +
						'writeMessage(path, "' + nSave + '")' +
					'</script>' +
				'</div>' +
				'<div style="width:100%">' +
					'<table cellspacing="0" width="100%">' +
						'<tr>' +
							'<td>' +
								'<div class="padb">' +
									'<span class="required">*</span><label>Debt Obligee</label><br />' +
									'<input type="text" size="25 maxlength="10" value="" />' +
								'</div>' +
							'</td>' +
							'<td>' +
								'<div class="padb">' +
									'<span class="required">*</span><label>Purpose of Debt</label><br />' +
									'<input type="text" size="25 maxlength="10" value="" />' +
								'</div>' +
							'</td>' +
							'<td>' +
								'<div class="padb">' +
									'<label>Monthly Payment</label><br />' +
									'$<input class="money" type="text" size="15" name="" value=""/>' +
								'</div>' +
							'</td>' +
							'<td>' +
								'<div class="padb">' +
									'<label>Original Date of Debt/Note</label><br />' +
									'<input type="text" size="10 maxlength="10" value="" />&nbsp;<img src="' + path + 'images/cal.gif" alt="Enter Batch Date" align="absmiddle" height="16" width="16" />' +
								'</div>' +
							'</td>' +
						'</tr>' +
						'<tr>' +
							'<td>' +
								'<div class="padb">' +
									'<label>Origianl Amount of Debt</label><br />' +
									'$<input class="money" type="text" size="15" name="" value=""/>' +
								'</div>' +
							'</td>' +
							'<td>' +
								'<div class="padb">' +
									'<label>Balance of Debt</label><br />' +
									'$<input class="money" type="text" size="15" name="" value=""/>' +
								'</div>' +
							'</td>' +
							'<td>' +
								'<div class="padb">' +
									'<label>Debt Maturity Date</label><br />' +
									'<input type="text" size="10 maxlength="10" value="" />&nbsp;<img src="' + path + 'images/cal.gif" alt="Enter Batch Date" align="absmiddle" height="16" width="16" />' +
								'</div>' +
							'</td>' +
							'<td>' +
								'<div class="padb">' +
									'<label>Interest for Period</label><br />' +
									'<input class="money" type="text" size="5" name="" value=""/>%' +
								'</div>' +
							'</td>' +
						'</tr>' +
						'<tr>' +
							'<td colspan="5">' +
								'<div><img src="' + path + 'images/x.gif" border="0" width="1" height="10" alt="" /></div>' +
								'<div class="question">Are the obligee and the provider related to each other through common ownership and/or control?</div>' +
								'<div class="answer"><input type="radio" name="related" />&nbsp;Yes&nbsp;&nbsp;&nbsp;<input type="radio" name="related" />&nbsp;No</div>' +
							'</td>' +
						'</tr>' +
					'</table>' +
				'</div>' +
			'</div>' +
		'</div>' +
	'</form>' +
'</div>';


// end_var_declaration


document.write(debtTable);

tigra_tables(tabId, 1, 1, 'row_alt', 'row', 'row_mouse', 'row_selected');
str++
}

//  SCHEDULE F
function writeOrigBuildings(path,action,listVar){

	var eTable = 'e'+ str + '_table';
	var eForm = 'eForm'+ str;
	var eSave = 'eSave'+ str;
	var delBox = 'del' + str;
	
	var nTable = 'n'+ str + '_table';
	var nForm = 'nForm'+ str;
	var nSave = 'nSave'+ str;
	
	var tabId = 'demTable' + str;
	var toggleBox = 'tBox' + str
	
	
buildingTable =
'<fieldset class="inner">' +
	'<legend>Original Building and Additions</legend>' +
	'<table width="100%">' +
		'<tr>' +
			'<td>' +
				'<script language="javascript">' +
					'writeMessage(path, "' + delBox+ '")' +
				'</script>' +
			'</td>' +
		'</tr>' +
	'</table>' +
	'<table width="100%" class="tableBar">' +
		'<tr>' +
			'<td class="tableTitle">&nbsp;</td>' +
			'<td class="tableAction"><input type="button" class="formBtn" value="Add Building or Addition" onClick="radioToggle(\'' + nTable + '\', 1);radioToggle(\'' + eTable + '\', 0)" /></td>' +
		'</tr>' +
	'</table>' +
	'<table width="100%" id="' + tabId + '" cellspacing="0" class="dataTable">' +
		'<tr class="tableHead">' +
			'<th scope="col">Type&nbsp;<img src="' + path + 'images/sort_desc.gif" alt="Sort Descending" align="absmiddle" /></th>' +
			'<th scope="col">Year Constructed&nbsp;<img src="' + path + 'images/sort_desc.gif" alt="Sort Descending" align="absmiddle" /></th>' +
			'<th class="rightCell"># of Beds&nbsp;<img src="' + path + 'images/sort_asds.gif" alt="Sort" align="absmiddle" /></th>' +
			'<th scope="col">Date of Lease&nbsp;<img src="' + path + 'images/sort_asds.gif" alt="Sort" align="absmiddle" /></th>' +
			'<th class="rightCell">Rental Amount&nbsp;<img src="' + path + 'images/sort_asds.gif" alt="Sort" align="absmiddle" /></th>' +
			'<th class="rightCell">Total Years of Lease&nbsp;<img src="' + path + 'images/sort_asds.gif" alt="Sort" align="absmiddle" /></th>' +
			'<th class="rightCell">Total Years Renewal Option&nbsp;<img src="' + path + 'images/sort_asds.gif" alt="Sort" align="absmiddle" /></th>' +	
		'</tr>' +	
		'<tr onclick="radioToggle(\'' + eTable + '\',1);radioToggle(\'' + nTable + '\',0)">' +
			'<td><a href="javascript:radioToggle(\'' + eTable + '\',1);radioToggle(\'' + nTable + '\',0)">BLDG-Building</a></td>' +
			'<td>2005</td>' +	
			'<td class="rightCell">200</td>' +	
			'<td>01/01/2006</td>' +	
			'<td class="rightCell">$10,000.00</td>' +	
			'<td class="rightCell">10</td>' +	
			'<td class="rightCell">20</td>' +	
		'</tr>' +
		'<tr class="row rowStrong">' +
			'<td>Totals</td>' +
			'<td>&nbsp;</td>' +
			'<td>&nbsp;</td>' +
			'<td>&nbsp;</td>' +
			'<td class="rightCell">$10,000.00</td>' +
			'<td>&nbsp;</td>' +
			'<td>&nbsp;</td>' +
		'</tr>' +
	'</table>' +
	'<div class="searchResults">' +
		'<div class="floatl">n - n of n</div>' +
		'<div class="floatr"><a href="javascript:void(0)"><< </a>&nbsp;&nbsp;<a href="javascript:void(0)">1</a>&nbsp;<a href="javascript:void(0)">2</a>&nbsp;<a href="javascript:void(0)">3</a>&nbsp;&nbsp;<a href="javascript:void(0)">>></a></div>' +
		'<div class="clear"></div>' +
	'</div>' +
	'<div><img src="'+ path + 'images/x.gif" border="0" width="1" height="10" /></div>' +
	'<div id="'+ eTable + '" class="hide">' +
		'<form name="' + eForm + '">' +
		'<div class="moreInfo">' +
			'<div class="moreInfoBar">' +
				'<div class="infoTitle">Edit Building or Addition</div>' +
				'<div class="infoActions">' +
					'<a href="javascript:success(\'' + eSave + '\');" class="strong">Save</a>&nbsp;&#124;&nbsp;<a href="javascript:resetForm(\'' + eForm + '\');hideMe(\'message_\')">Reset</a>&nbsp;&#124;&nbsp;<a href="javascript:deleteRow(\'' + delBox + '\',\'' + eTable + '\')">Delete</a>&nbsp;&#124;&nbsp;<a href="javascript:radioToggle(\'' + eTable + '\', 0);resetForm(\'' + eForm + '\');">Cancel</a>' +
				'</div>' +
				'<div class="clear"></div>' +
			'</div>' +
			'<div class="moreInfoContent">' +
				'<div>' +
					'<script language="javascript">' +
						'writeMessage(path, "' + eSave + '")' +
					'</script>' +
				'</div>' +
				'<div style="width:100%">' +
					'<table cellspacing="0" width="100%">' +
						'<tr>' +
							'<td>' +
								'<div class="padb">' +
									'<span class="required">*</span><label>Type</label><br />' +
									'<select id="X" name="">' +
										'<option></option>' +
										'<option selected="selected">BLDG-Building</Option>' +
										'<option>ADDT-Addition</option>' +	
									'</select>' +
								'</div>' +
							'</td>' +
							'<td>' +
								'<div class="padb">' +
									'<span class="required">*</span><label>Year Constructed</label><br />' +
									'<input type="text" size="3" maxlength="5" />' +
								'</div>' +
							'</td>' +
							'<td>' +
								'<div class="padb">' +
									'<span class="required">*</span><label># of Beds</label><br />' +
									'<input type="text" size="6" maxlength="5" />' +
								'</div>' +
							'</td>' +
							'<td>' +
								'<div class="padb">' +
									'<label>Date of Lease</label><br />' +
									'<input type="text" size="9" maxlength="5" />&nbsp;<img src="' + path + 'images/cal.gif" alt="Enter Date" align="absmiddle" height="16" width="16" />' +
								'</div>' +
							'</td>' +
						'</tr>' +
						'<tr>' +
							'<td>' +
								'<div class="padb">' +
									'<label>Rental Amount</label><br />' +
									'$<input type="text" class="money" size="9" maxlength="12" />' +
								'</div>' +
							'</td>' +
						
							'<td>' +
								'<div class="padb">' +
									'<label>Total Years of Lease</label><br />' +
									'<input type="text" size="6" maxlength="5" />' +
								'</div>' +
							'</td>' +
							'<td>' +
								'<div class="padb">' +
									'<label>Total Years Renewal Option</label><br />' +
									'<input type="text" size="6" maxlength="5" />' +
								'</div>' +
							'</td>' +
						'</tr>' +
					'</table>' +
					'<script language="javascript">' +
						'writeAuditSection(path, \'liAudit' + str + '\')' +
					'</script>' +
				'</div>' +
			'</div>' +
		'</div>' +
	'</form>' +
'</div>' +
'<div id="'+ nTable + '" class="hide">' +
		'<form name="' + nForm + '">' +
		'<div class="moreInfo">' +
			'<div class="moreInfoBar">' +
				'<div class="infoTitle">New Building or Addition</div>' +
				'<div class="infoActions">' +
					'<a href="javascript:success(\'' + nSave + '\');" class="strong">Save</a>&nbsp;&#124;&nbsp;<a href="javascript:resetForm(\'' + nForm + '\');hideMe(\'message_\')">Reset</a>&nbsp;&#124;&nbsp;<a href="javascript:radioToggle(\'' + nTable + '\', 0);resetForm(\'' + nForm + '\');">Cancel</a>' +
				'</div>' +
				'<div class="clear"></div>' +
			'</div>' +
			'<div class="moreInfoContent">' +
				'<div>' +
					'<script language="javascript">' +
						'writeMessage(path, "' + nSave + '")' +
					'</script>' +
				'</div>' +
				'<div style="width:100%">' +
					'<table cellspacing="0" width="100%">' +
						'<tr>' +
							'<td>' +
								'<div class="padb">' +
									'<span class="required">*</span><label>Type</label><br />' +
									'<select id="X" name="">' +
										'<option></option>' +
										'<option>BLDG-Building</Option>' +
										'<option>ADDT-Addition</option>' +	
									'</select>' +
								'</div>' +
							'</td>' +
							'<td>' +
								'<div class="padb">' +
									'<span class="required">*</span><label>Year Constructed</label><br />' +
									'<input type="text" size="3" maxlength="5" />' +
								'</div>' +
							'</td>' +
							'<td>' +
								'<div class="padb">' +
									'<span class="required">*</span><label># of Beds</label><br />' +
									'<input type="text" size="6" maxlength="5" />' +
								'</div>' +
							'</td>' +
							'<td>' +
								'<div class="padb">' +
									'<label>Date of Lease</label><br />' +
									'<input type="text" size="9" maxlength="5" />&nbsp;<img src="' + path + 'images/cal.gif" alt="Enter Date" align="absmiddle" height="16" width="16" />' +
								'</div>' +
							'</td>' +
						'</tr>' +
						'<tr>' +
							'<td>' +
								'<div class="padb">' +
									'<label>Rental Amount</label><br />' +
									'$<input type="text" class="money" size="9" maxlength="12" />' +
								'</div>' +
							'</td>' +
						
							'<td>' +
								'<div class="padb">' +
									'<label>Total Years of Lease</label><br />' +
									'<input type="text" size="6" maxlength="5" />' +
								'</div>' +
							'</td>' +
							'<td>' +
								'<div class="padb">' +
									'<label>Total Years Renewal Option</label><br />' +
									'<input type="text" size="6" maxlength="5" />' +
								'</div>' +
							'</td>' +
						'</tr>' +
					'</table>' +
				'</div>' +
			'</div>' +
		'</div>' +
	'</form>' +
'</div>' +
'</fieldset>';


document.write(buildingTable);

tigra_tables(tabId, 1, 1, 'row_alt', 'row', 'row_mouse', 'row_selected');
str++
}

function writeVehicleRental(path,action,listVar){

	var eTable = 'e'+ str + '_table';
	var eForm = 'eForm'+ str;
	var eSave = 'eSave'+ str;
	var delBox = 'del' + str;
	
	var nTable = 'n'+ str + '_table';
	var nForm = 'nForm'+ str;
	var nSave = 'nSave'+ str;
	
	var tabId = 'demTable' + str;
	var toggleBox = 'tBox' + str
	
	
vehicleRentalTable =
	
	'<table width="100%">' +
		'<tr>' +
			'<td>' +
				'<script language="javascript">' +
					'writeMessage(path, "' + delBox+ '")' +
				'</script>' +
			'</td>' +
		'</tr>' +
	'</table>' +
	'<table width="100%" class="tableBar">' +
		'<tr>' +
			'<td class="tableTitle">&nbsp;</td>' +
			'<td class="tableAction"><input type="button" class="formBtn" value="Add Vehicle Rental" onClick="radioToggle(\'' + nTable + '\', 1);radioToggle(\'' + eTable + '\', 0)" /></td>' +
		'</tr>' +
	'</table>' +
	'<table width="100%" id="' + tabId + '" cellspacing="0" class="dataTable">' +
		'<tr class="tableHead">' +
			'<th scope="col">Use&nbsp;<img src="' + path + 'images/sort_desc.gif" alt="Sort Descending" align="absmiddle" /></th>' +
			'<th scope="col">Model, Make & Year&nbsp;<img src="' + path + 'images/sort_asds.gif" alt="Sort" align="absmiddle" /></th>' +
			'<th scope="col" class="moneyHeader">Monthly Lease Payment&nbsp;<img src="' + path + 'images/sort_asds.gif" alt="Sort" align="absmiddle" /></th>' +
			'<th scope="col" class="moneyHeader">Rental Expense for This Period&nbsp;<img src="' + path + 'images/sort_asds.gif" alt="Sort" align="absmiddle" /></th>' +	
		'</tr>' +	
		'<tr onclick="radioToggle(\'' + eTable + '\',1);radioToggle(\'' + nTable + '\',0)">' +
			'<td><a href="javascript:radioToggle(\'' + eTable + '\',1);radioToggle(\'' + nTable + '\',0)">use 1</a></td>' +
			'<td>Ford F150, 2006</td>' +
			'<td class="moneyCell">$100.00</td>' +
			'<td class="moneyCell">$100.00</td>' +
		'</tr>' +
		'<tr class="row rowStrong">' +
			'<td>Totals</td>' +
			'<td>&nbsp;</td>' +
			'<td class="moneyCell">$100.00</td>' +
			'<td class="moneyCell">$100.00</td>' +
		'</tr>' +
	'</table>' +
	'<div class="searchResults">' +
		'<div class="floatl">n - n of n</div>' +
		'<div class="floatr"><a href="javascript:void(0)"><< </a>&nbsp;&nbsp;<a href="javascript:void(0)">1</a>&nbsp;<a href="javascript:void(0)">2</a>&nbsp;<a href="javascript:void(0)">3</a>&nbsp;&nbsp;<a href="javascript:void(0)">>></a></div>' +
		'<div class="clear"></div>' +
	'</div>' +
	'<div><img src="'+ path + 'images/x.gif" border="0" width="1" height="10" /></div>' +
	'<div id="'+ eTable + '" class="hide">' +
		'<form name="' + eForm + '">' +
		'<div class="moreInfo">' +
			'<div class="moreInfoBar">' +
				'<div class="infoTitle">Edit Vehicle Rental</div>' +
				'<div class="infoActions">' +
					'<a href="javascript:success(\'' + eSave + '\');" class="strong">Save</a>&nbsp;&#124;&nbsp;<a href="javascript:resetForm(\'' + eForm + '\');hideMe(\'message_\')">Reset</a>&nbsp;&#124;&nbsp;<a href="javascript:deleteRow(\'' + delBox + '\',\'' + eTable + '\')">Delete</a>&nbsp;&#124;&nbsp;<a href="javascript:radioToggle(\'' + eTable + '\', 0);resetForm(\'' + eForm + '\');">Cancel</a>' +
				'</div>' +
				'<div class="clear"></div>' +
			'</div>' +
			'<div class="moreInfoContent">' +
				'<div>' +
					'<script language="javascript">' +
						'writeMessage(path, "' + eSave + '")' +
					'</script>' +
				'</div>' +
				'<div style="width:100%">' +
					'<table cellspacing="0" width="100%">' +
						'<tr>' +
							'<td>' +
								'<div class="padb">' +
									'<span class="required">*</span><label>Use</label><br />' +
									'<input type="text" size="15" maxlength="25" />' +
								'</div>' +
							'</td>' +
							'<td>' +
								'<div class="padb">' +
									'<span class="required">*</span><label>Model, Make & Year</label><br />' +
									'<input type="text" size="15" maxlength="25" />' +
								'</div>' +
							'</td>' +
							'<td>' +
								'<div class="padb">' +
									'<label>Monthly Lease Payment</label><br />' +
									'$<input type="text" class="money" size="12" maxlength="12" />' +
								'</div>' +
							'</td>' +
						
							'<td>' +
								'<div class="padb">' +
									'<label>Rental Expense for This Period</label><br />' +
									'$<input type="text" class="money" size="12" maxlength="12" />' +
								'</div>' +
							'</td>' +
						'</tr>' +
					'</table>' +
					'<script language="javascript">' +
						'writeAuditSection(path, \'liAudit' + str + '\')' +
					'</script>' +
				'</div>' +
			'</div>' +
		'</div>' +
	'</form>' +
'</div>' +
'<div id="'+ nTable + '" class="hide">' +
		'<form name="' + nForm + '">' +
		'<div class="moreInfo">' +
			'<div class="moreInfoBar">' +
				'<div class="infoTitle">New Vehicle Rental</div>' +
				'<div class="infoActions">' +
					'<a href="javascript:success(\'' + nSave + '\');" class="strong">Save</a>&nbsp;&#124;&nbsp;<a href="javascript:resetForm(\'' + nForm + '\');hideMe(\'message_\')">Reset</a>&nbsp;&#124;&nbsp;<a href="javascript:radioToggle(\'' + nTable + '\', 0);resetForm(\'' + nForm + '\');">Cancel</a>' +
				'</div>' +
				'<div class="clear"></div>' +
			'</div>' +
			'<div class="moreInfoContent">' +
				'<div>' +
					'<script language="javascript">' +
						'writeMessage(path, "' + nSave + '")' +
					'</script>' +
				'</div>' +
				'<div style="width:100%">' +
					'<table cellspacing="0" width="100%">' +
						'<tr>' +
							'<td>' +
								'<div class="padb">' +
									'<span class="required">*</span><label>Use</label><br />' +
									'<input type="text" size="15" maxlength="25" />' +
								'</div>' +
							'</td>' +
							'<td>' +
								'<div class="padb">' +
									'<span class="required">*</span><label>Model, Make & Year</label><br />' +
									'<input type="text" size="15" maxlength="25" />' +
								'</div>' +
							'</td>' +
							'<td>' +
								'<div class="padb">' +
									'<label>Monthly Lease Payment</label><br />' +
									'$<input type="text" class="money" size="12" maxlength="12" />' +
								'</div>' +
							'</td>' +
						
							'<td>' +
								'<div class="padb">' +
									'<label>Rental Expense for This Period</label><br />' +
									'$<input type="text" class="money" size="12" maxlength="12" />' +
								'</div>' +
							'</td>' +
						'</tr>' +
					'</table>' +
				'</div>' +
			'</div>' +
		'</div>' +
	'</form>' +
'</div>';


// end_var_declaration


document.write(vehicleRentalTable);

tigra_tables(tabId, 1, 1, 'row_alt', 'row', 'row_mouse', 'row_selected');
str++
}


//  SCHEDULE G
function writeOwnersOfficers(path,action,listVar){

	var eTable = 'e'+ str + '_table';
	var eForm = 'eForm'+ str;
	var eSave = 'eSave'+ str;
	var delBox = 'del' + str;
	
	var nTable = 'n'+ str + '_table';
	var nForm = 'nForm'+ str;
	var nSave = 'nSave'+ str;
	
	var tabId = 'demTable' + str;
	var toggleBox = 'tBox' + str
	
	
ownersTable =

	'<table width="100%">' +
		'<tr>' +
			'<td>' +
				'<script language="javascript">' +
					'writeMessage(path, "' + delBox+ '")' +
				'</script>' +
			'</td>' +
		'</tr>' +
	'</table>' +
	'<table width="100%" class="tableBar">' +
		'<tr>' +
			'<td class="tableTitle">Owners / Officers</td>' +
			'<td class="tableAction"><input type="button" class="formBtn" value="Add Owner / Officer" onClick="radioToggle(\'' + nTable + '\', 1);radioToggle(\'' + eTable + '\', 0)" /></td>' +
		'</tr>' +
	'</table>' +
	'<table width="100%" id="' + tabId + '" cellspacing="0" class="dataTable">' +
		'<tr class="tableHead">' +
			'<th scope="col" rowspan="2">Last Name, First Name, MI&nbsp;<img src="' + path + 'images/sort_desc.gif" alt="Sort Descending" align="absmiddle" /></th>' +
			'<th scope="col" rowspan="2">Title&nbsp;<img src="' + path + 'images/sort_asds.gif" alt="Sort Descending" align="absmiddle" /></th>' +
			'<th scope="col" rowspan="2">Function&nbsp;<img src="' + path + 'images/sort_asds.gif" alt="Sort Descending" align="absmiddle" /></th>' +
			'<th rowspan="2" class="moneyHeader">Ownership Interest&nbsp;<img src="' + path + 'images/sort_asds.gif" alt="Sort Descending" align="absmiddle" /></th>' +
			'<th rowspan="2" class="moneyHeader">Payments Received From Other Businesses&nbsp;<img src="' + path + 'images/sort_asds.gif" alt="Sort" align="absmiddle" /></th>' +
			'<th colspan="2" class="centerCell">Avg Hrs per Work Week Devoted to this Business and % of Total Work Week</th>' +
			'<th colspan="2" class="centerCell">Compensation Included in Costs for this Reporting Period</th>' +
		'</tr>' +	
		'<tr>' +
			'<th class="moneyHeader">Hours&nbsp;<img src="' + path + 'images/sort_asds.gif" alt="Sort" align="absmiddle" /></th>' +
			'<th class="moneyHeader">Percent&nbsp;<img src="' + path + 'images/sort_asds.gif" alt="Sort" align="absmiddle" /></th>' +
			'<th>Description&nbsp;<img src="' + path + 'images/sort_asds.gif" alt="Sort" align="absmiddle" /></th>' +
			'<th class="moneyHeader">Amount&nbsp;<img src="' + path + 'images/sort_asds.gif" alt="Sort" align="absmiddle" /></th>' +
		'</tr>' +
		'<tr onclick="radioToggle(\'' + eTable + '\',1);radioToggle(\'' + nTable + '\',0)">' +
			'<td><a href="javascript:radioToggle(\'' + eTable + '\',1);radioToggle(\'' + nTable + '\',0)">Mary B. Smith</a></td>' +
			'<td>President</td>' +
			'<td>&nbsp;</td>' +
			'<td class="moneyCell">51%</td>' +
			'<td class="moneyCell">$1,000.00</td>' +
			'<td class="moneyCell">40</td>' +
			'<td class="moneyCell">90%</td>' +
			'<td>comp</td>' +
			'<td class="moneyCell">$0.00</td>' +
		'</tr>' +
		'<tr class="row rowStrong">' +
			'<td colspan="8">Total&nbsp;</td>' +
			'<td class="rightCell">$0.00</td>' +
		'</tr>' +
	'</table>' +
	'<div class="searchResults">' +
		'<div class="floatl">n - n of n</div>' +
		'<div class="floatr"><a href="javascript:void(0)"><< </a>&nbsp;&nbsp;<a href="javascript:void(0)">1</a>&nbsp;<a href="javascript:void(0)">2</a>&nbsp;<a href="javascript:void(0)">3</a>&nbsp;&nbsp;<a href="javascript:void(0)">>></a></div>' +
		'<div class="clear"></div>' +
	'</div>' +
	'<div><img src="'+ path + 'images/x.gif" border="0" width="1" height="10" /></div>' +
	'<div id="'+ eTable + '" class="hide">' +
		'<form name="' + eForm + '">' +
		'<div class="moreInfo">' +
			'<div class="moreInfoBar">' +
				'<div class="infoTitle">Edit Owner / Officer</div>' +
				'<div class="infoActions">' +
					'<a href="javascript:success(\'' + eSave + '\');" class="strong">Save</a>&nbsp;&#124;&nbsp;<a href="javascript:resetForm(\'' + eForm + '\');hideMe(\'message_\')">Reset</a>&nbsp;&#124;&nbsp;<a href="javascript:deleteRow(\'' + delBox + '\',\'' + eTable + '\')">Delete</a>&nbsp;&#124;&nbsp;<a href="javascript:radioToggle(\'' + eTable + '\', 0);resetForm(\'' + eForm + '\');">Cancel</a>' +
				'</div>' +
				'<div class="clear"></div>' +
			'</div>' +
			'<div class="moreInfoContent">' +
				'<div>' +
					'<script language="javascript">' +
						'writeMessage(path, "' + eSave + '")' +
					'</script>' +
				'</div>' +
				'<div style="width:100%">' +
					'<table cellspacing="0" width="100%">' +
						'<tr>' +
							'<td>' +
								'<div class="padb">' +
									'<span class="required">*</span><label>Last Name, First Name, MI</label><br />' +
									'<input type="text" size="30" maxlength="5" />' +
								'</div>' +
							'</td>' +
							'<td>' +
								'<div class="padb">' +
									'<span class="required">*</span><label>Title</label><br />' +
									'<input type="text" size="30" maxlength="5" />' +
								'</div>' +
							'</td>' +
							'<td>' +
								'<div class="padb">' +
									'<span class="required">*</span><label>Function</label><br />' +
									'<input type="text" size="30" maxlength="5" />' +
								'</div>' +
							'</td>' +
							'<td>' +
								'<div class="padb">' +
									'<span class="required">*</span><label>Ownership Interest</label><br />' +
									'<input type="text" size="3" maxlength="5" />%' +
								'</div>' +
							'</td>' +
						'</tr>' +
						'<tr>' +
							'<td>' +
								'<div class="padb">' +
									'<span class="required">*</span><label>Payments Received From Other Businesses</label><br />' +
									'$<input type="text" class="money" size="9" maxlength="12" />' +
								'</div>' +
							'</td>' +
							'<td>' +
								'<div class="padb">' +
									'<label>Avg Hrs per Work Week Devoted to this Business</label><br />' +
									'<input type="text" size="9" maxlength="5" />' +
								'</div>' +
							'</td>' +
							'<td>' +
								'<div class="padb">' +
									'<label>% of Total Work Week Devoted to this Business</label><br />' +
									'<input type="text" size="9" maxlength="5" />%' +
								'</div>' +
							'</td>' +
						'</tr>' +
						'<tr>' +
							'<td colspan="3"><h3>Compensation Included in Costs for this Reporting Period</h3></td>' +
						'</tr>' +
						'<tr>' +
							'<td>' +
								'<div class="padb">' +
									'<label>Description of Compensation</label><br />' +
									'<input type="text" class="money" size="30" maxlength="35" />' +
								'</div>' +
							'</td>' +
						
							'<td>' +
								'<div class="padb">' +
									'<label>Amount of Compensation</label><br />' +
									'$<input type="text" class="money" size="12" maxlength="15" />' +
								'</div>' +
							'</td>' +
						'</tr>' +
					'</table>' +
					'<script language="javascript">' +
						'writeAuditSection(path, \'liAudit' + str + '\')' +
					'</script>' +
				'</div>' +
			'</div>' +
		'</div>' +
	'</form>' +
'</div>' +
'<div id="'+ nTable + '" class="hide">' +
		'<form name="' + nForm + '">' +
		'<div class="moreInfo">' +
			'<div class="moreInfoBar">' +
				'<div class="infoTitle">New Owner / Officer</div>' +
				'<div class="infoActions">' +
					'<a href="javascript:success(\'' + nSave + '\');" class="strong">Save</a>&nbsp;&#124;&nbsp;<a href="javascript:resetForm(\'' + nForm + '\');hideMe(\'message_\')">Reset</a>&nbsp;&#124;&nbsp;<a href="javascript:radioToggle(\'' + nTable + '\', 0);resetForm(\'' + nForm + '\');">Cancel</a>' +
				'</div>' +
				'<div class="clear"></div>' +
			'</div>' +
			'<div class="moreInfoContent">' +
				'<div>' +
					'<script language="javascript">' +
						'writeMessage(path, "' + nSave + '")' +
					'</script>' +
				'</div>' +
				'<div style="width:100%">' +
					'<table cellspacing="0" width="100%">' +
						'<tr>' +
							'<td>' +
								'<div class="padb">' +
									'<span class="required">*</span><label>Last Name, First Name, MI</label><br />' +
									'<input type="text" size="30" maxlength="5" />' +
								'</div>' +
							'</td>' +
							'<td>' +
								'<div class="padb">' +
									'<span class="required">*</span><label>Title</label><br />' +
									'<input type="text" size="30" maxlength="5" />' +
								'</div>' +
							'</td>' +
							'<td>' +
								'<div class="padb">' +
									'<span class="required">*</span><label>Function</label><br />' +
									'<input type="text" size="30" maxlength="5" />' +
								'</div>' +
							'</td>' +
							'<td>' +
								'<div class="padb">' +
									'<span class="required">*</span><label>Ownership Interest</label><br />' +
									'<input type="text" size="3" maxlength="5" />%' +
								'</div>' +
							'</td>' +
						'</tr>' +
						'<tr>' +
							'<td>' +
								'<div class="padb">' +
									'<span class="required">*</span><label>Payments Received From Other Businesses</label><br />' +
									'$<input type="text" class="money" size="9" maxlength="12" />' +
								'</div>' +
							'</td>' +
							'<td>' +
								'<div class="padb">' +
									'<label>Avg Hrs per Work Week Devoted to this Business</label><br />' +
									'<input type="text" size="9" maxlength="5" />' +
								'</div>' +
							'</td>' +
							'<td>' +
								'<div class="padb">' +
									'<label>% of Total Work Week Devoted to this Business</label><br />' +
									'<input type="text" size="9" maxlength="5" />%' +
								'</div>' +
							'</td>' +
						'</tr>' +
						'<tr>' +
							'<td colspan="3"><h3>Compensation Included in Costs for this Reporting Period</h3></td>' +
						'</tr>' +
						'<tr>' +
							'<td>' +
								'<div class="padb">' +
									'<label>Description of Compensation</label><br />' +
									'<input type="text" class="money" size="30" maxlength="35" />' +
								'</div>' +
							'</td>' +
						
							'<td>' +
								'<div class="padb">' +
									'<label>Amount of Compensation</label><br />' +
									'$<input type="text" class="money" size="12" maxlength="15" />' +
								'</div>' +
							'</td>' +
						'</tr>' +
					'</table>' +
				'</div>' +
			'</div>' +
		'</div>' +
	'</form>' +
'</div>';


document.write(ownersTable);

tigra_tables(tabId, 1, 1, 'row_alt', 'row', 'row_mouse', 'row_selected');
str++
}